package com.kanomiya.steward.common.controller.action.key;

import com.kanomiya.steward.common.Game;
import com.kanomiya.steward.common.controller.action.IControlAction;
import com.kanomiya.steward.common.controller.unit.VirtualKeypad;
import com.kanomiya.steward.common.controller.unit.event.KeyboardUpdateEvent;
import com.kanomiya.steward.common.model.event.Direction;
import com.kanomiya.steward.common.model.event.Player;
import com.kanomiya.steward.common.model.overlay.window.message.Message;
import com.kanomiya.steward.common.model.overlay.window.message.MessageBook;
import com.kanomiya.steward.common.view.ViewUtils;

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

		Player player = game.thePlayer;

		if (player.hasWindow())
		{
			if (player.getWindow() instanceof MessageBook)
			{
				MessageBook book = (MessageBook) player.getWindow();

				if (event.isPressed(VirtualKeypad.LEFT) && ! book.isFirstPage()) book.prevPage();
				if (event.isPressed(VirtualKeypad.RIGHT) && ! book.isLastPage()) book.nextPage();

				Message current = book.currentPage();

				if (current.hasChoice())
				{
					if (event.isPressed(VirtualKeypad.UP) && ! current.isFirstChoice()) current.prevChoice();
					if (event.isPressed(VirtualKeypad.DOWN) && ! current.isLastChoice()) current.nextChoice();
				}
			}
		} else
		{
			if (game.thePlayer.mode.enableSelecter())
			{
				if (ViewUtils.topInWindowEdge(game.thePlayer.focusedY, game.thePlayer, 1))
				{
					if (event.anyIsPressed(VirtualKeypad.UP, VirtualKeypad.UP_LEFT, VirtualKeypad.UP_RIGHT))
						player.focusedY -= 1;
				}

				if (ViewUtils.bottomInWindowEdge(player.focusedY, player, 1))
				{
					if (event.anyIsPressed(VirtualKeypad.DOWN, VirtualKeypad.DOWN_LEFT, VirtualKeypad.DOWN_RIGHT))
						player.focusedY += 1;
				}

				if (ViewUtils.leftInWindowEdge(player.focusedX, player, 1))
				{
					if (event.anyIsPressed(VirtualKeypad.LEFT, VirtualKeypad.UP_LEFT, VirtualKeypad.DOWN_LEFT))
						player.focusedX -= 1;
				}

				if (ViewUtils.rightInWindowEdge(player.focusedX, player, 1))
				{
					if (event.anyIsPressed(VirtualKeypad.RIGHT, VirtualKeypad.UP_RIGHT, VirtualKeypad.DOWN_RIGHT))
						player.focusedX += 1;
				}
			}


			if (game.thePlayer.mode.enableMove())
			{
				if (event.isPressed(VirtualKeypad.UP)) { player.move(Direction.NORTH); game.currentTurn().consume(); }
				if (event.isPressed(VirtualKeypad.DOWN)) { player.move(Direction.SOUTH); game.currentTurn().consume(); }
				if (event.isPressed(VirtualKeypad.LEFT)) { player.move(Direction.WEST); game.currentTurn().consume(); }
				if (event.isPressed(VirtualKeypad.RIGHT)) { player.move(Direction.EAST); game.currentTurn().consume(); }
				if (event.isPressed(VirtualKeypad.UP_LEFT)) { player.move(Direction.NORTH, Direction.WEST); game.currentTurn().consume(); }
				if (event.isPressed(VirtualKeypad.UP_RIGHT)) { player.move(Direction.NORTH, Direction.EAST); game.currentTurn().consume(); }
				if (event.isPressed(VirtualKeypad.DOWN_LEFT)) { player.move(Direction.SOUTH, Direction.WEST); game.currentTurn().consume(); }
				if (event.isPressed(VirtualKeypad.DOWN_RIGHT)) { player.move(Direction.SOUTH, Direction.EAST); game.currentTurn().consume(); }
			}

		}

		if (event.isPressed(VirtualKeypad.STAMPING))
		{
			if (player.mode.enableSelecter())
			{
				player.focusedX = ViewUtils.xCenterInWindow(player);
				player.focusedY = ViewUtils.yCenterInWindow(player);

			}

			if (player.mode.enableTurn())
			{
				player.walkState = player.walkState.next();
				game.currentTurn().consume();
			}

		}

	}



}
