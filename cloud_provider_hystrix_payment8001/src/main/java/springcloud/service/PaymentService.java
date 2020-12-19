package springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Created by Miraclo Wei on 2020/12/15 10:08
 */
@Service
public class PaymentService {

    /**
     * 正常访问，不出错
     * @param id
     * @return
     */
    public String paymentInfo_OK(Integer id){
        return "线程池" + Thread.currentThread().getName()+" paymentInfo_OK,id:"+id+"\t"+"O(∩_∩)O哈哈~";
    }

    /**
     *模拟复杂业务流程 需要处理timenumber时间
     * @param id
     * @return
     */
    //报异常后如何处理↓备选方案
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })          //设置服务峰值 5秒钟，超出就去调用备选方案（兜底方法）
    public String paymentInfo_Timeout(Integer id){
        int timenumber = 3;
        try{
            TimeUnit.SECONDS.sleep(timenumber);
        }catch(Exception e){
            e.printStackTrace();
        }
        return "线程池" + Thread.currentThread().getName()+" id:"+id+"\t"+"O(∩_∩)O哈哈~\t耗时(秒)：" +timenumber;
    }


    public String paymentInfo_TimeoutHandler(Integer id){
        return "线程池" + Thread.currentThread().getName()+" 8001系统繁忙，请稍后再试。o(╥﹏╥)o";
    }

    //=====服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name="circuitBreaker.enabled", value="true"),  // 是否开启断路器
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="10"),  //请求次数
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value="10000"), // 时间窗口期10s 时间范围（半开状态 ）
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="60"),  // 失败率达到多少后跳闸
            //整体意思：10秒内 10次请求，有6次失败，就跳闸
    })
    //@HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback")
    public String paymentCircuitBreaker(Integer id){
        if(id < 0){
            throw new RuntimeException("*****id，不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();//糊涂工具包等价UUID.randomUUID().toString()
        return Thread.currentThread().getName() + "\t" + "调用成功，流水号：" + serialNumber;
    }

    public String paymentCircuitBreaker_fallback(Integer id){
        return "id 不能为负数，请稍后再试....ID:"+id;
    }
}
