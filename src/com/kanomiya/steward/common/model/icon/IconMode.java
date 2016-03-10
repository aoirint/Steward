package com.kanomiya.steward.common.model.icon;

/**
 * @author Kanomiya
 *
 */
public enum IconMode {
	STATIC("static"),
	ANIMATION("animation"),

	;

	private String id;

	private IconMode(String id)
	{
		this.id = id;
	}

	public static IconMode getFromId(String id)
	{
		IconMode[] values = values();

		for (IconMode mode: values)
		{
			if (id.equals(mode.id)) return mode;
		}

		throw new IllegalArgumentException("No such object in IconMode: name=" + id);
	}

	public String getId()
	{
		return id;
	}


}
