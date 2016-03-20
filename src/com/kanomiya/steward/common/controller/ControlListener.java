package com.kanomiya.steward.common.controller;

import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.kanomiya.steward.common.Game;
import com.kanomiya.steward.common.controller.unit.Keyboard;
import com.kanomiya.steward.common.controller.unit.Mouse;
import com.kanomiya.steward.common.controller.unit.event.KeyboardUpdateEvent;
import com.kanomiya.steward.common.controller.unit.event.MouseUpdateEvent;
import com.kanomiya.steward.common.controller.unit.identifier.Key;
import com.kanomiya.steward.common.controller.unit.identifier.MouseButton;
import com.kanomiya.steward.common.model.area.Area;
import com.kanomiya.steward.common.model.area.Tip;
import com.kanomiya.steward.common.model.event.PlayerMode;
import com.kanomiya.steward.common.view.ViewConsts;
import com.kanomiya.steward.common.view.ViewUtils;


/**
 * @author Kanomiya
 *
 */
public class ControlListener implements KeyListener, MouseListener, MouseMotionListener {

	protected Game game;

	public Keyboard keyboard;
	public Mouse mouse;

	public ControlListener(Game game)
	{
		this.game = game;

		keyboard = new Keyboard();
		mouse = new Mouse();
	}



	/**
	* @inheritDoc
	*/
	@Override
	public void keyPressed(KeyEvent e)
	{

		// DEBUG
		//System.out.println("key: " + e.getKeyCode() + " char: " + e.getKeyChar());

		Key key = keyEventToKey(e);
		keyboard.getKeyState(key).press();

		game.eventBus().post(new KeyboardUpdateEvent(keyboard, mouse, key));

	}

	/**
	* @inheritDoc
	*/
	@Override
	public void keyReleased(KeyEvent e)
	{
		Key key = keyEventToKey(e);
		keyboard.getKeyState(key).release();

		game.eventBus().post(new KeyboardUpdateEvent(keyboard, mouse, key));
	}

	/**
	* @inheritDoc
	*/
	@Override
	public void mousePressed(MouseEvent e)
	{
		boolean consumed = false;

		Insets frameInsets = game.getFrame().getInsets();

		int x = e.getX() -frameInsets.left;
		int y = e.getY() -frameInsets.top;

		MouseButton button = mouseEventToButton(e);

		mouse.getButtonState(button).press();
		mouse.getPointer().point(x, y);

		game.eventBus().post(new MouseUpdateEvent(keyboard, mouse, button));

		if (! consumed)
		{
			selectOnMouseEvent(e);
			wizardOnMouseEvent(e);
		}

	}


	public boolean selectOnMouseEvent(MouseEvent e)
	{
		if (game.thePlayer.mode.enableSelecter())
		{
			Area area = game.thePlayer.area;

			Insets frameInsets = game.getFrame().getInsets();

			int x = -ViewUtils.getCamX(game.thePlayer.x, area.getWidth()) + (e.getX() -frameInsets.left) /ViewConsts.tileSize;
			int y = -ViewUtils.getCamY(game.thePlayer.y, area.getHeight()) + (e.getY() -frameInsets.top) /ViewConsts.tileSize;

			// DEBUG
			// System.out.println("(" + x +"," + y + ")");

			if (area.inArea(x, y))
			{
				game.thePlayer.selectedX = x;
				game.thePlayer.selectedY = y;
			}

		}

		return false;
	}

	public boolean wizardOnMouseEvent(MouseEvent e)
	{
		if (game.thePlayer.mode == PlayerMode.WIZARD && game.frameTip != null)
		{
			Tip tip;
			int button = e.getButton();
			if (button == 0) button = MouseEvent.BUTTON1;

			switch (button)
			{
			case MouseEvent.BUTTON1: // 左クリック 設置
				tip = game.frameTip.getSelectedTip();
				int x = game.thePlayer.selectedX;
				int y = game.thePlayer.selectedY;

				game.thePlayer.area.setTip(tip, x, y);

				break;
			case MouseEvent.BUTTON3: // 右クリック スポイト

				tip = game.thePlayer.area.getTip(game.thePlayer.selectedX, game.thePlayer.selectedY);

				if (tip != null) game.frameTip.selectTip(tip);

				break;
			case MouseEvent.BUTTON2:
				// TODO: softTouch edit Event

				break;
			}

		}

		return false;
	}

	/**
	* @inheritDoc
	*/
	@Override
	public void mouseReleased(MouseEvent e)
	{
		Insets frameInsets = game.getFrame().getInsets();

		int x = e.getX() -frameInsets.left;
		int y = e.getY() -frameInsets.top;

		MouseButton button = mouseEventToButton(e);

		mouse.getButtonState(button).release();
		mouse.getPointer().point(x, y);

		game.eventBus().post(new MouseUpdateEvent(keyboard, mouse, button));
	}


