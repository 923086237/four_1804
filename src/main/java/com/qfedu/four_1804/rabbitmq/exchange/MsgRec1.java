package com.qfedu.four_1804.rabbitmq.exchange;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 *@Author feri
 *@Date Created in 2018/10/17 11:07
 */
public class MsgRec1 {
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

        //5、声明路由对象 指定名称
        channel.exchangeDeclare("test1","fanout");

        //6、获取随机队列名称
        String queueName=channel.queueDeclare().getQueue();

        //7、将队列和路由进行绑定 参数说明：1、队列名称 2、路由名称 3、参数信息
        channel.queueBind(queueName,"test1","");

        System.out.println("1消费者---监听");
        //6、接收消息
        Consumer consumer=new DefaultConsumer(channel){
            //重写回调函数
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg=new String(body);
                System.out.println("1接收消息："+msg);
            }
        };
        //自动回复应答
        channel.basicConsume(queueName,true,consumer);
    }
}
