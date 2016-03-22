package com.kanomiya.steward.controller.action.key;

import com.kanomiya.steward.Game;
import com.kanomiya.steward.controller.action.IControlAction;
import com.kanomiya.steward.controller.unit.event.KeyboardUpdateEvent;
import com.kanomiya.steward.controller.unit.identifier.Key;
import com.kanomiya.steward.model.assets.Assets;
import com.kanomiya.steward.model.assets.AssetsUtils;
import com.kanomiya.steward.model.event.Player;
import com.kanomiya.steward.model.event.PlayerMode;

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
		Player player = assets.getPlayer();


		if (event.isPressed(Key.s) && event.isControled())
		{
			if (player.getMode().enableSave())
			{
				if (player.getMode() == PlayerMode.WIZARD) AssetsUtils.saveOriginalAssets(assets);
				else AssetsUtils.saveAssets(assets, AssetsUtils.savesDir);
			}
		}


	}



}