	/**
	* @inheritDoc
	*/
	@Override
	public void mouseMoved(MouseEvent e)
	{
		Insets frameInsets = game.getFrame().getInsets();

		int x = e.getX() -frameInsets.left;
		int y = e.getY() -frameInsets.top;

		mouse.getPointer().point(x, y);

		game.eventBus().post(new MouseUpdateEvent(keyboard, mouse, MouseButton.POINTER));


		if (game.thePlayer.mode.enableSelecter())
		{
			Area area = game.thePlayer.area;

			int tileX = -ViewUtils.getCamX(game.thePlayer.x, area.getWidth()) + x /ViewConsts.tileSize;
			int tileY = -ViewUtils.getCamY(game.thePlayer.y, area.getHeight()) + y /ViewConsts.tileSize;

			if (area.inArea(tileX, tileY))
			{
				game.thePlayer.focusedX = tileX;
				game.thePlayer.focusedY = tileY;
			}

		}

	}


	/**
	* @inheritDoc
	*/
	@Override
	public void mouseDragged(MouseEvent e)
	{
		selectOnMouseEvent(e);
		wizardOnMouseEvent(e);
	}




	/**
	* @inheritDoc
	*/
	@Override
	public void keyTyped(KeyEvent e) {  }

	/**
	* @inheritDoc
	*/
	@Override
	public void mouseClicked(MouseEvent e) {  }

	/**
	* @inheritDoc
	*/
	@Override
	public void mouseEntered(MouseEvent e) {  }

	/**
	* @inheritDoc
	*/
	@Override
	public void mouseExited(MouseEvent e) {  }





