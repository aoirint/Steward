package com.kanomiya.steward.controller.unit;

import java.util.Map;

import com.google.common.collect.Maps;
import com.kanomiya.steward.controller.unit.identifier.Key;
import com.kanomiya.steward.controller.unit.state.InputStateButton;

/**
 * @author Kanomiya
 *
 */
public class Keyboard implements IInputUnit {

	protected Map<Key, InputStateButton> keyMap;

	public Keyboard()
	{
		keyMap = Maps.newHashMap();
	}

	public InputStateButton getKeyState(Key key)
	{
		if (! keyMap.containsKey(key)) keyMap.put(key, new InputStateButton());
		return keyMap.get(key);
	}

	public boolean isPressed(Key key)
	{
		return getKeyState(key).isPressed();
	}


	public boolean isShifted()
	{
		return isPressed(Key.SHIFT);
	}

	public boolean isAlted()
	{
		return isPressed(Key.ALT);
	}

	public boolean isControlled()
	{
		return isPressed(Key.CONTROL);
	}


}
