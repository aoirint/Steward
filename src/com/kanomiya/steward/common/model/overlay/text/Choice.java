package com.kanomiya.steward.common.model.overlay.text;



/**
 * @author Kanomiya
 *
 */
public class Choice extends Text implements ISelectableText, IConfirmable<Character> {


	public static Choice create(Character ch, Text text)
	{
		return new Choice(ch, text);
	}

	public static Choice create(Character ch, String text)
	{
		return new Choice(ch, text);
	}





	public Character ch;
	protected ConfirmHandler confirmHandler;


	public Choice(Character ch, Text text) {
		super(text);

		this.ch = ch;
	}

	public Choice(Character ch, String text) {
		super(((ch != null) ? "[" + ch + "] " : "") + text);

		this.ch = ch;
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

	public ConfirmResult confirm() {
		return confirm(ch);
	}





}
