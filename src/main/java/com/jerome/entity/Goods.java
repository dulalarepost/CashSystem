package com.jerome.entity;

import lombok.Data;

/**
 * Created with InteIIiJ IDEA.
 * Description:
 * User:
 * Date:2019-08-04
 * Time:10:34
 */
@Data
public class Goods {
    private Integer id;
    private String name;
    private String introduce;
    //商品库存
    private Integer stock;
    private String unit;//储存单位
    private Integer price;
    private Integer discount;

    //货物购买的数量
    private Integer BuyGoodsNum;

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("【商品信息】:").append("\n")
                .append("【商品编号】").append(this.getId()).append("\n")
                .append("【商品名称】").append(this.getName()).append("\n")
                .append("【商品简介】").append(this.getIntroduce()).append("\n")
                .append("【商品库存】").append(this.getStock()).append(this.getUnit()).append("\n")
                .append("【商品价格】").append(String.format("%.2f", 1.00D * this.getPrice() / 100)).append(" (元) ").append("\n")
                .append("【商品折扣】").append(this.getDiscount()).append("折").append("\n");
        sb.append("======================================================");
        return sb.toString();
    }
}
