package com.kanomiya.steward.common.model.event;

import java.util.Map;

import com.google.gson.annotations.Expose;
import com.kanomiya.steward.common.model.area.AccessType;
import com.kanomiya.steward.common.model.area.Area;
import com.kanomiya.steward.common.model.assets.Assets;
import com.kanomiya.steward.common.model.icon.Icon;
import com.kanomiya.steward.common.model.script.Script;
import com.kanomiya.steward.common.model.script.ScriptEventType;

/**
 * @author Kanomiya
 *
 */
public class Player extends Event {

	@Expose public boolean debugMode;

	@Expose public PlayerMode mode;

	public int selectedX, selectedY;
	public int focusedX, focusedY;

	public Player()
	{
		mode = PlayerMode.NORMAL;
		debugMode = false;
	}

	public Player(String id, Area area, int x, int y, Icon icon,
			Direction direction, WalkState walkState,
			AccessType access, Map<ScriptEventType, Script> scripts,
			PlayerMode mode, boolean debugMode)
	{
		super(id, area, x, y, icon, direction, walkState, access, scripts);

		this.mode = mode;
		this.debugMode = debugMode;

	}

	int turnCount = 0;
	/**
	* @inheritDoc
	*/
	@Override public void turn(Assets assets) {
		super.turn(assets);

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
