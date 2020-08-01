package com.lagou.model;

/**
 * @author baiaohou
 * @create 2020-07-28 2:55 PM
 */
public class UserMessage implements java.io.Serializable {
    private static final long serialVersionUID = -4729638835655094399L;

    private String type; // 消息类型代表具体业务
    private User user;

    public UserMessage() {
    }

    public UserMessage(String type, User user) {
        this.type = type;
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserMessage{" +
                "type='" + type + '\'' +
                ", user=" + user +
                '}';
    }
}
