package com.kanomiya.steward.model.item;

import java.util.Map;

/**
 * @author Kanomiya
 *
 */
public class Material {

	protected String id;
	protected double weight;

	protected Map<ForceType, Double> force;
	protected Map<ForceType, Double> resistance;

	public Material() {  }



}
