package cn.onmorn.hello;

import java.net.Socket;

public class SocketClient {

	public static void main(String[] args) {
		SocketClient clent = new SocketClient();
		clent.start();
	}

	public void start() {
		try {
			Socket socket = new Socket("127.0.0.1",1234);

		} catch (Exception e) {
		}

	};

}
