package com.muxiaocao.serverthread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

import org.json.JSONObject;

import com.muxiaocao.server.MainServer;

public class ServerThread implements Runnable {

	// 定义当前线程所处理的Socket
	private Socket socket;
	// 该线程所处理的Socket所对应的输入流
	BufferedReader br = null;

	public ServerThread(Socket socket) throws IOException {
		this.socket = socket;
		// 初始化输入流
		br = new BufferedReader(new InputStreamReader(socket.getInputStream(),
				"utf-8"));
	}

	@Override
	public void run() {
		String content = null;
		// 采用循环不断从socket中读取客户端发送过来的数据
		while ((content = readFromClient()) != null) {
			JSONObject jsonObject = new JSONObject(content);
			System.out.println(System.currentTimeMillis() +":"+ socket.hashCode() +jsonObject.toString());
		}
	}

	/**
	 * 定义读取客户端数据的方法
	 * @return
	 */
	private String readFromClient() {
		
		try {
			return br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			// 如果有异常说明该socket已经关闭，则需要删除该socket
			MainServer.socketList.remove(socket);
		}
		return null;
	}

}
