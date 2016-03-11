package com.kanomiya.steward.common.model.area;

import com.kanomiya.steward.common.model.event.Event;




/**
 * @author Kanomiya
 *
 */
public class Area {
	protected String id, name;

	protected int width, height;

	protected Tile[][] tiles;

	public Area(String id, String name, int width, int height, Tip[][] tips)
	{
		this.id = id;
		this.name = name;
		this.width = width;
		this.height = height;

		tiles = new Tile[width][height];

		for (int x=0; x<width; x++)
		{
			for (int y=0; y<height; y++)
			{
				tiles[x][y] = new Tile(x, y);
				tiles[x][y].setTip(tips[x][y]);
			}
		}
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

	public boolean tileExists(int x, int y)
	{
		if (! inArea(x, y)) return false;
		return (tiles[x][y] != null);
	}

	public Tile getTile(int x, int y)
	{
		if (! tileExists(x, y)) return null;
		return tiles[x][y];
	}


	public boolean canEnter(Event event, int x, int y)
	{
		if (! tileExists(x, y)) return false;
		return getTile(x, y).canEnter(event);
	}


	/**
	 *
	 * @param event
	 * @param x
	 * @param y
	 */
	public void setEvent(Event event)
	{
		tiles[event.x][event.y].setEvent(event);
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
