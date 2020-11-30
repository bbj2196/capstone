package macro;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.imageio.ImageIO;

public class Monitor extends Thread {
Socket socket;
	int port;
	
	public Monitor(Socket socket) {
		this.socket = socket;
		this.port=socket.getPort()+1; //����ʹ� ��Ʈ��ȣ�� 1����
	}

	@Override
	public void run(){
		
		try {
			ServerSocket serversocket = new ServerSocket();
			serversocket.bind(new InetSocketAddress(socket.getInetAddress(), port));
			this.socket = serversocket.accept();
			
			
			
			
			Robot r = new Robot();
			while(true) {
				//Moniter���忡�� ������ ������
				InputStream in =socket.getInputStream();
				DataInputStream din =new DataInputStream(in);
				
				//Moniter���忡�� ������ ������
				OutputStream out= socket.getOutputStream();
				DataOutputStream dout= new DataOutputStream(out);

				//��üȭ�� ĸ��
				Rectangle fullrect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
				BufferedImage image = r.createScreenCapture(fullrect);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				
				ImageIO.write(image,"jpeg",baos);
				
				byte[] sendImg = baos.toByteArray();
				
				///�̹��� ���� 
				dout.write(sendImg);
//				dout.write(image);
				
				//��Ʈ�� �ݱ�
				baos.flush();
				baos.close();
				
				out.flush();
				out.close();
				dout.flush();
				dout.close();
				
				in.close();
				din.close();
				
				Thread.sleep(1000);
			}//while ����
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {

		}
	}//run ����
	//�ڷắȯ https://m.blog.naver.com/PostView.nhn?blogId=vphljc&logNo=116544408&proxyReferer=https:%2F%2Fwww.google.com%2F
	//https://jongkyu.tistory.com/116?category=635402
	
}
