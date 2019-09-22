/*
 * Decompiled with CFR 0.146.
 */
package com.opensolution.parser.models;

import com.opensolution.parser.XMLParser;
import com.opensolution.parser.models.Amt;
import com.opensolution.parser.models.Cdtr;
import com.opensolution.parser.models.CdtrAcct;
import com.opensolution.parser.models.CdtrAgt;
import com.opensolution.parser.models.DOMGenerator;
import com.opensolution.parser.models.PmtId;
import com.opensolution.parser.models.Purp;
import com.opensolution.parser.models.RmtInf;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class CdtTrfTxInf
implements DOMGenerator {
    private Node node;
    private Element CdtTrfTxInf;
    private PmtId pmtId;
    private Amt amt;
    private String ChrgBr;
    private CdtrAgt cdtrAgt;
    private Cdtr cdtr;
    private CdtrAcct cdtrAcct;
    private Purp purp;
    private RmtInf rmtInf;

    public CdtTrfTxInf(Node node) {
        this.node = node;
        this.createChildNode();
    }

    private void createChildNode() {
        NodeList nodeList = this.node.getChildNodes();
        try {
            block20 : for (int index = 0; index < nodeList.getLength(); ++index) {
                Node node = nodeList.item(index);
                if (node.getNodeType() != 1) continue;
                Element element = (Element)node;
                switch (element.getTagName()) {
                    case "PmtId": {
                        this.pmtId = new PmtId(node);
                        continue block20;
                    }
                    case "Amt": {
                        this.amt = new Amt(node);
                        continue block20;
                    }
                    case "CdtrAgt": {
                        this.cdtrAgt = new CdtrAgt(node);
                        continue block20;
                    }
                    case "Cdtr": {
                        this.cdtr = new Cdtr(node);
                        continue block20;
                    }
                    case "CdtrAcct": {
                        this.cdtrAcct = new CdtrAcct(node);
                        continue block20;
                    }
                    case "RmtInf": {
                        this.rmtInf = new RmtInf(node);
                        continue block20;
                    }
                    case "ChrgBr": {
                        this.ChrgBr = element.getTextContent();
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return "CdtTrfTxInf{CdtTrfTxInf=" + this.CdtTrfTxInf + ", pmtId=" + this.pmtId + ", amt=" + this.amt + ", ChrgBr='" + this.ChrgBr + '\'' + ", cdtrAgt=" + this.cdtrAgt + ", cdtr=" + this.cdtr + ", cdtrAcct=" + this.cdtrAcct + ", purp=" + this.purp + ", rmtInf=" + this.rmtInf + '}';
    }

    @Override
    public void insertSelf(Element parentElement, Document document) {
        this.CdtTrfTxInf = document.createElement(this.getClass().getSimpleName());
        parentElement.appendChild(this.CdtTrfTxInf);
        Element ChrgBr = null;
        if (this.ChrgBr != null) {
            ChrgBr = document.createElement("ChrgBr");
            ChrgBr.appendChild(document.createTextNode(this.ChrgBr));
        } else if (XMLParser.transactionPayer.length() != 0) {
            ChrgBr = document.createElement("ChrgBr");
            ChrgBr.appendChild(document.createTextNode(XMLParser.transactionPayer));
        }
        this.pmtId.insertSelf(this.CdtTrfTxInf, document);
        this.amt.insertSelf(this.CdtTrfTxInf, document);
        if (ChrgBr != null) {
            this.CdtTrfTxInf.appendChild(ChrgBr);
        }
        if (this.cdtrAgt != null) {
            this.cdtrAgt.insertSelf(this.CdtTrfTxInf, document);
        }
        this.cdtr.insertSelf(this.CdtTrfTxInf, document);
        if (this.cdtrAcct != null) {
            this.cdtrAcct.insertSelf(this.CdtTrfTxInf, document);
        }
        if (this.purp != null) {
            this.purp.insertSelf(this.CdtTrfTxInf, document);
        } else if (XMLParser.paymentReason.length() != 0) {
            this.purp = new Purp(this.node);
            this.purp.insertSelf(this.CdtTrfTxInf, document);
        }
        if (this.rmtInf != null) {
            this.rmtInf.insertSelf(this.CdtTrfTxInf, document);
        }
    }
}
