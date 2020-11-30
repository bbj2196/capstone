package macro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

public class ServerRec implements Runnable{
	Socket socket;
	ServerFrame f;
	public static final String EXIT = "EXIT";
	public ServerRec(Socket socket, ServerFrame f) {
		this.socket = socket;
		this.f=f;
	}
	RWFile rwf = new RWFile();
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			
			while(true) {
				InputStreamReader isr = new InputStreamReader(socket.getInputStream(),"UTF-8");
				BufferedReader br = new BufferedReader(isr);
			String buffer =br.readLine();
			String meg="";
			int cnt=1;
			
			//조건문(첫줄이 이와 같은경우)
			if(buffer.equals(EXIT)) {
				
			}
			else if(buffer.equals("STOP")) {}
			else if(buffer.contentEquals("getlist")) {
				ServerSend sender = new ServerSend(socket,rwf.getCmdList());
				Thread ts = new Thread(sender);
				ts.start();
			}
			//첫줄이 명령문이 아닌경우
			else {
				while((buffer=br.readLine()) !=null) {
					
					meg = meg+ buffer;
					cnt++;
					}
				f.setText(meg);
				f.refresh();
			}
			isr.close();
			br.close();
			}
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}




