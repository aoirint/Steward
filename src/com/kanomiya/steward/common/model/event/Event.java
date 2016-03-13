package com.kanomiya.steward.common.model.event;

import java.util.List;
import java.util.Map;

import javax.script.ScriptException;

import com.google.gson.annotations.Expose;
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
public class Event implements ITextureOwner {

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

	public Assets assets;


	public Event(Assets assets)
	{
		this(null, null, 0, 0, null, assets);
	}

	public Event(String id, Area area, int x, int y, Texture texture, Assets assets)
	{
		this(id, area, x, y, texture, AccessType.allow, assets);
	}

	public Event(String id, Area area, int x, int y, Texture texture, AccessType access, Assets assets)
	{
		this(id, area, x, y, texture, Direction.SOUTH, WalkState.UPRIGHT, access, null, assets);
	}

	public Event(String id, Area area, int x, int y, Texture texture,
			Direction direction, WalkState walkState,
			AccessType access, Map<ScriptEventType, Script> scripts,
			Assets assets)
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

		if (texture.type.isDirectable()) direction = Direction.getDirection(x, y, fx, fy, direction);
		if (texture.type.isWalkable()) walkState = walkState.next();

		if (! area.tipExists(x, y)) return false;
		if (area.getTip(fx, fy).getAccessType() == AccessType.deny) return false;

		Area area = this.area;
		int x = this.x;
		int y = this.y;

		this.x = fx;
		this.y = fy;

		List<Event> elist = area.getEvents(fx, fy);
		boolean canEnter = true;

		for (int i=0; i<elist.size(); i++)
		{
			elist.get(i).launchScript(assets, ScriptEventType.ONCOLIDED);
			canEnter = canEnter && (elist.get(i).access != AccessType.deny);
		}

		if (! canEnter && area == this.area)
		{
			this.x = x;
			this.y = y;
		}

		this.area.setEvent(this);

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

		area.launchEvent(this, x, y, ScriptEventType.ONCOLIDED, assets);

		System.out.println(this.x + "/" + this.y); // TODO: デバッグ
		this.x = x;
		this.y = y;

		System.out.println(this.x + "/" + this.y);

		area.setEvent(this);

		System.out.println(this.x + "/" + this.y);

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
