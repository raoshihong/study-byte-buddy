package com.rao.study.bytebuddy.simple;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;

public class SimpleByteBuddy {

    public static void main(String[] args)throws Exception{
        //ByteBuddy采用的是构造者模式
        Class<?> dynamicType = new ByteBuddy()//创建ByteBuddy对象
                .subclass(Object.class)//这里表示创建一个子类继承Object这个类,ByteBuddy有类名命名的策略
                .method(ElementMatchers.<MethodDescription>named("toString"))//给这个类添加一个toString方法
                .intercept(FixedValue.value("Hello World"))//添加拦截器,使用默认的返回值处理拦截器FixedValue,将返回值改为Hello World
                .make()//生成类文件
                .load(SimpleByteBuddy.class.getClassLoader())//获取类加载器
                .getLoaded();//通过类加载器加载这个类,并返回类的Class对象

        //获取了Class的对象,则可以通过反射调用了
        System.out.println(dynamicType.newInstance().toString());
    }
}
