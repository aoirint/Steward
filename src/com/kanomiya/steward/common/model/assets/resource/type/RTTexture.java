package com.kanomiya.steward.common.model.assets.resource.type;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.FutureTask;

import com.google.gson.Gson;
import com.kanomiya.steward.common.model.assets.filter.ExtensionFilter;
import com.kanomiya.steward.common.model.texture.Texture;

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
	public void save(Texture resource, File file, Gson gson) throws IOException
	{
		// TODO 自動生成されたメソッド・スタブ

	}




}
