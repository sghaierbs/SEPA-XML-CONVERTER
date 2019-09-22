/*
 * Decompiled with CFR 0.146.
 */
package com.opensolution.parser.models;

import com.opensolution.parser.XMLParser;
import com.opensolution.parser.models.DOMGenerator;
import com.opensolution.parser.models.Othr;
import com.opensolution.parser.models.PstlAdr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class FinInstnId implements DOMGenerator {
    private Node node;
    private Element FinInstnId;
    private String BIC;
    private PstlAdr pstlAdr;
    private Othr othr;
    private String Nm;
    private boolean addNm = false;
    private boolean addOthr = false;

    public FinInstnId(Node node) {
        this.node = node;
        this.createChildNode();
    }

    public FinInstnId(Node node, boolean addNm, boolean addOthr){
        this.node = node;
        this.othr = new Othr("300");
        this.createChildNode();
    }

    public FinInstnId(Node node, boolean addNm) {
        this.node = node;
        this.addNm = addNm;
        this.createChildNode();
    }

    private void createChildNode() {
        NodeList nodeList = this.node.getChildNodes();
        try {
            block14 : for (int index = 0; index < nodeList.getLength(); ++index) {
                Node node = nodeList.item(index);
                if (node.getNodeType() != 1) continue;
                Element element = (Element)node;
                switch (element.getTagName()) {
                    case "BIC": {
                        this.BIC = element.getTextContent();
                        continue block14;
                    }
                    case "Nm": {
                        this.Nm = element.getTextContent();
                        continue block14;
                    }
                    case "PstlAdr": {
                        this.pstlAdr = new PstlAdr(node);
                        continue block14;
                    }
                    case "Othr": {
                        this.othr = new Othr(node);
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
        this.FinInstnId = document.createElement(this.getClass().getSimpleName());
        parentElement.appendChild(this.FinInstnId);
        Element BIC = null;
        if (this.BIC != null) {
            BIC = document.createElement("BIC");
            BIC.appendChild(document.createTextNode(this.BIC));
        }
        Element Nm = null;
        if (this.Nm != null) {
            Nm = document.createElement("Nm");
            Nm.appendChild(document.createTextNode(this.Nm));
        } else if (XMLParser.financialinstitutionName.length() != 0 && this.addNm) {
            Nm = document.createElement("Nm");
            Nm.appendChild(document.createTextNode(XMLParser.financialinstitutionName));
            XMLParser.financialinstitutionName = "";
        }
        if (BIC != null) {
            this.FinInstnId.appendChild(BIC);
        }
        if (Nm != null) {
            this.FinInstnId.appendChild(Nm);
        }
        if (this.pstlAdr != null) {
            this.pstlAdr.insertSelf(this.FinInstnId, document);
        }
        if (this.othr != null) {
            System.out.println("ADDING THE TAG Othr");
            this.othr.insertSelf(this.FinInstnId, document);
        }else if(this.othr == null && this.addOthr){
            System.out.println("Force Adding Othr TAG ");
            this.othr.insertSelf(this.FinInstnId, document);
        }else{
            System.out.println("ELSE NOT ADDING THE TAG Othr");
        }
    }
}
