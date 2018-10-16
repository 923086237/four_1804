package com.qfedu.four_1804.activemq.sub;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 *@Author feri
 *@Date Created in 2018/10/16 15:58
 * 消息订阅者
 */
public class MsgPub {
    public static void main(String[] args) throws JMSException {
        String url="tcp://10.8.164.91:61616";
        //1、创建连接工厂
        ConnectionFactory factory=new ActiveMQConnectionFactory(url);
        //2、创建连接对象
        Connection connection=factory.createConnection();
        //3、启动连接
        connection.start();
        //4、创建会话
        Session session=connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        //5、创建存储消息对象
        Topic topic=session.createTopic("java");
        //6、消息消费者
        MessageConsumer consumer=session.createConsumer(topic);
        //7、阻塞并监听消息
        TextMessage message=(TextMessage)consumer.receive();
        //8、处理消息
        System.err.println("接收消息："+message.getText());
    }
}
