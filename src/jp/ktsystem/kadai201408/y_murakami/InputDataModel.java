package jp.ktsystem.kadai201408.y_murakami;
import jp.ktsystem.kadai201408.y_murakami.Utils.CommonUtil;
import jp.ktsystem.kadai201408.y_murakami.Utils.ErrorUtil;

/**
 * 課題用データのモデルクラス
 * @author y_murakami
 *
 */
public class InputDataModel {

	private String dataStr;// データ文字列
	private long position;// データ出現位置
	private long score = 0;// データスコア
	private boolean errorFlag = false; // エラーフラグ

	/**
	 * 文字列と出現位置をメンバに設定
	 * スコアを計算してメンバに設定
	 * @param dataStr データ文字列
	 * @param position データ出現位置
	 * @throws KadaiException
	 */
	public InputDataModel(String dataStr, long position) throws KadaiException {

		// エラーの確認
		this.errorFlag = !ErrorUtil.CheckInputData(dataStr);
		this.dataStr = dataStr;
		this.position = position;
		if (!this.errorFlag) {
			this.score = this.calcScore();
		}
	}

	public String getDataStr() {
		return dataStr;
	}

	public long getPosition() {
		return position;
	}

	public long getScore() {
		return score;
	}

	public boolean getErrorFlag() {
		return errorFlag;
	}

	/**
	 * データのスコアを計算して返す
	 * (A=1,B=2.....Z=26点 × 出現位置)
	 * @throws KadaiException
	 */
	private long calcScore() throws KadaiException {

		long score = 0;
		if (null != dataStr) {
			dataStr = dataStr.toUpperCase();// 大文字に変換
			char[] charArray = this.dataStr.toCharArray();
			for (char charData : charArray) {
				score += CommonUtil.GetScoreByAlphabet(charData);
			}
		}

		return score * this.position;

	}

}
