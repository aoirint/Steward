package com.kanomiya.steward.common.view;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.util.Iterator;

import com.kanomiya.steward.common.model.assets.Assets;
import com.kanomiya.steward.common.model.overlay.PrettyText;
import com.kanomiya.steward.common.model.overlay.logger.IngameLogger;
import com.kanomiya.steward.common.model.overlay.message.MessageBox;

/**
 * @author Kanomiya
 *
 */
public class VCMessageBox implements IViewComponent<MessageBox> {

	/**
	* @inheritDoc
	*/
	@Override
	public void paint(Graphics2D g, MessageBox box, Assets assets, int frame) {
		g.translate(box.x, box.y);

		g.setComposite(ViewConsts.alpha80);
		if (box.hasBackground()) ViewConsts.vcTexture.paint(g, box.getBackground(), assets, frame);
		g.setComposite(AlphaComposite.SrcOver);

		g.translate(PrettyText.lineHeight, PrettyText.lineHeight);
		g.setFont(PrettyText.textFont);


		Iterator<PrettyText> itr = box.items.subList(box.getTopIndexToShow(), box.getLastIndexToShow(IngameLogger.oneHeight)).iterator();

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

		g.translate(-box.x, -box.y);
	}

}
