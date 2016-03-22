package com.kanomiya.steward.model.area;

import java.util.List;

import com.google.common.collect.Lists;
import com.kanomiya.steward.model.event.Event;

/**
 * @author Kanomiya
 *
 */
public class Chunk {
	public static int chunkSize = 8;

	protected Area area;
	protected List<Event> eventList;

	public Chunk(Area area)
	{
		this.area = area;
		eventList = Lists.newArrayList();
	}

	public List<Event> eventList()
	{
		return eventList;
	}

	public boolean hasEvent()
	{
		return ! eventList.isEmpty();
	}

}
