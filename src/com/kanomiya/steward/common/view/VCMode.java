package com.kanomiya.steward.common.view;

import java.awt.Color;
import java.awt.Graphics2D;

import com.kanomiya.steward.common.model.assets.Assets;
import com.kanomiya.steward.common.model.event.Player;


/**
 * @author Kanomiya
 *
 */
public class VCMode implements IViewComponent<Player> {

	/**
	* @inheritDoc
	*/
	@Override
	public void paint(Graphics2D g, Player player, Assets assets, int frame)
	{
		g.setColor(Color.WHITE);



		g.drawString("モード  " + player.mode.toString(), ViewConsts.viewWidth -100, 30);
	}

}
