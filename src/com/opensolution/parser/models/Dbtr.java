/*
 * Decompiled with CFR 0.146.
 */
package com.opensolution.parser.models;

import com.opensolution.parser.XMLParser;
import com.opensolution.parser.models.DOMGenerator;
import com.opensolution.parser.models.PstlAdr;
import java.io.PrintStream;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class Dbtr
implements DOMGenerator {
    private Node node;
    private Element Dbtr;
    private String Nm;
    private String CtryOfRes;
    private PstlAdr pstlAdr;

    public Dbtr(Node node) {
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
                    case "Nm": {
                        this.Nm = element.getTextContent();
                        continue block12;
                    }
                    case "CtryOfRes": {
                        this.CtryOfRes = element.getTextContent();
                        continue block12;
                    }
                    case "PstlAdr": {
                        this.pstlAdr = new PstlAdr(node);
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return "Dbtr{Nm='" + this.Nm + '\'' + '}';
    }

    @Override
    public void insertSelf(Element parentElement, Document document) {
        this.Dbtr = document.createElement(this.getClass().getSimpleName());
        parentElement.appendChild(this.Dbtr);
        if (this.Nm == null) {
            System.out.println("######## NULL VALUE");
        }
        Element Nm = document.createElement("Nm");
        Nm.appendChild(document.createTextNode(this.Nm));
        Element CtryOfRes = null;
        if (this.CtryOfRes != null) {
            CtryOfRes = document.createElement("CtryOfRes");
            CtryOfRes.appendChild(document.createTextNode(this.CtryOfRes));
        } else if (XMLParser.defaultCountryCode.length() > 0) {
            CtryOfRes = document.createElement("CtryOfRes");
            CtryOfRes.appendChild(document.createTextNode(XMLParser.defaultCountryCode));
        }
        this.Dbtr.appendChild(Nm);
        if (this.pstlAdr != null) {
            this.pstlAdr.insertSelf(this.Dbtr, document);
        } else if (XMLParser.defaultCountryCode.length() > 0) {
            this.pstlAdr = new PstlAdr(this.node);
            this.pstlAdr.setCtry(XMLParser.defaultCountryCode);
            this.pstlAdr.insertSelf(this.Dbtr, document);
        }
        if (CtryOfRes != null) {
            this.Dbtr.appendChild(CtryOfRes);
        }
    }
}
