package com.kanomiya.steward.common.item;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * @author Kanomiya
 *
 */
public class ForceTypeConverter implements JsonSerializer<ForceType>, JsonDeserializer<ForceType> {

	/**
	* @inheritDoc
	*/
	@Override
	public JsonElement serialize(ForceType forceType, Type type, JsonSerializationContext jsc) {
		return new JsonPrimitive(forceType.name());
	}

	/**
	* @inheritDoc
	*/
	@Override
	public ForceType deserialize(JsonElement jsElm, Type type, JsonDeserializationContext jdc) throws JsonParseException {
		return ForceType.valueOf(jsElm.getAsString()); // TODO: Illegal Argument Exception
	}


}
