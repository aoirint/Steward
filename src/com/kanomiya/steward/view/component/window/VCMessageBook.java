package com.kanomiya.steward.view.component.window;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Iterator;

import com.kanomiya.steward.model.assets.Assets;
import com.kanomiya.steward.model.overlay.GameColor;
import com.kanomiya.steward.model.overlay.GameFont;
import com.kanomiya.steward.model.overlay.text.IEditableText;
import com.kanomiya.steward.model.overlay.text.ISelectableText;
import com.kanomiya.steward.model.overlay.text.Text;
import com.kanomiya.steward.model.overlay.window.message.Message;
import com.kanomiya.steward.model.overlay.window.message.MessageBook;
import com.kanomiya.steward.view.ViewConsts;
import com.kanomiya.steward.view.ViewUtils;
import com.kanomiya.steward.view.component.IViewComponent;

/**
 * @author Kanomiya
 *
 */
public class VCMessageBook implements IViewComponent<MessageBook> {

	public static boolean containsBtnCheck(MessageBook book, int x, int y)
	{
		int dx = 0;
		if (book.isFirstPage() && book.pageCount() != 1) dx += -27;

		return (book.width -30 +dx <= x && x < book.width -30 +dx +27 && 20 <= y && y < 20 +27);
	}

	public static boolean containsBtnRight(MessageBook book, int x, int y)
	{
		int dx = 0;
		if (! book.isFirstPage() && book.isClosable()) dx += -27;
		return (book.width -30 +dx <= x && x < book.width -30 +dx +27 && 20 <= y && y < 20 +27);
	}

	public static boolean containsBtnLeft(MessageBook book, int x, int y)
	{
		int dx = 0;
		if (book.isClosable()) dx += -27;
		if (! book.isLastPage()) dx += -27;
		return (book.width -30 +dx <= x && x < book.width -30 +dx +27 && 20 <= y && y < 20 +27);
	}

	/*
	public static boolean containsBtnUp(MessageBook book, int x, int y)
	{
		return (book.width -30 -27 <= x && x < book.width -30 -27 +27 && book.height -30 <= y && y < book.height -30 +27);
	}

	public static boolean containsBtnDown(MessageBook book, int x, int y)
	{
		return (book.width -30 <= x && x < book.width -30 +27 && book.height -30 <= y && y < book.height -30 +27);
	}
	*/


	/**
	* @inheritDoc
	*/
	@Override
	public void paint(Graphics2D g, MessageBook book, Assets assets, int frame)
	{
		if (! book.hasPage()) return;

		Message page = book.currentPage();


		ISelectableText selected = (page.hasSelectable()) ? page.getSelectedText() : null;


		g.setComposite(ViewConsts.alpha80);
		if (book.hasBackground()) ViewConsts.vcTexture.paint(g, book.getBackground(), assets, frame);
		else
		{
			g.setColor(GameColor.background);
			g.fillRect(0, 0, book.width, book.height);
		}



		int x1 = book.width -30;
		int y1 = 20;
		int x3 = 0;

		g.translate(x1, y1);

		int x2 = 0;
		if (! book.isFirstPage() && book.isClosable())
		{
			x2 = -27;
			ViewConsts.vcTexture.paint(g, assets.textureRegistry.get("check"), assets, frame);
		}

		g.translate(x2, 0);

		if (! book.isLastPage())
		{
			x3 = -27;
			ViewConsts.vcTexture.paint(g, assets.textureRegistry.get("arrowBtnRight"), assets, frame);
		}

		g.translate(x3, 0);

		int x4 = 0;

		if (! book.isFirstPage())
		{
			x4 = -27;
			ViewConsts.vcTexture.paint(g, assets.textureRegistry.get("arrowBtnLeft"), assets, frame);
		}

		g.translate(x4, 0);

		if (book.isFirstPage() && book.isClosable())
		{
			ViewConsts.vcTexture.paint(g, assets.textureRegistry.get("check"), assets, frame);
		}

		g.translate(-x2 -x4, -y1);


		/*
		int x5 = -24;
		int y2 = book.height -30;

		g.translate(0, y2);

		if (topIndex < itemCount -1)
			ViewConsts.vcTexture.paint(g, ViewConsts.texArrowBtnDown, assets, frame); // 516, 196

		g.translate(x5, 0);

		if (0 < topIndex)
			ViewConsts.vcTexture.paint(g, ViewConsts.texArrowBtnUp, assets, frame); // 492, 196

		g.translate(-x5, -y2);
		*/

		g.translate(-x1 -x3, 0);


		g.translate(Text.defaultHeight, Text.defaultHeight);
		g.setFont(GameFont.textFont);


		g.setComposite(AlphaComposite.SrcOver);



		if (page.hasItem())
		{
			// int topIndex = 0; // (book.scrollType() == ScrollType.VERTICAL) ? page.selectedIndex : 0;
			// int lastIndex = Math.min(topIndex +Text.defaultHeight, page.itemCount());

			// Iterator<Text> itr = page.items().subList(topIndex, lastIndex).iterator();

			Iterator<Text> itr = page.items().iterator();

			int line = 0;
			int left = 0;

			while (itr.hasNext())
			{
				Text item = itr.next();
				int lineHeight = item.getLineHeight();
				int maxLine = book.height /item.getLineHeight();

				if (maxLine < line) break;

				int top = (line +1) *lineHeight;

				if (item.hasIcon())
				{
					g.translate(left, top);
					ViewConsts.vcTexture.paint(g, item.icon, assets, frame);
					g.translate(-left, -top);

					left += item.icon.getWidth();
				}

				if (item.hasBackground())
				{
					g.translate(left, top);
					ViewConsts.vcTexture.paint(g, item.background, assets, frame);
					g.translate(-left, -top);
				}

				g.setColor(item.color);
				g.setFont(GameFont.textFont);
				if (item.bold) g.setFont(GameFont.textFontBold);

				String text = ViewUtils.wordWrap(item.text, g, left, book.innerWidth());

				// System.out.println(text);

				String[] lines = text.split("\n");
				int yLen = lines.length;

				for (int i=0; i<yLen; i++)
				{
					if (0 < i)
					{
						top += lineHeight;
						left = 0;
					}

					g.drawString(lines[i], left, top);
					if (item.underline) g.drawLine(left, top, g.getFontMetrics().stringWidth(lines[i]), top);
				}

				if (selected == item) // selected instanceof Choice
				{
					g.setComposite(ViewConsts.halfBlend);
					g.setColor(Color.white);

					int itTop = line *lineHeight +3;
					g.fillRect(left, itTop, book.width -lineHeight*2 -left, lineHeight *yLen);

					g.setComposite(AlphaComposite.SrcOver);
					g.setColor(item.color);

					if (item instanceof IEditableText) //  && 0 <= frame && frame <= 10)
					{
						int cridx = ((IEditableText) item).getCaretIndex();

						int crleft = left +g.getFontMetrics().stringWidth(item.text.substring(0, cridx));
						g.drawLine(crleft, itTop, crleft, itTop +lineHeight);
					}

				}

				line += yLen -1;
				left += g.getFontMetrics().stringWidth(lines[yLen -1]);


				if (item.lineBreak)
				{
					line ++;
					left = 0;
				}

			} // end of loop






		}

		g.translate(-Text.defaultHeight, -Text.defaultHeight);

	}


}
