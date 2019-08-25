package com.jerome.entity;

import com.jerome.common.AccountStatus;
import com.jerome.common.AccountType;
import lombok.Data;


/**
 * Created with InteIIiJ IDEA.
 * Description:
 * User:
 * Date:2019-08-04
 * Time:10:16
 */
@Data
public class Account {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private AccountType accountType;
    private AccountStatus accountStatus;
}
