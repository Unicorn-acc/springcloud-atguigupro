package springcloud.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * Created by Miraclo Wei on 2020/12/9 19:24
 */
@RestController
@Slf4j
public class CustomerConsulController {

    @Value("${server.port}")
    private String serverport;

    @RequestMapping(value = "/payment/consul")
    public String paymentzk(){
        return "Springcloud with consul:"+serverport+"\t"+ UUID.randomUUID().toString();
    }




}
