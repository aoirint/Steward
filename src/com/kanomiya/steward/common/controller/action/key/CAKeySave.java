package com.kanomiya.steward.common.controller.action.key;

import com.kanomiya.steward.common.Game;
import com.kanomiya.steward.common.controller.action.IControlAction;
import com.kanomiya.steward.common.controller.unit.event.KeyboardUpdateEvent;
import com.kanomiya.steward.common.controller.unit.identifier.Key;
import com.kanomiya.steward.common.model.assets.Assets;
import com.kanomiya.steward.common.model.assets.AssetsUtils;
import com.kanomiya.steward.common.model.event.Player;
import com.kanomiya.steward.common.model.event.PlayerMode;

/**
 * @author Kanomiya
 *
 */
public class CAKeySave implements IControlAction<KeyboardUpdateEvent> {

	/**
	* @inheritDoc
	*/
	@Override
	public void action(KeyboardUpdateEvent event, Game game)
	{
		if (event.isCancelledOrConsumed()) return ;

		Assets assets = game.assets;
		Player player = game.thePlayer;


		if (event.isPressed(Key.s) && event.isControled())
		{
			if (player.mode.enableSave())
			{
				AssetsUtils.saveAssets(assets, (player.mode == PlayerMode.WIZARD) ? assets.getAssetsDir() : assets.getSaveDir());
			}
		}


	}



}
