package com.rao.study.bytebuddy.simple;

public class GreetingInterceptor {
    public Object greet(Object argument) {
        return "Hello from " + argument;
    }
}