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
		int x = 0;

		while (line < 14 && itr.hasNext())
		{
			LogItem item = itr.next();
			int y = line +1;

			g.setColor(item.pretty.color);

			String text = item.pretty.text;
			int textLen = text.length();
			int width = g.getFontMetrics().stringWidth(text);
			int right = x +width;


			if (IngameLogger.innerWidth < right) // 折り返し
			{
				StringBuilder stack = new StringBuilder(textLen +20);
				StringBuilder builder = null;

				int dx = x;

				for (int i=0; i<textLen; i++)
				{
					if (builder == null) builder = new StringBuilder(textLen);

					builder.append(text.charAt(i));

					int dw = g.getFontMetrics().stringWidth(builder.toString());
					int dr = dx +dw;

					if (IngameLogger.innerWidth -20 < dr)
					{
						stack.append(builder);
						stack.append('\n');
						builder = null;
					}

				}

				if (builder != null) stack.append(builder);

				text = stack.toString();
			}

			if (text.contains("\n"))
			{
				String[] lines = text.split("\n");
				int yLen = lines.length;

				for (int i=0; i<yLen; i++)
				{
					g.drawString(lines[i], x, (y +i) *IngameLogger.lineHeight);
					x = 0;
				}

				line += yLen;

			} else
			{
				g.drawString(text, x, y *IngameLogger.lineHeight);
			}


			x = right;

			if (item.lineBreak)
			{
				line ++;
				x = 0;
			}
		}

		g.translate(-IngameLogger.lineHeight, -IngameLogger.lineHeight);

		g.translate(-logger.x, -logger.y);
	}

}
