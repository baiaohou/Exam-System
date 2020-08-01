package com.lagou.client;

import com.lagou.model.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author baiaohou
 * @create 2020-07-28 12:37 AM
 * 编程实现客户端的主界面绘制，和相应功能实现
 */
public class ClientView {

    /**
     * 为了可以使用输入输出流，采用合成符永原则实现
     */
    private ClientInitClose cic;

    /**
     * 通过构造方法实现成员变量的初始化
     * @param cic
     */
    public ClientView(ClientInitClose cic) {
        this.cic = cic;
    }

    /**
     * 客户端main page的绘制
     */
    public void clientMainPage() throws IOException, ClassNotFoundException {
        while (true) {
            System.out.println("  \n\n\t\t    在线考试系统");
            System.out.println("----------------------------------");
            System.out.print("   [1] 学员系统");
            System.out.println("     [2] 管理员系统");
            System.out.println("   [0] 退出系统");
            System.out.println("----------------------------------");
            System.out.println("请选择要进行的业务编号：");
//            Scanner sc = new Scanner(System.in);
//            int choose = sc.nextInt();
            int choose = ClientScanner.getScanner().nextInt();
            switch (choose) {
                case 1:
                    System.out.println("正在进入学员系统...");
                    clientStudentLogin();
                    break;
                case 2:
                    System.out.println("正在进入管理员系统...");
                    clientManagerLogin();
                    break;
                case 0:
                    System.out.println("正在退出系统...");
                    cic.getOos().writeObject(null);
                    return;
                default:
                    System.out.println("输入错误，请重新选择！");
            }
        }
    }

    /**
     * 实现客户端学员登录的功能
     */
    private void clientStudentLogin() throws IOException, ClassNotFoundException {
        // 1. prepare login info
        System.out.println("请输入学员账户信息：");
        String userName = ClientScanner.getScanner().next();
        System.out.println("请输入学员密码信息：");
        String password = ClientScanner.getScanner().next();
        UserMessage tum = new UserMessage("studentLoginCheck", new User(userName, password));
        // 2. 将UserMessage对象通过对象输出流发送给服务器
        cic.getOos().writeObject(tum);
        System.out.println("客户端发送学员账户信息成功！");
        // 3.接收服务器的处理结果，给出提示
        tum = (UserMessage) cic.getOis().readObject();
        if ("success".equals(tum.getType())) {
            System.out.println("登陆成功，欢迎使用！");
            // goto student main page
            studentMainPage();
        } else {
            System.out.println("用户名或密码错误！");
        }
    }




    /**
     * 实现客户端管理员登录的功能
     */
    private void clientManagerLogin() throws IOException, ClassNotFoundException {
        // 1. prepare admin login info
        System.out.println("请输入管理员账户信息：");
        String userName = ClientScanner.getScanner().next();
        System.out.println("请输入管理员密码信息：");
        String password = ClientScanner.getScanner().next();
        UserMessage tum = new UserMessage("managerCheck", new User(userName, password));
        // 2. 将UserMessage对象通过对象输出流发送给服务器
        cic.getOos().writeObject(tum);
        System.out.println("客户端发送管理员账户信息成功！");
        // 3.接收服务器的处理结果，给出提示
        tum = (UserMessage) cic.getOis().readObject();
        if ("success".equals(tum.getType())) {
            System.out.println("登陆成功，欢迎使用！");
            // goto admin main page
            adminMainPage();
        } else {
            System.out.println("用户名或密码错误！");
        }
    }


    /**
     * 实现客户端学员选择界面
     */
    private void studentMainPage() throws IOException, ClassNotFoundException {
        while (true) {
            System.out.println("  \n\t\t  欢迎使用学员系统");
            System.out.println("----------------------------------");
            System.out.print("   [1] 修改密码");
            System.out.println("     [2] 考  试");
            System.out.println("   [0] 退出登录");
            System.out.println("----------------------------------");
            System.out.println("请选择要进行的业务编号：");
            int choose = ClientScanner.getScanner().nextInt();
            switch (choose) {
                case 1:
                    System.out.println("正在进入用户模块...");
                    changePasswordEntry();
                    break;
                case 2:
                    System.out.println("正在进入考试模块...");
                    examinationEntry();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("输入错误，请重新选择！");
            }
        }
    }



