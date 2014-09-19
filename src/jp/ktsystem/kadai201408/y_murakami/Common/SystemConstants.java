package jp.ktsystem.kadai201408.y_murakami.Common;

import java.util.regex.Pattern;

/**
 * 定数クラス
 *
 * @author y_murakami
 *
 */
public class SystemConstants {

	/**
	 * 半角英数字パターン
	 */
	public static final Pattern HALF_WIDTH_ALPHANUMERIC_PATTERN = Pattern.compile("^[a-zA-Z]+$");

	/**
	 * データ区切り文字列
	 */
	public static final String DATA_SEPARATE_STR = ",";

	/**
	 * データ出力フォーマット
	 */
	public static final String OUTPUT_FORMAT_STR = "%d:%s:%d";

	/**
	 * 文字コード
	 */
	public static final String CHARA_CODE = "UTF-8";

}
