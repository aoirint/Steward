package com.kanomiya.steward.common.controller.unit.event;

import com.kanomiya.steward.common.controller.unit.Keyboard;
import com.kanomiya.steward.common.controller.unit.Mouse;
import com.kanomiya.steward.common.controller.unit.identifier.Key;

/**
 * @author Kanomiya
 *
 */
public class KeyboardUpdateEvent extends IInputUpdateEvent {

	protected Key updateKey;

	public KeyboardUpdateEvent(Keyboard keyboard, Mouse mouse, Key updateKey) {
		super(keyboard, mouse);
		this.updateKey = updateKey;
	}


	public Key getUpdateKey()
	{
		return updateKey;
	}



}
