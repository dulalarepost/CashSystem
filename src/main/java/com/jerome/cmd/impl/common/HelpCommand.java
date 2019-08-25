package com.jerome.cmd.impl.common;

import com.jerome.cmd.annotation.AdminCommand;
import com.jerome.cmd.annotation.CommandMeta;
import com.jerome.cmd.annotation.CustomerCommand;
import com.jerome.cmd.annotation.EntranceCommand;
import com.jerome.cmd.impl.AbstractCommand;
import com.jerome.cmd.impl.Command;
import com.jerome.cmd.impl.Commands;
import com.jerome.cmd.impl.Subject;
import com.jerome.entity.Account;

import java.util.*;

/**
 * Created with InteIIiJ IDEA.
 * Description:
 * User:
 * Date:2019-08-04
 * Time:11:42
 */
@CommandMeta(
        name = "BZXX",
        desc = "帮助信息",
        group = "公共命令"
)
@AdminCommand
@CustomerCommand
@EntranceCommand
public class HelpCommand extends AbstractCommand {
    @Override
    public void excute(Subject subject) {
        System.out.println("Help_Command");
        Account account = subject.getAccount();
        if (account == null) {
            entranceHelp();
        } else {
            switch (account.getAccountType()) {

                case CUSTOMER:
                    customerHelp();
                    break;
                case ADMIN:
                    adminHelp();
                    break;
                default:
            }
        }
    }

    //Map.value()方法，会返回所有得value集合
    private void entranceHelp() {
        print("欢迎！！！", Commands.ENTRANCE_COMMANDS.values());
    }

    private void adminHelp() {
        print("管理端", Commands.ADMIN_COMMANDS.values());

    }

    private void customerHelp() {
        print("客户端", Commands.CUSTOMER_COMMANDS.values());
    }

    //通用的打印命令
    public void print(String title, Collection<Command> collection) {
        System.out.println("*********" + title + "**********");
        Map<String, List<String>> helpInfo = new HashMap<>();
        for (Command command : collection) {
            //通过反射获取到
            CommandMeta commandMeta = command.getClass().getDeclaredAnnotation(CommandMeta.class);
            String group = commandMeta.group();//Key

            List<String> func = helpInfo.get(group);
            if (func == null) {
                func = new ArrayList<>();
                helpInfo.put(group, func);
            }
            func.add(commandMeta.desc() + "(" + commandMeta.name() + ")");
        }
        int i = 0;
        for (Map.Entry<String, List<String>> entry : helpInfo.entrySet()) {
            i++;
            System.out.println(i + "." + entry.getKey());
            int j = 0;
            for (String item : entry.getValue()) {
                j++;
                System.out.println("\t" + (i) + "." + (j) + " " + item);
            }
            System.out.println("***输入菜单括号里面的操作（忽略大小写），进行下一步操作***");
            System.out.println("*******************************************************");
        }
    }
}
