package com.kanomiya.steward.common.model.overlay.logger;

import java.awt.AlphaComposite;
import java.util.LinkedList;

import com.google.common.collect.Lists;
import com.kanomiya.steward.common.model.overlay.Overlay;
import com.kanomiya.steward.common.model.overlay.PrettyText;
import com.kanomiya.steward.common.model.texture.Texture;
import com.kanomiya.steward.common.view.ViewConsts;

/**
 * @author Kanomiya
 *
 */
public class IngameLogger extends Overlay {

	public static AlphaComposite alpha80 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f);
	public static int millsWait = 5000;

	public static int innerWidth = 520;
	public static int innerHeight = 212;
	public static int oneHeight = 14;

	public LinkedList<PrettyText> items;

	public boolean visible;
	protected int millsCountShow, millsCountLock;

	public int beginIndex;
	public boolean autoCloseLock;
	public boolean autoScroolLock;

	public IngameLogger()
	{
		super(ViewConsts.viewWidth -560, ViewConsts.viewHeight -232, 560, 232);

		items = Lists.newLinkedList();
		setBackground(new Texture("background/overlay/ingameLogger.png", width, height));

		visible = false;
		autoCloseLock = false;
		autoScroolLock = false;
	}

	public void print(PrettyText text)
	{
		items.addLast(text);

		if (! autoScroolLock) beginIndex = Math.max(0, items.size() -IngameLogger.oneHeight +2);

		show(millsWait);
	}

	public void println(PrettyText text)
	{
		print(text.lineBreak());
	}

	public void print(String text)
	{
		print(PrettyText.create(text));
	}

	public void println(String text)
	{
		print(PrettyText.create(text).lineBreak());
	}



	public int getTopIndexToShow()
	{
		return beginIndex;
	}

	public int getLastIndexToShow()
	{
		return Math.min(beginIndex +IngameLogger.oneHeight, items.size());
	}

	public boolean isVisible()
	{
		return visible;
	}

	public void show(int mills)
	{
		millsCountShow = 0;

		if (isVisible()) return;

		Thread thread = new Thread("IGL_AutoClose")
		{
			@Override
			public void run()
			{
				setVisible(true);
				millsCountShow = 0;

				try {
					while (millsCountShow < mills)
					{
						if (autoCloseLock) break;

						millsCountShow ++;
						sleep(1);
					}
				} catch (InterruptedException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}

				if (! autoCloseLock) setVisible(false);
			}
		};

		thread.start();
	}

	public void autoScrollLock(int mills)
	{
		millsCountLock = 0;

		if (autoScroolLock) return;

		Thread thread = new Thread("IGL_AutoScrollLock")
		{
			@Override
			public void run()
			{
				autoScroolLock = true;
				millsCountLock = 0;

				try {
					while (millsCountLock < mills)
					{
						millsCountLock ++;
						sleep(1);
					}
				} catch (InterruptedException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}

				autoScroolLock = false;
			}
		};

		thread.start();
	}

	public void setVisible(boolean bool)
	{
		visible = bool;
		autoCloseLock = false;
		autoScroolLock = false;
	}

}
