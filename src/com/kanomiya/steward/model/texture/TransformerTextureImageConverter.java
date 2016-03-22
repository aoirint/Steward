package com.kanomiya.steward.model.texture;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.kanomiya.steward.model.assets.Assets;

/**
 * @author Kanomiya
 *
 */
public class TransformerTextureImageConverter implements JsonSerializer<TransformerTextureImage>, JsonDeserializer<TransformerTextureImage> {

	protected Assets assets;

	public TransformerTextureImageConverter(Assets assets) {
		this.assets = assets;
	}


	/**
	* @inheritDoc
	*/
	@Override
	public JsonElement serialize(TransformerTextureImage obj, Type type, JsonSerializationContext context) {
		JsonObject jsObj = new JsonObject();

		jsObj.addProperty("id", obj.id);

		if (! obj.id.equals(obj.base.getId()))
		{
			jsObj.addProperty("base", obj.base.getId());
		}

		if (obj.imageX != 0) jsObj.addProperty("imageX", obj.imageX);
		if (obj.imageY != 0) jsObj.addProperty("imageY", obj.imageY);

		if (! obj.autoSize)
		{
			jsObj.addProperty("width", obj.width);
			jsObj.addProperty("height", obj.height);
		} else
		{
			jsObj.addProperty("autoSize", true);
		}

		jsObj.addProperty("rotation", obj.rotation);

		return jsObj;
	}

	/**
	* @inheritDoc
	*/
	@Override
	public TransformerTextureImage deserialize(JsonElement jsElm, Type type, JsonDeserializationContext context) throws JsonParseException {

		JsonObject jsObj = jsElm.getAsJsonObject();

		String id = jsObj.get("id").getAsString();
		String base = (jsObj.has("base")) ? jsObj.get("base").getAsString() : id;

		TransformerTextureImage obj = new TransformerTextureImage(id, assets.texImageRegistry.get(base)); // VELIF 上書き

		if (jsObj.has("imageX")) obj.imageX = jsObj.get("imageX").getAsInt();
		if (jsObj.has("imageY")) obj.imageY = jsObj.get("imageY").getAsInt();

		if (jsObj.has("autoSize")) obj.autoSize = jsObj.get("autoSize").getAsBoolean();

		if (! obj.autoSize)
		{
			if (jsObj.has("width")) obj.width = jsObj.get("width").getAsInt();
			if (jsObj.has("height")) obj.height = jsObj.get("height").getAsInt();
		}

		if (jsObj.has("rotation")) obj.rotation = jsObj.get("rotation").getAsDouble();

		return obj;
	}




}
