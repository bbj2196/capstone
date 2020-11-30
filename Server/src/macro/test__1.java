package macro;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class test__1 {

	public static int find(String[] arr,String str) {
		for(int i=0;i<arr.length;i++) {
			if(arr[i].equals(str)) {return i;}
		}
		return -1;
	}
	
	public static int[] randnum(int max) {
		Random r = new Random();
		HashSet<Integer> luckyNumbers = new HashSet<>();
		 int[] numbers = new int[max];
		// 이 HashSet에 수가 6개 채워질 때 까지 반복합니다.
		// 중복 처리가 자동으로 이루어지므로 삽입만 하면 됩니다.
		// 하지만 중복된 수는 삽입이 거부되므로 반복 횟수가 늘어날 수 있습니다.
		while(luckyNumbers.size() < max){
		    // 1. 임의의 수 삽입
		    // Math.random()이 제공하는 0.0~0.999...의 수에 45를 곱해
		    // 0.0~44.xx의 수를 만들고 이를 정수로 바꾼다.
		    // 여기에 1을 더해서 1~45가 되도록 한다.
		    luckyNumbers.add(r.nextInt(max));
		}
		int i=0;
		for(int luckyNum : luckyNumbers) {
			numbers[i]= luckyNum;
			i++;
		}
		 
		return numbers;
	}

public static void main(String[] args) {


	
	
	ServerFrame f = new ServerFrame();
//	Server server = new Server(f);
//	Thread serv= new Thread(server);
	
	f.Create();}}
	//serv.start();
//	testcmd(20);
/*	
Scanner sc = new Scanner(System.in);

System.out.print(">> ");
String str = sc.nextLine();

while(true) {

	if(str.matches("^[a-zA-Z]*$")) {
		System.out.println("소문자 인듯"+str.toUpperCase());
	}
	else {System.out.println("ㄴㄴ아닌듯");}
	
	System.out.print(">> ");
	str = sc.nextLine();
	
	if(str.equals("q")) {break;}
}


			
		
	
	
	
	
	
	
	
	
//	ServerFrame sf = new ServerFrame();
//	sf.FrameCreate();
//	sf.SetNet(true);
}
public static void testcmd(int count) {
	String[][] rules = {{"KEY","PUSH","POP"},	//키보드의 어떤 키를 어떻게 할것인지?
			{"MOUSE","LEFT","RIGHT","MOVE","PUSH","POP","X","Y"},	// 마우스의 어떤버튼을 어디에 어떻게 할것인지?
			{"WAIT","SEC"}};

	Random rand = new Random();

;
	
	for(int x=0;x<count;x++) {
	String[] cmd;
	
	switch(rand.nextInt(3)){
	case 0:
		cmd= new String[4];
		for(int i=0;i<rules[0].length;i++) {
			//System.out.println("[0]"+"["+i+"]");
			cmd[i]=rules[0][rand.nextInt(3)];
			for(int j=0;j<i;j++) {
			if(cmd[i] == cmd[j]) {
				i--;
			}
			}
		}
		cmd[3]="code";
		System.out.println(Arrays.toString(cmd));
		break;
	case 1:
		cmd= new String[9];
		for(int i=0;i<rules[1].length;i++) {
			//System.out.println("[1]"+"["+i+"]");
			cmd[i]=rules[1][rand.nextInt(8)];
			
			for(int j=0;j<i;j++) {
			if(cmd[i] == cmd[j]) {
				i--;
			}
			}
		}
		cmd[8]="code";
		for(int i=0;i<9;i++) {
			if(cmd[i].equals("X")) {cmd[i]=Integer.toString(rand.nextInt(200))+"X";}
		}
		for(int i=0;i<9;i++) {
			if(cmd[i].equals("Y")) {cmd[i]=Integer.toString(rand.nextInt(200))+"Y";}
		}
		System.out.println(Arrays.toString(cmd));
		break;
	case 2:
		cmd= new String[2];
		for(int i=0;i<rules[2].length;i++) {
			//System.out.println("[2]"+"["+i+"]");
			cmd[i]=rules[2][rand.nextInt(2)];
			for(int j=0;j<i;j++) {
			if(cmd[i] == cmd[j]) {
				i--;
			}
			}
		}
		for(int i=0;i<2;i++) {
			if(cmd[i].equals("SEC")) {cmd[i]=Integer.toString(rand.nextInt(300))+"SEC";}
		}
		System.out.println(Arrays.toString(cmd));
		break;
	}
	}
}
}



	
	
	

















/*
import java.util.Arrays;
import java.io.*;

public class test__1 {
public static void main(final String[] args) {
String str[]= {"asd","qq","zxc","qqqqqqq"};



System.out.println(Arrays.toString(str));


try {
File file = new File("C:\\Users\\Administrator\\Desktop\\cmd.txt");

FileWriter fw = new FileWriter(file,true);

FileReader fr = new FileReader(file);
BufferedWriter bw = new BufferedWriter(fw);
BufferedReader br = new BufferedReader(fr);

String[] contents = new String[100];
String line = null;
int count=0;

while((line = br.readLine()) !=null) {
contents[count] = line;
count++;
}

for(int i=0;i<100;i++) {
if(contents[i] == null) {
break;
}
System.out.println(contents[i]+"  "+i);
}


} catch (IOException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
finally {}
}
}

















/*
import java.util.Arrays;
import java.io.*;

public class test__1 {
	 public static void main(final String[] args) {
		 String str[]= {"asd","qq","zxc","qqqqqqq"};
		 
		 
		 
		 System.out.println(Arrays.toString(str));
		 
		 
		 try {
			File file = new File("C:\\Users\\Administrator\\Desktop\\cmd.txt");
			
			FileWriter fw = new FileWriter(file,true);
			
			FileReader fr = new FileReader(file);
			BufferedWriter bw = new BufferedWriter(fw);
			BufferedReader br = new BufferedReader(fr);
			
			String[] contents = new String[100];
			String line = null;
			int count=0;
			
			while((line = br.readLine()) !=null) {
				contents[count] = line;
				count++;
			}
			
			for(int i=0;i<100;i++) {
				if(contents[i] == null) {
					break;
				}
				System.out.println(contents[i]+"  "+i);
			}
			
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 finally {}
	 }
}

*/
