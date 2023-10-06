package com.z2xinyu.io.tobe.demo01.Reader;

import java.io.*;

/**
 * 使用字符流进行文件的拷贝
 * 
 * @author zhang xinyu
 *
 */
public class Demo03CopyFile {

    public static void main(String[] args) throws IOException {
        String srcPath = "泰山吟.txt";
        String destPath = "C:\\Users\\zhang xinyu\\desktop\\泰山吟.txt";
        // copyFile(srcPath, destPath);
        // copyFileByBuffer(srcPath, destPath);

        testEncode(srcPath, destPath);
        // 因为我eclipse设置的默认编码是utf-8,所以默认使用utf-8的编码来解析
        readFromDestPath(destPath);

    }

    /**
     * 测试读取gbk编码的文件
     * 
     * @param destPath
     * @throws IOException
     */
    private static void readFromDestPath(String destPath) throws IOException {
        // 默认用的utf-8编码，因为我的eclipse默认是utf-8
        // FileReader fr = new FileReader(destPath);
        // 切换成gbk编码来解析
        InputStreamReader isr =
            new InputStreamReader(new FileInputStream(destPath), "gbk");
        int len = 0;
        char[] ch = new char[1024];
        while ((len = isr.read(ch)) != -1) {
            System.out.println(new String(ch, 0, len));
        }
        isr.close();
    }

    /**
     * 测试下编码问题
     */
    private static void testEncode(String srcPath, String destPath)
        throws IOException {
        BufferedReader br = new BufferedReader(
            new InputStreamReader(new FileInputStream(srcPath), "utf-8"));
        // 使用gbk编码来存储文件
        BufferedWriter bw = new BufferedWriter(
            new OutputStreamWriter(new FileOutputStream(destPath), "gbk"));
        String line = null;
        while ((line = br.readLine()) != null) {
            bw.write(line);
            bw.newLine();
            bw.flush();
        }
        bw.close();
        br.close();
    }

    public static void copyFile(String srcPath, String destPath)
        throws IOException {
        FileReader fr = new FileReader(srcPath);
        FileWriter fw = new FileWriter(destPath);
        int len = 0;
        char[] ch = new char[1024];
        while ((len = fr.read(ch)) != -1) {
            fw.write(ch, 0, len);
        }

        fw.close();
        fr.close();
    }

    /**
     * 使用字符缓冲流来进行拷贝 readLine:一次读一行，无法读取到换行符 newLine:进行换行
     * 
     * @param srcPath
     *            源
     * @param destPath
     *            目的地
     */
    public static void copyFileByBuffer(String srcPath, String destPath) {
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            br = new BufferedReader(new FileReader(srcPath));
            bw = new BufferedWriter(new FileWriter(destPath));

            int len = 0;
            char[] ch = new char[1024];
            while ((len = br.read(ch)) != -1) {
                bw.write(ch, 0, len);
                bw.flush();
            }
            /*
            String line = null;
            while ((line = br.readLine()) != null) {
                bw.write(line);
                bw.newLine();
                bw.flush();
            }
            */
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
