/*
 * Decompiled with CFR 0.146.
 */
package com.opensolution.parser.models;

import com.opensolution.parser.models.DOMGenerator;
import com.opensolution.parser.models.IdA;
import com.opensolution.view.MainFrame;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class InitgPty
implements DOMGenerator {
    private Node node;
    private Element InitgPty;
    private String Nm;
    private IdA id;

    public InitgPty(Node node) {
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
                    case "Nm": {
                        this.Nm = element.getTextContent();
                        continue block10;
                    }
                    case "Id": {
                        this.id = new IdA(node);
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return "InitgPty{Nm='" + this.Nm + '\'' + '}';
    }

    @Override
    public void insertSelf(Element parentElement, Document document) {
        this.InitgPty = document.createElement(this.getClass().getSimpleName());
        Element Nm = null;
        if (this.Nm != null) {
            Nm = document.createElement("Nm");
            Nm.appendChild(document.createTextNode(this.Nm));
        }
        if (Nm != null) {
            this.InitgPty.appendChild(Nm);
        }
        if (this.id != null && MainFrame.showFirstIdTag) {
            this.id.insertSelf(this.InitgPty, document);
        }
        parentElement.appendChild(this.InitgPty);
    }
}
