package com.z2xinyu.collection.map;

import org.junit.Test;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

/**
 * Properties继承自HashTable，表示一组持久的属性，以保存到流中或从流中加载，事一个和io流结合的属性集合类
 * 属性列表中的每个键及其对应的值都是一个字符串。
 * @author zhangxinyu
 * @date 2023/8/15
 **/
public class PropertiesTest {

    @Test
    public void demo() throws IOException {
        Properties p = new Properties();
        p.setProperty("1", "yi fei");
        p.setProperty("2", "yi fei");

        Object value = p.get("1");
        System.out.println(value);

        FileWriter fw = new FileWriter("output.txt");
        p.store(fw, "store");

        // 取
        p.load(new FileReader("output.txt"));

        // 返回所有的键
        Set<String> keys = p.stringPropertyNames();
        for (String s : keys) {
            // 键值为1的值修改
            if (s.equals("1")) {
                p.setProperty("1", "liu yi fei");
                break;
            }
        }
        // 重新存储
        p.store(fw, "store2");
    }
}
