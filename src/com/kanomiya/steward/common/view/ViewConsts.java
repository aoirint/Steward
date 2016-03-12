package com.kanomiya.steward.common.view;

import java.awt.Color;
import java.awt.Graphics;

import com.kanomiya.steward.common.model.texture.Texture;
import com.kanomiya.steward.editor.view.ViewTips;

/**
 * @author Kanomiya
 *
 */
public class ViewConsts {

	public static int frameLifeMills = 16;

	public static int tileSize = 32;
	public static int viewWidth = 800;
	public static int viewHeight = 608;

	public static int tileCols = viewWidth /tileSize;
	public static int tileRows = viewHeight /tileSize;

	public static int maxFrame = 16;
	public static ViewGame viewGame = new ViewGame();

	public static VCPlayerEye viewPlayerEye = new VCPlayerEye();

	public static VCArea viewArea = new VCArea();
	public static VCTexture vcTexture = new VCTexture();
	public static VCBorder vcBorder = new VCBorder();

	public static VCIngameLogger vcIngameLogger = new VCIngameLogger();
	public static VCDebug vcStat = new VCDebug();

	public static VCSelect vcSelect = new VCSelect();
	public static VCMarker vcMarker = new VCMarker();

	public static Texture iconUnknown = new Texture("unknown.png");

	public static Color colorSelected = new Color(0xFF, 0x00, 0xFF, 0xDD);
	public static Color colorFocused = new Color(0xFF, 0x00, 0xFF, 0xAA);


	public static ViewTips viewTips = new ViewTips();


	public static int getCamX(int x, int width)
	{
		int camX = x -ViewConsts.tileCols /2;
		if (camX < 0) return 0;

		int r = width -ViewConsts.tileCols;
		if (r < camX) return -r;

		return -camX;
	}

	public static int getCamY(int y, int height)
	{
		int camY = y -ViewConsts.tileRows /2;
		if (camY < 0) return 0;

		int b = height -ViewConsts.tileRows;
		if (b < camY) return -b;

		return -camY;

	}



	/**
	 *
	 * 折り返すべき場所に改行(\n)を挿入します
	 *
	 * @param text
	 * @param g
	 * @param beginLeft
	 * @param maxWidth
	 * @return
	 */
	public static String wordWrap(String text, Graphics g, int beginLeft, int maxWidth)
	{
		int textLen = text.length();
		int width = g.getFontMetrics().stringWidth(text);
		int right = beginLeft +width;


		if (maxWidth < right) // 折り返し
		{
			StringBuilder stack = new StringBuilder(textLen +20);
			StringBuilder builder = null;

			int dx = beginLeft;

			for (int i=0; i<textLen; i++)
			{
				if (builder == null) builder = new StringBuilder(textLen);

				builder.append(text.charAt(i));

				int dw = g.getFontMetrics().stringWidth(builder.toString());
				int dr = dx +dw;

				if (maxWidth -20 < dr)
				{
					stack.append(builder);
					stack.append('\n');
					builder = null;
				}

			}

			if (builder != null) stack.append(builder);

			text = stack.toString();
		}

		return text;
	}







}
