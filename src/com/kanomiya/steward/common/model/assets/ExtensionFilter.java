package com.kanomiya.steward.common.model.assets;

import java.io.File;
import java.io.FilenameFilter;

/**
 * @author Kanomiya
 *
 */
public class ExtensionFilter implements FilenameFilter {

	public String suffix;

	public ExtensionFilter(String ext)
	{
		suffix = "." + ext;
	}

	/**
	* @inheritDoc
	*/
	@Override
	public boolean accept(File dir, String name)
	{
		return (name.endsWith(suffix));
	}


}
