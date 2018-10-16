package com.qfedu.four_1804.activemq.p2p;

import com.qfedu.four_1804.activemq.listener.MyMessageLisenter;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 *@Author feri
 *@Date Created in 2018/10/16 15:57
 * 点对点 消息接收者
 */
public class P2p_MsgRecive {
    public static void main(String[] args) throws JMSException {
        String url="tcp://10.8.164.91:61616";
        //1、创建连接工厂
        ConnectionFactory factory=new ActiveMQConnectionFactory(url);
        //2、获取连接对象
        Connection connection=factory.createConnection();
        //3、启动连接
        connection.start();
        //4、获取会话对象
        Session session=connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        //5、创建消息存储对象
        Queue queue=session.createQueue("hello");
        //6、创建消息接收者
        MessageConsumer consumer=session.createConsumer(queue);

        System.out.println(consumer);
//        //7、阻塞并监听消息
//        Message message=consumer.receive();
//        TextMessage textMessage=(TextMessage)message;
//        //8、显示消息
//        System.err.println("获取消息："+textMessage.getText());
//        //9、关闭
//        session.close();
//        connection.close();

        //7、设置消息监听器
        consumer.setMessageListener(new MyMessageLisenter());



    }
}
