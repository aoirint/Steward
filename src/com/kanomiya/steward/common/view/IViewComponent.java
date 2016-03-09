package com.kanomiya.steward.common.view;

import java.awt.Graphics;

import com.kanomiya.steward.common.model.assets.Assets;

/**
 * @author Kanomiya
 *
 */
public interface IViewComponent<T> {

	public void paint(Graphics g, T model, Assets assets, int frame);

}
