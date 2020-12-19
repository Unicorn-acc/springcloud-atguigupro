package springcloud.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import springcloud.entities.Payment;

/**
 * 与数据库打交道
 * Created by Miraclo Wei on 2020/12/3 19:12
 */
@Mapper
public interface PaymentDao {
    //增删改查 add save create
    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);

}