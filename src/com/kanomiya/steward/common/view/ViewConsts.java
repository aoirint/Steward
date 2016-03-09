package com.kanomiya.steward.common.view;

import com.kanomiya.steward.common.model.icon.Icon;

/**
 * @author Kanomiya
 *
 */
public class ViewConsts {

	public static int frameLifeMills = 16;

	public static int tileSize = 32;
	public static int viewWidth = 800;
	public static int viewHeight = 600;

	public static int tileCols = viewWidth /tileSize;
	public static int tileRows = viewHeight /tileSize;

	public static int maxFrame = 16;
	public static ViewGame viewGame = new ViewGame();

	public static VCPlayerEye viewPlayerEye = new VCPlayerEye();

	public static VCArea viewArea = new VCArea();
	public static VCIcon vcIcon = new VCIcon();

	public static Icon iconUnknown = new Icon("unknown.png");



}
