package com.kanomiya.steward.common.model.assets;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import javax.imageio.ImageIO;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

import jdk.nashorn.api.scripting.NashornScriptEngineFactory;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.kanomiya.steward.common.model.area.Area;
import com.kanomiya.steward.common.model.area.Tip;
import com.kanomiya.steward.common.model.event.Event;
import com.kanomiya.steward.common.model.event.Player;
import com.kanomiya.steward.common.model.event.PlayerMode;
import com.kanomiya.steward.common.model.item.Item;
import com.kanomiya.steward.common.model.item.ItemStack;
import com.kanomiya.steward.common.model.lang.I18n;
import com.kanomiya.steward.common.model.overlay.GameColor;
import com.kanomiya.steward.common.model.overlay.text.Choice;
import com.kanomiya.steward.common.model.overlay.text.ConfirmResult;
import com.kanomiya.steward.common.model.overlay.text.Text;
import com.kanomiya.steward.common.model.overlay.text.TextField;
import com.kanomiya.steward.common.model.overlay.window.message.Message;
import com.kanomiya.steward.common.model.overlay.window.message.MessageBook;
import com.kanomiya.steward.common.model.script.ScriptEventType;
import com.kanomiya.steward.common.model.script.ScriptFunctionBinder;


/**
 * @author Kanomiya
 *
 */
public class AssetsFactory {


	protected AssetsFactory() {  }


	protected String assetsDir;

	public static AssetsFactory newInstance()
	{
		AssetsFactory afact = new AssetsFactory();

		afact.setAssetsDir("assets");

		return afact;
	}

	public void setAssetsDir(String assetsDir)
	{
		this.assetsDir = assetsDir;
	}


	public Assets newAssets()
	{
		Assets assets = new Assets(assetsDir);
		List<FutureTask> futureTaskList = Lists.newArrayList();

		Gson gson = AssetsUtils.createGson(assets);

		try {
			initTextures(gson, assets, futureTaskList);
			initTips(gson, assets, futureTaskList);
			initScripts(gson, assets, futureTaskList);
			initI18n(assets, futureTaskList);

			initItems(gson, assets, futureTaskList);
			initAreas(gson, assets, futureTaskList);
			initEvents(gson, assets, futureTaskList);

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

	protected void initTextures(Gson gson, Assets assets, List<FutureTask> futureTaskList) throws IOException
	{
		File root = new File(assetsDir, "texture");

		Files.walkFileTree(root.toPath(), new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException
			{
				File f = file.toFile();
				String relative = f.getCanonicalPath().substring(root.getCanonicalPath().length() +1);
				relative = relative.replace('\\', '/');

				BufferedImage image = ImageIO.read(f);

				assets.cacheImage(relative, image);
				return FileVisitResult.CONTINUE;
			}
		});

	}

	protected void initTips(Gson gson, Assets assets, List<FutureTask> futureTaskList) throws IOException
	{
		File root = new File(assetsDir, "tip");

		Files.walkFileTree(root.toPath(), new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException
			{
				if (! file.toString().endsWith(".json")) return FileVisitResult.CONTINUE;

				File f = file.toFile();
				// String relative = f.getCanonicalPath().substring(root.getCanonicalPath().length() +1);
				// relative = relative.replace('\\', '/');

				FileReader fr = new FileReader(f);
				Tip tipObj = gson.fromJson(fr, Tip.class);

				assets.addTip(tipObj);

				return FileVisitResult.CONTINUE;
			}
		});

	}


	protected void initItems(Gson gson, Assets assets, List<FutureTask> futureTaskList) throws IOException
	{
		File root = new File(assetsDir, "item");

		Files.walkFileTree(root.toPath(), new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException
			{
				if (! file.toString().endsWith(".json")) return FileVisitResult.CONTINUE;

				File f = file.toFile();
				String relative = f.getCanonicalPath().substring(root.getCanonicalPath().length() +1);
				relative = relative.replace('\\', '/');

				FileReader fr = new FileReader(f);

				Item item = gson.fromJson(fr, Item.class);

				assets.addItem(item);

				return FileVisitResult.CONTINUE;
			}
		});

	}

	protected void initAreas(Gson gson, Assets assets, List<FutureTask> futureTaskList) throws IOException
	{
		File root = new File(assetsDir, "area");

		Files.walkFileTree(root.toPath(), new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException
			{
				if (! file.toString().endsWith(".json")) return FileVisitResult.CONTINUE;

				File f = file.toFile();
				// String relative = f.getCanonicalPath().substring(root.getCanonicalPath().length() +1);
				// relative = relative.replace('\\', '/');

				FileReader fr = new FileReader(f);
				Area areaObj = gson.fromJson(fr, Area.class);

				assets.addArea(areaObj);

				return FileVisitResult.CONTINUE;
			}
		});

	}

	protected void initEvents(Gson gson, Assets assets, List<FutureTask> futureTaskList) throws IOException
	{
		File root = new File(assetsDir, "event");

		Files.walkFileTree(root.toPath(), new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException
			{
				if (! file.toString().endsWith(".json")) return FileVisitResult.CONTINUE;

				File f = file.toFile();
				String relative = f.getCanonicalPath().substring(root.getCanonicalPath().length() +1);
				relative = relative.replace('\\', '/');

				FileReader fr = new FileReader(f);

				// boolean isPlayer = file.getFileName().endsWith("player.json");
				Event eventObj = gson.fromJson(fr, Event.class);

				eventObj.texture.owner = eventObj;

				futureTaskList.add(new FutureTask(new Callable<Boolean>()
				{
					@Override
					public Boolean call() {
						eventObj.area.setEvent(eventObj);
						eventObj.area.launchEvent(eventObj, eventObj.x, eventObj.y, ScriptEventType.ONENTERED);

						return true;
					}
				}));

				assets.addEvent(eventObj);

				return FileVisitResult.CONTINUE;
			}
		});

	}

	protected void initScripts(Gson gson, Assets assets, List<FutureTask> futureTaskList) throws IOException
	{
		File root = new File(assetsDir, "script");

		Files.walkFileTree(root.toPath(), new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException
			{
				if (! file.toString().endsWith(".js")) return FileVisitResult.CONTINUE;

				File f = file.toFile();
				String relative = f.getCanonicalPath().substring(root.getCanonicalPath().length() +1);
				relative = relative.replace('\\', '/');

				InputStreamReader isrScript = new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_8);

				StringBuilder sb = new StringBuilder();

				while (isrScript.ready())
				{
					sb.appendCodePoint(isrScript.read());
				}

				isrScript.close();

				assets.addScriptCode(relative, sb.toString());

				return FileVisitResult.CONTINUE;
			}
		});

	}


	/**
	 * @param assets
	 */
	protected void initI18n(Assets assets, List<FutureTask> futureTaskList) throws IOException {
		File root = new File(assetsDir, "lang");

		Files.walkFileTree(root.toPath(), new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException
			{
				if (! file.toString().endsWith(".lang")) return FileVisitResult.CONTINUE;
				File f = file.toFile();

				String name = file.getFileName().toString();
				name = name.substring(0, name.lastIndexOf('.'));

				PropertyResourceBundle bundle = new PropertyResourceBundle(new FileReader(f));

				String[] tags = name.split("_");
				if (tags.length < 2) return FileVisitResult.CONTINUE;

				Locale.Builder builder = new Locale.Builder().setLanguage(tags[0]).setRegion(tags[1]);
				if (3 <= tags.length) builder.setVariant(tags[2]);

				assets.addI18n(new I18n(builder.build(), bundle));

				return FileVisitResult.CONTINUE;
			}
		});

	}






}

