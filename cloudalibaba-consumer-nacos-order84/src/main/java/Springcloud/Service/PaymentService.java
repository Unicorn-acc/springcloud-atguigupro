package Springcloud.Service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import springcloud.entities.CommonResult;
import springcloud.entities.Payment;

/**
 * Feign：接口+注解
 * Created by Miraclo Wei on 2020/12/26 14:53
 */
@FeignClient(value = "nacos-payment-provider",fallback = PaymentFallbackService.class)
public interface PaymentService {
    @GetMapping(value = "/payment/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id);
}
