package springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springcloud.service.IMessageProvider;

import javax.annotation.Resource;

/**
 * Created by Miraclo Wei on 2020/12/21 8:56
 */
@RestController
public class SendMessageController {
    @Resource
    private IMessageProvider iMessageProvider;

    @GetMapping(value = "/sendMessage")
    public String SendMessage(){
        return iMessageProvider.send();
    }
}
