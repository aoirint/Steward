package com.kanomiya.steward.common.model.assets;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kanomiya.steward.common.model.area.Area;
import com.kanomiya.steward.common.model.area.AreaConverter;

/**
 * @author Kanomiya
 *
 */
public class AssetsUtils {


	public static Gson createGson(Assets assets)
	{
		GsonBuilder gb = new GsonBuilder();

		gb.registerTypeAdapter(Area.class, new AreaConverter(assets));

		Gson gson = gb.setPrettyPrinting().create();

		return gson;
	}



	public static void saveAssets(Assets assets)
	{
		Gson gson = createGson(assets);

		Collection<Area> areaList = assets.areaList();

		try
		{
			for (Area area: areaList)
			{
				saveAsJson(area, gson, new File(assets.assetsDir, "area/" + area.getId() + ".json"));
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
