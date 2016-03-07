package com.kanomiya.steward;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{

		SchemaFactory schfactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

		schfactory.newSchema()


		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();

			File file = new File("");

			builder.parse(file);

		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}



	}

}
