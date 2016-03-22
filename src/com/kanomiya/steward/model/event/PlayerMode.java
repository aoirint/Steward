package com.kanomiya.steward.model.event;

import com.kanomiya.steward.Game;
import com.kanomiya.steward.model.util.IEnumWithId;

/**
 * @author Kanomiya
 *
 */
public enum PlayerMode implements IEnumWithId {
	NORMAL("normal", true, true, false, true),

	TARGET("select", false, false, true, false),
	WIZARD("wizard", true, false, true, true),


	;

	private String id;
	private boolean enableMove, enableTurn, enableSelecter, enableSave;


	private PlayerMode(String id, boolean enableMove, boolean enableTurn, boolean enableSelecter, boolean enableSave) {
		this.id = id;
		this.enableMove = enableMove;
		this.enableTurn = enableTurn;
		this.enableSelecter = enableSelecter;
		this.enableSave = enableSave;
	}

	public boolean enableMove()
	{
		return enableMove;
	}

	public boolean enableTurn()
	{
		return enableTurn;
	}

	public boolean enableSelecter()
	{
		return enableSelecter;
	}

	public boolean enableSave()
	{
		return enableSave;
	}

	@Override
	public String getId()
	{
		return id;
	}

	public String getUnlocalizedName()
	{
		return "playerMode." + id;
	}


	@Override
	public String toString()
	{
		return name();
	}



	public static void changeMode(Game game, Player player, PlayerMode mode) // VELIF
	{
		if (! player.modeIsAllowed(mode)) return ;

		if (player.getMode() == PlayerMode.WIZARD && game.frameTip.isVisible())
		{
			game.frameTip.setVisible(false);
		}

		if (mode == PlayerMode.WIZARD)
		{
			game.frameTip.setLocation(game.getFrame().getX() +game.getFrame().getWidth(), game.getFrame().getY());
			game.frameTip.setAutoRequestFocus(false); // 自動アクティブ無効化
			game.frameTip.setVisible(true);
		}

		player.focusedX = player.selectedX = player.x;
		player.focusedY = player.selectedY = player.y;

		player.mode = mode;


		/*
		logger.println(
				Text.create(assets.translate("playerMode.change")
						+ " " + assets.translate(mode.getUnlocalizedName()))
						.color(GameColor.orange));
		*/
	}


	/**
	 * @param id
	 * @return
	 */
	public static PlayerMode getFromId(String id)
	{
		PlayerMode[] values = values();

		for (PlayerMode type: values)
		{
			if (id.equals(type.id)) return type;
		}

		throw new IllegalArgumentException("No such object in PlayerMode: name=" + id);
	}

}
