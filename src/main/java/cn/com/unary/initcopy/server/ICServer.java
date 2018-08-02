package cn.com.unary.initcopy.server;

import java.io.IOException;
/**
 * 初始化复制目标端服务的启动类
 * 1.启动内部控制任务GRPC服务
 * 2.设置监听端口，接收外部文件数据包
 * @author shark
 *
 */
public class ICServer {
	private ICServer () {}
	
	public void start () throws IOException {
		
	}

	public void blockUntilShutdown () {
		
	}
	
	public static void activate() throws IOException, InterruptedException  {
		ICServer server = new ICServer();
		server.start();
		server.blockUntilShutdown();
	}
}
