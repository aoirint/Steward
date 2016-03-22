package com.kanomiya.steward.common.model.assets.resource.type;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.concurrent.FutureTask;

import com.google.gson.Gson;
import com.kanomiya.steward.common.model.assets.filter.ExtensionFilter;
import com.kanomiya.steward.common.model.lang.Language;

/**
 * @author Kanomiya
 *
 */
public class RTLanguage extends ResourceType<Language> {

	protected RTLanguage() {
		super(false, true, "lang", new ExtensionFilter("lang", true));
	}

	/**
	* @inheritDoc
	*/
	@Override
	public Language load(String id, File file, Gson gson, List<FutureTask> futureTaskList) throws IOException
	{
		PropertyResourceBundle bundle = new PropertyResourceBundle(new FileReader(file));

		String[] tags = id.split("_");
		if (tags.length < 2) return null;

		Locale.Builder builder = new Locale.Builder().setLanguage(tags[0]).setRegion(tags[1]);
		if (3 <= tags.length) builder.setVariant(tags[2]);

		return new Language(id, builder.build(), bundle);
	}

	/**
	* @inheritDoc
	*/
	@Override
	public void save(Language resource, File file, Gson gson) throws IOException
	{
		// TODO 自動生成されたメソッド・スタブ

	}




}
