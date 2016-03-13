package com.kanomiya.steward.common.model.script;

import com.kanomiya.steward.common.model.IEnumWithId;

/**
 * @author Kanomiya
 *
 */
public enum ScriptEventType implements IEnumWithId {
	ONCOLIDED("onColided"),



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

		throw new IllegalArgumentException("No such object in IconType: name=" + id);
	}



}
