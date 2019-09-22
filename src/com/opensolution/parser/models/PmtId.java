/*
 * Decompiled with CFR 0.146.
 */
package com.opensolution.parser.models;

import com.opensolution.parser.XMLParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.math.BigInteger;
import java.util.Random;

public class PmtId
implements DOMGenerator {
    private Node node;
    private Element PmtId;
    private String InstrId;
    private String EndToEndId;

    public PmtId(Node node) {
        this.node = node;
        this.InstrId = XMLParser.insrtId.length() > 0 ? XMLParser.insrtId : " ";
        this.createChildNode();
    }


    private String generateRandomId(){
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < 10; i++) {
            String n = BigInteger.valueOf(Math.abs(random.nextLong())).toString(32).toUpperCase();
            if (n.length() > 8) {
                if (n.length() > 10) {
                    n = n.substring(n.length() - 10);
                }
                return n;
            }
        }
        return "Valeur inconnue";
    }

    private void createChildNode() {
        NodeList nodeList = this.node.getChildNodes();
        try {
            block10 : for (int index = 0; index < nodeList.getLength(); ++index) {
                Node node = nodeList.item(index);
                if (node.getNodeType() != 1) continue;
                Element element = (Element)node;
                switch (element.getTagName()) {
                    case "InstrId": {
                        this.InstrId = element.getTextContent();
                        continue block10;
                    }
                    case "EndToEndId": {
                        this.EndToEndId = element.getTextContent();
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
        this.PmtId = document.createElement(this.getClass().getSimpleName());
        parentElement.appendChild(this.PmtId);
        Element InstrId = null;
        if (this.InstrId != null) {
            if (this.InstrId == null) {
                System.out.println("######## NULL VALUE");
            }
            InstrId = document.createElement("InstrId");
            InstrId.appendChild(document.createTextNode(this.InstrId));
        }
        if (this.EndToEndId == null) {
            System.out.println("######## NULL VALUE");
        }
        Element EndToEndId = document.createElement("EndToEndId");
        EndToEndId.appendChild(document.createTextNode(this.EndToEndId));
        if (InstrId != null) {
            this.PmtId.appendChild(InstrId);
        }
        this.PmtId.appendChild(EndToEndId);
    }
}
