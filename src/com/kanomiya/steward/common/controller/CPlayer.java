package com.kanomiya.steward.common.controller;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.kanomiya.steward.common.model.assets.Assets;
import com.kanomiya.steward.common.model.event.Player;
import com.kanomiya.steward.common.model.event.PlayerMode;
import com.kanomiya.steward.common.model.overlay.PrettyText;
import com.kanomiya.steward.common.view.ViewConsts;

/**
 * @author Kanomiya
 *
 */
public class CPlayer extends IController<Player> {

	/**
	* @inheritDoc
	*/
	@Override
	public boolean input(int keyCode, ControlListener controlListener, Player player, Assets assets) {
		boolean consumed = false;

		switch (keyCode)
		{
		case KeyEvent.VK_NUMPAD8:
		case KeyEvent.VK_UP:
			consumed = player.move(assets, 0, -1);
			break;

		case KeyEvent.VK_NUMPAD2:
		case KeyEvent.VK_DOWN:
			consumed = player.move(assets, 0, 1);
			break;

		case KeyEvent.VK_NUMPAD4:
		case KeyEvent.VK_LEFT:
			consumed = player.move(assets, -1, 0);
			break;

		case KeyEvent.VK_NUMPAD6:
		case KeyEvent.VK_RIGHT:
			consumed = player.move(assets, 1, 0);
			break;

		case KeyEvent.VK_NUMPAD7:
		case KeyEvent.VK_HOME:
			consumed = player.move(assets, -1, -1);
			break;

		case KeyEvent.VK_NUMPAD9:
		case KeyEvent.VK_PAGE_UP:
			consumed = player.move(assets, 1, -1);
			break;

		case KeyEvent.VK_NUMPAD1:
		case KeyEvent.VK_END:
			consumed = player.move(assets, -1, 1);
			break;

		case KeyEvent.VK_NUMPAD3:
		case KeyEvent.VK_PAGE_DOWN:
			consumed = player.move(assets, 1, 1);
			break;

		case KeyEvent.VK_NUMPAD5:
		case KeyEvent.VK_CLEAR:
			consumed = true;
			break;

		case KeyEvent.VK_F1: // help
			break;

		case KeyEvent.VK_F3: // debug
			player.debugVisible = ! player.debugVisible;
			break;

		case KeyEvent.VK_F4: // screenshot
			File ssDir = new File("screenshots");
			if (! ssDir.exists()) ssDir.mkdir();

			try {
				ImageIO.write(ViewConsts.viewGame.screenShot(), "png", new File(ssDir, System.currentTimeMillis() + ".png"));
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}

			player.logger.println(PrettyText.create(assets.translate("se.photo")).color(PrettyText.colorGreen));

			break;

		case KeyEvent.VK_MULTIPLY: // select
			controlListener.changeMode((player.mode == PlayerMode.SELECT) ? PlayerMode.NORMAL : PlayerMode.SELECT);
			break;

		case KeyEvent.VK_DIVIDE: // log
			player.logger.setVisible(! player.logger.isVisible());
			break;

		case KeyEvent.VK_F12: // debug
			controlListener.changeMode((player.mode == PlayerMode.WIZARD) ? PlayerMode.NORMAL : PlayerMode.WIZARD);

			break;
		}


		return consumed;
	}



}
