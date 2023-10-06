package com.z2xinyu.xml.jaxp.dom;

import com.z2xinyu.xml.dom4j.utils.Dom4jUtils;
import org.w3c.dom.*;

/**
 * jaxp操作xml
 * 
 * @author zhang xinyu
 * @date 2021-04-30 17:36:44
 * @version v1.0
 */
public class DomParseXMl {

    public static void main(String[] args) throws Exception {
        // listElement();
        // deleteElement();
        // modifyElementValue();
        // addElement();
        // queryAttributeValue();
        queryAttributesValue();
    }

    /**
     * 遍历节点，把所有元素名称打印出来
     * 
     * @throws Exception
     */
    public static void listElement() throws Exception {
        /*
         * 1、创建解析器工厂
         * 2、根据解析器工厂创建解析器
         * 3、解析xml，返回document
         * 
         * ====使用递归实现=====
         * 4、得到根节点
         * 5、得到根节点子节点
         * 6、得到根节点子节点的子节点
         * */
        // 得到document
        Document document = JaxpDomUtils.getDocument(Dom4jUtils.PATH);
        // 编写一个方法实现遍历操作
        iterator(document);
    }

    // 递归遍历的方法
    private static void iterator(Node node) {
        // 判断是元素类型时候才打印
        if (node.getNodeType() == Node.ELEMENT_NODE) { // 1
            System.out.println(node.getNodeName());
        }

        // 得到一层子节点
        NodeList list = node.getChildNodes();
        // 遍历list
        for (int i = 0; i < list.getLength(); i++) {
            // 得到每一个节点
            Node node1 = list.item(i);
            // 继续得到node1的子节点
            // node1.getChildNodes()
            iterator(node1);
        }
    }

    /**
     * 删除<sex>nan</sex>节点
     * 
     * @throws Exception
     */
    public static void deleteElement() throws Exception {
        /*
         * 1、创建解析器工厂
         * 2、根据解析器工厂创建解析器
         * 3、解析xml，返回document 
         * 
         * 4、获取sex元素
         * 5、获取sex的父节点 
         * 6、删除使用父节点删除 removeChild方法
         * 
         * 7、回写xml
         * */
        Document document = JaxpDomUtils.getDocument(Dom4jUtils.PATH);
        // 得到sex元素
        Node sex1 = document.getElementsByTagName("sex").item(0);
        // 得到sex1父节点
        Node p1 = sex1.getParentNode();
        // 删除操作
        p1.removeChild(sex1);
        // 回写xml
        JaxpDomUtils.writeBackXML(document, JaxpDomUtils.PATH);
    }

    /**
     * 修改第一个p1下面的sex内容是nan
     * 
     * @throws Exception
     */
    public static void modifyElementValue() throws Exception {
        /*
         * 1、创建解析器工厂
         * 2、根据解析器工厂创建解析器
         * 3、解析xml，返回document 
         * 
         * 4、得到sex item方法
         * 5、修改sex里面的值  setTextContent方法
         * 
         * 6、回写xml
         * */
        Document document = JaxpDomUtils.getDocument(Dom4jUtils.PATH);
        // 得到sex
        Node sex1 = document.getElementsByTagName("sex").item(0);
        // 修改sex值
        sex1.setTextContent("nan");
        // 回写xml
        JaxpDomUtils.writeBackXML(document, JaxpDomUtils.PATH);
    }

    /**
     * 在第一个p1下面（末尾）添加 <sex>nv</sex>
     * 
     * @throws Exception
     */
    public static void addElement() throws Exception {
        /*
         * 1、创建解析器工厂
         * 2、根据解析器工厂创建解析器
         * 3、解析xml，返回document
         * 
         * 4、得到第一个p1
         *  - 得到所有p1，使用item方法下标得到
         * 5、创建sex标签 createElement
         * 6、创建文本 createTextNode
         * 7、把文本添加到sex下面 appendChild
         * 8、把sex添加到第一个p1下面
         * 
         * 9、回写xml
         * */
        Document document = JaxpDomUtils.getDocument(Dom4jUtils.PATH);

        // 得到所有的p1
        NodeList list = document.getElementsByTagName("p1");
        // 得到第一个p1
        Node p1 = list.item(0);
        // 创建标签
        Element sex1 = document.createElement("sex");
        // 创建文本
        Text text1 = document.createTextNode("nv");
        // 把文本添加到sex1下面
        sex1.appendChild(text1);
        // 把sex1添加到p1下面
        p1.appendChild(sex1);
        // 回写xml
        JaxpDomUtils.writeBackXML(document, JaxpDomUtils.PATH);
    }

    /**
     * 查询xml中第一个name元素的值
     * 
     * @throws Exception
     */
    public static void queryAttributeValue() throws Exception {
        /*
         * 1、创建解析器工厂
         * 2、根据解析器工厂创建解析器
         * 3、解析xml，返回document
         * 
         * 4、得到所有name元素
         * 5、使用返回集合，里面方法 item，下标获取具体的元素
         * 6、得到具体的值，使用 getTextContent方法
         * 
         * */
        Document document = JaxpDomUtils.getDocument(Dom4jUtils.PATH);
        // 得到所有的name元素
        NodeList list = document.getElementsByTagName("name");
        // 使用下标 得到第一个元素
        Node name1 = list.item(1);
        // 得到name里面的具体的值
        String s1 = name1.getTextContent();
        System.out.println(s1);
    }

    /**
     * 查询所有name元素的值
     * 
     * @throws Exception
     */
    public static void queryAttributesValue() throws Exception {
        /*
         * 1、创建解析器工厂
         * 2、根据解析器工厂创建解析器
         * 3、解析xml返回document
         *  
         * 4、得到所有的name元素
         * 5、返回集合，遍历集合，得到每一个name元素
         * */
        Document document = JaxpDomUtils.getDocument(Dom4jUtils.PATH);
        // 得到name元素
        NodeList list = document.getElementsByTagName("name");
        // 遍历集合
        for (int i = 0; i < list.getLength(); i++) {
            Node name1 = list.item(i); // 得到每一个name元素
            // 得到name元素里面的值
            String s = name1.getTextContent();
            System.out.println(s);
        }
    }

}
