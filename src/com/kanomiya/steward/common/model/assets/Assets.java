package com.kanomiya.steward.common.model.assets;

import java.awt.Image;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Maps;
import com.kanomiya.steward.common.model.area.Area;
import com.kanomiya.steward.common.model.area.Tip;


/**
 * @author Kanomiya
 *
 */
public class Assets {

	protected Assets() {  }

	protected Map<String, Image> textures = Maps.newHashMap();
	protected Map<String, Tip> tips = Maps.newHashMap();
	protected Map<String, Area> areas = Maps.newHashMap();


	public Area getArea(String id)
	{
		return areas.get(id);
	}

	public Image getTexture(String path)
	{
		return textures.get(path);
	}

	public Tip getTip(String id)
	{
		return tips.get(id);
	}



	public void addTexture(String path, Image image)
	{
		textures.put(path, image);
	}

	public void addTip(Tip tip)
	{
		tips.put(tip.getId(), tip);
	}

	public void addArea(Area area)
	{
		areas.put(area.getId(), area);
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();

		builder.append(getClass().getSimpleName());
		builder.append('[');


		builder.append("textures");
		builder.append('[');

		Set<String> texKeys = textures.keySet();
		for (String key: texKeys)
		{
			builder.append(key);
			builder.append(',');
		}

		builder.append("], ");


		builder.append("tips");
		builder.append('[');

		Set<String> tipKeys = tips.keySet();
		for (String key: tipKeys)
		{
			builder.append(key);
			builder.append(',');
		}

		builder.append("], ");


		builder.append("areas");
		builder.append('[');

		Set<String> areaKeys = areas.keySet();
		for (String key: areaKeys)
		{
			builder.append(areas.get(key).toString());
			builder.append(',');
		}

		builder.append("], ");

		builder.append(']');

		return new String(builder);
	}
}
