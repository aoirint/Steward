package com.kanomiya.steward.common.model.texture;

import java.util.List;

import com.google.common.collect.Lists;
import com.google.gson.annotations.Expose;

/**
 * @author Kanomiya
 *
 */
public class IdWithRange {

	@Expose public String id;
	@Expose public int from, to;


	public static List<IdWithRange> list(List<Object> argList)
	{
		List<IdWithRange> rangeList = Lists.newArrayList();

		if (argList != null)
		{
			Object prev = null;
			IdWithRange temp = null;

			for (int i=0; i<argList.size(); i++)
			{
				if (temp == null)
				{
					temp = new IdWithRange();
					temp.from = temp.to = i;
				}

				Object current = argList.get(i);

				if (prev != null && current != null)
				{
					if (prev.equals(current))
					{
						temp.to = i;
						continue;
					} else
					{
						rangeList.add(temp);
						temp = null;
						continue;
					}

				} else if (prev == null && current == null)
				{
					temp.to = i;
					continue;
				} else
				{
					rangeList.add(temp);
					temp = null;
					continue;
				}

			}

		}

		return rangeList;
	}

}
