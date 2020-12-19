package springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import springcloud.service.PaymentHystrixService;

import javax.annotation.Resource;

/**
 * Created by Miraclo Wei on 2020/12/15 14:50
 */
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
    //没有fallbackMethod的就用统一处理页面全局兜底方法 带fallbackMethod的就用自己的
    //通用和独享各自分开，避免代码膨胀
public class OrderHystrixController {
    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        String result = paymentHystrixService.paymentInfo_OK(id);
        return result;
    }

    /*@HystrixCommand(fallbackMethod = "paymentInfo_timeoutHandler", commandProperties = {
            //设置峰值，超过 3 秒，就会调用兜底方法
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value = "1500")
    })*/
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    @HystrixCommand//降级兜底，用全局兜底方法 timeoutInMilliseconds:15000改成 1500
    public String paymentInfo_Timeout(@PathVariable("id")Integer id){
        //log.info("paymentInfo_timeout");
        return paymentHystrixService.paymentInfo_Timeout(id);
    }
    //注意:找不到fallbackMethod的错误的,fallback函数的参数类型和返回值类型要和原来的方法一致
    public String paymentInfo_timeoutHandler(@PathVariable("id") Integer id){
        //log.info("paymentInfo_timeout--handler");
        return "消费者80访问 payment8001 失败o(╥﹏╥)o----人工报错";
    }
    //每个业务都有对应兜底方法 代码膨胀↓
    //global fallback 全局通用 全局默认方法不能有形参
    public String payment_Global_FallbackMethod(){
        return "Global异常处理信息，请稍后再试。o(╥﹏╥)o";
    }
}
