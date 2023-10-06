package com.z2xinyu.io.cn.xy.io.file;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

/**
 * File对象是文件和目录路径名的抽象表示，该文件路径并不一定实际存在
 * <p>
 * 测试File对象的方法
 * <p>
 * 文件的递归
 * @author z-xy
 */
public class FileDemo {
    public static void main(String[] args) throws IOException {
        // 构建File对象
//        File f1 = new File(new File("demo"), "hello.txt");
//        File f2 = new File("demo\\1\\2\\3");
//        File f3 = new File("hello","world.txt");

        /*
        mkdir()只能创建单级目录，上级目录不存在返回false
        mkdirs()可创建多级目录，上级目录不存在自动创建
        tip:用这个两个方法创建的只能是目录
        */
//        f2.mkdir();
//        f2.mkdirs();
        /*
         * creatNewFile()
         * 上级目录不存在，IOException
         */
//        f1.createNewFile();

        /*
         * delete()
         * 只能删除单级目录或文件，且目录下有文件，删除目录会失败，false
         * 不走回收站
         */
//        System.out.println(f1.delete());

        File f4 = new File("C:\\Users\\zhang xinyu\\Desktop\\2\\1.txt");
        File f5 = new File("C:\\Users\\zhang xinyu\\Desktop\\2\\1\\2.txt");

        /**
         * renameTo()
         * 1、目标文件目录必须存在，文件不用
         * 2、同一级目录下，是修改文件名字;不是同一级目录，是修改名称加复制到指定目录
         */
        boolean b = f4.renameTo(f5);
        System.out.println(b);
// --------------------------------------------------------------------------------
//判断   //  isDirectory()
        // isFile()
        // exists()
        // canRead()
        // canWrite()
        // isHidden()
        File file = new File("demo\\1\\2.txt");
        /*System.out.println(cn.xy.io.file.isDirectory()); // false
        System.out.println(cn.xy.io.file.isFile()); // true
        System.out.println(cn.xy.io.file.exists());
        System.out.println(cn.xy.io.file.canRead());
        System.out.println(cn.xy.io.file.canWrite());
        System.out.println(cn.xy.io.file.isHidden()); // false*/

// --------------------------------------------------------------------------------
//获取   //  String getPath()
        //  String getName() 最后的名字
        //  String getAbsolutePath() long length() 字节个数
        //  long lastModified() 最后一次修改时间

        //  String[] list() 指定目录下的所有文件或目录的名称数组,指定路径不是目录返回null
        //  String[] list(FileNameFilter filter)
        //  File[] listFiles() 获取指定目录下的文件和目录表示的抽象路径File
        System.out.println("getPath:" + file.getPath()
                + "\ngetName():" + file.getName()
                + "\ngetAbsolutePath:" + file.getAbsolutePath()
                + "\ngetAbsoluteFile:" + file.getAbsoluteFile()
                + "\ngetParent:" + file.getParent()
                + "\nlength:" + file.length()
                + "\nlastModify:" + file.lastModified()
                + "\ncanonicalPath:" + file.getCanonicalPath());

        /**
         * 获取文件名以.jpg结尾的文件
         * 两种方式，建议第一种递归
         * 明白递归的出口是什么？
         * 这里就是文件，
         * 如果是目录，把目录封装成根目录继续往下，是文件就可以进行判断，输出
         */
        getFile(new File("D:\\develop\\Codes\\IDEA\\basic\\io\\src"));
        getFile2(new File("D:\\develop\\Codes\\IDEA\\basic\\io\\src"));
    }

    /**
     * 获取以.jpg结尾的文件
     *
     * @param file
     */
    public static void getFile(File file) {
        // 获取当前抽象路径下所有的File对象
        File[] files = file.listFiles();
        if (files == null) {
            System.out.println("该目录下没有.jpg结尾的文件");
        }
        for (File f : files) {
            // 如果该路径映射的是目录，获取该目录下的所有抽象路径
            if (f.isDirectory()) {
                getFile(f);
            } else {
                // 不是目录说明是文件，判读是否以为.jpg结尾
                if (f.getName().endsWith(".jpg")) {
                    System.out.println(f.getAbsolutePath());
                }
            }
        }
    }

    /**
     * 获取以.jpg结尾的文件
     *
     * @param file
     */
    public static void getFile2(File file) {
        // 使用文件过滤器
        File[] files = file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File newFile) {
                // 对抽象路径映射的路径下的每个文件对象，进行处理，仅一级目录
                // 如果是文件，且以.jpg结尾，不会添加到files数组中
//                if (newFile.isFile()) {
//                    if (newFile.getName().endsWith(".jpg")) {
//                        return true;
//                    }
//                }
//                return false;
                return newFile.isFile() && newFile.getName().endsWith(".jpg");
            }
        });
    }
}
