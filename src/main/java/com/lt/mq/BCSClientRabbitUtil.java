package com.lt.mq;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownListener;
import com.rabbitmq.client.ShutdownSignalException;

/**
 * RabbitMQ工具
 * 
 */
public class BCSClientRabbitUtil {

    private static final Logger logger = LoggerFactory.getLogger(BCSClientRabbitUtil.class);

    private static final boolean durable = true;// 消息队列持久化

    private final String exchangeName;// 交换名

    private final String queue;// queue名字

    private static boolean isRuning = false;// 是否启动

    private static final int repeatTime = 10000;// 重启延时，单位：秒

    private Properties properties;// 客户端配置文件对象

    // 业务线SSO配置文件param-name
    private static final String config_name_in_webxml = "MQConfigProperties";

    // RabbitMQ地址
    private static final String bcs_rabbit_addr = "bcs.mq.rabbitmq.host";

    // RabbitMQ端口
    private static final String bcs_rabbit_port = "bcs.mq.rabbitmq.port";

    // RabbitMQ 虚拟主机名
    private static final String bcs_rabbit_vhost = "bcs.mq.rabbitmq.virtualhost";

    // RabbitMQ用户名
    private static final String bcs_rabbit_user = "bcs.mq.rabbitmq.username";

    // RabbitMQ密码
    private static final String bcs_rabbit_psw = "bcs.mq.rabbitmq.password";

    // RabbitMQ交换名
    private static final String bcs_rabbit_exchange = "bcs.mq.rabbitmq.exchange";

    // RabbitMQ queue名字
    private static final String bcs_rabbit_queue = "bcs.mq.rabbitmq.queue";

    /**
     * RabbitMQ初始化
     * 
     * @param addr
     * @param user
     * @param psw
     * @param vhost
     * @param exchange
     */
    public BCSClientRabbitUtil(ServletContext context) {
        String path = context.getInitParameter(config_name_in_webxml);
        if (path == null || path.trim().equals("")) {
            throw new IllegalArgumentException(config_name_in_webxml + " was not set in web.xml->context-param");
        }
        String realPath = context.getRealPath(path);
        checkProperties(realPath);
        exchangeName = properties.getProperty(bcs_rabbit_exchange);
        queue = properties.getProperty(bcs_rabbit_queue);
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(getConfigParameter(bcs_rabbit_addr));
        factory.setPort(Integer.valueOf(getConfigParameter(bcs_rabbit_port)));
        factory.setUsername(getConfigParameter(bcs_rabbit_user));
        factory.setPassword(getConfigParameter(bcs_rabbit_psw));
        factory.setVirtualHost(getConfigParameter(bcs_rabbit_vhost));
        startMQ(factory);
    }

    /**
     * 开启接收信息线程
     * 
     * @param consumer
     */
    private void startListener(final QueueingConsumer consumer) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    QueueingConsumer.Delivery delivery = null;
                    try {
                        delivery = consumer.nextDelivery();
                        String message = new String(delivery.getBody());
                        if (StringUtils.isNotEmpty(message)) {
                            logger.info("MQDoProcessor :" + message);
                            // 通知MQ服务器任务处理完成
                            consumer.getChannel().basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                            logger.info("MQ:" + message + "被消费");
                            try {
                                BspIssueTicketRunnable bspIssue = new BspIssueTicketRunnable(message);
                                Thread t = new Thread(bspIssue);
                                t.start();
                                Thread.sleep(5 * 1000);
                            } catch (Exception e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        } else {
                            logger.info("MQ:取出的数据异常···" + message);
                        }
                    } catch (Exception e) {
                        // e.printStackTrace(); //不要打印异常信息,会导致Mq重连失败.切记！
                    }
                }
            }
        }).start();
    }

    /**
     * 连接MQ失败后隔十秒重新连接
     * 
     * @param factory
     */
    private void startMQ(final ConnectionFactory factory) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 1;
                while (!isRuning) {
                    try {
                        Thread.sleep(repeatTime); // 十秒后重新连接
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    QueueingConsumer consumer = initRabbitMQ(factory);
                    if (isRuning) {
                        System.out.println("BCS Rabbit MQ连接成功，连接次数：" + i);
                    } else {
                        System.out.println("BCS Rabbit MQ连接失败，" + repeatTime / 1000 + "秒后重试");
                    }
                    if (consumer != null) {
                        startListener(consumer);
                    }
                    i++;
                }
                System.out.println("BSP监听器初始化成功!");
            }
        }).start();
    }

    /**
     * 初始化MQ连接信息并绑定Queue、Exchange
     * 
     * @param factory
     * @return QueueingConsumer
     * @throws IOException
     */
    private QueueingConsumer initRabbitMQ(final ConnectionFactory factory) {
        QueueingConsumer consumer = null;
        try {
            Connection connection = factory.newConnection();
            connection.addShutdownListener(new ShutdownListener() {
                @Override
                public void shutdownCompleted(ShutdownSignalException e) {
                    System.out.println("BCS RabbitMQ  服务中断" + e);
                    isRuning = false;
                    startMQ(factory);
                }
            });
            Channel channel = connection.createChannel();
            // 声明此队列并且持久化
            channel.queueDeclare(queue, true, false, false, null);
            channel.basicQos(1);// 告诉RabbitMQ同一时间给一个消息给消费者
            consumer = new QueueingConsumer(channel);
            /*
             * 把名字为TASK_QUEUE_NAME的Channel的值回调给QueueingConsumer,
             * 即使一个worker在处理消息的过程中停止了 ，这个消息也不会失效
             */
            channel.basicConsume(queue, false, consumer);

            isRuning = true;
            return consumer;
        } catch (Exception e) {
            // e.printStackTrace(); //不要打印异常信息,会导致Mq重连失败.切记！
        }
        return consumer;
    }

    private String getConfigParameter(String name) {
        return properties.getProperty(name);
    }

    /**
     * 检查SSO客户端配置文件参数有效性
     * 
     * @param path
     */
    private void checkProperties(String path) {

        try {
            properties = new Properties();
            properties.load(new FileReader(new File(path)));
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
        if (StringUtils.isBlank(getConfigParameter(bcs_rabbit_addr))) {
            throw new IllegalArgumentException("[" + bcs_rabbit_addr + "] was not set in " + path);
        }
        if (StringUtils.isBlank(getConfigParameter(bcs_rabbit_port))) {
            throw new IllegalArgumentException("[" + bcs_rabbit_port + "] was not set in " + path);
        }
        if (StringUtils.isBlank(getConfigParameter(bcs_rabbit_user))) {
            throw new IllegalArgumentException("[" + bcs_rabbit_user + "] was not set in " + path);
        }
        if (StringUtils.isBlank(getConfigParameter(bcs_rabbit_psw))) {
            throw new IllegalArgumentException("[" + bcs_rabbit_psw + "] was not set in " + path);
        }
        if (StringUtils.isBlank(getConfigParameter(bcs_rabbit_vhost))) {
            throw new IllegalArgumentException("[" + bcs_rabbit_vhost + "] was not set in " + path);
        }
        if (StringUtils.isBlank(getConfigParameter(bcs_rabbit_exchange))) {
            throw new IllegalArgumentException("[" + bcs_rabbit_exchange + "] was not set in " + path);
        }
    }

    class BspIssueTicketRunnable implements Runnable {
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public BspIssueTicketRunnable(String message) {
            this.message = message;
        }

        @Override
        public void run() {
            try {
                // com.bcs.util.BspIssueTicket.dealBSPIssueTicket(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
