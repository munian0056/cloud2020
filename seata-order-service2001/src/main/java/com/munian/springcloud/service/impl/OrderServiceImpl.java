package com.munian.springcloud.service.impl;

import com.munian.springcloud.dao.OrderDao;
import com.munian.springcloud.domain.Order;
import com.munian.springcloud.service.AccountService;
import com.munian.springcloud.service.OrderService;
import com.munian.springcloud.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;

    @Resource
    private StorageService storageService;

    @Resource
    private AccountService accountService;

    @GlobalTransactional(name = "fsp-create-order",rollbackFor = Exception.class)
    public void create(Order order) {
        log.info("---->开始创建订单");
        //新建订单
        orderDao.create(order);

        log.info("---->订单微服务开始调用库存，做扣减count");
        //扣减库存
        storageService.decrease(order.getProductId(),order.getCount());
        log.info("---->订单微服务开始调用库存，扣减end");

        log.info("---->订单微服务开始调用账户，做扣减money");
        //扣减账户
        accountService.decrease(order.getUserId(),order.getMoney());
        log.info("---->订单微服务开始调用账户，扣减end");

        //修改订单状态，从0到1，1代表已完成
        log.info("---->修改订单状态开始");
        orderDao.update(order.getUserId(),0);
        log.info("---->修改订单状态end");

        log.info("---->新建订单结束，O(∩_∩)O");
    }
}
