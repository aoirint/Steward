package com.kanomiya.steward.common.view;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.util.Iterator;

import com.kanomiya.steward.common.model.assets.Assets;
import com.kanomiya.steward.common.model.overlay.PrettyText;
import com.kanomiya.steward.common.model.overlay.logger.IngameLogger;

/**
 * @author Kanomiya
 *
 */
public class VCIngameLogger implements IViewComponent<IngameLogger> {

	/**
	* @inheritDoc
	*/
	@Override
	public void paint(Graphics2D g, IngameLogger logger, Assets assets, int frame) {
		g.translate(logger.x, logger.y);

		g.setComposite(IngameLogger.alpha80);
		if (logger.hasBackground()) ViewConsts.vcTexture.paint(g, logger.getBackground(), assets, frame);
		g.setComposite(AlphaComposite.SrcOver);

		g.translate(PrettyText.lineHeight, PrettyText.lineHeight);
		g.setFont(PrettyText.textFont);


		Iterator<PrettyText> itr = logger.items.subList(logger.getTopIndexToShow(), logger.getLastIndexToShow()).iterator();

		int line = 0;
		int left = 0;

		while (line < IngameLogger.oneHeight && itr.hasNext())
		{
			PrettyText item = itr.next();
			int top = (line +1) *PrettyText.lineHeight;

			g.setColor(item.color);
			g.setFont(PrettyText.textFont);
			if (item.bold) g.setFont(PrettyText.textFontBold);

			String text = ViewConsts.wordWrap(item.text, g, left, IngameLogger.innerWidth);

			if (text.contains("\n"))
			{
				String[] lines = text.split("\n");
				int yLen = lines.length;

				for (int i=0; i<yLen; i++)
				{
					top += i *PrettyText.lineHeight;

					g.drawString(lines[i], left, top);
					if (item.underline) g.drawLine(left, top, g.getFontMetrics().stringWidth(lines[i]), top);

					left = 0;
				}

				line += yLen -1;

				left += g.getFontMetrics().stringWidth(lines[yLen -1]);

			} else
			{
				g.drawString(text, left, top);
				if (item.underline) g.drawLine(left, top, g.getFontMetrics().stringWidth(text), top);

				left += g.getFontMetrics().stringWidth(text);
			}



			if (item.lineBreak)
			{
				line ++;
				left = 0;
			}
		}

		g.translate(-PrettyText.lineHeight, -PrettyText.lineHeight);

		g.translate(-logger.x, -logger.y);
	}

}
