package com.kanomiya.steward.common.model.overlay.text;

import jdk.nashorn.api.scripting.ScriptObjectMirror;


/**
 * @author Kanomiya
 *
 */
public class Choice extends Text {


	public static Choice createFromScript(String text, ScriptObjectMirror function)
	{
		return create(null, text, ChoiceFunction.create(function));
	}

	public static Choice createFromScript(Text text, ScriptObjectMirror function)
	{
		return create(null, text, ChoiceFunction.create(function));
	}

	public static Choice createFromScript(Character ch, String text, ScriptObjectMirror function)
	{
		return create(ch, text, ChoiceFunction.create(function));
	}

	public static Choice createFromScript(Character ch, Text text, ScriptObjectMirror function)
	{
		return create(ch, text, ChoiceFunction.create(function));
	}

	public static Choice create(Character ch, String text, ChoiceFunction function)
	{
		return new Choice(ch, text, function);
	}

	public static Choice create(Character ch, Text text, ChoiceFunction function)
	{
		return new Choice(ch, text, function);
	}




	public Character ch;
	protected ChoiceFunction function;


	public Choice(Character ch, String text, ChoiceFunction function) {
		super(((ch != null) ? "[" + ch + "] " : "") + text);

		this.ch = ch;
		this.function = function;
	}

	public Choice(Character ch, Text text, ChoiceFunction function) {
		super(text);

		this.ch = ch;
		this.function = function;
	}


	public ChoiceResult apply()
	{
		return function.apply(ch);
	}


}
