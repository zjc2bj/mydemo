package com.lt.mq;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lt.utils.PropertyUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

/**
 * RabbitMQ工具
 * 
 */
public class BCSServerRabbitUtil {
    private static final Logger logger = LoggerFactory.getLogger(BCSClientRabbitUtil.class);

    private static Channel channel;// RabbitMQ通道对象

    private static String exchangeName;// RabbitMQ交换名

    private static String queue;// queue名字

    static {
        constructorProperty();
    }

    private static void constructorProperty() {
        String host = PropertyUtil.getProperty("bcs.mq.rabbitmq.host", "/config/properties/mq.bcs.properties");
        String port = PropertyUtil.getProperty("bcs.mq.rabbitmq.port", "/config/properties/mq.bcs.properties");
        String user = PropertyUtil.getProperty("bcs.mq.rabbitmq.username", "/config/properties/mq.bcs.properties");
        String psw = PropertyUtil.getProperty("bcs.mq.rabbitmq.password", "/config/properties/mq.bcs.properties");
        String vhost = PropertyUtil.getProperty("bcs.mq.rabbitmq.virtualhost", "/config/properties/mq.bcs.properties");
        exchangeName = PropertyUtil.getProperty("bcs.mq.rabbitmq.exchange", "/config/properties/mq.bcs.properties");
        queue = PropertyUtil.getProperty("bcs.mq.rabbitmq.queue", "/config/properties/mq.bcs.properties");
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);
        factory.setPort(Integer.valueOf(port));
        factory.setUsername(user);
        factory.setPassword(psw);
        factory.setVirtualHost(vhost);
        try {
            Connection connection = factory.newConnection();
            channel = connection.createChannel();
            // 声明此队列并且持久化
            channel.queueDeclare(queue, true, false, false, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送消息
     * 
     * @param msg
     */
    public static boolean sendMessage(String msg) {
        try {
            channel.basicPublish("", queue, MessageProperties.PERSISTENT_TEXT_PLAIN, msg.getBytes());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            if (channel == null || !channel.isOpen()) {
                constructorProperty();
            }
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        try {
            channel.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        BCSServerRabbitUtil.sendMessage("bscp2013112600029177");
        BCSServerRabbitUtil.sendMessage("bscp2013112600029178");
        // for (int i = 0; i < 100; i++) {
        // try {
        // Thread.sleep(1000);
        // } catch (InterruptedException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
        logger.info("发送MQ数据 orderIdis:bscp2013112600029178");
        // }

    }

}
