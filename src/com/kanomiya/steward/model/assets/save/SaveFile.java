package com.kanomiya.steward.model.assets.save;

import java.io.File;
import java.util.List;

import com.google.common.collect.Lists;
import com.kanomiya.steward.model.assets.AssetsUtils;
import com.kanomiya.steward.util.filter.DirectoryFilter;

/**
 * @author Kanomiya
 *
 */
public class SaveFile {

	public String name, path;

	public SaveFile(String name, String path)
	{
		this.name = name;
		this.path = path;
	}


	public static SaveFile create(String name)
	{
		return new SaveFile(name, new File(AssetsUtils.savesDir, name).getPath());
	}

	public static List<SaveFile> saveFiles()
	{
		File fSavesDir = new File(AssetsUtils.savesDir);
		List<SaveFile> saveFileList = Lists.newArrayList();

		if (! fSavesDir.exists()) return saveFileList;

		File[] dirs = fSavesDir.listFiles(DirectoryFilter.SIMPLE_DIRECTORY_FILTER);

		for (int i=0; i<dirs.length; i++)
		{
			saveFileList.add(new SaveFile(dirs[i].getName(), dirs[i].getPath()));
		}

		return saveFileList;
	}

}
