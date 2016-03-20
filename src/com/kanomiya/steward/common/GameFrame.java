package com.kanomiya.steward.common;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.kanomiya.steward.common.view.View;
import com.kanomiya.steward.common.view.ViewConsts;


/**
 * @author Kanomiya
 *
 */
public class GameFrame extends FrameWithView<Game> {

	/**
	 * @param view
	 * @param model
	 */
	public GameFrame(View<Game> view, Game game) {
		super(view, game);

		setResizable(false);
		pack();
		// frame.setPreferredSize(new Dimension(ViewConsts.viewWidth, ViewConsts.viewHeight));
		setRealSize(ViewConsts.viewWidth, ViewConsts.viewHeight);

		setTitle("Steward");
		setLocation(60, 30);
		addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		addComponentListener(new ComponentAdapter()
		{
			@Override public void componentResized(ComponentEvent e)
			{
				setRealSize(ViewConsts.viewWidth, ViewConsts.viewHeight);
			}
		});

		addKeyListener(game.controlListener);
		addMouseListener(game.controlListener);
		addMouseMotionListener(game.controlListener);

	}


}
