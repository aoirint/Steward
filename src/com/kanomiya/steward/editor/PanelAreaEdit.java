package com.kanomiya.steward.editor;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.kanomiya.steward.Game;
import com.kanomiya.steward.model.area.Area;

/**
 * @author Kanomiya
 *
 */
public class PanelAreaEdit extends JPanel {


	public PanelAreaEdit(Game game, Area area)
	{
		super();

		setLayout(new GridLayout(14, 1));

		{
			JPanel p = new JPanel();

			JButton cTravel = new JButton(game.assets.translate("vocabulary.travel"));
			cTravel.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e) {
					area.setEvent(game.assets.getPlayer(), false);
				}
			});
			p.add(cTravel);
			add(p);
		}

		{
			JPanel p = new JPanel();
			p.setLayout(new FlowLayout());

			JTextField text = new JTextField(area.getName());
			text.setPreferredSize(new Dimension(150, 20));
			p.add(new JLabel(game.assets.translate(("vocabulary.name"))));
			p.add(text);
			p.add(new JLabel(game.assets.translate((area.getName()))));
			add(p);
		}

		{
			JPanel p = new JPanel();
			p.setLayout(new FlowLayout());

			p.add(new JLabel(game.assets.translate(("vocabulary.size"))));

			{
				JTextField text = new JTextField("" +area.getWidth());
				text.setPreferredSize(new Dimension(80, 20));
				p.add(new JLabel(game.assets.translate(("vocabulary.width"))));
				p.add(text);
			}

			{
				JTextField text = new JTextField("" +area.getHeight());
				text.setPreferredSize(new Dimension(80, 20));
				p.add(new JLabel(game.assets.translate(("vocabulary.height"))));
				p.add(text);
			}

			add(p);
		}


	}

}
