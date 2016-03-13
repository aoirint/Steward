package com.kanomiya.steward.common.model.event;

import java.lang.reflect.Type;
import java.util.Map;

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

		if (event.direction != Direction.SOUTH) jsObj.add("direction", context.serialize(event.direction));
		if (event.walkState != WalkState.UPRIGHT) jsObj.add("walkState", context.serialize(event.walkState));
		if (event.access != AccessType.allow) jsObj.add("access", context.serialize(event.access));

		jsObj.add("icon", context.serialize(event.getIcon()));

		if (Player.isPlayerId(event.getId()))
		{
			Player player = (Player) event;

			if (player.mode != PlayerMode.NORMAL) jsObj.add("mode", context.serialize(player.mode));
			if (! player.debugVisible) jsObj.addProperty("debugMode", player.debugVisible);

		}

		if (event.scripts != null) jsObj.add("scripts", context.serialize(event.scripts));

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

		Direction direction = Direction.SOUTH;
		if (jsObj.has("direction")) context.deserialize(jsObj.get("direction"), Direction.class);

		WalkState walkState = WalkState.UPRIGHT;
		if (jsObj.has("walkState")) walkState = context.deserialize(jsObj.get("walkState"), WalkState.class);

		AccessType access = AccessType.allow;
		if (jsObj.has("access")) access = context.deserialize(jsObj.get("access"), AccessType.class);

		Texture texture = context.deserialize(jsObj.get("icon"), Texture.class);

		if (Player.isPlayerId(id))
		{
			PlayerMode mode = PlayerMode.NORMAL;
			if(jsObj.has("mode")) mode = context.deserialize(jsObj.get("mode"), PlayerMode.class);
			boolean debugMode = false;
			if(jsObj.has("debugMode")) debugMode = jsObj.get("debugMode").getAsBoolean();

			return new Player(id, area, x, y, texture, direction, walkState, access, null, mode, debugMode, assets);
		}

		Map<ScriptEventType, Script> scripts = null;
		Type sceventScriptMap = new TypeToken<Map<ScriptEventType, Script>>(){}.getType();
		if (jsObj.has("scripts")) scripts = context.deserialize(jsObj.get("scripts"), sceventScriptMap);

		return new Event(id, area, x, y, texture, direction, walkState, access, scripts, assets);
	}

}
