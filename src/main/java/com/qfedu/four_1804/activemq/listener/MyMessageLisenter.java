package com.qfedu.four_1804.activemq.listener;

import javax.jms.*;

/**
 *@Author feri
 *@Date Created in 2018/10/16 17:04
 */
public class MyMessageLisenter implements MessageListener {
    @Override
    public void onMessage(Message message) {
//        if(message instanceof TextMessage){
//            //文本消息
//        }else if(message instanceof ObjectMessage){
//            //对象消息
//        }
        //1、验证消息类型  文本消息 还是对象消息
        //2、验证消息的具体类型 订单消息  库存消息等
        //3、处理消息

        TextMessage textMessage=(TextMessage) message;
        try {
            System.out.println(System.currentTimeMillis()/1000+"---接收消息："+textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
