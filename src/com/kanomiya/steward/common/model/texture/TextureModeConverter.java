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
public class TextureModeConverter implements JsonSerializer<TextureMode>, JsonDeserializer<TextureMode> {

	/**
	* @inheritDoc
	*/
	@Override
	public JsonElement serialize(TextureMode textureMode, Type type, JsonSerializationContext jsc) {
		return new JsonPrimitive(textureMode.getId());
	}

	/**
	* @inheritDoc
	*/
	@Override
	public TextureMode deserialize(JsonElement jsElm, Type type, JsonDeserializationContext jdc) throws JsonParseException {
		return TextureMode.getFromId(jsElm.getAsString()); // TODO: Illegal Argument Exception
	}




}
