package com.jerome.common;

import lombok.Getter;
import lombok.ToString;

/**
 * Created with InteIIiJ IDEA.
 * Description:
 * User:
 * Date:2019-08-04
 * Time:10:16
 */
@Getter
@ToString
public enum AccountType {
    ADMIN(1, "管理员"), CUSTOMER(2, "客户");
    private int flag;
    private String desc;

    AccountType(int flag, String desc) {
        this.flag = flag;
        this.desc = desc;
    }

    public static AccountType valueOf(int flg) {
        for (AccountType accountType : values()) {
            if (accountType.flag == flg) {
                return accountType;
            }
        }
        throw new RuntimeException("AccountType flg" + flg + "not found !");
    }
}
