package com.jerome.cmd.impl.account;

import com.jerome.cmd.annotation.AdminCommand;
import com.jerome.cmd.annotation.CommandMeta;
import com.jerome.cmd.impl.AbstractCommand;
import com.jerome.cmd.impl.Subject;

/**
 * Created with InteIIiJ IDEA.
 * Description:设置账号启停信息，
 * User:
 * Date:2019-08-04
 * Time:11:41
 */
@CommandMeta(
        name = "QTZH",
        desc = "启停账号",
        group = "账号信息"
)
@AdminCommand
public class AccountStatusSetCommand extends AbstractCommand {
    @Override
    public void excute(Subject subject) {
        printlnInfo("设置账号启停信息!");
        printlnInfo("您当前账号信息启停信息："+subject.getAccount().getAccountStatus().getFlag()+": "+
                subject.getAccount().getAccountStatus().getDesc());
        printlnInfo("请确定是否要更改当前账号信息，一旦设置为停用，您将无法登陆，需要联系管理员帮助您更改设置");
        printlnInfo("输入y/yes表示确定，输入其他表示放弃！");
        String message = scanner.nextLine();
        if("y".equalsIgnoreCase(message)||"yes".equalsIgnoreCase(message)){
            boolean effect = this.accountService.updateAccountStatus(subject.getAccount().getId());
            if(effect){
                printlnInfo("您的账号已经设置为停用，本次下线之后您将无法登陆，如需解决请联系管理员！");
            }
        }else{
            printlnInfo("您选择了放弃更新账号启停信息");
        }

    }
}
