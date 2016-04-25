package com.muxiaocao.test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.json.JSONObject;

public class Test {
	public static void main(String[] args) throws IOException {
		Socket socket = new Socket();
		InetSocketAddress adrAddress = new InetSocketAddress("127.0.0.1", 10086);
		socket.connect(adrAddress);
		JSONObject jsonObject = new JSONObject();
		while (true) {
			jsonObject.put("key1", "value1");
			jsonObject.put("key2", "value2");
			writeToServer(socket,jsonObject);
			jsonObject.put("key3", "value3");
			writeToServer(socket, jsonObject);
		}
	}

	private static void writeToServer(Socket socket, JSONObject jsonObject) {
		try {
			OutputStream out = socket.getOutputStream();
			out.write((jsonObject.toString() + "\n").getBytes("utf-8"));
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
