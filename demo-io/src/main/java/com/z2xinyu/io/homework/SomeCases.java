package com.z2xinyu.io.homework;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

/**
 * 把数据存储和读取到文件
 */
public class SomeCases {
    public static void main(String[] args) throws IOException {
        List<String> al = new ArrayList<>();
        al.add("周芷若：周海媚");
        al.add("张无忌");
        al.add("张三丰");

//        listToFile(al);
//        readFileToList(al);
        storeScore();
    }

    /**把文件中的文本读取到集合中*/
    private static void readFileToList(List<String> al) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("倚天屠龙记.txt"));
        String s = null;
        while((s = br.readLine()) != null) {
            al.add(s);
        }
        System.out.println(al);
        br.close();
    }

    /**
     * 把集合中的数据写入到文本中
     */
    public static void listToFile(List<String> list) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("倚天屠龙记.txt"));
        for (String s : list) {
            bw.write(s);
            bw.newLine();
            bw.flush();
        }
        bw.close();
    }

    /**键盘录入学生的成绩信息，存储到文件中,按照总分排序*/
    public static void storeScore() throws IOException {
        // 定义一个set集合存储学生信息，在set集合内部定义一个比较器进行判定
        TreeSet<Student> ts= new TreeSet<>(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                int num = o1.getScore() - o2.getScore();
                // 不允许重复元素也是通过compare方法限定的
                return num == 0 ? o1.getName().compareTo(o2.getName()) : num;
            }
        });

        /**录入学生的信息到集合中*/
        while(true) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("请输入学生姓名：" + '\n');
            String name = br.readLine();
            System.out.print("请输入学生的年龄" + '\n');
            int age = Integer.valueOf(br.readLine());
            System.out.print("请输入学生的成绩：" + '\n');
            int score = Integer.valueOf(br.readLine());
            Student s = new Student(name, age, score);
            ts.add(s);
            System.out.print("是否要继续录入：y/n？" +'\n');
            String r = br.readLine();
            if (r.equals("n")) {
                br.close();
                break;
            }
        }

        /**通过字符缓冲流，把集合中的学生信息存储到文件中*/
        BufferedWriter bw = new BufferedWriter(new FileWriter("学生信息.txt"));
        bw.write("学生信息如下：");
        bw.newLine();
        bw.write("| 姓名 | " + " | 年龄 | " + " | 成绩 |");
        bw.newLine();
        bw.flush();
        for (Student s : ts) {
            StringBuilder sb = new StringBuilder();
            sb.append(s.getName() + " " + s.getAge() + " " + s.getScore());
            bw.write(sb.toString());
            bw.newLine();
            bw.flush();
        }
        bw.close();
    }
}
