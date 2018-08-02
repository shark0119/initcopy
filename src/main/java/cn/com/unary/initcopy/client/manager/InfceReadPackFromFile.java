package cn.com.unary.initcopy.client.manager;

import java.io.IOException;
import java.util.List;

import cn.com.unary.initcopy.entity.FileReadOption;

public interface InfceReadPackFromFile {

	/**
	 * 从已有的文件列表中，将文件打包成指定大小的数据包。
	 * @param packSize
	 * @return 读取结束后，返回NULL值
	 * @throws IOException 文件读取失败
	 */
	public byte[] read (int packSize)throws IOException;
	/**
	 * 注册指定文件列表，读取顺序按照注册顺序
	 * @param fileId
	 * @throws IOException 
	 */ 
	public void registerFileIds (List<String> fileIds) throws IOException;
	/**
	 * 设置读取选项
	 * @param option
	 */
	public void setReadOption (FileReadOption option);
}