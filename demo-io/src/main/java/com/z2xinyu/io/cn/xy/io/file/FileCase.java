package com.z2xinyu.io.cn.xy.io.file;

import java.io.File;

// 递归的案例演示：
// 统一修改文件名称
// 删除指定文件
public class FileCase {
    public static void main(String[] args) {
        File file = new File("D:\\CodeDemo\\renameto");
//        modify(cn.xy.io.file);
        delete(file);
    }

    public static void delete(File file) {
        File[] files = file.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    delete(f);
                } else {
                    f.delete();
                }
            }
        }
    }

    public static void modify(File file) {
        File[] files = file.listFiles();
        for (File f : files) {
            if (f.isDirectory()) {
                modify(f);
            } else {
                String fileName = f.getName();
                int index = fileName.indexOf(" - ");
                if (index != -1) {
                    String prefix = fileName.substring(0, index);
//                    String suffix = fileName.substring(index + 1, fileName.length());
                    String suffix = fileName.substring(index + 3);

                    String newFileName = prefix.concat(suffix);

                    File newFile = new File(file, newFileName);

                    f.renameTo(newFile);
                }
            }
        }
    }
}
