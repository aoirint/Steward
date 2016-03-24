package com.kanomiya.steward.model.assets.resource.type;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

import com.google.gson.Gson;
import com.kanomiya.steward.model.area.Area;
import com.kanomiya.steward.model.assets.Assets;
import com.kanomiya.steward.model.assets.AssetsUtils;
import com.kanomiya.steward.util.filter.ExtensionFilter;

/**
 * @author Kanomiya
 *
 */
public class RTArea extends ResourceType<Area> {

	protected RTArea() {
		super(true, true, "area", new ExtensionFilter("json", true));
	}

	/**
	* @inheritDoc
	*/
	@Override
	public Area load(String id, File file, Gson gson, List<Consumer<Assets>> futureTaskList) throws IOException {
		return gson.fromJson(new FileReader(file), Area.class);
	}

	/**
	* @inheritDoc
	*/
	@Override
	public void save(Area resource, File baseDir, Gson gson) throws IOException
	{
		File file = new File(baseDir, resource.getId() + ".json");

		if (! file.getParentFile().exists()) file.getParentFile().mkdirs();

		AssetsUtils.saveAsJson(resource, gson, file);
	}




}
