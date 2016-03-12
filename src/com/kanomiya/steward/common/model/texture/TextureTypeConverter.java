package com.kanomiya.steward.common.model.texture;

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
public class TextureTypeConverter implements JsonSerializer<TextureType>, JsonDeserializer<TextureType> {

	/**
	* @inheritDoc
	*/
	@Override
	public JsonElement serialize(TextureType textureType, Type type, JsonSerializationContext jsc) {
		return new JsonPrimitive(textureType.getId());
	}

	/**
	* @inheritDoc
	*/
	@Override
	public TextureType deserialize(JsonElement jsElm, Type type, JsonDeserializationContext jdc) throws JsonParseException {
		return TextureType.getFromId(jsElm.getAsString()); // TODO: Illegal Argument Exception
	}




}
