package springcloud.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Miraclo Wei on 2020/12/22 9:58
 */
@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverport;

    @GetMapping(value = "/payment/nacos/{id}")
    public String getPayment(@PathVariable("id") Long id){
        return "nacos registry, serverPort:"+serverport;
    }
}
