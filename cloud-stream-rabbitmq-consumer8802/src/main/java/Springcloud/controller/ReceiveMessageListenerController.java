package Springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.sql.SQLOutput;

/**
 * Created by Miraclo Wei on 2020/12/21 14:25
 */
@Component
@EnableBinding(Sink.class)
public class ReceiveMessageListenerController {

    @Value("${server.port}")
    private  String serverport;

    @StreamListener(Sink.INPUT)
    public void input(Message<String> message){
        //收到一个消息getPayload()
        System.out.println("消费者1号接收到的消息："+message.getPayload()+"\t port"+ serverport);
    }
}
