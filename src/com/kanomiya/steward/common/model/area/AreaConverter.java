package com.kanomiya.steward.common.model.area;

import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;
import com.kanomiya.steward.common.model.assets.Assets;
import com.kanomiya.steward.common.model.script.Script;
import com.kanomiya.steward.common.model.script.ScriptEventType;

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
	public JsonElement serialize(Area area, Type type, JsonSerializationContext context) {

		JsonObject jsObj = new JsonObject();

		jsObj.addProperty("id", area.getId());
		jsObj.addProperty("name", area.getName());

		int width = area.getWidth();
		int height = area.getHeight();

		jsObj.addProperty("width", width);
		jsObj.addProperty("height", height);

		if (area.hasBackground()) jsObj.addProperty("background", area.background.getId());

		JsonArray tips = new JsonArray();

		for (int y=0; y<height; y++)
		{
			JsonArray tipLine = new JsonArray();

			for (int x=0; x<width; x++)
			{
				if (area.tipExists(x, y))
				{
					Tip tip = area.getTip(x, y);

					tipLine.add(tip.getId());
				}
			}

			tips.add(tipLine);
		}

		jsObj.add("tips", tips);



		if (area.scripts != null)
		{
			Iterator<Entry<ScriptEventType, Script>> itr = area.scripts.entrySet().iterator();

			JsonObject scriptObj = new JsonObject();

			while (itr.hasNext())
			{
				Entry<ScriptEventType, Script> entry = itr.next();

				scriptObj.add(entry.getKey().getId(), context.serialize(entry.getValue()));
			}

			jsObj.add("scripts", scriptObj);
		}

		return jsObj;
	}

	/**
	* @inheritDoc
	*/
	@Override
	public Area deserialize(JsonElement jsElm, Type type, JsonDeserializationContext context) throws JsonParseException {

		if (jsElm.isJsonPrimitive()) // for id
		{
			return assets.getArea(jsElm.getAsString());
		}

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


		Map<ScriptEventType, Script> scripts = null;
		Type sceventScriptMap = new TypeToken<Map<ScriptEventType, Script>>(){}.getType();
		if (jsObj.has("scripts")) scripts = context.deserialize(jsObj.get("scripts"), sceventScriptMap);


		Area area = new Area(id, name, width, height, tips, assets);

		area.scripts = scripts;

		if (jsObj.has("background")) area.setBackground(assets.textureRegistry.get(jsObj.get("background").getAsString()));



		return area;
	}


}
