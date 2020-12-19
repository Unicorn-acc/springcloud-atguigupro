package spring.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Created by Miraclo Wei on 2020/12/8 9:26
 */
@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverport;

    @RequestMapping(value = "/payment/zk")
    public String paymentzk(){
        return "Springcloud with zookeeper:"+serverport+"\t"+ UUID.randomUUID().toString();
    }


}
