package com.kanomiya.steward.common.model.assets.resource.type;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.FutureTask;

import com.google.gson.Gson;
import com.kanomiya.steward.common.model.area.Tip;
import com.kanomiya.steward.common.model.assets.filter.ExtensionFilter;

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
	public Tip load(String id, File file, Gson gson, List<FutureTask> futureTaskList) throws IOException
	{
		FileReader fr = new FileReader(file);
		Tip tip = gson.fromJson(fr, Tip.class);

		return tip;
	}

	/**
	* @inheritDoc
	*/
	@Override
	public void save(Tip resource, File file, Gson gson) throws IOException
	{
		// TODO 自動生成されたメソッド・スタブ

	}




}
