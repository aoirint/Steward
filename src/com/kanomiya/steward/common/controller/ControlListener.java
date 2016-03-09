package com.kanomiya.steward.common.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.kanomiya.steward.common.Game;


/**
 * @author Kanomiya
 *
 */
public class ControlListener implements KeyListener, MouseListener, MouseMotionListener {

	public Game game;

	/**
	* @inheritDoc
	*/
	@Override
	public void keyPressed(KeyEvent e)
	{
		// TODO:

		// System.out.println("key: " + e.getKeyCode() + " char: " + e.getKeyChar());

		boolean turnFlag = false;

		switch (e.getKeyCode())
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
		}

		if (turnFlag) game.turn();

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
	public void mouseDragged(MouseEvent e) {  }

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
