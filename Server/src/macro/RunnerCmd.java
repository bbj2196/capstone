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
	private String[] sort_cmd;	//정렬된 명령
	private String[] cmdlist;	//여러줄명령
	private boolean flag_cmd;	//현재 명령이 한줄인지 여러줄인지 확인용 0=여러줄 1=한줄

	public final static int ALL = 0;
	public final static int ONLY_CMD = 1;
	public final static int ONLY_LIST = 2;
	/*@Override
	public void run() {
		if(flag_cmd?cmd != null:cmdlist!=null) {//명령이 있다면
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
	//구동순서
	//1.trans 로 한영 변환
	//2.sort로 정렬
	//3. action으로 실행
	
 	public String check(int i) { //입력값이 있으면 구동전까지만 해 값을 보여줌
		String meg="";
		if(flag_cmd) {
		sort_cmd = sort(trans(cmd));
		if (i == ONLY_CMD) {// 내가 입력한명령
			meg = "InputCmd : "+cmd;
		} 
		else if(i == ONLY_LIST) { //함수를 거쳐정렬된 명령
			meg = "SortList = "+Arrays.toString(sort_cmd);
		}
		else if(i==ALL) { //전부 보여줌
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
			if (i == ONLY_CMD) {// 내가 입력한명령
				meg += "InputCmd"+(j+1)+" : "+cmdlist[j];
			} 
			else if(i == ONLY_LIST) { //함수를 거쳐정렬된 명령
				meg += "SortList"+(j+1)+" = "+Arrays.toString(sort_cmd);
			}
			else if(i==ALL) { //전부 보여줌
				meg += "InputCmd"+(j+1)+" = "+cmdlist[j];
				meg +="\n"+"SortList = "+Arrays.toString(sort_cmd);
			}
			else {
				meg += "Error";
				break;
			}
			meg+="\n";
			}//for문 끝
		}
		return meg;
	}
 	public String print(int i) { //현재 입력된 명령을 보여줌
 		String meg;
 		if (i == ONLY_CMD) {// 내가 입력한명령
			meg = "InputCmd : "+cmd;
		} 
		else if(i == ONLY_LIST) { //함수를 거쳐정렬된 명령
			meg = "CmdList = "+Arrays.toString(sort_cmd);
		}
		else if(i==ALL) { //전부 보여줌
			meg = cmd;
			meg +="\n"+Arrays.toString(sort_cmd);
		}
		else {
			meg = "Error";
		}
		return meg;
 	}
	
	
	public void check()  { //구동하는 함수 로봇객체를 실행마다 생성함
		try {
			Robot robo = new Robot();

			check(robo);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void check(Robot r) { //지정해둔 로봇객체로 돌릴때
		
		try {
		if(flag_cmd) {
			if(cmd == "") {
				System.out.println("명령이 없습니다.");
			}
			else {
				sort_cmd = sort(trans(cmd));			
				action(sort_cmd, r);
			}
		}
		//여러줄일경우
		else {
			for(int i=0;i<cmdlist.length;i++) {
				sort_cmd=sort(trans(cmdlist[i]));
				action(sort_cmd,r);
			}
		}
		}
		catch(Exception e) {e.printStackTrace();}
	}
	
	
	public String encode(String str) { //한글을 영어로 변환
		String strlast = str.substring(str.length()-1, str.length()); //마지막문자구하기
		
		if (strlast=="초") {//마지막문자가 s,초 일경우
			strlast = "SEC";
			str = str.substring(0, str.length()-1)+strlast;
		}
		else { //마지막글자가 초가 아닐시 
		switch(str) {
		case "마우스":
			str = "MOUSE";
			break;
		case "키보드":
			str = "KEY";
			break;
		case "왼쪽":
		case "좌":
			str ="LEFT";
			break;
		case "오른쪽":
		case "우":
			str ="RIGHT";
			break;
		case "누름":
			str ="PUSH";
			break;
		case "떼기":
			str ="POP";
			break;
		case "클릭":
			str ="CLICK";
			break;
		case "이동":
			str="MOVE";
		case "대기":
			str="WAIT";
			break;
			//////////////////// 특수키  //////////////////////
		case "위":
		case "상":
			str="UP";
			break;
		case "아래":
		case "하":
			str="DOWN";
			break;
		case "스페이스바":
			str="SPACE";
			break;
		case "컨트롤":
			str="CONTROL";
			break;
		case "쉬프트":
			str="SHIFT";
			break;
		case "알트":
			str="MENU";
			break;
		case "탭":
			str="TAB";
			break;
		case "엔터":
			str="RETURN";
			break;
		case "백스페이스":
			str="BACK";
			break;
		case "인서트":
			str="INSERT";
			break;
		case "홈":
			str="HOME";
			break;
		case "페이지업":
			str="PRIOR";
			break;
		case "페이지다운":
			str="NEXT";
			break;
		case "딜리트":
			str="DELETE";
			break;
		case "엔드":
			str="END";
			break;
		default:// x좌표 y좌표 시 또는 영어시 통과
			break;
			}
		}	
		return str;
	}


	public String[] trans(String str) { //한줄로 받은 명령을스페이스바 단위로 나누고encode 함수에 전달 나눠진 명령을 배열로 반환
		//스페이스 단위로 끊음 (스페이스단위 인식
		String[] arrystr = str.split(" ");
		
		for(int i=0; i<arrystr.length;i++) { //영어라면 대문자로 바꿈
			if(arrystr[i].matches("^[a-zA-Z]*$")) {
				arrystr[i] = arrystr[i].toUpperCase();
			}
			
			arrystr[i]=encode(arrystr[i]);
		}
		
		return arrystr;
	}


	public String[] sort(String[] str) {	//우선순위대로 명령나열하는 함수
		int length = str.length;
		String num="";
		String[] clone = new String[length];
		for (int i=0;i<length;i++) {
			clone[i]=str[i];
		}
	//순위 정렬 1. 키보드마우스 구분 2. 명령 구분 3. 좌표 구분 4..키보드 어떤키
		String[][] rules = {{"KEY","PUSH","POP"},	//키보드의 어떤 키를 어떻게 할것인지?
							{"MOUSE","LEFT","RIGHT","MOVE","PUSH","POP","X","Y"},	// 마우스의 어떤버튼을 어디에 어떻게 할것인지?
							{"WAIT","MS"}};	
		
		int r_row =1;	//정렬기준 행
		int r_col=rules[r_row].length;	//정렬기준 열
		//key라는 단어가 있으면 행값을 바꿈
		for(int i=0;i<length;i++) {
			
			if(str[i].indexOf("KEY")!=-1) { //str에 "key"가 있다면 (-1이 아니라면)
				r_row=0;
				r_col=rules[r_row].length;
				break;
			}
			else if (str[i].indexOf("WAIT")!=-1) { //str에 "wait"이 있다면
				r_row=2;
				r_col=rules[r_row].length;
				break;
			}
			
		}
		//배열 순서로 우선순위 추출해 num에 저장
		for(int i=0;i<r_col;i++) {
			for(int j=0;j<length;j++) {
				if(clone[j].indexOf(rules[r_row][i])!=-1) {
					clone[j] = "";	//우선순위가 부여된 단어는 ""값으로 변환하여 공백으로 만듬
					num= num+j;		//43152 이런식으로 저장된다 문자형이기 때문이다  우선순위대로 방번호 저장 예 4번쨰방이 1순위를 가진다
				}	
			}
		}
		//정의되지 않은 순서 값(키보드 키 지정등)을 마지막 우선순위 배정
		for(int i=0;i<length;i++) {
			if(clone[i]!="") {		//공백이 아닌애들=필요없는 애들, 우선순위가 부여되지않은애들은 차례대로 부여
				num += i;
			}
		}
		//num에 저장된 숫자의 순서에따라 우선순위대로 정렬
		for(int i=0;i<length;i++) {
			clone[i]=str[Integer.parseInt(num.substring(i,i+1))];	//한글자씩 잘라서 저장
		}
		
		
		return clone;
	}

	public void action(String[] str_cmd,Robot r){ //명령은 받으면 해석해 동작하는 함수
		ActionCmd act = new ActionCmd(str_cmd,r);
		act.run();
		//th.
		//t1.start();
	}
	
	public int getVKcode(String str) {	//string을 vk값로 바꿔주는 함수
		RWFile rwf = new RWFile();
		File file = new File(System.getProperty("user.dir")+"\\code\\vkcodelist.txt");
		String[] code=rwf.readFile(file);
		int vkcode=65535;
		vkcode = Integer.parseInt(code[findIndex(code,"VK_"+str)+1]);
		return vkcode;
	}
	public int  findIndex(String[] arr,String str) {//배열안에 값이 있다면 인덱스 리턴
		for(int i=0;i<arr.length;i++) {
			if(arr[i].equals(str)) {return i;}
			
		}
		return -1;
	}
	}
	
	
	
	
	
	



	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

