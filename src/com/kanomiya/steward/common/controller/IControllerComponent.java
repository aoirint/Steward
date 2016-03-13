package com.kanomiya.steward.common.controller;

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
	public boolean input(int keyCode, ControlListener controlListener, T model, Assets assets) { return false; }

	public boolean click(int button, int x, int y, ControlListener controlListener, T model, Assets assets) { return false; }

	public boolean mouseMove(int x, int y, ControlListener controlListener, T model, Assets assets) { return false; }

}
