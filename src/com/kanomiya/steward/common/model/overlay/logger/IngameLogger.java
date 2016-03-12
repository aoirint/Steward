package com.kanomiya.steward.common.model.overlay.logger;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.util.LinkedList;

import com.google.common.collect.Lists;
import com.kanomiya.steward.common.model.overlay.Overlay;
import com.kanomiya.steward.common.model.texture.Texture;
import com.kanomiya.steward.common.view.ViewConsts;

/**
 * @author Kanomiya
 *
 */
public class IngameLogger extends Overlay {

	public static int lineHeight = 14;
	public static Color textColor = Color.WHITE;
	public static Color effectColor1 = new Color(0x9A, 0xDE, 0x64);
	public static Color effectColor2 = new Color(0xFF, 0xD7, 0x53);
	public static Color effectColor3 = new Color(0xD9, 0x94, 0xE0);
	public static AlphaComposite alpha80 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f);
	public static Font textFont = new Font(Font.MONOSPACED, Font.PLAIN, lineHeight);

	public static int millsToClose = 6000;

	public static int innerWidth = 540;
	public static int innerHeight = 212;

	public LinkedList<LogItem> items;

	public boolean visible;
	protected int millsCount;

	public IngameLogger()
	{
		super(ViewConsts.viewWidth -560, ViewConsts.viewHeight -232);

		items = Lists.newLinkedList();
		setBackground(new Texture("background/overlay/ingameLogger.png"));

		visible = false;
	}

	public void print(String text, Color textColor, boolean lineBreak)
	{
		items.addLast(new LogItem(new PrettyText(text, textColor), lineBreak));
		show(millsToClose);
	}

	protected void print(String text, boolean lineBreak)
	{
		print(text, textColor, lineBreak);
	}

	public void print(String text)
	{
		print(text, false);
	}

	public void println(String text)
	{
		print(text, true);
	}


	/**
	 * @param text
	 * @param textColor
	 */
	public void println(String text, Color textColor)
	{
		print(text, textColor, true);
	}


	public boolean isVisible()
	{
		return visible;
	}

	public void show(int mills)
	{
		millsCount = 0;

		if (isVisible()) return;

		Thread thread = new Thread()
		{
			@Override
			public void run()
			{
				setVisible(true);
				millsCount = 0;

				try {
					while (millsCount < mills)
					{
						millsCount ++;
						sleep(1);
					}
				} catch (InterruptedException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}

				setVisible(false);
			}
		};

		thread.start();
	}

	public void setVisible(boolean bool)
	{
		visible = bool;
	}

}
