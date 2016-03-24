package com.kanomiya.steward.model.assets;

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Pattern;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

import com.google.common.collect.Maps;
import com.kanomiya.steward.Game;
import com.kanomiya.steward.model.area.Area;
import com.kanomiya.steward.model.area.Tip;
import com.kanomiya.steward.model.assets.resource.ResourceRegistry;
import com.kanomiya.steward.model.assets.resource.type.ResourceType;
import com.kanomiya.steward.model.assets.save.SaveFile;
import com.kanomiya.steward.model.event.Event;
import com.kanomiya.steward.model.event.Player;
import com.kanomiya.steward.model.event.PlayerMode;
import com.kanomiya.steward.model.item.Item;
import com.kanomiya.steward.model.item.ItemStack;
import com.kanomiya.steward.model.lang.Language;
import com.kanomiya.steward.model.overlay.GameColor;
import com.kanomiya.steward.model.overlay.text.Choice;
import com.kanomiya.steward.model.overlay.text.ConfirmResult;
import com.kanomiya.steward.model.overlay.text.Text;
import com.kanomiya.steward.model.overlay.text.TextField;
import com.kanomiya.steward.model.overlay.window.Window;
import com.kanomiya.steward.model.overlay.window.message.Message;
import com.kanomiya.steward.model.overlay.window.message.MessageBook;
import com.kanomiya.steward.model.script.IScriptLauncher;
import com.kanomiya.steward.model.script.IScriptOwner;
import com.kanomiya.steward.model.script.Script;
import com.kanomiya.steward.model.script.ScriptCode;
import com.kanomiya.steward.model.script.ScriptEventType;
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


	public void load()
	{
		AssetsUtils.loadAssets(this, SaveFile.create(saveName));
	}

	public void save()
	{
		AssetsUtils.saveAssets(this, SaveFile.create(saveName));
	}


	public ScriptEngine getScriptEngine()
	{
		return scriptEngine;
	}

	public void setScriptEngine(ScriptEngine scriptEngine)
	{
		this.scriptEngine = scriptEngine;
		resetBindings();
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


	public void resetBindings()
	{
		Bindings bindings = scriptEngine.createBindings();

		Player player = getPlayer();

		bindings.put("assets", this);
		bindings.put("player", player);

		bindings.put("console", Game.logger);
		bindings.put("logger", player.logger);
		bindings.put("Text", Text.class);
		bindings.put("Choice", Choice.class);
		bindings.put("ChoiceResult", ConfirmResult.class);
		bindings.put("TextField", TextField.class);
		bindings.put("Message", Message.class);
		bindings.put("MessageBook", MessageBook.class);
		bindings.put("Book", MessageBook.class);
		bindings.put("GameColor", GameColor.class);
		bindings.put("PlayerMode", PlayerMode.class);
		bindings.put("ItemStack", ItemStack.class);
		bindings.put("SaveFile", SaveFile.class);

		binder = new ScriptFunctionBinder(this, player);
		bindings.put("binder", binder);

		bindings.put("translate", (Function<String, String>) this::translate);
		bindings.put("exec", (Consumer<String>) this::exec);

		bindings.put("text", (Function<String, Text>) Text::create);
		bindings.put("choice", (Function<String, Choice>) Choice::create);
		bindings.put("textField", (Supplier<TextField>) TextField::create);
		bindings.put("message", (Supplier<Message>) Message::create);
		bindings.put("messageBook", (Supplier<MessageBook>) MessageBook::create);
		bindings.put("showWindow", new Consumer()
		{
			@Override
			public void accept(Object arg) {
				if (arg instanceof Message)
				{
					player.showWindow((Message) arg);
				} else
				{
					player.showWindow((Window) arg);;
				}
			}
		});

		bindings.put("exit", (Runnable) binder::exit);
		bindings.put("itemStack", (BiFunction<Item, Integer, ItemStack>) ItemStack::create);
		bindings.put("saveFiles", (Supplier<List<SaveFile>>) SaveFile::saveFiles);

		// Iterator itr = bindings.keySet().iterator();
		// while (itr.hasNext()) Game.logger.debug(itr.next());

		scriptEngine.setBindings(bindings, ScriptContext.ENGINE_SCOPE);
	}



	/**
	 * @param owner
	 * @param launcher
	 * @param eventType
	 */
	public void exec(IScriptOwner owner, IScriptLauncher launcher, ScriptEventType eventType) {
		if (! owner.hasScript(launcher, eventType)) return ;

		Script script = owner.getScript(launcher, eventType);

		Map<String, Object> arguments = (script.hasArgument()) ? Maps.newHashMap(script.arguments) : Maps.newHashMap();

		owner.initArguments(launcher, arguments);

		exec(script.src, arguments);
	}

	public void exec(String id) {
		exec(id, null);
	}

	/*
	public void exec(String id, ScriptObjectMirror mirror) {

	}
	*/

	/**
	 *
	 * スクリプトを実行します
	 *
	 * @param id
	 * @param arguments
	 */
	protected void exec(String id, Map<String, Object> arguments) {
		if (arguments == null) arguments = Maps.newHashMap();

		ScriptCode scriptCode = scriptCodeRegistry.get(id);

		if (scriptCode == null)
		{
			Game.logger.warn("No such Script code: " + id);
			return ;
		}

		Bindings bindings = scriptEngine.getBindings(ScriptContext.ENGINE_SCOPE);

		arguments.forEach((String key, Object value) -> bindings.put(key, value));

		// bindings.put("arguments", arguments); // 複数型の連想配列はnullになる？

		// Iterator itr = bindings.keySet().iterator();
		// while (itr.hasNext()) Game.logger.debug(itr.next());

		try {
			scriptEngine.eval(scriptCode.code, bindings);

		} catch (ScriptException e) {
			// TODO 自動生成された catch ブロック
			Game.logger.warn("Excepion source: " + id + " " + e.getFileName() + ":" + e.getLineNumber());
			e.printStackTrace();

		} catch (Exception e)
		{
			// TODO
			Game.logger.warn("Excepion source: " + id);
			e.printStackTrace();
		}

		// resetBindings();

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
	 * @param event
	 */
	public void removeEvent(Event event) {
		eventRegistry.remove(event);
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




	/**
	 * @return
	 */
	public Collection<Area> areaList() {
		return areaRegistry.values();
	}

































}
