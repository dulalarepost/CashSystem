package com.jerome.cmd.impl.goods;

import com.jerome.cmd.annotation.AdminCommand;
import com.jerome.cmd.annotation.CommandMeta;
import com.jerome.cmd.impl.AbstractCommand;
import com.jerome.cmd.impl.Subject;
import com.jerome.entity.Goods;

/**
 * Created with InteIIiJ IDEA.
 * Description:
 * User:
 * Date:2019-08-04
 * Time:11:44
 */
@CommandMeta(
        name = "XJSP",
        desc = "下架商品",
        group = "商品信息"
)
@AdminCommand
public class GoodsSoldOutCommand extends AbstractCommand {
    @Override
    public void excute(Subject subject) {
        System.out.println("下架商品");
        System.out.println("请输入下架商品编号：");
        int goodsId = scanner.nextInt();

        scanner.nextLine();

        Goods goods = this.goodsService.getGoods(goodsId);
        if (goods == null) {
            System.out.println("您要下架的商品不存在！");
        } else {
            System.out.println("您要下架的商品信息如下：");
            System.out.println(goods);
            printlnInfo("请确认是否下架（y/n yes/no）");

            String flag = scanner.nextLine();
            if ("y".equalsIgnoreCase(flag) || "yes".equalsIgnoreCase(flag)) {
                boolean effect = this.goodsService.soldOutGoods(goodsId);
                if (effect) {
                    printlnInfo("下架成功！");
                } else {
                    printlnInfo("下架失败！");
                }
            }else{
                printlnInfo("您选择了不下架！");
            }
        }
    }
}
