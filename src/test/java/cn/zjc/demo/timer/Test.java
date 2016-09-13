package cn.zjc.demo.timer;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Test extends JFrame {

    JLabel lbl = new JLabel();

    Date now = new Date();

    public Test() {

        now.setHours(0);
        now.setMinutes(0);
        now.setSeconds(0);

        Timer timer = new Timer(1000, new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                Date now2 = new Date(now.getTime() + 1000);
                now = now2;
                SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");

                lbl.setText(formatter.format(now));
            }

        });
        timer.start();

        this.setLayout(new FlowLayout());
        this.add(lbl);

        this.setSize(300, 200);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        Test t = new Test();
    }
}
