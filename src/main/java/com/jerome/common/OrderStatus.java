package com.jerome.common;

import lombok.Getter;
import lombok.ToString;

/**
 * Created with InteIIiJ IDEA.
 * Description:
 * User:
 * Date:2019-08-04
 * Time:10:40
 */
@Getter//枚举的构造函数私有
@ToString
public enum OrderStatus {
    PLAYING(1, "待支付"), OK(2, "支付完成");
    private int flag;
    private String desc;

    OrderStatus(int flag, String desc) {
        this.flag = flag;
        this.desc = desc;
    }

    public static OrderStatus valueOf(int flg) {
        for (OrderStatus orderStatus : values()) {
            if (orderStatus.flag == flg) {
                return orderStatus;
            }
        }
        throw new RuntimeException("OrderStatus flg" + flg + "not found !");
    }
}
