package Springcloud.Service;

import org.springframework.stereotype.Component;
import springcloud.entities.CommonResult;
import springcloud.entities.Payment;

/**
 * 实现fallback
 * Created by Miraclo Wei on 2020/12/26 14:56
 */
@Component
public class PaymentFallbackService implements PaymentService {
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(444,"服务降级返回,--PaymentFallbackService");
    }
}
