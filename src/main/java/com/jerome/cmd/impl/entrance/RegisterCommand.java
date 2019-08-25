package com.jerome.cmd.impl.entrance;

import com.jerome.cmd.annotation.CommandMeta;
import com.jerome.cmd.annotation.EntranceCommand;
import com.jerome.cmd.impl.AbstractCommand;
import com.jerome.cmd.impl.Subject;
import com.jerome.common.AccountStatus;
import com.jerome.common.AccountType;
import com.jerome.entity.Account;

/**
 * Created with InteIIiJ IDEA.
 * Description:
 * User:
 * Date:2019-08-04
 * Time:11:43
 */
@CommandMeta(
        name = "ZC",
        desc = "注册",
        group = "入口命令"
)
@EntranceCommand
public class RegisterCommand extends AbstractCommand {
    @Override
    public void excute(Subject subject) {
        System.out.println("请输入用户名：");
        String username = scanner.nextLine();
        System.out.println("请输入密码：");
        String password1 = scanner.nextLine();
        System.out.println("请再次输入密码：");
        String password2 = scanner.nextLine();
        if (!password1.equals(password2)) {
            System.out.println("您输入的两次密码不一致！");
            return;
        }
        System.out.println("请输入真实姓名：");
        String name = scanner.nextLine();
        System.out.println("请输入账户类型：（1->管理员，2->普通用户）");//（1代表管理员，2代表普通用户）
        int accountType = scanner.nextInt();
        AccountType accountType1 = AccountType.valueOf(accountType);
        System.out.println("请输入用户状态:(1->启用; 2->启停)");//1：启用   2：启停
        int accountStatus = scanner.nextInt();
        AccountStatus accountStatus1 = AccountStatus.valueOf(accountStatus);
//注册账户，账户不能发生改变，用final修饰
        final Account account = new Account();
        account.setUsername(username);
        account.setPassword(password1);//此处没有加密，我们在Dao层进行加密
        account.setAccountType(accountType1);
        account.setName(name);
        account.setAccountStatus(accountStatus1);
        boolean effect = this.accountService.register(account);
        if (effect) {
            System.out.println("注册成功");
        } else {
            System.out.println("注册失败");
        }
    }
}
