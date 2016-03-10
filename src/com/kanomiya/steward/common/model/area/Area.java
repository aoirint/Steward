package com.kanomiya.steward.common.model.area;

import com.kanomiya.steward.common.model.event.Event;




/**
 * @author Kanomiya
 *
 */
public class Area {
	protected String id, name;

	protected int width, height;

	protected Tip[][] tips;

	public Area(String id, String name, int width, int height, Tip[][] tips)
	{
		this.id = id;
		this.name = name;
		this.width = width;
		this.height = height;
		this.tips = tips;
	}

	/**
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id セットする id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name セットする name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width セットする width
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height セットする height
	 */
	public void setHeight(int height) {
		this.height = height;
	}


	public boolean inArea(int x, int y)
	{
		if (x < 0 || width <= x || y < 0 || height <= y) return false;
		return true;
	}

	public boolean tipExists(int x, int y)
	{
		if (! inArea(x, y)) return false;
		return (tips[x][y] != null);
	}

	public Tip getTip(int x, int y)
	{
		if (! tipExists(x, y)) return null;
		return tips[x][y];
	}


	public boolean canEnter(Event event, int x, int y)
	{
		if (! tipExists(x, y)) return false;

		Tip tip = getTip(x, y);
		if (tip.getAccessType() == AccessType.deny) return false;

		return true;
	}


	/**
	 * @param tip
	 * @param x
	 * @param y
	 */
	public void setTip(Tip tip, int x, int y) {
		tips[x][y] = tip;
	}


	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();

		builder.append(getClass().getSimpleName());
		builder.append('[');

		builder.append("id: ");
		builder.append(id);
		builder.append(", name: ");
		builder.append(name);

		builder.append(']');

		return new String(builder);
	}



}
