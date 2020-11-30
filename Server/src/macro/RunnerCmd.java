package macro;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class RunnerCmd{
	//private boolean debug = true;
	private String cmd;
	private String[] sort_cmd;	//���ĵ� ���
	private String[] cmdlist;	//�����ٸ��
	private boolean flag_cmd;	//���� ����� �������� ���������� Ȯ�ο� 0=������ 1=����

	public final static int ALL = 0;
	public final static int ONLY_CMD = 1;
	public final static int ONLY_LIST = 2;
	/*@Override
	public void run() {
		if(flag_cmd?cmd != null:cmdlist!=null) {//����� �ִٸ�
			check();
		}
	}*/

	
	public RunnerCmd(String str) {
		
		cmdInputline(str);
		flag_cmd=true;
	}
	public RunnerCmd(String[] str) {
		cmdlist = str.clone();
		flag_cmd=false;
	}
	public RunnerCmd(String str ,String ch) {
		cmdlist = str.split(ch);
		flag_cmd=false;
	}
	public RunnerCmd() {
		cmd = null;
		cmdlist = null;
		flag_cmd = true;
	}
	
	
	public void cmdInputline(String str) {
		cmd = str ;
		flag_cmd=true;
	}
	public void cmdInput(String[] str) {
		cmdlist=str.clone();
		flag_cmd=false;
	}
	
	public void cmdInput(String str ,String ch) {
		cmdlist = str.split(ch);
		flag_cmd=false;
	}
	//��������
	//1.trans �� �ѿ� ��ȯ
	//2.sort�� ����
	//3. action���� ����
	
 	public String check(int i) { //�Է°��� ������ ������������ �� ���� ������
		String meg="";
		if(flag_cmd) {
		sort_cmd = sort(trans(cmd));
		if (i == ONLY_CMD) {// ���� �Է��Ѹ��
			meg = "InputCmd : "+cmd;
		} 
		else if(i == ONLY_LIST) { //�Լ��� �������ĵ� ���
			meg = "SortList = "+Arrays.toString(sort_cmd);
		}
		else if(i==ALL) { //���� ������
			meg = cmd;
			meg +="\n"+Arrays.toString(sort_cmd);
		}
		else {
			meg = "Error";
		}
		}
		else {
			for(int j=0;j<cmdlist.length;j++) {
			sort_cmd = sort(trans(cmdlist[j]));
			if (i == ONLY_CMD) {// ���� �Է��Ѹ��
				meg += "InputCmd"+(j+1)+" : "+cmdlist[j];
			} 
			else if(i == ONLY_LIST) { //�Լ��� �������ĵ� ���
				meg += "SortList"+(j+1)+" = "+Arrays.toString(sort_cmd);
			}
			else if(i==ALL) { //���� ������
				meg += "InputCmd"+(j+1)+" = "+cmdlist[j];
				meg +="\n"+"SortList = "+Arrays.toString(sort_cmd);
			}
			else {
				meg += "Error";
				break;
			}
			meg+="\n";
			}//for�� ��
		}
		return meg;
	}
 	public String print(int i) { //���� �Էµ� ����� ������
 		String meg;
 		if (i == ONLY_CMD) {// ���� �Է��Ѹ��
			meg = "InputCmd : "+cmd;
		} 
		else if(i == ONLY_LIST) { //�Լ��� �������ĵ� ���
			meg = "CmdList = "+Arrays.toString(sort_cmd);
		}
		else if(i==ALL) { //���� ������
			meg = cmd;
			meg +="\n"+Arrays.toString(sort_cmd);
		}
		else {
			meg = "Error";
		}
		return meg;
 	}
	
	
	public void check()  { //�����ϴ� �Լ� �κ���ü�� ���ึ�� ������
		try {
			Robot robo = new Robot();

			check(robo);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void check(Robot r) { //�����ص� �κ���ü�� ������
		
		try {
		if(flag_cmd) {
			if(cmd == "") {
				System.out.println("����� �����ϴ�.");
			}
			else {
				sort_cmd = sort(trans(cmd));			
				action(sort_cmd, r);
			}
		}
		//�������ϰ��
		else {
			for(int i=0;i<cmdlist.length;i++) {
				sort_cmd=sort(trans(cmdlist[i]));
				action(sort_cmd,r);
			}
		}
		}
		catch(Exception e) {e.printStackTrace();}
	}
	
	
	public String encode(String str) { //�ѱ��� ����� ��ȯ
		String strlast = str.substring(str.length()-1, str.length()); //���������ڱ��ϱ�
		
		if (strlast=="��") {//���������ڰ� s,�� �ϰ��
			strlast = "SEC";
			str = str.substring(0, str.length()-1)+strlast;
		}
		else { //���������ڰ� �ʰ� �ƴҽ� 
		switch(str) {
		case "���콺":
			str = "MOUSE";
			break;
		case "Ű����":
			str = "KEY";
			break;
		case "����":
		case "��":
			str ="LEFT";
			break;
		case "������":
		case "��":
			str ="RIGHT";
			break;
		case "����":
			str ="PUSH";
			break;
		case "����":
			str ="POP";
			break;
		case "Ŭ��":
			str ="CLICK";
			break;
		case "�̵�":
			str="MOVE";
		case "���":
			str="WAIT";
			break;
			//////////////////// Ư��Ű  //////////////////////
		case "��":
		case "��":
			str="UP";
			break;
		case "�Ʒ�":
		case "��":
			str="DOWN";
			break;
		case "�����̽���":
			str="SPACE";
			break;
		case "��Ʈ��":
			str="CONTROL";
			break;
		case "����Ʈ":
			str="SHIFT";
			break;
		case "��Ʈ":
			str="MENU";
			break;
		case "��":
			str="TAB";
			break;
		case "����":
			str="RETURN";
			break;
		case "�齺���̽�":
			str="BACK";
			break;
		case "�μ�Ʈ":
			str="INSERT";
			break;
		case "Ȩ":
			str="HOME";
			break;
		case "��������":
			str="PRIOR";
			break;
		case "�������ٿ�":
			str="NEXT";
			break;
		case "����Ʈ":
			str="DELETE";
			break;
		case "����":
			str="END";
			break;
		default:// x��ǥ y��ǥ �� �Ǵ� ����� ���
			break;
			}
		}	
		return str;
	}


	public String[] trans(String str) { //���ٷ� ���� ����������̽��� ������ ������encode �Լ��� ���� ������ ����� �迭�� ��ȯ
		//�����̽� ������ ���� (�����̽����� �ν�
		String[] arrystr = str.split(" ");
		
		for(int i=0; i<arrystr.length;i++) { //������ �빮�ڷ� �ٲ�
			if(arrystr[i].matches("^[a-zA-Z]*$")) {
				arrystr[i] = arrystr[i].toUpperCase();
			}
			
			arrystr[i]=encode(arrystr[i]);
		}
		
		return arrystr;
	}


	public String[] sort(String[] str) {	//�켱������� ��ɳ����ϴ� �Լ�
		int length = str.length;
		String num="";
		String[] clone = new String[length];
		for (int i=0;i<length;i++) {
			clone[i]=str[i];
		}
	//���� ���� 1. Ű���帶�콺 ���� 2. ��� ���� 3. ��ǥ ���� 4..Ű���� �Ű
		String[][] rules = {{"KEY","PUSH","POP"},	//Ű������ � Ű�� ��� �Ұ�����?
							{"MOUSE","LEFT","RIGHT","MOVE","PUSH","POP","X","Y"},	// ���콺�� ���ư�� ��� ��� �Ұ�����?
							{"WAIT","MS"}};	
		
		int r_row =1;	//���ı��� ��
		int r_col=rules[r_row].length;	//���ı��� ��
		//key��� �ܾ ������ �ప�� �ٲ�
		for(int i=0;i<length;i++) {
			
			if(str[i].indexOf("KEY")!=-1) { //str�� "key"�� �ִٸ� (-1�� �ƴ϶��)
				r_row=0;
				r_col=rules[r_row].length;
				break;
			}
			else if (str[i].indexOf("WAIT")!=-1) { //str�� "wait"�� �ִٸ�
				r_row=2;
				r_col=rules[r_row].length;
				break;
			}
			
		}
		//�迭 ������ �켱���� ������ num�� ����
		for(int i=0;i<r_col;i++) {
			for(int j=0;j<length;j++) {
				if(clone[j].indexOf(rules[r_row][i])!=-1) {
					clone[j] = "";	//�켱������ �ο��� �ܾ�� ""������ ��ȯ�Ͽ� �������� ����
					num= num+j;		//43152 �̷������� ����ȴ� �������̱� �����̴�  �켱������� ���ȣ ���� �� 4�������� 1������ ������
				}	
			}
		}
		//���ǵ��� ���� ���� ��(Ű���� Ű ������)�� ������ �켱���� ����
		for(int i=0;i<length;i++) {
			if(clone[i]!="") {		//������ �ƴѾֵ�=�ʿ���� �ֵ�, �켱������ �ο����������ֵ��� ���ʴ�� �ο�
				num += i;
			}
		}
		//num�� ����� ������ ���������� �켱������� ����
		for(int i=0;i<length;i++) {
			clone[i]=str[Integer.parseInt(num.substring(i,i+1))];	//�ѱ��ھ� �߶� ����
		}
		
		
		return clone;
	}

	public void action(String[] str_cmd,Robot r){ //����� ������ �ؼ��� �����ϴ� �Լ�
		ActionCmd act = new ActionCmd(str_cmd,r);
		act.run();
		//th.
		//t1.start();
	}
	
	public int getVKcode(String str) {	//string�� vk���� �ٲ��ִ� �Լ�
		RWFile rwf = new RWFile();
		File file = new File(System.getProperty("user.dir")+"\\code\\vkcodelist.txt");
		String[] code=rwf.readFile(file);
		int vkcode=65535;
		vkcode = Integer.parseInt(code[findIndex(code,"VK_"+str)+1]);
		return vkcode;
	}
	public int  findIndex(String[] arr,String str) {//�迭�ȿ� ���� �ִٸ� �ε��� ����
		for(int i=0;i<arr.length;i++) {
			if(arr[i].equals(str)) {return i;}
			
		}
		return -1;
	}
	}
	
	
	
	
	
	



	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

