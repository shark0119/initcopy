package cn.com.unary.initcopy.filecopy.io;

import java.io.IOException;
import java.util.List;

import com.cn.unary.initcopy.mock.Request;
import com.cn.unary.initcopy.mock.ServerProcess;

interface InfceFilePackAndAna extends ServerProcess {
	void start (List<String> fileIds) throws IOException;// 开始文件读取打包
	void process(Request req);// 包数据解析IO写入
}
