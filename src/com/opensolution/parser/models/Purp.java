/*
 * Decompiled with CFR 0.146.
 */
package com.opensolution.parser.models;

import com.opensolution.parser.XMLParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Purp
implements DOMGenerator {
    private Node node;
    private Element Purp;
    private String Cd;

    public Purp(Node node) {
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
                    case "Cd": {
                        this.Cd = element.getTextContent();
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
        this.Purp = document.createElement(this.getClass().getSimpleName());
        parentElement.appendChild(this.Purp);
        Element Cd = null;
        if (this.Cd != null) {
            Cd = document.createElement("Cd");
            Cd.appendChild(document.createTextNode(this.Cd));
        } else if (XMLParser.paymentReason.length() != 0) {
            Cd = document.createElement("Cd");
            Cd.appendChild(document.createTextNode(XMLParser.paymentReason));
        }
        this.Purp.appendChild(Cd);
    }
}
