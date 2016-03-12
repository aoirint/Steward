package com.kanomiya.steward.common.model.icon;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * @author Kanomiya
 *
 */
public class IconConverter implements JsonSerializer<Icon>, JsonDeserializer<Icon> {

	/**
	* @inheritDoc
	*/
	@Override
	public JsonElement serialize(Icon icon, Type type, JsonSerializationContext context) {
		JsonObject jsObj = new JsonObject();

		jsObj.add("src", context.serialize(icon.src));
		if (icon.mode != IconMode.STATIC) jsObj.add("mode", context.serialize(icon.mode));
		if (icon.type != IconType.front) jsObj.add("type", context.serialize(icon.type));

		if (icon.mode.requireInterval()) jsObj.addProperty("interval", icon.interval);

		return jsObj;
	}

	/**
	* @inheritDoc
	*/
	@Override
	public Icon deserialize(JsonElement jsElm, Type type, JsonDeserializationContext context) throws JsonParseException {

		JsonObject jsObj = jsElm.getAsJsonObject();

		String[] src = context.deserialize(jsObj.get("src"), String[].class);
		IconMode mode = IconMode.STATIC;
		if (jsObj.has("mode")) mode = context.deserialize(jsObj.get("mode"), IconMode.class);

		IconType itype = IconType.front;
		if (jsObj.has("type")) itype = context.deserialize(jsObj.get("type"), IconType.class);

		int interval = 1000;
		if (mode.requireInterval() && jsObj.has("interval")) interval = jsObj.get("interval").getAsInt();

		return new Icon(src, mode, itype, null, interval);
	}




}
