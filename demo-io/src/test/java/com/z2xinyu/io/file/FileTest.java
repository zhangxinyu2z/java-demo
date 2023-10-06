package com.z2xinyu.io.file;

import org.junit.Test;

import java.io.*;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author zhangxinyu
 * @date 2023/8/28
 **/
public class FileTest {

    @Test
    public void testConstructor() {
        // 表示d盘下的一个test文件夹
        File f1 = new File("d:\\test");
        // 根据方法的不同，h1.txt也可能是一个文件夹
        File f2 = new File("d:\\test\\h1.txt");
        // 表示d:\\test\\hello    hello可以是一个文件夹，也可以是一个文件
        File f3 = new File("d:\\test", "h1");
    }

    @Test
    public void testCreate() throws IOException {
        String currentDirectory = System.getProperty("user.dir");
        System.out.println("当前工作目录的路径：" + currentDirectory);
        String classPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        System.out.println("当前类所在的目录的路径：" + classPath);

        File f1 = new File("d:\\f1");
        boolean mkdir = f1.mkdir();

        // 1、在已经存在文件的情况下，不会做任何操作
        File f2 = new File("d:\\f1\\h1.txt");
        // 2、创建文件或者文件夹必须有先后 文件夹->文件 >> 没有办法在不创建文件夹的情况下，直接创建文件，否则   >> 抛出io异常，系统找不到指定路径
        boolean newFile = f2.createNewFile();

        // 当同一级目录下已经存在某文件或目录，再想创建同名的文件或目录，使用mkdir createNewFile mkdirs都无法实现
        boolean mkdir1 = f2.mkdir();
        boolean mkdirs = f2.mkdirs();

        // 默认相对于当前项目目录下
        File f4 = new File("f2.txt");
        boolean newFile1 = f4.createNewFile();
        File f5 = new File("aaa/bbb/ccc");
        boolean mkdirs1 = f5.mkdirs();

    }

    /**
     * 在testCreate的基础上测试删除
     *
     * 删除必须一层一层执行，不存在直接删除目录和目录下的文件或多级目录删除
     */
    @Test
    public void testDelete() {
        File f1 = new File("d:\\f1\\h1.txt");
        boolean delete = f1.delete();

        File f2 = new File("aaa/bbb/ccc");
        boolean delete1 = f2.delete();
    }

    /**
     * 在同一目录下，是重命名
     * 在不同目录下，是剪切
     */
    @Test
    public void testRename() {
        File f1 = new File("d:\\f1\\h1.txt");
        File f2 = new File("h-rename.txt");
        boolean b = f1.renameTo(f2);
    }

    @Test
    public void testIterator() {
        File f = new File("d:\\");
        // 获取一级目录
        // Arrays.stream(Objects.requireNonNull(f.list())).forEach(System.out::println);

        // Arrays.stream(Objects.requireNonNull(f.listFiles())).map(File::getName).forEach(System.out::println);

        File[] files = f.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isFile() && file.getName().endsWith(".properties");
            }
        });

        Arrays.stream(Objects.requireNonNull(files)).map(File::getAbsolutePath).forEach(System.out::println);
    }
}
