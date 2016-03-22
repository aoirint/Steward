package com.kanomiya.steward.common.model.texture;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.kanomiya.steward.common.Game;
import com.kanomiya.steward.common.model.assets.Assets;
import com.kanomiya.steward.common.model.util.IdWithRange;
import com.kanomiya.steward.common.view.ViewConsts;

/**
 * @author Kanomiya
 *
 */
public class TextureConverter implements JsonSerializer<Texture>, JsonDeserializer<Texture> {

	protected Assets assets;

	public TextureConverter(Assets assets) {
		this.assets = assets;
	}


	protected JsonObject mergeJsonObject(JsonObject base, JsonObject addition)
	{
		Iterator<Entry<String, JsonElement>> itr = addition.entrySet().iterator();

		while (itr.hasNext())
		{
			Entry<String, JsonElement> entry = itr.next();
			base.add(entry.getKey(), entry.getValue());
		}

		return base;
	}

	/**
	* @inheritDoc
	*/
	@Override
	public JsonElement serialize(Texture texture, Type type, JsonSerializationContext context) {
		JsonObject jsObj = new JsonObject();

		jsObj.addProperty("id", texture.id);

		jsObj.add("type", context.serialize(texture.type));


		List<IdWithRange> rangeList = IdWithRange.list(Arrays.asList(texture.frames));
		Iterator<IdWithRange> rangeItr = rangeList.iterator();

		JsonArray jsFrameArr = new JsonArray();

		while (rangeItr.hasNext())
		{
			JsonObject jsFrame = new JsonObject();

			IdWithRange rangeObj = rangeItr.next();
			TextureFrame frameObj = texture.getFrameAt(rangeObj.from);

			JsonArray jsImages = new JsonArray();
			Iterator<TextureImage> imgItr = Arrays.asList(frameObj.images).iterator();

			while (imgItr.hasNext())
			{
				jsImages.add(imgItr.next().getId());
			}

			if (frameObj.drawingX != 0) jsFrame.addProperty("drawingX", frameObj.drawingX);
			if (frameObj.drawingY != 0) jsFrame.addProperty("drawingY", frameObj.drawingY);


			JsonObject jsRange = context.serialize(rangeObj).getAsJsonObject();
			mergeJsonObject(jsFrame, jsRange);

			jsFrameArr.add(jsFrame);
		}

		jsObj.add("frames", jsFrameArr);


		return jsObj;
	}

	/**
	* @inheritDoc
	*/
	@Override
	public Texture deserialize(JsonElement jsElm, Type type, JsonDeserializationContext context) throws JsonParseException
	{
		JsonObject jsObj = jsElm.getAsJsonObject();

		String id = jsObj.get("id").getAsString();

		TextureFrame[] frames = null;

		if (jsObj.has("frames"))
		{
			JsonArray jsFrameArr = jsObj.get("frames").getAsJsonArray();
			int frameCount = jsFrameArr.size();
			frames = new TextureFrame[ViewConsts.FPS /10]; // VELIF fps //  (1->6)/s

			for (int i=0; i<frameCount; i++)
			{
				JsonObject jsFrame = jsFrameArr.get(i).getAsJsonObject();

				IdWithRange iwr = context.deserialize(jsFrame, IdWithRange.class);

				TextureFrame frame = new TextureFrame();

				JsonArray images = jsFrame.get("images").getAsJsonArray();
				frame.images = new TextureImage[images.size()];

				for (int k=0; k<images.size(); k++)
				{
					frame.images[k] = assets.texImageRegistry.get(images.get(k).getAsString());
				}


				if (jsFrame.has("drawingX")) frame.drawingX = jsFrame.get("drawingX").getAsInt();
				if (jsFrame.has("drawingY")) frame.drawingY = jsFrame.get("drawingY").getAsInt();

				int from = Math.max(0, Math.min(frames.length -1, iwr.from));
				int to = Math.max(0, Math.min(frames.length -1, iwr.to));

				if (from != iwr.from) Game.logger.warning("Json 'from': IndexOutOfBounds ( " + iwr.from + "->" + from + " ) at " + id);
				if (to != iwr.to) Game.logger.warning("Json 'to': IndexOutOfBounds ( " + iwr.to + "->" + to + " ) at " + id);

				for (int j=from; j<=to;j++) // VELIF ? isValid
				{
					frames[j] = frame;
				}

			}

		}


		Texture texture = new Texture(id, frames);

		if (jsObj.has("type")) texture.type = context.deserialize(jsObj.get("type"), TextureType.class);
		if (jsObj.has("tileWidth")) texture.tileWidth = jsObj.get("tileWidth").getAsInt();
		if (jsObj.has("tileHeight")) texture.tileHeight = jsObj.get("tileHeight").getAsInt();

		return texture;
	}




}
