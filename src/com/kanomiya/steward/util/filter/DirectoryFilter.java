package com.kanomiya.steward.util.filter;

import java.io.File;
import java.io.FileFilter;

/**
 * @author Kanomiya
 *
 */
public class DirectoryFilter implements FileFilter {

	public static DirectoryFilter SIMPLE_DIRECTORY_FILTER = new DirectoryFilter();

	boolean enable_pre, enable_suf;
	String prefix, suffix;
	boolean pre_flag, suf_flag;

	public DirectoryFilter()
	{
		enable_pre = enable_suf = false;
	}

	public DirectoryFilter(String prefix, boolean pre_flag, String suffix, boolean suf_flag)
	{
		enable_pre = enable_suf = true;

		this.prefix = prefix;
		this.pre_flag = pre_flag;

		this.suffix = suffix;
		this.suf_flag = suf_flag;
	}

	/**
	* @inheritDoc
	*/
	@Override
	public boolean accept(File pathname) {
		if (! pathname.isDirectory()) return false;

		String name = pathname.getName();
		return (! enable_pre || name.startsWith(suffix) == pre_flag) && (! enable_suf || name.endsWith(suffix) == suf_flag);
	}


}
