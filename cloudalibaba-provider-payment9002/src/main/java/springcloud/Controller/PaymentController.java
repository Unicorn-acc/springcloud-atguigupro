package springcloud.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import springcloud.entities.CommonResult;
import springcloud.entities.Payment;

import javax.xml.bind.ValidationEventLocator;
import java.util.HashMap;

/**
 * Created by Miraclo Wei on 2020/12/22 11:07
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
