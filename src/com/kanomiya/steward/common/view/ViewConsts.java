package com.kanomiya.steward.common.view;

import java.awt.Color;

import com.kanomiya.steward.common.model.icon.Icon;
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
	public static VCIcon vcIcon = new VCIcon();
	public static VCBorder vcBorder = new VCBorder();

	public static VCMode vcMode = new VCMode();

	public static VCSelect vcSelect = new VCSelect();
	public static VCMarker vcMarker = new VCMarker();

	public static Icon iconUnknown = new Icon("unknown.png");

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


}
