package com.jerome.cmd.impl;

import com.jerome.service.AccountService;
import com.jerome.service.GoodsService;
import com.jerome.service.OrderService;


/**
 * Created with InteIIiJ IDEA.
 * Description:
 * User:
 * Date:2019-08-04
 * Time:11:45
 */
//抽象类实现的Comand接口有统一的Scanner对象和excute方法
//启动的每一个服务就会拥有Scanner对象和excute方法
public abstract class AbstractCommand implements Command {
    //启动所有服务
    public AccountService accountService;
    public GoodsService goodsService;
    public OrderService orderService;

    public AbstractCommand() {
        accountService = new AccountService();
        goodsService = new GoodsService();
        orderService = new OrderService();
    }
    //通用的打印信息的方法
    public void printlnInfo(String info) {
        System.out.println(info);
    }
}
