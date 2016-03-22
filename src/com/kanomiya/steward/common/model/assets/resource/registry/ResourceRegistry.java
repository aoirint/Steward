package com.kanomiya.steward.common.model.assets.resource.registry;

import java.util.HashMap;

import com.kanomiya.steward.common.model.assets.resource.IResource;

/**
 * @author Kanomiya
 *
 */
public class ResourceRegistry<R extends IResource> extends HashMap<String, R> {

	public ResourceRegistry()
	{
		super();
	}



}
