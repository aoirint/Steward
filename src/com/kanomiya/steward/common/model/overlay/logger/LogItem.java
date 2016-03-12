package com.kanomiya.steward.common.model.overlay.logger;


/**
 * @author Kanomiya
 *
 */
public class LogItem {

	public PrettyText pretty;
	public boolean lineBreak;

	public LogItem(PrettyText pretty, boolean lineBreak)
	{
		this.pretty = pretty;
		this.lineBreak = lineBreak;
	}

}
