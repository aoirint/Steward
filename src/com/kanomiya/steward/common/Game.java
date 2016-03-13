package com.kanomiya.steward.common;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptException;

import jdk.nashorn.api.scripting.NashornScriptEngineFactory;

import com.kanomiya.steward.common.model.assets.Assets;
import com.kanomiya.steward.common.model.event.Event;
import com.kanomiya.steward.common.model.event.Player;
import com.kanomiya.steward.common.model.overlay.PrettyText;

/**
 * @author Kanomiya
 *
 */
public class Game {

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

		scriptEngine.put("logger", thePlayer.logger);
		scriptEngine.put("PrettyText", PrettyText.class);

		try {
			scriptEngine.eval("var translate = function(unlocalized, args) { assets.translate(unlocalized, args); };");

		} catch (ScriptException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}


		assets.setScriptEngine(scriptEngine);
	}


	public void turn()
	{
		// TODO: others

		thePlayer.turn();

		List<Event> eventList = thePlayer.area.eventList();
		int elLen = eventList.size();

		for (int i=0; i<elLen; i++)
		{
			eventList.get(i).turn();
		}

	}


}
