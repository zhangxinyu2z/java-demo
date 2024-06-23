package com.z2xinyu.io.file;

import org.junit.Test;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

/**
 * File(String name)
 * File(String parent, String child)
 * createNewFile()
 * mkdir()
 * mkdirs()
 * delete():只能一层一层删
 *
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
        // 根据方法的不同，a.txt也可能是一个文件夹，比如mkDir或mkDirs
        File f2 = new File("d:\\f1\\a.txt");
        // 表示d:\\test\\hello hello可以是一个文件夹，也可以是一个文件
        File f3 = new File("d:\\f1", "b.txt");
        boolean mkdir = f1.mkdir();

        // 1、createNewFile在已经存在文件的情况下，不会做任何操作
        // 2、创建文件或者文件夹必须有先后 文件夹->文件 >> 没有办法在不创建文件夹的情况下，直接创建文件，否则   >> 抛出io异常，系统找不到指定路径
        // 3、使用createNewFile就是创建一个没有定义格式的文件
        boolean newFile = f2.createNewFile();

        //         mkdir()只能创建单级目录，上级目录不存在返回false
        //         mkdirs()可创建多级目录，上级目录不存在自动创建
        // 注意：当同一级目录下已经存在某文件或目录，再想创建同名的文件或目录，使用mkdir createNewFile mkdirs都无法实现
        // false
        boolean mkdir1 = f2.mkdir();
        // false
        boolean mkdirs = f2.mkdirs();

        // 默认相对于当前项目目录
        File f4 = new File("c.txt");
        boolean newFile1 = f4.createNewFile();
        File f5 = new File("aaa/bbb/ccc");
        boolean mkdirs1 = f5.mkdirs();

    }

    /**
     * 在testCreate的基础上测试删除
     * <p>
     * 只能删除单级目录或文件，如果目录下有文件或目录，直接删除目录会失败，false
     */
    @Test
    public void testDelete() {
        File f1 = new File("d:\\f1\\a.txt");
        boolean delete = f1.delete();
        File file = new File("d:\\f1");
        boolean delete2 = file.delete();
        // 删除ccc目录
        File f2 = new File("aaa/bbb/ccc");
        boolean delete1 = f2.delete();

        // 递归删除
        deleteRecursive(f2);
    }

    public void deleteRecursive(File file) {
        File[] files = file.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    deleteRecursive(f);
                } else {
                    f.delete();
                }
            }
        }
    }

    /**
     * 在同一目录下，是重命名
     * 在不同目录下，是剪切
     */
    @Test
    public void testRename() {
        File f1 = new File("d:\\f1\\a.txt");
        File f2 = new File("b.txt");
        File f3 = new File("d:\\f1\\c.txt");
        // 剪切
        boolean b = f1.renameTo(f2);
        // 重命名
        boolean b1 = f1.renameTo(f3);

        // 统一修改文件名称
        renameRecursive(f1);
    }

    public static void renameRecursive(File file) {
        File[] files = file.listFiles();
        for (File f : files) {
            if (f.isDirectory()) {
                renameRecursive(f);
            } else {
                String fileName = f.getName();
                int index = fileName.indexOf(" - ");
                if (index != -1) {
                    String prefix = fileName.substring(0, index);
                    // String suffix = fileName.substring(index + 1, fileName.length());
                    String suffix = fileName.substring(index + 3);

                    String newFileName = prefix.concat(suffix);

                    File newFile = new File(file, newFileName);

                    f.renameTo(newFile);
                }
            }
        }
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

    @Test
    public void testVerify() {
        File file = new File("d:\\c-data");
        boolean directory = file.isDirectory();
        boolean file1 = file.isFile();
        boolean exists = file.exists();
        boolean b = file.canRead();
        boolean b1 = file.canWrite();
        boolean hidden = file.isHidden();
    }

    /**
     * String getPath()
     * String getName() 最后的名字
     * String getAbsolutePath() long length() 字节个数
     * long lastModified() 最后一次修改时间
     * <p>
     * String[] list() 指定目录下的所有文件或目录的名称数组,指定路径不是目录返回null
     * String[] list(FileNameFilter filter)
     * File[] listFiles() 获取指定目录下的文件和目录表示的抽象路径File
     */
    @Test
    public void testGet() throws IOException {
        File file = new File("d:\\c-data\\1.txt");
        System.out.println("getPath:" + file.getPath()
            + "\ngetName():" + file.getName()
            + "\ngetAbsolutePath:" + file.getAbsolutePath()
            + "\ngetAbsoluteFile:" + file.getAbsoluteFile()
            + "\ngetParent:" + file.getParent()
            + "\nlength:" + file.length()
            + "\nlastModify:" + file.lastModified()
            + "\ncanonicalPath:" + file.getCanonicalPath());

        getFile(new File("D:\\develop\\Codes\\IDEA\\basic\\io\\src"));
        getFile2(new File("D:\\develop\\Codes\\IDEA\\basic\\io\\src"));
    }

    /**
     * 获取以.jpg结尾的文件
     *
     * @param file
     */
    public void getFile(File file) {
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
    public void getFile2(File file) {
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
