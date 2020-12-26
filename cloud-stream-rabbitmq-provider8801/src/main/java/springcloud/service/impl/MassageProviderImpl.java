package springcloud.service.impl;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import springcloud.service.IMessageProvider;

import javax.annotation.Resource;
import java.util.UUID;

/**.
 * 与RabbitMQ打交道，不再@Service
 * 基于Stream做output 指定通道开启Binder
 * 消息生产者->Source->Channel->Binder->rabbitmq
 * Created by Miraclo Wei on 2020/12/21 8:45
 */
@EnableBinding(Source.class) //定义消息的推送管道
public class MassageProviderImpl implements IMessageProvider {

    //不再调入Dao 在此操作的是rabbitmq
    @Resource
    private MessageChannel output;//消息发送管道

    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        //发了一个message:withPayload
        output.send(MessageBuilder.withPayload(serial).build());
        System.out.println("serial:"+ serial);
        return null;
    }

}
