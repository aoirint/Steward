package com.kanomiya.steward.editor;

import java.awt.Insets;
import java.awt.Scrollbar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collection;

import com.kanomiya.steward.Game;
import com.kanomiya.steward.model.area.Tip;
import com.kanomiya.steward.model.event.PlayerMode;
import com.kanomiya.steward.util.awt.FrameWithView;
import com.kanomiya.steward.view.ViewConsts;

/**
 * @author Kanomiya
 *
 */
public class FrameTip extends FrameWithView<Game> {

	public Game game;

	public static int tipCols = 8;
	public static int tipRows = 16;

	public Tip[] tips;
	public int tipSelectedX, tipSelectedY;

	public FrameTip(Game game) {
		super(ViewConsts.viewTips, game);

		this.game = game;

		int w = tipCols *ViewConsts.tileSize;
		int h = tipRows *ViewConsts.tileSize;

		ViewConsts.viewTips.frameTip = this;
		ViewConsts.viewTips.setSize(w, h);

		addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				PlayerMode.changeMode(game, game.thePlayer, PlayerMode.NORMAL);
				e.getWindow().setVisible(false);
			}
		});

		setLayout(null);
		pack();
		setRealSize(w +16, h);

		Insets insets = getInsets();

		Scrollbar vscroll = new Scrollbar(Scrollbar.VERTICAL);

		Collection<Tip> tipList = game.assets.tipList();
		int tipCount = tipList.size();
		tips = tipList.toArray(new Tip[tipCount]);


		vscroll.setValue(0);
		vscroll.setMinimum(0);
		vscroll.setMaximum(tipCount / tipCols +1);
		vscroll.setBounds(insets.left + w, insets.top, 16, h);
		add(vscroll);

		addMouseListener(new MouseAdapter()
		{
			@Override
			public void mousePressed(MouseEvent e) {
				Insets insets = getInsets();
				int x = (e.getX() -insets.left) /ViewConsts.tileSize;
				int y = (e.getY() -insets.top) /ViewConsts.tileSize;


				if (x < 0 || tipCols <= x) return ;
				if (y < 0 || tipRows <= x) return ;

				y += vscroll.getValue();

				int tipNum = getTipIndex(x, y);

				if (tips.length <= tipNum) return ;
				// DEBUG
				System.out.println("(" + x +"," + y + ")");

				tipSelectedX = x;
				tipSelectedY = y;

				repaint();
			}

		});

	}

	public int getTipIndex(int x, int y)
	{
		return y *tipRows +x;
	}

	public int getTipX(int index)
	{
		return index %tipRows;
	}

	public int getTipY(int index)
	{
		return index /tipRows;
	}

	/**
	 * @return
	 */
	public int getSelectedTipIndex() {
		return getTipIndex(tipSelectedX, tipSelectedY);
	}

	/**
	 * @return
	 */
	public Tip getSelectedTip() {
		if (tips.length == 0) return null;
		return tips[getSelectedTipIndex()];
	}


	/**
	 * @return 選択に成功したらtrue、失敗したらfalse
	 */
	public boolean selectTip(Tip tip) {
		for (int i=0; i<tips.length; i++)
		{
			if (tip.getId().equals(tips[i].getId()))
			{
				tipSelectedX = getTipX(i);
				tipSelectedY = getTipY(i);

				repaint();

				return true;
			}
		}

		return false;
	}


}
