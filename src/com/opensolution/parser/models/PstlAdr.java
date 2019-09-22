/*
 * Decompiled with CFR 0.146.
 */
package com.opensolution.parser.models;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

public class PstlAdr
implements DOMGenerator {
    private Node node;
    private Element PstlAdr;
    private String StrtNm;
    private String PstCd;
    private String TwnNm;
    private String Ctry;
    private ArrayList<String> AdrLine;

    public PstlAdr(Node node) {
        this.node = node;
        this.AdrLine = new ArrayList();
        this.createChildNode();
    }

    private void createChildNode() {
        NodeList nodeList = this.node.getChildNodes();
        try {
            block16 : for (int index = 0; index < nodeList.getLength(); ++index) {
                Node node = nodeList.item(index);
                if (node.getNodeType() != 1) continue;
                Element element = (Element)node;
                switch (element.getTagName()) {
                    case "StrtNm": {
                        this.StrtNm = element.getTextContent();
                        continue block16;
                    }
                    case "PstCd": {
                        this.PstCd = element.getTextContent();
                        continue block16;
                    }
                    case "TwnNm": {
                        this.TwnNm = element.getTextContent();
                        continue block16;
                    }
                    case "Ctry": {
                        this.Ctry = element.getTextContent();
                        continue block16;
                    }
                    case "AdrLine": {
                        this.AdrLine.add(element.getTextContent());
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setCtry(String ctry) {
        this.Ctry = ctry;
    }

    @Override
    public void insertSelf(Element parentElement, Document document) {
        this.PstlAdr = document.createElement(this.getClass().getSimpleName());
        parentElement.appendChild(this.PstlAdr);
        Element StrtNm = null;
        if (this.StrtNm != null) {
            StrtNm = document.createElement("StrtNm");
            StrtNm.appendChild(document.createTextNode(this.StrtNm));
        }
        Element PstCd = null;
        if (this.PstCd != null) {
            if (this.PstCd == null) {
                System.out.println("######## NULL VALUE");
            }
            PstCd = document.createElement("PstCd");
            PstCd.appendChild(document.createTextNode(this.PstCd));
        }
        Element TwnNm = null;
        if (this.TwnNm != null) {
            if (this.TwnNm == null) {
                System.out.println("######## NULL VALUE");
            }
            TwnNm = document.createElement("TwnNm");
            TwnNm.appendChild(document.createTextNode(this.TwnNm));
        }
        Element Ctry = null;
        if (this.Ctry != null) {
            if (this.Ctry == null) {
                System.out.println("######## NULL VALUE");
            }
            Ctry = document.createElement("Ctry");
            Ctry.appendChild(document.createTextNode(this.Ctry));
        }
        if (StrtNm != null) {
            this.PstlAdr.appendChild(StrtNm);
        }
        if (PstCd != null) {
            this.PstlAdr.appendChild(PstCd);
        }
        if (TwnNm != null) {
            this.PstlAdr.appendChild(TwnNm);
        }
        if (Ctry != null) {
            this.PstlAdr.appendChild(Ctry);
        }
        for (String str : this.AdrLine) {
            if (str == null) {
                System.out.println("######## NULL VALUE");
            }
            Element AddressLine = document.createElement("AdrLine");
            AddressLine.appendChild(document.createTextNode(str));
            this.PstlAdr.appendChild(AddressLine);
        }
    }
}
