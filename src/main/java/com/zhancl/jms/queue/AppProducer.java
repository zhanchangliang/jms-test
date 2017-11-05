package com.zhancl.jms.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by Administrator on 2017/11/5.
 */
public class AppProducer {

    private static final String url="tcp://127.0.0.1:61616";
    private static final String queueName = "queue-test";
    public static void main(String[] args) throws JMSException {
        //创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        //创建连接
        Connection connection=connectionFactory.createConnection();
        //启动连接
        connection.start();
        //创建会话
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        //创建一个目标
        Destination destination =session.createQueue(queueName);
        //创建一个生产者
        MessageProducer producer =session.createProducer(destination);

        for (int i = 0; i < 100; i++) {
            //创建消息
            TextMessage textMessage = session.createTextMessage("test"+i);
            producer.send(textMessage);
        }

        connection.close();
    }
}
