package com.kanomiya.steward.common.model.overlay.text;


/**
 * @author Kanomiya
 *
 */
public class TextField extends Text implements IEditableText {

	public static TextField create()
	{
		return new TextField();
	}

	public static TextField create(String defaulz)
	{
		return new TextField(defaulz);
	}

	protected ConfirmHandler confirmHandler;
	protected int caretIndex;

	public TextField()
	{
		super("");
	}

	public TextField(String defaulz)
	{
		super(defaulz);
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
	public IConfirmable confirmHandler(ConfirmHandler confirmHandler) {
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
