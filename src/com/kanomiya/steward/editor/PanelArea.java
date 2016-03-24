package com.kanomiya.steward.editor;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.kanomiya.steward.Game;

/**
 * @author Kanomiya
 *
 */
public class PanelArea extends JPanel {


	public PanelArea(Game game)
	{
		super();

		setLayout(new GridLayout(14, 1));

		{
			JPanel p = new JPanel();

			JButton cCreate = new JButton(game.assets.translate("vocabulary.createNew"));
			cCreate.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e) {

				}
			});
			p.add(cCreate);
			add(p);
		}


	}

}
