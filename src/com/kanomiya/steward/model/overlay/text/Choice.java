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
	protected boolean enabled;

	public Choice(Text text) {
		super(text);

		enabled = true;
	}

	public Choice(String text) {
		super(text);

		enabled = true;
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



	/**
	* @inheritDoc
	*/
	@Override
	public boolean isEnabled() {
		return enabled;
	}


	/**
	* @inheritDoc
	*/
	@Override
	public Choice enabled(boolean enabled) {
		this.enabled = enabled;
		return this;
	}






}
