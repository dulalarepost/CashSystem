package com.jerome.service;

import com.jerome.dao.AccountDao;
import com.jerome.entity.Account;
import com.jerome.entity.Goods;
import com.sun.org.apache.bcel.internal.generic.RET;

import java.util.List;

/**
 * Created with InteIIiJ IDEA.
 * Description:
 * User:
 * Date:2019-08-04
 * Time:16:27
 */
//操作Dao层
public class AccountService {
    private AccountDao accountDao;

    public AccountService() {
        this.accountDao = new AccountDao();
    }

    public Account login(String username, String password) {
        return this.accountDao.login(username, password);
    }

    public boolean register(Account account) {
        return this.accountDao.register(account);
    }

    //查看账户
    public List<Account> queryAllAccount(){
        return this.accountDao.queryAllAccount();
    }

    //修改密码
    public boolean updatePassword(String password,int id){
        return this.accountDao.updatePassword(password,id);
    }

    //设置账号启停信息
    public  boolean updateAccountStatus(int id){
        return this.accountDao.updateAccountStatus(id);
    }


}
