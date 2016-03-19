package com.kanomiya.steward.common.view.component.window;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Iterator;

import com.kanomiya.steward.common.model.assets.Assets;
import com.kanomiya.steward.common.model.item.ItemStack;
import com.kanomiya.steward.common.model.overlay.GameColor;
import com.kanomiya.steward.common.model.overlay.GameFont;
import com.kanomiya.steward.common.model.overlay.InventoryWindow;
import com.kanomiya.steward.common.model.overlay.text.Text;
import com.kanomiya.steward.common.view.component.IViewComponent;

/**
 * @author Kanomiya
 *
 */
public class VCInventoryWindow implements IViewComponent<InventoryWindow> {


	/**
	* @inheritDoc
	*/
	@Override
	public void paint(Graphics2D g, InventoryWindow invWindow, Assets assets, int frame)
	{
		Color frColor = (invWindow.hasForegroundColor()) ? invWindow.getForegroundColor() : GameColor.white;
		g.setColor(frColor);
		g.setFont(GameFont.textFont);

		g.translate(Text.defaultHeight, Text.defaultHeight);

		Iterator<ItemStack> itr = invWindow.inventory.stacks.iterator();

		int y = 0;
		while (itr.hasNext())
		{
			ItemStack itemStack = itr.next();

			y += g.getFontMetrics().getHeight();
			g.drawString(assets.translate(itemStack.item.getUnlocalizedName()) + " x" + itemStack.stackSize, 0, y);
		}

		g.translate(-Text.defaultHeight, -Text.defaultHeight);

	}


}
