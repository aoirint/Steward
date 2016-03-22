package com.kanomiya.steward.controller.unit;

import java.util.Map;

import com.google.common.collect.Maps;
import com.kanomiya.steward.controller.unit.identifier.MouseButton;
import com.kanomiya.steward.controller.unit.state.InputStateButton;
import com.kanomiya.steward.controller.unit.state.InputStatePointer;

/**
 * @author Kanomiya
 *
 */
public class Mouse implements IInputUnit {

	public InputStatePointer pointer;
	public Map<MouseButton, InputStateButton> mouseButtonMap;

	public Mouse()
	{
		pointer = new InputStatePointer();
		mouseButtonMap = Maps.newHashMap();
	}

	public InputStateButton getButtonState(MouseButton button)
	{
		if (! mouseButtonMap.containsKey(button)) mouseButtonMap.put(button, new InputStateButton());
		return mouseButtonMap.get(button);
	}

	public InputStatePointer getPointer()
	{
		return pointer;
	}

}
