package Springcloud.service.impl;

import Springcloud.dao.OrderDao;
import Springcloud.domain.Order;
import Springcloud.service.AccountService;
import Springcloud.service.OrderService;
import Springcloud.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 重要
 * 创建订单->调用库存服务扣减库存->调用账户服务扣减账户余额->修改订单状态
 */
@Service
@Slf4j
public class OrderServiceImpl  implements OrderService {

    @Resource
    private OrderDao orderDao;

    @Resource
    private StorageService storageService;

    @Resource
    private AccountService accountService;

    //http://localhost:2001/order/create?userId=1&productId=1&count=10&money=100
    //There was an unexpected error (type=Internal Server Error, status=500).
    //Read timed out executing POST http://seata-storage-service/storage/decrease?productId=1&count=10
    //feign.RetryableException: Read timed out executing POST http://seata-storage-service/storage/decrease?productId=1&count=10
    //storge-service中故意设置中断测试有没有回滚！！！
    @Override           //abc自己定义的全局事务一开始未修改导致数据库脏数据 问题不大 完结！
    @GlobalTransactional(name = "abc", rollbackFor = Exception.class)
    public void create(Order order) {
        log.info("--------》 开始创建订单");
        //新建订单
        orderDao.create(order);
        log.info("--------》 订单微服务开始调用库存，做扣减---Count-");
        //扣减库存storageService
        storageService.decrease(order.getProductId(), order.getCount());
        log.info("--------》 订单微服务开始调用库存，库存扣减完成！！");
        log.info("--------》 订单微服务开始调用账户，账户扣减---money-");
        //扣减账户accountService
        accountService.decrease(order.getUserId(),order.getMoney());
        log.info("--------》 订单微服务开始调用账户，账户扣减完成!!");
        //修改订单状态，从0到1
        log.info("--------》 订单微服务修改订单状态，start");
        orderDao.update(order.getUserId(),0);
        log.info("--------》 订单微服务修改订单状态，end");

        log.info("--订单结束--");
    }

    @Override
    public void update(Long userId, Integer status) {

    }
}
