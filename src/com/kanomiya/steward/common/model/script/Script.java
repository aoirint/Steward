package com.kanomiya.steward.common.model.script;

import com.google.gson.annotations.Expose;


/**
 * @author Kanomiya
 *
 */
public class Script {

	@Expose public String src;

	public Script(String src)
	{
		this.src = src;
	}


}
