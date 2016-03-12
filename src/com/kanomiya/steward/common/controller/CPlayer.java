package com.kanomiya.steward.common.controller;

import java.awt.event.KeyEvent;

import com.kanomiya.steward.common.model.assets.Assets;
import com.kanomiya.steward.common.model.event.Player;
import com.kanomiya.steward.common.model.event.PlayerMode;

/**
 * @author Kanomiya
 *
 */
public class CPlayer implements IController<Player> {

	/**
	* @inheritDoc
	*/
	@Override
	public void input(int keyCode, TurnInfo turnInfo, Player player, Assets assets) {

		switch (keyCode)
		{
		case KeyEvent.VK_NUMPAD8:
		case KeyEvent.VK_UP:
			turnInfo.consume(player.move(assets, 0, -1));
			break;

		case KeyEvent.VK_NUMPAD2:
		case KeyEvent.VK_DOWN:
			turnInfo.consume(player.move(assets, 0, 1));
			break;

		case KeyEvent.VK_NUMPAD4:
		case KeyEvent.VK_LEFT:
			turnInfo.consume(player.move(assets, -1, 0));
			break;

		case KeyEvent.VK_NUMPAD6:
		case KeyEvent.VK_RIGHT:
			turnInfo.consume(player.move(assets, 1, 0));
			break;

		case KeyEvent.VK_NUMPAD7:
		case KeyEvent.VK_HOME:
			turnInfo.consume(player.move(assets, -1, -1));
			break;

		case KeyEvent.VK_NUMPAD9:
		case KeyEvent.VK_PAGE_UP:
			turnInfo.consume(player.move(assets, 1, -1));
			break;

		case KeyEvent.VK_NUMPAD1:
		case KeyEvent.VK_END:
			turnInfo.consume(player.move(assets, -1, 1));
			break;

		case KeyEvent.VK_NUMPAD3:
		case KeyEvent.VK_PAGE_DOWN:
			turnInfo.consume(player.move(assets, 1, 1));
			break;

		case KeyEvent.VK_NUMPAD5:
		case KeyEvent.VK_CLEAR:
			turnInfo.consume(true);
			break;

		case KeyEvent.VK_F1: // help
			break;

		case KeyEvent.VK_MULTIPLY: // select
			turnInfo.controlListener.changeMode((player.mode == PlayerMode.SELECT) ? PlayerMode.NORMAL : PlayerMode.SELECT);
			break;

		case KeyEvent.VK_DIVIDE: // log
			player.logger.setVisible(! player.logger.isVisible());
			break;

		case KeyEvent.VK_F12: // debug
			turnInfo.controlListener.changeMode((player.mode == PlayerMode.WIZARD) ? PlayerMode.NORMAL : PlayerMode.WIZARD);

			break;
		}


	}



}
