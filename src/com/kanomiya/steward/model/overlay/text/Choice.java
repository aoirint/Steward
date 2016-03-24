package com.kanomiya.steward.model.overlay.text;



/**
 * @author Kanomiya
 *
 */
public class Choice extends Text implements ISelectableText, IConfirmable<Character> {


	public static Choice create(Text text)
	{
		return new Choice(text);
	}

	public static Choice create(String text)
	{
		return new Choice(text);
	}




	protected ConfirmHandler confirmHandler;

	public Choice(Text text) {
		super(text);
	}

	public Choice(String text) {
		super(text);
	}





	/**
	* @inheritDoc
	*/
	@Override
	public Choice confirmHandler(ConfirmHandler confirmHandler) {
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






}
