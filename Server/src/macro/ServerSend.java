package macro;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerSend implements Runnable{
	Socket socket;
	String meg;
	String[] message;
	boolean flag;
	public ServerSend(Socket socket,String str) {
		this.socket=socket;
		this.meg=str;
	}
	public ServerSend(Socket socket,String[] str) {
		this.socket=socket;
		message=str.clone();
	}
	public ServerSend(Socket socket,int port, String str) throws IOException {
		this.socket = new Socket(socket.getInetAddress(),port);
		this.meg=str;
	}
	public ServerSend(Socket socket,int port, String[] str) throws IOException {
		this.socket = new Socket(socket.getInetAddress(),port);
		this.message=str.clone();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			OutputStream os = socket.getOutputStream();
			//OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
			PrintWriter pw = new PrintWriter(os, true);
			
			if(flag) {
				for(int i=0;i<message.length;i++) {
					pw.write(message[i]+"\n");
				}
			}
			else {
			pw.write(meg);
			}
			//pw.flush();
			//osw.flush();
			pw.close();
			//osw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}