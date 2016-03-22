package com.kanomiya.steward.controller.unit.state;

/**
 * @author Kanomiya
 *
 */
public enum EnumInputStateButton {

	PRESSED_FIRST(true),
	PRESSED(true),
	RELEASED(false),

	;

	private boolean pressed;

	private EnumInputStateButton(boolean pressed)
	{
		this.pressed = pressed;
	}

	public boolean isPressed()
	{
		return pressed;
	}

}
