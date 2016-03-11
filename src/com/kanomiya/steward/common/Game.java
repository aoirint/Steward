package com.kanomiya.steward.common;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import com.kanomiya.steward.common.model.assets.Assets;
import com.kanomiya.steward.common.model.event.Player;
import com.kanomiya.steward.common.model.icon.Icon;

/**
 * @author Kanomiya
 *
 */
public class Game{

	public static int tickMills = 100;

	public Assets assets;
	public Player thePlayer;

	public Game(Assets assets)
	{
		this.assets = assets;
		thePlayer = new Player(assets.getArea("test"), 3, 3, new Icon("event/player.png")); // TODO: ソース直書き


		ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
		ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("JavaScript");
		// TODO: スコープ

		scriptEngine.put("assets", assets);
		scriptEngine.put("player", thePlayer);

		assets.setScriptEngine(scriptEngine);;
	}


	public void turn()
	{
		// TODO: others

		thePlayer.turn(assets);
	}

}
