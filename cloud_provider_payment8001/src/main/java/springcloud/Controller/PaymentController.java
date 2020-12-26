package springcloud.Controller;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;  //导包
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import springcloud.entities.CommonResult;
import springcloud.entities.Payment;
import springcloud.service.PaymentService;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Miraclo Wei on 2020/12/3 19:57
 */
//使用@Controller 注解，在对应的方法上，视图解析器可以解析return 的jsp,html页面，并且跳转到相应页面
//若返回json等内容到页面，则需要加@ResponseBody注解
//@RestController注解相当于@ResponseBody ＋ @Controller合在一起的作用。
@RestController
@Slf4j    //打日志
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}") //查看当前使用的eureka端口
    private String serverPort;

    @Resource   //能被拿到的信息
    private DiscoveryClient discoveryClient;


    //不用告诉前端要传入什么类型的数据（Payment），只认规范和约定
    //传给前端只用CommentResult(200，success)
    @PostMapping(value = "/payment/create") //写操作用PostMapping
    public CommonResult create(@RequestBody Payment payment){//并在传入参数上@RequestBody注解
            //Get请求发送数据的方式不是json格式，所以当我们使用@RequsetBody封装Get请求的数据时就会出现无法获取到数据的情况
            //post请求，如果是接收json格式,接收参数要是一个参数或者是一个对象并且参数前加上@RequestBody注解）
        int result = paymentService.create(payment);
        log.info("插入结果："+ result);  //打日志
        if(result > 0) {
            return new CommonResult(200, "插入数据库成功,serverPort:"+serverPort, result);
        }else{
            return new CommonResult(444,"插入数据库失败",null);
        }
    }

    @GetMapping(value = "/payment/get/{id}") //读操作用GetMaping
    public CommonResult getPaymentid(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("查询结果："+ payment);
        if(payment != null) {
            return new CommonResult(200, "查询成功,serverPort:"+serverPort, payment);
        }else{
            return new CommonResult(444,"没有对应记录,查询id："+id,null);
        }
    }

    @GetMapping(value = "/payment/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();  //获取服务清单列表 注意与下面的区别
        for (String element : services) {
            log.info("element: " + element);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");//从对外暴露的名称获得信息
                //获取一个微服务下面的全部各种具体实例payment8002 , payment8001
        for(ServiceInstance instance : instances){
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getUri());
        }
        return this.discoveryClient;
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLb(){
        return serverPort;
    }

    //测试Feign超时控制
    @GetMapping(value = "/payment/feign/timeout")
    public String PaymentFeignTimeout(){
        //暂停几秒钟 3 秒钟
        try{
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return serverPort;
    }

    @GetMapping("/payment/zipkin")
    public String testzipkin(){
        return "zipkin ------";
    }

}
