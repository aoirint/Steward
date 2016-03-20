package com.kanomiya.steward.common.model.overlay.text;

/**
 * @author Kanomiya
 *
 */
public interface IEditableText extends ISelectableText, IConfirmable {

	public boolean multiLineIsAvailable();

	public char getConfirmationChar();

	public int getCaretIndex();
	public void setCaretIndex(int index);


}
