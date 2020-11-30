package macro;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class ServerFrame{
	private static String ip_num;
	private static String pt_num;
	private boolean flag_s=false;
	private boolean flag_m = false;
	
	public final static int SERVER = 1;
	public final static int CLIENT = 2;
	public final static int MOBILE = 3;
	public final static int ERROR = 4;
	String text="";
	TextArea ta = new TextArea("",18,54,TextArea.SCROLLBARS_VERTICAL_ONLY);
	TextArea ta_s = new TextArea("",10,20,TextArea.SCROLLBARS_VERTICAL_ONLY);
	public ServerFrame() {
		this("0,0,0,0","0000");
	}

	public ServerFrame(String ip, int port) {
		this(ip,Integer.toString(port));
	}
	public ServerFrame(String ip,String port) {
			this(ip,port,false);
	}
	public ServerFrame(String ip,String port,boolean net) {
		ip_num=ip;
		pt_num=port;
		flag_s = net;
	}
	public void refresh() {
		Frame[] f_old =Frame.getFrames();
		for(int i=0;i<f_old.length;i++) {
		f_old[i].dispose();
		}
		Create();
	}
	public void setText(String str) {
		this.text = str;
	}
	public void printLog(String str,int hostcase) {
		String host="";
		switch(hostcase) {
		case SERVER:
			host="[Server]";
			break;
		case CLIENT:
			host="[Client]";
			break;
		case MOBILE:
			host="[Mobile]";
			break;
		case ERROR:
			host="[Error]";
			break;
		}
		SimpleDateFormat format = new SimpleDateFormat("[hh:mm]");
		String time = format.format(new Date());
		String memo;
		memo = ta_s.getText();
		
		if(memo.equals(""))
		{ta_s.setText(host+time+str);}
		else
			ta_s.setText(memo+"\n"+host+time+str);
	}

	public void Create() {
		
		RunnerCmd runner = new RunnerCmd();
		RWFile rwf = new RWFile();
		
		JFrame f = new JFrame();


		//패널 선언
		JPanel p_Ip =new JPanel();
		JPanel p_Pt = new JPanel();
		JPanel p = new JPanel();
		
		JPanel p_left = new JPanel();
		JPanel p_text = new JPanel();
		JPanel p_bot = new JPanel();
		
		JPanel p_radio = new JPanel();
		JPanel p_xy = new JPanel();
		//컴포넌트 객체들 선언
		JLabel la_Ip,la_Pt,la_IpNum,la_PtNum,la_stus,la_moniter;
		JLabel la_mouseX,la_mouseY;
		JButton btn_exit = new JButton("종료");
		JButton btn_list = new JButton("불러오기");
		JButton btn_re = new JButton("새로고침");
		JButton btn_run = new JButton("실행");
		JButton btn_save = new JButton("저장");
		JButton btn_find = new JButton("찾기");
		JButton btn_xy = new JButton("좌표");
		
		Color w = new Color(255,255,255);
		Color r = new Color(255,0,0);
		Color g = new Color(0,255,0);
		
		
		
		JRadioButton rb_one = new JRadioButton("한번");
		JRadioButton rb_roop = new JRadioButton("계속");
		JRadioButton rb_num = new JRadioButton("횟수");
		
		TextField tf_radio = new TextField();
		
		Font fnt_btn = new Font("고딕",Font.ITALIC,11);
		//패널  설정
		p.setPreferredSize(new Dimension(600,400));
		p_left.setBounds(0, 0, 200, 400);
		p_text.setBounds(200, 0, 400, 300);
		p_bot.setBounds(200, 300, 400, 100);
		
		p_bot.setLayout(new GridLayout(2,5));
		
		p_left.setLayout(new GridLayout(8,1));
		
		//p.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		p.setLayout(null);

		p_radio.setPreferredSize(new Dimension(200,100));
		p_radio.setLayout(new GridLayout(2,2));
		
		p_xy.setLayout(new GridLayout(1,3));
		//텍스트 창 설정
		
		ta.setPreferredSize(new Dimension(400,300));
		ta.setForeground(new Color(0,0,0));
		ta.setText(text);
		
		//ta_s.setPreferredSize(new Dimension(200,100));
	//	ta_s.setForeground(new Color(0,0,0));
	//	ta_s.setText(text);
	//	ta.set
		//텍스트 필드 설정
		tf_radio.setForeground(new Color(0,0,0));
		tf_radio.setBackground(w);
		tf_radio.setColumns(8);
		tf_radio.setPreferredSize(new Dimension(20,20));
		//라벨 설정
		la_Ip = new JLabel("아이피");
		la_Pt = new JLabel("포트번호");
		
		la_IpNum = new JLabel(ip_num);
		la_PtNum = new JLabel(pt_num);
		
		la_stus = new JLabel("Connecting...");
		la_moniter = new JLabel("");
		la_stus.setForeground(r);
		
		la_mouseX= new JLabel("X : ");
		la_mouseY = new JLabel("Y : ");
		if(flag_s) {
			la_stus.setText("Server Connected");
			la_stus.setForeground(g);
		}
		if(flag_m) {
			la_moniter.setText("Moniter Connected");
			la_stus.setForeground(g);
		}
		la_stus.setHorizontalAlignment(SwingConstants.CENTER);
		
		//버튼설정
		btn_list.setFont(fnt_btn);
		btn_run.setFont(fnt_btn);
		btn_re.setFont(fnt_btn);
		btn_exit.setFont(fnt_btn);
		btn_save.setFont(fnt_btn);
		btn_find.setFont(fnt_btn);
		btn_xy.setFont(fnt_btn);

		la_IpNum.setBackground(w);
		la_PtNum.setBackground(w);
		
		//라디오 버튼 설정
		ButtonGroup radio = new ButtonGroup();
		radio.add(rb_num);
		radio.add(rb_roop);
		radio.add(rb_one);
		rb_one.setSelected(true);
		
		//판넬및 프레임에 더하기
		p_Ip.add(la_Ip);
		p_Ip.add(la_IpNum);
		
		p_Pt.add(la_Pt);
		p_Pt.add(la_PtNum);
		
		
		p_radio.add(rb_one);
		p_radio.add(rb_num);
		p_radio.add(rb_roop);

		p_radio.add(tf_radio);
		
		p_xy.add(la_mouseX);
		p_xy.add(la_mouseY);
		p_xy.add(btn_xy);
		//상단
		p_text.add(ta);
		
		//하단
		p_bot.add(btn_re);
		p_bot.add(btn_list);
		p_bot.add(btn_run);
		p_bot.add(btn_find);
		p_bot.add(btn_save);
		p_bot.add(btn_exit);
		
		//좌측
		//p.setLayout(new BorderLayout());
		p_left.add(p_Ip);
		p_left.add(p_Pt);
		p_left.add(la_stus);
		p_left.add(la_moniter);
		
		p_left.add(p_radio);
		p_left.add(p_xy);
		
		p_left.add(ta_s);
		//,확인용


		
		
		
		p.add(p_text);
		p.add(p_bot);
		p.add(p_left);
		
		f.add(p);
		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		f.setResizable(false); //최대화 크기 비활성
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		la_moniter.setFocusable(true);
		//종료버튼 이벤트
		
		btn_exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
			
		});
		//리스트 버튼 이벤트
		btn_list.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Dialog d = new Dialog(f,"다이얼로그");
				Button btn_open = new Button("열기");
				d.setSize(200, 200);
				List li = new List();
				
				String[] cmdlist=rwf.getCmdList();
				if (cmdlist==null) {
					JOptionPane.showMessageDialog(null,"리스트 없음");
					d.dispose();
				}
				else {
				for(int i=0;i<cmdlist.length;i++) {li.add(cmdlist[i]);}
				
				d.setLayout(new BorderLayout());
				d.add(li);
				d.add(btn_open,"South");
				
				//열기버튼 설정
				btn_open.addActionListener(new ActionListener() {	
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						
						String[] memo = rwf.readFile(li.getSelectedItem());
						for(int i=0;i<memo.length;i++) {
							if(i==0) {ta.setText(memo[i]);}
							else {
							ta.append(memo[i]+"\n");}
						}
						d.dispose();
					}});
				//최대화 비활성, 중앙, 보이게 설정
				d.setResizable(false);
				d.setLocationRelativeTo(null);
				d.setVisible(true);
				}
												
				
				
				d.addWindowListener(new WindowListener() { // x버튼시 창 닫기

					@Override
					public void windowOpened(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void windowClosing(WindowEvent e) {
						// TODO Auto-generated method stub
						d.dispose();
						
					}

					@Override
					public void windowClosed(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void windowIconified(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void windowDeiconified(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void windowActivated(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void windowDeactivated(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}});
			
				;
			}		

		});
		//새로고침 버튼 이벤트
		btn_re.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				la_IpNum.setText(ip_num);
				la_PtNum.setText(pt_num);
				if(flag_m) {
				la_moniter.setText("Moniter Connected");
				la_moniter.setForeground(g);}
				if(flag_s) {		
					la_stus.setText("Server Connected");
					la_stus.setForeground(g);
				}
				else {
					la_stus.setText("Connecting...");
					la_stus.setForeground(r);
				}
		//		Refresh();
			}
		});
		
		
		//실행버튼 이벤트
		btn_run.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JPanel p = new JPanel();
				p.setBackground(new Color(0,0,0,0));
				p.addKeyListener(new KeyListener() {
					
					@Override
					public void keyTyped(KeyEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void keyReleased(KeyEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void keyPressed(KeyEvent e) {
						// TODO Auto-generated method stub
						if(e.getKeyCode() == KeyEvent.VK_F1) {
							Thread.currentThread();
							JOptionPane.showMessageDialog(null,"정지 정지다");
						}
					}
				});
				
				if(rb_one.isSelected()) {
					runner.cmdInput(ta.getText(),"\n");
					ta.setText(runner.check(RunnerCmd.ALL));
					p.requestFocus();
				}
				else if(rb_num.isSelected()) {
					for(int i=0;i<Integer.parseInt(tf_radio.getText());i++) {
						runner.cmdInput(ta.getText(),"\n");
						ta.setText(runner.check(RunnerCmd.ALL));
						p.requestFocus();
					}
				}
				else if(rb_roop.isSelected()) {
					while(true) {runner.cmdInput(ta.getText(),"\n");
					ta.setText(runner.check(RunnerCmd.ALL));
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					p.requestFocus();
					}
				}
				
			}
		});
		//저장버튼 이벤트
		btn_save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JDialog d = new JDialog(f,"파일 이름 입력");
				Button btn_dsave = new Button("저장");
				TextField tf_name = new TextField(20);
				
			/*	if(rwf.getRecentFile().getName() == null) {
					tf_name.setText("");}
				else {
				tf_name.setText(rwf.getRecentFile().getName());}*/
				d.setLayout(new FlowLayout());
				d.add(tf_name);
				d.add(btn_dsave);
				d.setSize(new Dimension(200,100));
				d.setResizable(false);
				d.setLocationRelativeTo(null);
				d.setVisible(true);
				btn_dsave.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						rwf.saveAll(tf_name.getText(), ta.getText().split("\n"));
						d.dispose();
					}
				});
				
			}
		});
		btn_find.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JDialog d = new JDialog(f,"코드검색");
				JPanel p = new JPanel();
				JPanel p2 = new JPanel();
				JButton btn_set = new JButton("입력");
				JTextField tf = new JTextField(10);
				JComboBox cb = new JComboBox();
				
				p.setLayout(new BorderLayout());
				p2.setLayout(new FlowLayout(FlowLayout.CENTER));
				//tf.setSize(200, 50);
				//cb.setSize(200, 1);
				//btn_set.setSize(200, 50);
				cb.setPreferredSize(new Dimension(130,1));
				cb.setEditable(false);
				String[] code = rwf.readFile(new File(System.getProperty("user.dir")+"\\code\\vkcodelist.txt"));
				for(int i=0;i<code.length/2;i++) {
						cb.addItem(String.format("%-8.8s", code[i*2])+" || "+code[(i*2)+1]);
				}
				//텍스트필드 값 변화시
				tf.addKeyListener(new KeyListener() {
					
					@Override
					public void keyTyped(KeyEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void keyReleased(KeyEvent e) {
						// TODO Auto-generated method stub
						if(e.getKeyCode() == KeyEvent.VK_ENTER) {
							String str=cb.getSelectedItem().toString();
							str= str.substring(3,str.indexOf(" "));
							tf.setText(str);
							
							cb.hidePopup();
							tf.setFocusable(true);
							}
						else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
							int index =cb.getSelectedIndex();
							if(cb.getItemCount() > index+1) {
								cb.setSelectedIndex(index+1);
							}
						}
						else if(e.getKeyCode() == KeyEvent.VK_UP) {
							int index =cb.getSelectedIndex();
							if(index != 0) {
								cb.setSelectedIndex(index-1);
							}
						}
						else {
						cb.removeAllItems();
						for(int i=0;i<code.length/2;i++) {
							if(code[i*2].indexOf("VK_"+tf.getText().toUpperCase())!=-1) {
								cb.addItem(String.format("%-8.8s", code[i*2])+" || "+code[(i*2)+1]);
							}
						}
						cb.showPopup();
						}
					}
					
					@Override
					public void keyPressed(KeyEvent e) {
						// TODO Auto-generated method stub
						
						
					}
				});
				
				//입력버튼 클릭시
				btn_set.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						ta.setText(ta.getText()+tf.getText());
						d.dispose();
					}
				});
				
				
				p2.add(tf);
				p2.add(cb);
				p.add(p2,"North");
				p.add(btn_set,"South");
				d.add(p);
				d.setSize(150, 200);
				d.setLocationRelativeTo(null);
				d.setVisible(true);
				d.setResizable(false);
				d.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			}
			
		});
		//XY좌표 구하는 버튼
		btn_xy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JDialog d = new JDialog(f,"좌표구하기");
				JLabel la = new JLabel("아무곳이나 클릭하세요");
				JPanel p = new JPanel();
				la.setHorizontalAlignment(JLabel.CENTER);
				//d.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
				
				d.setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
				d.add(la);
				
				d.setUndecorated(true);
				d.setAlwaysOnTop(true);
				d.setBackground(new Color(0,0,0,1));
				
				d.setResizable(false);
				d.setLocationRelativeTo(null);
				d.setVisible(true);
				la.addMouseListener(new MouseListener() {
					
					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
						PointerInfo pt =MouseInfo.getPointerInfo();
						la_mouseX.setText("X : "+ pt.getLocation().x);
						la_mouseY.setText("Y : "+pt.getLocation().y);
						printLog("좌표구함",ServerFrame.CLIENT);
						d.dispose();
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
				
			}
		});
		
		
		
		tf_radio.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				String num =tf_radio.getText();
				if(!num.equals("")) {
					if(num.matches("^[0-9]*$")) {
						if(Integer.parseInt(num)<=99) {}
						else
						{JOptionPane.showMessageDialog(null,"99이하의 숫자만 입력 가능합니다");
						tf_radio.requestFocus();
						}
					}
				
					else
					{
					JOptionPane.showMessageDialog(null,"숫자만 입력 가능합니다");
					tf_radio.requestFocus();
					}
				}
				btn_list.setEnabled(true);
				btn_run.setEnabled(true);
				btn_re.setEnabled(true);

				btn_save.setEnabled(true);
				btn_find.setEnabled(true);;
				btn_xy.setEnabled(true);
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				//btn_exit.set
				btn_list.setEnabled(false);
				btn_run.setEnabled(false);
				btn_re.setEnabled(false);
				btn_save.setEnabled(false);
				btn_find.setEnabled(false);;
				btn_xy.setEnabled(false);

			}
		});
		
		
		//키인식 버튼 이벤트
		
	}

	
	
	
