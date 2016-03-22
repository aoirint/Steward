package com.kanomiya.steward.model.item;

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
public class ItemStackConverter implements JsonSerializer<ItemStack>, JsonDeserializer<ItemStack> {

	protected Assets assets;

	public ItemStackConverter(Assets assets)
	{
		this.assets = assets;
	}

	/**
	* @inheritDoc
	*/
	@Override
	public JsonElement serialize(ItemStack itemStack, Type type, JsonSerializationContext context)
	{
		JsonObject jsObj = new JsonObject();

		jsObj.addProperty("id", itemStack.item.getId());
		jsObj.addProperty("stackSize", itemStack.stackSize);

		return jsObj;
	}

	/**
	* @inheritDoc
	*/
	@Override
	public ItemStack deserialize(JsonElement jsElm, Type type, JsonDeserializationContext context) throws JsonParseException
	{
		JsonObject jsObj = jsElm.getAsJsonObject();

		String id = jsObj.get("id").getAsString();
		int stackSize = jsObj.get("stackSize").getAsInt();

		return new ItemStack(assets.getItem(id), stackSize);
	}


}
