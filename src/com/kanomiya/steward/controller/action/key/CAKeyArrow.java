package com.kanomiya.steward.controller.action.key;

import com.kanomiya.steward.Game;
import com.kanomiya.steward.config.GameKeys;
import com.kanomiya.steward.controller.action.IControlAction;
import com.kanomiya.steward.controller.unit.event.KeyboardUpdateEvent;
import com.kanomiya.steward.model.event.Direction;
import com.kanomiya.steward.model.event.Player;
import com.kanomiya.steward.model.overlay.window.message.Message;
import com.kanomiya.steward.model.overlay.window.message.MessageBook;
import com.kanomiya.steward.view.ViewUtils;

/**
 * @author Kanomiya
 *
 */
public class CAKeyArrow implements IControlAction<KeyboardUpdateEvent> {

	/**
	* @inheritDoc
	*/
	@Override
	public void action(KeyboardUpdateEvent event, Game game)
	{
		if (event.isCancelledOrConsumed()) return ;

		Player player = game.assets.getPlayer();

		if (player.hasWindow())
		{
			if (player.getWindow() instanceof MessageBook)
			{
				MessageBook book = (MessageBook) player.getWindow();

				if (event.isPressed(GameKeys.LEFT) && ! book.isFirstPage()) book.prevPage();
				if (event.isPressed(GameKeys.RIGHT) && ! book.isLastPage()) book.nextPage();

				Message current = book.currentPage();

				if (current.hasSelectable())
				{
					if (event.isPressed(GameKeys.UP) && ! current.isFirstSelectable()) current.prevSelectable();
					if (event.isPressed(GameKeys.DOWN) && ! current.isLastSelectable()) current.nextSelectable();
				}
			}
		} else
		{
			if (player.getMode().enableSelecter())
			{
				if (ViewUtils.topInWindowEdge(player.focusedY, player, 1))
				{
					if (event.anyIsPressed(GameKeys.UP, GameKeys.UP_LEFT, GameKeys.UP_RIGHT))
						player.focusedY -= 1;
				}

				if (ViewUtils.bottomInWindowEdge(player.focusedY, player, 1))
				{
					if (event.anyIsPressed(GameKeys.DOWN, GameKeys.DOWN_LEFT, GameKeys.DOWN_RIGHT))
						player.focusedY += 1;
				}

				if (ViewUtils.leftInWindowEdge(player.focusedX, player, 1))
				{
					if (event.anyIsPressed(GameKeys.LEFT, GameKeys.UP_LEFT, GameKeys.DOWN_LEFT))
						player.focusedX -= 1;
				}

				if (ViewUtils.rightInWindowEdge(player.focusedX, player, 1))
				{
					if (event.anyIsPressed(GameKeys.RIGHT, GameKeys.UP_RIGHT, GameKeys.DOWN_RIGHT))
						player.focusedX += 1;
				}
			}


			if (player.getMode().enableMove())
			{
				if (event.isPressed(GameKeys.UP)) { player.move(Direction.NORTH); game.currentTurn().consume(); }
				if (event.isPressed(GameKeys.DOWN)) { player.move(Direction.SOUTH); game.currentTurn().consume(); }
				if (event.isPressed(GameKeys.LEFT)) { player.move(Direction.WEST); game.currentTurn().consume(); }
				if (event.isPressed(GameKeys.RIGHT)) { player.move(Direction.EAST); game.currentTurn().consume(); }
				if (event.isPressed(GameKeys.UP_LEFT)) { player.move(Direction.NORTH, Direction.WEST); game.currentTurn().consume(); }
				if (event.isPressed(GameKeys.UP_RIGHT)) { player.move(Direction.NORTH, Direction.EAST); game.currentTurn().consume(); }
				if (event.isPressed(GameKeys.DOWN_LEFT)) { player.move(Direction.SOUTH, Direction.WEST); game.currentTurn().consume(); }
				if (event.isPressed(GameKeys.DOWN_RIGHT)) { player.move(Direction.SOUTH, Direction.EAST); game.currentTurn().consume(); }
			}

		}

		if (event.isPressed(GameKeys.STAMPING))
		{
			if (player.getMode().enableSelecter())
			{
				player.focusedX = ViewUtils.xCenterInWindow(player);
				player.focusedY = ViewUtils.yCenterInWindow(player);

			}

			if (player.getMode().enableTurn())
			{
				player.walkState = player.walkState.next();
				game.currentTurn().consume();
			}

		}

	}



}
