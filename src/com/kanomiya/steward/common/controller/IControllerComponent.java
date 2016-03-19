package com.kanomiya.steward.common.controller;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.kanomiya.steward.common.model.assets.Assets;

/**
 * @author Kanomiya
 *
 */
public abstract class IControllerComponent<T> {

	/**
	 *
	 * @param keyCode
	 * @param model
	 * @param assets
	 * @return
	 */
	public boolean input(KeyEvent keyEvent, ControlBus controlBus, T model, Assets assets) { return false; }

	public boolean click(MouseEvent mouseEvent, int x, int y, ControlBus controlBus, T model, Assets assets) { return false; }

	public boolean mouseMove(int x, int y, ControlBus controlBus, T model, Assets assets) { return false; }

}
