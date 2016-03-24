package com.kanomiya.steward.controller.action.key;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.kanomiya.steward.Game;
import com.kanomiya.steward.config.GameKeys;
import com.kanomiya.steward.controller.action.IControlAction;
import com.kanomiya.steward.controller.unit.event.KeyboardUpdateEvent;
import com.kanomiya.steward.editor.FrameEditor;
import com.kanomiya.steward.model.assets.Assets;
import com.kanomiya.steward.model.event.Player;
import com.kanomiya.steward.model.event.PlayerMode;
import com.kanomiya.steward.model.overlay.GameColor;
import com.kanomiya.steward.model.overlay.text.ConfirmHandler;
import com.kanomiya.steward.model.overlay.text.ConfirmResult;
import com.kanomiya.steward.model.overlay.text.Text;
import com.kanomiya.steward.model.overlay.text.TextField;
import com.kanomiya.steward.model.overlay.window.message.Message;
import com.kanomiya.steward.view.ViewConsts;

/**
 * @author Kanomiya
 *
 */
public class CAKeyFunction implements IControlAction<KeyboardUpdateEvent> {

	/**
	* @inheritDoc
	*/
	@Override
	public void action(KeyboardUpdateEvent event, Game game)
	{
		if (event.isCancelledOrConsumed()) return ;

		Assets assets = game.assets;
		Player player = assets.getPlayer();

		if (event.isPressed(GameKeys.F1)); // help

		if (event.isPressed(GameKeys.F2)); //

		// F3 -> CAKeyMode

		if (event.isPressed(GameKeys.F4)) // screenshot
		{
			File ssDir = new File("screenshots");
			if (! ssDir.exists()) ssDir.mkdir();

			try {
				ImageIO.write(ViewConsts.viewGame.screenShot(), "png", new File(ssDir, System.currentTimeMillis() + ".png"));
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}

			player.logger.println(Text.create(assets.translate("se.photo")).color(GameColor.green));
		}

		if (event.isPressed(GameKeys.F5)); //
		if (event.isPressed(GameKeys.F6)); //
		if (event.isPressed(GameKeys.F7)); //
		if (event.isPressed(GameKeys.F8)); //
		if (event.isPressed(GameKeys.F9)); //
		if (event.isPressed(GameKeys.F10)); //
		if (event.isPressed(GameKeys.F11)); //


		if (event.isPressed(GameKeys.F12)) // wizard
		{
			if (! player.hasWindow())
			{
				player.showWindow(Message.create("コンソール")
						.println("")
						.println(TextField.create().confirmHandler(new ConfirmHandler<String, ConfirmResult>()
						{
							@Override
							public ConfirmResult apply(String text)
							{

								if (text.equals("wizard"))
								{
									player.changeMode(PlayerMode.WIZARD);
									player.logger.println("Oracle: Wi-zaaaard!");
								} else if (text.equals("normal"))
								{
									player.changeMode(PlayerMode.NORMAL);
									player.logger.println("Oracle: Normal;");
								} else if (text.equals("editor"))
								{
									new FrameEditor(game).setVisible(true);
								} else
								{
									player.logger.println("Oracle: Huh? What do you mean?");
								}


								player.removeWindow();

								return null;
							}
						}
						)));


			}

		}



	}



}
