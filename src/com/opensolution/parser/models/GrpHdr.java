/*
 * Decompiled with CFR 0.146.
 */
package com.opensolution.parser.models;

import com.opensolution.parser.models.DOMGenerator;
import com.opensolution.parser.models.InitgPty;
import java.io.PrintStream;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class GrpHdr
implements DOMGenerator {
    private Node node;
    private Element GrpHdr;
    private String MsgId;
    private String CreDtTm;
    private String NbOfTxs;
    private String CtrlSum;
    private InitgPty initgPty;

    public GrpHdr(Node node) {
        this.node = node;
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
                    case "InitgPty": {
                        this.initgPty = new InitgPty(node);
                        continue block16;
                    }
                    case "MsgId": {
                        this.MsgId = element.getTextContent();
                        continue block16;
                    }
                    case "CreDtTm": {
                        this.CreDtTm = element.getTextContent();
                        continue block16;
                    }
                    case "NbOfTxs": {
                        this.NbOfTxs = element.getTextContent();
                        continue block16;
                    }
                    case "CtrlSum": {
                        this.CtrlSum = element.getTextContent();
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return "GrpHdr{MsgId='" + this.MsgId + '\'' + ", CreDtTm='" + this.CreDtTm + '\'' + ", NbOfTxs='" + this.NbOfTxs + '\'' + ", CtrlSum='" + this.CtrlSum + '\'' + ", initgPty=" + this.initgPty + '}';
    }

    @Override
    public void insertSelf(Element parentElement, Document document) {
        this.GrpHdr = document.createElement(this.getClass().getSimpleName());
        if (this.MsgId == null) {
            System.out.println("######## NULL VALUE");
        }
        Element MsgId = document.createElement("MsgId");
        MsgId.appendChild(document.createTextNode(this.MsgId));
        if (this.CreDtTm == null) {
            System.out.println("######## NULL VALUE");
        }
        Element CreDtTm = document.createElement("CreDtTm");
        CreDtTm.appendChild(document.createTextNode(this.CreDtTm));
        if (this.NbOfTxs == null) {
            System.out.println("######## NULL VALUE");
        }
        Element NbOfTxs = document.createElement("NbOfTxs");
        NbOfTxs.appendChild(document.createTextNode(this.NbOfTxs));
        if (this.CtrlSum == null) {
            System.out.println("######## NULL VALUE");
        }
        Element CtrlSum = document.createElement("CtrlSum");
        CtrlSum.appendChild(document.createTextNode(this.CtrlSum));
        this.GrpHdr.appendChild(MsgId);
        this.GrpHdr.appendChild(CreDtTm);
        this.GrpHdr.appendChild(NbOfTxs);
        this.GrpHdr.appendChild(CtrlSum);
        parentElement.appendChild(this.GrpHdr);
        this.initgPty.insertSelf(this.GrpHdr, document);
    }
}
