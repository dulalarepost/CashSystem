package com.jerome.cmd.impl;

import com.jerome.entity.Account;

/**
 * Created with InteIIiJ IDEA.
 * Description:
 * User:
 * Date:2019-08-04
 * Time:15:03
 */
public class CheckStand extends AbstractCommand {
    public static void main(String[] args) {
        Subject subject = new Subject();
        new CheckStand().excute(subject);
    }

    @Override
    public void excute(Subject subject) {
        Commands.getCachedHelpCommands().excute(subject);
        while (true) {
            System.out.println(">>");
            String line = scanner.nextLine();
            String commandCode = line.toUpperCase();//转换位大写
            Account account = subject.getAccount();
            if (account == null) {
                Commands.getEntranceCommand(commandCode).excute(subject);

            } else {
                switch (account.getAccountType()) {
                    case ADMIN:
                        Commands.getAdminCommand(commandCode).excute(subject);
                        break;
                    case CUSTOMER:
                        Commands.getCustomerCommand(commandCode).excute(subject);
                        break;
                    default:
                }
            }
        }
    }
}

