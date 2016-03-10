package com.kanomiya.steward.common.model.icon;

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
public class IconModeConverter implements JsonSerializer<IconMode>, JsonDeserializer<IconMode> {

	/**
	* @inheritDoc
	*/
	@Override
	public JsonElement serialize(IconMode iconMode, Type type, JsonSerializationContext jsc) {
		return new JsonPrimitive(iconMode.getId());
	}

	/**
	* @inheritDoc
	*/
	@Override
	public IconMode deserialize(JsonElement jsElm, Type type, JsonDeserializationContext jdc) throws JsonParseException {
		return IconMode.getFromId(jsElm.getAsString()); // TODO: Illegal Argument Exception
	}




}
