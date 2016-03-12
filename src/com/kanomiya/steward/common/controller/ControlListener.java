package com.kanomiya.steward.common.controller;

import java.awt.Frame;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.kanomiya.steward.common.Game;
import com.kanomiya.steward.common.model.area.Area;
import com.kanomiya.steward.common.model.area.Tip;
import com.kanomiya.steward.common.model.assets.AssetsUtils;
import com.kanomiya.steward.common.model.event.PlayerMode;
import com.kanomiya.steward.common.view.ViewConsts;
import com.kanomiya.steward.editor.FrameTip;


/**
 * @author Kanomiya
 *
 */
public class ControlListener implements KeyListener, MouseListener, MouseMotionListener {

	public Game game;
	public Frame gameFrame;
	public Insets frameInsets;
	public FrameTip frameTip;


	public void changeMode(PlayerMode mode)
	{
		if (frameTip != null)
		{
			frameTip.dispose();
			frameTip = null;
		}

		if (mode == PlayerMode.WIZARD)
		{
			frameTip = new FrameTip(game);
			frameTip.setLocation(gameFrame.getX() +gameFrame.getWidth(), gameFrame.getY());

			frameTip.setAutoRequestFocus(false); // 自動アクティブ無効化
			frameTip.setVisible(true);

		}

		game.thePlayer.mode = mode;
	}


	/**
	* @inheritDoc
	*/
	@Override
	public void keyPressed(KeyEvent e)
	{
		int keyCode = e.getKeyCode();

		// TODO: for Debug
		// System.out.println("key: " + e.getKeyCode() + " char: " + e.getKeyChar());

		if (keyCode == KeyEvent.VK_COLON && e.isShiftDown()) keyCode = KeyEvent.VK_MULTIPLY;

		boolean turnFlag = false;


		if (e.isControlDown() && keyCode == KeyEvent.VK_S)
		{
			if (game.thePlayer.mode.enableSave())
			{
				AssetsUtils.saveAssets(game.assets, (game.thePlayer.mode == PlayerMode.WIZARD) ? game.assets.getAssetsDir() : game.assets.getSaveDir());
			}
		}



		switch (keyCode)
		{
		case KeyEvent.VK_NUMPAD8:
		case KeyEvent.VK_UP:
			turnFlag = game.thePlayer.move(game.assets, 0, -1);
			break;

		case KeyEvent.VK_NUMPAD2:
		case KeyEvent.VK_DOWN:
			turnFlag = game.thePlayer.move(game.assets, 0, 1);
			break;

		case KeyEvent.VK_NUMPAD4:
		case KeyEvent.VK_LEFT:
			turnFlag = game.thePlayer.move(game.assets, -1, 0);
			break;

		case KeyEvent.VK_NUMPAD6:
		case KeyEvent.VK_RIGHT:
			turnFlag = game.thePlayer.move(game.assets, 1, 0);
			break;

		case KeyEvent.VK_NUMPAD7:
		case KeyEvent.VK_HOME:
			turnFlag = game.thePlayer.move(game.assets, -1, -1);
			break;

		case KeyEvent.VK_NUMPAD9:
		case KeyEvent.VK_PAGE_UP:
			turnFlag = game.thePlayer.move(game.assets, 1, -1);
			break;

		case KeyEvent.VK_NUMPAD1:
		case KeyEvent.VK_END:
			turnFlag = game.thePlayer.move(game.assets, -1, 1);
			break;

		case KeyEvent.VK_NUMPAD3:
		case KeyEvent.VK_PAGE_DOWN:
			turnFlag = game.thePlayer.move(game.assets, 1, 1);
			break;

		case KeyEvent.VK_NUMPAD5:
		case KeyEvent.VK_CLEAR:
			turnFlag = true;
			break;

		case KeyEvent.VK_F1: // help
			break;

		case KeyEvent.VK_MULTIPLY: // select
			changeMode((game.thePlayer.mode == PlayerMode.SELECT) ? PlayerMode.NORMAL : PlayerMode.SELECT);
			break;

		case KeyEvent.VK_DIVIDE: // log
		case KeyEvent.VK_SLASH: // log
			game.thePlayer.logger.setVisible(! game.thePlayer.logger.isVisible());
			break;

		case KeyEvent.VK_F12: // debug
			changeMode((game.thePlayer.mode == PlayerMode.WIZARD) ? PlayerMode.NORMAL : PlayerMode.WIZARD);

			break;
		}


		if (turnFlag && game.thePlayer.mode.enableTurn()) game.turn();

	}

	/**
	* @inheritDoc
	*/
	@Override
	public void keyReleased(KeyEvent e)
	{

	}

	/**
	* @inheritDoc
	*/
	@Override
	public void mousePressed(MouseEvent e)
	{

		selectOnMouseEvent(e);
		wizardOnMouseEvent(e);


	}


	public boolean selectOnMouseEvent(MouseEvent e)
	{
		if (game.thePlayer.mode.enableSelecter())
		{
			Area area = game.thePlayer.area;

			int x = -ViewConsts.getCamX(game.thePlayer.x, area.getWidth()) + (e.getX() -frameInsets.left) /ViewConsts.tileSize;
			int y = -ViewConsts.getCamY(game.thePlayer.y, area.getHeight()) + (e.getY() -frameInsets.top) /ViewConsts.tileSize;

			// TODO: for Debug
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
		if (game.thePlayer.mode == PlayerMode.WIZARD && frameTip != null)
		{
			Tip tip;
			int button = e.getButton();
			if (button == 0) button = MouseEvent.BUTTON1;

			switch (button)
			{
			case MouseEvent.BUTTON1: // 左クリック 設置
				tip = frameTip.getSelectedTip();
				int x = game.thePlayer.selectedX;
				int y = game.thePlayer.selectedY;

				game.thePlayer.area.setTip(tip, x, y);

				break;
			case MouseEvent.BUTTON3: // 右クリック スポイト

				tip = game.thePlayer.area.getTip(game.thePlayer.selectedX, game.thePlayer.selectedY);

				if (tip != null) frameTip.selectTip(tip);

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
	}


	/**
	* @inheritDoc
	*/
	@Override
	public void mouseMoved(MouseEvent e)
	{
		if (game.thePlayer.mode.enableSelecter())
		{
			Area area = game.thePlayer.area;

			int x = -ViewConsts.getCamX(game.thePlayer.x, area.getWidth()) + (e.getX() -frameInsets.left) /ViewConsts.tileSize;
			int y = -ViewConsts.getCamY(game.thePlayer.y, area.getHeight()) + (e.getY() -frameInsets.top) /ViewConsts.tileSize;

			if (area.inArea(x, y))
			{
				game.thePlayer.focusedX = x;
				game.thePlayer.focusedY = y;
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




}
