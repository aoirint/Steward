package com.kanomiya.steward;

import com.kanomiya.steward.model.assets.Assets;
import com.kanomiya.steward.model.assets.AssetsUtils;
import com.kanomiya.steward.view.ViewConsts;



public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Assets assets = AssetsUtils.newAssets();

		Game game = Game.getInstance(assets);



		Thread repainter = new Thread("Repainter") // VELIF 通知式にする？
		{
			@Override public void run()
			{
				while (game.getFrame().isVisible())
				{
					game.getFrame().repaint();

					try {
						sleep(ViewConsts.frameLifeMills);
					} catch (InterruptedException e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
					}
				}
			}
		};

		repainter.start();





		/*
		game.thePlayer.logger.println(Text.create(game.assets.translate("vocabulary.welcome")).color(GameColor.orange));

		game.thePlayer.showMessage(
				MessageBook.create(
						).append(
								Message.create().println(Text.create("このページはEscクローズが有効です"))
						).append(
							Message.create().println(Text.create("Stewardを起動しました").color(GameColor.orange)
									).println(Text.create(" ")
											).println(Text.create("このページのEscクローズはロックされています")
													).lockClose()
						).append(
								Message.create().println(Text.create("このページは左右スクロール及びEscクローズが有効です"))
						).append(
								Message.create().println(Text.create("@は仮テクスチャのイベントテストです").color(GameColor.orange))
						)
				);
		 */

	}

}
