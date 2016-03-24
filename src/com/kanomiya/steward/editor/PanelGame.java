package com.kanomiya.steward.editor;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.kanomiya.steward.Game;

/**
 * @author Kanomiya
 *
 */
public class PanelGame extends JPanel {


	public PanelGame(Game game)
	{
		super();

		{
			JPanel p = new JPanel();
			p.setLayout(new FlowLayout());

			JTextField text = new JTextField(game.getName());
			text.setPreferredSize(new Dimension(150, 20));
			p.add(new JLabel(game.assets.translate(("vocabulary.name"))));
			p.add(text);
			add(p);
		}



	}

}
