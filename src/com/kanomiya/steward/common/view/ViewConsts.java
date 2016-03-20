package com.kanomiya.steward.common.view;

import java.awt.AlphaComposite;
import java.awt.Color;

import com.kanomiya.steward.common.model.texture.Texture;
import com.kanomiya.steward.common.view.component.VCArea;
import com.kanomiya.steward.common.view.component.VCBorder;
import com.kanomiya.steward.common.view.component.VCDebug;
import com.kanomiya.steward.common.view.component.VCMarker;
import com.kanomiya.steward.common.view.component.VCPlayerEye;
import com.kanomiya.steward.common.view.component.VCSelect;
import com.kanomiya.steward.common.view.component.VCTexture;
import com.kanomiya.steward.common.view.component.window.VCWindow;
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

	public static VCWindow vcWindow = new VCWindow();
	public static VCDebug vcStat = new VCDebug();

	public static VCSelect vcSelect = new VCSelect();
	public static VCMarker vcMarker = new VCMarker();

	public static Texture texUnknown = new Texture("unknown.png");
	public static Texture texArrowBtnUp = new Texture("arrowButtonUp.png");
	public static Texture texArrowBtnDown = new Texture("arrowButtonDown.png");
	public static Texture texArrowBtnRight = new Texture("arrowButtonRight.png");
	public static Texture texArrowBtnLeft = new Texture("arrowButtonLeft.png");
	public static Texture texCheck = new Texture("check.png");

	public static Color colorSelected = new Color(0xFF, 0x00, 0xFF, 0xDD);
	public static Color colorFocused = new Color(0xFF, 0x00, 0xFF, 0xAA);


	public static ViewTips viewTips = new ViewTips();


	public static AlphaComposite alpha80 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f);
	public static AlphaComposite halfBlend = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);









}
