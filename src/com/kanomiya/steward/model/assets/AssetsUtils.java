package com.kanomiya.steward.model.assets;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.function.Consumer;

import javax.script.ScriptEngine;

import jdk.nashorn.api.scripting.NashornScriptEngineFactory;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kanomiya.steward.Game;
import com.kanomiya.steward.model.area.Area;
import com.kanomiya.steward.model.area.AreaConverter;
import com.kanomiya.steward.model.area.Tip;
import com.kanomiya.steward.model.assets.resource.ResourceLoader;
import com.kanomiya.steward.model.assets.resource.ResourceRegistry;
import com.kanomiya.steward.model.assets.resource.ResourceSaver;
import com.kanomiya.steward.model.assets.resource.type.ResourceType;
import com.kanomiya.steward.model.assets.save.SaveFile;
import com.kanomiya.steward.model.event.Event;
import com.kanomiya.steward.model.event.EventConverter;
import com.kanomiya.steward.model.event.PlayerMode;
import com.kanomiya.steward.model.item.Item;
import com.kanomiya.steward.model.item.ItemConverter;
import com.kanomiya.steward.model.item.ItemStack;
import com.kanomiya.steward.model.item.ItemStackConverter;
import com.kanomiya.steward.model.lang.Language;
import com.kanomiya.steward.model.overlay.GameColor;
import com.kanomiya.steward.model.overlay.text.Text;
import com.kanomiya.steward.model.script.Script;
import com.kanomiya.steward.model.texture.Texture;
import com.kanomiya.steward.model.texture.TextureConverter;
import com.kanomiya.steward.model.texture.TransformerTextureImage;
import com.kanomiya.steward.model.texture.TransformerTextureImageConverter;
import com.kanomiya.steward.model.util.EnumWithIdConverter;
import com.kanomiya.steward.model.util.IEnumWithId;

/**
 * @author Kanomiya
 *
 */
public class AssetsUtils {

	public static String savesDir = "saves";

	public static Gson createGson(Assets assets)
	{
		GsonBuilder gb = new GsonBuilder();

		gb.registerTypeHierarchyAdapter(IEnumWithId.class, new EnumWithIdConverter());

		gb.registerTypeAdapter(TransformerTextureImage.class, new TransformerTextureImageConverter(assets));
		gb.registerTypeAdapter(Texture.class, new TextureConverter(assets));

		gb.registerTypeAdapter(Tip.class, new Tip.Serializer());
		gb.registerTypeAdapter(Tip.class, new Tip.Deserializer(assets));
		gb.registerTypeAdapter(Script.class, new Script.Serializer());
		gb.registerTypeAdapter(Script.class, new Script.Deserializer());

		gb.registerTypeAdapter(Area.class, new AreaConverter(assets));
		gb.registerTypeHierarchyAdapter(Event.class, new EventConverter(assets));
		gb.registerTypeHierarchyAdapter(Item.class, new ItemConverter(assets));
		gb.registerTypeHierarchyAdapter(ItemStack.class, new ItemStackConverter(assets));

		Gson gson = gb.setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();

		return gson;
	}


	public static void loadAssets(Assets assets, SaveFile saveFile)
	{
		loadAssets(assets, new File(saveFile.path));
	}

	public static void saveAssets(Assets assets, SaveFile saveFile)
	{
		saveAssets(assets, new File(saveFile.path));
	}








