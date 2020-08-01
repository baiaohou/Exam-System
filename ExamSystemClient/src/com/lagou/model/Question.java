package com.lagou.model;

/**
 * @author baiaohou
 * @create 2020-07-29 11:55 PM
 */
public class Question implements java.io.Serializable{
    private String id;
    private String header;
    private String choiceA;
    private String choiceB;
    private String choiceC;
    private String choiceD;
    private String answer;
    private static final long serialVersionUID = -8528438728027558707L;

    public Question() {
    }

    public Question(String id, String header, String choiceA, String choiceB, String choiceC, String choiceD, String answer) {
        this.id = id;
        this.header = header;
        this.choiceA = choiceA;
        this.choiceB = choiceB;
        this.choiceC = choiceC;
        this.choiceD = choiceD;
        this.answer = answer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getChoiceA() {
        return choiceA;
    }

    public void setChoiceA(String choiceA) {
        this.choiceA = choiceA;
    }

    public String getChoiceB() {
        return choiceB;
    }

    public void setChoiceB(String choiceB) {
        this.choiceB = choiceB;
    }

    public String getChoiceC() {
        return choiceC;
    }

    public void setChoiceC(String choiceC) {
        this.choiceC = choiceC;
    }

    public String getChoiceD() {
        return choiceD;
    }

    public void setChoiceD(String choiceD) {
        this.choiceD = choiceD;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        if (id != null ? !id.equals(question.id) : question.id != null) return false;
        if (header != null ? !header.equals(question.header) : question.header != null) return false;
        if (choiceA != null ? !choiceA.equals(question.choiceA) : question.choiceA != null) return false;
        if (choiceB != null ? !choiceB.equals(question.choiceB) : question.choiceB != null) return false;
        if (choiceC != null ? !choiceC.equals(question.choiceC) : question.choiceC != null) return false;
        if (choiceD != null ? !choiceD.equals(question.choiceD) : question.choiceD != null) return false;
        return answer != null ? answer.equals(question.answer) : question.answer == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (header != null ? header.hashCode() : 0);
        result = 31 * result + (choiceA != null ? choiceA.hashCode() : 0);
        result = 31 * result + (choiceB != null ? choiceB.hashCode() : 0);
        result = 31 * result + (choiceC != null ? choiceC.hashCode() : 0);
        result = 31 * result + (choiceD != null ? choiceD.hashCode() : 0);
        result = 31 * result + (answer != null ? answer.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Question #" + id + ": " + header + "\n"
                + "\tA: " + choiceA + "\n"
                + "\tB: " + choiceB + "\n"
                + "\tC: " + choiceC + "\n"
                + "\tD: " + choiceD + "\n"
                + "[Ans]: " + answer + "\n";
    }

    public String toStringStudentView() {
        return header + "\n"
                + "\tA: " + choiceA + "\n"
                + "\tB: " + choiceB + "\n"
                + "\tC: " + choiceC + "\n"
                + "\tD: " + choiceD + "\n";
    }
}
