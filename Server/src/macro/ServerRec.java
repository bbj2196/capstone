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
			
			if(buffer == EXIT) {
				
			}
			else {
				while((buffer=br.readLine()) !=null) {
					if(buffer.equals("STOP")) {}
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




