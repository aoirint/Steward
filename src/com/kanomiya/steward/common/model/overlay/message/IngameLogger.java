package com.kanomiya.steward.common.model.overlay.message;

import com.kanomiya.steward.common.model.assets.Assets;
import com.kanomiya.steward.common.model.overlay.LocationType;
import com.kanomiya.steward.common.model.overlay.Text;
import com.kanomiya.steward.common.model.texture.Texture;

/**
 * @author Kanomiya
 *
 */
public class IngameLogger extends MessageBook {

	public static int millsWait = 5000;

	public static String bgTextureSrc = "background/overlay/ingameLogger.png";
	public static Texture bgTexture = new Texture(bgTextureSrc);

	protected int millsCountShow, millsCountLock;

	public boolean autoCloseLock;
	public boolean autoLastLock;

	public IngameLogger(Assets assets)
	{
		super();

		setBackground(bgTexture);

		autoCloseLock = false;
		autoLastLock = false;

		width = 560;
		height = 232;

		autoSize(assets);
		autoLocation(LocationType.BOTTOM_RIGHT);

		append(new Message());
	}



	@Override
	public IngameLogger print(Text text)
	{
		Message last = get(getLastPageIndex());

		if (14 < last.itemCount() +1)
		{
			append(Message.create());
		}

		super.print(getLastPageIndex(), text);
		if (! autoLastLock) currentPageIndex = pageCount() -1;

		show(millsWait);

		return this;
	}

	@Override
	public IngameLogger println(Text text)
	{
		return print(text.lineBreak());
	}

	@Override
	public IngameLogger print(String text)
	{
		return print(Text.create(text));
	}

	@Override
	public IngameLogger println(String text)
	{
		return print(Text.create(text).lineBreak());
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

	public void autoLastLock(int mills)
	{
		millsCountLock = 0;

		if (autoLastLock) return;

		Thread thread = new Thread("IGL_AutoLastLock")
		{
			@Override
			public void run()
			{
				autoLastLock = true;
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

				autoLastLock = false;
			}
		};

		thread.start();
	}

	@Override
	public void setVisible(boolean bool)
	{
		super.setVisible(bool);

		autoCloseLock = false;
		autoLastLock = false;
	}


	@Override
	public int innerWidth()
	{
		return 520;
	}

	@Override
	public int innerHeight()
	{
		return 212;
	}

}
