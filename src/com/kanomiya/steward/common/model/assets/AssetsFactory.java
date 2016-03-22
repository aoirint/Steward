package com.kanomiya.steward.common.model.assets;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.FutureTask;

import javax.script.ScriptEngine;
import javax.script.ScriptException;

import jdk.nashorn.api.scripting.NashornScriptEngineFactory;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.kanomiya.steward.common.model.assets.loader.ResourceLoader;
import com.kanomiya.steward.common.model.assets.resource.type.ResourceType;
import com.kanomiya.steward.common.model.event.Player;
import com.kanomiya.steward.common.model.event.PlayerMode;
import com.kanomiya.steward.common.model.item.ItemStack;
import com.kanomiya.steward.common.model.lang.Language;
import com.kanomiya.steward.common.model.overlay.GameColor;
import com.kanomiya.steward.common.model.overlay.text.Choice;
import com.kanomiya.steward.common.model.overlay.text.ConfirmResult;
import com.kanomiya.steward.common.model.overlay.text.Text;
import com.kanomiya.steward.common.model.overlay.text.TextField;
import com.kanomiya.steward.common.model.overlay.window.message.Message;
import com.kanomiya.steward.common.model.overlay.window.message.MessageBook;
import com.kanomiya.steward.common.model.script.ScriptFunctionBinder;


/**
 * @author Kanomiya
 *
 */
public class AssetsFactory {


	public Assets newAssets()
	{
		return newAssets(new File("assets"));
	}

	protected Assets newAssets(File loadDir)
	{
		return loadAssets(new Assets(loadDir.getPath()), loadDir);
	}

	protected Assets loadAssets(Assets assets, File loadDir)
	{
		List<FutureTask> futureTaskList = Lists.newArrayList();

		Gson gson = AssetsUtils.createGson(assets);

		try {

			assets.texImageRegistry = new ResourceLoader(ResourceType.rtTextureImage, assets.texImageRegistry)
			{
				{
					load(loadDir, gson, futureTaskList);
				}
			}.toRegistry();

			assets.textureRegistry = new ResourceLoader(ResourceType.rtTexture, assets.textureRegistry)
			{
				{
					load(loadDir, gson, futureTaskList);
				}
			}.toRegistry();

			assets.tipRegistry = new ResourceLoader(ResourceType.rtTip, assets.tipRegistry)
			{
				{
					load(loadDir, gson, futureTaskList);
				}
			}.toRegistry();

			assets.scriptCodeRegistry = new ResourceLoader(ResourceType.rtScriptCode, assets.scriptCodeRegistry)
			{
				{
					load(loadDir, gson, futureTaskList);
				}
			}.toRegistry();

			assets.itemRegistry = new ResourceLoader(ResourceType.rtItem, assets.itemRegistry)
			{
				{
					load(loadDir, gson, futureTaskList);
				}
			}.toRegistry();

			assets.areaRegistry = new ResourceLoader(ResourceType.rtArea, assets.areaRegistry)
			{
				{
					load(loadDir, gson, futureTaskList);
				}
			}.toRegistry();

			assets.eventRegistry = new ResourceLoader(ResourceType.rtEvent, assets.eventRegistry)
			{
				{
					load(loadDir, gson, futureTaskList);
				}
			}.toRegistry();

			assets.langRegistry = new ResourceLoader(ResourceType.rtLanguage, assets.langRegistry)
			{
				{
					load(loadDir, gson, futureTaskList);
				}
			}.toRegistry();

			Iterator<Language> langItr = assets.langRegistry.values().iterator();
			while (langItr.hasNext())
			{
				Language lang = langItr.next();
				assets.localeToLanguage.put(lang.getLocale(), lang);
			}

		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		assets.setLocale(Locale.getDefault());

		Player player = assets.getPlayer();

		NashornScriptEngineFactory factory = new NashornScriptEngineFactory();
		ScriptEngine scriptEngine = factory.getScriptEngine("-strict", "--no-java", "--no-syntax-extensions");

		scriptEngine.put("assets", assets);
		scriptEngine.put("player", player);

		scriptEngine.put("logger", player.logger);
		scriptEngine.put("Text", Text.class);
		scriptEngine.put("Choice", Choice.class);
		scriptEngine.put("ChoiceResult", ConfirmResult.class);
		scriptEngine.put("TextField", TextField.class);
		scriptEngine.put("Message", Message.class);
		scriptEngine.put("MessageBook", MessageBook.class);
		scriptEngine.put("Book", MessageBook.class);
		scriptEngine.put("GameColor", GameColor.class);
		scriptEngine.put("PlayerMode", PlayerMode.class);
		scriptEngine.put("ItemStack", ItemStack.class);

		scriptEngine.put("binder", new ScriptFunctionBinder(assets, player));

		try {
			scriptEngine.eval("var translate = Function.prototype.bind.call(assets.translate, assets);");
			scriptEngine.eval("var text = Function.prototype.bind.call(Text.static.create, Text);");
			scriptEngine.eval("var choice = Function.prototype.bind.call(Choice.static.create, Choice);");
			scriptEngine.eval("var textField = Function.prototype.bind.call(TextField.static.create, TextField);");
			scriptEngine.eval("var message = Function.prototype.bind.call(Message.static.create, Message);");
			scriptEngine.eval("var messageBook = Function.prototype.bind.call(MessageBook.static.create, MessageBook);");
			scriptEngine.eval("var book = Function.prototype.bind.call(Book.static.create, Book);");
			scriptEngine.eval("var showWindow = Function.prototype.bind.call(player.showWindow, player);");
			scriptEngine.eval("var execute = Function.prototype.bind.call(binder.execute, binder);");
			scriptEngine.eval("var exit = Function.prototype.bind.call(binder.exit, binder);");
			scriptEngine.eval("var itemStack = Function.prototype.bind.call(ItemStack.static.create, ItemStack);");



		} catch (ScriptException e) {
			// TODO 自動生成された catch ブロック
			System.err.println("Excepion source: AssetsFactory");
			e.printStackTrace();
		} catch (Exception e) {
			// TODO
			System.err.println("Excepion source: AssetsFactory");
			e.printStackTrace();
		}

		assets.setScriptEngine(scriptEngine);

		assets.inited = true;

		Iterator<FutureTask> itr = futureTaskList.iterator();
		while (itr.hasNext())
		{
			FutureTask task = itr.next();
			if (task.isCancelled()) continue;

			task.run();
		}

		return assets;
	}










}

