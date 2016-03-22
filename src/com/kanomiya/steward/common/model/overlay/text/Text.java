package com.kanomiya.steward.common.model.overlay.text;

import java.awt.Color;

import com.kanomiya.steward.common.model.overlay.GameColor;
import com.kanomiya.steward.common.model.texture.Texture;

/**
 * @author Kanomiya
 *
 */
public class Text implements IText { // TODO PrettyText??
	public static int defaultHeight = 14;

	public String text;
	public Color color;
	public boolean underline;
	public boolean bold;
	public boolean lineBreak;
	public Texture icon, background;

	public static Text create(String text)
	{
		return new Text(text);
	}


	public Text()
	{
		this("");
	}

	public Text(String text)
	{
		this.text = text;
		color = GameColor.white;

	}

	public Text(Text copyFrom)
	{
		text = copyFrom.text; // VELIF: プリミティブコピーの方が良?
		text(copyFrom.text).color(copyFrom.color);

		underline = copyFrom.underline;
		bold = copyFrom.bold;
		lineBreak = copyFrom.lineBreak;
	}

	/**
	 * @param text セットする text
	 */
	public Text text(String text)
	{
		this.text = text;
		return this;
	}

	/**
	 * @param color セットする color
	 */
	public Text color(Color color) {
		this.color = color;
		return this;
	}

	public Text underline() {
		underline = true;
		return this;
	}

	public Text bold() {
		bold = true;
		return this;
	}

	public Text lineBreak() {
		lineBreak = true;
		return this;
	}

	public Text background(Texture background)
	{
		this.background = background;
		return this;
	}

	public boolean hasBackground()
	{
		return (background != null);
	}

	public Text icon(Texture icon)
	{
		this.icon = icon;
		return this;
	}

	public boolean hasIcon()
	{
		return (icon != null);
	}


	public int getLineHeight()
	{
		int height = Text.defaultHeight;

		if (hasBackground()) height = Math.max(height, background.getHeight());
		if (hasIcon()) height = Math.max(height, icon.getHeight());

		return height;
	}



	/**
	* @inheritDoc
	*/
	@Override
	public String getTextString() {
		return text;
	}

	/**
	* @inheritDoc
	*/
	@Override
	public void setTextString(String text) {
		text(text);
	}



}
