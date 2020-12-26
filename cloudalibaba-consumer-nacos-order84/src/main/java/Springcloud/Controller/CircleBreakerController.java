package Springcloud.Controller;

import Springcloud.Service.PaymentService;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import springcloud.entities.CommonResult;
import springcloud.entities.Payment;

import javax.annotation.Resource;

/**
 * Created by Miraclo Wei on 2020/12/26 12:43
 */
@RestController
@Slf4j
public class CircleBreakerController {
    public static final String SERVICE_URL="http://nacos-payment-provider";

    @Resource
    private RestTemplate restTemplate;
    private Object Throwable;

    @RequestMapping("/consumer/fallback/{id}")
    //@SentinelResource(value = "fallback")//未配置
    //@SentinelResource(value = "fallback",fallback = "handlerFallback")//fallback只负责业务异常
    //@SentinelResource(value = "fallback",blockHandler = "blockHandler")//blockhandler只负责sentinel控制台配置违规
    //若blockHandler和fallback都进行配置，则被限流降级而跑出BlockExceptiono时只会进入BlockHandler处理
    @SentinelResource(value = "fallback",fallback = "handlerFallback",
            blockHandler = "blockHanddler",exceptionsToIgnore = {IllegalArgumentException.class})//排除异常
    public CommonResult<Payment> fallback(@PathVariable Long id){
        CommonResult<Payment> result = restTemplate.getForObject(SERVICE_URL+"/payment/"+id,CommonResult.class);
        if(id == 4){
            throw new IllegalArgumentException("IllegalArgumentException,参数异常");//无熔断无降级策略返回ERROR PAGE
        }else if(result.getData() == null){
            throw new NullPointerException("NullPointerException,对应ID没记录，空指针异常");
        }
        return result;
    }
    //fallback兜底异常
    public CommonResult handlerFallback(@PathVariable Long id,Throwable e){
        Payment payment = new Payment(id,"null");
        return new CommonResult<>(444,"fallback兜底异常handlerFallback,exception内容："+e.getMessage(),payment);
    }

    //blockhandler
    public CommonResult blockHandler(@PathVariable Long id, BlockException blockException){
        Payment payment = new Payment(id,"null");
        return new CommonResult<>(444,"blockhandler限流,blockException："+blockException.getMessage(),payment);
    }

    @Resource
    private PaymentService paymentService;

    @GetMapping(value = "/consumer/paymentSQL/{id}")//整合feign
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id){
        return paymentService.paymentSQL(id);
    }

}
