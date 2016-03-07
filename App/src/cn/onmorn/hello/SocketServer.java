package cn.onmorn.hello;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
	public static void main(String[] args) {
		SocketServer server = new SocketServer();
		server.start();

	}

	public void start() {
		try {
			ServerSocket serverSocket = new ServerSocket(1234);
			while (true) {
				Socket socket = serverSocket.accept();
				System.out.println("Client " + socket.hashCode() + "  connect");
			}

		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} finally {

		}
	}

	public void socketCoonnect(Socket socket) {
		
		
	}
}
