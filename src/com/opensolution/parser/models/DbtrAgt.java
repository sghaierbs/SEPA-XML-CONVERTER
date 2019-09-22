/*
 * Decompiled with CFR 0.146.
 */
package com.opensolution.parser.models;

import com.opensolution.parser.models.DOMGenerator;
import com.opensolution.parser.models.FinInstnId;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DbtrAgt implements DOMGenerator {
    private Node node;
    private Element DbtrAgt;
    private FinInstnId finInstnId;

    public DbtrAgt(Node node) {
        this.node = node;
        this.createChildNode();
    }

    private void createChildNode() {
        NodeList nodeList = this.node.getChildNodes();
        try {
            for (int index = 0; index < nodeList.getLength(); ++index) {
                Node node = nodeList.item(index);
                if (node.getNodeType() != 1) continue;
                Element element = (Element)node;
                switch (element.getTagName()) {
                    case "FinInstnId": {
                        this.finInstnId = new FinInstnId(node,false, true);
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
        this.DbtrAgt = document.createElement(this.getClass().getSimpleName());
        parentElement.appendChild(this.DbtrAgt);
        this.finInstnId.insertSelf(this.DbtrAgt, document);
    }
}
