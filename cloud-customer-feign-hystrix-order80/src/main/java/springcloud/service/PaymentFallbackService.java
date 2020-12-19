package springcloud.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

/**
 * 解决代码膨胀 代码混乱问题
 * Created by Miraclo Wei on 2020/12/16 18:30
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService{
    @Override
    public String paymentInfo_OK(Integer id) {
        return "return --PaymentFallbackService paymentInfo_OK--,o(╥﹏╥)o";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "--PaymentFallbackService paymentInfo_Timeout--,o(╥﹏╥)o";
    }
}
