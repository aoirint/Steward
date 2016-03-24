package com.kanomiya.steward.model.overlay.text;

import java.util.function.Function;

import jdk.nashorn.api.scripting.ScriptObjectMirror;

import com.kanomiya.steward.Game;

/**
 * @author Kanomiya
 *
 */
public abstract class ConfirmHandler<T, R> implements Function<T, R> {

	/**
	 * @param function
	 * @return
	 */
	public static <T, R> ConfirmHandler create(ScriptObjectMirror jsFunction)
	{
		return new ConfirmHandler<T, R>() {

			@Override
			public R apply(T arg) {
				try
				{
					jsFunction.call(jsFunction, arg); // TODO return

				} catch (Exception e)
				{
					Game.logger.warn("Exception source: ConfirmHandler");
					e.printStackTrace();
				}

				return null;
			}};

	}

}
