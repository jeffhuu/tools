package com.jeff.demo.mq;

import com.alibaba.fastjson.JSONObject;
import com.jeff.demo.entity.User;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @program: demo
 * @description:
 * @author: Jeff Hu 2022/06/14 16:29
 */

@Component
public class DirectReceiver {

    @RabbitHandler
    @RabbitListener(queues = "TestDirectQueue")//监听的队列名称 TestDirectQueue
    public void handler(Channel channel, Message message) {
        String send = new String(message.getBody());
        User user = JSONObject.parseObject(send, User.class);
        System.out.println("DirectReceiver消费者收到消息  : " + user);
    }

}
