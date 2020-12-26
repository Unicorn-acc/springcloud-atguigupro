package springcloud.myHandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import springcloud.entities.CommonResult;
import springcloud.entities.Payment;

/**
 * 自定义兜底全局降级处理类
 * Created by Miraclo Wei on 2020/12/26 9:50
 */
public class CustomerBlockHandler {

    public static CommonResult handlerException(BlockException e){
        return new CommonResult(414, "用户自定义的全局降级处理类----1", new Payment(2525L, "serialdkf1"));
    }

    public static CommonResult handlerException2(BlockException e){
        return new CommonResult(414, "用户自定义的全局降级处理类====2", new Payment(2525L, "serialdkf1"));
    }
}
