package com.kanomiya.steward.common.model.event;

import java.util.Map;

import com.google.common.collect.Maps;
import com.google.gson.annotations.Expose;
import com.kanomiya.steward.common.model.area.Area;
import com.kanomiya.steward.common.model.assets.Assets;
import com.kanomiya.steward.common.model.overlay.LocationType;
import com.kanomiya.steward.common.model.overlay.message.IngameLogger;
import com.kanomiya.steward.common.model.overlay.message.Message;
import com.kanomiya.steward.common.model.overlay.message.MessageBook;
import com.kanomiya.steward.common.view.ViewConsts;

/**
 * @author Kanomiya
 *
 */
public class Player extends Event {

	@Expose public boolean debug;

	@Expose public PlayerMode mode;
	public Map<PlayerMode, Boolean> allowedModeMap;

	public int selectedX, selectedY;
	public int focusedX, focusedY;

	protected MessageBook messageBook;
	public IngameLogger logger;


	public Player(Assets assets)
	{
		super(assets);

		logger = new IngameLogger(assets);

		allowedModeMap = Maps.newHashMap();

		PlayerMode[] modes = PlayerMode.values();
		for (PlayerMode m: modes) allowedModeMap.put(m, true);
	}

	@Override
	public boolean travel(Area area, int x, int y)
	{
		boolean bool = super.travel(area, x, y);
		if (hasMessage() && messageBook.isClosable()) removeMessage();

		return bool;
	}


	public void showMessage(MessageBook messageBook)
	{
		if (logger.isVisible()) logger.setVisible(false);

		// messageBook.autoSize(assets);
		messageBook.autoLocation(LocationType.CENTER);
		messageBook.setVisible(true);

		this.messageBook = messageBook;

	}


	public void showMessage(Message message)
	{
		showMessage(message.asBook());
	}

	public boolean hasMessage()
	{
		if (messageBook == null) return false;
		if (! messageBook.isVisible()) messageBook = null;

		return (messageBook != null);
	}

	public MessageBook getMessage()
	{
		return messageBook;
	}

	public void removeMessage()
	{
		if (! hasMessage()) return ;

		messageBook.setVisible(false);
		messageBook = null;
	}


	public void changeMode(PlayerMode mode)
	{

		if (this.mode == PlayerMode.WIZARD && ViewConsts.frameTip.isVisible())
		{
			ViewConsts.frameTip.setVisible(false);
		}

		if (mode == PlayerMode.WIZARD)
		{
			ViewConsts.frameTip.setLocation(ViewConsts.gameFrame.getX() +ViewConsts.gameFrame.getWidth(), ViewConsts.gameFrame.getY());
			ViewConsts.frameTip.setAutoRequestFocus(false); // 自動アクティブ無効化
			ViewConsts.frameTip.setVisible(true);
		}


		focusedX = selectedX = x;
		focusedY = selectedY = y;

		this.mode = mode;

		/*
		logger.println(
				Text.create(assets.translate("playerMode.change")
						+ " " + assets.translate(mode.getUnlocalizedName()))
						.color(GameColor.orange));
		*/

	}

	public void allowMode(PlayerMode mode)
	{
		allowedModeMap.put(mode, true);
	}

	public void disallowMode(PlayerMode mode)
	{
		allowedModeMap.put(mode, false);
	}


	int turnCount = 0;
	/**
	* @inheritDoc
	*/
	@Override public void turn() {
		super.turn();

		// Area area = assets.getArea(areaId);

		// if (area.tileExists(x, y)) System.out.println("tip(" + x + "," + y + "): " + area.getTile(x, y).toString());

		// System.out.println("Turn " + ++turnCount);  // DEBUG
	}



	public static boolean isPlayerId(String id)
	{
		System.out.println("hey, im " + id);  // DEBUG
		return id.equals("player");
	}

}
