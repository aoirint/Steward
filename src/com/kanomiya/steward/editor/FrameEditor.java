package com.kanomiya.steward.editor;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
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

	protected JSplitPane splitPane;
	protected JTree tree;
	protected DefaultMutableTreeNode nodeRoot;
	protected DefaultMutableTreeNode nodeArea;


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


		setSize(640, 480);
		setTitle("Editor");

		splitPane = new JSplitPane();

		initNodeRoot(game);
		tree = new JTree(nodeRoot);

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

						if (path.getPathCount() == 2) setRightToAreaGroup();
						else if (2 < path.getPathCount())
						{
							Area area = (Area) ((DefaultMutableTreeNode) path.getPathComponent(2)).getUserObject();
							setRightToArea(area);
						}

						break;
					}
				} else if (path.getPathCount() == 1) setRightToGame();
			}
		});

		tree.setMinimumSize(new Dimension(150, 480));
		splitPane.setLeftComponent(tree);

		setRightToGame();

		splitPane.setSize(640, 480);

		add(splitPane);

	}


	public void refreshTree()
	{
		initNodeRoot(game);

		tree.setModel(new DefaultTreeModel(nodeRoot));
	}



	public TreePath getTreePathFromUserObject(DefaultMutableTreeNode node, Object obj, boolean exclusive)
	{
		if (obj == node.getUserObject()) return new TreePath(node.getPath());

		for (int i=0; i<node.getChildCount(); i++)
		{
			TreeNode cNode = node.getChildAt(i);
			if (cNode instanceof DefaultMutableTreeNode)
			{
				DefaultMutableTreeNode cNodeDMTN = (DefaultMutableTreeNode) cNode;
				if (cNodeDMTN.getUserObject() == obj) return new TreePath(cNodeDMTN.getPath());

				if (exclusive)
				{
					TreePath ex = getTreePathFromUserObject(cNodeDMTN, obj, true);
					if (ex != null) return ex;
				}
			}
		}

		return null;
	}

	protected void initNodeRoot(Game game)
	{
		nodeRoot = new DefaultMutableTreeNode("Game");
		initNodeArea(game);
	}

	protected void initNodeArea(Game game)
	{
		nodeArea = new DefaultMutableTreeNode("Area");

		Iterator<Area> itr = game.assets.areaList().iterator();

		while (itr.hasNext())
		{
			DefaultMutableTreeNode child = new DefaultMutableTreeNode(itr.next());

			nodeArea.add(child);
		}

		nodeRoot.add(nodeArea);
	}




	protected void setRightToGame()
	{
		JPanel p = new PanelGame(game);
		p.setPreferredSize(new Dimension(450, 480));
		splitPane.setRightComponent(p);
	}

	protected void setRightToAreaGroup()
	{
		JPanel p = new PanelAreaGroup(game, this);
		p.setPreferredSize(new Dimension(450, 480));
		splitPane.setRightComponent(p);
	}

	protected void setRightToArea(Area area)
	{
		JPanel p = new PanelArea(game, area, this);
		p.setPreferredSize(new Dimension(450, 480));
		splitPane.setRightComponent(p);
	}

}
