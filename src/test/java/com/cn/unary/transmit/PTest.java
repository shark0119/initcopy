package com.cn.unary.transmit;

import com.cn.unary.transmit.utils.Process;

public class PTest implements Process {

	@Override
	public void process(byte[] data) {
		System.out.println("Test received data: " + new String(data));
	}

}
