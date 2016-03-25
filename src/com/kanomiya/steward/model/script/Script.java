package com.kanomiya.steward.model.script;

import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Maps;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.Expose;


/**
 * @author Kanomiya
 *
 */
public class Script {

	@Expose public String src;
	@Expose public Map<String, String> arguments;

	public Script(String src)
	{
		this.src = src;
	}

	public boolean hasArgument()
	{
		return (arguments != null && ! arguments.isEmpty());
	}

	public static class Serializer implements JsonSerializer<Script>
	{
		/**
		* @inheritDoc
		*/
		@Override
		public JsonElement serialize(Script obj, Type type, JsonSerializationContext context)
		{
			JsonObject jsObj = new JsonObject();

			jsObj.addProperty("src", obj.src);

			if (obj.hasArgument())
			{
				JsonObject jsArgs = new JsonObject();

				Iterator<Entry<String, String>> itr = obj.arguments.entrySet().iterator();

				while (itr.hasNext())
				{
					Entry<String, String> entry = itr.next();
					JsonElement jsArg = context.serialize(entry.getValue());

					if (jsArg instanceof JsonPrimitive)
					{
						jsArgs.add(entry.getKey(), jsArg);
					} else
					{
						throw new IllegalArgumentException("An argument isn't JsonPrimitive");
					}

				}

				jsObj.add("arguments", jsArgs);
			}

			return jsObj;
		}

	}

	public static class Deserializer implements JsonDeserializer<Script>
	{
		/**
		* @inheritDoc
		*/
		@Override
		public Script deserialize(JsonElement jsElm, Type type, JsonDeserializationContext context) throws JsonParseException
		{
			JsonObject jsObj = jsElm.getAsJsonObject();

			String src = jsObj.get("src").getAsString();
			Script script = new Script(src);

			if (jsObj.has("arguments"))
			{
				Map<String, String> arguments = Maps.newHashMap();
				JsonObject jsArgs = jsObj.get("arguments").getAsJsonObject();

				Iterator<Entry<String, JsonElement>> itr = jsArgs.entrySet().iterator();

				while (itr.hasNext())
				{
					Entry<String, JsonElement> entry = itr.next();

					if (entry.getValue().isJsonPrimitive())
					{
						JsonPrimitive pValue = entry.getValue().getAsJsonPrimitive();

						arguments.put(entry.getKey(), pValue.getAsString());

					} else
					{
						throw new JsonSyntaxException("An argument isn't JsonPrimitive");
					}

				}

				script.arguments = arguments;
			}

			return script;
		}

	}

}
