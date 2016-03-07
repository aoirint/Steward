package com.kanomiya.steward.common.item;

import java.util.HashMap;

import com.google.common.collect.Maps;
import com.kanomiya.steward.common.attribute.Attribute;

/**
 * @author Kanomiya
 *
 */
public class AttributeRegistry {

	public HashMap<String, Class<Attribute>> attributeClasses = Maps.newHashMap();

	public AttributeRegistry() {  }

	public Attribute createInstance(String name)
	{
		return 
	}



}
