package com.kanomiya.steward.common.model.item;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.kanomiya.steward.common.model.assets.Assets;

/**
 * @author Kanomiya
 *
 */
public class ItemConverter implements JsonSerializer<Item>, JsonDeserializer<Item> {

	protected Assets assets;

	public ItemConverter(Assets assets)
	{
		this.assets = assets;
	}

	/**
	* @inheritDoc
	*/
	@Override
	public JsonElement serialize(Item item, Type type, JsonSerializationContext context)
	{
		JsonObject jsObj = new JsonObject();

		jsObj.addProperty("id", item.id);
		jsObj.addProperty("unlocalizedName", item.unlocalizedName);
		jsObj.addProperty("icon", item.icon.getId());
		jsObj.addProperty("weight", item.weight);

		return jsObj;
	}

	/**
	* @inheritDoc
	*/
	@Override
	public Item deserialize(JsonElement jsElm, Type type, JsonDeserializationContext context) throws JsonParseException
	{
		JsonObject jsObj = jsElm.getAsJsonObject();

		Item item = new Item();

		item.id = jsObj.get("id").getAsString();
		item.unlocalizedName = jsObj.get("unlocalizedName").getAsString();
		item.icon = assets.textureRegistry.get(jsObj.get("icon"));
		item.weight = jsObj.get("weight").getAsDouble();

		return item;
	}


}
