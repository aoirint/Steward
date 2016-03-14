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
import com.kanomiya.steward.common.model.event.PlayerMode;
import com.kanomiya.steward.common.model.overlay.GameColor;
import com.kanomiya.steward.common.model.overlay.Text;
import com.kanomiya.steward.common.view.ViewConsts;
import com.kanomiya.steward.common.view.ViewUtils;
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

		game.thePlayer.focusedX = game.thePlayer.selectedX = game.thePlayer.x;
		game.thePlayer.focusedY = game.thePlayer.selectedY = game.thePlayer.y;

		game.thePlayer.mode = mode;
		game.thePlayer.logger.println(
				Text.create(game.assets.translate("playerMode.change")
						+ " " + game.assets.translate(mode.getUnlocalizedName()))
						.color(GameColor.orange));

	}


	/**
	* @inheritDoc
	*/
	@Override
	public void keyPressed(KeyEvent e)
	{

		// DEBUG
		// System.out.println("key: " + e.getKeyCode() + " char: " + e.getKeyChar());


		boolean consumed = false;

		consumed = ControlConsts.cPlayer.input(e, this, game.thePlayer, game.assets);

		if (consumed && game.thePlayer.mode.enableTurn()) game.turn();

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
		boolean consumed = false;

		int x = e.getX() -frameInsets.left;
		int y = e.getY() -frameInsets.top;

		ControlConsts.cPlayer.click(e, x, y, this, game.thePlayer, game.assets);

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

			int x = -ViewUtils.getCamX(game.thePlayer.x, area.getWidth()) + (e.getX() -frameInsets.left) /ViewConsts.tileSize;
			int y = -ViewUtils.getCamY(game.thePlayer.y, area.getHeight()) + (e.getY() -frameInsets.top) /ViewConsts.tileSize;

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
