/*
 * Decompiled with CFR 0.146.
 */
package com.opensolution.parser.models;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SchmeNm
implements DOMGenerator {
    private Node node;
    private Element SchmeNm;
    private String Prtry;

    public SchmeNm(Node node) {
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
                switch (element.getTagName()) {
                    case "Prtry": {
                        this.Prtry = element.getTextContent();
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
        this.SchmeNm = document.createElement(this.getClass().getSimpleName());
        parentElement.appendChild(this.SchmeNm);
        if (this.Prtry == null) {
            System.out.println("######## NULL VALUE");
        }
        Element Prtry = document.createElement("Prtry");
        Prtry.appendChild(document.createTextNode(this.Prtry));
        this.SchmeNm.appendChild(Prtry);
    }
}
