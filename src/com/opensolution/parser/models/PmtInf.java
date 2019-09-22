/*
 * Decompiled with CFR 0.146.
 */
package com.opensolution.parser.models;

import com.opensolution.view.MainFrame;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

public class PmtInf
implements DOMGenerator {
    private Node node;
    private Element PmtInf;
    private String PmtInfId;
    private String PmtMtd;
    private String BtchBookg;
    private String NbOfTxs;
    private String CtrlSum;
    private PmtTpInf pmtTpInf;
    private String ReqdExctnDt;
    private Dbtr dbtr;
    private DbtrAcct dbtrAcct;
    private DbtrAgt dbtrAgt;
    private ArrayList<CdtTrfTxInf> cdtTrfTxInf;

    public PmtInf(Node node) {
        this.node = node;
        this.cdtTrfTxInf = new ArrayList();
        this.createChildNode();
    }

    private void createChildNode() {
        NodeList nodeList = this.node.getChildNodes();
        try {
            block28 : for (int index = 0; index < nodeList.getLength(); ++index) {
                Node node = nodeList.item(index);
                if (node.getNodeType() != 1) continue;
                Element element = (Element)node;
                switch (element.getTagName()) {
                    case "PmtTpInf": {
                        this.pmtTpInf = new PmtTpInf(node);
                        continue block28;
                    }
                    case "Dbtr": {
                        this.dbtr = new Dbtr(node);
                        continue block28;
                    }
                    case "DbtrAcct": {
                        this.dbtrAcct = new DbtrAcct(node);
                        continue block28;
                    }
                    case "DbtrAgt": {
                        this.dbtrAgt = new DbtrAgt(node);
                        continue block28;
                    }
                    case "CdtTrfTxInf": {
                        this.cdtTrfTxInf.add(new CdtTrfTxInf(node));
                        System.out.println("######## CdtTrfTxInf FOUND ");
                        continue block28;
                    }
                    case "PmtInfId": {
                        this.PmtInfId = element.getTextContent();
                        continue block28;
                    }
                    case "PmtMtd": {
                        this.PmtMtd = element.getTextContent();
                        continue block28;
                    }
                    case "BtchBookg": {
                        this.BtchBookg = element.getTextContent();
                        continue block28;
                    }
                    case "NbOfTxs": {
                        this.NbOfTxs = element.getTextContent();
                        continue block28;
                    }
                    case "CtrlSum": {
                        this.CtrlSum = element.getTextContent();
                        continue block28;
                    }
                    case "ReqdExctnDt": {
                        this.ReqdExctnDt = element.getTextContent();
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return "PmtInf{PmtInfId='" + this.PmtInfId + '\'' + ", PmtMtd='" + this.PmtMtd + '\'' + ", NbOfTxs='" + this.NbOfTxs + '\'' + ", CtrlSum='" + this.CtrlSum + '\'' + ", pmtTpInf=" + this.pmtTpInf + ", ReqdExctnDt='" + this.ReqdExctnDt + '\'' + ", dbtr=" + this.dbtr + ", dbtrAcct=" + this.dbtrAcct + '}';
    }

    @Override
    public void insertSelf(Element parentElement, Document document) {
        this.PmtInf = document.createElement(this.getClass().getSimpleName());
        parentElement.appendChild(this.PmtInf);
        Element PmtInfId = document.createElement("PmtInfId");
        PmtInfId.appendChild(document.createTextNode(this.PmtInfId));
        Element PmtMtd = document.createElement("PmtMtd");
        PmtMtd.appendChild(document.createTextNode(this.PmtMtd));
        Element BtchBookg = null;
        if (this.BtchBookg != null && MainFrame.showBtchBookgTag) {
            BtchBookg = document.createElement("BtchBookg");
            BtchBookg.appendChild(document.createTextNode(this.BtchBookg));
        }
        Element NbOfTxs = null;
        if (this.NbOfTxs != null) {
            NbOfTxs = document.createElement("NbOfTxs");
            NbOfTxs.appendChild(document.createTextNode(this.NbOfTxs));
        }
        Element CtrlSum = null;
        if (this.CtrlSum != null) {
            CtrlSum = document.createElement("CtrlSum");
            CtrlSum.appendChild(document.createTextNode(this.CtrlSum));
        }
        Element ReqdExctnDt = document.createElement("ReqdExctnDt");
        ReqdExctnDt.appendChild(document.createTextNode(this.ReqdExctnDt));
        this.PmtInf.appendChild(PmtInfId);
        this.PmtInf.appendChild(PmtMtd);
        if (BtchBookg != null) {
            this.PmtInf.appendChild(BtchBookg);
        }
        if (NbOfTxs != null) {
            this.PmtInf.appendChild(NbOfTxs);
        }
        if (CtrlSum != null) {
            this.PmtInf.appendChild(CtrlSum);
        }
        if (this.pmtTpInf != null) {
            this.pmtTpInf.insertSelf(this.PmtInf, document);
        }
        this.PmtInf.appendChild(ReqdExctnDt);
        this.dbtr.insertSelf(this.PmtInf, document);
        this.dbtrAcct.insertSelf(this.PmtInf, document);
        this.dbtrAgt.insertSelf(this.PmtInf, document);
        for (CdtTrfTxInf cdtTrfTxInf : this.cdtTrfTxInf) {
            cdtTrfTxInf.insertSelf(this.PmtInf, document);
        }
    }
}
