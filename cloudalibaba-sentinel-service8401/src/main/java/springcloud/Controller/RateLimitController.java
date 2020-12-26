package springcloud.Controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springcloud.entities.CommonResult;
import springcloud.entities.Payment;
import springcloud.myHandler.CustomerBlockHandler;


/**
 * Created by Miraclo Wei on 2020/12/26 9:45
 */
@RestController
public class RateLimitController {

    @GetMapping("/byResource")//测试按资源名限流
    @SentinelResource(value = "byResource", blockHandler = "handleException")
    public CommonResult byResource() {
        return new CommonResult(200, "按照资源名限流测试0K", new Payment(2020L, "serial001"));
    }

    public CommonResult handleException(BlockException e) {
        return new CommonResult(444, e.getClass().getCanonicalName() + "\t 服务不可用");
    }

    @GetMapping("/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",
            blockHandlerClass = CustomerBlockHandler.class,  //自定义兜底处理类
            blockHandler = "handlerException2")  //兜底处理类里面的方法
    public CommonResult customerRequest() {
        return new CommonResult(200, "嘎嘎嘎自定义----success", new Payment(2020L, "serial001"));
    }
}