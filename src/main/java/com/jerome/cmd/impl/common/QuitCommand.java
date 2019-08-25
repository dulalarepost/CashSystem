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
        name = "TC",
        desc = "退出系统",
        group = "公共命令"
)
@AdminCommand
@CustomerCommand
@EntranceCommand
public class QuitCommand extends AbstractCommand {
    @Override
    public void excute(Subject subject) {
        System.out.println("退出系统，欢迎再次使用");
        this.scanner.close();
        System.exit(0);
    }
}
