package com.kanomiya.steward.common.controller.unit.state;

import com.kanomiya.steward.common.model.IRegion;

/**
 * @author Kanomiya
 *
 */
public class InputStatePointer implements IInputState {

	protected int x, y;


	public InputStatePointer()
	{
		x = -1;
		y = -1;
	}

	public boolean isContainedIn(int x, int y, int width, int height)
	{
		return (x <= this.x && this.x < x +width && y <= this.y && this.y < y +height);
	}

	public boolean isContainedIn(IRegion region)
	{
		return isContainedIn(region.getX(), region.getY(), region.getWidth(), region.getHeight());
	}



	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	public void point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

}
