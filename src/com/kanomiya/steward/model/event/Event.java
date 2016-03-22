package com.kanomiya.steward.model.event;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.script.ScriptEngine;
import javax.script.ScriptException;

import com.kanomiya.steward.model.area.AccessType;
import com.kanomiya.steward.model.area.Area;
import com.kanomiya.steward.model.area.Chunk;
import com.kanomiya.steward.model.area.Tip;
import com.kanomiya.steward.model.assets.Assets;
import com.kanomiya.steward.model.assets.resource.IResource;
import com.kanomiya.steward.model.item.Inventory;
import com.kanomiya.steward.model.script.Script;
import com.kanomiya.steward.model.script.ScriptEventType;
import com.kanomiya.steward.model.texture.ITextureOwner;
import com.kanomiya.steward.model.texture.Texture;

/**
 * @author Kanomiya
 *
 */
public class Event implements ITextureOwner, IResource {

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

		List<Event> elist = area.getEvents(fx, fy);
		boolean canEnter = true;

		for (int i=0; i<elist.size(); i++)
		{
			elist.get(i).launchScript(assets, this, ScriptEventType.ONCOLIDED);
			canEnter = canEnter && (elist.get(i).access != AccessType.DENY);
		}

		if (canEnter && area == this.area && this.x == x && this.y == y)
		{
			this.x = fx;
			this.y = fy;
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


	public boolean travel(Area area, int x, int y) // TODO: player travelling, remove message or not
	{
		if (! canTravel(area, x, y)) return false;

		area.launchEvent(this, x, y, ScriptEventType.ONCOLIDED);

		this.x = x;
		this.y = y;

		area.setEvent(this);

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



	public void launchScript(Assets assets, Event launcher, ScriptEventType eventType)
	{
		if (scripts == null) return ;
		if (! scripts.containsKey(eventType)) return ;

		Script script = scripts.get(eventType);
		String scriptCode = assets.getScriptCode(script.src).code; // TODO: No such script code

		ScriptEngine scriptEngine = assets.getScriptEngine();

		scriptEngine.put("launcher", launcher);
		scriptEngine.put("owner", this);

		if (script.hasArgument())
		{
			// Bindings bindings = scriptEngine.createBindings();
			Iterator<Entry<String, Object>> itr = script.args.entrySet().iterator();

			while (itr.hasNext())
			{
				Entry<String, Object> entry = itr.next();
				scriptEngine.put(entry.getKey(), entry.getValue());
			}

		}

		try {
			scriptEngine.eval(scriptCode);

		} catch (ScriptException e) {
			// TODO 自動生成された catch ブロック
			System.err.println("Excepion source: " + script.src);
			e.printStackTrace();

		} catch (Exception e)
		{
			// TODO
			System.err.println("Excepion source: " + script.src);
			e.printStackTrace();
		}


	}



	@Override
	public String toString()
	{
		return id;
	}






}
