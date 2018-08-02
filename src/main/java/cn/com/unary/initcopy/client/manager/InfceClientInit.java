package cn.com.unary.initcopy.client.manager;

import java.io.IOException;
import java.util.List;

import cn.com.unary.initcopy.entity.InitOption;
/**
 * 客户端初始化，包括文件列表
 * @author shark
 *
 */
public interface InfceClientInit {

	/**
	 * 开始进行客户端初始化，首先将所有选定的文件进行校验码和UUID的生成，读取大小，
	 * 修改时间等相关信息。发送到服务端进行确认，服务端根据给定的传输策略进行核实，返回需要同步的文件列表。
	 * 直到初始化完成后，函数才会返回。
	 * @param serverIp 目标端IP
	 * @param port	目标端端口
	 * @param fileList 待同步的文件列表
	 * @param options 同步附加选项
	 * @return 需要同步的文件Id 集合
	 * @throws IOException 
	 */
	List<String> startInit(String serverIp, int port, List<String> fileNames, InitOption options) throws IOException;
	
	/**
	 * 接受目标端返回的数据，并进行业务处理。
	 * @param response
	 */
	void setServerResp (byte[] response);
	
}
