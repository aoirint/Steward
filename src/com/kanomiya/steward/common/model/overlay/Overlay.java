package com.kanomiya.steward.common.model.overlay;

import com.kanomiya.steward.common.model.texture.Texture;

/**
 * @author Kanomiya
 *
 */
public abstract class Overlay {

	public int x, y;
	public Texture background;

	public Overlay(int x, int y)
	{
		this.x = x;
		this.y = y;
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



}
