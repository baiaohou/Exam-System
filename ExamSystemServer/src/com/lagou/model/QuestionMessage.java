package com.lagou.model;

/**
 * @author baiaohou
 * @create 2020-07-30 12:00 AM
 */
public class QuestionMessage implements java.io.Serializable {
    private String type;
    private Question question;
    private static final long serialVersionUID = 587319898205378822L;

    public QuestionMessage() {
    }

    public QuestionMessage(String type, Question question) {
        this.type = type;
        this.question = question;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "QuestionMessage{" +
                "type='" + type + '\'' +
                ", question=" + question +
                '}';
    }
}
