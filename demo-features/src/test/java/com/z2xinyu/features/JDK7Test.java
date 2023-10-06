package com.z2xinyu.features;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * JDK7新特性之 try_with_resource
 * <br>
 * "try with resources"可以在try语句块中自动关闭资源，无需手动编写finally块。使用这种方式，可以更简洁地处理资源的关闭操作，并且可以自动处理异常。
 * <br>
 * 资源类必须实现AutoCloseable接口或其子接口（如Closeable）。这些接口中定义了close()方法，用于关闭资源。
 *
 * @author zhangxinyu
 * @date 2023/8/27
 **/
public class JDK7Test {
    /**
     * plain: 在try语句块的括号内，我们声明并初始化这些资源。当try块结束时，不论是正常执行还是出现异常，这些资源都会被自动关闭。无需手动调用close()方法。
     */
    @Test
    public void test1() {
        try (
            BufferedReader br =
                new BufferedReader(new FileReader(".\\src\\com\\dhjy\\d01\\try_with_resource\\Demo01.java"));
            BufferedWriter bw = new BufferedWriter(new FileWriter("Demo01.java"))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                bw.write(line);
                bw.newLine();
                bw.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
