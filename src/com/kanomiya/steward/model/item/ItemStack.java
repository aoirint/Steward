package com.kanomiya.steward.model.item;

/**
 * @author Kanomiya
 *
 */
public class ItemStack {

	public Item item;
	public int stackSize;

	public ItemStack(Item item, int stackSize)
	{
		this.item = item;
		this.stackSize = stackSize;
	}

	public ItemStack(Item item)
	{
		this(item, 1);
	}


	public static ItemStack create(Item item, int stackSize)
	{
		return new ItemStack(item, stackSize);
	}

	public static ItemStack create(Item item)
	{
		return ItemStack.create(item, 1);
	}

	public static boolean itemDataIsEquals(ItemStack a, ItemStack b)
	{
		return (a.item == b.item);
	}

}
