package com.kanomiya.steward.editor;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import com.kanomiya.steward.Game;
import com.kanomiya.steward.model.area.Area;
import com.kanomiya.steward.model.event.PlayerMode;

/**
 * @author Kanomiya
 *
 */
public class FrameEditor extends JFrame {

	public Game game;

	public Area[] array;

	public FrameEditor(Game game) {
		this.game = game;

		addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				PlayerMode.changeMode(game, game.assets.getPlayer(), PlayerMode.NORMAL);
				e.getWindow().setVisible(false);
			}
		});

		setLayout(null);
		pack();

		Collection<Area> list = game.assets.areaList();
		int count = list.size();
		array = list.toArray(new Area[count]);


		int cols = 8;

		Insets insets = getInsets();
		setSize(640, 480);
		setTitle("Editor");


		JSplitPane splitPane = new JSplitPane();

		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Game");
		DefaultMutableTreeNode childArea = new DefaultMutableTreeNode("Area");

		Iterator<Area> itr = list.iterator();

		while (itr.hasNext())
		{
			DefaultMutableTreeNode child = new DefaultMutableTreeNode(itr.next());

			childArea.add(child);
		}

		root.add(childArea);

		JTree tree = new JTree(root);
		tree.setRootVisible(true);

		tree.addTreeSelectionListener(new TreeSelectionListener()
		{
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				TreePath path = e.getPath();

				if (1 < path.getPathCount())
				{
					String type = (String) ((DefaultMutableTreeNode) path.getPathComponent(1)).getUserObject();

					switch (type)
					{
					case "Area":

						if (path.getPathCount() == 2)
						{
							JPanel p = new PanelArea(game);
							p.setPreferredSize(new Dimension(450, 480));
							splitPane.setRightComponent(p);

						} else if (2 < path.getPathCount())
						{
							Area area = (Area) ((DefaultMutableTreeNode) path.getPathComponent(2)).getUserObject();
							JPanel p = new PanelAreaEdit(game, area);
							p.setPreferredSize(new Dimension(450, 480));
							splitPane.setRightComponent(p);
						}


						break;
					}
				} else if (path.getPathCount() == 1)
				{
					splitPane.setRightComponent(new PanelGame(game));
				}
			}
		});

		tree.setMinimumSize(new Dimension(150, 480));
		splitPane.setLeftComponent(tree);
		splitPane.setRightComponent(new PanelGame(game));

		splitPane.setSize(640, 480);

		add(splitPane);

	}

}
