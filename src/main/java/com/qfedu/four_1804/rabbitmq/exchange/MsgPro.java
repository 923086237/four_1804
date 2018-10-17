package com.qfedu.four_1804.rabbitmq.exchange;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 *@Author feri
 *@Date Created in 2018/10/17 11:05
 */
public class MsgPro {
    public static void main(String[] args) throws IOException, TimeoutException {
        //1、创建连接工厂
        ConnectionFactory factory=new ConnectionFactory();
        //2、设置服务器
        factory.setHost("10.8.164.91");
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setVirtualHost("/");
        //3、获取连接对象
        Connection connection=factory.newConnection();
        //4、获取通道对象
        Channel channel=connection.createChannel();

        //5、创建路由对象 参数说明：1、路由名称 唯一 2、路由类型 4大类型 topic fanout(分发)
        channel.exchangeDeclare("test1","fanout");
        //6、发送消息
        /*参数说明：
         * 1、交换机名称
         * 2、队列名称
         * 3、属性参数
         * 4、发送的消息内容 要求字节*/
        String msg="发布订阅消息："+System.currentTimeMillis();
        channel.basicPublish("test1","",null,msg.getBytes());

        System.out.println("消息发送成功……");

        //7、关闭销毁
        channel.close();
        connection.close();
    }
}
