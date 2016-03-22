package com.kanomiya.steward.controller.action;

import com.kanomiya.steward.Game;
import com.kanomiya.steward.controller.unit.event.IInputEvent;

/**
 *
 * @author Kanomiya
 *
 * @param <E> {@link IInputEvent}
 */
public interface IControlAction<E extends IInputEvent> {

	public void action(E event, Game game);

}
