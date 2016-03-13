package com.kanomiya.steward.common.model.event;

import java.util.Map;

import com.google.gson.annotations.Expose;
import com.kanomiya.steward.common.model.area.AccessType;
import com.kanomiya.steward.common.model.area.Area;
import com.kanomiya.steward.common.model.assets.Assets;
import com.kanomiya.steward.common.model.overlay.LocationType;
import com.kanomiya.steward.common.model.overlay.message.IngameLogger;
import com.kanomiya.steward.common.model.overlay.message.MessageBook;
import com.kanomiya.steward.common.model.script.Script;
import com.kanomiya.steward.common.model.script.ScriptEventType;
import com.kanomiya.steward.common.model.texture.Texture;

/**
 * @author Kanomiya
 *
 */
public class Player extends Event {

	@Expose public boolean debugVisible;

	@Expose public PlayerMode mode;

	public int selectedX, selectedY;
	public int focusedX, focusedY;

	protected MessageBook messageBook;
	public IngameLogger logger;

	/*
	public Player()
	{
		mode = PlayerMode.NORMAL;
		debugMode = false;

		ingameLogger = new IngameLogger(0, 0);
	}
	*/

	public Player(String id, Area area, int x, int y, Texture texture,
			Direction direction, WalkState walkState,
			AccessType access, Map<ScriptEventType, Script> scripts,
			PlayerMode mode, boolean debugMode,
			Assets assets)
	{
		super(id, area, x, y, texture, direction, walkState, access, scripts, assets);

		this.mode = mode;
		debugVisible = debugMode;

		logger = new IngameLogger(assets);
	}


	public void showMessage(MessageBook messageBook)
	{
		if (logger != null && logger.isVisible()) logger.setVisible(false);

		messageBook.autoSize(assets);
		messageBook.autoLocation(LocationType.CENTER);
		messageBook.setVisible(true);

		this.messageBook = messageBook;
	}

	public boolean hasMessage()
	{
		return (messageBook != null);
	}

	public MessageBook getMessage()
	{
		return messageBook;
	}


	int turnCount = 0;
	/**
	* @inheritDoc
	*/
	@Override public void turn() {
		super.turn();

		// Area area = assets.getArea(areaId);

		// if (area.tileExists(x, y)) System.out.println("tip(" + x + "," + y + "): " + area.getTile(x, y).toString());

		// System.out.println("Turn " + ++turnCount); // TODO: デバッグ用コード
	}



	public static boolean isPlayerId(String id)
	{
		System.out.println("hey, im " + id);
		return id.equals("player");
	}

}
