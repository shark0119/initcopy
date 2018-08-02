package cn.com.unary.initcopy.filecopy.filepacker;

import java.util.List;

import cn.com.unary.initcopy.mock.UnaryProcess;

public interface FilePackAndResolve extends UnaryProcess {
	/**
	 * 开始文件读取打包，并向目标端发送数据包
	 * @param fileIds 文件的UUID
	 */
	void start (List<String> fileIds);
}
