package com.kanomiya.steward.common.model.overlay;

import java.awt.Color;
import java.awt.Font;

/**
 * @author Kanomiya
 *
 */
public class PrettyText {
	public static int lineHeight = 14;

	public static Font textFont = new Font(Font.MONOSPACED, Font.PLAIN, PrettyText.lineHeight);
	public static Font textFontBold = new Font(Font.MONOSPACED, Font.BOLD, PrettyText.lineHeight);
	public static Color textColor = Color.WHITE;
	public static Color colorPurple = new Color(0xD9, 0x94, 0xE0);
	public static Color colorOrange = new Color(0xFF, 0xD7, 0x53);
	public static Color colorGreen = new Color(0x9A, 0xDE, 0x64);


	public String text;
	public Color color;
	public boolean underline;
	public boolean bold;
	public boolean lineBreak;

	public static PrettyText create(String text)
	{
		return new PrettyText(text);
	}



	public PrettyText(String text)
	{
		this.text = text;
		color = PrettyText.textColor;

	}

	/**
	 * @param text セットする text
	 */
	public PrettyText text(String text)
	{
		this.text = text;
		return this;
	}

	/**
	 * @param color セットする color
	 */
	public PrettyText color(Color color) {
		this.color = color;
		return this;
	}

	public PrettyText underline() {
		underline = true;
		return this;
	}

	public PrettyText bold() {
		bold = true;
		return this;
	}

	public PrettyText lineBreak() {
		lineBreak = true;
		return this;
	}




}
