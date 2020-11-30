package macro;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class ActionCmd implements Runnable{
String[] str_cmd;
Robot r;
@Override
public void run(){
	//ù �ܾ Ű������ ���
	
			if(str_cmd[0]=="KEY") {
				switch(str_cmd[1]) {
				case "PUSH":
					r.keyPress(getVKcode(str_cmd[str_cmd.length-1]));		//----------------------------------------------���ڸ� ��� ���� �����ϱ� 
					break;
				case "POP":
					r.keyRelease(getVKcode(str_cmd[str_cmd.length-1]));
					break;
					default:
						System.out.println("�ι�° ���� ����_key ");
						System.out.println(Arrays.toString(str_cmd));
						
				}
			}
			//ù�ܾ ���콺�� ���
			else if (str_cmd[0]=="MOUSE") {
				switch(str_cmd[1]) {
				case "LEFT":
					if(str_cmd[2]=="PUSH") {
						r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
					}
					else if(str_cmd[2]=="POP") {
						r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
					}
					break;
				case"RIGHT":
					if(str_cmd[2]=="PUSH") {
						r.mousePress(InputEvent.BUTTON2_DOWN_MASK);
					}
					else if(str_cmd[2]=="POP") {
						r.mouseRelease(InputEvent.BUTTON2_DOWN_MASK);
					}
					break;
				case "MOVE":
					//�� �迭�� ������ ���ڸ� ������ ���ڸ� INT�� ��ȯ ��ǥ�� 100X 200Y�� ���ֱ� ����
					r.mouseMove(Integer.parseInt(str_cmd[2].substring(0,str_cmd[2].length()-2)), Integer.parseInt(str_cmd[3].substring(0,str_cmd[3].length()-2)));
					break;
					default:
						System.out.println("�ι�° ���� ����_mouse");
						System.out.println(Arrays.toString(str_cmd));
				}
			}
			//����� ���
			else if(str_cmd[0]=="WAIT"){
				try {
					Thread.sleep(Integer.parseInt(str_cmd[1].substring(0,str_cmd[1].length()-3)));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			else { //������ ���
				System.out.println("ù��° ���� ����");
				System.out.println(Arrays.toString(str_cmd));
			}
}//run
		
public ActionCmd(String[] str,Robot r) {
	this.str_cmd = str;
	this.r=r;
}
public static int getVKcode(String code) {	//string�� vk���� �ٲ��ִ� �Լ�
	File file = new File(System.getProperty("user.dir")+"\\code\\vkcodelist.txt");
	int vkcode=0;
	try {
		FileReader file_reader = new FileReader(file);
		BufferedReader br = new BufferedReader(file_reader);

		String line;
		code="VK_"+code;
		while((line = br.readLine()) !=null) {
			if(code.equals(line)) {
				line=br.readLine();
				vkcode =Integer.parseInt(line);
				break;
			}
		}
		br.close();
		}
		catch(Exception e) {e.printStackTrace();}
		finally {}
	return vkcode;
}
}
