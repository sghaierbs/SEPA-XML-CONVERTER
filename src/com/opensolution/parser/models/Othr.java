/*
 * Decompiled with CFR 0.146.
 */
package com.opensolution.parser.models;

import com.opensolution.parser.models.DOMGenerator;
import com.opensolution.parser.models.SchmeNm;
import java.io.PrintStream;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class Othr implements DOMGenerator {
    private Node node;
    private Element Othr;
    private String Id;
    private String Issr;
    private SchmeNm schmeNm;

    public Othr(Node node) {
        this.node = node;
        this.createChildNode();
    }

    public Othr(String Id){
        this.Id = Id;
    }

    private void createChildNode() {
        NodeList nodeList = this.node.getChildNodes();
        try {
            block12 : for (int index = 0; index < nodeList.getLength(); ++index) {
                Node node = nodeList.item(index);
                if (node.getNodeType() != 1) continue;
                Element element = (Element)node;
                switch (element.getTagName()) {
                    case "SchmeNm": {
                        this.schmeNm = new SchmeNm(node);
                        continue block12;
                    }
                    case "Id": {
                        this.Id = element.getTextContent();
                        continue block12;
                    }
                    case "Issr": {
                        this.Issr = element.getTextContent();
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
        this.Othr = document.createElement("Othr");
        parentElement.appendChild(this.Othr);
        if (this.Id == null) {
            System.out.println("######## NULL VALUE");
        }
        Element Id = document.createElement("Id");
        Id.appendChild(document.createTextNode(this.Id));
        this.Othr.appendChild(Id);
        Element Issr = null;
        if (Issr != null) {
            if (this.Issr == null) {
                System.out.println("######## NULL VALUE");
            }
            Issr = document.createElement("Issr");
            Issr.appendChild(document.createTextNode(this.Issr));
        }
        if (Issr != null) {
            this.Othr.appendChild(Issr);
        }
        if (this.schmeNm != null) {
            this.schmeNm.insertSelf(this.Othr, document);
        }
    }
}
