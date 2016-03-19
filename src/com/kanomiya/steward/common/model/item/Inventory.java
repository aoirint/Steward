package com.kanomiya.steward.common.model.item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.google.gson.annotations.Expose;

/**
 * @author Kanomiya
 *
 */
public class Inventory {

	@Expose public List<ItemStack> stacks;

	public Inventory()
	{
		stacks = new ArrayList<>();
	}

	public void getItem(ItemStack itemStack)
	{
		for (int i=0; i<stacks.size(); i++)
		{
			if (ItemStack.itemDataIsEquals(stacks.get(i), itemStack))
			{
				stacks.get(i).stackSize += itemStack.stackSize;
				return ;
			}
		}

		stacks.add(itemStack);

		Collections.sort(stacks, new Comparator<ItemStack>()
				{
					@Override
					public int compare(ItemStack a, ItemStack b) {
						int hashA = a.item.id.hashCode();
						int hashB = b.item.id.hashCode();

						return (hashA < hashB) ? 1 : (hashA < hashB) ? -1 : 0;
					}
				});

	}



}
