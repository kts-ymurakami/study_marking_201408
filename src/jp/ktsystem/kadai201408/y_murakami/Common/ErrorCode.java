package jp.ktsystem.kadai201408.y_murakami.Common;

/**
 * エラーコード
 * @author y_murakami
 */
public enum ErrorCode {

	//エラーコード
	FILE_IO_ERROR(1), //1:ファイルの入出力エラー
	ILLEGAL_INPUT_DATA(2), //2:データ内部に半角英数字以外の文字が存在した
	SYSTEM_EEROR(3); //3:その他のエラー

	private int errorCode;

	/**
	 * エラーコードの設定
	 * @param errorCode
	 */
	private ErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * エラーコードを返す
	 * @return エラーコード
	 */
	public int getErrorCode() {
		return errorCode;
	}

}
