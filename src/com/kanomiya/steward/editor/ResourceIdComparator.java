package com.kanomiya.steward.editor;

import java.text.Collator;
import java.util.Comparator;

import com.kanomiya.steward.model.assets.resource.IResource;

/**
 * @author Kanomiya
 *
 */
public class ResourceIdComparator implements Comparator<IResource> {

	protected Collator collator;

	public ResourceIdComparator(Collator collator) {
		this.collator = collator;
	}

	/**
	* @inheritDoc
	*/
	@Override
	public int compare(IResource o1, IResource o2) {
		return collator.compare(o1.getId(), o2.getId());
	}

}