    /**
     * Student Mode [1] - change password
     */
    private void changePasswordEntry() throws IOException, ClassNotFoundException {
        while (true) {
            System.out.println("请输入新密码：");
            String s1 = ClientScanner.getScanner().next();
            System.out.println("请再次输入新密码：");
            String s2 = ClientScanner.getScanner().next();
            if (!s1.equals(s2)) {
                System.out.println("两次密码输入不一致！请重试！");
                continue;
            }
            UserMessage tum = new UserMessage("studentChangePassword", new User(null, s1));
            // 2. 将UserMessage对象通过对象输出流发送给服务器
            cic.getOos().writeObject(tum);
            System.out.println("客户端发送学员账户新密码成功！");
            // 3.接收服务器的处理结果，给出提示
            tum = (UserMessage) cic.getOis().readObject();
            if ("success".equals(tum.getType())) {
                System.out.println("密码修改成功！");
                // goto student main page
                studentMainPage();
            } else {
                System.out.println("密码修改失败！");
            }
            break;
        }
    }

    /**
     * Student Mode [2] - test
     */
    private void examinationEntry() throws IOException, ClassNotFoundException {
        label : while (true) {
            System.out.println("  \n\t\t   学员考试系统");
            System.out.println("----------------------------------");
            System.out.print("   [1] 开始考试");
            System.out.println("     [2] 导出成绩");
            System.out.print("   [3] 查询成绩");
            System.out.println("     [0] 返回上一级");
            System.out.println("----------------------------------");
            System.out.println("请选择要进行的业务编号：");
            int choose = ClientScanner.getScanner().nextInt();
            switch (choose) {
                case 1:
                    System.out.println("正在进入考试...");
                    Date d = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String time = sdf.format(d);

                    int result = startExamEntry();
                    System.out.println("分数为：\t" + result + "/100");
                    System.out.println("考试时间为：" + time);
                    cic.getOos().writeObject(time);
                    cic.getOos().writeObject((Integer) result);

                    continue label;
                case 2:
                    outputExamEntry();
                    continue label;
                case 3:
                    System.out.println("正在查询成绩...");
                    System.out.println(searchExamEntry());
                case 0:
                    return;
                default:
                    System.out.println("输入错误，请重新选择！");
            }
        }
    }



    private int startExamEntry() throws IOException, ClassNotFoundException {
        cic.getOos().writeObject("startExam");
        List<Question> questions = (List<Question>) cic.getOis().readObject();
        int correct = 0;
        int total = questions.size();
        for (int i = 1; i <= total ; i++) {
            System.out.print("第" + i + "题：" + questions.get(i - 1).toStringStudentView());
            if (ClientScanner.getScanner().next().equalsIgnoreCase(questions.get(i - 1).getAnswer())) {
                correct++;
            }
        }
        System.out.println("############################");
        System.out.println();
        System.out.println("考试结束！");
        System.out.println("本次考试共" + total + "题，你答对了" + correct + "题。");
        int result = (int) (((double) correct / total) * 100);

        return result;
    }

    public String searchExamEntry() throws IOException, ClassNotFoundException {
        cic.getOos().writeObject("searchExam");
        HashMap<String, Integer> map = (HashMap<String, Integer>) cic.getOis().readObject();
        String str = "";
        str += "----- Date & Time -----  --- Score ---" + "\n";
        for (String s : map.keySet()) {
            str += "  " + s + "         " + map.get(s) + "\n";
        }
        return str;
    }

    private void outputExamEntry() throws IOException, ClassNotFoundException {
        String s = searchExamEntry();
        System.out.println("请输入要导出的路径：");
        String path = ClientScanner.getScanner().next();
        FileWriter fw = new FileWriter(path);
        fw.write(s);
        fw.close();
        System.out.println("写入成功！");
    }


