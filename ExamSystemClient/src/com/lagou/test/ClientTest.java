package com.lagou.test;

import com.lagou.client.ClientInitClose;
import com.lagou.client.ClientScanner;
import com.lagou.client.ClientView;

import java.io.IOException;

/**
 * @author baiaohou
 * @create 2020-07-28 12:34 AM
 */
public class ClientTest {
    public static void main(String[] args) {
        ClientInitClose cic = null;
        try {
            // 1. Declare ClientInitClose instance
            cic = new ClientInitClose();
            // 2. 调用成员方法实现客户端的初始化
            cic.clientInit();
            // 3. 声明ClientView类型的引用指向该类型的对象
            ClientView cv = new ClientView(cic);
            cv.clientMainPage();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                cic.clientClose();
                ClientScanner.closeScanner();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 3.调用成员方法实现客户端的关闭
    }
}