	protected Key keyEventToKey(KeyEvent e) // TODO: 未実装キー
	{

		switch (e.getKeyCode())
		{
		case KeyEvent.VK_SHIFT: return Key.SHIFT;
		case KeyEvent.VK_ALT: return Key.ALT;
		case KeyEvent.VK_CONTROL: return Key.CONTROL;
		}

		boolean shift = e.isShiftDown();
		boolean alt = e.isAltDown();
		boolean ctrl = e.isControlDown();

		/*
		 *  Missing keycodes
		 *
		 * ? % |
		 *
		 * and shifted-input
		 *
		 */

		char ch = e.getKeyChar();

		switch (ch)
		{
		case '?': return Key.QUESTION;
		case '%': return Key.PERCENT;
		case '|': return Key.VERTICAL_LINE;
		}


		switch (e.getKeyCode())
		{
		// アルファベット
		case KeyEvent.VK_A: return (shift) ? Key.A : Key.a;
		case KeyEvent.VK_B: return (shift) ? Key.B : Key.b;
		case KeyEvent.VK_C: return (shift) ? Key.C : Key.c;
		case KeyEvent.VK_D: return (shift) ? Key.D : Key.d;
		case KeyEvent.VK_E: return (shift) ? Key.E : Key.e;
		case KeyEvent.VK_F: return (shift) ? Key.F : Key.f;
		case KeyEvent.VK_G: return (shift) ? Key.G : Key.g;
		case KeyEvent.VK_H: return (shift) ? Key.H : Key.h;
		case KeyEvent.VK_I: return (shift) ? Key.I : Key.i;
		case KeyEvent.VK_J: return (shift) ? Key.J : Key.j;
		case KeyEvent.VK_K: return (shift) ? Key.K : Key.k;
		case KeyEvent.VK_L: return (shift) ? Key.L : Key.l;
		case KeyEvent.VK_M: return (shift) ? Key.M : Key.m;
		case KeyEvent.VK_N: return (shift) ? Key.N : Key.n;
		case KeyEvent.VK_O: return (shift) ? Key.O : Key.o;
		case KeyEvent.VK_P: return (shift) ? Key.P : Key.p;
		case KeyEvent.VK_Q: return (shift) ? Key.Q : Key.q;
		case KeyEvent.VK_R: return (shift) ? Key.R : Key.r;
		case KeyEvent.VK_S: return (shift) ? Key.S : Key.s;
		case KeyEvent.VK_T: return (shift) ? Key.T : Key.t;
		case KeyEvent.VK_U: return (shift) ? Key.U : Key.u;
		case KeyEvent.VK_V: return (shift) ? Key.V : Key.v;
		case KeyEvent.VK_W: return (shift) ? Key.W : Key.w;
		case KeyEvent.VK_X: return (shift) ? Key.X : Key.x;
		case KeyEvent.VK_Y: return (shift) ? Key.Y : Key.y;
		case KeyEvent.VK_Z: return (shift) ? Key.Z : Key.z;

		// 数字
		case KeyEvent.VK_1: return Key.NUM1;
		case KeyEvent.VK_2: return Key.NUM2;
		case KeyEvent.VK_3: return Key.NUM3;
		case KeyEvent.VK_4: return Key.NUM4;
		case KeyEvent.VK_5: return Key.NUM5;
		case KeyEvent.VK_6: return Key.NUM6;
		case KeyEvent.VK_7: return Key.NUM7;
		case KeyEvent.VK_8: return Key.NUM8;
		case KeyEvent.VK_9: return Key.NUM9;
		case KeyEvent.VK_0: return Key.NUM0;

		// ファンクションキー
		case KeyEvent.VK_F1: return Key.F1;
		case KeyEvent.VK_F2: return Key.F2;
		case KeyEvent.VK_F3: return Key.F3;
		case KeyEvent.VK_F4: return Key.F4;
		case KeyEvent.VK_F5: return Key.F5;
		case KeyEvent.VK_F6: return Key.F6;
		case KeyEvent.VK_F7: return Key.F7;
		case KeyEvent.VK_F8: return Key.F8;
		case KeyEvent.VK_F9: return Key.F9;
		case KeyEvent.VK_F10: return Key.F10;
		case KeyEvent.VK_F11: return Key.F11;
		case KeyEvent.VK_F12: return Key.F12;

		// 記号
		case KeyEvent.VK_COLON: if (! shift) return Key.COLON; // SHIFTならAsterisk
		case KeyEvent.VK_ASTERISK: return Key.ASTERISK;
		case KeyEvent.VK_SEMICOLON: if (! shift) return Key.SEMICOLON; // SHIFTならPlus
		case KeyEvent.VK_PLUS: return Key.PLUS;
		case KeyEvent.VK_MINUS: return Key.MINUS;
		case KeyEvent.VK_SLASH: return Key.SLASH;

		case KeyEvent.VK_PERIOD: return Key.PERIOD;
		case KeyEvent.VK_COMMA: return Key.COMMA;
		case KeyEvent.VK_EXCLAMATION_MARK: return Key.EXCLAMATION;
		case KeyEvent.VK_AMPERSAND: return Key.AMPERSAND;

		case KeyEvent.VK_NUMBER_SIGN: return Key.NUMBER_SIGN;
		case KeyEvent.VK_DOLLAR: return Key.DOLLAR;

		case KeyEvent.VK_AT: return Key.AT;
		case KeyEvent.VK_BACK_SLASH: return Key.BACK_SLASH;
		case KeyEvent.VK_CIRCUMFLEX: return Key.CIRCUMFLEX;
		case KeyEvent.VK_UNDERSCORE: return Key.UNDER_SCORE;

		case KeyEvent.VK_OPEN_BRACKET: return Key.OPEN_BRACKET;
		case KeyEvent.VK_CLOSE_BRACKET: return Key.OPEN_BRACKET;
		case KeyEvent.VK_BRACELEFT: return Key.OPEN_BRACE;
		case KeyEvent.VK_BRACERIGHT: return Key.CLOSE_BRACE;
		case KeyEvent.VK_LEFT_PARENTHESIS: return Key.OPEN_PARENTHESIS;
		case KeyEvent.VK_RIGHT_PARENTHESIS: return Key.CLOSE_PARENHESIS;

		case KeyEvent.VK_QUOTE: return Key.QUOTE;
		case KeyEvent.VK_BACK_QUOTE: return Key.BACK_QUOTE;
		case KeyEvent.VK_QUOTEDBL: return Key.DOUBLE_QUOTE;


		// 文字制御
		case KeyEvent.VK_BACK_SPACE: return Key.BACK_SPACE;
		case KeyEvent.VK_DELETE: return Key.DELETE;
		case KeyEvent.VK_ENTER: return Key.ENTER;

		// ページ制御
		case KeyEvent.VK_HOME: return Key.HOME;
		case KeyEvent.VK_END: return Key.END;
		case KeyEvent.VK_PAGE_UP: return Key.PAGE_UP;
		case KeyEvent.VK_PAGE_DOWN: return Key.PAGE_DOWN;

		case KeyEvent.VK_UP: return Key.UP;
		case KeyEvent.VK_DOWN: return Key.DOWN;
		case KeyEvent.VK_LEFT: return Key.LEFT;
		case KeyEvent.VK_RIGHT: return Key.RIGHT;

		case KeyEvent.VK_SCROLL_LOCK: return Key.SCROLL_LOCK;

		// システム制御
		case KeyEvent.VK_ESCAPE: return Key.ESCAPE;
		// case KeyEvent.VK_: return Key.SYSTEMREQUEST;
		case KeyEvent.VK_PRINTSCREEN: return Key.PRINTSCREEN;
		// case KeyEvent.VK_: return Key.BREAK;
		case KeyEvent.VK_PAUSE: return Key.PAUSE;
		case KeyEvent.VK_CLEAR: return Key.CLEAR;


		// 入力制御
		case KeyEvent.VK_NUM_LOCK: return Key.NUM_LOCK;
		case KeyEvent.VK_CAPS_LOCK: return Key.CAPS_LOCK;
		case KeyEvent.VK_INSERT: return Key.INSERT;


		}

		return Key.charToKey(ch);
	}



	protected MouseButton mouseEventToButton(MouseEvent e)
	{

		switch (e.getButton())
		{
		case MouseEvent.BUTTON1: return MouseButton.LEFT;
		case MouseEvent.BUTTON2: return MouseButton.CENTER;
		case MouseEvent.BUTTON3: return MouseButton.RIGHT;
		}

		return MouseButton.UNKNOWN;
	}











}
