package com.kanomiya.steward.common.model.event;

import com.kanomiya.steward.common.model.ITurnObject;
import com.kanomiya.steward.common.model.assets.Assets;
import com.kanomiya.steward.common.model.icon.Icon;

/**
 * @author Kanomiya
 *
 */
public class Event implements ITurnObject {

	public String areaId;
	public int x, y;

	public Icon icon;

	public Event(String areaId, int x, int y, Icon icon)
	{
		this.areaId = areaId;
		this.x = x;
		this.y = y;

		this.icon = icon;
	}

	/**
	* @inheritDoc
	*/
	@Override
	public void turn(Assets assets) {

	}


	public boolean move(Assets assets, int offsetX, int offsetY)
	{
		if (! assets.getArea(areaId).canEnter(this, x +offsetX, y +offsetY)) return false;

		x += offsetX;
		y += offsetY;

		return true;
	}

	public void warp(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public void travel(String areaId, int x, int y)
	{
		this.x = x;
		this.y = y;
	}


	/**
	 * @return
	 */
	public Icon getIcon() {
		return icon;
	}


}
