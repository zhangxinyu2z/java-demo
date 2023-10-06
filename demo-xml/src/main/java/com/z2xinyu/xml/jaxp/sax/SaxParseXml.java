package com.z2xinyu.xml.jaxp.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Jdk提供的Sax解析Xml文件的用法
 * 
 * @author zhang xinyu
 * @date 2021-04-30 19:23:50
 * @version v1.0
 */
public class SaxParseXml {

    public static void main(String[] args) throws Exception {
        /*
         * 1、创建解析器工厂
         * 2、创建解析器
         * 3、执行parse方法
         * 4、自己创建一个类，继承DefaultHandler
         * 5、重写类里面的三个方法
         */
        // 创建解析器工厂
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        // 创建解析器
        SAXParser saxParser = saxParserFactory.newSAXParser();
        // 执行parse方法
        saxParser.parse("Xml\\p1.xml", new DefaultHandlerImpl());
        System.out.println();
        saxParser.parse("Xml\\p1.xml", new DefaultHandlerImpl2());

    }

}

/** 实现获取所有的name元素的值 */
class DefaultHandlerImpl2 extends DefaultHandler {

    boolean flag = false;
    // int index = 1; // 用索引控制获取第几个name元素的value

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        // 判断qName是否是name元素
        if ("name".equals(qName)) {
            flag = true;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        // 当flag值是true时候，表示解析到name元素
        if (flag == true) {
            // if (flag == true && index == 1) { // 获取第一个name元素的value
            System.out.println("value=" + new String(ch, start, length));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        // 把flag设置成false，表示name元素结束
        if ("name".equals(qName)) {
            flag = false;
            // index++;
        }
    }

}

/**
 * 测试：打印XML文件内容
 * 
 * @author zhang xinyu
 * @date 2021-04-30 19:03:47
 * @version v1.0
 */
class DefaultHandlerImpl extends DefaultHandler {

    /**
     * Sax解析到开始标签,自动执行该方法,qName是标签的名字
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.print("<" + qName + ">");
    }

    /**
     * Sax解析到文本内容,自动执行该方法
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        System.out.print(new String(ch, start, length));
    }

    /**
     * Sax解析到结束标签,自动执行该方法
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.print("</" + qName + ">");
    }

}
