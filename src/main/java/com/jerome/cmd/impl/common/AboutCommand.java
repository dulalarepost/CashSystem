package com.jerome.cmd.impl.common;

import com.jerome.cmd.annotation.AdminCommand;
import com.jerome.cmd.annotation.CommandMeta;
import com.jerome.cmd.annotation.CustomerCommand;
import com.jerome.cmd.annotation.EntranceCommand;
import com.jerome.cmd.impl.AbstractCommand;
import com.jerome.cmd.impl.Subject;

/**
 * Created with InteIIiJ IDEA.
 * Description:
 * User:
 * Date:2019-08-04
 * Time:11:42
 */
@CommandMeta(
        name = "GYXT",
        desc = "关于系统",
        group = "公共命令"
)
@AdminCommand
@CustomerCommand
@EntranceCommand
public class AboutCommand extends AbstractCommand {
    @Override
    public void excute(Subject subject) {
        System.out.println("**********************************");
        System.out.println("*******基于字符界面的收银台系统*******");
        System.out.println("*********时间：2019-08-05*********");
        System.out.println("************作者：Jerome************");
        System.out.println("**********************************");
    }
}
