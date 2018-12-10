package com.rao.study.bytebuddy.agent;

public class AgentTest {

    private void fun1() throws Exception {
        System.out.println("this is fun 1.");
        Thread.sleep(500);
    }

    private void fun2() throws Exception {
        System.out.println("this is fun 2.");
        Thread.sleep(500);
    }

    public static void main(String[] args) throws Exception {//运行时指定jvm参数-javaagent:D:\study-byte-buddy\target\study-byte-buddy-1.0-SNAPSHOT.jar，就可以使用ideal进行代码调试了
        AgentTest test = new AgentTest();
        test.fun1();
        test.fun2();

    }
}
