package com.kanomiya.steward;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import com.kanomiya.steward.common.FrameWithView;
import com.kanomiya.steward.common.Game;
import com.kanomiya.steward.common.controller.ControlListener;
import com.kanomiya.steward.common.model.assets.Assets;
import com.kanomiya.steward.common.model.assets.AssetsFactory;
import com.kanomiya.steward.common.view.ViewConsts;



public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		File savesDir = new File("saves");
		if (! savesDir.exists()) savesDir.mkdir();





		AssetsFactory afact = AssetsFactory.newInstance();
		Assets assets = afact.newAssets();

		// TODO: for Debug
		// System.out.println(assets.toString());


		Game game = new Game(assets, assets.getPlayer());


		FrameWithView frame = new FrameWithView(ViewConsts.viewGame, game);
		ViewConsts.viewGame.setSize(ViewConsts.viewWidth, ViewConsts.viewHeight);

		frame.setResizable(false);
		frame.pack();
		// frame.setPreferredSize(new Dimension(ViewConsts.viewWidth, ViewConsts.viewHeight));
		frame.setRealSize(ViewConsts.viewWidth, ViewConsts.viewHeight);

		frame.setTitle("Steward");
		frame.setLocation(60, 30);
		frame.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		frame.addComponentListener(new ComponentAdapter()
		{
			@Override public void componentResized(ComponentEvent e)
			{
				frame.setRealSize(ViewConsts.viewWidth, ViewConsts.viewHeight);
			}
		});

		ControlListener cl = new ControlListener();
		cl.game = game;
		cl.gameFrame = frame;
		cl.frameInsets = frame.getInsets();

		cl.changeMode(game.thePlayer.mode);

		frame.addKeyListener(cl);
		frame.addMouseListener(cl);
		frame.addMouseMotionListener(cl);


		frame.setVisible(true);


		Thread repainter = new Thread("Repainter")
		{
			@Override public void run()
			{
				while (frame.isVisible())
				{
					frame.repaint();

					try {
						sleep(ViewConsts.frameLifeMills);
					} catch (InterruptedException e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
					}
				}
			}
		};

		repainter.start();

	}

}
