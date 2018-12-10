package com.rao.study.bytebuddy.agent;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;

import java.lang.instrument.Instrumentation;

public class SimpleAgent {

    public static void premain(String arguments, Instrumentation instrumentation) {

        /**
         * Byte Buddy不仅限于创建子类，还能够重新定义现有代码。 为此，Byte Buddy提供了一个方便的API来定义所谓的Java代理。 Java代理是普通的旧Java程序，
         * 可用于在运行时更改现有Java应用程序的代码。 例如，我们可以使用Byte Buddy来更改方法以打印其执行时间。
         * 为此，我们首先定义一个拦截器，类似于前面例子中的拦截器：
         */

        //使用Byte-Buddy中的代理,这个代理
        System.out.println("this is an perform monitor agent.");

        AgentBuilder.Transformer transformer = new AgentBuilder.Transformer() {
            @Override
            public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder,
                                                    TypeDescription typeDescription,
                                                    ClassLoader classLoader) {
                return builder
                        .method(ElementMatchers.<MethodDescription>any()) // 拦截任意方法
                        .intercept(MethodDelegation.to(TimingInterceptor.class)); // 委托
            }
        };

        AgentBuilder.Listener listener = new AgentBuilder.Listener() {
            @Override
            public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule module, DynamicType dynamicType) {
            }

            @Override
            public void onIgnored(TypeDescription typeDescription, ClassLoader classLoader, JavaModule module) {
            }

            @Override
            public void onError(String typeName, ClassLoader classLoader, JavaModule module, Throwable throwable) {
            }

            @Override
            public void onComplete(String typeName, ClassLoader classLoader, JavaModule module) {
            }
        };

        new AgentBuilder
                .Default()
                .type(ElementMatchers.nameStartsWith("com.rao.study.bytebuddy.agent.AgentTest")) // 指定需要拦截的类
                .transform(transformer)
                .with(listener)//使用监听
                .installOn(instrumentation);
    }

}

