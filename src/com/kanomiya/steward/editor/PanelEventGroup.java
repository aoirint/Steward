package com.kanomiya.steward.editor;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.tree.TreePath;

import com.kanomiya.steward.Game;
import com.kanomiya.steward.model.assets.resource.type.ResourceType;
import com.kanomiya.steward.model.event.Event;

/**
 * @author Kanomiya
 *
 */
public class PanelEventGroup extends JPanel {


	public PanelEventGroup(Game game, FrameEditor editor)
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
					Event event = new Event("new", game.assets);
					game.assets.registries.get(ResourceType.rtEvent).put("new", event);
					editor.refreshTree();

					editor.tree.expandPath(new TreePath(editor.nodeEvent));
					TreePath path = editor.getTreePathFromUserObject(editor.nodeEvent, event, false);
					if (path != null) editor.tree.setSelectionPath(path);

				}
			});
			p.add(cCreate);
			add(p);
		}


	}

}
