package com.muxiaocao.serverthread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

import org.json.JSONObject;

import com.muxiaocao.server.MainServer;

public class ServerThread implements Runnable {

	// ���嵱ǰ�߳��������Socket
	private Socket socket;
	// ���߳��������Socket����Ӧ��������
	BufferedReader br = null;

	public ServerThread(Socket socket) throws IOException {
		this.socket = socket;
		// ��ʼ��������
		br = new BufferedReader(new InputStreamReader(socket.getInputStream(),
				"utf-8"));
	}

	@Override
	public void run() {
		String content = null;
		// ����ѭ�����ϴ�socket�ж�ȡ�ͻ��˷��͹���������
		while ((content = readFromClient()) != null) {
			JSONObject jsonObject = new JSONObject(content);
			System.out.println(System.currentTimeMillis() +":"+ socket.hashCode() +jsonObject.toString());
		}
	}

	/**
	 * �����ȡ�ͻ������ݵķ���
	 * @return
	 */
	private String readFromClient() {
		
		try {
			return br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			// ������쳣˵����socket�Ѿ��رգ�����Ҫɾ����socket
			MainServer.socketList.remove(socket);
		}
		return null;
	}

}
