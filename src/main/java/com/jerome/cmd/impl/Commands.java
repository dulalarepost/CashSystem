package com.jerome.cmd.impl;

import com.jerome.cmd.annotation.AdminCommand;
import com.jerome.cmd.annotation.CommandMeta;
import com.jerome.cmd.annotation.CustomerCommand;
import com.jerome.cmd.annotation.EntranceCommand;
import com.jerome.cmd.impl.account.AccountBrowseCommand;
import com.jerome.cmd.impl.account.AccountPasswordResetCommand;
import com.jerome.cmd.impl.account.AccountStatusSetCommand;
import com.jerome.cmd.impl.common.AboutCommand;
import com.jerome.cmd.impl.common.HelpCommand;
import com.jerome.cmd.impl.common.QuitCommand;
import com.jerome.cmd.impl.entrance.LoginCommand;
import com.jerome.cmd.impl.entrance.RegisterCommand;
import com.jerome.cmd.impl.goods.GoodsBrowseCommand;
import com.jerome.cmd.impl.goods.GoodsPutAwayCommand;
import com.jerome.cmd.impl.goods.GoodsSoldOutCommand;
import com.jerome.cmd.impl.goods.GoodsUpdateCommand;
import com.jerome.cmd.impl.order.OrderBrowseCommand;
import com.jerome.cmd.impl.order.OrderPayCommand;

import java.util.*;

/**
 * Created with InteIIiJ IDEA.
 * Description:
 * User:
 * Date:2019-08-04
 * Time:12:23
 */
public class Commands {
    public static Map<String, Command> ADMIN_COMMANDS = new HashMap<>();
    public static Map<String, Command> CUSTOMER_COMMANDS = new HashMap<>();
    public static Map<String, Command> ENTRANCE_COMMANDS = new HashMap<>();
    //存放所有命令的集合
    private static final Set<Command> COMMANDS = new HashSet<>();
    private static final Command CACHED_HELP_COMMANDS;

    static {
        Collections.addAll(COMMANDS,
                new AccountBrowseCommand(),
                new AccountPasswordResetCommand(),
                new AccountStatusSetCommand(),
                new AboutCommand(),
                //将helpCommand进行缓存
                CACHED_HELP_COMMANDS = new HelpCommand(),
                new HelpCommand(),
                new QuitCommand(),
                new LoginCommand(),
                new RegisterCommand(),
                new GoodsBrowseCommand(),
                new GoodsPutAwayCommand(),
                new GoodsSoldOutCommand(),
                new GoodsUpdateCommand(),
                new OrderBrowseCommand(),
                new OrderPayCommand());
        for (Command command : COMMANDS) {
            //利用反射，将命令进行分类到不同的map
            Class<?> cls = command.getClass();
            AdminCommand adminCommand = cls.getDeclaredAnnotation(AdminCommand.class);
            CustomerCommand customerCommand = cls.getDeclaredAnnotation(CustomerCommand.class);
            EntranceCommand entranceCommand = cls.getDeclaredAnnotation(EntranceCommand.class);
            CommandMeta commandMeta = cls.getDeclaredAnnotation(CommandMeta.class);
            if (commandMeta == null) {
                continue;
            }
            String commandKey = commandMeta.name();
            if (adminCommand != null) {
                ADMIN_COMMANDS.put(commandKey, command);
            }
            if (customerCommand != null) {
                CUSTOMER_COMMANDS.put(commandKey, command);
            }
            if (entranceCommand != null) {
                ENTRANCE_COMMANDS.put(commandKey, command);
            }
        }
    }

    //得到缓存的命令帮助
    public static Command getCachedHelpCommands() {
        return CACHED_HELP_COMMANDS;
    }

    public static Command getAdminCommand(String commandKey) {
        return getCommand(commandKey, ADMIN_COMMANDS);
    }

    public static Command getCustomerCommand(String commandKey) {
        return getCommand(commandKey, CUSTOMER_COMMANDS);
    }

    public static Command getEntranceCommand(String commandKey) {
        return getCommand(commandKey, ENTRANCE_COMMANDS);
    }

    public static Command getCommand(String commandKey, Map<String, Command> commandMap) {

        //遍历相应的Map，根据commandkey得到对应的value
        return commandMap.getOrDefault(commandKey,CACHED_HELP_COMMANDS);
    }

}
