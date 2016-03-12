package com.kanomiya.steward.common.model;

import java.util.Locale;
import java.util.PropertyResourceBundle;

/**
 * @author Kanomiya
 *
 */
public class I18n {

	protected Locale locale;
	protected PropertyResourceBundle mappings;

	public I18n(Locale locale, PropertyResourceBundle mappings)
	{
		this.locale = locale;
		this.mappings = mappings;
	}

	public String translate(String unlocalizedName)
	{
		if (! mappings.containsKey(unlocalizedName)) return unlocalizedName;
		return mappings.getString(unlocalizedName);
	}

	public Locale getLocale()
	{
		return locale;
	}

}
