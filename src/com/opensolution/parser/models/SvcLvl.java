/*
 * Decompiled with CFR 0.146.
 */
package com.opensolution.parser.models;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SvcLvl
implements DOMGenerator {
    private Node node;
    private Element SvcLvl;
    private String Cd;
    private String Prtry;

    public SvcLvl(Node node) {
        this.node = node;
        this.createChildNode();
    }

    private void createChildNode() {
        NodeList nodeList = this.node.getChildNodes();
        try {
            block10 : for (int index = 0; index < nodeList.getLength(); ++index) {
                Node node = nodeList.item(index);
                if (node.getNodeType() != 1) continue;
                Element element = (Element)node;
                switch (element.getTagName()) {
                    case "Cd": {
                        this.Cd = element.getTextContent();
                        continue block10;
                    }
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

    public String toString() {
        return "SvcLvl{Cd='" + this.Cd + '\'' + '}';
    }

    @Override
    public void insertSelf(Element parentElement, Document document) {
        this.SvcLvl = document.createElement(this.getClass().getSimpleName());
        parentElement.appendChild(this.SvcLvl);
        Element Cd = null;
        if (this.Cd != null) {
            if (this.Cd == null) {
                System.out.println("######## NULL VALUE");
            }
            Cd = document.createElement("Cd");
            Cd.appendChild(document.createTextNode(this.Cd));
        }
        Element Prtry = null;
        if (this.Prtry != null) {
            if (this.Prtry == null) {
                System.out.println("######## NULL VALUE");
            }
            Prtry = document.createElement("Prtry");
            Prtry.appendChild(document.createTextNode(this.Prtry));
        }
        if (Cd != null) {
            this.SvcLvl.appendChild(Cd);
        }
        if (Prtry != null) {
            this.SvcLvl.appendChild(Prtry);
        }
    }
}
