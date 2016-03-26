package com.kanomiya.steward.editor;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Collator;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.tree.TreePath;

import com.google.common.collect.Lists;
import com.kanomiya.steward.Game;
import com.kanomiya.steward.model.area.AccessType;
import com.kanomiya.steward.model.area.Area;
import com.kanomiya.steward.model.assets.resource.type.ResourceType;
import com.kanomiya.steward.model.event.Event;
import com.kanomiya.steward.model.texture.Texture;

/**
 * @author Kanomiya
 *
 */
public class PanelEvent extends JPanel {

	public JTextField inputId;
	public JTextField inputName;
	public JLabel lblLocalizedName;
	public JComboBox inputArea;
	public JComboBox inputAccessType;
	public JTextField inputX, inputY;
	public JComboBox inputIcon;


	public PanelEvent(Game game, Event event, FrameEditor editor) // TODO area.eventList -- assets.event ???
	{
		super();

		setLayout(new GridLayout(14, 1));

		{
			JPanel p = new JPanel();

			JButton cTravel = new JButton(game.assets.translate("vocabulary.travel"));
			if (event.area == null) cTravel.setEnabled(false);
			cTravel.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e) {
					event.area.setEvent(game.assets.getPlayer(), false);
					game.assets.getPlayer().x = event.x;
					game.assets.getPlayer().y = event.y;
				}
			});
			p.add(cTravel);

			JButton cDel = new JButton(game.assets.translate("vocabulary.delete"));
			cDel.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e) {
					event.area.removeEvent(event);
					game.assets.registries.get(ResourceType.rtEvent).remove(event.getId());

					editor.refreshTree();

					TreePath eventPath = new TreePath(editor.nodeEvent.getPath());

					editor.tree.setSelectionPath(eventPath);
					editor.tree.expandPath(eventPath);
				}
			});
			p.add(cDel);

			JButton cReflect = new JButton(game.assets.translate("vocabulary.reflection"));
			cReflect.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e) {
					event.setId(inputId.getText());
					event.setUnlocalizedName(inputName.getText());
					lblLocalizedName.setText(game.assets.translate(event.getUnlocalizedName()));

					event.area = game.assets.getArea((String) inputArea.getSelectedItem());
					event.x = Integer.valueOf(inputX.getText());
					event.y = Integer.valueOf(inputY.getText());
					event.access = AccessType.getFromId((String) inputAccessType.getSelectedItem());

					if (0 <= inputIcon.getSelectedIndex())
					{
						if (inputIcon.getSelectedIndex() == 0) event.icon = null;
						else event.icon = game.assets.getTexture((String) inputIcon.getSelectedItem());
					}

					event.area.setEvent(event, false);

					editor.refreshTree();
					editor.tree.setSelectionPath(editor.getTreePathFromUserObject(editor.nodeEvent, event, false));
				}
			});
			p.add(cReflect);

			add(p);
		}

		{
			JPanel p = new JPanel();
			p.setLayout(new FlowLayout());

			inputId = new JTextField(event.getId());
			inputId.setPreferredSize(new Dimension(150, 20));
			p.add(new JLabel(game.assets.translate(("vocabulary.id"))));
			p.add(inputId);
			add(p);
		}

		{
			JPanel p = new JPanel();
			p.setLayout(new FlowLayout());

			inputName = new JTextField(event.getUnlocalizedName());
			inputName.setPreferredSize(new Dimension(150, 20));
			p.add(new JLabel(game.assets.translate(("vocabulary.name"))));
			p.add(inputName);
			lblLocalizedName = new JLabel(game.assets.translate((event.getUnlocalizedName())));
			p.add(lblLocalizedName);
			add(p);
		}

		{
			JPanel p = new JPanel();
			p.setLayout(new FlowLayout());

			{
				inputArea = new JComboBox();
				inputArea.setPreferredSize(new Dimension(180, 20));

				List<Area> list = Lists.newArrayList(game.assets.registries.get(ResourceType.rtArea).values().iterator());
				Collator collator = Collator.getInstance();
				collator.setStrength(Collator.PRIMARY);
				list.sort(new ResourceIdComparator(collator));

				Iterator<Area> itr = list.iterator();
				while (itr.hasNext())
				{
					inputArea.addItem(itr.next().getId());
				}

				if (event.area != null) inputArea.setSelectedItem(event.area.getId());

				p.add(new JLabel(game.assets.translate(("area"))));
				p.add(inputArea);
			}

			JPanel p2 = new JPanel();
			p2.setLayout(new FlowLayout());
			p2.add(new JLabel(game.assets.translate(("vocabulary.coordinate"))));

			{
				inputX = new JTextField("" +event.x);
				inputX.setPreferredSize(new Dimension(80, 20));
				inputX.addKeyListener(new IntKeyListener());
				p2.add(new JLabel(game.assets.translate(("vocabulary.x"))));
				p2.add(inputX);
			}

			{
				inputY = new JTextField("" +event.y);
				inputY.setPreferredSize(new Dimension(80, 20));
				inputY.addKeyListener(new IntKeyListener());
				p2.add(new JLabel(game.assets.translate(("vocabulary.y"))));
				p2.add(inputY);
			}

			add(p);
			add(p2);
		}


		{
			JPanel p = new JPanel();
			p.setLayout(new FlowLayout());

			inputAccessType = new JComboBox();
			inputAccessType.setPreferredSize(new Dimension(200, 20));

			List<AccessType> list = Arrays.asList(AccessType.values());
			Collator collator = Collator.getInstance();
			collator.setStrength(Collator.PRIMARY);
			list.sort(new ResourceIdComparator(collator));

			Iterator<AccessType> itr = list.iterator();
			while (itr.hasNext())
			{
				inputAccessType.addItem(itr.next().getId());
			}

			if (event.access != null) inputAccessType.setSelectedItem(event.access.getId());

			p.add(new JLabel(game.assets.translate(("accessType"))));
			p.add(inputAccessType);
			add(p);
		}

		{
			JPanel p = new JPanel();
			p.setLayout(new FlowLayout());

			inputIcon = new JComboBox();
			inputIcon.setPreferredSize(new Dimension(200, 20));

			inputIcon.addItem("(nothing)");

			List<Texture> list = Lists.newArrayList(game.assets.registries.get(ResourceType.rtTexture).values().iterator());
			Collator collator = Collator.getInstance();
			collator.setStrength(Collator.PRIMARY);
			list.sort(new ResourceIdComparator(collator));

			Iterator<Texture> itr = list.iterator();
			while (itr.hasNext())
			{
				inputIcon.addItem(itr.next().id);
			}

			if (event.icon != null) inputIcon.setSelectedItem(event.icon.id);

			p.add(new JLabel(game.assets.translate(("vocabulary.icon"))));
			p.add(inputIcon);
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
					editor.splitPane.setRightComponent(new PanelScriptOwner(game, event, editor));
				}
			});

			p.add(cScript);
			add(p);
		}


	}

}
