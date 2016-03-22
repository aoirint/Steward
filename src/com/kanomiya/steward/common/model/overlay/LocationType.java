package com.kanomiya.steward.common.model.overlay;

import com.kanomiya.steward.common.model.util.TBLR;

/**
 * @author Kanomiya
 *
 */
public enum LocationType {
	TOP_LEFT(TBLR.TOP, TBLR.LEFT),
	TOP_RIGHT(TBLR.TOP, TBLR.RIGHT),
	BOTTOM_LEFT(TBLR.BOTTOM, TBLR.LEFT),
	BOTTOM_RIGHT(TBLR.BOTTOM, TBLR.RIGHT),

	CENTER,

	;

	private TBLR[] tblrs;

	private LocationType(TBLR... tblrs)
	{
		this.tblrs = tblrs;
	}

	public boolean has(TBLR tblr)
	{
		if (tblr != null)
		{
			for (int i=0; i<tblrs.length; i++)
			{
				if (tblrs[i] == tblr) return true;
			}
		}

		return false;
	}

}
