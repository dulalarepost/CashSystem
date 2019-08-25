package com.jerome.cmd.impl.entrance;

import com.jerome.cmd.annotation.CommandMeta;
import com.jerome.cmd.annotation.EntranceCommand;
import com.jerome.cmd.impl.AbstractCommand;
import com.jerome.cmd.impl.Subject;
import com.jerome.common.AccountStatus;
import com.jerome.entity.Account;

/*
* 登陆界面
* */
/**
 * Created with InteIIiJ IDEA.
 * Description:
 * User:
 * Date:2019-08-04
 * Time:11:43
 */
@CommandMeta(
        name = "DL",
        desc = "登陆",
        group = "入口命令"
)
@EntranceCommand
public class LoginCommand extends AbstractCommand {
    @Override
    public void excute(Subject subject) {
        //拿到当前的观察对象
        //TODO
        Account account = subject.getAccount();
        if(account!= null){
            System.out.println("您已经登陆过了");
        }
        System.out.println("请输入用户名： ");
        String username = scanner.nextLine();
        System.out.println("请输入密码： ");
        String password = scanner.nextLine();
        //操作数据库
        account = this.accountService.login(username,password);
        //AccountStatus.UNLOCK：账号启用
        if(account !=null&&account.getAccountStatus() == AccountStatus.UNLOCK){
            System.out.println(account.getAccountType()+"登陆成功");
            subject.setAccount(account);
        }else {
            System.out.println("密码或者用户名错误");
        }
    }
}