/*	public static void main(String[] args) {
	Frame f = new Frame();
	
	f.setSize(200,200);
	f.setLocationRelativeTo(null);
	f.setVisible(true);
	
	Panel p_Ip =new Panel();
	Panel p_Pt = new Panel();
	Panel p =new Panel();
	Label la_Ip,la_Pt,la_IpNum,la_PtNum,la_stus;
	
	la_Ip = new Label("아이피");
	la_Pt = new Label("포트번호");
	
	la_IpNum = new Label(ip_num);
	la_PtNum = new Label(pt_num);
	
	la_stus = new Label("connecting...");
	
	Color c = new Color(255,255,255); 
	la_IpNum.setBackground(c);
	la_PtNum.setBackground(c);
	
	p_Ip.add(la_Ip);
	p_Ip.add(la_IpNum);
	
	p_Pt.add(la_Pt);
	p_Pt.add(la_PtNum);
	
	p.add(p_Ip);
	p.add(p_Pt);
	p.add(la_stus);
	f.add(p);

	


	}
	*/
	public void setNum(String ip, String port) {
		ip_num=ip;
		pt_num=port;
	}
	public void setNum(String ip, int port) {
		this.setNum(ip,Integer.toString(port));
	}
	
	public void setConn(boolean server) { //연결 완료 설정
		this.setConn(server,false);
	}
	public void setConn(boolean server,boolean moniter) {
		flag_s=server;
		flag_m=moniter;
	}


}