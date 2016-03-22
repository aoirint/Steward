package com.kanomiya.steward.common.model.assets.resource.type;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.FutureTask;

import com.google.gson.Gson;
import com.kanomiya.steward.common.model.assets.filter.ExtensionFilter;
import com.kanomiya.steward.common.model.script.ScriptCode;

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
	public ScriptCode load(String id, File file, Gson gson, List<FutureTask> futureTaskList) throws IOException
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
	public void save(ScriptCode resource, File file, Gson gson) throws IOException
	{
		// TODO 自動生成されたメソッド・スタブ

	}




}
