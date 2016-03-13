package com.kanomiya.steward.common.view.component;

import java.awt.Graphics2D;

import com.kanomiya.steward.common.model.assets.Assets;

/**
 * @author Kanomiya
 *
 */
public interface IViewComponent<T> {

	public void paint(Graphics2D g, T model, Assets assets, int frame);

}
