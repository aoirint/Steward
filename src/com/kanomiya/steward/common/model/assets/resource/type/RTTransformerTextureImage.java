package com.kanomiya.steward.common.model.assets.resource.type;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.FutureTask;

import com.google.gson.Gson;
import com.kanomiya.steward.common.model.assets.AssetsUtils;
import com.kanomiya.steward.common.model.texture.TransformerTextureImage;
import com.kanomiya.steward.common.util.filter.ExtensionFilter;

/**
 * @author Kanomiya
 *
 */
public class RTTransformerTextureImage extends ResourceType<TransformerTextureImage> {

	protected RTTransformerTextureImage() {
		super(false, true, "texture/image", new ExtensionFilter("json", true));
	}

	/**
	* @inheritDoc
	*/
	@Override
	public TransformerTextureImage load(String id, File file, Gson gson, List<FutureTask> futureTaskList) throws IOException
	{
		return gson.fromJson(new FileReader(file), TransformerTextureImage.class);
	}

	/**
	* @inheritDoc
	*/
	@Override
	public void save(TransformerTextureImage resource, File file, Gson gson) throws IOException
	{
		AssetsUtils.saveAsJson(resource, gson, file);
	}




}
