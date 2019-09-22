/*
 * Decompiled with CFR 0.146.
 */
package com.opensolution.parser.models;

import com.opensolution.parser.models.DOMGenerator;
import java.io.PrintStream;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class Amt
implements DOMGenerator {
    private Node node;
    private Element Amt;
    private String InstdAmt;
    private String NodeName;
    private String NodeValue;

    public Amt(Node node) {
        this.node = node;
        this.createChildNode();
    }

    private void createChildNode() {
        NodeList nodeList = this.node.getChildNodes();
        try {
            for (int index = 0; index < nodeList.getLength(); ++index) {
                Node node = nodeList.item(index);
                if (node.getNodeType() != 1) continue;
                Element element = (Element)node;
                NamedNodeMap nodeMap = element.getAttributes();
                for (int j = 0; j < nodeMap.getLength(); ++j) {
                    System.out.println("--------------------------------- NAME  " + nodeMap.item(j).getNodeName());
                    System.out.println("--------------------------------- VALUE  " + nodeMap.item(j).getNodeValue());
                    System.out.println("--------------------------------- TEXT " + nodeMap.item(j).getTextContent());
                }
                switch (element.getTagName()) {
                    case "InstdAmt": {
                        this.NodeName = nodeMap.item(0).getNodeName();
                        this.NodeValue = nodeMap.item(0).getNodeValue();
                        this.InstdAmt = element.getTextContent();
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertSelf(Element parentElement, Document document) {
        this.Amt = document.createElement(this.getClass().getSimpleName());
        parentElement.appendChild(this.Amt);
        if (this.InstdAmt == null) {
            System.out.println("######## NULL VALUE");
        }
        Element InstdAmt = document.createElement("InstdAmt");
        InstdAmt.appendChild(document.createTextNode(this.InstdAmt));
        Attr xsi = document.createAttribute(this.NodeName);
        xsi.setValue(this.NodeValue);
        InstdAmt.setAttributeNode(xsi);
        this.Amt.appendChild(InstdAmt);
    }
}
