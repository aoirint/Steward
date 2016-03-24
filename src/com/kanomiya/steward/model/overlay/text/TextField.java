package com.kanomiya.steward.model.overlay.text;


/**
 * @author Kanomiya
 *
 */
public class TextField extends Text implements IEditableText {

	public static TextField create()
	{
		return new TextField();
	}

	protected ConfirmHandler<String, ConfirmResult> confirmHandler;
	protected int caretIndex;

	protected TextField()
	{
		super("");
	}



	/**
	* @inheritDoc
	*/
	@Override
	public boolean multiLineIsAvailable()
	{
		return false;
	}

	/**
	* @inheritDoc
	*/
	@Override
	public char getConfirmationChar()
	{
		return '\n';
	}



	/**
	* @inheritDoc
	*/
	@Override
	public TextField confirmHandler(ConfirmHandler<String, ConfirmResult> confirmHandler) {
		this.confirmHandler = confirmHandler;
		return this;
	}

	/**
	* @inheritDoc
	*/
	@Override
	public boolean hasConfirmHandler() {
		return (confirmHandler != null);
	}

	/**
	* @inheritDoc
	*/
	@Override
	public ConfirmHandler getConfirmHandler() {
		return confirmHandler;
	}


	/**
	* @inheritDoc
	*/
	@Override
	public int getCaretIndex()
	{
		return caretIndex;
	}


	/**
	* @inheritDoc
	*/
	@Override
	public void setCaretIndex(int index)
	{
		caretIndex = index;
	}





}
