package com.kanomiya.steward.common.controller.unit.event;

import com.kanomiya.steward.common.controller.unit.Keyboard;
import com.kanomiya.steward.common.controller.unit.Mouse;
import com.kanomiya.steward.common.controller.unit.identifier.Key;
import com.kanomiya.steward.common.controller.unit.identifier.MouseButton;
import com.kanomiya.steward.common.event.ICancellable;
import com.kanomiya.steward.common.event.IConsumable;

/**
 * @author Kanomiya
 *
 */
public abstract class IInputUpdateEvent implements IInputEvent, IConsumable, ICancellable {

	protected Keyboard keyboard;
	protected Mouse mouse;

	public IInputUpdateEvent(Keyboard keyboard, Mouse mouse) {
		this.keyboard = keyboard;
		this.mouse = mouse;
	}



	public Keyboard getKeyboard()
	{
		return keyboard;
	}

	public Mouse getMouse()
	{
		return mouse;
	}




	public boolean isPressed(Key key)
	{
		return keyboard.isPressed(key);
	}

	public boolean anyIsPressed(Key... keys)
	{
		for (int i=0; i<keys.length; i++)
		{
			if (isPressed(keys[i])) return true;
		}

		return false;
	}

	public boolean isShifted()
	{
		return isPressed(Key.SHIFT);
	}

	public boolean isAlted()
	{
		return isPressed(Key.ALT);
	}

	public boolean isControled()
	{
		return isPressed(Key.CONTROL);
	}


	public boolean isPressed(MouseButton button)
	{
		return mouse.getButtonState(button).isPressed();
	}

	public int getX() {
		return mouse.getPointer().getX();
	}
	public int getY() {
		return mouse.getPointer().getY();
	}



	protected boolean consumed;
	protected boolean cancelled;

	public boolean isCancelledOrConsumed() { return (isCancelled() || isConsumed()); }

	/**
	* @inheritDoc
	*/
	@Override public void consume() { consumed = true; }

	/**
	* @inheritDoc
	*/
	@Override public boolean isConsumed() { return consumed; }

	/**
	* @inheritDoc
	*/
	@Override public void cancel() { cancelled = true; }

	/**
	* @inheritDoc
	*/
	@Override public boolean isCancelled() { return cancelled; }



}
