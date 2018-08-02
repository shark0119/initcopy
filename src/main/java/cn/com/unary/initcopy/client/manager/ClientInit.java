package cn.com.unary.initcopy.client.manager;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.com.unary.initcopy.dao.InfceFileManager;
import cn.com.unary.initcopy.entity.BaseFileInfo;
import cn.com.unary.initcopy.entity.Constants.GlobalVar;
import cn.com.unary.initcopy.entity.Constants.PackType;
import cn.com.unary.initcopy.entity.FileInfo;
import cn.com.unary.initcopy.entity.InitOption;
import cn.com.unary.initcopy.grpc.entity.ClientInitReq;
import cn.com.unary.initcopy.mock.Mock;
import cn.com.unary.initcopy.mock.Mock.Transfer;

public class ClientInit implements InfceClientInit {

	private CountDownLatch latch ;
	private Transfer transfer = Mock.newTransfer();
	private InfceFileManager ifm = Mock.getIFM();
	private List<String> syncFileIds;
	private List<byte[]> responses;
	private InitOption option;
	private String serverIp;
	private int port;
	
	@Override
	public List<String> startInit(
			String serverIp, int port, 
			List<String> fileNames, InitOption option)
			throws IOException {
		this.serverIp = serverIp;
		this.port = port;
		this.option = option;
		List<BaseFileInfo> bfis = loadFiles (fileNames);
		ifm.save(new FileInfo());
		return initServer (bfis);
	}

	/**
	 * 加载指定路径下所有的文件，并包装为 BaseFileInfo 集合
	 * @param fileNames 如果是个文件夹，则遍历该文件夹下所有文件 
	 * @return
	 * @throws IOException 
	 */
	protected List<BaseFileInfo> loadFiles (List<String> fileNames) throws IOException {
		List<BaseFileInfo> bfis = new ArrayList<>();
		Path path;
		for (String fileName : fileNames) {
			path = Paths.get(fileName);
			Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
				@Override
				public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) 
						throws IOException {
					if (dir.toFile().list().length == 0) {
						BaseFileInfo bfi = new BaseFileInfo();
						bfi.setFileSize(dir.toFile().length());
						bfi.setId(UUID.randomUUID().toString());
						bfi.setModifyTime(dir.toFile().lastModified());
						/*bfi.setFullName(fileName);
						bfis.add(bfi);	*/					
					}
					return FileVisitResult.CONTINUE;
				}
				// 访问文件使用该方法
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) 
						throws IOException {
					BaseFileInfo bfi = new BaseFileInfo();
					bfi.setFileSize(file.toFile().length());
					bfi.setId(UUID.randomUUID().toString());
					bfi.setModifyTime(file.toFile().lastModified());
					/*bfi.setFullName(fileName);
					bfis.add(bfi);*/
					return FileVisitResult.CONTINUE;
				}
			});
		}		
		return bfis;
	}

	protected List<String> initServer (List<BaseFileInfo> bfis) {
		latch = new CountDownLatch(1);
		// 序列化。
		byte[] bfiByte = null;
		ObjectMapper mapper = new ObjectMapper();
		int totalSize = 0;
		for (BaseFileInfo bfi : bfis) {
			totalSize += bfi.getFileSize();
		}
		ClientInitReq cir = ClientInitReq.newBuilder().build();
		/*cir.setTaskId(option.getTaskId());
		cir.setSyncType(option.getSyncType());
		cir.setTargetDir(option.getTargetDir());
		cir.setFiles(bfis);
		cir.setTotalSize(totalSize);*/
		try {
			bfiByte = mapper.writeValueAsBytes(cir);
			int length = GlobalVar.MAX_PACK_SIZE;
			byte[] data = new byte[length];
			int pos = 0, copyLength;
			for (int packIndex = 0; packIndex <= bfiByte.length / (length-1); packIndex++) {
				try {
					data[0] = PackType.REQ_INIT.getValue();
					copyLength = bfiByte.length - pos > length-1 ? length-1:bfiByte.length-pos ;
					System.arraycopy(bfiByte, pos, data, 1, copyLength);
					pos += copyLength;
					transfer.sendData(data);
				} catch (Exception e) {
					// TODO 传输失败。暂停程序，日志
					throw new IllegalStateException("Server Response Data Error With Code 0x02;", e);
				}
			}
			try {
				latch.await();
			} catch (InterruptedException e) {
				// TODO 程序出错，被中断，暂停程序，日志
				throw new IllegalStateException("Server Response Data Error With Code 0x03;", e);
			}
			return this.syncFileIds;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public void setServerResp(byte[] response) {
		// responses = responses == null ? new ArrayList<>() : responses;
		responses.add(response);
		if (response[1]  ==  responses.size()) {
			int length = 0, pos = 0;
			for (byte[] data : responses) {
				length += (data.length - 2);
			}
			byte [] fileIdByte = new byte[length];
			for (byte[] data : responses) {
				pos += (data.length - 2);
				System.arraycopy(data, 2, fileIdByte, pos, data.length - 2);
			}
			try {
				response = new ObjectMapper().readValue(fileIdByte, new TypeReference<List<String>>() {});
			} catch (Exception e) {
				// TODO 日志，数据包格式错误
				throw new IllegalStateException("Server Response Data Error With Code 0x01;", e);
			}
			this.latch.countDown();
		} else if (response[1] < responses.size()) {
			// TODO 日志，数据包格式错误
			throw new IllegalStateException("Server Response Data Error With Code 0x01;");
		}
	}

}
