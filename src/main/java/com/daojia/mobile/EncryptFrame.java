package com.daojia.mobile;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class EncryptFrame implements ActionListener {
	private JFrame mainFrame = new JFrame();
	private JLabel mobileLabel = new JLabel("手机号:");
	private JTextField mobile = new JTextField("");
	private JLabel encryptMobile = new JLabel("加密值:");
	private JTextField encryptValue = new JTextField("");
	private JButton encryptButton = new JButton("加密");

	public EncryptFrame() {
		mainFrame.setTitle("加密手机号");
		mainFrame.setBounds(300, 300, 400, 150);
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		// initComponents();
		mainFrame.setLayout(null);
		//mainFrame.setResizable(true);
		mainFrame.add(mobileLabel);
		mainFrame.add(mobile);
		mainFrame.add(encryptMobile);
		mainFrame.add(encryptValue);
		mainFrame.add(encryptButton);
		// initLayOut();
		mobileLabel.setBounds(10, 20, 60, 20);
		mobileLabel.setFont(new Font("MS Song", Font.PLAIN, 12));
		mobile.setBounds(80, 20, 200, 20);
		
		encryptButton.setBounds(290, 20, 60, 22);
		encryptButton.setActionCommand("encrypt");
		encryptButton.addActionListener(this);
		encryptButton.setFont(new Font("MS Song", Font.PLAIN, 12));

		encryptMobile.setBounds(10, 60, 60, 20);
		encryptMobile.setFont(new Font("MS Song", Font.PLAIN, 12));
		encryptValue.setBounds(80, 60, 270, 22);
		encryptValue.setEditable(false);
		encryptValue.setBackground(Color.lightGray);

		mainFrame.setVisible(true);
		mainFrame.setResizable(false);
	}

	public static void main(String[] args) {
		new EncryptFrame();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();
		if (cmd.equals("encrypt")) {
			try {
				String secretPhn = this.mobile.getText();
				secretPhn = DESUtils.encrypt(secretPhn.getBytes(), "On1CGiKQ;IyJ^$56MG;U");
				secretPhn = URLEncoder.encode(secretPhn.trim(), "utf-8");
				encryptValue.setText(secretPhn);
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		}
	}
}
