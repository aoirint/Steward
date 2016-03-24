package com.kanomiya.steward.model.event;

import java.util.Iterator;
import java.util.Map;

import com.kanomiya.steward.model.area.AccessType;
import com.kanomiya.steward.model.area.Area;
import com.kanomiya.steward.model.area.Chunk;
import com.kanomiya.steward.model.area.Tip;
import com.kanomiya.steward.model.assets.Assets;
import com.kanomiya.steward.model.assets.resource.IResource;
import com.kanomiya.steward.model.item.Inventory;
import com.kanomiya.steward.model.script.IScriptLauncher;
import com.kanomiya.steward.model.script.IScriptOwner;
import com.kanomiya.steward.model.script.Script;
import com.kanomiya.steward.model.script.ScriptEventType;
import com.kanomiya.steward.model.texture.ITextureOwner;
import com.kanomiya.steward.model.texture.Texture;

/**
 * @author Kanomiya
 *
 */
public class Event implements IResource, ITextureOwner, IScriptOwner, IScriptLauncher {

	public String id;
	public Area area;
	public Chunk chunk;

	public int x, y;
	public boolean visible;
	public EventStatus status;

	public Direction direction;
	public WalkState walkState;

	public AccessType access;
	public Map<ScriptEventType, Script> scripts;
	public boolean isDead;

	public Texture icon;
	public Inventory inventory;

	public Assets assets;


	public Event(Assets assets)
	{
		this.assets = assets;
	}



	public void turn() {

	}



	public boolean move(Direction... direction)
	{
		int dx = 0;
		int dy = 0;

		for (Direction d: direction)
		{
			switch (d)
			{
			case SOUTH: dy += 1; break;
			case NORTH: dy -= 1; break;
			case EAST: dx += 1; break;
			case WEST: dx -= 1; break;
			}
		}

		return move(dx, dy);
	}

	public boolean move(int offsetX, int offsetY)
	{
		int fx = x +offsetX;
		int fy = y +offsetY;

		if (icon.type.isDirectable()) direction = Direction.getDirection(x, y, fx, fy, direction);
		if (icon.type.isWalkable()) walkState = walkState.next();

		if (! area.tipExists(fx, fy)) return false;
		if (area.getTip(fx, fy).getAccessType() == AccessType.DENY) return false;

		Area area = this.area;
		int x = this.x;
		int y = this.y;

		Iterator<Event> eventItr = area.getEvents(fx, fy).iterator();
		boolean canEnter = true;

		while (eventItr.hasNext())
		{
			Event event = eventItr.next();

			if (event.hasScript(this, ScriptEventType.ONENTERED))
			{
				assets.exec(event, this, ScriptEventType.ONENTERED);
			}

			canEnter = canEnter && (event.access != AccessType.DENY);
		}

		if (canEnter && area == this.area && this.x == x && this.y == y)
		{
			this.x = fx;
			this.y = fy;
		}

		this.area.setEvent(this, false);

		return true;
	}

	public boolean warp(int x, int y)
	{
		return travel(area, x, y);
	}

	public boolean canTravel(Area area, int x, int y)
	{
		if (! area.tipExists(x, y)) return false;
		if (! area.canEnter(this, x, y)) return false;

		return true;
	}


	public boolean travel(Area area, int x, int y)
	{
		if (! canTravel(area, x, y)) return false;

		Iterator<Event> eventItr = area.getEvents(x, y).iterator();

		while (eventItr.hasNext())
		{
			Event event = eventItr.next();

			assets.exec(event, this, ScriptEventType.ONENTERED);
		}

		this.x = x;
		this.y = y;

		area.setEvent(this, true);

		return true;
	}

	public boolean travel(String areaId, int x, int y)
	{
		return travel(assets.getArea(areaId), x, y);
	}

	public void goForward()
	{
		move(direction);
	}

	public void goBack()
	{
		move(direction.opposite());
	}



	/**
	 * @param assets
	 *
	 * @return 足元のタイル
	 */
	public Tip getFootTip() {
		return area.getTip(x, y);
	}



	@Override
	public String getId()
	{
		return id;
	}

	public Area getArea()
	{
		return area;
	}

	/**
	 * @return
	 */
	public Texture getIcon()
	{
		return icon;
	}

	/**
	* @inheritDoc
	*/
	@Override
	public Direction getDirection() {
		return direction;
	}

	/**
	* @inheritDoc
	*/
	@Override
	public WalkState getWalkState() {
		return walkState;
	}

	/**
	 * @return accessType
	 */
	public AccessType getAccessType() {
		return access;
	}


	public boolean hasInventory()
	{
		return (inventory != null);
	}

	public Inventory getInventory()
	{
		if (inventory == null) inventory = new Inventory();
		return inventory;
	}



	/**
	 * @return visible
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * @param visible セットする visible
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}


	public void setDead()
	{
		area.removeEvent(this);
		isDead = true;
	}

	public boolean isDead()
	{
		return isDead;
	}



	@Override
	public boolean hasScript(IScriptLauncher launcher, ScriptEventType eventType)
	{
		if (scripts == null) return false;
		return scripts.containsKey(eventType);
	}

	@Override
	public Script getScript(IScriptLauncher launcher, ScriptEventType eventType)
	{
		if (scripts == null) return null;
		return scripts.get(eventType);
	}



	@Override
	public String toString()
	{
		return id;
	}






}
