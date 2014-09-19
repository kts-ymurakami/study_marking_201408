package jp.ktsystem.kadai201408.y_murakami.Utils;

import java.util.regex.Matcher;

import jp.ktsystem.kadai201408.y_murakami.Common.SystemConstants;

/**
 * エラーチェック用クラス
 * @author y_murakami
 *
 */
public class ErrorUtil {

	/**
	 * 文字列がデータとして正しいか
	 * @param str 対象文字列
	 * @return 正　true エラー false
	 */
	public static boolean CheckInputData(String str){

		if(null == str){
			return false;
		}

		if(str.isEmpty()){
			return true;
		}

		Matcher strMatcher = SystemConstants.HALF_WIDTH_ALPHANUMERIC_PATTERN.matcher(str);

		return strMatcher.matches();
	}

}
