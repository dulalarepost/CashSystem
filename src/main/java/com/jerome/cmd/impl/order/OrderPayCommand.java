package com.jerome.cmd.impl.order;

import com.jerome.cmd.annotation.CommandMeta;
import com.jerome.cmd.annotation.CustomerCommand;
import com.jerome.cmd.impl.AbstractCommand;
import com.jerome.cmd.impl.Subject;
import com.jerome.common.OrderStatus;
import com.jerome.entity.Goods;
import com.jerome.entity.Order;
import com.jerome.entity.OrderItem;

import java.time.LocalDateTime;
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
        name = "DDZF",
        desc = "订单支付",
        group = "订单信息"
)
@CustomerCommand
public class OrderPayCommand extends AbstractCommand {
    @Override
    public void excute(Subject subject) {
        System.out.println("请输入你要购买的货物id以及多个数量的货物之间使用，隔开，格式：1-8；3-5");
        String string = scanner.nextLine();
        String[] strings = string.split(",");

        List<Goods> goodsList = new ArrayList<>();

        for (String goodsString : strings) {
            String[] str = goodsString.split("-");
            Goods goods = this.goodsService.getGoods(Integer.parseInt(str[0]));
            goods.setBuyGoodsNum(Integer.parseInt(str[1]));
            goodsList.add(goods);
        }

        Order order = new Order();

        order.setId(String.valueOf(System.currentTimeMillis()));


        System.out.println(subject.getAccount());
        order.setAccountId(subject.getAccount().getId());

        order.setAccountId(subject.getAccount().getId());
        order.setAccountName(subject.getAccount().getName());
        order.setCreateTime(LocalDateTime.now());

        int totalMoney = 0;
        int acualMoney = 0;

        for (Goods goods : goodsList) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(order.getId());
            orderItem.setGoodsId(goods.getId());
            orderItem.setGoodsName(goods.getName());
            orderItem.setGoodsIntroduce(goods.getIntroduce());
            orderItem.setGoodsNum(goods.getBuyGoodsNum());
            orderItem.setGoodsUnit(goods.getUnit());
            orderItem.setGoodsPrice(goods.getPrice());
            orderItem.setGoodsDiscount(goods.getDiscount());

            order.orderItemList.add(orderItem);

            int currentMoney = goods.getBuyGoodsNum() * goods.getPrice();
            totalMoney += currentMoney;
            acualMoney += currentMoney * goods.getDiscount() / 100;
        }
        order.setTotalMoney(totalMoney);
        order.setActualMoney(acualMoney);
        order.setOrderStatus(OrderStatus.PLAYING);


        //展示当前订单
        System.out.println(order);
        System.out.println("请确认是否支付以上订单：确认输入：zf");
        String confirm = scanner.nextLine();
        if ("zf".equals(confirm)) {
            order.setFinishTime(LocalDateTime.now());
            order.setOrderStatus(OrderStatus.OK);
            boolean effect = this.orderService.commitOrder(order);
            if (effect) {//插入订单订单项成功
                //将goods表中额具体货物数量更新

                for (Goods goods : goodsList) {
                    boolean isupdate = this.goodsService.updateAfterPay(goods, goods.getBuyGoodsNum());
                    if (isupdate) {
                        printlnInfo("库存更新成功");
                    } else {
                        printlnInfo("库存更新失败");
                    }
                }

            }
        } else {
            printlnInfo("订单没有支付成功，请重新下单！");
        }

    }
}
