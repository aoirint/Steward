package com.kanomiya.steward.common.item;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author Kanomiya
 *
 */
public class Item {

	protected String id, name;

	public Item()
	{

	}

	public void setName(String name)
	{
		this.name = name;
	}

	public static Item nodeToItem(Node node) throws XPathExpressionException
	{
		Item item = new Item();

		XPathFactory xfact = XPathFactory.newInstance();
		XPath xpath = xfact.newXPath();

		item.setName(xpath.evaluate("/name", node));
		NodeList attrs = (NodeList) xpath.evaluate("/attributes/*", node, XPathConstants.NODESET);

		int attrsLen = attrs.getLength();

		for (int i=0; i<attrsLen; i++)
		{
			Node atNode = attrs.item(i);
			String atNodeName = atNode.getNodeName();

			switch (atNodeName)
			{
			case "food":
				double amount = Double.parseDouble(atNode.getAttributes().getNamedItem("amount").getNodeValue());

				break;
			}

		}



		return item;
	}

}
