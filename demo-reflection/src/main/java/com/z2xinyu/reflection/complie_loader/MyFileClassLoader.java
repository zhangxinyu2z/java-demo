package com.z2xinyu.reflection.complie_loader;

import java.io.*;

/**
 * class对象加载类 可根据MyFileClassLoader 从文件中动态生成类
 * 
 * @author zhang xinyu
 * @date 2021-04-27 14:03:09
 * @version v1.0
 */
public class MyFileClassLoader extends ClassLoader {

    private String classPath;

    public String getClassPath() {
        return classPath;
    }

    public void setClassPath(String classPath) {
        this.classPath = classPath;
    }

    @Override
    public Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        return super.loadClass(name, resolve);
    }

    /**
     * 根据类名字符串从指定的目录查找类，并返回类对象
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classData = null;
        try {
            classData = loadClassData(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.defineClass(name, classData, 0, classData.length);
    }

    /**
     * 根据类名字符串加载类 byte 数据流
     * 
     * @param name
     *            类名字符串 例如： com.cmw.entity.SysEntity
     * @return 返回类文件 byte 流数据
     * @throws IOException
     */
    private byte[] loadClassData(String name) throws IOException {

        File file = getFile(name);
        @SuppressWarnings("resource")
        FileInputStream fis = new FileInputStream(file);
        byte[] arrData = new byte[(int)file.length()];
        fis.read(arrData);
        return arrData;
    }

    /**
     * 根据类名字符串返回一个 File 对象
     * 
     * @param name
     *            类名字符串
     * @return File 对象
     * @throws FileNotFoundException
     */
    private File getFile(String name) throws FileNotFoundException {
        File dir = new File(classPath);
        if (!dir.exists())
            throw new FileNotFoundException(classPath + " 目录不存在！");
        String _classPath = classPath.replaceAll("[\\\\]", "/");
        int offset = _classPath.lastIndexOf("/");
        name = name.replaceAll("[.]", "/");
        if (offset != -1 && offset < _classPath.length() - 1) {
            _classPath += "/";
        }
        _classPath += name + ".class";
        System.out.println(_classPath);
        dir = new File(_classPath);
        if (!dir.exists())
            throw new FileNotFoundException(dir + " 不存在！");
        return dir;

    }

}
