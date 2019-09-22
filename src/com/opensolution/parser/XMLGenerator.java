/*
 * Decompiled with CFR 0.146.
 */
package com.opensolution.parser;

import com.opensolution.parser.models.DOMGenerator;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class XMLGenerator {
    private DOMGenerator domGenerator;

    public XMLGenerator(DOMGenerator domGenerator) {
        this.domGenerator = domGenerator;
        this.generate();
    }

    private void generate() {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            Element rootElement = doc.createElement("Document");
            doc.appendChild(rootElement);
            Attr xmlns = doc.createAttribute("xmlns");
            xmlns.setValue("urn:iso:std:iso:20022:tech:xsd:pain.001.001.03");
            rootElement.setAttributeNode(xmlns);
            Attr xsi = doc.createAttribute("xmlns:xsi");
            xsi.setValue("http://www.w3.org/2001/XMLSchema-instance");
            rootElement.setAttributeNode(xsi);
            this.domGenerator.insertSelf(rootElement, doc);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty("indent", "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            doc.setXmlStandalone(true);
            DOMSource source = new DOMSource(doc);
            System.out.println("Result" + source.toString());
            File file = new File(XMLParser.directoryPath + "/" + XMLParser.filename);
            StreamResult result = new StreamResult(file);
            System.out.println("Result" + result.toString());
            transformer.transform(source, result);
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}
