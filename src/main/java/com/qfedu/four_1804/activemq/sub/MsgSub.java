package com.qfedu.four_1804.activemq.sub;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 *@Author feri
 *@Date Created in 2018/10/16 15:57
 *
 * 消息发布者
 */
public class MsgSub {
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
        //6、消息提供者
        MessageProducer producer=session.createProducer(topic);
        //7、创建消息对象
        TextMessage textMessage=session.createTextMessage("Java是全中国最爱的语言");
        //8、发送消息
        producer.send(textMessage);



    }
}
