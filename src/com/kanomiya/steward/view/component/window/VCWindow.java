package com.kanomiya.steward.view.component.window;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

import com.kanomiya.steward.model.assets.Assets;
import com.kanomiya.steward.model.overlay.GameColor;
import com.kanomiya.steward.model.overlay.InventoryWindow;
import com.kanomiya.steward.model.overlay.window.Window;
import com.kanomiya.steward.model.overlay.window.message.MessageBook;
import com.kanomiya.steward.view.ViewConsts;
import com.kanomiya.steward.view.component.IViewComponent;

/**
 * @author Kanomiya
 *
 */
public class VCWindow implements IViewComponent<Window> {

	public static VCMessageBook vcMessageBook = new VCMessageBook();
	public static VCInventoryWindow vcInventoryWindow = new VCInventoryWindow();

	/**
	* @inheritDoc
	*/
	@Override
	public void paint(Graphics2D g, Window window, Assets assets, int frame)
	{
		g.translate(window.x, window.y);

		g.setComposite(ViewConsts.alpha80);

		if (window.hasBackground()) ViewConsts.vcTexture.paint(g, window.getBackground(), assets, frame);
		else
		{
			Color bgColor = (window.hasBackgroundColor()) ? window.getBackgroundColor() : GameColor.background;

			g.setColor(bgColor);
			g.fillRect(0, 0, window.width, window.height);
		}

		g.setComposite(AlphaComposite.SrcOver);

		if (window instanceof MessageBook) vcMessageBook.paint(g, (MessageBook) window, assets, frame);
		else if (window instanceof InventoryWindow) vcInventoryWindow.paint(g, (InventoryWindow) window, assets, frame);


		g.translate(-window.x, -window.y);
	}


}
