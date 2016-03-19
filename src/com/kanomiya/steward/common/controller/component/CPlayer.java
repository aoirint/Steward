package com.kanomiya.steward.common.controller.component;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.kanomiya.steward.common.controller.ControlConsts;
import com.kanomiya.steward.common.controller.ControlListener;
import com.kanomiya.steward.common.controller.IControllerComponent;
import com.kanomiya.steward.common.model.assets.Assets;
import com.kanomiya.steward.common.model.assets.AssetsUtils;
import com.kanomiya.steward.common.model.event.Direction;
import com.kanomiya.steward.common.model.event.Player;
import com.kanomiya.steward.common.model.event.PlayerMode;
import com.kanomiya.steward.common.model.overlay.GameColor;
import com.kanomiya.steward.common.model.overlay.InventoryWindow;
import com.kanomiya.steward.common.model.overlay.text.Choice;
import com.kanomiya.steward.common.model.overlay.text.ChoiceFunction;
import com.kanomiya.steward.common.model.overlay.text.ChoiceResult;
import com.kanomiya.steward.common.model.overlay.text.Text;
import com.kanomiya.steward.common.model.overlay.window.message.IngameLogger;
import com.kanomiya.steward.common.model.overlay.window.message.Message;
import com.kanomiya.steward.common.model.overlay.window.message.MessageBook;
import com.kanomiya.steward.common.view.ViewConsts;
import com.kanomiya.steward.common.view.ViewUtils;

/**
 * @author Kanomiya
 *
 */
public class CPlayer extends IControllerComponent<Player> {


