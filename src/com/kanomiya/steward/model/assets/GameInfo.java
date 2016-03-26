package com.kanomiya.steward.model.assets;

import com.google.gson.annotations.Expose;

/**
 * @author Kanomiya
 *
 */
public class GameInfo {

	@Expose protected String name;


	public GameInfo()
	{
		name = "Unknown Game";
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name セットする name
	 */
	public void setName(String name) {
		this.name = name;
	}


}
