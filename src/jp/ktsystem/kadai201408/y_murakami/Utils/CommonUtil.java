package jp.ktsystem.kadai201408.y_murakami.Utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

import jp.ktsystem.kadai201408.y_murakami.KadaiException;
import jp.ktsystem.kadai201408.y_murakami.Common.AlphabetScore;
import jp.ktsystem.kadai201408.y_murakami.Common.ErrorCode;

public class CommonUtil {

	/**
	 * アルファベットのスコアを返す　
	 * A=1,B=2.....Z=26
	 * @param alphabet アルファベット
	 * @return　スコア　1～26
	 * @throws KadaiException
	 */
	public static int GetScoreByAlphabet(String alphabet) throws KadaiException {

		int score = 0;
		AlphabetScore scoreEnum = null;

		try {
			scoreEnum = AlphabetScore.valueOf(alphabet);
		} catch (Exception ex) {
			throw new KadaiException(ErrorCode.SYSTEM_EEROR);
		}

		if(null != scoreEnum){
			score = scoreEnum.GetScore();
		}

		return score;

	}

	/**
	 * 対象InputStreamにのBOMが存在する場合は読み飛ばし後のInputStreamを返す
	 * @param input 読み込みFileInputStream
	 * @return　BOMなしのInputStream
	 * @throws KadaiException　
	 */
	public static InputStream skipBOM(FileInputStream input)
			throws KadaiException {

		InputStream inputStrm = new BufferedInputStream(input);

		try {
			inputStrm.mark(3);// BOM呼み飛ばし後にマーク
			// BOM該当部分を読み込み
			if (3 <= inputStrm.available()) {
				byte[] b = new byte[3];
				inputStrm.read(b, 0, 3);
				// BOMでない場合はマークをリセット
				if ((byte) 0xEF != b[0] || (byte) 0xBB != b[1]
						|| (byte) 0xBF != b[2]) {
					inputStrm.reset();
				}
			}
		} catch (Exception ex) {
			throw new KadaiException(ErrorCode.FILE_IO_ERROR);
		}

		return inputStrm;
	}
}