	/**
	* @inheritDoc
	*/
	@Override
	public boolean input(KeyEvent keyEvent, ControlListener controlListener, Player player, Assets assets) {
		boolean turnConsumed = false;

		if (player.hasWindow() && player.getWindow() instanceof MessageBook)
		{
			turnConsumed = ControlConsts.cMessageBook.input(keyEvent, controlListener, (MessageBook) player.getWindow(), assets);
		}

		switch (keyEvent.getKeyCode())
		{
		case KeyEvent.VK_ENTER:

			if (player.mode.enableSelecter())
			{
				player.selectedX = player.focusedX;
				player.selectedY = player.focusedY;
			}

			break;


		case KeyEvent.VK_ESCAPE:
			if (player.hasWindow() && player.getWindow().isClosable()) player.removeWindow();
			else
			{

				player.showWindow(Message.create()
						.println(assets.translate("question.saveAndExit"))
						.println("")
						.println(Choice.create('a', assets.translate("vocabulary.yes"), new ChoiceFunction()
						{
							@Override
							public ChoiceResult apply(Character t) {
								player.removeWindow();

								AssetsUtils.saveAssets(assets, assets.getSaveDir());

								new Thread("Game Closer")
								{
									@Override
									public void run()
									{
										try {
											sleep(1000);
										} catch (InterruptedException e) {
											// TODO 自動生成された catch ブロック
											e.printStackTrace();
										}

										System.exit(0); // TODO 共通の終了処理
									}
								}.start();

								return null; // TODO ChoiceResult
							}
						})
						)

						.println(Choice.create('b', assets.translate("vocabulary.no"), new ChoiceFunction()
						{
							@Override
							public ChoiceResult apply(Character t) {
								player.removeWindow();
								return null; // TODO ChoiceResult
							}
						})
						)
				);

			}

			break;
		case KeyEvent.VK_NUMPAD8:
		case KeyEvent.VK_UP:
			if (player.mode.enableSelecter() && ViewUtils.topInWindowEdge(player.focusedY, player, 1)) player.focusedY -= 1;
			else if (player.mode.enableMove() && ! player.hasWindow()) turnConsumed = player.move(Direction.NORTH);
			break;

		case KeyEvent.VK_NUMPAD2:
		case KeyEvent.VK_DOWN:
			if (player.mode.enableSelecter() && ViewUtils.bottomInWindowEdge(player.focusedY, player, 1)) player.focusedY += 1;
			else if (player.mode.enableMove() && ! player.hasWindow()) turnConsumed = player.move(Direction.SOUTH);
			break;

		case KeyEvent.VK_NUMPAD4:
		case KeyEvent.VK_LEFT:
			if (player.mode.enableSelecter() && ViewUtils.leftInWindowEdge(player.focusedX, player, 1)) player.focusedX -= 1;
			else if (player.mode.enableMove() && ! player.hasWindow()) turnConsumed = player.move(Direction.WEST);
			break;

		case KeyEvent.VK_NUMPAD6:
		case KeyEvent.VK_RIGHT:
			if (player.mode.enableSelecter() && ViewUtils.rightInWindowEdge(player.focusedX, player, 1)) player.focusedX += 1;
			else if (player.mode.enableMove() && ! player.hasWindow()) turnConsumed = player.move(Direction.EAST);
			break;

		case KeyEvent.VK_NUMPAD7:
		case KeyEvent.VK_HOME:
			if (player.mode.enableSelecter())
			{
				if (ViewUtils.leftInWindowEdge(player.focusedX, player, 1))
						player.focusedX -= 1;
				if (ViewUtils.topInWindowEdge(player.focusedY, player, 1))
					player.focusedY -= 1;
			}
			else if (player.mode.enableMove() && ! player.hasWindow()) turnConsumed = player.move(Direction.NORTH, Direction.WEST);
			break;

		case KeyEvent.VK_NUMPAD9:
		case KeyEvent.VK_PAGE_UP:
			if (player.mode.enableSelecter())
			{
				if (ViewUtils.rightInWindowEdge(player.focusedX, player, 1))
					player.focusedX += 1;
				if (ViewUtils.topInWindowEdge(player.focusedY, player, 1))
					player.focusedY -= 1;
			}
			else if (player.mode.enableMove() && ! player.hasWindow()) turnConsumed = player.move(Direction.NORTH, Direction.EAST);
			break;

		case KeyEvent.VK_NUMPAD1:
		case KeyEvent.VK_END:
			if (player.mode.enableSelecter())
			{
				if (ViewUtils.leftInWindowEdge(player.focusedX, player, 1))
					player.focusedX -= 1;
				if (ViewUtils.bottomInWindowEdge(player.focusedY, player, 1))
					player.focusedY += 1;
			}
			else if (player.mode.enableMove() && ! player.hasWindow()) turnConsumed = player.move(Direction.SOUTH, Direction.WEST);
			break;

		case KeyEvent.VK_NUMPAD3:
		case KeyEvent.VK_PAGE_DOWN:
			if (player.mode.enableSelecter())
			{
				if (ViewUtils.rightInWindowEdge(player.focusedX, player, 1))
					player.focusedX += 1;
				if (ViewUtils.bottomInWindowEdge(player.focusedY, player, 1))
					player.focusedY += 1;
			}
			else if (player.mode.enableMove() && ! player.hasWindow()) turnConsumed = player.move(Direction.SOUTH, Direction.EAST);
			break;

		case KeyEvent.VK_NUMPAD5:
		case KeyEvent.VK_CLEAR:

			if (player.mode.enableSelecter())
			{
				player.focusedX = ViewUtils.xCenterInWindow(player);
				player.focusedY = ViewUtils.yCenterInWindow(player);

			} else if (player.mode.enableTurn())
			{
				player.walkState = player.walkState.next();
				turnConsumed = true;
			}
			break;

		case KeyEvent.VK_S: // save
			if (keyEvent.isControlDown())
			{
				if (player.mode.enableSave())
				{
					AssetsUtils.saveAssets(assets, (player.mode == PlayerMode.WIZARD) ? assets.getAssetsDir() : assets.getSaveDir());
				}
			}

			break;

		case KeyEvent.VK_X: // inventory

			if (! player.hasWindow()) player.showWindow(new InventoryWindow(player.getInventory()));

			break;
		case KeyEvent.VK_F1: // help
			break;

		case KeyEvent.VK_F3: // debug
			player.debug = ! player.debug;
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

			player.logger.println(Text.create(assets.translate("se.photo")).color(GameColor.green));

			break;

		case KeyEvent.VK_COLON: // select
			if (! keyEvent.isShiftDown()) break;
		case KeyEvent.VK_MULTIPLY:
			player.changeMode((player.mode == PlayerMode.SELECT) ? PlayerMode.NORMAL : PlayerMode.SELECT);
			break;

		case KeyEvent.VK_SLASH: // log
		case KeyEvent.VK_DIVIDE:

			if (! player.hasWindow()) player.logger.setVisible(! player.logger.isVisible());
			break;

		case KeyEvent.VK_F12: // debug
			player.changeMode((player.mode == PlayerMode.WIZARD) ? PlayerMode.NORMAL : PlayerMode.WIZARD);

			break;
		}


		return turnConsumed;
	}

	@Override
	public boolean click(MouseEvent mouseEvent, int x, int y, ControlListener controlListener, Player player, Assets assets)
	{
		boolean consumed = false;

		IngameLogger logger = player.logger;

		if (logger.isVisible() && logger.contains(x, y))
		{
			ControlConsts.cMessageBook.click(mouseEvent, x -logger.x, y -logger.y, controlListener, logger, assets);
		}


		if (! consumed && player.hasWindow() && player.getWindow() instanceof MessageBook)
		{
			MessageBook messageBook = (MessageBook) player.getWindow();

			if (messageBook.isVisible() && messageBook.contains(x, y))
			{
				ControlConsts.cMessageBook.click(mouseEvent, x -messageBook.x, y -messageBook.y, controlListener, messageBook, assets);
			}

		}



		return consumed;
	}


}
