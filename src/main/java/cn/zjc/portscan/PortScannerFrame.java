package cn.zjc.portscan;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
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
import javax.swing.JLabel;
import javax.swing.JTextField;

public class PortScannerFrame implements ActionListener {
    public static int minPort = 0;

    public static int maxPort = 0;

    private JFrame mainFrame = new JFrame();

    private JLabel labelIP = new JLabel("IP地址");

    private JLabel labelPortStart = new JLabel("开始端口：");

    private JLabel labelPortEnd = new JLabel("结束端口：");

    private JLabel labelThread = new JLabel("线程数：");

    private JLabel labelResult = new JLabel("扫描结果：");

    private JLabel state = new JLabel("状态");

    private JLabel scanning = new JLabel("扫描");

    private JTextField hostName = new JTextField("127.0.0.1");

    private JTextField portStart = new JTextField("0");

    private JTextField portEnd = new JTextField("8085");

    private JTextField threadnum = new JTextField("1");

    private TextArea result = new TextArea();

    // private TextArea Result2 = new TextArea();
    private Label DLGinfo = new Label("");

    private JButton clear = new JButton("清空");

    private JButton start = new JButton("开始");

    private JButton exit = new JButton("退出");

    private JDialog DLGError = new JDialog(mainFrame, "错误信息");

    private JButton OK = new JButton("确定");

    public PortScannerFrame() {
        // SubstanceAutumnLookAndFeel.setSkin(new FieldOfWheatSkin());
        mainFrame.setTitle("端口扫描");
        mainFrame.setBounds(180, 200, 550, 300);
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        initComponents();
        initLayOut();
        mainFrame.setVisible(true);
    }

    private void initLayOut() {
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
        labelIP.setFont(new Font("MS Song", Font.PLAIN, 12));

        hostName.setBounds(67, 10, 92, 25);
        hostName.setHorizontalAlignment(JTextField.CENTER);

        labelPortStart.setBounds(162, 13, 60, 20);
        labelPortStart.setFont(new Font("MS Song", Font.PLAIN, 12));

        portStart.setBounds(227, 10, 45, 25);
        portStart.setHorizontalAlignment(JTextField.CENTER);

        labelPortEnd.setBounds(292, 13, 60, 20);
        labelPortEnd.setFont(new Font("MS Song", Font.PLAIN, 12));

        portEnd.setBounds(357, 10, 45, 25);
        portEnd.setHorizontalAlignment(JTextField.CENTER);
        labelThread.setBounds(422, 13, 50, 20);
        labelThread.setFont(new Font("MS Song", Font.PLAIN, 12));

        threadnum.setBounds(477, 10, 45, 25);
        threadnum.setHorizontalAlignment(JTextField.CENTER);

        labelResult.setBounds(1, 45, 55, 20);
        // labelResult2.setBounds(274, 45, 63, 20);
        result.setBounds(1, 65, 544, 160);
        result.setEditable(false);
        result.setBackground(Color.lightGray);
        // Result2.setBounds(274, 65, 270, 160);
        // /Result2.setEditable(false);
        // Result2.setBackground(Color.lightGray);

        state.setBounds(87, 237, 45, 20);
        scanning.setBounds(135, 237, 100, 20);
    }

    private void initComponents() {
        Container dPanel = DLGError.getContentPane();
        dPanel.setLayout(null);
        dPanel.add(DLGinfo);
        dPanel.add(OK);
        OK.setActionCommand("ok");
        OK.addActionListener(this);

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
        mainFrame.add(labelThread);
        mainFrame.add(threadnum);
        mainFrame.add(labelResult);
        // mainFrame.add(labelResult2);
        mainFrame.add(result);
        // mainFrame.add(Result2);
        mainFrame.add(state);
        mainFrame.add(scanning);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String cmd = event.getActionCommand(); // �õ������¼�
        if (cmd.equals("Start")) {
            try {
                minPort = Integer.parseInt(portStart.getText());
                maxPort = Integer.parseInt(portEnd.getText());
                // port=minPort;
                // ������Ϣ������
                if ((minPort < 0) || (maxPort > 65535) || (minPort > maxPort)) {
                    String info = "端口号是0-65535!";
                    showDialogInfo(info);
                    return;
                }
                result.append("正在扫描" + hostName.getText() + "\n");
                // scanning.setText("��ʼɨ�� ...");
                result.append("扫描端口为：" + minPort + " --> " + maxPort + " \n");

                int threadCount = Integer.parseInt(threadnum.getText());
                for (int i = 0; i < threadCount; i++) {
                    new Thread(new Runnable() {
                        int port;

                        @Override
                        public void run() {
                            while (compare()) {
                                try {
                                    scanning.setText("正在扫描..." + port);
                                    boolean isConn = ServerConnection.isPortConn(hostName.getText(), port);
                                    if (isConn)
                                        result.append("开启端口：" + port + " \n");
                                } catch (IOException e) {
                                    // result.append("δ���Ŷ˿ںţ�" + port +
                                    // " \n");
                                } finally {
                                    if (port == maxPort) {
                                        try {
                                            Thread.sleep(1000);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        result.append("扫描完成!!!\n");
                                        scanning.setText("扫描完成!");
                                    }
                                }
                            }
                        }

                        private boolean compare() {
                            synchronized (PortScannerFrame.class) {
                                port = minPort++;
                                return port <= maxPort;
                            }
                        }
                    }).start();
                }

            } catch (NumberFormatException e) {
                String info = "线程数错误!";
                showDialogInfo(info);
                return;
            } /*
               * catch (UnknownHostException e) { String info ="����������ַ";
               * showDialogInfo(info); return; } catch (IOException e) { String
               * info ="����������ַ"; showDialogInfo(info); return; }
               */
        } else if (cmd.equals("ok")) {
            DLGError.dispose();
        } else if (cmd.equals("Exit")) {
            System.exit(1);
        } else if (cmd.equals("clear")) {
            result.setText("");
        }
    }

    private void showDialogInfo(String info) {
        DLGError.setBounds(300, 280, 200, 120);
        DLGinfo.setText(info);
        DLGinfo.setFont(new Font("MS Song", Font.PLAIN, 12));
        DLGinfo.setBounds(10, 20, 160, 20);
        OK.setBounds(75, 50, 60, 30);
        DLGError.setVisible(true);
    }

    public static void main(String[] args) {
        new PortScannerFrame();
    }
}
