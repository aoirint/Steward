package com.kanomiya.steward.common;
import javax.script.ScriptEngine;

import jdk.nashorn.api.scripting.NashornScriptEngineFactory;

import com.kanomiya.steward.common.model.assets.Assets;
import com.kanomiya.steward.common.model.event.Player;
import com.kanomiya.steward.common.model.overlay.logger.IngameLogger;

/**
 * @author Kanomiya
 *
 */
public class Game{

	public static int tickMills = 100;

	public Assets assets;
	public Player thePlayer;

	public Game(Assets assets, Player thePlayer)
	{
		this.assets = assets;
		this.thePlayer = thePlayer;

		NashornScriptEngineFactory factory = new NashornScriptEngineFactory();
		ScriptEngine scriptEngine = factory.getScriptEngine("-strict", "--no-java", "--no-syntax-extensions");

		scriptEngine.put("assets", assets);
		scriptEngine.put("player", thePlayer);

		scriptEngine.put("IngameLogger", IngameLogger.class);

		assets.setScriptEngine(scriptEngine);
	}


	public void turn()
	{
		// TODO: others

		thePlayer.turn(assets);
	}

}
