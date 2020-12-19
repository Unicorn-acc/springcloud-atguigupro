package springcloud.service;

import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import springcloud.entities.CommonResult;
import springcloud.entities.Payment;

/**
 * Created by Miraclo Wei on 2020/12/12 15:09
 */
@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")  //作为feign功能的接口，去调用8001服务提供者服务接口
public interface PaymentFeignService { //feign接口
    //CommonResult<Payment> getPaymentById(@Param("id") Long id);
    @GetMapping(value = "/payment/get/{id}") //调用的地址
    public CommonResult getPaymentid(@PathVariable("id") Long id);

    @GetMapping(value = "/payment/feign/timeout")
    public String PaymentFeignTimeout();
}
