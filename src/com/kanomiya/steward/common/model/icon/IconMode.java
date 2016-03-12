package com.kanomiya.steward.common.model.icon;

/**
 * @author Kanomiya
 *
 */
public enum IconMode {
	STATIC("static", false),
	ANIMATION("animation", true),

	;

	private String id;
	private boolean requireInterval;

	private IconMode(String id, boolean requireInterval)
	{
		this.id = id;
		this.requireInterval = requireInterval;
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

	public boolean requireInterval()
	{
		return requireInterval;
	}

}
