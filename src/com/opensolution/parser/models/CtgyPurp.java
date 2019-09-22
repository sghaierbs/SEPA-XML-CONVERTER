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

public class CtgyPurp
implements DOMGenerator {
    private Node node;
    private Element CtgyPurp;
    private String Cd;

    public CtgyPurp(Node node) {
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

    public String toString() {
        return "CtgyPurp{Cd='" + this.Cd + '\'' + '}';
    }

    @Override
    public void insertSelf(Element parentElement, Document document) {
        this.CtgyPurp = document.createElement(this.getClass().getSimpleName());
        parentElement.appendChild(this.CtgyPurp);
        Element Cd = null;
        if (this.Cd != null) {
            if (this.Cd == null) {
                System.out.println("######## NULL VALUE");
            }
            Cd = document.createElement("Cd");
            Cd.appendChild(document.createTextNode(this.Cd));
        }
        if (Cd != null) {
            this.CtgyPurp.appendChild(Cd);
        }
    }
}
