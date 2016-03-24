package com.kanomiya.steward.model.assets.resource.type;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

import com.google.gson.Gson;
import com.kanomiya.steward.model.area.Tip;
import com.kanomiya.steward.model.assets.Assets;
import com.kanomiya.steward.model.assets.AssetsUtils;
import com.kanomiya.steward.util.filter.ExtensionFilter;

/**
 * @author Kanomiya
 *
 */
public class RTTip extends ResourceType<Tip> {

	protected RTTip() {
		super(false, true, "tip", new ExtensionFilter("json", true));
	}


	/**
	* @inheritDoc
	*/
	@Override
	public Tip load(String id, File file, Gson gson, List<Consumer<Assets>> futureTaskList) throws IOException
	{
		FileReader fr = new FileReader(file);
		Tip tip = gson.fromJson(fr, Tip.class);

		return tip;
	}

	/**
	* @inheritDoc
	*/
	@Override
	public void save(Tip resource, File baseDir, Gson gson) throws IOException
	{
		File file = new File(baseDir, resource.getId() + ".json");

		if (! file.getParentFile().exists()) file.getParentFile().mkdirs();

		AssetsUtils.saveAsJson(resource, gson, file);
	}




}
