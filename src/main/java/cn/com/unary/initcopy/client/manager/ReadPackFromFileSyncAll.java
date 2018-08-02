package cn.com.unary.initcopy.client.manager;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.UUID;

import cn.com.unary.initcopy.dao.InfceFileManager;
import cn.com.unary.initcopy.entity.FileInfo;
import cn.com.unary.initcopy.entity.FileReadOption;

public class ReadPackFromFileSyncAll implements InfceReadPackFromFile {

	/**
	 * Key: FileId + Size
	 */
	private List<FileInfo> files;
	private int currentIndex;
	private FileChannel currentFileChannel ;
	private FileReadOption option ;
	private InfceFileManager ifm;
	private byte[] remain;
	
	public ReadPackFromFileSyncAll() {
		this.option = FileReadOption.DEFAULT;
		currentIndex = 0;
	}
	
	@Override
	public byte[] read(int packSize) throws IOException {
		//  文件已读完，也无未发送残留字节
		if (currentIndex == files.size() && remain.length == 0) {
			return null;
		}
		int actualSize=0;
		ByteBuffer buffer ;
		if (packSize < 256*1024)
			buffer = ByteBuffer.allocate(packSize);
		else 
			buffer = ByteBuffer.allocateDirect(packSize);
		actualSize = readRemain (buffer);

		if (remain.length == 0) {
			actualSize = remain.length;
			while (true) {
				if (currentIndex == files.size()) {
					return null;
				}
				Path currentFile = Paths.get(files.get(currentIndex).getFullName());
				currentFileChannel = FileChannel.open(currentFile, StandardOpenOption.READ);
				actualSize += setDataHead (buffer);
				currentIndex ++;
				actualSize += currentFileChannel.read(buffer);
				if (actualSize < packSize) {
					currentFileChannel = null;
				} else {
					break;
				}
			}
		}
		return buffer.array();
	}

	@Override
	public void registerFileIds(List<String> fileIds) throws IOException {
		files = ifm.query(fileIds);
	}
	@Override
	public void setReadOption(FileReadOption option) {
		this.option = (this.option == FileReadOption.DEFAULT) ?
					option == null ? FileReadOption.DEFAULT : option
					: this.option; 
	}
	
	private int readRemain(ByteBuffer buffer) {
		int readSize ;
		if (buffer.remaining() > remain.length) {
			readSize = remain.length;
			buffer.put(remain);
			remain = new byte[0];
		} else {
			readSize = buffer.remaining();
			buffer.put(remain, 0, buffer.remaining());
			byte[] remaining = new byte[remain.length - buffer.remaining()];
			System.arraycopy(remain, readSize, remaining, 0, buffer.remaining());
			remain = remaining;
		}
		return readSize;
	}

	private int setDataHead(ByteBuffer buffer) {
		File file = new File (files.get(currentIndex).getFullName());
		String uuid = UUID.randomUUID().toString();// 36 byte
		String md5Check = "";
		return readRemain(buffer);
	}
}
