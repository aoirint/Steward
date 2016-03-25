package com.kanomiya.steward.editor;

import java.awt.TextComponent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class IntKeyListener implements KeyListener {
	public IntKeyListener() { }

	@Override
	public void keyTyped(KeyEvent e) {
		char c = e.getKeyChar();

		if (! ('0' <= c && c <= '9')) {
			if (e.getComponent() instanceof TextComponent) {
				TextComponent component = (TextComponent) e.getComponent();

				if (component.getText().length() == 1 && e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
					e.setKeyChar('0');
				}
			}

			e.consume();
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}


}
