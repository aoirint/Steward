package com.kanomiya.steward.model.overlay.text;

/**
 * @author Kanomiya
 *
 */
public interface IEditableText extends ISelectableText, IConfirmable<String> {

	public boolean multiLineIsAvailable();

	public char getConfirmationChar();

	public int getCaretIndex();
	public void setCaretIndex(int index);


}
