package com.z2xinyu.xml.jaxp.dom;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;

public class JaxpDomUtils {

    public static final String PATH = "Xml/person.xml";
    
    /**
     * 返回Document对象
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public static Document getDocument(String path) throws ParserConfigurationException, SAXException, IOException {
        // 创建解析器工厂
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        // 创建解析器
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        // 得到document
        Document document = builder.parse(PATH);
        return document;
    }
    
    /**
     * 回写Xml
     * @param document
     * @throws TransformerException
     */
    public static void writeBackXML(Document document,String path) throws TransformerException {
        // 回写xml
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.transform(new DOMSource(document), new StreamResult(PATH));
    }

}
