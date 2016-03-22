package com.kanomiya.steward.model.texture;

import com.kanomiya.steward.model.event.Direction;
import com.kanomiya.steward.model.event.WalkState;

/**
 * @author Kanomiya
 *
 */
public interface ITextureOwner {

	public Direction getDirection();
	public WalkState getWalkState();

}
