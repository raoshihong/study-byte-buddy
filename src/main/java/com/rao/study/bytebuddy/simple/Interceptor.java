package com.rao.study.bytebuddy.simple;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

public class Interceptor {
    public static void main(String[] args) throws Exception{

        Class<? extends java.util.function.Function> dynamicType = new ByteBuddy()
                .subclass(java.util.function.Function.class)
                .method(ElementMatchers.named("apply"))
                .intercept(MethodDelegation.to(new GreetingInterceptor()))//使用自定义的拦截方法,这个类必须是public的
                .make()
                .load(Interceptor.class.getClassLoader())
                .getLoaded();

        System.out.println((String) dynamicType.newInstance().apply("Byte Buddy"));
    }
}


