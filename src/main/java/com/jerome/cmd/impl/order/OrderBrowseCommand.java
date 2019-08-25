package com.jerome.cmd.impl.order;

import com.jerome.cmd.annotation.CommandMeta;
import com.jerome.cmd.annotation.CustomerCommand;
import com.jerome.cmd.impl.AbstractCommand;
import com.jerome.cmd.impl.Subject;
import com.jerome.entity.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with InteIIiJ IDEA.
 * Description:
 * User:
 * Date:2019-08-04
 * Time:11:44
 */
@CommandMeta(
        name = "LLDD",
        desc = "浏览订单",
        group = "订单信息"
)
@CustomerCommand

public class OrderBrowseCommand extends AbstractCommand {
    @Override
    public void excute(Subject subject) {
        System.out.println("我的订单列表");
        List<Order> orderList = this.orderService.queryOrderByAccount(subject.getAccount().getId());
        if(orderList.isEmpty()){
            printlnInfo("暂时没订单");
        }else{
            for(Order order:orderList){
                printlnInfo("-----------------------开始分割线--------------------------");
                System.out.println(order);
                printlnInfo("-----------------------结束分割线--------------------------");
            }
        }
    }
}
