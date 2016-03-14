package com.kanomiya.steward.common;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptException;

import jdk.nashorn.api.scripting.NashornScriptEngineFactory;

import com.kanomiya.steward.common.model.assets.Assets;
import com.kanomiya.steward.common.model.event.Event;
import com.kanomiya.steward.common.model.event.Player;
import com.kanomiya.steward.common.model.overlay.GameColor;
import com.kanomiya.steward.common.model.overlay.Text;
import com.kanomiya.steward.common.model.overlay.message.Choice;
import com.kanomiya.steward.common.model.overlay.message.ChoiceResult;
import com.kanomiya.steward.common.model.overlay.message.Message;
import com.kanomiya.steward.common.model.overlay.message.MessageBook;
import com.kanomiya.steward.common.model.script.ScriptFunctionBinder;

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
		scriptEngine.put("Text", Text.class);
		scriptEngine.put("Choice", Choice.class);
		scriptEngine.put("ChoiceResult", ChoiceResult.class);
		scriptEngine.put("Message", Message.class);
		scriptEngine.put("Book", MessageBook.class);
		scriptEngine.put("GameColor", GameColor.class);

		scriptEngine.put("binder", new ScriptFunctionBinder(assets, thePlayer));

		try {
			scriptEngine.eval("var translate = Function.prototype.bind.call(assets.translate, assets);");
			scriptEngine.eval("var text = Function.prototype.bind.call(Text.static.create, Text);");
			scriptEngine.eval("var choice = Function.prototype.bind.call(Choice.static.createFromScript, Choice);");
			scriptEngine.eval("var message = Function.prototype.bind.call(Message.static.create, Message);");
			scriptEngine.eval("var book = Function.prototype.bind.call(Book.static.create, Book);");
			scriptEngine.eval("var showMessage = Function.prototype.bind.call(player.showMessage, player);");



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
