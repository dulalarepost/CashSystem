package com.jerome.cmd.impl.account;

import com.jerome.cmd.annotation.AdminCommand;
import com.jerome.cmd.annotation.CommandMeta;
import com.jerome.cmd.impl.AbstractCommand;
import com.jerome.cmd.impl.Subject;
import com.jerome.entity.Account;

import java.text.Format;
import java.util.List;

/**
 * Created with InteIIiJ IDEA.
 * Description:
 * User:
 * Date:2019-08-04
 * Time:11:41
 */
@CommandMeta(
        name = "CKZH",
        desc = "查看账户",
        group = "账号信息"
)
@AdminCommand
//浏览账户
public class AccountBrowseCommand extends AbstractCommand {
    @Override
    public void excute(Subject subject) {
        System.out.println("查看账户");
        List<Account> accountList = this.accountService.queryAllAccount();
        if (accountList.isEmpty()) {
            printlnInfo("暂时没有账户信息");
        } else {
            System.out.println("------------------账号信息列表---------------------");
            System.out.println("|  编号  |    姓名   |   账号  |  密码  |  类型  | 状态 |");
            for (Account account : accountList) {

                String str = new StringBuilder().append("| ").append(account.getId()).append("    ")
                        .append("| ").append(account.getName()).append("  ")
                        .append("| ").append(account.getUsername()).append("  ")
                        .append("| ").append("******").append("  ")
                        .append("| ").append(account.getAccountType().getDesc()).append("  ")
                        .append("| ").append(account.getAccountStatus().getDesc()).append("  ")
                        .append("| ").toString();
                System.out.println(str);
            }
            System.out.println("--------------------------------------------");
        }
    }
}
