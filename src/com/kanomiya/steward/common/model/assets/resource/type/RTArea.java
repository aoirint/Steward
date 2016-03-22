package com.kanomiya.steward.common.model.assets.resource.type;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.FutureTask;

import com.google.gson.Gson;
import com.kanomiya.steward.common.model.area.Area;
import com.kanomiya.steward.common.model.assets.AssetsUtils;
import com.kanomiya.steward.common.model.assets.filter.ExtensionFilter;

/**
 * @author Kanomiya
 *
 */
public class RTArea extends ResourceType<Area> {

	protected RTArea() {
		super(false, true, "area", new ExtensionFilter("json", true));
	}

	/**
	* @inheritDoc
	*/
	@Override
	public Area load(String id, File file, Gson gson, List<FutureTask> futureTaskList) throws IOException {
		return gson.fromJson(new FileReader(file), Area.class);
	}

	/**
	* @inheritDoc
	*/
	@Override
	public void save(Area resource, File file, Gson gson) throws IOException
	{
		AssetsUtils.saveAsJson(resource, gson, file);
	}




}
