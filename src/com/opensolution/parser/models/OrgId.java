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

public class OrgId
implements DOMGenerator {
    private Node node;
    private Element OrgId;
    private Othr othr;
    private String BICorBEI;

    public OrgId(Node node) {
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
                    case "Othr": {
                        this.othr = new Othr(node);
                        continue block10;
                    }
                    case "BICorBEI": {
                        this.BICorBEI = element.getTextContent();
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
        this.OrgId = document.createElement(this.getClass().getSimpleName());
        parentElement.appendChild(this.OrgId);
        Element BICorBEI = null;
        if (this.BICorBEI != null) {
            if (this.BICorBEI == null) {
                System.out.println("######## NULL VALUE");
            }
            BICorBEI = document.createElement("BICorBEI");
            BICorBEI.appendChild(document.createTextNode(this.BICorBEI));
        }
        if (BICorBEI != null) {
            this.OrgId.appendChild(BICorBEI);
        }
        if (this.othr != null) {
            this.othr.insertSelf(this.OrgId, document);
        }
    }
}
