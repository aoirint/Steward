package com.kanomiya.steward.common.model.texture;

import com.kanomiya.steward.common.model.event.Direction;
import com.kanomiya.steward.common.model.event.WalkState;

/**
 * @author Kanomiya
 *
 */
public interface ITextureOwner {

	public Direction getDirection();
	public WalkState getWalkState();

}
