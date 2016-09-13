package com.gd.compare;

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


public class FileCompareFrame implements ActionListener{
	
	// 主窗体
	private JFrame mainFrame = new JFrame();
	
	private Label filelabel1 = new Label("文件路径:");
	private Label filelabel2 = new Label("文件路径:");
	
	private JTextField filePath1 = new JTextField();
	private JTextField filePath2 = new JTextField();
	
	private Label labelResult = new Label("对比结果");
	public static TextArea result = new TextArea();
	
	private JButton selectBut1 = new JButton("...");
	private JButton selectBut2 = new JButton("...");
	
	private JButton submit = new JButton("比较文件");
	private JButton saveAs = new JButton("另存为");
	private JButton clear = new JButton("清空");
	
	
	private Label DLGinfo = new Label("");
	private JDialog DLGError = new JDialog(mainFrame, "错误信息");
	private JButton OK = new JButton("确定");
	
	public FileCompareFrame(){
		mainFrame.setTitle("文件对比");
		mainFrame.setBounds(150, 200, 600, 400);
		mainFrame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		initComponents();
		initLayOut();
		mainFrame.setVisible(true);
	}

	private void initComponents() {
		// 添加错误信息窗体
		Container dPanel = DLGError.getContentPane();
		dPanel.setLayout(null);
		dPanel.add(DLGinfo);
		dPanel.add(OK);
		OK.setActionCommand("ok");
		OK.addActionListener(this);
		
		mainFrame.setLayout(null);
		mainFrame.setResizable(true);
		
		// 添加label
		mainFrame.add(filelabel1);
		mainFrame.add(filelabel2);
		mainFrame.add(filePath1);
		mainFrame.add(filePath2);
		mainFrame.add(labelResult);
		mainFrame.add(result);
		mainFrame.add(selectBut1);
		mainFrame.add(selectBut2);
		
		mainFrame.add(submit);
		mainFrame.add(saveAs);
		mainFrame.add(submit);
		mainFrame.add(clear);
	}
	
	/**
	 * 初始化页面布局
	 */
	private void initLayOut() {
		clear.setBounds(1, 237, 60, 20);
		clear.setActionCommand("clear");
		clear.addActionListener(this);
		
/*
 * private Label filelabel1 = new Label("文件路径：");
	private Label filelabel2 = new Label("文件路径：");
	private JTextField filePath1 = new JTextField();
	private JTextField filePath2 = new JTextField();
	private Label labelResult = new Label("对比结果");
	public static TextArea result = new TextArea();
	private JButton selectBut1 = new JButton("选择文件");
	private JButton selectBut2 = new JButton("选择文件");
	private JButton submit = new JButton("比较文件");
	private JButton saveAs = new JButton("另存为");
	private JButton clear = new JButton("清空");
 */
		filelabel1.setBounds(10, 10, 60, 25);
		filelabel2.setBounds(10, 50, 60, 25);
		
		filePath1.setBounds(75, 10, 150, 22);
		filePath1.setHorizontalAlignment(JTextField.LEFT);
		filePath1.setEditable(true);
		
		filePath2.setBounds(75, 50, 150, 22);
		filePath2.setHorizontalAlignment(JTextField.LEFT);
		filePath2.setEditable(true);
		
		selectBut1.setBounds(230, 10, 50, 22);
		selectBut1.setActionCommand("selctO");
		selectBut1.addActionListener(this);
		
		selectBut2.setBounds(230, 50, 50, 22);
		selectBut2.setActionCommand("selctT");
		selectBut2.addActionListener(this);
		
		submit.setBounds(300, 50, 90, 25);
		submit.setActionCommand("compare");
		submit.addActionListener(this);
		
		
		
		
		//labelResult.setBounds(1, 45, 55, 20);
		//labelResult2.setBounds(274, 45, 63, 20);
		//result.setBounds(1, 65, 544, 160);
		//result.setEditable(false);
		//result.setBackground(Color.lightGray);//���ô�����ɫ
		//Result2.setBounds(274, 65, 270, 160);
		///Result2.setEditable(false);
		//Result2.setBackground(Color.lightGray);//���ô�����ɫ
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String cmd = event.getActionCommand();      //获取事件监听名称
		if(cmd.equals("submit")){
			
//			String info = "文件类型错误!";
//			showDialogInfo(info);
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
		new FileCompareFrame();
	}
}
