package cn.zjc.copyfile;

import java.awt.GridLayout;
import java.awt.List;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class FtpDemo extends JFrame {
	JButton b1 = new JButton("增加文件");
	JButton b2 = new JButton("删除文件");
	JButton b3 = new JButton("上传文件");
	JTextArea jt = new JTextArea(20, 50);
	List l = new List();
	JPanel jp = new JPanel();
	JFileChooser fd = new JFileChooser();
	Map mapFile = new HashMap();

	public FtpDemo(String title) {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 关闭按钮退出程序
		setLayout(new GridLayout()); // 设置布局管理器
		double lx = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double ly = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		setLocation((int) lx / 2 - 150, (int) ly / 2 - 150);
		fd.setDialogTitle("添加文件");
		fd.setCurrentDirectory(new File("C:\\"));
		// 添加文件按钮的动作监听

		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource().equals(b1)) {
					fd.setFileSelectionMode(JFileChooser.FILES_ONLY); // 只能选择文件
					fd.setMultiSelectionEnabled(true); // 允许选择多个文件
					int state = fd.showOpenDialog(null);
					if (state == 1) {
						return; // 撤销则返回
					} else {
						File[] f = fd.getSelectedFiles();// f为选择到的文件(因为允许选到多个文件，所以。。)
						if (f.length > 0) {
							for (int u = 0; u < f.length; u++) {
								File ff = f[u];
								if (l.getItemCount() > 0) {
									int j = 0;
									for (int i = 0; i < l.getItemCount(); i++) {
										if (ff.getName().equals(l.getItem(i))) {
											j = 1; // 判断是否有重名的文件
										}
									}
									if (j == 0) {
										l.add(ff.getName());
										mapFile.put(ff.getName(), ff.getPath());
									} else {
										j = 0;
									}
								} else {
									l.add(ff.getName());
									mapFile.put(ff.getName(), ff.getPath());
								}
							}
						}
					}
				}
			}
		});

		// 删除文件按钮的动作监听

		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource().equals(b2)) {
					if (l.getSelectedIndex() != -1) {
						String cc = l.getItem(l.getSelectedIndex());
						System.out.println(cc);
						mapFile.remove(cc);
						l.remove(l.getSelectedIndex());
					}
				}
			}
		});

		// 上传文件按钮的动作监听
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource().equals(b3)) {
					if (mapFile.size() > 0) {
						for (Iterator it = mapFile.entrySet().iterator(); it
								.hasNext();) {
							Map.Entry entry = (Map.Entry)it.next();
							String south = (String) entry.getValue();
							String target = "F:\\copy\\"
									+ (String) entry.getKey();
							boolean bb = copy(south, target);
							if (bb == true) {
								l.remove((String) entry.getKey());
							}
						}
					}
				}
			}
		});
		// add(jt);
		add(l);
		jp.setLayout(new GridLayout(3, 1, 40, 100)); // 后面2个参数是：hgap -
		// 水平间距,vgap - 垂直间距
		jp.setBorder(new EmptyBorder(30, 20, 30, 20)); // top - 边框顶部 inset,left
		// - 边框左部 inset,bottom -
		// 边框底部 inset,right -
		// 边框右部 inset
		// jp.setLayout(null);
		jp.add(b1);
		jp.add(b2);
		jp.add(b3);
		add(jp);
		setSize(600, 400);
		setVisible(true);
	}

	// 模拟文件上传功能
	// 将文件由src复制到dst
	private boolean copy(String sourth, String target) {
		try {
			File src = new File(sourth);
			File dst = new File(target);
			InputStream in = null;
			OutputStream out = null;
			try {
				in = new BufferedInputStream(new FileInputStream(src),
				16 * 1024);
				out = new BufferedOutputStream(new FileOutputStream(dst),
				16 * 1024);
				byte[] buffer = new byte[16 * 1024];
				while (in.read(buffer) > 0) {
					out.write(buffer);
				}
				in.close();
				out.close();
			} catch (IOException ioe) {
				ioe.getStackTrace();
				System.out.println("文件路径有错！！");
				return false;
			} finally {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public static void main(String[] args) {
		FtpDemo ftpd = new FtpDemo("FTP上传文件");
	}

}
