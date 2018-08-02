package com.cn.unary.transmit;

import java.util.Arrays;

import com.cn.unary.transmit.server.UnaryTServer;

public class SDataTest {
	
	private static UnaryTServer server;
	
    public static void main(String[] args) throws Exception {
        byte[] data = new byte[250];
        String ip = "10.10.1.51";
        
        Arrays.fill(data,(byte)0xF);
    
        new Thread (new Runnable() {
			
			@Override
			public void run() {

	        	server = new UnaryTServer(8002);
	            server.setDataprocess(new PTest());
	            try {
					server.startServer();
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}
		}).start();
       
        Thread.sleep(10000);
        
        System.out.println("2222222222222222222");
        
        for(byte i =0;i<100;i++) {
        	data[0]=i;
        	if(server == null)
        		continue;
            server.sendMessage(data,ip);
        }
    }
    
}
