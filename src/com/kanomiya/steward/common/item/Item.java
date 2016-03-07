package com.kanomiya.steward.common.item;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author Kanomiya
 *
 */
public class Item {

	public String name;
	public ArrayList<I> attributes;

	public Item()
	{

	}

	public static Item documentToItem(Document doc)
	{
		doc.getElementsByTagName("name").item(0).getTextContent();

		NodeList attrNodes = doc.getElementsByTagName("attributes").item(0).getChildNodes();
		int attrNodesLen = attrNodes.getLength();

		for (int i=0; i<attrNodesLen; i++)
		{
			Node node = attrNodes.item(i);
			node.getAttributes().getNamedItem("name");

		}

		doc.getElementsByTagName("icon").item(0).getTextContent();
	}

}
