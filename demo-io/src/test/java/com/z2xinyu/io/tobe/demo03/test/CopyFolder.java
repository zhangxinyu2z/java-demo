package com.z2xinyu.io.tobe.demo03.test;

import java.io.*;

/**
 * 拷贝指定目录下的文件
 * 
 * @author zhang xinyu
 * @date 2021-04-13_19:10:03
 */
public class CopyFolder {

    public static void main(String[] args) throws IOException {
        File srcFile = new File("D:\\develop\\projects\\eclipse-workspace\\day02_process_control");
        File destFile = new File("C:\\Users\\zhang xinyu\\desktop\\test");
        copyFolder(srcFile, destFile);
    }

    /**
     * 遍历要拷贝的路径，判断内部是否还有文件夹
     * 
     * @param srcFile
     * @param destFile
     * @throws IOException
     */
    private static void copyFolder(File srcFile, File destFile) throws IOException {
        if (!srcFile.exists()) {
            throw new IOException("没有该目录呀");
        }
        // 如果是文件夹，在dest下创建新的文件夹
        if (srcFile.isDirectory()) {
            File newFolder = new File(destFile, srcFile.getName());
            newFolder.mkdir();
            File[] files = srcFile.listFiles();
            for (File file : files) {
                copyFolder(file, newFolder);
            }
        } else { // 如果是文件，把文件拷贝到当前目录下
            File newFile = new File(destFile, srcFile.getName());
            copyFile(srcFile, newFile);
        }
    }

    /**
     * 拷贝文件
     * 
     * @param srcFile
     *            源文件的路径
     * @param newFile
     *            拷贝的路径
     * @throws IOException
     */
    private static void copyFile(File srcFile, File newFile) {
        /** jdk7新特性 流在try的作用域中，就不必在手动关闭流了 */
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(srcFile));
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(newFile));) {
            int len = 0;
            byte[] b = new byte[1024];
            while ((len = bis.read(b)) != -1) {
                bos.write(b, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*
        BufferedInputStream bis =
            new BufferedInputStream(new FileInputStream(srcFile));
        BufferedOutputStream bos =
            new BufferedOutputStream(new FileOutputStream(newFile));
        int len = 0;
        byte[] b = new byte[1024];
        while ((len = bis.read(b)) != -1) {
            bos.write(b, 0, len);
        }
        bos.close();
        bis.close();
        */
    }

}
