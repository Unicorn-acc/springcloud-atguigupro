package Springcloud.service;
import Springcloud.domain.Order;
public interface OrderService {


    void create(Order order);

    void update(Long userId, Integer status);
}
