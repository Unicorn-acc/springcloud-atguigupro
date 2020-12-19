package springcloud.service;

import org.apache.ibatis.annotations.Param;
import springcloud.entities.Payment;

/**
 * Created by Miraclo Wei on 2020/12/3 19:53
 */
public interface PaymentService {
    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}
