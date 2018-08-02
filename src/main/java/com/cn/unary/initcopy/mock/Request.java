package com.cn.unary.initcopy.mock;

public interface Request {
	byte[] receive();
	void send (byte[] data);
}
