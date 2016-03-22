package com.kanomiya.steward.common.model.area;

import com.kanomiya.steward.common.model.util.IEnumWithId;

/**
 * @author Kanomiya
 *
 */
public enum AccessType implements IEnumWithId {
	ALLOW("allow"),
	DENY("deny"),

	;

	private String id;

	private AccessType(String id)
	{
		this.id = id;
	}


	@Override
	public String getId() {
		return id;
	}


	/**
	 * @param id
	 * @return
	 */
	public static AccessType getFromId(String id)
	{
		AccessType[] values = values();

		for (AccessType type: values)
		{
			if (id.equals(type.id)) return type;
		}

		throw new IllegalArgumentException("No such object in AccessType: name=" + id);
	}



}
