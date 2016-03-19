package com.kanomiya.steward.common.model.script;

import java.util.Map;

import com.google.gson.annotations.Expose;


/**
 * @author Kanomiya
 *
 */
public class Script {

	@Expose public String src;
	@Expose public Map<String, Object> args;

	public Script(String src)
	{
		this.src = src;
	}

	public boolean hasArgument()
	{
		return (args != null && ! args.isEmpty());
	}

}
