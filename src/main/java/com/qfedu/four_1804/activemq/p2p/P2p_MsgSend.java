package com.qfedu.four_1804.activemq.p2p;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.autoconfigure.jms.JmsProperties;

import javax.jms.*;


/**
 *@Author feri
 *@Date Created in 2018/10/16 15:56
 * 点对点 消息发送者
 * 无需同时在线  一条消息只能消费1次
 *
 */
public class P2p_MsgSend {
    public static void main(String[] args) throws JMSException {
        //1、创建连接工厂
        ConnectionFactory factory=new ActiveMQConnectionFactory("tcp://10.8.164.91:61616");
        //2、获取连接对象
        Connection connection=factory.createConnection();
        //3、启动连接
        connection.start();
        //4、获取会话对象  参数说明1、是否开启事务  2、消息应答模式  注意如果开启了事务必须手动应答
        Session session=connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        //5、创建消息存储对象  点对点就选择Queue  发布订阅就选择主题Topic
        Queue queue=session.createQueue("hello");
        //6、创建消息提供者
        MessageProducer producer=session.createProducer(queue);
        //7、创建消息对象
        TextMessage textMessage=session.createTextMessage();
        textMessage.setText("第一条MQ消息："+System.currentTimeMillis());
        //8、发送消息
        producer.send(textMessage);
        //9、关闭
        session.close();
        connection.close();
        System.out.println("消息发送成功");
    }

}
