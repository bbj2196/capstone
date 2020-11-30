package macro;
import java.io.*;

public class RWFile { //파일을 일관적으로 컴퓨터에 txt파일로 보관한다
	
private int rline=0;
private File rctfile= new File("cmd1.txt");
//private String cmd;
//private String[] cmdlist;
	
	public void saveLine(String name,String cmd) {//이어쓰기, 파일이 없다면 만들고 한줄을 추가로 저장
		try {
		File file = new File(System.getProperty("user.dir")+"\\list\\"+name+".txt");
		FileWriter fw = new FileWriter(file,true);
			//파일이 이미 있는지 조사 없으면 생성 있으면 추가
		if (!file.exists()) {
			file.createNewFile();
		}
		else {
			fw.write('\n');
		}
		fw.write(cmd);
		fw.flush();
		fw.close();
		rctfile = file;
		}
		catch (IOException e) {
	        e.printStackTrace();
	    }
		
		
	}

	public void saveAll(String filename,String[] cmd) { //덮어쓰기, 엔터로 배열마다 나누어져 있다고 가정하에  svaeLine을 반복
		File file = new File(System.getProperty("user.dir")+"\\list\\"+filename+".txt");
		if (!file.exists()) { //덮어쓰기라서 이미 파일이 있다면 지우고 다시만듬 
		    file.delete();
		}
		for(int j=0;j<cmd.length;j++) {
			saveLine(filename,cmd[j]);
		}
		
	}
	
	public String[] readFile() {
		return readFile(rctfile);
	}
	public String[] readFile(File file) {
		String line;
		String[] str= new String[1000];
		int cnt=0;
		File f = file;
		try {
		FileReader file_reader = new FileReader(f);
		BufferedReader br = new BufferedReader(file_reader);
		
		while ((line = br.readLine()) != null) {
			str[cnt]=line;
			cnt++;
	    }
		br.close();		
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		rline=cnt;
		this.rctfile = f;
		
		
		//최대 1000줄까지 받고  배열크기조절
		if(cnt<1000) {
			String[] clone = new String[cnt];
			for(int j=0;j<cnt;j++) {
				clone[j]=str[j];
			}
		return clone;
		}
		else {
			return str;
		}
	}
	public String[] readFile(String filename){//파일에서 명령을 읽어 반환
		
		File f = new File(System.getProperty("user.dir")+"\\list\\"+filename);
		return readFile(f);
		
	}
	
	public int getReadLines() {
		return rline;
	}
	public File getRecentFile() {
		return rctfile;
	}
	
	public String[] getCmdList() {  //경로내의 txt파일을 추출함
		String filelist=null;
		//현재 경로 구하기
		File path = new File(System.getProperty("user.dir")+"\\list");
		File[] list = path.listFiles(); //현재폴더내 모든 파일을 가져옴
		
		for(File f:list) { //배열내 txt이름만 가져옴
			String str=f.getName();
			
			if(str.endsWith(".txt")) {
				if(filelist ==null) {
					filelist = str;
					}
				else {
				filelist =filelist +"\n"+str;
				}
			}
		}
		
if (filelist ==null) {
	return null;}
else {
		return filelist.split("\n");}
	}
	
}
