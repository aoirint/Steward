package com.kanomiya.steward.common.model.event;

import java.util.Map;

import javax.script.ScriptException;

import com.kanomiya.steward.common.model.ITurnObject;
import com.kanomiya.steward.common.model.area.AccessType;
import com.kanomiya.steward.common.model.area.Area;
import com.kanomiya.steward.common.model.area.Tile;
import com.kanomiya.steward.common.model.assets.Assets;
import com.kanomiya.steward.common.model.icon.Icon;
import com.kanomiya.steward.common.model.script.Script;
import com.kanomiya.steward.common.model.script.ScriptEventType;

/**
 * @author Kanomiya
 *
 */
public class Event implements ITurnObject {

	public String id;
	public Area area;
	public int x, y;

	public AccessType access;
	public Map<ScriptEventType, Script> scripts;

	public Icon icon;

	/*
	 * 直接Areaオブジェクトを持つように
	 */

	public Event()
	{
		this(null, null, 0, 0, null);
	}

	public Event(String id, Area area, int x, int y, Icon icon)
	{
		this(id, area, x, y, icon, AccessType.allow);
	}

	public Event(String id, Area area, int x, int y, Icon icon, AccessType access)
	{
		this(id, area, x, y, icon, access, null);
	}

	public Event(String id, Area area, int x, int y, Icon icon, AccessType access, Map<ScriptEventType, Script> scripts)
	{
		this.id = id;
		this.area = area;
		this.x = x;
		this.y = y;

		this.icon = icon;
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

		if (! area.tileExists(fx, fy)) return false;

		Tile ftile = area.getTile(fx, fy);

		if (ftile.hasEvent())
		{
			Event fevent = ftile.getEvent();

			fevent.launchScript(assets, ScriptEventType.onColided);
		}

		if (! area.canEnter(this, fx, fy)) return false;


		x = fx;
		y = fy;

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
	public Tile getFootTile() {
		return area.getTile(x, y);
	}



	/**
	 * @return
	 */
	public Icon getIcon() {
		return icon;
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
