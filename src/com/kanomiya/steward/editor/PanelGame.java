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
import com.kanomiya.steward.model.assets.AssetsUtils;
import com.kanomiya.steward.model.assets.GameInfo;

/**
 * @author Kanomiya
 *
 */
public class PanelGame extends JPanel {

	JTextField textName;

	public PanelGame(Game game)
	{
		super();

		setLayout(new GridLayout(14, 1));

		GameInfo gameInfo = game.assets.getGameInfo();

		{
			JPanel p = new JPanel();

			JButton cReflect = new JButton(game.assets.translate("vocabulary.reflection"));
			cReflect.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e) {
					gameInfo.setName(textName.getText());

				}
			});
			p.add(cReflect);

			JButton cSave = new JButton(game.assets.translate("vocabulary.save"));
			cSave.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e) {
					AssetsUtils.saveOriginalAssets(game.assets);
				}
			});
			p.add(cSave);

			add(p);
		}

		{
			JPanel p = new JPanel();
			p.setLayout(new FlowLayout());

			textName = new JTextField(gameInfo.getName());
			textName.setPreferredSize(new Dimension(150, 20));
			p.add(new JLabel(game.assets.translate(("vocabulary.name"))));
			p.add(textName);
			add(p);
		}



	}

}
