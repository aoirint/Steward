package com.kanomiya.steward.common.model.area;

import com.kanomiya.steward.common.model.event.Event;

/**
 * @author Kanomiya
 *
 */
public class Tile {

	protected int x, y;
	protected Tip tip;
	protected Event event;

	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
	}



	public boolean canEnter(Event visitor)
	{
		if (hasTip() && tip.getAccessType() == AccessType.deny) return false;
		if (hasEvent() && event.getAccessType() == AccessType.deny) return false;

		return true;
	}


	/**
	 * @return tip
	 */
	public Tip getTip() {
		return tip;
	}

	/**
	 * @param tip セットする tip
	 */
	public void setTip(Tip tip) {
		this.tip = tip;
	}

	/**
	 * @return x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param event セットする event
	 */
	public void setEvent(Event event) {
		this.event = event;
	}

	public Event getEvent()
	{
		return event;
	}






	/**
	 * @return
	 */
	public boolean hasTip()
	{
		return (tip != null);
	}

	/**
	 * @return
	 */
	public boolean hasEvent() {
		return (event != null);
	}


}
