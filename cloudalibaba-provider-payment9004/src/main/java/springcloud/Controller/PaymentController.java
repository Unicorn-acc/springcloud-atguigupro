package springcloud.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import springcloud.entities.CommonResult;
import springcloud.entities.Payment;

import java.util.HashMap;

/**
 * Created by Miraclo Wei on 2020/12/26 11:28
 */
@RestController
public class PaymentController {
    @Value("${server.port}")
    private String serverport;

    @GetMapping(value = "/payment/nacos/{id}")
    public String getPayment(@PathVariable("id") Long id){
        return "nacos registry, serverPort:"+serverport;
    }

    public static HashMap<Long, Payment> hashMap = new HashMap<>();
    static{
        hashMap.put(1L,new Payment(1L,"11111111111111111"));
        hashMap.put(2L,new Payment(2L,"22222222222222222"));
        hashMap.put(3L,new Payment(3L,"33333333333333333"));
    }

    @GetMapping(value = "/payment/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id){
        Payment payment = hashMap.get(id);
        CommonResult<Payment> result = new CommonResult<>(200,"From mysql,serverPort"+serverport,hashMap.get(id));
        return result;
    }

}
