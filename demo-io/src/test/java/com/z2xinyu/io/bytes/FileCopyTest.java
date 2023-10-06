package com.z2xinyu.io.bytes;

import java.io.*;

/**
 * @author zhangxinyu
 * @date 2023/8/29
 **/
public class FileCopyTest {

    public static void main(String[] args) {
        String srcPath = "C:\\Users\\zhang xinyu\\Pictures\\刘亦菲.jpg";
        String destPath = "刘亦菲.jpg";
        long start = System.currentTimeMillis();

        // copyFile1(srcPath, destPath); // 1096毫秒
        // copyFile2(srcPath, destPath); // 4毫秒
        // copyFile3(srcPath, destPath); // 33毫秒
        copyFile4(srcPath, destPath); // 3毫秒

        long end = System.currentTimeMillis();
        System.out.println("拷贝的时间是：" + (end - start) + "毫秒");
    }

    /**
     * 第一种方式，一个一个字节拷贝
     *
     * @param srcPath
     * @param descPath
     */
    public static void copyFile1(String srcPath, String descPath) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(srcPath);
            fos = new FileOutputStream(descPath);
            int b = 0;
            while ((b = fis.read()) != -1) {
                fos.write(b);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 拷贝图片
     *
     * @param src
     *            源文件位置
     * @param dest
     *            拷贝的位置
     */
    public static void copyFile2(String src, String dest) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(src);
            fos = new FileOutputStream(dest);
            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = fis.read(bytes)) != -1) {
                fos.write(bytes, 0, len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 使用字节包装流先一个一个字节读取到缓冲区中
     *
     * @param srcPath
     * @param destPath
     */
    public static void copyFile3(String srcPath, String destPath) {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(srcPath));
            bos = new BufferedOutputStream(new FileOutputStream(destPath));
            int b = 0;
            while ((b = bis.read()) != -1) {
                bos.write(b);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 使用字节包装流
     *
     * @param srcPath
     * @param destPath
     */
    public static void copyFile4(String srcPath, String destPath) {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(srcPath));
            bos = new BufferedOutputStream(new FileOutputStream(destPath));
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = bis.read(bytes)) != -1) {
                bos.write(bytes, 0, len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
