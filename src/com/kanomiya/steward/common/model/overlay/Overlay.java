package com.kanomiya.steward.common.model.overlay;

import com.kanomiya.steward.common.model.TBLR;
import com.kanomiya.steward.common.model.assets.Assets;
import com.kanomiya.steward.common.model.texture.Texture;
import com.kanomiya.steward.common.view.ViewConsts;

/**
 * @author Kanomiya
 *
 */
public abstract class Overlay {

	public int x, y, width, height;
	public Texture background;
	public boolean visible;

	public Overlay()
	{
		this(0,0, 0,0);
	}

	public Overlay(int x, int y)
	{
		this(x,y, 0,0);
	}

	public Overlay(int x, int y, int width, int height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		visible = false;

	}


	public boolean hasBackground()
	{
		return (background != null);
	}

	/**
	 * @return background
	 */
	public Texture getBackground() {
		return background;
	}


	/**
	 * @param background セットする background
	 */
	public void setBackground(Texture background) {
		this.background = background;
	}

	public boolean contains(int tgtX, int tgtY)
	{
		return x <= tgtX && tgtX < x +width && y <= tgtY && tgtY < y +height;
	}


	public boolean isVisible()
	{
		return visible;
	}

	public void setVisible(boolean bool)
	{
		visible = bool;
	}


	public void autoSize(Assets assets)
	{
		if (! hasBackground()) return ;

		width = background.getWidth();
		height = background.getHeight();

	}

	public void autoLocation(LocationType loc)
	{
		autoLocation(loc, ViewConsts.viewWidth, ViewConsts.viewHeight);
	}

	public void autoLocation(LocationType loc, int regionWidth, int regionHeight)
	{
		if (loc == LocationType.CENTER)
		{
			x = regionWidth /2 -width /2;
			y = regionHeight /2 -height /2;

			return;
		}

		if (loc.has(TBLR.TOP)) y = 0;
		if (loc.has(TBLR.BOTTOM)) y = regionHeight -height;
		if (loc.has(TBLR.LEFT)) x = 0;
		if (loc.has(TBLR.RIGHT)) x = regionWidth -width;

	}


}
