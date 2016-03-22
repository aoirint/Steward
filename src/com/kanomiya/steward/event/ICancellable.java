package com.kanomiya.steward.event;


/**
 *
 * <p>
 * イベントがキャンセル可能なとき実装します
 *
 * <p>
 * {@link ICancellable}を実装したイベントのイベントハンドラは{@link ICancellable#isCancelled()}の扱いをそれぞれ考慮する必要があります
 *
 * <p>
 * {@link IConsumable}よりも{@link ICancellable}の方がより強力にイベントの受諾拒否を表します
 *
 * @author Kanomiya
 *
 */public interface ICancellable {

	/**
	 *
	 * イベントをキャンセルします
	 *
	 */
	void cancel();

	/**
	 *
	 * キャンセルフラグを返します
	 *
	 * @return キャンセルフラグ
	 */
	boolean isCancelled();

}
