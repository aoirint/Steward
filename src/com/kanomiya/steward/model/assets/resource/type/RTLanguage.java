package com.kanomiya.steward.model.assets.resource.type;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.function.Consumer;

import com.google.gson.Gson;
import com.kanomiya.steward.model.assets.Assets;
import com.kanomiya.steward.model.lang.Language;
import com.kanomiya.steward.util.filter.ExtensionFilter;

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
	public Language load(String id, File file, Gson gson, List<Consumer<Assets>> futureTaskList) throws IOException
	{
		PropertyResourceBundle bundle = new PropertyResourceBundle(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));

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
	public void save(Language resource, File baseDir, Gson gson) throws IOException
	{
		File file = new File(baseDir, resource.getId() + ".lang");
		FileWriter fw = new FileWriter(file);

		Properties props = new Properties();
		Iterator<String> keyItr = resource.mappings.keySet().iterator();

		while (keyItr.hasNext())
		{
			String key = keyItr.next();
			props.setProperty(key, resource.mappings.getString(key));
		}

		if (! file.getParentFile().exists()) file.getParentFile().mkdirs();

		props.store(fw, "Steward Language Property File");

		fw.close();

	}




}
