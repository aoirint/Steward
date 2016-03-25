package com.kanomiya.steward.editor;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Collator;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.google.common.collect.Lists;
import com.kanomiya.steward.Game;
import com.kanomiya.steward.model.area.Area;
import com.kanomiya.steward.model.assets.resource.type.ResourceType;
import com.kanomiya.steward.model.texture.Texture;

/**
 * @author Kanomiya
 *
 */
public class PanelArea extends JPanel {

	public JTextField textId;
	public JTextField textName;
	public JLabel lblLocalizedName;
	public JTextField textWidth, textHeight;
	public JComboBox textBackground;


	public PanelArea(Game game, Area area, FrameEditor editor)
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

			JButton cReflect = new JButton(game.assets.translate("vocabulary.reflection"));
			cReflect.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e) {
					area.setId(textId.getText());
					area.setName(textName.getText());
					lblLocalizedName.setText(game.assets.translate(area.getName()));

					area.setSize(Integer.valueOf(textWidth.getText()), Integer.valueOf(textHeight.getText()));

					if (0 <= textBackground.getSelectedIndex())
					{
						if (textBackground.getSelectedIndex() == 0) area.setBackground(null);
						else area.setBackground(game.assets.getTexture((String) textBackground.getSelectedItem()));
					}

					editor.refreshTree();
					editor.tree.setSelectionPath(editor.getTreePathFromUserObject(editor.nodeArea, area, false));
				}
			});
			p.add(cReflect);

			add(p);
		}

		{
			JPanel p = new JPanel();
			p.setLayout(new FlowLayout());

			textId = new JTextField(area.getId());
			textId.setPreferredSize(new Dimension(150, 20));
			p.add(new JLabel(game.assets.translate(("vocabulary.id"))));
			p.add(textId);
			add(p);
		}

		{
			JPanel p = new JPanel();
			p.setLayout(new FlowLayout());

			textName = new JTextField(area.getName());
			textName.setPreferredSize(new Dimension(150, 20));
			p.add(new JLabel(game.assets.translate(("vocabulary.name"))));
			p.add(textName);
			lblLocalizedName = new JLabel(game.assets.translate((area.getName())));
			p.add(lblLocalizedName);
			add(p);
		}

		{
			JPanel p = new JPanel();
			p.setLayout(new FlowLayout());

			p.add(new JLabel(game.assets.translate(("vocabulary.size"))));


			{
				textWidth = new JTextField("" +area.getWidth());
				textWidth.setPreferredSize(new Dimension(80, 20));
				textWidth.addKeyListener(new IntKeyListener());
				p.add(new JLabel(game.assets.translate(("vocabulary.width"))));
				p.add(textWidth);
			}

			{
				textHeight = new JTextField("" +area.getHeight());
				textHeight.setPreferredSize(new Dimension(80, 20));
				textHeight.addKeyListener(new IntKeyListener());
				p.add(new JLabel(game.assets.translate(("vocabulary.height"))));
				p.add(textHeight);
			}

			add(p);
		}


		{
			JPanel p = new JPanel();
			p.setLayout(new FlowLayout());

			textBackground = new JComboBox();
			textBackground.setPreferredSize(new Dimension(150, 20));

			textBackground.addItem("(nothing)");

			List<Texture> list = Lists.newArrayList(game.assets.registries.get(ResourceType.rtTexture).values().iterator());
			Collator collator = Collator.getInstance();
			collator.setStrength(Collator.PRIMARY);
			list.sort(new ResourceIdComparator(collator));

			Iterator<Texture> itr = list.iterator();
			while (itr.hasNext())
			{
				textBackground.addItem(itr.next().id);
			}

			if (area.hasBackground()) textBackground.setSelectedItem(area.getBackground().id);

			p.add(new JLabel(game.assets.translate(("vocabulary.background"))));
			p.add(textBackground);
			add(p);
		}


	}

}
