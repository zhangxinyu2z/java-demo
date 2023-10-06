package com.z2xinyu.io.homework;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;

/**
 * 文件的复制
 */
public class CopyFolders {
    public static void main(String[] args) throws IOException {
//        File src = new File("D:\\CodeDemo\\src");
        File src = new File("D:\\Workspace\\IdeaProjects\\io\\src\\homework");
        File dest = new File("D:\\CodeDemo\\dest");

        if (!dest.exists()) {
            dest.mkdir();
        }
//        copyMultiFiles(src, dest);
        copySingleFiles(src,dest);
    }

    /**
     * 复制单级目录下的文件到指定目录下，并修改其后缀名
     * @param src
     * @param dest
     * @throws IOException
     */
    public static void copySingleFiles(File src, File dest) throws IOException {
        /**
         * 可以用来判断单级目录下指定后缀名的文件
         * listFiles(filter)通过list()方法只能获得当前目录下的文件名（name），无法获取次级目录
         * name是目录，当然无法以.java结尾，false就不会添加到list中
         */
        File[] files = src.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return new File(dir,name).isFile() && name.endsWith(".java");
            }
        });

        for (File file :files) {
            // 复制file文件到dest录下
            File newFile = new File(dest, file.getName());
            copyFile(file,newFile);
        }

        // 修改后缀名
        File[] modifyFiles = dest.listFiles();
        for(File file : modifyFiles) {
            String name = file.getName().replace(".java",".jad");
            file.renameTo(new File(dest, name));
        }
    }

    /**
     * 复制单级目录下的所有文件到指定位置
     * @param src
     * @param dest
     * @throws IOException
     */
    public static void copyMultiFiles(File src, File dest) throws IOException {
        // src如果是目录，用src构建新的目录对象
        if (src.isDirectory()) {
            File newFolder = new File(dest, src.getName());
            newFolder.mkdir();
            // 得到src下的所有文件夹对象
            File[] files = src.listFiles();
            for (File file : files) {
                // 对于files中的所有文件（包括文件夹），依次进行判断是否是folder,再构建新的folder
                copyMultiFiles(file, newFolder);
            }
        } else { // 如果是文件：构建新的File对象，复制到对应的目录下
            File newFile = new File(dest, src.getName());
            copyFile(src, newFile);
        }
    }

    /**
     * 复制文件
     */
    private static void copyFile(File src, File newFile) throws IOException {
        // 目标文件对象
        BufferedInputStream bis =
                new BufferedInputStream(new FileInputStream(src));
        // 创建并复制文件和内容
        BufferedOutputStream bos =
                new BufferedOutputStream(new FileOutputStream(newFile));

        /**
         * 使用reader操作，出现了乱码问题
         */
        byte[] bytes = new byte[1024];
        int len = 0;
        while((len = bis.read(bytes)) != -1) {
            bos.write(bytes,0,len);
        }
        bos.close();
        bis.close();
    }
}
