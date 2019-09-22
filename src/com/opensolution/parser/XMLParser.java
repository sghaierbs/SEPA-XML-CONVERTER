/*
 * Decompiled with CFR 0.146.
 */
package com.opensolution.parser;

import com.opensolution.parser.models.CstmrCdtTrfInitn;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class XMLParser {
    private File sourceFile;
    private CstmrCdtTrfInitn cstmrCdtTrfInitn;
    public static String defaultCountryCode;
    public static String filename;
    public static String directoryPath;
    public static String priority;
    public static String paymentReason;
    public static String financialinstitutionName;
    public static String transactionPayer;
    public static String insrtId;

    public XMLParser(File sourceFile, String filename, String defaultCountryCode, String insrtId, String priority, String paymentReason, String financialinstitutionName, String transactionPayer, String directoryPath) {
        this.sourceFile = sourceFile;
        XMLParser.filename = filename;
        XMLParser.directoryPath = directoryPath;
        XMLParser.defaultCountryCode = defaultCountryCode;
        XMLParser.paymentReason = paymentReason;
        XMLParser.financialinstitutionName = financialinstitutionName;
        XMLParser.transactionPayer = transactionPayer;
        XMLParser.priority = priority;
        XMLParser.insrtId = insrtId;
        this.parseFile();
    }

    private void parseFile() {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(this.sourceFile);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("CstmrCdtTrfInitn");
            System.out.println("---------------------------- " + nodeList.getLength());
            for (int index = 0; index < nodeList.getLength(); ++index) {
                Node node = nodeList.item(index);
                System.out.println("Root length " + nodeList.getLength());
                System.out.println("\nCurrent Element :" + node.getNodeName());
                this.cstmrCdtTrfInitn = new CstmrCdtTrfInitn(node);
                System.out.println(this.cstmrCdtTrfInitn.toString());
            }
            new XMLGenerator(this.cstmrCdtTrfInitn);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
