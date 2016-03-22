package com.kanomiya.steward.model.area;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.kanomiya.steward.model.assets.Assets;
import com.kanomiya.steward.model.assets.resource.IResource;
import com.kanomiya.steward.model.texture.Texture;

/**
 * @author Kanomiya
 *
 */
public class Tip implements IResource {
	protected String id, name;
	protected Texture icon;
	protected AccessType access; // optional

	public Tip(String id)
	{
		this.id = id;
		access = AccessType.ALLOW;
	}

	@Override
	public String getId()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}

	public Texture getIcon()
	{
		return icon;
	}

	public AccessType getAccessType()
	{
		return access;
	}

	@Override
	public String toString()
	{
		return getName();
	}





	public static class Serializer implements JsonSerializer<Tip>
	{
		/**
		* @inheritDoc
		*/
		@Override
		public JsonElement serialize(Tip obj, Type type, JsonSerializationContext context) {
			JsonObject jsObj = new JsonObject();

			jsObj.addProperty("id", obj.id);
			jsObj.addProperty("name", obj.name);
			jsObj.addProperty("icon", obj.icon.getId());
			if (obj.access != AccessType.ALLOW) jsObj.add("access", context.serialize(obj.access));

			return jsObj;
		}
	}

	public static class Deserializer implements JsonDeserializer<Tip>
	{
		protected Assets assets;

		public Deserializer(Assets assets) {
			this.assets = assets;
		}

		/**
		* @inheritDoc
		*/
		@Override
		public Tip deserialize(JsonElement jsElm, Type type, JsonDeserializationContext context) {
			JsonObject jsObj = jsElm.getAsJsonObject();

			Tip tip = new Tip(jsObj.get("id").getAsString());

			tip.name = jsObj.get("name").getAsString();
			tip.icon = assets.textureRegistry.get(jsObj.get("icon").getAsString());
			if (jsObj.has("access")) tip.access = context.deserialize(jsObj.get("access"), AccessType.class);

			return tip;
		}
	}

}
