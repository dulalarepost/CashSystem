package com.jerome.cmd.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created with InteIIiJ IDEA.
 * Description:
 * User:
 * Date:2019-08-04
 * Time:11:39
 */
//注解的使用：通过反射对命令进行分类，储存在Map中
//使用注解对命令进行分类
//命令分为：管理员；用户；入口命令

//@Retention注解不仅被保存在class文件中，jvm加载class文件后，仍然存在
//@Target：说明Annotation所修饰的对象范围
//用于描述类，接口（包括注解类型）或enum声明
@Retention(RetentionPolicy.RUNTIME)//RUNTIME：运行时信息会保存在class文件中-->反射的应用
@Target(ElementType.TYPE)//TYPE：表明这个注解可以修饰到类
public @interface AdminCommand {

}
