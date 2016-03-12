package com.kanomiya.steward.common.view;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.util.Iterator;

import com.kanomiya.steward.common.model.assets.Assets;
import com.kanomiya.steward.common.model.overlay.logger.IngameLogger;
import com.kanomiya.steward.common.model.overlay.logger.LogItem;

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

		g.translate(IngameLogger.lineHeight, IngameLogger.lineHeight);
		g.setFont(IngameLogger.textFont);


		Iterator<LogItem> itr = logger.items.listIterator(Math.max(0, logger.items.size() -14));

		int line = 0;
		int left = 0;

		while (line < 14 && itr.hasNext())
		{
			LogItem item = itr.next();
			int y = line +1;

			g.setColor(item.pretty.color);

			String text = ViewConsts.wordWrap(item.pretty.text, g, left, IngameLogger.innerWidth);

			if (text.contains("\n"))
			{
				String[] lines = text.split("\n");
				int yLen = lines.length;

				for (int i=0; i<yLen; i++)
				{
					g.drawString(lines[i], left, (y +i) *IngameLogger.lineHeight);
					left = 0;
				}

				line += yLen -1;

				left += g.getFontMetrics().stringWidth(lines[yLen -1]);

			} else
			{
				g.drawString(text, left, y *IngameLogger.lineHeight);

				left += g.getFontMetrics().stringWidth(text);
			}



			if (item.lineBreak)
			{
				line ++;
				left = 0;
			}
		}

		g.translate(-IngameLogger.lineHeight, -IngameLogger.lineHeight);

		g.translate(-logger.x, -logger.y);
	}

}
