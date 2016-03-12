package com.kanomiya.steward.common.model.assets;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kanomiya.steward.common.model.area.Area;
import com.kanomiya.steward.common.model.area.AreaConverter;
import com.kanomiya.steward.common.model.event.Event;
import com.kanomiya.steward.common.model.event.EventConverter;
import com.kanomiya.steward.common.model.texture.Texture;
import com.kanomiya.steward.common.model.texture.TextureConverter;
import com.kanomiya.steward.common.model.texture.TextureMode;
import com.kanomiya.steward.common.model.texture.TextureModeConverter;

/**
 * @author Kanomiya
 *
 */
public class AssetsUtils {


	public static Gson createGson(Assets assets)
	{
		GsonBuilder gb = new GsonBuilder();

		gb.registerTypeAdapter(Area.class, new AreaConverter(assets));
		gb.registerTypeHierarchyAdapter(Event.class, new EventConverter(assets));
		gb.registerTypeAdapter(Texture.class, new TextureConverter());
		gb.registerTypeAdapter(TextureMode.class, new TextureModeConverter());

		Gson gson = gb.setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();

		return gson;
	}



	public static void saveAssets(Assets assets, String saveDirPath)
	{
		Gson gson = createGson(assets);

		Collection<Area> areaList = assets.areaList();
		Collection<Event> eventList = assets.eventList();

		try
		{
			File saveDir = new File(saveDirPath);
			File tempDir = new File("temp");
			if (! tempDir.exists()) tempDir.mkdir();

			File bkSaveDir = new File(tempDir, saveDirPath);
			if (bkSaveDir.exists()) bkSaveDir.delete();
			saveDir.renameTo(bkSaveDir);

			saveDir.mkdir();

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

		System.out.println("Saved Assets");

	}

	public static void saveAsJson(Object obj, Gson gson, File file) throws IOException
	{
		String json = gson.toJson(obj);

		FileWriter fw = new FileWriter(file);
		fw.write(json);

		fw.close();

	}


}
