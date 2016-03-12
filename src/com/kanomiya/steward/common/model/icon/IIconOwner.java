package com.kanomiya.steward.common.model.icon;

import com.kanomiya.steward.common.model.event.Direction;
import com.kanomiya.steward.common.model.event.WalkState;

/**
 * @author Kanomiya
 *
 */
public interface IIconOwner {

	public Icon getIcon();

	public Direction getDirection();
	public WalkState getWalkState();

}
