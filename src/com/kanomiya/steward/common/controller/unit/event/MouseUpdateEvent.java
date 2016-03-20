package com.kanomiya.steward.common.controller.unit.event;

import com.kanomiya.steward.common.controller.unit.Keyboard;
import com.kanomiya.steward.common.controller.unit.Mouse;
import com.kanomiya.steward.common.controller.unit.identifier.MouseButton;

/**
 * @author Kanomiya
 *
 */
public class MouseUpdateEvent extends IInputUpdateEvent {

	protected MouseButton updateButton;

	public MouseUpdateEvent(Keyboard keyboard, Mouse mouse, MouseButton updateButton) {
		super(keyboard, mouse);

		this.updateButton = updateButton;
	}

	public MouseButton getUpdateButton()
	{
		return updateButton;
	}



}
