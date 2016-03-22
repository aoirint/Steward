package com.kanomiya.steward.model.lang;

import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.kanomiya.steward.model.assets.resource.IResource;

/**
 * @author Kanomiya
 *
 */
public class Language implements IResource {

	public static Pattern argPattern = Pattern.compile("%(.*?)%");

	protected String id;
	protected Locale locale;
	public PropertyResourceBundle mappings;

	public Language(String id, Locale locale, PropertyResourceBundle mappings)
	{
		this.id = id;
		this.locale = locale;
		this.mappings = mappings;
	}

	/**
	* @inheritDoc
	*/
	@Override
	public String getId() {
		return id;
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
