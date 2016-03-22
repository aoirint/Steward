package com.kanomiya.steward.common.model.event;

import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;
import com.kanomiya.steward.common.model.area.AccessType;
import com.kanomiya.steward.common.model.area.Area;
import com.kanomiya.steward.common.model.assets.Assets;
import com.kanomiya.steward.common.model.item.Inventory;
import com.kanomiya.steward.common.model.script.Script;
import com.kanomiya.steward.common.model.script.ScriptEventType;
import com.kanomiya.steward.common.model.texture.Texture;

/**
 * @author Kanomiya
 *
 */
public class EventConverter implements JsonDeserializer<Event>, JsonSerializer<Event> {

	protected Assets assets;

	public EventConverter(Assets assets)
	{
		this.assets = assets;
	}

	/**
	* @inheritDoc
	*/
	@Override
	public JsonElement serialize(Event event, Type type, JsonSerializationContext context)
	{
		JsonObject jsObj = new JsonObject();

		jsObj.addProperty("id", event.getId());
		jsObj.addProperty("area", event.getArea().getId());
		jsObj.addProperty("x", event.x);
		jsObj.addProperty("y", event.y);
		if (! event.visible) jsObj.addProperty("visible", event.visible);

		if (event.direction != Direction.SOUTH) jsObj.add("direction", context.serialize(event.direction));
		if (event.walkState != WalkState.UPRIGHT) jsObj.add("walkState", context.serialize(event.walkState));
		if (event.access != AccessType.ALLOW) jsObj.add("access", context.serialize(event.access));

		jsObj.addProperty("icon", event.getIcon().getId());

		if (event.hasInventory()) jsObj.add("inventory", context.serialize(event.getInventory()));

		if (Player.isPlayerId(event.getId()))
		{
			Player player = (Player) event;

			// if (player.mode != PlayerMode.NORMAL) jsObj.add("mode", context.serialize(player.mode));
			if (! player.debug) jsObj.addProperty("debugMode", player.debug);

		}

		if (event.scripts != null)
		{
			Iterator<Entry<ScriptEventType, Script>> itr = event.scripts.entrySet().iterator();

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
	public Event deserialize(JsonElement jsElm, Type type, JsonDeserializationContext context) throws JsonParseException
	{
		JsonObject jsObj = jsElm.getAsJsonObject();

		String id = jsObj.get("id").getAsString();
		Area area = assets.getArea(jsObj.get("area").getAsString());
		int x = jsObj.get("x").getAsInt();
		int y = jsObj.get("y").getAsInt();
		boolean visible = true;
		if (jsObj.has("visible")) jsObj.get("visible").getAsBoolean();

		Direction direction = Direction.SOUTH;
		if (jsObj.has("direction")) direction = context.deserialize(jsObj.get("direction"), Direction.class);

		WalkState walkState = WalkState.UPRIGHT;
		if (jsObj.has("walkState")) walkState = context.deserialize(jsObj.get("walkState"), WalkState.class);

		AccessType access = AccessType.ALLOW;
		if (jsObj.has("access")) access = context.deserialize(jsObj.get("access"), AccessType.class);

		Texture texture = assets.textureRegistry.get(jsObj.get("icon").getAsString());

		Inventory inventory = null;
		if (jsObj.has("inventory")) inventory = context.deserialize(jsObj.get("inventory"), Inventory.class);

		Map<ScriptEventType, Script> scripts = null;
		Type sceventScriptMap = new TypeToken<Map<ScriptEventType, Script>>(){}.getType();
		if (jsObj.has("scripts")) scripts = context.deserialize(jsObj.get("scripts"), sceventScriptMap);


		boolean isPlayer = Player.isPlayerId(id);

		Event event = (! isPlayer) ? new Event(assets) : new Player(assets);

		event.id = id;
		event.x = x;
		event.y = y;
		event.visible = visible;

		event.direction = direction;
		event.walkState = walkState;
		event.access = access;

		event.texture = texture;
		event.inventory = inventory;
		event.scripts = scripts;

		if (isPlayer)
		{
			Player player = (Player) event;

			PlayerMode mode = PlayerMode.NORMAL;
			// if(jsObj.has("mode")) mode = context.deserialize(jsObj.get("mode"), PlayerMode.class);
			boolean debug = false;
			if(jsObj.has("debug")) debug = jsObj.get("debug").getAsBoolean();

			player.mode = mode;
			player.debug = debug;
		}

		event.area = area;
		area.eventList().add(event);
		area.getChunk(event.x, event.y).eventList().add(event);

		return event;
	}

}
