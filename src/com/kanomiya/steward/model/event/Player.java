package com.kanomiya.steward.model.event;

import java.util.Map;

import com.google.common.collect.Maps;
import com.google.gson.annotations.Expose;
import com.kanomiya.steward.Game;
import com.kanomiya.steward.model.area.Area;
import com.kanomiya.steward.model.assets.Assets;
import com.kanomiya.steward.model.overlay.window.Window;
import com.kanomiya.steward.model.overlay.window.message.IngameLogger;
import com.kanomiya.steward.model.overlay.window.message.Message;

/**
 * @author Kanomiya
 *
 */
public class Player extends Event {

	@Expose public boolean debug;

	@Expose protected PlayerMode mode;
	public Map<PlayerMode, Boolean> allowedModeMap;

	public int selectedX, selectedY;
	public int focusedX, focusedY;

	protected Window window;
	public IngameLogger logger;


	public Player(String id, Assets assets)
	{
		super(id, assets);

		logger = new IngameLogger(assets);

		allowedModeMap = Maps.newHashMap();

		PlayerMode[] modes = PlayerMode.values();
		for (PlayerMode m: modes) allowedModeMap.put(m, true);
	}

	@Override
	public boolean travel(Area area, int x, int y)
	{
		boolean bool = super.travel(area, x, y);
		if (hasWindow() && window.isClosable()) removeWindow();

		return bool;
	}


	public boolean hasWindow() {
		return (window != null);
	}

	public Window getWindow() {
		return window;
	}

	/**
	 * @param window
	 */
	public void showWindow(Window window) {
		if (logger.isVisible()) logger.setVisible(false);

		window.setVisible(true);
		this.window = window;
	}

	public void showWindow(Message message)
	{
		showWindow(message.asBook());
	}


	public void removeWindow() {
		window.setVisible(false);
		window = null;
	}



	public void changeMode(PlayerMode mode)
	{
		PlayerMode.changeMode(Game.getInstance(assets), this, mode);
	}

	public void allowMode(PlayerMode mode)
	{
		allowedModeMap.put(mode, true);
	}

	public void disallowMode(PlayerMode mode)
	{
		allowedModeMap.put(mode, false);
	}

	/**
	 * @param mode
	 * @return
	 */
	public boolean modeIsAllowed(PlayerMode mode) {
		return allowedModeMap.get(mode);
	}

	/**
	 * @return mode
	 */
	public PlayerMode getMode() {
		return mode;
	}

	public boolean modeIs(PlayerMode mode)
	{
		return (getMode() == mode);
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
		return id.equals("player");
	}







}
