package com.jerome.common;

import lombok.Getter;
import lombok.ToString;

/**
 * Created with InteIIiJ IDEA.
 * Description: 账户的状态进行枚举
 * User:
 * Date:2019-08-04
 * Time:10:18
 */
@Getter
@ToString
public enum AccountStatus {//账户状态
    UNLOCK(1, "启用"), LOCK(2, "启停");
    private int flag;
    private String desc;

    //枚举的构造函数默认私有
    AccountStatus(int flag, String desc) {
        this.flag = flag;
        this.desc = desc;
    }

    public static AccountStatus valueOf(int flg) {
        //枚举的values()方法返回枚举的values数组
        for (AccountStatus accountStatus : values()) {
            if (accountStatus.flag == flg) {
                return accountStatus;
            }
        }
        throw new RuntimeException("AccountStatus flg" + flg + "not found !");
    }
}
