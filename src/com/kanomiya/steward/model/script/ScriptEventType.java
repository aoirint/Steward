package com.kanomiya.steward.model.script;

import com.kanomiya.steward.model.util.IEnumWithId;

/**
 * @author Kanomiya
 *
 */
public enum ScriptEventType implements IEnumWithId {
	ONENTERED("onEntered"),
	ONEXITED("onExited"),



	;

	private String id;

	private ScriptEventType(String id)
	{
		this.id = id;
	}

	/**
	* @inheritDoc
	*/
	@Override
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 * @return
	 */
	public static ScriptEventType getFromId(String id)
	{
		ScriptEventType[] values = values();

		for (ScriptEventType type: values)
		{
			if (id.equals(type.id)) return type;
		}

		throw new IllegalArgumentException("No such object in ScriptEventType: name=" + id);
	}



}
