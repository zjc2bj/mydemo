package cn.zjc.sendrequest;

import java.awt.Color;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import cn.zjc.util.XmlFormat;

import com.lt.utils.HttpClientUtil;

public class SendRequestFrame implements ActionListener {
    Logger log = Logger.getLogger(SendRequestFrame.class);

    JFileChooser jfc1 = new JFileChooser();

    JFileChooser jfc2 = new JFileChooser();

    private JFrame mainFrame = new JFrame();

    private JLabel label_ReqUrl = new JLabel("请求地址:");

    private JButton select = new JButton("选择");

    private JButton print = new JButton("输出");

    private JLabel label_ReqStr = new JLabel("请求参数:");

    private JLabel label_RspStr = new JLabel("返回信息:");

    private JTextField reqUrl = new JTextField("");

    private TextArea reqStr = new TextArea();

    private TextArea rspStr = new TextArea();

    private JButton send_post = new JButton("发送");

    // private JButton send_get = new JButton("send-get");

    private JButton exit = new JButton("exit");

    private JButton clear = new JButton("清空");

    private JButton reset = new JButton("重置");

    /**
     * 
     */
    public SendRequestFrame() {
        mainFrame.setTitle("发送HttpClient请求");
        mainFrame.setBounds(200, 200, 630, 450);
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
        mainFrame.setLayout(null);
        mainFrame.setResizable(true);
        mainFrame.add(send_post);
        // mainFrame.add(send_get);
        mainFrame.add(label_ReqUrl);
        mainFrame.add(select);
        mainFrame.add(print);
        mainFrame.add(exit);
        mainFrame.add(reqUrl);
        mainFrame.add(label_ReqStr);
        mainFrame.add(reqStr);
        mainFrame.add(label_RspStr);
        mainFrame.add(rspStr);
        mainFrame.add(clear);
        mainFrame.add(reset);
    }

    private void initLayOut() {
        label_ReqUrl.setBounds(10, 10, 60, 20);
        label_ReqUrl.setFont(new Font("MS Song", Font.PLAIN, 12));

        reqUrl.setBounds(80, 10, 350, 22);
        // reqUrl.setHorizontalAlignment(JTextField.CENTER);
        select.setBounds(440, 10, 65, 22);
        select.setActionCommand("selctO");
        select.addActionListener(this);
        select.setFont(new Font("MS Song", Font.PLAIN, 12));

        print.setBounds(515, 10, 65, 22);
        print.setActionCommand("selctT");
        print.addActionListener(this);
        print.setFont(new Font("MS Song", Font.PLAIN, 12));

        label_ReqStr.setBounds(10, 40, 60, 20);
        label_ReqStr.setFont(new Font("MS Song", Font.PLAIN, 12));

        reqStr.setBounds(80, 40, 500, 150);

        reset.setBounds(80, 210, 60, 25);
        reset.setActionCommand("reset");
        reset.addActionListener(this);
        reset.setFont(new Font("MS Song", Font.PLAIN, 12));

        clear.setBounds(425, 210, 60, 25);
        clear.setActionCommand("clear");
        clear.addActionListener(this);
        clear.setFont(new Font("MS Song", Font.PLAIN, 12));

        send_post.setBounds(500, 210, 60, 25);
        send_post.setActionCommand("send_post");
        send_post.addActionListener(this);
        send_post.setFont(new Font("MS Song", Font.PLAIN, 12));

        label_RspStr.setBounds(10, 250, 60, 20);
        label_RspStr.setFont(new Font("MS Song", Font.PLAIN, 12));

        rspStr.setBounds(80, 250, 500, 150);
        rspStr.setEditable(false);
        rspStr.setBackground(Color.lightGray);

        // exit.setBounds(600, 300, 60, 20);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String cmd = event.getActionCommand();

        if (cmd.equals("selctO")) {
            jfc1.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            int intRetVal = jfc1.showSaveDialog(null);
            if (intRetVal == JFileChooser.APPROVE_OPTION) {
                this.reqUrl.setText(jfc1.getSelectedFile().getPath());
                this.reqStr.setText(null);
                this.reqStr.setBackground(Color.lightGray);
            }
        }
        if (cmd.equals("selctT")) {
            jfc2.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            int intRetVal = jfc2.showSaveDialog(null);
            if (intRetVal == JFileChooser.APPROVE_OPTION) {
                this.rspStr.setText("结果输出到：" + jfc2.getSelectedFile().getPath());
            }
        }

        String reqtxt = this.reqStr.getText();
        String reqUri = this.reqUrl.getText();

        if (cmd.equals("send_post")) {
            if (StringUtils.isNotBlank(reqtxt)) {// 直接参数推单
                String rspTxt = null;
                try {
                    System.out.println("post....");
                    Map<String, String> reqMap = com.lt.utils.HttpClientUtil.convertStr2Map(reqtxt);
                    System.out.println("reqMap= " + reqMap);
                    rspTxt = HttpClientUtil.requestAsHttpPOST(reqUri, reqMap, "UTF-8", 30000, 30000);

                    if (StringUtils.isBlank(rspTxt))
                        throw new RuntimeException("返回值为空");

                    String rspFormatTxt = XmlFormat.xmlPrettyFormat(rspTxt);
                    rspStr.setText(rspFormatTxt);
                } catch (Exception e) {
                    e.printStackTrace();
                    rspStr.setText(rspTxt);
                    rspStr.append("\n");
                    rspStr.append("\n");
                    rspStr.append(e.getMessage());
                }
            } else {// 表格参数推单
                rspStr.append("\n");
                rspStr.append("正在执行。。。");
                rspStr.append("\n");
                List<String> resultList = RequestSend.sendRequestByExcel(reqUri, jfc2.getSelectedFile().getPath());
                for (String string : resultList) {
                    rspStr.append(string);
                    rspStr.append("\n");
                }
                rspStr.append("执行完成！");
            }
        } else if (cmd.equals("clear")) {
            rspStr.setText("");
        } else if (cmd.equals("reset")) {
            reqUrl.setText("");
            reqStr.setText("");
        }
    }

