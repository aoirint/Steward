package com.kanomiya.steward.common.model;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.kanomiya.steward.common.model.area.AccessType;
import com.kanomiya.steward.common.model.event.Direction;
import com.kanomiya.steward.common.model.event.PlayerMode;
import com.kanomiya.steward.common.model.event.WalkState;
import com.kanomiya.steward.common.model.script.ScriptEventType;
import com.kanomiya.steward.common.model.texture.TextureType;

/**
 * @author Kanomiya
 *
 */
public class EnumWithIdConverter implements JsonSerializer<IEnumWithId>, JsonDeserializer<IEnumWithId> {

	/**
	* @inheritDoc
	*/
	@Override
	public JsonElement serialize(IEnumWithId obj, Type type, JsonSerializationContext jsc) {
		return new JsonPrimitive(obj.getId());
	}

	/**
	* @inheritDoc
	*/
	@Override
	public IEnumWithId deserialize(JsonElement jsElm, Type type, JsonDeserializationContext jdc) throws JsonParseException {

		if (type instanceof Class)
		{
			Class clazz = (Class) type;
			String id = jsElm.getAsString();

			if (clazz.isAssignableFrom(TextureType.class)) return TextureType.getFromId(id);
			if (clazz.isAssignableFrom(PlayerMode.class)) return PlayerMode.getFromId(id);
			if (clazz.isAssignableFrom(Direction.class)) return Direction.getFromId(id);
			if (clazz.isAssignableFrom(WalkState.class)) return WalkState.getFromId(id);
			if (clazz.isAssignableFrom(AccessType.class)) return AccessType.getFromId(id);
			if (clazz.isAssignableFrom(ScriptEventType.class)) return ScriptEventType.getFromId(id);
		}

		throw new IllegalArgumentException();
	}




}
