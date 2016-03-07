package com.kanomiya.steward.common.assets;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;


/**
 * @author Kanomiya
 *
 */
public class AssetsFactory {

	protected static final ExtensionFilter xmlFilter = new ExtensionFilter("xml");

	protected static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
	protected static final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";


	protected AssetsFactory() {  }

	public static AssetsFactory newInstance()
	{
		AssetsFactory afactory = new AssetsFactory();

		DocumentBuilderFactory dfactory = DocumentBuilderFactory.newInstance();
		dfactory.setNamespaceAware(true);
		dfactory.setValidating(true);

		dfactory.setAttribute(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);

		try {
			afactory.setDocumentBuilder(dfactory.newDocumentBuilder());
		} catch (ParserConfigurationException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return afactory;
	}

	protected DocumentBuilder builder;

	protected void setDocumentBuilder(DocumentBuilder builder)
	{
		this.builder = builder;
	}




	public Assets newAssets()
	{
		Assets assets = new Assets();

		try {
			getMixedDocument(new File("assets/item"));
			// TODO: mapファイルの準備 load(assets, new File("assets/map"));

		} catch (SAXException | IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return assets;
	}

	protected Document getMixedDocument(File dir) throws SAXException, IOException
	{
		File[] xmls = dir.listFiles(xmlFilter);

		Document root = builder.newDocument();

		for (File f: xmls)
		{
			builder.reset();

			Node node = builder.parse(f); // Document -> Node

			node = root.importNode(node, true); // nodeはrootをownerDocumentに持つ
			root.appendChild(node); // rootはnodeを子に持つ
		}

		return root;
	}

}

