package com.kanomiya.steward.editor;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.tree.TreePath;

import com.kanomiya.steward.Game;
import com.kanomiya.steward.model.area.Area;
import com.kanomiya.steward.model.assets.resource.type.ResourceType;

/**
 * @author Kanomiya
 *
 */
public class PanelAreaGroup extends JPanel {


	public PanelAreaGroup(Game game, FrameEditor editor)
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
					Area area = new Area("new", game.assets);
					game.assets.registries.get(ResourceType.rtArea).put("new", area);
					editor.refreshTree();

					editor.tree.expandPath(new TreePath(editor.nodeArea));
					TreePath path = editor.getTreePathFromUserObject(editor.nodeArea, area, false);
					if (path != null) editor.tree.setSelectionPath(path);

				}
			});
			p.add(cCreate);
			add(p);
		}


	}

}
