package macro;
import java.io.*;

public class RWFile { //������ �ϰ������� ��ǻ�Ϳ� txt���Ϸ� �����Ѵ�
	
private int rline=0;
private File rctfile= new File("cmd1.txt");
//private String cmd;
//private String[] cmdlist;
	
	public void saveLine(String name,String cmd) {//�̾��, ������ ���ٸ� ����� ������ �߰��� ����
		try {
		File file = new File(System.getProperty("user.dir")+"\\list\\"+name+".txt");
		FileWriter fw = new FileWriter(file,true);
			//������ �̹� �ִ��� ���� ������ ���� ������ �߰�
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

	public void saveAll(String filename,String[] cmd) { //�����, ���ͷ� �迭���� �������� �ִٰ� �����Ͽ�  svaeLine�� �ݺ�
		File file = new File(System.getProperty("user.dir")+"\\list\\"+filename+".txt");
		if (!file.exists()) { //������ �̹� ������ �ִٸ� ����� �ٽø��� 
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
		
		
		//�ִ� 1000�ٱ��� �ް�  �迭ũ������
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
	public String[] readFile(String filename){//���Ͽ��� ����� �о� ��ȯ
		
		File f = new File(System.getProperty("user.dir")+"\\list\\"+filename);
		return readFile(f);
		
	}
	
	public int getReadLines() {
		return rline;
	}
	public File getRecentFile() {
		return rctfile;
	}
	
	public String[] getCmdList() {  //��γ��� txt������ ������
		String filelist=null;
		//���� ��� ���ϱ�
		File path = new File(System.getProperty("user.dir")+"\\list");
		File[] list = path.listFiles(); //���������� ��� ������ ������
		
		for(File f:list) { //�迭�� txt�̸��� ������
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
