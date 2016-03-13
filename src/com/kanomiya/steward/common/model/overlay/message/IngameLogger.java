package com.kanomiya.steward.common.model.overlay.message;

import com.kanomiya.steward.common.model.assets.Assets;
import com.kanomiya.steward.common.model.overlay.LocationType;
import com.kanomiya.steward.common.model.overlay.PrettyText;
import com.kanomiya.steward.common.model.texture.Texture;

/**
 * @author Kanomiya
 *
 */
public class IngameLogger extends MessageBook {

	public static int millsWait = 5000;

	public static int innerWidth = 520;
	public static int innerHeight = 212;
	public static int oneHeight = 14;
	public static String bgTextureSrc = "background/overlay/ingameLogger.png";
	public static Texture bgTexture = new Texture(bgTextureSrc);

	protected int millsCountShow, millsCountLock;

	public boolean autoCloseLock;
	public boolean autoScroolLock;

	public IngameLogger(Assets assets)
	{
		super();

		setBackground(bgTexture);

		autoCloseLock = false;
		autoScroolLock = false;

		autoSize(assets);
		autoLocation(LocationType.BOTTOM_RIGHT);

		append(new Message());
	}



	public IngameLogger print(PrettyText text)
	{
		super.print(0, text);

		if (! autoScroolLock) beginIndex = Math.max(0, itemCount(0) -IngameLogger.oneHeight +2);

		show(millsWait);

		return this;
	}

	public IngameLogger println(PrettyText text)
	{
		return print(text.lineBreak());
	}

	public IngameLogger print(String text)
	{
		return print(PrettyText.create(text));
	}

	public IngameLogger println(String text)
	{
		return print(PrettyText.create(text).lineBreak());
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

	@Override
	public void setVisible(boolean bool)
	{
		super.setVisible(bool);

		autoCloseLock = false;
		autoScroolLock = false;
	}



}
