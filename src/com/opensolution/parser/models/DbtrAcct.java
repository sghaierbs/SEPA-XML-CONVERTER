/*
 * Decompiled with CFR 0.146.
 */
package com.opensolution.parser.models;

import com.opensolution.parser.models.DOMGenerator;
import com.opensolution.parser.models.IdB;
import java.io.PrintStream;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class DbtrAcct
implements DOMGenerator {
    private Node node;
    private Element DbtrAcct;
    private IdB id;
    private String Ccy;

    public DbtrAcct(Node node) {
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
                    case "Id": {
                        this.id = new IdB(node);
                        continue block10;
                    }
                    case "Ccy": {
                        this.Ccy = element.getTextContent();
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return "DbtrAcct{id=" + this.id + ", Ccy='" + this.Ccy + '\'' + '}';
    }

    @Override
    public void insertSelf(Element parentElement, Document document) {
        this.DbtrAcct = document.createElement(this.getClass().getSimpleName());
        parentElement.appendChild(this.DbtrAcct);
        Element Ccy = null;
        if (this.Ccy != null) {
            if (this.Ccy == null) {
                System.out.println("######## NULL VALUE");
            }
            Ccy = document.createElement("Ccy");
            Ccy.appendChild(document.createTextNode(this.Ccy));
        }
        this.id.insertSelf(this.DbtrAcct, document);
        if (Ccy != null) {
            this.DbtrAcct.appendChild(Ccy);
        }
    }
}
