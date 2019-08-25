package com.jerome.cmd.impl.goods;

import com.jerome.cmd.annotation.AdminCommand;
import com.jerome.cmd.annotation.CommandMeta;
import com.jerome.cmd.impl.AbstractCommand;
import com.jerome.cmd.impl.Subject;
import com.jerome.entity.Goods;

/**
 * Created with InteIIiJ IDEA.
 * Description:商品上架
 * User:
 * Date:2019-08-04
 * Time:11:43
 */
@CommandMeta(
        name = "SJSP",
        desc = "上架商品",
        group = "商品信息"
)
@AdminCommand

public class GoodsPutAwayCommand extends AbstractCommand {
    @Override
    public void excute(Subject subject) {
        System.out.println("上架商品");
        System.out.println("请输入商品名称");
        String name = scanner.nextLine();
        printlnInfo("商品简介");
        String introduce = scanner.nextLine();
        printlnInfo("请输入商品库存");
        int stock = scanner.nextInt();
        scanner.nextLine();
        printlnInfo("请输入商品单位");
        String unit = scanner.nextLine();
        printlnInfo("请输入商品单价:单位（元）,保留小数点后两位");
        int price = new Double(100 * scanner.nextDouble()).intValue();
        printlnInfo("请输入商品折扣");
        int discount = scanner.nextInt();
        //Goods不加final，往后可能面临更新
        Goods goods = new Goods();
        goods.setName(name);
        goods.setIntroduce(introduce);
        goods.setStock(stock);
        goods.setUnit(unit);
        goods.setPrice(price);
        goods.setDiscount(discount);

        boolean effect = this.goodsService.putAwayGoods(goods);
        if (effect) {
            System.out.println("上架成功");
        } else {
            System.out.println("上架失败");
        }

    }
}
