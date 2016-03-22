package com.kanomiya.steward.controller.unit.state;

/**
 * @author Kanomiya
 *
 */
public class InputStateButton implements IInputState {

	protected EnumInputStateButton state;

	public InputStateButton()
	{
		state = EnumInputStateButton.RELEASED;
	}

	public boolean isPressedFirst()
	{
		return (state == EnumInputStateButton.PRESSED_FIRST);
	}

	public boolean isPressed()
	{
		return (state.isPressed());
	}

	public void press()
	{
		state = (state == EnumInputStateButton.RELEASED) ? EnumInputStateButton.PRESSED_FIRST : EnumInputStateButton.PRESSED;
	}

	public void release()
	{
		state = EnumInputStateButton.RELEASED;
	}



}
