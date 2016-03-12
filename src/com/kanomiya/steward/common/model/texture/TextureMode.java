package com.kanomiya.steward.common.model.texture;

/**
 * @author Kanomiya
 *
 */
public enum TextureMode {
	STATIC("static", false),
	ANIMATION("animation", true),

	;

	private String id;
	private boolean requireInterval;

	private TextureMode(String id, boolean requireInterval)
	{
		this.id = id;
		this.requireInterval = requireInterval;
	}

	public static TextureMode getFromId(String id)
	{
		TextureMode[] values = values();

		for (TextureMode mode: values)
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
