package com.kanomiya.steward.common.model.area;

import java.util.List;

import com.google.common.collect.Lists;
import com.kanomiya.steward.common.model.event.Event;




/**
 * @author Kanomiya
 *
 */
public class Area {
	protected String id, name;

	protected int width, height;
	protected int chunkWidth, chunkHeight;

	protected Tip[][] tips;
	protected List<Event> eventList;
	protected Chunk[][] chunks;


	public Area(String id, String name, int width, int height, Tip[][] tips)
	{
		this.id = id;
		this.name = name;
		this.width = width;
		this.height = height;
		this.tips = tips;

		eventList = Lists.newArrayList();

		chunkWidth = width /Chunk.chunkSize +1;
		chunkHeight = height /Chunk.chunkSize +1;
		chunks = new Chunk[chunkWidth][chunkHeight];

		for (int x=0; x<chunkWidth; x++)
		{
			for (int y=0; y<chunkHeight; y++)
			{
				chunks[x][y] = new Chunk(this);
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

	public Chunk getChunk(int x, int y)
	{
		int chunkX = x /Chunk.chunkSize;
		int chunkY = y /Chunk.chunkSize;
		if (width -Chunk.chunkSize < x) chunkX += 1;
		if (height -Chunk.chunkSize < y) chunkY += 1;

		return chunks[chunkX][chunkY];
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

	/**
	 * @param tip
	 * @param x
	 * @param y
	 */
	public void setTip(Tip tip, int x, int y) {
		tips[x][y] = tip;
	}


	public boolean canEnter(Event event, int x, int y)
	{
		if (! tipExists(x, y)) return false;
		if (getTip(x, y).getAccessType() == AccessType.deny) return false;

		List<Event> ceventList = getChunk(x, y).eventList;
		for (Event cevent: ceventList)
		{
			if (x == cevent.x && y == cevent.y && cevent.getAccessType() == AccessType.deny) return false;
		}

		return true;
	}


	/**
	 *
	 * @param event
	 */
	public void setEvent(Event event)
	{
		if (event.area != null) event.area.eventList.remove(event);
		event.area = this;
		if (! eventList.contains(event)) eventList.add(event);


		if (event.chunk != null) event.chunk.eventList.remove(event);
		event.chunk = getChunk(event.x, event.y);
		if (! event.chunk.eventList.contains(event)) event.chunk.eventList.add(event);

	}

	/**
	 * @return
	 */
	public List<Event> eventList() {
		return eventList;
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