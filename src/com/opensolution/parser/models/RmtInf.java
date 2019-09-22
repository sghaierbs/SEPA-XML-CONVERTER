/*
 * Decompiled with CFR 0.146.
 */
package com.opensolution.parser.models;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class RmtInf
implements DOMGenerator {
    private Node node;
    private Element RmtInf;
    private String Ustrd;

    public RmtInf(Node node) {
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
                    case "Ustrd": {
                        this.Ustrd = element.getTextContent();
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
        this.RmtInf = document.createElement(this.getClass().getSimpleName());
        parentElement.appendChild(this.RmtInf);
        if (this.Ustrd == null) {
            System.out.println("######## NULL VALUE");
        }
        Element Ustrd = document.createElement("Ustrd");
        Ustrd.appendChild(document.createTextNode(this.Ustrd));
        this.RmtInf.appendChild(Ustrd);
    }
}
