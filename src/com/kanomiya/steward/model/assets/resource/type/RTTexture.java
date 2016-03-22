package com.kanomiya.steward.model.assets.resource.type;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.FutureTask;

import com.google.gson.Gson;
import com.kanomiya.steward.model.assets.AssetsUtils;
import com.kanomiya.steward.model.texture.Texture;
import com.kanomiya.steward.util.filter.ExtensionFilter;

/**
 * @author Kanomiya
 *
 */
public class RTTexture extends ResourceType<Texture> {

	protected RTTexture() {
		super(false, true, "texture", new ExtensionFilter("json", true));
	}

	/**
	* @inheritDoc
	*/
	@Override
	public Texture load(String id, File file, Gson gson, List<FutureTask> futureTaskList) throws IOException
	{
		return gson.fromJson(new FileReader(file), Texture.class);
	}

	/**
	* @inheritDoc
	*/
	@Override
	public void save(Texture resource, File baseDir, Gson gson) throws IOException
	{
		File file = new File(baseDir, resource.getId() + ".json");

		if (! file.getParentFile().exists()) file.getParentFile().mkdirs();

		AssetsUtils.saveAsJson(resource, gson, file);
	}




}
