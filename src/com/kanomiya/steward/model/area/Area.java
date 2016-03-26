package com.kanomiya.steward.model.area;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;
import com.kanomiya.steward.model.assets.Assets;
import com.kanomiya.steward.model.assets.resource.IResource;
import com.kanomiya.steward.model.event.Event;
import com.kanomiya.steward.model.script.IScriptOwner;
import com.kanomiya.steward.model.script.Script;
import com.kanomiya.steward.model.script.ScriptEventType;
import com.kanomiya.steward.model.texture.Texture;




/**
 * @author Kanomiya
 *
 */
public class Area implements IResource, IScriptOwner, Cloneable {
	protected String id;
	protected Assets assets;

	protected String unlocalizedName;
	protected Texture background;

	protected int width, height;
	protected Tip[][] tips;
	protected List<Event> eventList;

	protected Map<ScriptEventType, Script> scripts;



	public Area(String id, Assets assets)
	{
		this.id = id;
		this.assets = assets;

		unlocalizedName = "vocabulary.unknown";
		tips = new Tip[0][0];
		eventList = Lists.newArrayList();
	}



	/**
	 * @return id
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * @param id セットする id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return unlocalizedName
	 */
	public String getUnlocalizedName() {
		return unlocalizedName;
	}

	/**
	 * @param unlocalizedName セットする unlocalizedName
	 */
	public void setUnlocalizedName(String unlocalizedName) {
		this.unlocalizedName = unlocalizedName;
	}



	public boolean hasBackground()
	{
		return (background != null);
	}

	/**
	 * @return background
	 */
	public Texture getBackground() {
		return background;
	}

	/**
	 * @param background セットする background
	 */
	public void setBackground(Texture background) {
		this.background = background;
	}






	public boolean inArea(int x, int y)
	{
		if (x < 0 || width <= x || y < 0 || height <= y) return false;
		return true;
	}

	public boolean tipExists(int x, int y)
	{
		if (! inArea(x, y)) return false;
		return (tips[x][y] != null);
	}

	public void setSize(int width, int height)
	{
		this.width = width;
		this.height = height;

		if (tips == null) tips = new Tip[width][height];
		else
		{
			if (tips.length != width) tips = Arrays.copyOf(tips, width);

			for (int x=0; x<width; x++)
			{
				if (tips[x] == null) tips[x] = new Tip[height];
				if (tips[x].length != height) tips[x] = Arrays.copyOf(tips[x], height);
			}
		}
	}

	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	}

	public Tip getTip(int x, int y)
	{
		if (! tipExists(x, y)) return null;
		return tips[x][y];
	}

	public void setTip(Tip tip, int x, int y)
	{
		tips[x][y] = tip;
	}






	/**
	 * @param x
	 * @param y
	 * @return
	 */
	public List<Event> getEvents(int x, int y)
	{
		List<Event> result = Lists.newArrayList();

		for (Event event: eventList)
		{
			if (x == event.x && y == event.y)
			{
				result.add(event);
			}
		}

		return result;
	}

	public AccessType getAccessType(int x, int y)
	{
		if (tips[x][y].getAccessType() == AccessType.DENY) return AccessType.DENY;

		for (Event event: eventList)
		{
			if (x == event.x && y == event.y && event.getAccessType() == AccessType.DENY) return AccessType.DENY;
		}

		return AccessType.ALLOW;
	}


	/**
	 *
	 * @param event
	 */
	public void setEvent(Event event, boolean flagEnter)
	{
		// Area area = event.area;

		if (event.area != null)
		{
			event.area.removeEvent(event);
		}

		event.area = this;
		if (! eventList.contains(event)) eventList.add(event);

		if (assets.isInited() && flagEnter)
		{
			assets.exec(this, event, ScriptEventType.ONENTERED);
		}
	}

	/**
	 * @param event
	 */
	public void removeEvent(Event event)
	{
		if (assets.isInited())
		{
			assets.exec(this, event, ScriptEventType.ONEXITED);
		}

		eventList.remove(event);
	}



	/**
	 * @return
	 */
	public List<Event> eventList() {
		return eventList;
	}



	@Override
	public boolean hasScript(ScriptEventType eventType)
	{
		if (scripts == null) return false;
		return scripts.containsKey(eventType);
	}

	@Override
	public Script getScript(ScriptEventType eventType)
	{
		if (scripts == null) return null;
		return scripts.get(eventType);
	}

	/**
	* @inheritDoc
	*/
	@Override
	public void setScript(ScriptEventType type, Script script)
	{
		if (scripts == null) scripts = Maps.newHashMap();
		scripts.put(type, script);
	}


	@Override
	public String toString()
	{
		return id;
	}







	public static class Serializer implements JsonSerializer<Area>
	{
		/**
		* @inheritDoc
		*/
		@Override
		public JsonElement serialize(Area obj, Type type, JsonSerializationContext context) {

			JsonObject jsObj = new JsonObject();

			jsObj.addProperty("id", obj.id);
			if (! obj.unlocalizedName.equals(obj.id)) jsObj.addProperty("unlocalizedName", obj.unlocalizedName);


			if (obj.hasBackground()) jsObj.addProperty("background", obj.background.getId());

			if (obj.scripts != null)
			{
				Iterator<Entry<ScriptEventType, Script>> itr = obj.scripts.entrySet().iterator();

				JsonObject jsScripts = new JsonObject();

				while (itr.hasNext())
				{
					Entry<ScriptEventType, Script> entry = itr.next();

					jsScripts.add(entry.getKey().getId(), context.serialize(entry.getValue()));
				}

				jsObj.add("scripts", jsScripts);
			}


			jsObj.addProperty("width", obj.width);
			jsObj.addProperty("height", obj.height);

			JsonArray jsTips = new JsonArray();

			for (int y=0; y<obj.height; y++)
			{
				JsonArray jsTipLine = new JsonArray();

				for (int x=0; x<obj.width; x++)
				{
					if (obj.tipExists(x, y))
					{
						Tip tip = obj.getTip(x, y);

						jsTipLine.add(tip.getId());
					}
				}

				jsTips.add(jsTipLine);
			}

			jsObj.add("tips", jsTips);


			return jsObj;
		}
	}

	public static class Deserializer implements JsonDeserializer<Area>
	{
		protected Assets assets;

		public Deserializer(Assets assets) {
			this.assets = assets;
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
			Area area = new Area(id, assets);

			area.unlocalizedName = id;
			if (jsObj.has("unlocalizedName")) area.unlocalizedName = jsObj.get("unlocalizedName").getAsString();

			area.width = jsObj.get("width").getAsInt();
			area.height = jsObj.get("height").getAsInt();


			area.scripts = null;
			Type sceventScriptMap = new TypeToken<Map<ScriptEventType, Script>>(){}.getType();
			if (jsObj.has("scripts")) area.scripts = context.deserialize(jsObj.get("scripts"), sceventScriptMap);


			if (jsObj.has("background")) area.setBackground(assets.getTexture(jsObj.get("background").getAsString()));


			JsonArray jsTips = jsObj.getAsJsonArray("tips");
			area.tips = new Tip[area.width][area.height];

			for (int y=0; y<area.height; y++)
			{
				if (jsTips.size() <= y) continue;
				JsonArray jsTipLine = jsTips.get(y).getAsJsonArray();

				for (int x=0; x<area.width; x++)
				{
					if (jsTipLine.size() <= x) continue;
					JsonElement jsTip = jsTipLine.get(x);

					if (! jsTip.isJsonObject())
					{
						area.tips[x][y] = assets.getTip(jsTip.getAsString());
					}
				}
			}



			return area;
		}
	}








}
