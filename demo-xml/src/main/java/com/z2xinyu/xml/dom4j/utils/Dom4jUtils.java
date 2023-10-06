package com.z2xinyu.xml.dom4j.utils;

import org.dom4j.Document;
import org.dom4j.io.*;

import java.io.FileOutputStream;

public class Dom4jUtils {

    public static final String PATH = "fo/p1.xml";

    /**
     * 返回Document对象
     * 
     * @param path
     * @return
     */
    public static Document getDocument(String path) {
        try {
            // 创建解析器
            SAXReader reader = new SAXReader();
            // 得到document
            Document document = reader.read(path);
            return document;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 回写xml的方法
     * 
     * @param path
     * @param document
     */
    public static void backWriteXml(String path, Document document) {
        try {
            OutputFormat compactFormat = OutputFormat.createCompactFormat(); // 紧凑的格式
            OutputFormat format = OutputFormat.createPrettyPrint(); // 美观的格式
            format.setEncoding("utf-8");
            XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(path), format);
            xmlWriter.write(document);
            xmlWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
