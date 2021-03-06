package com.cn.unary.transmit;

import java.util.Arrays;

import com.cn.unary.transmit.client.UnaryTClient;

public class CDataTest {

	private static UnaryTClient client;
	
    public static void main(String[] args) throws Exception {
        byte[] data = new byte[1024*1024];
        Arrays.fill(data,(byte)0xF);
        new Thread(new Runnable() {
			
			@Override
			public void run() {
	        	client = new UnaryTClient("127.0.0.1",8002,"10.10.1.51","1-1-1-1-1-1-1");
	        	client.setprocess(new PTest());
	            try {
	            	client.startClient();
				} catch (Exception e) {}
			}
		}).start();
        Thread.sleep(5000);
        for(byte i =0;i<100;i++) {
        	data[0] = i;
        	if(client == null)
        		continue;
            client.sendMessage(data);  
        }
    }
}