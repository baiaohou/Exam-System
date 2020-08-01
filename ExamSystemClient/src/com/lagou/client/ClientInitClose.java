package com.lagou.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author baiaohou
 * @create 2020-07-28 12:28 AM
 * 编程实现客户端的初始化和关闭
 */
public class ClientInitClose {

    /**
     * attributes
     */
    private Socket s;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    public ObjectOutputStream getOos() {
        return oos;
    }

    public ObjectInputStream getOis() {
        return ois;
    }

    /**
     * 客户端初始化
     */
    public void clientInit() throws IOException {
        // 1. create Socket object, give IP and port #
        s = new Socket(InetAddress.getLocalHost(), 8888);
        // 2. use i/o stream
        oos = new ObjectOutputStream(s.getOutputStream());
        ois = new ObjectInputStream(s.getInputStream());
        System.out.println("连接服务器成功！");
    }

    /**
     * 客户端关闭操作
     */
    public void clientClose() throws IOException {
        // 3. close stream
        ois.close();
        oos.close();
        s.close();
    }
}
