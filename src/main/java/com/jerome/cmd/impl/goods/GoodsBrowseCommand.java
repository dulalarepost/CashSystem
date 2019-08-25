package com.jerome.cmd.impl.goods;

import com.jerome.cmd.annotation.AdminCommand;
import com.jerome.cmd.annotation.CommandMeta;
import com.jerome.cmd.annotation.CustomerCommand;
import com.jerome.cmd.impl.AbstractCommand;
import com.jerome.cmd.impl.Subject;
import com.jerome.entity.Goods;

import java.util.List;

/**
 * Created with InteIIiJ IDEA.
 * Description:
 * User:
 * Date:2019-08-04
 * Time:11:43
 */
@CommandMeta(
        name = "LLSP",
        desc = "浏览商品",
        group = "商品信息"
)
@AdminCommand
@CustomerCommand
public class GoodsBrowseCommand extends AbstractCommand {
    @Override
    public void excute(Subject subject) {
        System.out.println("浏览商品");
        //1.查询所有商品
        List<Goods> goodsList = this.goodsService.queryAllGoods();
        if (goodsList.isEmpty()) {
            System.out.println("商品为空");
        } else {
            for (Goods goods : goodsList) {
                System.out.println(goods);
            }
        }

    }
}
