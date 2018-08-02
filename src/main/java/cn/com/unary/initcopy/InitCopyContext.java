package cn.com.unary.initcopy;

import java.io.IOException;

import cn.com.unary.initcopy.client.InitCopyGrpcServer;
import cn.com.unary.initcopy.mock.UnaryTransferServer;
import cn.com.unary.initcopy.server.FileDataServerProcess;
import cn.com.unary.initcopy.server.TaskControlGrpcServer;

/**
 * 初始化复制上下文启动类
 * 包括源端和目标端相关参数的初始化
 * @author shark
 */
public class InitCopyContext {
	private InitCopyContext() {}
	
	protected static int transPort;
	protected static int grpcPort; 
	protected static int innerGrpcPort = 6002; 
	
	private static Boolean isActive = Boolean.FALSE;
	private static UnaryTransferServer uts;
	
	private static InitCopyContext icc;
	private static Object lock = new Object();
	
	public static InitCopyContext getInstance () {
		if (icc == null) {
			synchronized (lock) {
				if (icc == null)
					icc = new InitCopyContext();
			}
		}
		return icc;
	}
	public static InitCopyContext getCurrentContext () {
		return null;
	}
	/**
	 * 初始化相关变量；
	 * 启动向外部提供任务管理的 GRPC 服务（源端）；
	 * 启动内部控制任务信息的 GRPC 服务（目标端）；
	 * 启动传输模块（目标端）；
	 * 
	 * @param transPort 文件数据传输端口
	 * @param grpcPort 服务监听的端口
	 * @param innerGrpcPort 内部服务的监听端口 
	 * @throws InterruptedException 
	 * @throws IOException 端口占用
	 */
	public void start (int transPort, int grpcPort, int innerGrpcPort) throws IOException, InterruptedException{
		// 判断上下文是否已经启动
		if (!isActive) {
			synchronized (isActive) {
				if (!isActive) {
					// 初始化相关参数
					InitCopyContext.transPort = transPort;
					InitCopyContext.grpcPort = grpcPort;
					InitCopyContext.innerGrpcPort = innerGrpcPort;
					this.clientInit();
					this.serverInit();
					isActive = Boolean.TRUE;
				}
			}
		}
	}
	
	public void destory () {		uts = null;	}
	public UnaryTransferServer getUts() {		return uts;	}
	
	private void clientInit () throws IOException, InterruptedException {
		// 启动向外部提供任务管理的 GRPC 服务（源端）
		InitCopyGrpcServer.activate(grpcPort);
	}
	private void serverInit () throws IOException, InterruptedException {
		// 启动和初始化传输模块（目标端）
		uts.start(transPort);
		uts.setProcess(new FileDataServerProcess());
		// 启动内部控制任务信息的 GRPC 服务（目标端）
		TaskControlGrpcServer.activate(innerGrpcPort);
	}
}
