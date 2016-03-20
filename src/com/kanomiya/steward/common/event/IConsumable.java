package com.kanomiya.steward.common.event;

/**
 *
 * <p>
 * イベントが消費可能なとき実装します
 *
 * <p>
 * {@link IConsumable}を実装したイベントのイベントハンドラは{@link IConsumable#isConsumed()}の扱いをそれぞれ考慮する必要があります
 *
 * <p>
 * {@link IConsumable}よりも{@link ICancellable}の方がより強力にイベントの受諾拒否を表します
 *
 * @author Kanomiya
 *
 */
public interface IConsumable {

	/**
	 * イベントを消費します
	 */
	void consume();

	/**
	 *
	 * 消費フラグを返します
	 *
	 * @return 消費フラグ
	 */
	boolean isConsumed();

}
