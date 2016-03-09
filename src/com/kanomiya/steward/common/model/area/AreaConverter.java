package com.kanomiya.steward.common.model.area;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
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
public class AreaConverter implements JsonSerializer<Area>, JsonDeserializer<Area> {

	protected Assets assets;

	public AreaConverter(Assets assets)
	{
		this.assets = assets;
	}

	/**
	* @inheritDoc
	*/
	@Override
	public JsonElement serialize(Area area, Type type, JsonSerializationContext jsc) {

		JsonObject jsObj = new JsonObject();

		jsObj.addProperty("id", area.getId());
		jsObj.addProperty("name", area.getName());

		int width = area.getWidth();
		int height = area.getHeight();

		jsObj.addProperty("width", width);
		jsObj.addProperty("height", height);

		JsonArray tips = new JsonArray();

		for (int y=0; y<height; y++)
		{
			JsonArray tileLine = new JsonArray();

			for (int x=0; x<width; x++)
			{
				if (area.tipExists(x, y)) tileLine.add(area.getTip(x, y).getId());
			}

			tips.add(tileLine);
		}

		jsObj.add("tips", tips);

		return jsObj;
	}

	/**
	* @inheritDoc
	*/
	@Override
	public Area deserialize(JsonElement jsElm, Type type, JsonDeserializationContext jdc) throws JsonParseException {

		JsonObject jsObj = jsElm.getAsJsonObject();

		String id = jsObj.get("id").getAsString();
		String name = jsObj.get("name").getAsString();

		int width = jsObj.get("width").getAsInt();
		int height = jsObj.get("height").getAsInt();

		JsonArray tileArray = jsObj.getAsJsonArray("tips");
		Tip[][] tips = new Tip[width][height];

		for (int y=0; y<height; y++)
		{
			if (tileArray.size() <= y) continue;
			JsonArray tileLine = tileArray.get(y).getAsJsonArray();

			for (int x=0; x<width; x++)
			{
				if (tileLine.size() <= x) continue;
				JsonElement tile = tileLine.get(x);

				if (! tile.isJsonObject())
				{
					tips[x][y] = assets.getTip(tile.getAsString());
				}
			}
		}

		return new Area(id, name, width, height, tips);
	}


}
