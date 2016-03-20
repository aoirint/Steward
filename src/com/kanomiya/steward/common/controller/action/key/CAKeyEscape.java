package com.kanomiya.steward.common.controller.action.key;

import com.kanomiya.steward.common.Game;
import com.kanomiya.steward.common.controller.action.IControlAction;
import com.kanomiya.steward.common.controller.unit.VirtualKeypad;
import com.kanomiya.steward.common.controller.unit.event.KeyboardUpdateEvent;
import com.kanomiya.steward.common.model.assets.Assets;
import com.kanomiya.steward.common.model.assets.AssetsUtils;
import com.kanomiya.steward.common.model.event.Player;
import com.kanomiya.steward.common.model.overlay.text.Choice;
import com.kanomiya.steward.common.model.overlay.text.ConfirmHandler;
import com.kanomiya.steward.common.model.overlay.text.ConfirmResult;
import com.kanomiya.steward.common.model.overlay.window.message.Message;

/**
 * @author Kanomiya
 *
 */
public class CAKeyEscape implements IControlAction<KeyboardUpdateEvent> {

	/**
	 * @inheritDoc
	 */
	@Override
	public void action(KeyboardUpdateEvent event, Game game)
	{
		if (event.isCancelledOrConsumed()) return ;
		if (! event.isPressed(VirtualKeypad.ESCAPE)) return ;

		Player player = game.thePlayer;


		if (player.hasWindow())
		{
			if (player.getWindow().isClosable()) player.removeWindow();

		} else
		{
			Assets assets = game.assets;

			player.showWindow(Message.create()
					.println(assets.translate("question.saveAndExit"))
					.println("")
					.println(Choice.create('a', assets.translate("vocabulary.yes"))
							.confirmHandler(new ConfirmHandler<Character, ConfirmResult>()
									{
										@Override
										public ConfirmResult apply(Character t) {
											player.removeWindow();

											AssetsUtils.saveAssets(assets, AssetsUtils.savesDir);

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

											return null; // TODO ConfirmResult
										}
									})
							)

							.println(Choice.create('b', assets.translate("vocabulary.no"))
									.confirmHandler(new ConfirmHandler<Character, ConfirmResult>()
											{
										@Override
										public ConfirmResult apply(Character t) {
											player.removeWindow();
											return null; // TODO ConfirmResult
										}
											})
									)
					);

		}




	}



}