    /**
     * 实现客户端管理员选择界面
     */
    private void adminMainPage() throws IOException, ClassNotFoundException {
        while (true) {
            System.out.println("  \n\t\t 欢迎使用管理员系统");
            System.out.println("----------------------------------");
            System.out.print("   [1] 学员管理");
            System.out.println("     [2] 考题管理");
            System.out.println("   [0] 返回上一级");
            System.out.println("----------------------------------");
            System.out.println("请选择要进行的业务编号：");
//            Scanner sc = new Scanner(System.in);
//            int choose = sc.nextInt();
            int choose = ClientScanner.getScanner().nextInt();
            switch (choose) {
                case 1:
                    System.out.println("正在进入学员管理模块...");
                    studentDBEntry();
                    break;
                case 2:
                    System.out.println("正在进入考题管理模块...");
                    questionDBEntry();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("输入错误，请重新选择！");
            }
        }
    }


    /**
     * When user hit [1]: 进入学员管理模块界面
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void studentDBEntry() throws IOException, ClassNotFoundException {
        while (true) {
            System.out.println("  \n\t\t   学员管理模块");
            System.out.println("----------------------------------");
            System.out.print("   [1] 添加学员");
            System.out.println("     [2] 删除学员");
            System.out.print("   [3] 修改学员");
            System.out.println("     [4] 搜索学员");
            System.out.print("   [5] 遍历学员");
            System.out.println("     [0] 返回上一级");
            System.out.println("----------------------------------");
            System.out.println("请选择要进行的业务编号：");
//            Scanner sc = new Scanner(System.in);
//            int choose = sc.nextInt();
            int choose = ClientScanner.getScanner().nextInt();
            switch (choose) {
                case 1:
                    // 1. prepare student info
                    System.out.println("下面采集需要添加的学生信息：");
                    StudentMessage tsm1 = getStudentFromUser("studentAdd");
                    // 2. 将StudentMessage对象通过对象输出流发送给服务器
                    cic.getOos().writeObject(tsm1);
                    System.out.println("客户端发送学生信息成功！");

                    // 3.接收服务器的处理结果，给出提示
                    tsm1 = (StudentMessage) cic.getOis().readObject();
                    if ("success".equals(tsm1.getType())) {
                        System.out.println("添加成功！");
                        break;
                    } else {
                        System.out.println("添加失败！");
                    }
                    break;
                case 2:
                    // 1. prepare student info
                    System.out.println("下面采集需要删除的学生信息：");
                    StudentMessage tsm2 = getStudentFromUser("studentDelete");
                    // 2. 将StudentMessage对象通过对象输出流发送给服务器
                    cic.getOos().writeObject(tsm2);
                    System.out.println("客户端发送学生信息成功！");

                    // 3.接收服务器的处理结果，给出提示
                    tsm2 = (StudentMessage) cic.getOis().readObject();
                    if ("success".equals(tsm2.getType())) {
                        System.out.println("删除成功！");
                        break;
                    } else {
                        System.out.println("学生不存在于数据库中，删除失败！");
                    }
                    break;
                case 3:
                    // 1. prepare student info
                    System.out.println("下面采集修改前的学生信息：");
                    StudentMessage tsm3 = getStudentFromUser("studentModify");
                    // 2. 将StudentMessage对象通过对象输出流发送给服务器
                    cic.getOos().writeObject(tsm3);
                    System.out.println("客户端发送学生信息成功！");

                    // 3.接收服务器的处理结果，给出提示
                    tsm3 = (StudentMessage) cic.getOis().readObject();
                    if ("success".equals(tsm3.getType())) {
                        System.out.println("下面采集修改后的学生信息：");
                        tsm3 = getStudentFromUser("studentModify");
                        // 4. 将StudentMessage对象通过对象输出流发送给服务器
                        cic.getOos().writeObject(tsm3);
                        System.out.println("客户端发送学生信息成功！");
                        tsm3 = (StudentMessage) cic.getOis().readObject();

                        if ("success".equals(tsm3.getType())) {
                            System.out.println("修改成功！");
                            break;
                        } else {
                            System.out.println("此学生不存在于数据库中！修改失败！");
                        }
                        break;
                    } else {
                        System.out.println("此学生不存在于数据库中！修改失败！");
                    }
                    break;
                case 4:
                    // 1. prepare student info
                    System.out.println("下面采集需要查询的学生信息：");
                    StudentMessage tsm4 = getStudentFromUser("studentSearch");
                    // 2. 将StudentMessage对象通过对象输出流发送给服务器
                    cic.getOos().writeObject(tsm4);
                    System.out.println("客户端发送学生信息成功！");

                    // 3.接收服务器的处理结果，给出提示
                    tsm4 = (StudentMessage) cic.getOis().readObject();
                    if ("success".equals(tsm4.getType())) {
                        System.out.println("此学生存在于数据库中！");
                        break;
                    } else {
                        System.out.println("此学生不存在于数据库中！");
                    }
                    break;

                case 5:
                    // 1. prepare student info
                    StudentMessage tsm5 = new StudentMessage("studentIterate", null);

                    // 2. 将StudentMessage对象通过对象输出流发送给服务器
                    cic.getOos().writeObject(tsm5);
                    System.out.println("客户端请求遍历学生数据库成功！");

                    // 3.接收服务器返回的iterate结果
                    tsm5 = (StudentMessage) cic.getOis().readObject();
                    System.out.println(tsm5.getType());
                    break;
                case 0:
                    return;
                default:
                    System.out.println("输入错误，请重新选择！");
            }
        }
    }

    private StudentMessage getStudentFromUser(String type) {
        // 1. prepare student info
        System.out.println("请输入学员的学号：");
        String id = ClientScanner.getScanner().next();
        System.out.println("请输入学员的姓名：");
        String name = ClientScanner.getScanner().next();
        System.out.println("请输入学员的年龄：");
        int age = ClientScanner.getScanner().nextInt();
        System.out.println("请输入学员用户名：");
        String username = ClientScanner.getScanner().next();
        System.out.println("请输入学员的密码：");
        String password = ClientScanner.getScanner().next();
        return new StudentMessage(type, new Student(id, name, age, username, password));
    }


    /**
     * When user hit [2]: 进入考试管理模块界面
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void questionDBEntry() throws IOException, ClassNotFoundException {
        while (true) {
            System.out.println("  \n\t\t   考试管理模块");
            System.out.println("----------------------------------");
            System.out.print("   [1] 添加考题");
            System.out.println("     [2] 删除考题");
            System.out.print("   [3] 修改考题");
            System.out.println("     [4] 搜索考题");
            System.out.print("   [5] 遍历考题");
            System.out.println("     [6] 导入考题");
            System.out.println("   [0] 返回上一级");
            System.out.println("----------------------------------");
            System.out.println("请选择要进行的业务编号：");
            int choose = ClientScanner.getScanner().nextInt();
            switch (choose) {
                case 1:
                    // 1. prepare question info
                    System.out.println("下面采集需要添加的考题信息：");
                    QuestionMessage tsm1 = getQuestionFromUser("questionAdd");
                    // 2. 将StudentMessage对象通过对象输出流发送给服务器
                    cic.getOos().writeObject(tsm1);
                    System.out.println("客户端发送考题信息成功！");

                    // 3.接收服务器的处理结果，给出提示
                    tsm1 = (QuestionMessage) cic.getOis().readObject();
                    if ("success".equals(tsm1.getType())) {
                        System.out.println("添加成功！");
                        break;
                    } else {
                        System.out.println("添加失败！");
                    }
                    break;
                case 2:
                    // 1. prepare student info
                    System.out.println("下面采集需要删除的考题信息：");
                    QuestionMessage tsm2 = getQuestionFromUser("questionDelete");
                    // 2. 将StudentMessage对象通过对象输出流发送给服务器
                    cic.getOos().writeObject(tsm2);
                    System.out.println("客户端发送考题信息成功！");

                    // 3.接收服务器的处理结果，给出提示
                    tsm2 = (QuestionMessage) cic.getOis().readObject();
                    if ("success".equals(tsm2.getType())) {
                        System.out.println("删除成功！");
                        break;
                    } else {
                        System.out.println("考题不存在于数据库中，删除失败！");
                    }
                    break;
                case 3:
                    // 1. prepare student info
                    System.out.println("下面采集修改前的考题信息：");
                    QuestionMessage tsm3 = getQuestionFromUser("questionModify");
                    // 2. 将StudentMessage对象通过对象输出流发送给服务器
                    cic.getOos().writeObject(tsm3);
                    System.out.println("客户端发送考题信息成功！");

                    // 3.接收服务器的处理结果，给出提示
                    tsm3 = (QuestionMessage) cic.getOis().readObject();
                    if ("success".equals(tsm3.getType())) {
                        System.out.println("下面采集修改后的考题信息：");
                        tsm3 = getQuestionFromUser("questionModify");
                        // 4. 将StudentMessage对象通过对象输出流发送给服务器
                        cic.getOos().writeObject(tsm3);
                        System.out.println("客户端发送考题信息成功！");
                        tsm3 = (QuestionMessage) cic.getOis().readObject();

                        if ("success".equals(tsm3.getType())) {
                            System.out.println("修改成功！");
                            break;
                        } else {
                            System.out.println("此考题不存在于数据库中！修改失败！");
                        }
                        break;
                    } else {
                        System.out.println("此考题不存在于数据库中！修改失败！");
                    }
                    break;
                case 4:
                    // 1. prepare student info
                    System.out.println("下面采集需要查询的考题信息：");
                    QuestionMessage tsm4 = getQuestionFromUser("questionSearch");
                    // 2. 将StudentMessage对象通过对象输出流发送给服务器
                    cic.getOos().writeObject(tsm4);
                    System.out.println("客户端发送考题信息成功！");

                    // 3.接收服务器的处理结果，给出提示
                    tsm4 = (QuestionMessage) cic.getOis().readObject();
                    if ("success".equals(tsm4.getType())) {
                        System.out.println("此考题存在于数据库中！");
                        break;
                    } else {
                        System.out.println("此考题不存在于数据库中！");
                    }
                    break;

                case 5:
                    // 1. prepare student info
                    QuestionMessage tsm5 = new QuestionMessage("questionIterate", null);

                    // 2. 将StudentMessage对象通过对象输出流发送给服务器
                    cic.getOos().writeObject(tsm5);
                    System.out.println("客户端请求遍历考题数据库成功！");

                    // 3.接收服务器返回的iterate结果
                    tsm5 = (QuestionMessage) cic.getOis().readObject();
                    System.out.println(tsm5.getType());
                    break;
                case 6:
                    // 导入考题
                    System.out.println("请输入要导入考题的文件路径：");
                    String path = ClientScanner.getScanner().next();
                    File file = new File(path);
                    if (!file.exists()) {
                        System.out.println("该路径不存在，请重试！");
                        break;
                    }
                    QuestionMessage tsm6 = new QuestionMessage("questionInput", null);
                    cic.getOos().writeObject(tsm6);
                    //
                    System.out.println("Oh yeah");
                    tsm6 = (QuestionMessage) cic.getOis().readObject();
                    //
                    tsm6 = new QuestionMessage(path, null);

                    cic.getOos().writeObject(tsm6);
                    //
                    // 3.接收服务器的处理结果，给出提示
                    tsm6 = (QuestionMessage) cic.getOis().readObject();
                    if ("success".equals(tsm6.getType())) {
                        System.out.println("导入成功！");
                        break;
                    } else {
                        System.out.println("导入失败！");
                    }
                    break;


                case 0:
                    return;
                default:
                    System.out.println("输入错误，请重新选择！");
            }
        }

    }

    private QuestionMessage getQuestionFromUser(String type) {
        // 1. prepare question info

        System.out.println("请输入考题的题号：");
        String id = ClientScanner.getScanner().next();


        System.out.println("请输入考题的题干：");
        String header = ClientScanner.getScanner().next();


        System.out.println("请输入考题的A选项：");
        String choiceA = ClientScanner.getScanner().next();

        System.out.println("请输入考题的B选项：");
        String choiceB = ClientScanner.getScanner().next();

        System.out.println("请输入考题的C选项：");
        String choiceC = ClientScanner.getScanner().next();

        System.out.println("请输入考题的D选项：");
        String choiceD = ClientScanner.getScanner().next();

        System.out.println("请输入考题的答案选项：");
        String answer = ClientScanner.getScanner().next();

        return new QuestionMessage(type, new Question(id, header, choiceA, choiceB, choiceC, choiceD, answer));
    }

}
