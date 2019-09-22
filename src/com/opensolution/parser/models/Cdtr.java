/*
 * Decompiled with CFR 0.146.
 */
package com.opensolution.parser.models;

import com.opensolution.parser.XMLParser;
import com.opensolution.parser.models.DOMGenerator;
import com.opensolution.parser.models.IdA;
import com.opensolution.parser.models.PstlAdr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class Cdtr
implements DOMGenerator {
    private Node node;
    private Element Cdtr;
    private String Nm;
    private PstlAdr pstlAdr;
    private IdA id;
    public static String staticNm = null;

    public Cdtr(Node node) {
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
                    case "PstlAdr": {
                        this.pstlAdr = new PstlAdr(node);
                        continue block12;
                    }
                    case "Id": {
                        this.id = new IdA(node);
                        continue block12;
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
        this.Cdtr = document.createElement(this.getClass().getSimpleName());
        parentElement.appendChild(this.Cdtr);
        Element Nm = document.createElement("Nm");
        Nm.appendChild(document.createTextNode(this.Nm));
        staticNm = this.Nm;
        this.Cdtr.appendChild(Nm);
        if (this.pstlAdr != null) {
            this.pstlAdr.setCtry(XMLParser.defaultCountryCode);
            this.pstlAdr.insertSelf(this.Cdtr, document);
        }
        if (this.id != null) {
            this.id.insertSelf(this.Cdtr, document);
        }
    }
}
