package com.jerome.cmd.impl;

import java.util.Scanner;

/**
 * Created with InteIIiJ IDEA.
 * Description:
 * User:
 * Date:2019-08-04
 * Time:11:48
 */
public interface Command {
    //所有实现此接口的类都会拥有Scanner对象
    Scanner scanner = new Scanner(System.in);
    //统一的方法操作Service层
    void excute(Subject subject);
}
