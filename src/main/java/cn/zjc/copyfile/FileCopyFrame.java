package cn.zjc.copyfile;

import java.awt.Container;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;


public class FileCopyFrame implements ActionListener {
	JFileChooser jfc1 = new JFileChooser();
	JFileChooser jfc2 = new JFileChooser();

	// 主窗体
	private JFrame mainFrame = new JFrame();

	private Label filelabel1 = new Label("srcPath:");
	private Label filelabel2 = new Label("DLPath:");

	private JTextField filePath1 = new JTextField();
	private JTextField filePath2 = new JTextField();

	private JButton selectBut1 = new JButton("...");
	private JButton selectBut2 = new JButton("...");

	private JButton submit = new JButton("DownLoad");

	private Label DLGinfo = new Label("");
	private JDialog DLGError = new JDialog(mainFrame, "ErrorInfo");
	private JButton OK = new JButton("OK");

	public FileCopyFrame() {
		mainFrame.setTitle("CopyFiles");
		mainFrame.setBounds(150, 200, 500, 200);
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		initComponents();
		initLayOut();
		mainFrame.setVisible(true);
		mainFrame.setResizable(false);
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
		// mainFrame.add(labelResult);
		// mainFrame.add(result);
		mainFrame.add(selectBut1);
		mainFrame.add(selectBut2);

		mainFrame.add(submit);
		mainFrame.add(submit);
	}

	/**
	 * 初始化页面布局
	 */
	private void initLayOut() {
		filelabel1.setBounds(60, 20, 60, 30);
		filelabel2.setBounds(60, 60, 60, 30);

		filePath1.setBounds(125, 20, 150, 22);
		filePath1.setHorizontalAlignment(JTextField.LEFT);
		filePath1.setEditable(false);

		filePath2.setBounds(125, 60, 150, 22);
		filePath2.setHorizontalAlignment(JTextField.LEFT);
		filePath2.setEditable(false);

		selectBut1.setBounds(280, 20, 50, 22);
		selectBut1.setActionCommand("selctO");
		selectBut1.addActionListener(this);

		selectBut2.setBounds(280,60, 50, 22);
		selectBut2.setActionCommand("selctT");
		selectBut2.addActionListener(this);

		submit.setBounds(60, 100, 100, 25);
		submit.setActionCommand("compare");
		submit.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String cmd = event.getActionCommand(); // 获取事件监听名称
		if (cmd.equals("selctO")) {
			jfc1.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
			int intRetVal = jfc1.showSaveDialog(null);
			if (intRetVal == JFileChooser.APPROVE_OPTION) {
				filePath1.setText(jfc1.getSelectedFile().getPath());
			}
		}
		if (cmd.equals("selctT")) {
			/*
			 * 这是尤为重要的。因为JFileChooser默认的是选择文件，而需要选目录。 故要将DIRECTORIES_ONLY装入模型
			 * 另外，若选择文件，则无需此句
			 */
			jfc2.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int intRetVal = jfc2.showSaveDialog(null);
			if (intRetVal == JFileChooser.APPROVE_OPTION) {
				filePath2.setText(jfc2.getSelectedFile().getPath());
			}
		}
		if (cmd.equals("compare")) {
			new CopyDirectory().copy(filePath1.getText(), filePath2.getText());
			String info = "File Copy Success!!!";
			showDialogInfo(info);
		} else if (cmd.equals("ok")) {
			DLGError.dispose();
		} else if (cmd.equals("Exit")) {
			System.exit(1);
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
		new FileCopyFrame();
	}
}
