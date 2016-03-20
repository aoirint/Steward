package com.kanomiya.steward.common.controller.action;

import com.kanomiya.steward.common.Game;
import com.kanomiya.steward.common.controller.unit.event.IInputEvent;

/**
 *
 * @author Kanomiya
 *
 * @param <E> {@link IInputEvent}
 */
public interface IControlAction<E extends IInputEvent> {

	public void action(E event, Game game);

}
