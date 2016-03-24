package com.kanomiya.steward.model.assets.resource.type;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

import com.google.gson.Gson;
import com.kanomiya.steward.model.assets.Assets;
import com.kanomiya.steward.model.assets.resource.IResource;

/**
 * @author Kanomiya
 *
 */
public abstract class ResourceType<R extends IResource> {

	/*
	 *
	TEXTURE(false, true, "texture"),
	TIP(false, true, "tip"),
	SCRIPT(false, true, "script"),

	LANGUAGE(false, false, "lang"),
	ITEM(false, true, "item"),
	AREA(true, true, "area"),
	EVENT(true, true, "event"),

	 *
	 */

	public static RTTextureImage rtTextureImage = new RTTextureImage();
	public static RTTransformerTextureImage rtTransformerTextureImage = new RTTransformerTextureImage();
	public static RTTexture rtTexture = new RTTexture();
	public static RTTip rtTip = new RTTip();
	public static RTScriptCode rtScriptCode = new RTScriptCode();
	public static RTItem rtItem = new RTItem();
	public static RTArea rtArea = new RTArea();
	public static RTEvent rtEvent = new RTEvent();
	public static RTLanguage rtLanguage = new RTLanguage();


	protected boolean enableSave, enableExclusive;
	protected String dirName;
	protected FileFilter fileFilter;

	/**
	 * @param enableSave
	 * @param enableExclusive
	 * @param dirName
	 */
	protected ResourceType(boolean enableSave, boolean enableExclusive, String dirName, FileFilter fileFilter)
	{
		this.enableSave = enableSave;
		this.enableExclusive = enableExclusive;
		this.dirName = dirName;
		this.fileFilter = fileFilter;
	}

	public abstract R load(String id, File file, Gson gson, List<Consumer<Assets>> futureTaskList) throws IOException;
	public abstract void save(R resource, File baseDir, Gson gson) throws IOException;

	public boolean enableSave() { return enableSave; }
	public boolean enableExclusive() { return enableExclusive; }

	public String getDirName() { return dirName; }

	public boolean hasFilter() { return fileFilter != null; }
	public FileFilter getFilter() { return fileFilter; }


}
