package cn.zjc.portband;

import java.awt.Color;
import java.awt.Container;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;

import cn.zjc.portscan.PortScannerFrame;


public class PortBandFrame implements ActionListener{
	public static int minPort = 0;
	public static int maxPort = 0;
	public static boolean temp;
	
	// ����������
	private JFrame mainFrame = new JFrame();
	
	private Label labelIP = new Label("������IP");
	private Label labelPortStart = new Label("��ʼ�˿ڣ�");
	private Label labelPortEnd = new Label("����˿ڣ�");
	//private Label labelThread = new Label("�߳���");
	private Label labelResult = new Label("���Ŷ˿ڣ�");
	private Label state = new Label("״̬��");
	public static Label scanning = new Label("δ��ʼ��");
	private JTextField hostName = new JTextField("127.0.0.1");
	private JTextField portStart = new JTextField("0");
	private JTextField portEnd = new JTextField("0");
	//private JTextField threadnum = new JTextField("1");
	// �ı�������ʾɨ����
	public static TextArea result = new TextArea();
	//private TextArea Result2 = new TextArea();
	private Label DLGinfo = new Label("");
	private JButton start = new JButton("��");
	private JButton exit = new JButton("�˳�");
	private JButton clear = new JButton("���");
	//	 ������ʾ�Ի���
	private JDialog DLGError = new JDialog(mainFrame, "����");
	private JButton OK = new JButton("ȷ��");
	
	public PortBandFrame(){
		//���ô���Ƥ��
	     //SubstanceAutumnLookAndFeel.setSkin(new FieldOfWheatSkin());
		// �������������
		mainFrame.setTitle("�˿ڰ���");
		// ����������λ�úʹ�С
		mainFrame.setBounds(180, 200, 550, 300);
		mainFrame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		initComponents();
		initLayOut();
		mainFrame.setVisible(true);
	}

	private void initLayOut() {
		// ����ɨ�谴ť���˳���ť
		clear.setBounds(1, 237, 60, 20);
		clear.setActionCommand("clear");
		clear.addActionListener(this);
		start.setBounds(405, 232, 60, 30);
		start.setActionCommand("Start");
		start.addActionListener(this);
		exit.setBounds(475, 232, 60, 30);
		exit.setActionCommand("Exit");
		exit.addActionListener(this);
		labelIP.setBounds(17, 13, 50, 20);
		hostName.setBounds(67, 10, 92, 25);
		hostName.setHorizontalAlignment(JTextField.CENTER);
		hostName.setEditable(false);
		
		labelPortStart.setBounds(162, 13, 60, 20);
		portStart.setBounds(227, 10, 45, 25);
		portStart.setHorizontalAlignment(JTextField.CENTER);
		
		labelPortEnd.setBounds(292, 13, 60, 20);
		portEnd.setBounds(357, 10, 45, 25);
		portEnd.setHorizontalAlignment(JTextField.CENTER);
		//labelThread.setBounds(422, 13, 50, 20);
		//threadnum.setBounds(477, 10, 45, 25);
		//threadnum.setHorizontalAlignment(JTextField.CENTER);
		
		labelResult.setBounds(1, 45, 55, 20);
		//labelResult2.setBounds(274, 45, 63, 20);
		result.setBounds(1, 65, 544, 160);
		result.setEditable(false);
		result.setBackground(Color.lightGray);//���ô�����ɫ
		//Result2.setBounds(274, 65, 270, 160);
		///Result2.setEditable(false);
		//Result2.setBackground(Color.lightGray);//���ô�����ɫ
		
		state.setBounds(87, 237, 45, 20);
		scanning.setBounds(135, 237, 100, 20);
	}

	private void initComponents() {
		// ���ô�����ʾ��
		Container dPanel = DLGError.getContentPane();
		dPanel.setLayout(null);
		dPanel.add(DLGinfo);
		dPanel.add(OK);
		OK.setActionCommand("ok");
		OK.addActionListener(this);
		
		// ��������������������
		mainFrame.setLayout(null);
		mainFrame.setResizable(true);
		mainFrame.add(clear);
		mainFrame.add(start);
		mainFrame.add(exit);
		mainFrame.add(labelIP);
		mainFrame.add(hostName);
		mainFrame.add(labelPortStart);
		mainFrame.add(labelPortEnd);
		mainFrame.add(portStart);
		mainFrame.add(portEnd);
		//mainFrame.add(labelThread);
		//mainFrame.add(threadnum);
		mainFrame.add(labelResult);
		//mainFrame.add(labelResult2);
		mainFrame.add(result);
		//mainFrame.add(Result2);
		mainFrame.add(state);
		mainFrame.add(scanning);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String cmd = event.getActionCommand();      //�õ������¼�
		if(cmd.equals("Start")){
			// ��ȡ�������
			try {
				minPort = Integer.parseInt(portStart.getText());
				maxPort = Integer.parseInt(portEnd.getText());
				//port=minPort;
				// ������Ϣ������
				if((minPort < 0)||(maxPort > 65535)||(minPort > maxPort)){
					String info = "��С�˿ڱ�����1-65535����!";
					showDialogInfo(info);
					return ;
				}
				result.append("���ڰ� " + hostName.getText()+"\n");
				result.append("��ʼ�˿� " + minPort + " ����˿� " + maxPort + " \n");
				
				//�����߳�
				while(minPort<=maxPort){
					new Thread(new Runnable() {
						int port = minPort;
						@Override
						public void run() {
							try {
								scanning.setText("���ڰ� ..." + port);
								ServerPortBind.bind(port);
								//result.append("�󶨶˿ںţ�" + port + "�ɹ��� \n");
							} catch (IOException e) {
								result.append("          �󶨶˿ںţ�" + port + "ʧ�ܣ� \n");
							}
						}
					}).start();
					minPort++;
				}
				
			} catch (NumberFormatException e) {
				String info = "����Ķ˿ں�!";
				showDialogInfo(info);
				return;
			} /*catch (UnknownHostException e) {
				String info ="����������ַ";
				showDialogInfo(info);
				return;
			} catch (IOException e) {
				String info ="����������ַ";
				showDialogInfo(info);
				return;
			}*/
		}
		else if(cmd.equals("ok")){
			DLGError.dispose();
		}
		else if(cmd.equals("Exit")){
			System.exit(1);
		}
		else if(cmd.equals("clear")){
			result.setText("");
		}
	}

	private void showDialogInfo(String info) {
		DLGError.setBounds(300, 280, 200, 120);
		DLGinfo.setText(info);
		DLGinfo.setBounds(10, 20, 160, 20);
		OK.setBounds(75, 50, 60, 30);
		DLGError.setVisible(true);
	}
	
	public static void main(String[] args) {
		new PortBandFrame();
	}
}
