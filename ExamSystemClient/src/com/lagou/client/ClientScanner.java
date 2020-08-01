package com.lagou.client;

import java.util.Scanner;

/**
 * @author baiaohou
 * @create 2020-07-28 12:51 AM
 * 实现扫描器工具类的封装，可以再任意位置使用
 */
public class ClientScanner {
    private static Scanner sc = new Scanner(System.in); // Class.method

    /**
     * 自定义成员方法，实现扫描器的获取
     * @return
     */
    public static Scanner getScanner() { // ClientScanner.getScanner()
        return sc;
    }

    /**
     * 自定义成员方法实现扫描器的关闭
     */
    public static void closeScanner() {
        sc.close();
    }
}
