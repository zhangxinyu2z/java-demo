package com.z2xinyu.io.tobe.demo03.test;

import java.io.*;
import java.util.*;

public class StoreDataToFile {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        TreeSet<Student> ts = new TreeSet<Student>(new Comparator<Student>() {

            @Override
            public int compare(Student o1, Student o2) {
                int totalScoreNum = o2.getTotalScore() - o1.getTotalScore();
                int chineseScoreNum = totalScoreNum == 0 ? o2.getChineseScore() - o1.getChineseScore() : totalScoreNum;
                int mathScoreNum = chineseScoreNum == 0 ? o2.getMathScore() - o1.getMathScore() : chineseScoreNum;
                int englishNum = mathScoreNum == 0 ? o2.getEnglishScore() - o1.getEnglishScore() : mathScoreNum;
                return englishNum == 0 ? o1.getName().compareTo(o2.getName()) : englishNum;
            }
        });
        for (int i = 0; i < 3; i++) {
            System.out.println("input student name:");
            String name = sc.nextLine();
            System.out.println("input student math score");
            String mathScore = sc.nextLine();
            System.out.println("input student chinese score");
            String chineseScore = sc.nextLine();
            System.out.println("input student english score");
            String englishScore = sc.nextLine();
            Student s = new Student();
            s.setName(name);
            s.setMathScore(Integer.parseInt(mathScore));
            s.setChineseScore(Integer.parseInt(chineseScore));
            s.setEnglishScore(Integer.parseInt(englishScore));

            ts.add(s);
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter("学生信息.txt"));
        bw.write("学生信息如下");
        bw.newLine();
        bw.write("姓名\t语文\t数学\t英语");
        bw.newLine();
        for (Student s : ts) {
            StringBuilder sb = new StringBuilder();
            sb.append(s.getName()).append(" ").append(s.getChineseScore()).append(" ").append(s.getMathScore())
                .append(" ").append(s.getEnglishScore());
            bw.write(sb.toString());
            bw.newLine();
        }
        bw.close();
    }

}
