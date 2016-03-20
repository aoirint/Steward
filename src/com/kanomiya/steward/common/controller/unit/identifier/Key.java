package com.kanomiya.steward.common.controller.unit.identifier;

/**
 * @author Kanomiya
 *
 */
public enum Key implements IInputIdentifier {
	UNKNOWN,


	// アルファベット
	A('A'),a('a'), B('B'),b('b'), C('C'),c('c'), D('D'),d('d'), E('E'),e('e'),
	F('F'),f('f'), G('G'),g('g'), H('H'),h('h'), I('I'),i('i'), J('J'),j('j'),
	K('K'),k('k'), L('L'),l('l'), M('M'),m('m'), N('N'),n('n'), O('O'),o('o'),
	P('P'),p('p'), Q('Q'),q('q'), R('R'),r('r'), S('S'),s('s'), T('T'),t('t'),
	U('U'),u('u'), V('V'),v('v'), W('W'),w('w'), X('X'),x('x'), Y('Y'),y('y'),
	Z('Z'),z('z'),

	// 数字
	NUM1('1'), NUM2('2'), NUM3('3'), NUM4('4'), NUM5('5'),
	NUM6('6'), NUM7('7'), NUM8('8'), NUM9('9'), NUM0('0'),

	// ファンクションキー
	F1, F2, F3, F4, F5,
	F6, F7, F8, F9, F10,
	F11, F12,

	// 記号
	PERIOD('.'), // .
	COMMA(','), // ,

	COLON(':'), // :
	SEMICOLON(';'), // ;

	EXCLAMATION('!'), // !
	QUESTION('?'), // ?
	AMPERSAND('&'), // &

	NUMBER_SIGN('#'), // #
	DOLLAR('$'), // $
	PERCENT('%'), // %

	AT('@'), // @
	BACK_SLASH('\\'), // \
	CIRCUMFLEX('^'), // ^
	UNDER_SCORE('_'), // _
	VERTICAL_LINE('|'), // |

	OPEN_BRACKET('['), CLOSE_BRACKET(']'), // []
	OPEN_BRACE('{'), CLOSE_BRACE('}'), // {}
	OPEN_PARENTHESIS('('), CLOSE_PARENHESIS(')'), // ()

	QUOTE('\''), // '
	BACK_QUOTE('`'), // `
	DOUBLE_QUOTE('"'), // "


	// 算術記号
	PLUS('+'), // +
	MINUS('-'), // -
	ASTERISK('*'), // *
	SLASH('/'), // /



	// 文字制御
	BACK_SPACE, DELETE, ENTER,

	// ページ制御
	HOME, END, PAGE_UP, PAGE_DOWN,
	UP, DOWN, RIGHT, LEFT,
	SCROLL_LOCK,

	// システム制御
	ESCAPE, SYSTEMREQUEST, PRINTSCREEN,
	BREAK, PAUSE, CLEAR,

	// 入力制御
	NUM_LOCK, CAPS_LOCK,
	INSERT,
	SHIFT, ALT, CONTROL,

	SELECT_ALL,

	;

	char ch;

	Key() {  }
	Key(char ch) { this.ch = ch; }

	public char toChar() { return ch; }

	/**
	 * @return 設定されているcharがヌル文字（\u0000）でないときtrue
	 */
	public boolean hasChar() { return isNullChar(ch); }

	protected static boolean isNullChar(char ch)
	{
		return (ch != '\u0000');
	}

	public static Key charToKey(char ch)
	{
		if (! isNullChar(ch))
		{
			Key[] keys = values();

			for (Key key: keys)
			{
				if (! key.hasChar()) continue;
				if (key.toChar() == ch) return key;
			}

		}

		return Key.UNKNOWN;
	}

}
