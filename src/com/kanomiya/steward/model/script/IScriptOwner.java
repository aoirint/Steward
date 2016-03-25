package com.kanomiya.steward.model.script;

import java.util.Map;

/**
 * @author Kanomiya
 *
 */
public interface IScriptOwner {

	boolean hasScript(ScriptEventType eventType);
	Script getScript(ScriptEventType eventType);

	default void initArguments(IScriptLauncher launcher, Map<String, Object> arguments)
	{
		arguments.put("owner", this);
		arguments.put("launcher", launcher);

		arguments.replaceAll((String key, Object value) -> (value.equals("$this")) ? this : value);


	}

	/**
	 * @param type
	 * @param script
	 */
	void setScript(ScriptEventType type, Script script);


}
