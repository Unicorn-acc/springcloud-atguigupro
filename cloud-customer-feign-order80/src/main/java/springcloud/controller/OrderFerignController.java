package springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import springcloud.entities.CommonResult;
import springcloud.entities.Payment;
import springcloud.service.PaymentFeignService;

import javax.annotation.Resource;

/**
 * Created by Miraclo Wei on 2020/12/12 15:18
 */
@RestController
@Slf4j
public class OrderFerignController {
    //面向接口 相当于去调用Service接口
    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentByIdInFeign(@PathVariable("id") Long id){
        return paymentFeignService.getPaymentid(id);
    }

    @GetMapping(value = "/consumer/payment/feign/timeout")
    public String paymentFeignTimeout(){
        //测试 超时控制 客户端一般默认等待一秒钟 服务强制控制3秒钟延时
        return paymentFeignService.PaymentFeignTimeout();
        //若是为长时间服务，需要在yml中添加配置
    }

}
