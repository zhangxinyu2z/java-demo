package com.z2xinyu.io.tobe.demo03.test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 把文件中的读取到数组中并输出
 * 
 * @author zhang xinyu
 * @date 2021-04-13_19:25:57
 */
public class ReadDataToList {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("泰山吟.txt"));
        List<String> al = new ArrayList<String>();
        String line = null;
        while ((line = br.readLine()) != null) {
            al.add(line);
        }
        br.close();

        for (String s : al) {
            System.out.println(s);
        }
    }
}