    public static void main(String[] args) {
        new SendRequestFrame();

        // String reqUri =
        // "http://119.146.222.132/webcontent/agent/platSeller.shtml";
        // String reqTxt =
        // "sign=D5882EC3B063567FA5AB06B44A534305&operateTime=2014-08-15 14:44:14&businessNo=GBLY&channelNo=10100003&content=<?xml version=\"1.0\" encoding=\"UTF-8\"?><request><airwayRebate>0.02</airwayRebate><asmsAutoEtdz/><bigPnrNo>MX93K2</bigPnrNo><businessNo>GBLY</businessNo><buyAmount>1287.2</buyAmount><buyBusinessNo>zhgongbeishanglv</buyBusinessNo><buyPayment>汇付</buyPayment><cardId/><cardType>NI</cardType><deductionAmount/><discount>0.8</discount><etdzOrderNo/><etdzScny>1140.0</etdzScny><flightNo>ZH9534</flightNo><fromDatetime>2014-08-15 20:20</fromDatetime><gaiqianRetirement>按客规</gaiqianRetirement><ifAutoEtdz>1</ifAutoEtdz><ifChangeOrder>0</ifChangeOrder><ifChangePnr>0</ifChangePnr><newBigPnrNo/><newPnrNO/><orderNo>112014081543311930</orderNo><orderStatus>161</orderStatus><passenger>杨晓华</passenger><passengerMobile/><passengerType>1</passengerType><payment>支付宝</payment><paymentTransaction>2014081570481275</paymentTransaction><planeType/><platformFee>4.99</platformFee><pnrNo>JR9TDV</pnrNo><policyId>53118359</policyId><productType>1</productType><realFlightNo/><rebate>0.055</rebate><remark/><saleAmount>1242.31</saleAmount><scny>1140.0</scny><seatClass>H</seatClass><subSeatClass/><tax>120.0</tax><terminal/><ticketNo>479-2363948402</ticketNo><ticketType>BPET</ticketType><toDatetime>2014-08-15 23:35</toDatetime><tofromterminal/><travelRange>ZUHWUX</travelRange><travelType>1</travelType><yq>50.0</yq></request>";
        // Map<String, String> reqMap = HttpClientUtil.convertStr2Map(reqTxt);
        // System.out.println(reqMap);
        // String rspTxt = HttpClientUtil.requestAsHttpPOST(reqUri, reqMap,
        // "UTF-8", 30000, 30000);
        // System.out.println("rspTxt=" + rspTxt);
    }
}
