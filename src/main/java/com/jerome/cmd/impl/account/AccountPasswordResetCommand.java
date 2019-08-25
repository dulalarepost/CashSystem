package com.jerome.cmd.impl.account;

import com.jerome.cmd.annotation.AdminCommand;
import com.jerome.cmd.annotation.CommandMeta;
import com.jerome.cmd.annotation.CustomerCommand;
import com.jerome.cmd.impl.AbstractCommand;
import com.jerome.cmd.impl.Subject;
import org.apache.commons.codec.digest.DigestUtils;
import sun.security.provider.MD4;
import sun.security.provider.MD5;

/**
 * Created with InteIIiJ IDEA.
 * Description:管理员和用户应该都拥有充值密码的权限，用来更改自己当前账户的密码
 * User:
 * Date:2019-08-04
 * Time:11:41
 */
@CommandMeta(
        name = "CZMM",
        desc = "重置密码",
        group = "账号信息"
)
@AdminCommand
//@CustomerCommand

public class AccountPasswordResetCommand extends AbstractCommand {
    @Override
    public void excute(Subject subject) {
        System.out.println("更改密码");
        printlnInfo("请输入旧密码？");
        String oldPassword = scanner.nextLine();
        if (DigestUtils.md5Hex(oldPassword).equals(subject.getAccount().getPassword())) {
            printlnInfo("请输入新密码？");
            String newPassword1 = scanner.nextLine();
            printlnInfo("请再次输入密码？（应该与上次保持一致）");
            String newPassword2 = scanner.nextLine();
            if (!newPassword1.equals(newPassword2)) {
                printlnInfo("你两次输入的密码不一致");
                return;
            }
            boolean effect = this.accountService.updatePassword(newPassword2, subject.getAccount().getId());
            if (effect) {
                printlnInfo("密码修改成功");
            }else{
                printlnInfo("密码修改失败！");
            }
        }else {
            printlnInfo("密码输入有误！");
        }

    }
}
