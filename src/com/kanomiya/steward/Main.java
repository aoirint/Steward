package com.kanomiya.steward;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
		AssetsFactory afact = AssetsFactory.newInstance();
		Assets assets = afact.newAssets();

		System.out.println(assets.toString());

		Game game = new Game(assets);

		Frame frame = new FrameWithView(ViewConsts.viewGame, game);
		frame.setResizable(false);
		frame.setSize(ViewConsts.viewWidth, ViewConsts.viewHeight);
		// frame.setPreferredSize(new Dimension(ViewConsts.viewWidth, ViewConsts.viewHeight));
		// frame.pack();
		frame.setLocation(60, 30);
		frame.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		frame.setVisible(true);

		ControlListener cl = new ControlListener();
		cl.game = game;
		frame.addKeyListener(cl);


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