	protected static void saveAssets(Assets assets, File saveDir)
	{
		Gson gson = createGson(assets);


		try
		{
			File tempDir = new File("temp");
			if (! tempDir.exists()) tempDir.mkdirs();

			File bkSaveDir = new File(tempDir, savesDir + "/" + assets.saveName);
			if (bkSaveDir.exists()) bkSaveDir.delete();

			bkSaveDir.mkdirs();
			saveDir.mkdirs();

			Files.walkFileTree(saveDir.toPath(), new SimpleFileVisitor<Path>() {
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException
				{
					File f = file.toFile();
					String relative = f.getCanonicalPath().substring(saveDir.getCanonicalPath().length() +1);

					File toFile = new File(bkSaveDir, relative);
					toFile.getParentFile().mkdirs();

					Files.copy(file, toFile.toPath(), StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);

					return FileVisitResult.CONTINUE;
				}
			});

			Files.walkFileTree(tempDir.toPath(), new SimpleFileVisitor<Path>() {
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException
				{
					File f = file.toFile();
					String relative = f.getCanonicalPath().substring(saveDir.getCanonicalPath().length() +1);

					File fromFile = new File(bkSaveDir, relative);

					if (! fromFile.exists()) f.delete();

					File ff = f.getParentFile();

					while (ff.list().length == 0)
					{
						if (ff.isDirectory()) ff.delete();
						ff = ff.getParentFile();
					}

					return FileVisitResult.CONTINUE;
				}
			});

			Iterator<Entry<ResourceType, ResourceRegistry>> regItr = assets.registries.entrySet().iterator();

			while (regItr.hasNext())
			{
				Entry<ResourceType, ResourceRegistry> entry = regItr.next();
				new ResourceSaver<>(entry.getKey(), entry.getValue()).save(saveDir, gson);
			}

		} catch (IOException e)
		{
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		assets.getPlayer().logger.println(Text.create("*保存*")
				.color((assets.getPlayer().modeIs(PlayerMode.WIZARD)) ? GameColor.purple : GameColor.orange));


	}

	public static void saveOriginalAssets(Assets assets)
	{
		saveAssets(assets, new File(assets.assetsDir));
	}

	public static void saveAssets(Assets assets, String savesDir)
	{
		saveAssets(assets, new File(savesDir, assets.saveName));
	}


	public static void saveString(String string, File file) throws IOException
	{
		FileWriter fw = new FileWriter(file);
		fw.write(string);

		fw.close();
	}

	public static void saveAsJson(Object obj, Gson gson, File file) throws IOException
	{
		String json = gson.toJson(obj);
		saveString(json, file);
	}






	public static Assets newAssets()
	{
		return newAssets(new File("assets"));
	}

	protected static Assets newAssets(File loadDir)
	{
		return loadAssets(new Assets(loadDir.getPath()), loadDir);
	}

	protected static Assets loadAssets(Assets assets, File loadDir)
	{
		List<Consumer<Assets>> futureTaskList = Lists.newArrayList();

		Gson gson = AssetsUtils.createGson(assets);

		try {

			new ResourceLoader(ResourceType.rtTextureImage, assets.texImageRegistry)
			{
				{
					load(loadDir, gson, futureTaskList);
				}
			};

			new ResourceLoader(ResourceType.rtTransformerTextureImage, assets.tftexImageRegistry)
			{
				{
					load(loadDir, gson, futureTaskList);
				}
			};

			Iterator<Entry<String, TransformerTextureImage>> tftexItr = assets.tftexImageRegistry.entrySet().iterator();
			while (tftexItr.hasNext())
			{
				Entry<String, TransformerTextureImage> entry = tftexItr.next();

				if (assets.texImageRegistry.containsKey(entry.getKey())) Game.logger.debug("Overload a TextureImage: " + entry.getKey());
				assets.texImageRegistry.put(entry.getKey(), entry.getValue().toTextureImage());
			}


			new ResourceLoader(ResourceType.rtTexture, assets.textureRegistry)
			{
				{
					load(loadDir, gson, futureTaskList);
				}
			};


			new ResourceLoader(ResourceType.rtTip, assets.tipRegistry)
			{
				{
					load(loadDir, gson, futureTaskList);
				}
			};

			new ResourceLoader(ResourceType.rtScriptCode, assets.scriptCodeRegistry)
			{
				{
					load(loadDir, gson, futureTaskList);
				}
			};

			new ResourceLoader(ResourceType.rtItem, assets.itemRegistry)
			{
				{
					load(loadDir, gson, futureTaskList);
				}
			};

			new ResourceLoader(ResourceType.rtArea, assets.areaRegistry)
			{
				{
					load(loadDir, gson, futureTaskList);
				}
			};

			new ResourceLoader(ResourceType.rtEvent, assets.eventRegistry)
			{
				{
					load(loadDir, gson, futureTaskList);
				}
			};

			new ResourceLoader(ResourceType.rtLanguage, assets.langRegistry)
			{
				{
					load(loadDir, gson, futureTaskList);
				}
			};

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


		NashornScriptEngineFactory factory = new NashornScriptEngineFactory();
		ScriptEngine scriptEngine = factory.getScriptEngine("-strict", "--no-java", "--no-syntax-extensions");

		assets.setScriptEngine(scriptEngine);

		assets.inited = true;

		Iterator<Consumer<Assets>> itr = futureTaskList.iterator();
		while (itr.hasNext())
		{
			Consumer<Assets> task = itr.next();
			task.accept(assets);
		}

		return assets;
	}


}
