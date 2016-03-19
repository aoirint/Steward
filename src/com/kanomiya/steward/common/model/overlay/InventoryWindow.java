package com.kanomiya.steward.common.model.overlay;

import com.kanomiya.steward.common.model.item.Inventory;
import com.kanomiya.steward.common.model.overlay.window.Window;

/**
 * @author Kanomiya
 *
 */
public class InventoryWindow extends Window {

	public Inventory inventory;

	public InventoryWindow(Inventory inventory)
	{
		width = 756;
		height = 252;

		this.inventory = inventory;
		isClosable = true;

		autoLocation(LocationType.CENTER);
	}


}
