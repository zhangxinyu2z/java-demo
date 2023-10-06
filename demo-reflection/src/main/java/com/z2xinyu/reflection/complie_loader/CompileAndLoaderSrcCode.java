package com.z2xinyu.reflection.complie_loader;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * java编译api
 * https://blog.csdn.net/u010398771/article/details/90474813
 * 
 * @author zhang xinyu
 * @date 2021-04-27 11:23:29
 * @version v1.0
 */
public class CompileAndLoaderSrcCode {
    public static void main(String[] args)
        throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException,
        IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        Object obj = work();


    }

    public static Object work()
        throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException,
        IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        // FIRST
        String file = ".\\src\\com\\dhjy\\Test.java";
        // 把.java编译成字节码文件,注意：编译的.class文件在当前目录,classpath下没有
        /*** 使用JavaCompiler对象需要安装JDK，只有JAR无法使用 **/
        /*
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileMgr = compiler.getStandardFileManager(null, null, null);
        Iterable units = fileMgr.getJavaFileObjects(file);
        JavaCompiler.CompilationTask t = compiler.getTask(null, fileMgr, null, null, null, units);
        t.call();
        fileMgr.close();
        */
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        int result = compiler.run(null, null, null, file);
        System.out.println(result == 0 ? "编译成功" : "编译失败");

        
        
        // SECOND
        /*** 将对象new出来读取到内存中 ***/
        
        URL[] urls = new URL[] {new URL("file:D:\\develop\\projects\\eclipse-workspace\\day27_Reflection\\src\\")};
        URLClassLoader urlClassLoader = new URLClassLoader(urls);
        Class cls = urlClassLoader.loadClass("com.dhjy.Test");
        System.out.println(cls.getClassLoader().toString()); // sun.misc.Launcher$AppClassLoader@73d16e93
                                                             // 该加载器加载的是classpath下的class文件

        Constructor constructor = cls.getConstructor(String.class);
        Object o = constructor.newInstance("wowowowo");

        return o;
        
    
    }

}
