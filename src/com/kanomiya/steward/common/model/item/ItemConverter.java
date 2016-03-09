package com.kanomiya.steward.common.model.item;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * @author Kanomiya
 *
 */
public class ItemConverter implements JsonSerializer<Item>, JsonDeserializer<Item> {

	/**
	* @inheritDoc
	*/
	@Override
	public JsonElement serialize(Item item, Type type, JsonSerializationContext jsc) {

		return null;
	}

	/**
	* @inheritDoc
	*/
	@Override
	public Item deserialize(JsonElement jsElm, Type type, JsonDeserializationContext jdc) throws JsonParseException {

		return null;
	}


}
