package com.kanomiya.steward.model.script;

import com.google.gson.annotations.Expose;
import com.kanomiya.steward.model.assets.resource.IResource;


/**
 * @author Kanomiya
 *
 */
public class ScriptCode implements IResource {

	@Expose public String id;
	@Expose public String code;


	public ScriptCode(String id, String code)
	{
		this.id = id;
		this.code = code;
	}

	/**
	* @inheritDoc
	*/
	@Override
	public String getId() {
		return id;
	}


}
