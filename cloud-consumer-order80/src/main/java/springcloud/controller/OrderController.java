package springcloud.controller;

import jdk.nashorn.internal.ir.debug.ClassHistogramElement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import springcloud.entities.CommonResult;
import springcloud.entities.Payment;
import springcloud.lb.LoadBalancer;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * Created by Miraclo Wei on 2020/12/4 1:20
 */
@RestController
@Slf4j
public class OrderController {

    //public static final String  PAYMENT_URL = "http://localhost:8001";
    public static final String  PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";
    //更新 多个服务提供从只认微服务名称  需要开启负载均衡功能(注解 默认轮询)
    //在这里要去调用8001微服务提供者
    //两个服务间调用？RestTemplate:远程Http服务方法的封装
    @Resource //注入并实例化
    private RestTemplate restTemplate;

    @Resource
    private LoadBalancer loadBalancer;
    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("customer/payment/create") //在开头不再加"/"
    public CommonResult<Payment> create(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
                //写用post 读用get
    }

    //JSON getForObject推荐使用
    @GetMapping("customer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
            return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }

    //ENTITY对象 getForEntity
    @GetMapping("customer/payment/forEntity/{id}")
    public CommonResult<Payment> getPaymentById2(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        if(entity.getStatusCode().is2xxSuccessful()){
            log.info(entity.getStatusCode()+"\t"+entity.getHeaders());
            return entity.getBody();
        }else{
            return new CommonResult<>(444, "操作失败");
        }
    }

    @GetMapping(value = "customer/payment/lb")
    public String getpaymentlb(){
        //得到两个服务8001,8002
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if(instances == null || instances.size() <= 0){
            return null;
        }
        //传入在8001和8002中选择
        ServiceInstance serviceInstance =loadBalancer.instances(instances);
        URI uri = serviceInstance.getUri();
        return restTemplate.getForObject(uri+"/payment/lb",String.class);
    }

    //==========zipkin + sleuth
    @GetMapping("/customer/zipkin")
    public String testzipkin(){
        String result = restTemplate.getForObject(PAYMENT_URL+"/payment/zipkin", String.class);
        return result;
    }

}
