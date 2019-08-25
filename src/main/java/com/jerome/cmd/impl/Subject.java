package com.jerome.cmd.impl;

import com.jerome.entity.Account;

/**
 * Created with InteIIiJ IDEA.
 * Description:
 * User:
 * Date:2019-08-04
 * Time:11:49
 */

//观察对象
//观察一个账户（可能是管理员，可能是普通客户）
public class Subject {
    private Account account;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}
