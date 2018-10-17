package com.qfedu.four_1804.rabbitmq.p2p;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 *@Author feri
 *@Date Created in 2018/10/17 09:45
 * 基于RabbitMQ 消息提供者
 */
public class MsgProducer {
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
        //5、创建队列对象 存储消息
        /*参数说明：
        * 1、队列名称
        * 2、是否持久化 队列消息是否存储到磁盘
        * 3、是否独占队列
        * 4、是否断开之后自动删除消息*/
        channel.queueDeclare("hello",false,false,false,null);
        //6、发送消息
        /*参数说明：
        * 1、交换机名称
        * 2、队列名称
        * 3、属性参数
        * 4、发送的消息内容 要求字节*/
        channel.basicPublish("","hello",null,"第一天消息".getBytes());
        System.out.println("消息发送成功……");

        //7、关闭销毁
        channel.close();
        connection.close();
    }
}
