package com.muxiaocao.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.muxiaocao.serverthread.ServerThread;
import com.muxiaocao.util.DataUtil;

/**
 * socket�������࣬����Э����server�߳�
 * @author muxiaocao
 *
 */
public class MainServer {
	// ���屣������Socket��Arraylist
	public static ArrayList<Socket> socketList = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(10086);
		while (DataUtil.VIDEOCALL_RUNNING) {
			// ���д������������һֱ�ȴ��ͻ��˵�����
			Socket s = ss.accept();
			socketList.add(s);
			// ÿ���ͻ������Ӻ�����һ��ServerThread�߳�Ϊ�ͻ��˷���
			new Thread(new ServerThread(s)).start();
		}
	}
}
