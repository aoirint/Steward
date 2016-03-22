package com.kanomiya.steward.common.model.assets.filter;

import java.io.File;
import java.io.FileFilter;

/**
 * @author Kanomiya
 *
 */
public class ExtensionFilter implements FileFilter {

	public String suffix;
	boolean flag;

	public ExtensionFilter(String ext, boolean flag)
	{
		suffix = "." + ext;
		this.flag = flag;
	}

	/**
	* @inheritDoc
	*/
	@Override
	public boolean accept(File pathname) {
		return pathname.getName().endsWith(suffix) == flag;
	}


}
