package com.lagou.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author baiaohou
 * @create 2020-07-29 5:08 PM
 */
public class ServerThread extends Thread {
    private Socket s;
    private ServerInitClose sic;

    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    public ServerThread(Socket s, ServerInitClose sic) {
        this.s = s;
        this.sic = sic;
    }

    public ObjectInputStream getOis() {
        return ois;
    }

    public ObjectOutputStream getOos() {
        return oos;
    }

    public Socket getS() {
        return s;
    }

    @Override
    public void run() {
        // Server TO-DO:
        // 3. 使用输入输出流进行通信
        try {
            ois = new ObjectInputStream(s.getInputStream());
            oos = new ObjectOutputStream((s.getOutputStream()));
            System.out.println("服务器初始化成功！");

            while (true) { // keep reading objects
                // 4.声明ServerDao类型的引用，指向该对象
                ServerDao sd = new ServerDao();
                // 3.声明ServerView类型的引用，指向该对象
                ServerView sv = new ServerView(this, sd);
                boolean b = sv.serverReceiver();
                if (!b) break;
            }



        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            System.out.println("客户端" + s.getInetAddress() + "已断开连接!");
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (s != null) {
                try {
                    s.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
