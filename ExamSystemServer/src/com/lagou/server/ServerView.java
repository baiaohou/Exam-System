package com.lagou.server;

import com.lagou.model.*;
import com.lagou.questionDB.QuestionDB;
import com.lagou.studentDB.StudentDB;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * @author baiaohou
 * @create 2020-07-28 3:05 PM
 * 实现服务器的主功能
 */
public class ServerView {

    /**
     * 合成符永原则
     */
//    private ServerInitClose sic;
    private ServerThread st;
    private ServerDao sd;


    /**
     * 构造方法实现成员变量的初始化
     */
//    public ServerView(ServerInitClose sic, ServerDao sd) {
    public ServerView(ServerThread st, ServerDao sd) {
        this.st = st;
        this.sd = sd;
    }

    /**
     * 实现客户端发来消息的接收并处理
     */
    public boolean serverReceiver() throws IOException, ClassNotFoundException {
        Object o = st.getOis().readObject();
        if (o == null) return false;
        System.out.println(o.getClass());

        if (o.getClass().toString().equals("class com.lagou.model.UserMessage")) {
            /**
             *  user message
             */
            UserMessage tum = (UserMessage) o;
            System.out.println("接收到的消息类型：" + tum.getClass());
            System.out.println("接收到的消息是：" + tum);
            switch (tum.getType()) {
                case "managerCheck":
                    serverManagerCheck(tum);
                    break;
                case "studentLoginCheck":
                    serverStudentLoginCheck(tum);
                    break;
                case "studentChangePassword":
                    serverStudentChangePassword(tum);
                default:
                    break;
            }
        } else if (o.getClass().toString().equals("class com.lagou.model.StudentMessage")) {
            /**
             *  student message
             */

            StudentDB.readList();

            StudentMessage tsm = (StudentMessage) o;
            System.out.println("接收到的消息类型："+ tsm.getClass());
            System.out.println("接收到的消息是：" + tsm);
            switch (tsm.getType()) {
                case "studentAdd":
                    System.out.println("服务端收到客户端" + st.getS().getInetAddress() + "的【添加学生】请求。");
                    System.out.println("请求数据为：" + tsm.getType());
                    System.out.println("调用方法为：addStudent");

                    boolean isSuccess1 = StudentDB.addStudent(tsm.getStudent());
                    serverStudentCheck(tsm, isSuccess1);
                    StudentDB.quitSave();
                    break;
                case "studentDelete":
                    System.out.println("服务端收到客户端" + st.getS().getInetAddress() + "的【删除学生】请求。");
                    System.out.println("请求数据为：" + tsm.getType());
                    System.out.println("调用方法为：deleteStudent");

                    boolean isSuccess2 = StudentDB.deleteStudent(tsm.getStudent());
                    serverStudentCheck(tsm, isSuccess2);
                    StudentDB.quitSave();
                    break;
                case "studentModify":
                    System.out.println("服务端收到客户端" + st.getS().getInetAddress() + "的【修改学生】请求。");
                    System.out.println("请求数据1为：" + tsm.getType());
                    Student oldInfo = tsm.getStudent();
                    serverStudentCheck(tsm, true);

                    tsm = (StudentMessage) st.getOis().readObject(); // read next newInfo
                    System.out.println("请求数据2为：" + tsm.getType());
                    Student newInfo = tsm.getStudent();

                    System.out.println("调用方法为：modifyStudent");

                    boolean isSuccess3 = StudentDB.modifyStudent(oldInfo, newInfo);
                    serverStudentCheck(tsm, isSuccess3);
                    StudentDB.quitSave();
                    break;
                case "studentSearch":
                    System.out.println("服务端收到客户端" + st.getS().getInetAddress() + "的【查询学生】请求。");
                    System.out.println("请求数据为：" + tsm.getType());
                    System.out.println("调用方法为：searchStudent");

                    boolean isSuccess4 = StudentDB.searchStudent(tsm.getStudent());
                    serverStudentCheck(tsm, isSuccess4);
                    StudentDB.quitSave();
                    break;
                case "studentIterate":
                    System.out.println("服务端收到客户端" + st.getS().getInetAddress() + "的【遍历学生】请求。");
                    System.out.println("请求数据为：" + tsm.getType());
                    System.out.println("调用方法为：iterateShow");
                    tsm.setType(StudentDB.iterateShow());
                    System.out.println("响应结果为：" + tsm);
                    st.getOos().writeObject(tsm);
                    System.out.println("服务器发送校验结果成功！");
                    StudentDB.quitSave();
                    break;
                default: break;
            }
        } else if (o.getClass().toString().equals("class com.lagou.model.QuestionMessage")) {
            /**
             *  question message
             */

            com.lagou.questionDB.QuestionDB.readList();

            QuestionMessage tsm = (QuestionMessage) o;
            System.out.println("接收到的消息类型：" + tsm.getClass());
            System.out.println("接收到的消息是：" + tsm);
            switch (tsm.getType()) {
                case "questionAdd":
                    System.out.println("服务端收到客户端" + st.getS().getInetAddress() + "的【添加考题】请求。");
                    System.out.println("请求数据为：" + tsm.getType());
                    System.out.println("调用方法为：addQuestion");

                    boolean isSuccess1 = com.lagou.questionDB.QuestionDB.addQuestion(tsm.getQuestion());
                    serverQuestionCheck(tsm, isSuccess1);
                    QuestionDB.quitSave();
                    break;
                case "questionDelete":
                    System.out.println("服务端收到客户端" + st.getS().getInetAddress() + "的【删除考题】请求。");
                    System.out.println("请求数据为：" + tsm.getType());
                    System.out.println("调用方法为：deleteQuestion");

                    boolean isSuccess2 = com.lagou.questionDB.QuestionDB.deleteQuestion(tsm.getQuestion());
                    serverQuestionCheck(tsm, isSuccess2);
                    QuestionDB.quitSave();
                    break;
                case "questionModify":
                    System.out.println("服务端收到客户端" + st.getS().getInetAddress() + "的【修改考题】请求。");
                    System.out.println("请求数据1为：" + tsm.getType());
                    Question oldInfo = tsm.getQuestion();
                    serverQuestionCheck(tsm, true);

                    tsm = (QuestionMessage) st.getOis().readObject(); // read next newInfo
                    System.out.println("请求数据2为：" + tsm.getType());
                    Question newInfo = tsm.getQuestion();

                    System.out.println("调用方法为：modifyQuestion");

                    boolean isSuccess3 = QuestionDB.modifyQuestion(oldInfo, newInfo);
                    serverQuestionCheck(tsm, isSuccess3);
                    QuestionDB.quitSave();
                    break;
                case "questionSearch":
                    System.out.println("服务端收到客户端" + st.getS().getInetAddress() + "的【查询考题】请求。");
                    System.out.println("请求数据为：" + tsm.getType());
                    System.out.println("调用方法为：searchQuestion");

                    boolean isSuccess4 = QuestionDB.searchQuestion(tsm.getQuestion());
                    serverQuestionCheck(tsm, isSuccess4);
                    QuestionDB.quitSave();
                    break;
                case "questionIterate":
                    System.out.println("服务端收到客户端" + st.getS().getInetAddress() + "的【遍历考题】请求。");
                    System.out.println("请求数据为：" + tsm.getType());
                    System.out.println("调用方法为：iterateShow");
                    tsm.setType(QuestionDB.iterateShow());
                    System.out.println("响应结果为：" + tsm);
                    st.getOos().writeObject(tsm);
                    System.out.println("服务器发送校验结果成功！");
                    QuestionDB.quitSave();
                    break;
                case "questionInput":
                    //
                    st.getOos().writeObject(tsm);
                    System.out.println("服务器发送校验结果成功！");
                    //
                    tsm = (QuestionMessage) st.getOis().readObject(); // read next newInfo
                    File f = new File(tsm.getType());
                    FileReader fr = new FileReader(f);
                    String s = "";
                    int res = 0;
                    while ((res = fr.read()) != -1) {
                        s += ((char) res) + "";
                    }
                    fr.close();
                    String[] arr = s.split("\\s+");
                    List<String> list = new ArrayList(Arrays.asList(arr));

                    QuestionDB.readList();
                    while (list.size() != 0) {
                        Question curr = new Question(list.get(0), list.get(1), list.get(2),
                                list.get(3), list.get(4), list.get(5), list.get(6));
                        QuestionDB.addQuestion(curr);
                        for (int i = 1; i <= 7 ; i++) {
                            list.remove(0);
                        }
                    }
                    QuestionDB.quitSave();
                    System.out.println(QuestionDB.iterateShow());

                    //
                    tsm.setType("success");
                    st.getOos().writeObject(tsm);
                default:
                    break;
            }
        } else if (o.getClass().toString().equals("class java.lang.String")) {
            String receive = (String) o;
            switch (receive) {
                case "startExam":
                    QuestionDB.readList();

                    List<Question> pool = new ArrayList<>(QuestionDB.db);
                    System.out.println(pool);
                    List<Question> ret = new ArrayList<>();

                    for (int i = 1; i <= 3 ; i++) {
                        Random r = new Random();
                        int n = r.nextInt(pool.size());
                        Question q = pool.remove(n);
                        ret.add(q);
                    }
                    st.getOos().writeObject(ret);
                    // now get result and time
                    String time = (String) st.getOis().readObject();
                    Integer score = (Integer) st.getOis().readObject();
                    QuestionDB.scores.put(time, score);
                    System.out.println(QuestionDB.scores);
                    break;
                case "searchExam":
                    HashMap<String, Integer> map = new HashMap(QuestionDB.scores);
                    System.out.println(map);
                    st.getOos().writeObject(map);
                    break;
                default:
                    break;
            }
        }
        return true;
    }




