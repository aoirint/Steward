package com.kanomiya.steward.common.view;

import java.awt.Graphics;

import com.kanomiya.steward.common.model.event.Event;

/**
 * @author Kanomiya
 *
 */
public class ViewUtils {

	public static int getCamX(Event eye) {
		return getCamX(eye.x, eye.area.getWidth());
	}

	public static int getCamY(Event eye) {
		return getCamY(eye.y, eye.area.getHeight());
	}

	public static int getCamX(int x, int width) {
		int camX = x -ViewConsts.tileCols /2;
		if (camX < 0) return 0;

		int r = width -ViewConsts.tileCols;
		if (r < camX) return -r;

		return -camX;
	}

	public static int getCamY(int y, int height) {
		int camY = y -ViewConsts.tileRows /2;
		if (camY < 0) return 0;

		int b = height -ViewConsts.tileRows;
		if (b < camY) return -b;

		return -camY;

	}






	public static int xCenterInWindow(Event eye) {
		return xCenterInWindow(getCamX(eye));
	}

	public static int xCenterInWindow(int camX) {
		return camX + ViewConsts.tileCols /2;
	}

	public static int yCenterInWindow(Event eye) {
		return yCenterInWindow(getCamY(eye));
	}

	public static int yCenterInWindow(int camY) {
		return camY + ViewConsts.tileRows /2;
	}







	public static boolean xInWindow(int x, Event eye) {
		return xInWindow(x, getCamX(eye));
	}

	public static boolean xInWindow(int x, int camX) {
		return xInWindowEdge(x, camX, 0);
	}

	public static boolean xInWindowEdge(int x, Event eye, int distance) {
		return xInWindowEdge(x, getCamX(eye), distance);
	}

	public static boolean xInWindowEdge(int x, int camX, int distance) {
		return (leftInWindowEdge(x, camX, distance) && rightInWindowEdge(x, camX, distance));
	}

	public static boolean leftInWindowEdge(int x, Event eye, int distance) {
		return leftInWindowEdge(x, getCamX(eye), distance);
	}

	public static boolean leftInWindowEdge(int x, int camX, int distance) {
		return (camX +distance <= x);
	}

	public static boolean rightInWindowEdge(int x, Event eye,
			int distance) {
				return rightInWindowEdge(x, getCamX(eye), distance);
			}

	public static boolean rightInWindowEdge(int x, int camX,
			int distance) {
				return (x < camX +ViewConsts.tileCols -distance);
			}





	public static boolean yInWindow(int y, Event eye) {
		return yInWindow(y, getCamY(eye));
	}

	public static boolean yInWindow(int y, int camY) {
		return yInWindowEdge(y, camY, 0);
	}

	public static boolean yInWindowEdge(int y, Event eye, int distance) {
		return yInWindowEdge(y, getCamY(eye), distance);
	}

	public static boolean yInWindowEdge(int y, int camY, int distance) {
		return (topInWindowEdge(y, camY, distance) && bottomInWindowEdge(y, camY, distance));
	}

	public static boolean topInWindowEdge(int y, Event eye, int distance) {
		return topInWindowEdge(y, getCamY(eye), distance);
	}

	public static boolean topInWindowEdge(int y, int camY, int distance) {
		return (camY +distance <= y);
	}

	public static boolean bottomInWindowEdge(int y, Event eye,
			int distance) {
				return bottomInWindowEdge(y, getCamY(eye), distance);
			}

	public static boolean bottomInWindowEdge(int y, int camY,
			int distance) {
				return (y < camY +ViewConsts.tileRows -distance);
			}



	/**
	 *
	 * 折り返すべき場所に改行(\n)を挿入します
	 *
	 * @param text
	 * @param g
	 * @param beginLeft
	 * @param maxWidth
	 * @return
	 */
	public static String wordWrap(String text, Graphics g, int beginLeft,
			int maxWidth) {
				int textLen = text.length();
				int width = g.getFontMetrics().stringWidth(text);
				int right = beginLeft +width;


				if (maxWidth < right) // 折り返し
				{
					StringBuilder stack = new StringBuilder(textLen +20);
					StringBuilder builder = null;

					int dx = beginLeft;

					for (int i=0; i<textLen; i++)
					{
						if (builder == null) builder = new StringBuilder(textLen);

						builder.append(text.charAt(i));

						int dw = g.getFontMetrics().stringWidth(builder.toString());
						int dr = dx +dw;

						if (maxWidth -20 < dr)
						{
							stack.append(builder);
							stack.append('\n');
							builder = null;
						}

					}

					if (builder != null) stack.append(builder);

					text = stack.toString();
				}

				return text;
			}




}
