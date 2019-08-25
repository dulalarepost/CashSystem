package com.jerome.service;

import com.jerome.dao.OrderDao;
import com.jerome.entity.Order;

import java.util.List;

/**
 * Created with InteIIiJ IDEA.
 * Description:
 * User:
 * Date:2019-08-10
 * Time:15:42
 */
public class OrderService {
    private OrderDao orderDao;

    public OrderService() {
        this.orderDao = new OrderDao();
    }

    public boolean commitOrder(Order order) {
        return this.orderDao.commitOrder(order);
    }
    public List<Order> queryOrderByAccount(Integer accountId){
        return this.orderDao.queryOrderByAccount(accountId);
    }
}
