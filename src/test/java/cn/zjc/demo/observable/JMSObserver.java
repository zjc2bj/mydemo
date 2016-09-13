package cn.zjc.demo.observable;

import java.util.Observable;
import java.util.Observer;

public class JMSObserver implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        // TODO Auto-generated method stub
        System.out.println("发送消息给jms服务器的观察者已经被执行");
    }

}
