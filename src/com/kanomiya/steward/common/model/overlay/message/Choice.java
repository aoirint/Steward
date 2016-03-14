package com.kanomiya.steward.common.model.overlay.message;

import jdk.nashorn.api.scripting.ScriptObjectMirror;

import com.kanomiya.steward.common.model.overlay.Text;


/**
 * @author Kanomiya
 *
 */
public class Choice extends Text {

	public static Choice createFromScript(char ch, String text, ScriptObjectMirror function)
	{
		return create(ch, text, ChoiceFunction.create(function));
	}


	public static Choice createFromScript(char ch, Text text, ScriptObjectMirror function)
	{
		return create(ch, text, ChoiceFunction.create(function));

	}

	public static Choice create(char ch, String text, ChoiceFunction function)
	{
		return new Choice(ch, text, function);
	}

	public static Choice create(char ch, Text text, ChoiceFunction function)
	{
		return new Choice(ch, text, function);
	}




	protected char ch;
	protected ChoiceFunction function;


	public Choice(char ch, String text, ChoiceFunction function) {
		super("[" + ch + "] " + text);

		this.ch = ch;
		this.function = function;
	}

	public Choice(char ch, Text text, ChoiceFunction function) {
		super(text);

		this.ch = ch;
		this.function = function;
	}


	public ChoiceResult apply()
	{
		return function.apply(ch);
	}


}
