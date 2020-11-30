package macro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JFrame;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Server implements Runnable {

	public static final int PORT = 7000;

	Socket socket;
	ServerFrame f;
	String localhost = "";
	ServerSocket serverSocket = null;
	InputStream is = null;
	InputStreamReader isr = null;
	BufferedReader br_isr = null;
	OutputStream os = null;
	OutputStreamWriter osw = null;
	PrintWriter pw = null;
	final Scanner sc = new Scanner(System.in);
		
	String meg;
	public Server(ServerFrame f) {
		this.f=f;
	}
		@Override
		public void run(){
		
			try {
			// 1. Server Socket 생성
			serverSocket = new ServerSocket();

			// 2. Binding : Socket에 SocketAddress(IpAddress + Port) 바인딩 함

			final InetAddress inetAddress = InetAddress.getLocalHost();
			localhost = inetAddress.getHostAddress();

			serverSocket.bind(new InetSocketAddress(localhost, PORT));

			System.out.println("[server] binding " + localhost);
			f.setNum(localhost, PORT);
			f.refresh();
		
			while (true) {
				// 3. accept(클라이언트로 부터 연결요청을 기다림)
				Socket socket = serverSocket.accept();
				this.socket = socket;
				f.setConn(true);
				f.refresh();
				Thread reciv = new Thread(new ServerRec(socket,f));	
				Thread moni = new Thread(new Monitor(socket));
				reciv.start();
				moni.start();
			}

			// 3.accept(클라이언트로 부터 연결요청을 기다림)
			// .. blocking 되면서 기다리는중, connect가 들어오면 block이 풀림
		} catch (Exception e) {
			e.printStackTrace();
			pw.println("에러발생 서버 종료");
		} finally {

			try {

				if (serverSocket != null && !serverSocket.isClosed()) {
					serverSocket.close();
					System.out.println("서버종료");
				}

			} catch (final Exception e) {
				e.printStackTrace();
			}

			sc.close();

		}
			
		}
		public String getIpadress() {
			return this.localhost;
		}
		public int getPortnum() {
			return this.PORT;
		}
		public void sendMsg(String msg) {
			Runnable send = new ServerSend(socket,msg);
			Thread sender = new Thread(send);	
			sender.start();
		}
		public void sendMsg(String[] msg) {
			Runnable send = new ServerSend(socket,msg);
			Thread sender = new Thread(send);	
			sender.start();
		}
}

