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
			// 1. Server Socket ����
			serverSocket = new ServerSocket();

			// 2. Binding : Socket�� SocketAddress(IpAddress + Port) ���ε� ��

			final InetAddress inetAddress = InetAddress.getLocalHost();
			localhost = inetAddress.getHostAddress();

			serverSocket.bind(new InetSocketAddress(localhost, PORT));

			System.out.println("[server] binding " + localhost);
			f.setNum(localhost, PORT);
			f.refresh();
		
			while (true) {
				// 3. accept(Ŭ���̾�Ʈ�� ���� �����û�� ��ٸ�)
				Socket socket = serverSocket.accept();
				this.socket = socket;
				f.setConn(true);
				f.refresh();
				Thread reciv = new Thread(new ServerRec(socket,f));	
				Thread moni = new Thread(new Monitor(socket));
				reciv.start();
				moni.start();
			}

			// 3.accept(Ŭ���̾�Ʈ�� ���� �����û�� ��ٸ�)
			// .. blocking �Ǹ鼭 ��ٸ�����, connect�� ������ block�� Ǯ��
		} catch (Exception e) {
			e.printStackTrace();
			pw.println("�����߻� ���� ����");
		} finally {

			try {

				if (serverSocket != null && !serverSocket.isClosed()) {
					serverSocket.close();
					System.out.println("��������");
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

