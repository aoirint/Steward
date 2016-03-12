package com.kanomiya.steward.common.model.event;

import java.util.List;
import java.util.Map;

import javax.script.ScriptException;

import com.google.gson.annotations.Expose;
import com.kanomiya.steward.common.model.ITurnObject;
import com.kanomiya.steward.common.model.area.AccessType;
import com.kanomiya.steward.common.model.area.Area;
import com.kanomiya.steward.common.model.area.Chunk;
import com.kanomiya.steward.common.model.area.Tip;
import com.kanomiya.steward.common.model.assets.Assets;
import com.kanomiya.steward.common.model.script.Script;
import com.kanomiya.steward.common.model.script.ScriptEventType;
import com.kanomiya.steward.common.model.texture.ITextureOwner;
import com.kanomiya.steward.common.model.texture.Texture;

/**
 * @author Kanomiya
 *
 */
public class Event implements ITurnObject, ITextureOwner {

	@Expose public String id;
	public Area area;
	public Chunk chunk;

	@Expose public int x, y;
	public EventStatus status;

	@Expose public Direction direction;
	@Expose public WalkState walkState;

	@Expose public AccessType access;
	@Expose public Map<ScriptEventType, Script> scripts;

	@Expose public Texture texture;

	public Event()
	{
		this(null, null, 0, 0, null);
	}

	public Event(String id, Area area, int x, int y, Texture texture)
	{
		this(id, area, x, y, texture, AccessType.allow);
	}

	public Event(String id, Area area, int x, int y, Texture texture, AccessType access)
	{
		this(id, area, x, y, texture, Direction.south, WalkState.upright, access, null);
	}

	public Event(String id, Area area, int x, int y, Texture texture,
			Direction direction, WalkState walkState,
			AccessType access, Map<ScriptEventType, Script> scripts)
	{
		this.id = id;
		this.area = area;

		this.x = x;
		this.y = y;

		this.texture = texture;
		this.direction = direction;
		this.walkState = walkState;

		this.access = access;
		this.scripts = scripts;

	}


	/**
	* @inheritDoc
	*/
	@Override
	public void turn(Assets assets) {

	}


	public boolean move(Assets assets, int offsetX, int offsetY)
	{
		int fx = x +offsetX;
		int fy = y +offsetY;

		if (texture.type.isDirectable()) direction = Direction.getDirection(x, y, fx, fy, direction);
		if (texture.type.isWalkable()) walkState = walkState.next();

		if (! area.tipExists(fx, fy)) return false;

		Chunk fchunk = area.getChunk(fx, fy);

		if (fchunk.hasEvent())
		{
			List<Event> feventList = fchunk.eventList();

			for (Event fevent: feventList)
			{
				if (fevent.x == fx && fevent.y == fy) fevent.launchScript(assets, ScriptEventType.onColided);
			}

		}

		if (! area.canEnter(this, fx, fy)) return false;

		x = fx;
		y = fy;

		area.setEvent(this);

		return true;
	}

	public void warp(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public void travel(String areaId, int x, int y)
	{
		this.x = x;
		this.y = y;
	}


	/**
	 * @param assets
	 *
	 * @return 足元のタイル
	 */
	public Tip getFootTip() {
		return area.getTip(x, y);
	}



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
	@Override
	public Texture getIcon()
	{
		return texture;
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


	public void launchScript(Assets assets, ScriptEventType eventType)
	{
		if (scripts == null) return ;
		if (! scripts.containsKey(eventType)) return ;

		String scriptCode = assets.getScriptCode(scripts.get(eventType).src); // TODO: No such script code

		try {
			assets.getScriptEngine().eval(scriptCode);
		} catch (ScriptException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}


	}



	@Override
	public String toString()
	{
		return id;
	}






}
