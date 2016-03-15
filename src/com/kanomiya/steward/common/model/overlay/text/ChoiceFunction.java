package com.kanomiya.steward.common.model.overlay.text;

import java.util.function.Function;

import jdk.nashorn.api.scripting.ScriptObjectMirror;

/**
 * @author Kanomiya
 *
 */
public abstract class ChoiceFunction implements Function<Character, ChoiceResult> {

	/**
	 * @param function
	 * @return
	 */
	public static ChoiceFunction create(ScriptObjectMirror function)
	{
		return new ChoiceFunction() {

			@Override
			public ChoiceResult apply(Character ch) {
				function.call(function, ch); // TODO return

				return null;
			}};

	}

}
