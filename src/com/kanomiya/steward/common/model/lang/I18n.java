package com.kanomiya.steward.common.model.lang;

import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Kanomiya
 *
 */
public class I18n {

	public static Pattern argPattern = Pattern.compile("%(.*?)%");

	protected Locale locale;
	protected PropertyResourceBundle mappings;

	public I18n(Locale locale, PropertyResourceBundle mappings)
	{
		this.locale = locale;
		this.mappings = mappings;
	}


	public String translate(String unlocalized, String... args)
	{
		if (! mappings.containsKey(unlocalized)) return unlocalized;

		String localized = mappings.getString(unlocalized);

		if (0 < args.length)
		{
			Matcher argMatcher;
			int num = 0;
			while ((argMatcher = argPattern.matcher(localized)).matches())
			{
				if (num < args.length) localized.replace(argMatcher.group(), args[num]);
				++ num;
			}
		}

		return localized;
	}


	public Locale getLocale()
	{
		return locale;
	}

}