    private void serverStudentCheck(StudentMessage tsm, boolean isSuccess) throws IOException {
        if (isSuccess) {
            tsm.setType("success");
        } else {
            tsm.setType("fail");
        }
        st.getOos().writeObject(tsm);
        System.out.println("响应结果为：" + tsm);
        System.out.println("服务器发送校验结果成功！");
    }


    private void serverQuestionCheck(QuestionMessage tsm, boolean isSuccess) throws IOException {
        if (isSuccess) {
            tsm.setType("success");
        } else {
            tsm.setType("fail");
        }
        st.getOos().writeObject(tsm);
        System.out.println("响应结果为：" + tsm);
        System.out.println("服务器发送校验结果成功！");
    }



    public void serverManagerCheck(UserMessage tum) throws IOException {
        System.out.println("服务端收到客户端" + st.getS().getInetAddress() + "的【管理员登录】请求。");
        System.out.println("请求数据为：" + tum.getType());
        System.out.println("调用方法为：serverManagerCheck");
        // 调用方法实现管理员账号和密码信息的校验
        if (sd.serverManagerCheck(tum.getUser())) {
            tum.setType("success");
        } else {
            tum.setType("fail");
        }
        // 将校验结果发送给客户端
//        sic.getOos().writeObject(tum);
        st.getOos().writeObject(tum);
        System.out.println("响应结果为：" + tum);
        System.out.println("服务器发送校验结果成功！");
    }

