/*
 * Decompiled with CFR 0.146.
 */
package com.opensolution.parser.models;

import com.opensolution.parser.XMLParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class PmtTpInf
implements DOMGenerator {
    private Node node;
    private Element PmtTpInf;
    private String InstrPrty;
    private SvcLvl svcLvl;
    private CtgyPurp ctgyPurp;

    public PmtTpInf(Node node) {
        this.node = node;
        this.createChildNode();
    }

    private void createChildNode() {
        NodeList nodeList = this.node.getChildNodes();
        try {
            block12 : for (int index = 0; index < nodeList.getLength(); ++index) {
                Node node = nodeList.item(index);
                if (node.getNodeType() != 1) continue;
                Element element = (Element)node;
                switch (element.getTagName()) {
                    case "SvcLvl": {
                        this.svcLvl = new SvcLvl(node);
                        continue block12;
                    }
                    case "CtgyPurp": {
                        this.ctgyPurp = new CtgyPurp(node);
                        continue block12;
                    }
                    case "InstrPrty": {
                        this.InstrPrty = element.getTextContent();
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return "PmtTpInf{svcLvl=" + this.svcLvl + ", ctgyPurp=" + this.ctgyPurp + '}';
    }

    @Override
    public void insertSelf(Element parentElement, Document document) {
        this.PmtTpInf = document.createElement(this.getClass().getSimpleName());
        parentElement.appendChild(this.PmtTpInf);
        Element InstrPrty = null;
        if (this.InstrPrty != null) {
            InstrPrty = document.createElement("InstrPrty");
            InstrPrty.appendChild(document.createTextNode(this.InstrPrty));
        } else if (XMLParser.priority.length() != 0) {
            InstrPrty = document.createElement("InstrPrty");
            InstrPrty.appendChild(document.createTextNode(XMLParser.priority));
        }
        if (InstrPrty != null) {
            this.PmtTpInf.appendChild(InstrPrty);
        }
        if (this.svcLvl != null) {
            this.svcLvl.insertSelf(this.PmtTpInf, document);
        }
        if (this.ctgyPurp != null) {
            this.ctgyPurp.insertSelf(this.PmtTpInf, document);
        }
    }
}
