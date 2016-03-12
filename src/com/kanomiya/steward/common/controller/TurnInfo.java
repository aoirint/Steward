package com.kanomiya.steward.common.controller;

/**
 * @author Kanomiya
 *
 */
public class TurnInfo {

	public boolean turnIsConsumed;
	public ControlListener controlListener;

	public TurnInfo(ControlListener controlListener)
	{
		this.controlListener = controlListener;
		turnIsConsumed = false;
	}

	public void consume(boolean bool)
	{
		turnIsConsumed = bool;
	}

}
