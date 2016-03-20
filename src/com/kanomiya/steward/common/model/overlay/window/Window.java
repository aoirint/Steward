package com.kanomiya.steward.common.model.overlay.window;

/**
 * @author Kanomiya
 *
 */
public class Window extends Component {

	public boolean isClosable;

	public Window()
	{
		super(0,0, 0,0);

		isClosable = true;
	}

	public Window(int x, int y)
	{
		super(x,y, 0,0);

		isClosable = true;
	}

	public Window(int x, int y, int width, int height)
	{
		super(x, y, width, height);

		isClosable = true;
	}


	/**
	 *
	 * 任意のタイミングで閉じることができるかどうかを判定します
	 *
	 * @return 閉じられるならtrue
	 */
	public boolean isClosable() {
		return isClosable;
	}

	public void setClosable(boolean isClosable) {
		this.isClosable = isClosable;
	}


}
