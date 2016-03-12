package com.kanomiya.steward.common.controller;

import com.kanomiya.steward.common.model.assets.Assets;

/**
 * @author Kanomiya
 *
 */
public interface IController<T> {

	/**
	 *
	 * @param keyCode
	 * @param model
	 * @param assets
	 * @return
	 */
	public void input(int keyCode, TurnInfo turnInfo, T model, Assets assets);

}
