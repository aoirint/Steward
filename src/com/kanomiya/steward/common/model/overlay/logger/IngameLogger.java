package com.kanomiya.steward.common.model.overlay.logger;

import com.kanomiya.steward.common.model.overlay.PrettyText;
import com.kanomiya.steward.common.model.overlay.message.MessageBox;
import com.kanomiya.steward.common.model.texture.Texture;
import com.kanomiya.steward.common.view.ViewConsts;

/**
 * @author Kanomiya
 *
 */
public class IngameLogger extends MessageBox {

	public static int millsWait = 5000;

	public static int innerWidth = 520;
	public static int innerHeight = 212;
	public static int oneHeight = 14;

	protected int millsCountShow, millsCountLock;

	public boolean autoCloseLock;
	public boolean autoScroolLock;

	public IngameLogger()
	{
		super(ViewConsts.viewWidth -560, ViewConsts.viewHeight -232, 560, 232);

		setBackground(new Texture("background/overlay/ingameLogger.png", true));

		autoCloseLock = false;
		autoScroolLock = false;
	}

	@Override
	public void print(PrettyText text)
	{
		super.print(text);

		if (! autoScroolLock) beginIndex = Math.max(0, items.size() -IngameLogger.oneHeight +2);

		show(millsWait);
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
