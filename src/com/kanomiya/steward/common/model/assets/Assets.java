package com.kanomiya.steward.common.model.assets;

import java.awt.Dimension;
import java.awt.Image;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import javax.script.ScriptEngine;

import com.google.common.collect.Maps;
import com.kanomiya.steward.common.model.area.Area;
import com.kanomiya.steward.common.model.area.Tip;
import com.kanomiya.steward.common.model.event.Event;
import com.kanomiya.steward.common.model.event.Player;


/**
 * @author Kanomiya
 *
 */
public class Assets {


	protected Map<String, Image> srcToImage = Maps.newHashMap();
	protected Map<Image, Dimension> imageToSize = Maps.newHashMap();
	protected Map<String, Tip> idToTip = Maps.newHashMap();
	protected Map<String, Area> idToArea = Maps.newHashMap();
	protected Map<String, String> srcToScriptCode = Maps.newHashMap();
	protected Map<String, Event> idToEvent = Maps.newHashMap();

	protected String assetsDir, saveDir;
	protected ScriptEngine scriptEngine;


	public Assets(String assetsDir, String saveDir)
	{
		this.assetsDir = assetsDir;
		this.saveDir = saveDir;
	}



	/**
	 * @return
	 */
	public String getAssetsDir() {
		return assetsDir;
	}

	/**
	 * @return
	 */
	public String getSaveDir() {
		return saveDir;
	}


	public Area getArea(String id)
	{
		return idToArea.get(id);
	}

	public Image getCachedImage(String path)
	{
		return srcToImage.get(path);
	}

	public Dimension getCachedImageDim(Image cachedImage)
	{
		return imageToSize.get(cachedImage);
	}


	public Tip getTip(String id)
	{
		return idToTip.get(id);
	}

	public String getScriptCode(String path)
	{
		return srcToScriptCode.get(path);
	}

	public Event getEvent(String id)
	{
		return idToEvent.get(id);
	}


	public ScriptEngine getScriptEngine()
	{
		return scriptEngine;
	}

	public void setScriptEngine(ScriptEngine scriptEngine)
	{
		this.scriptEngine = scriptEngine;
	}



	public void cacheImage(String path, Image image)
	{
		srcToImage.put(path, image);
		imageToSize.put(image, new Dimension(image.getWidth(null), image.getHeight(null)));
	}

	public void addTip(Tip tip)
	{
		idToTip.put(tip.getId(), tip);
	}

	public void addArea(Area area)
	{
		idToArea.put(area.getId(), area);
	}

	public void addScriptCode(String path, String source)
	{
		srcToScriptCode.put(path, source);
	}

	public void addEvent(Event event)
	{
		idToEvent.put(event.getId(), event);
	}


	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();

		builder.append(getClass().getSimpleName());
		builder.append('[');


		builder.append("textures");
		builder.append('[');

		Set<String> texKeys = srcToImage.keySet();
		for (String key: texKeys)
		{
			builder.append(key);
			builder.append(',');
		}

		builder.append("], ");


		builder.append("tips");
		builder.append('[');

		Set<String> tipKeys = idToTip.keySet();
		for (String key: tipKeys)
		{
			builder.append(key);
			builder.append(',');
		}

		builder.append("], ");


		builder.append("areas");
		builder.append('[');

		Set<String> areaKeys = idToArea.keySet();
		for (String key: areaKeys)
		{
			builder.append(idToArea.get(key).toString());
			builder.append(',');
		}

		builder.append("], ");

		builder.append(']');

		return new String(builder);
	}


	public Player getPlayer()
	{
		return (Player) getEvent("player");
	}



	public Collection<Tip> tipList() {
		return idToTip.values();
	}

	public Collection<Area> areaList() {
		return idToArea.values();
	}

	public Collection<Event> eventList() {
		return idToEvent.values();
	}







}
