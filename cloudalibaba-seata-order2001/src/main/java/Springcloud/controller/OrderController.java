package Springcloud.controller;

import Springcloud.domain.CommonResult;
import Springcloud.domain.Order;
import Springcloud.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by Miraclo Wei on 2020/12/27 12:23
 */
@RestController
@Slf4j
public class OrderController {
    @Resource
    private OrderService orderService;

    @GetMapping("/order/create")
    public CommonResult create(Order order){
        orderService.create(order);
        return new CommonResult(200, "订单创建成功！");
    }

}
