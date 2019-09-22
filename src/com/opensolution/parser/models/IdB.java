/*
 * Decompiled with CFR 0.146.
 */
package com.opensolution.parser.models;

import com.opensolution.parser.models.DOMGenerator;
import java.io.PrintStream;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class IdB
implements DOMGenerator {
    private Node node;
    private Element Id;
    private String IBAN;

    public IdB(Node node) {
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
                    case "IBAN": {
                        this.IBAN = element.getTextContent();
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return "Id{IBAN='" + this.IBAN + '\'' + '}';
    }

    @Override
    public void insertSelf(Element parentElement, Document document) {
        this.Id = document.createElement("Id");
        parentElement.appendChild(this.Id);
        if (this.IBAN == null) {
            System.out.println("######## NULL VALUE");
        }
        Element IBAN = document.createElement("IBAN");
        IBAN.appendChild(document.createTextNode(this.IBAN));
        this.Id.appendChild(IBAN);
    }
}
