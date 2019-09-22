/*
 * Decompiled with CFR 0.146.
 */
package com.opensolution.parser.models;

import com.opensolution.parser.models.DOMGenerator;
import com.opensolution.parser.models.Othr;
import java.io.PrintStream;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class CdtrAgtFinInstnId implements DOMGenerator {
    private Node node;
    private Element FinInstnId;
    private String BIC;
    private String Nm = "Societe Generale, AG St Laurent, St Laurent Du Var";
    private Othr othr;

    public CdtrAgtFinInstnId(Node node) {
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
                    case "BIC": {
                        this.BIC = element.getTextContent();
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
        this.FinInstnId = document.createElement("FinInstnId");
        parentElement.appendChild(this.FinInstnId);
        if (this.BIC == null) {
            System.out.println("######## NULL VALUE");
        }
        Element BIC = document.createElement("BIC");
        BIC.appendChild(document.createTextNode(this.BIC));
        if (this.Nm == null) {
            System.out.println("######## NULL VALUE");
        }
        Element Nm = document.createElement("Nm");
        Nm.appendChild(document.createTextNode(this.Nm));
        this.FinInstnId.appendChild(BIC);
        this.FinInstnId.appendChild(Nm);
        this.othr.insertSelf(this.FinInstnId, document);
    }
}
