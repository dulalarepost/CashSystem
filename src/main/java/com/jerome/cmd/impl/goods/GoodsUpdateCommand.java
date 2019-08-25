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
        name = "SPGX",
        desc = "商品更新",
        group = "商品信息"
)
@AdminCommand
public class GoodsUpdateCommand extends AbstractCommand {
    @Override
    public void excute(Subject subject) {
        System.out.println("更新商品");
        printlnInfo("请输入更新商品的编号");
        int goodsId = scanner.nextInt();
        scanner.nextLine();
        //查看商品是否存在
        Goods goods = this.goodsService.getGoods(goodsId);
        if (goods == null) {
            System.out.println("没有此编号的货物");
            return;//结束函数
        } else {
            //打印商品的原信息
            printlnInfo("商品信息如下：");
            System.out.println(goods);
        }
        printlnInfo("请输入要更新商品的简介");
        String introduce = scanner.nextLine();
        printlnInfo("请输入要更新商品的库存");
        int stock = scanner.nextInt();
        scanner.nextLine();
        printlnInfo("请输入要更新商品的单位");
        String unit = scanner.nextLine();
        printlnInfo("请输入要更新商品单价:单位（元）,保留小数点后两位");
        int price = new Double(100 * scanner.nextDouble()).intValue();
        printlnInfo("请输入要更新商品折扣");
        int discount = scanner.nextInt();

        scanner.nextLine();

        printlnInfo("请确认是否要更新：（y/yes表示确定；n/no表示拒绝）");
        String flag = scanner.nextLine();
        if("y".equalsIgnoreCase(flag)||"yes".equalsIgnoreCase(flag)){
            goods.setIntroduce(introduce);
            goods.setStock(stock);
            goods.setUnit(unit);
            goods.setPrice(price);
            goods.setDiscount(discount);
            //执行更新
            //更新数据库的goods表
            boolean effect = this.goodsService.modifyGoods(goods);
            if(effect){
                System.out.println("商品更新成功");
            }else{
                printlnInfo("商品更新失败");
            }
        }else{
            printlnInfo("你选择了不更新此商品");
        }
    }
}
