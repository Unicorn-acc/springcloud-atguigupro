package Springcloud.dao;

import Springcloud.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Miraclo Wei on 2020/12/27 10:56
 */
@Mapper
public interface OrderDao {

    //创建订单
    public void create(Order order);

    //修改订单状态
    public void update(@Param("userId") Long userId, @Param("status") Integer status);

}
