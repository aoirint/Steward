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
import java.util.Collection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kanomiya.steward.model.area.Area;
import com.kanomiya.steward.model.area.AreaConverter;
import com.kanomiya.steward.model.area.Tip;
import com.kanomiya.steward.model.event.Event;
import com.kanomiya.steward.model.event.EventConverter;
import com.kanomiya.steward.model.event.PlayerMode;
import com.kanomiya.steward.model.item.Item;
import com.kanomiya.steward.model.item.ItemConverter;
import com.kanomiya.steward.model.item.ItemStack;
import com.kanomiya.steward.model.item.ItemStackConverter;
import com.kanomiya.steward.model.overlay.GameColor;
import com.kanomiya.steward.model.overlay.text.Text;
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

		gb.registerTypeAdapter(Area.class, new AreaConverter(assets));
		gb.registerTypeHierarchyAdapter(Event.class, new EventConverter(assets));
		gb.registerTypeHierarchyAdapter(Item.class, new ItemConverter(assets));
		gb.registerTypeHierarchyAdapter(ItemStack.class, new ItemStackConverter(assets));

		Gson gson = gb.setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();

		return gson;
	}


	protected static void saveAssets(Assets assets, File saveDir)
	{
		Gson gson = createGson(assets);

		Collection<Area> areaList = assets.areaRegistry.values();
		Collection<Event> eventList = assets.eventRegistry.values();

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


			File areaDir = new File(saveDir, "area");
			File eventDir = new File(saveDir, "event");

			if (! areaDir.exists()) areaDir.mkdir();
			if (! eventDir.exists()) eventDir.mkdir();

			for (Area area: areaList)
			{
				File areaFile = new File(areaDir, area.getId() + ".json");
				File aDir = areaFile.getParentFile();
				if (! aDir.exists()) aDir.mkdirs();


				saveAsJson(area, gson, areaFile);
			}

			for (Event event: eventList)
			{
				File eventFile = new File(eventDir, event.getId() + ".json");
				File eDir = eventFile.getParentFile();
				if (! eDir.exists()) eDir.mkdirs();

				saveAsJson(event, gson, eventFile);
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

	public static void saveAsJson(Object obj, Gson gson, File file) throws IOException
	{
		String json = gson.toJson(obj);

		FileWriter fw = new FileWriter(file);
		fw.write(json);

		fw.close();

	}


}
