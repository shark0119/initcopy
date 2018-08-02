package cn.com.unary.initcopy.filecopy.filepacker;

import java.util.ArrayList;
import java.util.List;

import cn.com.unary.initcopy.InitCopyContext;
import cn.com.unary.initcopy.dao.FileManager;
import cn.com.unary.initcopy.entity.Constants.MODE;
import cn.com.unary.initcopy.entity.FileInfo;
import cn.com.unary.initcopy.filecopy.io.AbstractFileInput;
import cn.com.unary.initcopy.mock.UnaryTransferClient;
import cn.com.unary.initcopy.mock.UnaryTransferServer;

/**
 * 文件打包与解析，同时包括源端打包和目标端解包
 * @author shark
 *
 */
public class SyncAllFilePackAndResolve implements FilePackAndResolve {
	
	private AbstractFileInput input;
	private FileManager ifm;
	private UnaryTransferClient utc;
	private UnaryTransferServer uts;
	private static int MAX_PACK_SIZE;
	
	public SyncAllFilePackAndResolve (MODE mode) {
		switch (mode) {
		case SERVER :
			uts = InitCopyContext.getCurrentContext().getUts();
			uts.setProcess(this);
			break;
		case CLIENT :
			MAX_PACK_SIZE = utc.getMaxPackSize();
			break;
			default:
				throw new IllegalStateException("ERROR CODE 0X02: inproper file mode use");
		}
	}
	@Override
	public void start(List<String> fileIds){
		List<FileInfo> fis = ifm.query(fileIds.toArray(new String[fileIds.size()]));
		List<String> fileNames = new ArrayList<>();
		for (FileInfo fi : fis) {
			fileNames.add(fi.getFullName());
			input.setFile(fi.getFullName());
			// TODO 读数据与打包
			byte[] fileData = input.read(MAX_PACK_SIZE);
			utc.sendData(fileData);
		}
	}

	/**
	 * 目标端解包
	 */
	@Override
	public void process(byte[] data) {
		// TODO 客户端是否有必要处理数据
	}
}