    private void serverStudentLoginCheck(UserMessage tum) throws IOException, ClassNotFoundException {
        System.out.println("服务端收到客户端" + st.getS().getInetAddress() + "的【学员登录】请求。");
        System.out.println("请求数据为：" + tum.getType());
        System.out.println("调用方法为：studentLoginCheck");
        // 调用方法实现管理员账号和密码信息的校验
        if (sd.serverStudentUsernamePasswordCheck(tum.getUser())) {
            tum.setType("success");
        } else {
            tum.setType("fail");
        }
        // 将校验结果发送给客户端
//        sic.getOos().writeObject(tum);
        st.getOos().writeObject(tum);
        System.out.println("响应结果为：" + tum);
        System.out.println("服务器发送校验结果成功！");
    }

    private void serverStudentChangePassword(UserMessage tum) throws IOException, ClassNotFoundException {
        System.out.println("服务端收到客户端" + st.getS().getInetAddress() + "的【学员修改密码】请求。");
        System.out.println("请求数据为：" + tum.getType());
        System.out.println("调用方法为：studentChangePassword");
        // 调用方法实现管理员账号和密码信息的校验
        if (sd.serverStudentChangePassword(tum.getUser())) {
            tum.setType("success");
        } else {
            tum.setType("fail");
        }
        // 将校验结果发送给客户端
        st.getOos().writeObject(tum);
        System.out.println("响应结果为：" + tum);
        System.out.println("服务器发送校验结果成功！");
    }


}
