package com.kanomiya.steward.model.overlay.text;

import jdk.nashorn.api.scripting.ScriptObjectMirror;

/**
 * @author Kanomiya
 *
 */
public interface IConfirmable<T> {

	boolean hasConfirmHandler();
	ConfirmHandler<T, ConfirmResult> getConfirmHandler();

	// ConfirmResult confirm();

	default ConfirmResult confirm(T arg)
	{
		if (! hasConfirmHandler()) return null;
		if (! isEnabled()) return null;

		return getConfirmHandler().apply(arg);
	}


	IConfirmable confirmHandler(ConfirmHandler<T, ConfirmResult> confirmHandler);

	default IConfirmable confirmHandlerJS(ScriptObjectMirror jsFunction)
	{
		return confirmHandler(ConfirmHandler.create(jsFunction));
	}


	boolean isEnabled();
	IConfirmable enabled(boolean enabled);

}
