package com.kanomiya.steward.model.assets.resource.type;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.function.Consumer;

import com.google.gson.Gson;
import com.kanomiya.steward.model.assets.Assets;
import com.kanomiya.steward.model.assets.AssetsUtils;
import com.kanomiya.steward.model.script.ScriptCode;
import com.kanomiya.steward.util.filter.ExtensionFilter;

/**
 * @author Kanomiya
 *
 */
public class RTScriptCode extends ResourceType<ScriptCode> {

	protected RTScriptCode() {
		super(false, true, "script", new ExtensionFilter("js", true));
	}

	/**
	* @inheritDoc
	*/
	@Override
	public ScriptCode load(String id, File file, Gson gson, List<Consumer<Assets>> futureTaskList) throws IOException
	{
		InputStreamReader isrScript = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);

		StringBuilder sb = new StringBuilder();

		while (isrScript.ready())
		{
			sb.appendCodePoint(isrScript.read());
		}

		isrScript.close();

		return new ScriptCode(id, sb.toString());
	}

	/**
	* @inheritDoc
	*/
	@Override
	public void save(ScriptCode resource, File baseDir, Gson gson) throws IOException
	{
		File file = new File(baseDir, resource.getId() + ".js");

		if (! file.getParentFile().exists()) file.getParentFile().mkdirs();

		AssetsUtils.saveString(resource.code, file);

	}




}
