/*
 * Decompiled with CFR 0.146.
 */
package com.opensolution.parser.models;

import com.opensolution.parser.models.Cdtr;
import com.opensolution.parser.models.DOMGenerator;
import com.opensolution.parser.models.IdB;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class CdtrAcct
implements DOMGenerator {
    private Node node;
    private Element CdtrAcct;
    private IdB id;
    private String Nm;

    public CdtrAcct(Node node) {
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
                    case "Nm": {
                        this.Nm = element.getTextContent();
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
        this.CdtrAcct = document.createElement(this.getClass().getSimpleName());
        parentElement.appendChild(this.CdtrAcct);
        Element Nm = null;
        if (this.Nm != null) {
            Nm = document.createElement("Nm");
            Nm.appendChild(document.createTextNode(this.Nm));
        } else if (Cdtr.staticNm != null) {
            Nm = document.createElement("Nm");
            Nm.appendChild(document.createTextNode(Cdtr.staticNm));
            Cdtr.staticNm = null;
        }
        this.id.insertSelf(this.CdtrAcct, document);
        if (Nm != null) {
            this.CdtrAcct.appendChild(Nm);
        }
    }
}
