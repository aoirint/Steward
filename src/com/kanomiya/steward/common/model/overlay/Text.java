package com.kanomiya.steward.common.model.overlay;

import java.awt.Color;

/**
 * @author Kanomiya
 *
 */
public class Text {
	public static int lineHeight = 14;

	public String text;
	public Color color;
	public boolean underline;
	public boolean bold;
	public boolean lineBreak;

	public static Text create(String text)
	{
		return new Text(text);
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



}
