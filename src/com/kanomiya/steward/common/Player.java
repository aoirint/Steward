package com.kanomiya.steward.common;

import com.kanomiya.steward.common.model.ITurnObject;

/**
 * @author Kanomiya
 *
 */
public class Player implements ITurnObject {

	public String areaId;
	public int x, y;

	public Player(String areaId, int x, int y)
	{
		this.areaId = areaId;
		this.x = x;
		this.y = y;

	}


	/**
	* @inheritDoc
	*/
	@Override public void turn() {

	}



}
