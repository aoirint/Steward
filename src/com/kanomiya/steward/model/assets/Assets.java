package com.kanomiya.steward.model.assets;

import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

import javax.script.ScriptEngine;

import com.google.common.collect.Maps;
import com.kanomiya.steward.model.area.Area;
import com.kanomiya.steward.model.area.Tip;
import com.kanomiya.steward.model.assets.resource.ResourceRegistry;
import com.kanomiya.steward.model.assets.resource.type.ResourceType;
import com.kanomiya.steward.model.event.Event;
import com.kanomiya.steward.model.event.Player;
import com.kanomiya.steward.model.item.Item;
import com.kanomiya.steward.model.lang.Language;
import com.kanomiya.steward.model.script.ScriptCode;
import com.kanomiya.steward.model.script.ScriptFunctionBinder;
import com.kanomiya.steward.model.texture.Texture;
import com.kanomiya.steward.model.texture.TextureImage;
import com.kanomiya.steward.model.texture.TransformerTextureImage;


/**
 * @author Kanomiya
 *
 */
public class Assets {

	public Map<ResourceType, ResourceRegistry> registries;

	protected ResourceRegistry<TextureImage> texImageRegistry;
	protected ResourceRegistry<TransformerTextureImage> tftexImageRegistry;
	protected ResourceRegistry<Texture> textureRegistry;
	protected ResourceRegistry<Tip> tipRegistry;
	protected ResourceRegistry<ScriptCode> scriptCodeRegistry;
	protected ResourceRegistry<Item> itemRegistry;
	protected ResourceRegistry<Area> areaRegistry;
	protected ResourceRegistry<Event> eventRegistry;
	protected ResourceRegistry<Language> langRegistry;
	protected Map<Locale, Language> localeToLanguage;

	public Locale locale;

	protected String assetsDir, saveName;
	protected ScriptEngine scriptEngine;
	public ScriptFunctionBinder binder;

	protected boolean inited;

	public Assets(String assetsDir)
	{
		this.assetsDir = assetsDir;

		saveName = "unknown";
		inited = false;

		texImageRegistry = new ResourceRegistry<>();
		tftexImageRegistry = new ResourceRegistry<>();
		textureRegistry = new ResourceRegistry<>();
		tipRegistry = new ResourceRegistry<>();
		scriptCodeRegistry = new ResourceRegistry<>();
		itemRegistry = new ResourceRegistry<>();
		areaRegistry = new ResourceRegistry<>();
		eventRegistry = new ResourceRegistry<>();
		langRegistry = new ResourceRegistry<>();
		localeToLanguage = Maps.newHashMap();

		registries = Maps.newHashMap();

		registries.put(ResourceType.rtTextureImage, texImageRegistry);
		registries.put(ResourceType.rtTransformerTextureImage, tftexImageRegistry);
		registries.put(ResourceType.rtTexture, textureRegistry);
		registries.put(ResourceType.rtTip, tipRegistry);
		registries.put(ResourceType.rtScriptCode, scriptCodeRegistry);
		registries.put(ResourceType.rtItem, itemRegistry);
		registries.put(ResourceType.rtArea, areaRegistry);
		registries.put(ResourceType.rtEvent, eventRegistry);
		registries.put(ResourceType.rtLanguage, langRegistry);


	}



	/**
	 * @return
	 */
	public boolean isInited() {
		return inited;
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
	public String getSaveName() {
		return saveName;
	}


	protected Pattern fnValid = Pattern.compile("[\0\\./'\"`\t\n\r\f?*\\\\<:|>]+");


	public boolean saveNameIsValid(String saveName)
	{
		return saveName != null && 0 < saveName.length() && ! fnValid.matcher(saveName).find();
	}

	public boolean setSaveName(String saveName)
	{
		if (! saveNameIsValid(saveName)) return false;
		this.saveName = saveName;

		return true;
	}



	public ScriptEngine getScriptEngine()
	{
		return scriptEngine;
	}

	public void setScriptEngine(ScriptEngine scriptEngine)
	{
		this.scriptEngine = scriptEngine;
	}


	public void setLocale(Locale locale)
	{
		this.locale = locale;
	}


	public TextureImage getTextureImage(String id)
	{
		return texImageRegistry.get(id);
	}

	public Texture getTexture(String id)
	{
		return textureRegistry.get(id);
	}

	/**
	 * @param id
	 * @return
	 */
	public ScriptCode getScriptCode(String id) {
		return scriptCodeRegistry.get(id);
	}

	/**
	 * @param id
	 * @return
	 */
	public Tip getTip(String id) {
		return tipRegistry.get(id);
	}

	/**
	 * @param id
	 * @return
	 */
	public Item getItem(String id) {
		return itemRegistry.get(id);
	}

	/**
	 * @param id
	 * @return
	 */
	public Area getArea(String id) {
		return areaRegistry.get(id);
	}

	/**
	 * @param id
	 * @return
	 */
	public Event getEvent(String id) {
		return eventRegistry.get(id);
	}


	/**
	 * @return
	 */
	public Player getPlayer() {
		return (Player) eventRegistry.get("player");
	}


	/**
	 * @param unlocalizedName
	 * @return
	 */
	public String translate(String unlocalizedName, String... args) {
		Locale locale = this.locale;

		if (! localeToLanguage.containsKey(locale))
		{
			if (! localeToLanguage.containsKey(Locale.JAPAN)) return unlocalizedName;
			locale = Locale.JAPAN;
		}

		return localeToLanguage.get(locale).translate(unlocalizedName, args);
	}





	protected void clearRegistries()
	{
		texImageRegistry.clear();
		textureRegistry.clear();
		tipRegistry.clear();
		scriptCodeRegistry.clear();
		itemRegistry.clear();
		areaRegistry.clear();
		eventRegistry.clear();
		langRegistry.clear();

	}




	/**
	 * @return
	 */
	public Collection<Tip> tipList() {
		return tipRegistry.values();
	}

























}
