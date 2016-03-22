package com.kanomiya.steward.common.model.item;

import com.kanomiya.steward.common.model.assets.resource.IResource;
import com.kanomiya.steward.common.model.texture.Texture;


/**
 * @author Kanomiya
 *
 */
public class Item implements IResource {

	protected String id;
	protected String unlocalizedName;
	protected Texture icon;


	protected double weight;


	public Item()
	{

	}



	/**
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return unlocalizedName
	 */
	public String getUnlocalizedName() {
		return unlocalizedName;
	}

	/**
	 * @param id セットする id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @param unlocalizedName セットする unlocalizedName
	 */
	public void setUnlocalizedName(String unlocalizedName) {
		this.unlocalizedName = unlocalizedName;
	}

	/**
	 * @return icon
	 */
	public Texture getIcon() {
		return icon;
	}

	/**
	 * @return weight
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * @param icon セットする icon
	 */
	public void setIcon(Texture icon) {
		this.icon = icon;
	}

	/**
	 * @param weight セットする weight
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}



}
