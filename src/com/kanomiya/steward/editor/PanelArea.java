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

	public JTextField inputId;
	public JTextField inputName;
	public JLabel lblLocalizedName;
	public JTextField inputWidth, inputHeight;
	public JComboBox inputBackground;


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
					area.setId(inputId.getText());
					area.setName(inputName.getText());
					lblLocalizedName.setText(game.assets.translate(area.getName()));

					area.setSize(Integer.valueOf(inputWidth.getText()), Integer.valueOf(inputHeight.getText()));

					if (0 <= inputBackground.getSelectedIndex())
					{
						if (inputBackground.getSelectedIndex() == 0) area.setBackground(null);
						else area.setBackground(game.assets.getTexture((String) inputBackground.getSelectedItem()));
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

			inputId = new JTextField(area.getId());
			inputId.setPreferredSize(new Dimension(150, 20));
			p.add(new JLabel(game.assets.translate(("vocabulary.id"))));
			p.add(inputId);
			add(p);
		}

		{
			JPanel p = new JPanel();
			p.setLayout(new FlowLayout());

			inputName = new JTextField(area.getName());
			inputName.setPreferredSize(new Dimension(150, 20));
			p.add(new JLabel(game.assets.translate(("vocabulary.name"))));
			p.add(inputName);
			lblLocalizedName = new JLabel(game.assets.translate((area.getName())));
			p.add(lblLocalizedName);
			add(p);
		}

		{
			JPanel p = new JPanel();
			p.setLayout(new FlowLayout());

			p.add(new JLabel(game.assets.translate(("vocabulary.size"))));


			{
				inputWidth = new JTextField("" +area.getWidth());
				inputWidth.setPreferredSize(new Dimension(80, 20));
				inputWidth.addKeyListener(new IntKeyListener());
				p.add(new JLabel(game.assets.translate(("vocabulary.width"))));
				p.add(inputWidth);
			}

			{
				inputHeight = new JTextField("" +area.getHeight());
				inputHeight.setPreferredSize(new Dimension(80, 20));
				inputHeight.addKeyListener(new IntKeyListener());
				p.add(new JLabel(game.assets.translate(("vocabulary.height"))));
				p.add(inputHeight);
			}

			add(p);
		}


		{
			JPanel p = new JPanel();
			p.setLayout(new FlowLayout());

			inputBackground = new JComboBox();
			inputBackground.setPreferredSize(new Dimension(200, 20));

			inputBackground.addItem("(nothing)");

			List<Texture> list = Lists.newArrayList(game.assets.registries.get(ResourceType.rtTexture).values().iterator());
			Collator collator = Collator.getInstance();
			collator.setStrength(Collator.PRIMARY);
			list.sort(new ResourceIdComparator(collator));

			Iterator<Texture> itr = list.iterator();
			while (itr.hasNext())
			{
				inputBackground.addItem(itr.next().id);
			}

			if (area.hasBackground()) inputBackground.setSelectedItem(area.getBackground().id);

			p.add(new JLabel(game.assets.translate(("vocabulary.background"))));
			p.add(inputBackground);
			add(p);
		}

		{
			JPanel p = new JPanel();
			p.setLayout(new FlowLayout());

			JButton cScript = new JButton(game.assets.translate("vocabulary.script"));
			cScript.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e) {
					editor.splitPane.setRightComponent(new PanelScriptOwner(game, area, editor));
				}
			});

			p.add(cScript);
			add(p);
		}


	}

}
