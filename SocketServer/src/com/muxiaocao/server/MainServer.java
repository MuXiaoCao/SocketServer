package com.muxiaocao.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.muxiaocao.serverthread.ServerThread;
import com.muxiaocao.util.DataUtil;

/**
 * socket主服务类，用于协调各server线程
 * @author muxiaocao
 *
 */
public class MainServer {
	// 定义保存所有Socket的Arraylist
	public static ArrayList<Socket> socketList = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(10086);
		while (DataUtil.VIDEOCALL_RUNNING) {
			// 此行代码会阻塞，将一直等待客户端的连接
			Socket s = ss.accept();
			socketList.add(s);
			// 每当客户端连接后启动一条ServerThread线程为客户端服务
			new Thread(new ServerThread(s)).start();
		}
	}
}
