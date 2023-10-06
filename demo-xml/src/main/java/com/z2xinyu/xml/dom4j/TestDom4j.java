package com.z2xinyu.xml.dom4j;

import com.z2xinyu.xml.dom4j.utils.Dom4jUtils;
import org.dom4j.*;

import java.util.List;

/**
 * 使用dom4j来解析Xml文件
 * 
 * @author zhang xinyu
 * @date 2021-04-30 19:47:29
 * @version v1.0
 */
public class TestDom4j {

    public static void main(String[] args) throws Exception {
        // selectName();
        // selectSin();
        // selectSecond();
        // addSex();
        // addAgeBefore();
        // modifyAge();
        // delSch();
        getAttributeValue();

    }

    /**
     * 获取第一个p1里面的属性id1的值
     */
    public static void getAttributeValue() throws Exception {
        /*
         * 1、得到document
         * 2、得到根节点
         * 3、得到第一个p1元素
         * 4、得到p1里面的属性值
         */
        // 得到document
        Document document = Dom4jUtils.getDocument(Dom4jUtils.PATH);
        // 得到根节点
        Element root = document.getRootElement();
        // 得到第一个p1元素
        Element p1 = root.element("p1");
        // 得到p1里面的属性值
        String value = p1.attributeValue("id1");
        System.out.println(value);
    }

    /**
     * 删除第一个p1下面的<school>ecit</school>元素
     * 
     * @throws Exception
     */
    public static void delSch() throws Exception {
        /*
         * 1、得到document
         * 2、得到根节点
         * 3、得到第一个p1标签
         * 4、得到第一个p1下面的school元素
         * 5、删除（使用p1删除school）
         * 
         * 6、回写xml
         */
        // 得到document
        Document document = Dom4jUtils.getDocument(Dom4jUtils.PATH);
        // 得到根节点
        Element root = document.getRootElement();
        // 得到第一个p1元素
        Element p1 = root.element("p1");
        // 得到p1下面的school标签
        Element sch = p1.element("school");
        // 删除school元素
        // 通过父节点删除
        // 获取父节点的方法
        // sch.getParent(); //获取到school的父节点p1
        p1.remove(sch);
        // 回写xml
        Dom4jUtils.backWriteXml(Dom4jUtils.PATH, document);
    }

    /**
     * 修改第一个p1下面的age元素的值 <age>30</age>
     * 
     * @throws Exception
     */
    public static void modifyAge() throws Exception {
        /*
         * 1、得到document
         * 2、得到根节点，然后再得到第一个p1元素
         * 3、得到第一个p1下面的age
         * 4、修改值是 30
         * 
         * 5、回写xml
         * 
         * */
        // 得到document
        Document document = Dom4jUtils.getDocument(Dom4jUtils.PATH);
        // 得到第一个根节点
        Element root = document.getRootElement();
        // 得到第一个p1
        Element p1 = root.element("p1");
        // 得到p1下面的age
        Element age = p1.element("age");
        // 修改age的值
        age.setText("300");
        // 回写xml
        Dom4jUtils.backWriteXml(Dom4jUtils.PATH, document);
    }

    /**
     * 在第一个p1下面的age标签之前添加 <school>ecit.edu.cn</schlool>
     * 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static void addElementBefore() throws Exception {
        /*
         * 1、创建解析器
         * 2、得到document
         * 3、得到根节点
         * 4、获取到第一个p1
         * 
         * 5、获取p1下面的所有的元素
         *      ** elements()方法 返回 list集合
         *      ** 使用list里面的方法，在特定位置添加元素
         *      ** 创建元素 在元素下面创建文本
         *          *** add(int index, E element)
         *              - 第一个参数是 位置 下标，从0开始
         *              - 第二个参数是 要添加的元素
         * 6、回写xml
         * */

        Document document = Dom4jUtils.getDocument(Dom4jUtils.PATH);
        // 得到根节点
        Element root = document.getRootElement();
        // 获取到第一个p1
        Element p1 = root.element("p1");
        // 获取p1下面的所有元素
        List<Element> list = p1.elements();
        // 创建元素 使用
        Element school = DocumentHelper.createElement("school");
        // 在school下面创建文本
        school.setText("ecit");
        // 在特定位置添加
        list.add(1, school);
        // 回写xml
        Dom4jUtils.backWriteXml(Dom4jUtils.PATH, document);
    }

    /**
     * 在第一个p1标签末尾添加一个元素 <sex>nv</sex>
     * 
     * @throws Exception
     */
    public static void addSex() throws Exception {
        /*
         * 1、创建解析器
         * 2、得到document
         * 3、得到根节点
         * 
         * 4、获取到第一个p1
         * 5、在p1下面添加元素
         * 6、在添加完成之后的元素下面添加文本
         * 
         * 7、回写xml
         * */
        // 得到document
        Document document = Dom4jUtils.getDocument(Dom4jUtils.PATH);
        // 得到根节点
        Element root = document.getRootElement();
        // 得到第一个p1元素
        Element p1 = root.element("p1");
        // 在p1下面直接添加元素
        Element sex1 = p1.addElement("sex");
        // 在sex下面添加文本
        sex1.setText("nv");
        // 回写xml
        Dom4jUtils.backWriteXml(Dom4jUtils.PATH, document);
    }

    /**
     * 获取第二个name元素里面的值
     * 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static void getTextValue() throws Exception {
        /*
         * 1、创建解析器
         * 2、得到document
         * 3、得到根节点
         * 
         *  4、得到所有的p1
         *  5、遍历得到第二个p1
         *  6、得到第二个p1下面的name
         *  7、得到name的值
         * */
        // 得到document
        Document document = Dom4jUtils.getDocument(Dom4jUtils.PATH);
        // 得到根节点
        Element root = document.getRootElement();
        // 得到所有的p1
        List<Element> list = root.elements("p1");
        // 得到第一个p1
        // Element p1 = root.element("p1");
        // 得到第二个p1 list集合下标从0开始
        Element p2 = list.get(1);
        // 得到p1下面的name
        Element name2 = p2.element("name");
        // 得到name里面的值
        String s2 = name2.getText();
        System.out.println(s2);
    }

    /**
     * 查询xml中所有name元素的值
     * 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static void selectName() throws Exception {
        /*
         * 1、创建解析器
         * 2、得到document
         * 3、得到根节点
         * 
         * 4、得到p1
         * 5、得到p1下面的name
         * 6、得到name里面的值
         * */
        // 得到document
        Document document = Dom4jUtils.getDocument(Dom4jUtils.PATH);
        // 得到根节点
        Element root = document.getRootElement();
        // 得到p1
        List<Element> list = root.elements("p1");
        // 遍历list
        for (Element element : list) {
            // element是每一个p1元素
            // 得到p1下面的name元素
            Element name1 = element.element("name");
            // 得到name里面的值
            String s = name1.getText();
            System.out.println(s);
        }
    }
}
