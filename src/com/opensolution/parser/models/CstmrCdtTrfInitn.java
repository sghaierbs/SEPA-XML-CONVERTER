/*
 * Decompiled with CFR 0.146.
 */
package com.opensolution.parser.models;

import com.opensolution.parser.models.DOMGenerator;
import com.opensolution.parser.models.GrpHdr;
import com.opensolution.parser.models.PmtInf;
import java.util.ArrayList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class CstmrCdtTrfInitn
implements DOMGenerator {
    private Node node;
    private Element CstmrCdtTrfInitn;
    private GrpHdr grpHdr;
    private ArrayList<PmtInf> pmtInf;

    public CstmrCdtTrfInitn(Node node) {
        this.node = node;
        this.pmtInf = new ArrayList();
        this.createChildNode();
    }

    private void createChildNode() {
        NodeList nodeList = this.node.getChildNodes();
        try {
            for (int index = 0; index < nodeList.getLength(); ++index) {
                Node node = nodeList.item(index);
                if (node.getNodeType() != 1) continue;
                Element element = (Element)node;
                if (element.getTagName() == "GrpHdr") {
                    this.grpHdr = new GrpHdr(node);
                }
                if (element.getTagName() != "PmtInf") continue;
                this.pmtInf.add(new PmtInf(node));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return "CstmrCdtTrfInitn{grpHdr=" + this.grpHdr + ", pmtInf=" + this.pmtInf + '}';
    }

    @Override
    public void insertSelf(Element parentElement, Document document) {
        this.CstmrCdtTrfInitn = document.createElement(this.getClass().getSimpleName());
        parentElement.appendChild(this.CstmrCdtTrfInitn);
        this.grpHdr.insertSelf(this.CstmrCdtTrfInitn, document);
        for (PmtInf pmtInf : this.pmtInf) {
            pmtInf.insertSelf(this.CstmrCdtTrfInitn, document);
        }
    }
}
