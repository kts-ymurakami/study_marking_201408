package jp.ktsystem.kadai201408.y_murakami;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import jp.ktsystem.kadai201408.y_murakami.Common.ErrorCode;
import jp.ktsystem.kadai201408.y_murakami.Common.SystemConstants;

/**
 * 課題Ｌｖ制御クラス
 * 課題1と読み込み処理等はかぶるため、KadaiLv1Controlを継承
 * @author y_murakami
 *
 */
public class KadaiLv2Control extends KadaiLv1Control {

	private String outputFilePath;// 出力ファイルパス

	/**
	 * 入力ファイルパスと出力ファイルパスを設定
	 *
	 * @param anInputPath 入力ファイルパス
	 * @param anOutputPath　出力ファイルパス
	 * @throws KadaiException
	 */
	public KadaiLv2Control(String anInputPath, String anOutputPath)
			throws KadaiException {

		super(anInputPath);
		if (null != anOutputPath && !anOutputPath.isEmpty()) {
			this.outputFilePath = anOutputPath;
		} else {
			throw new KadaiException(ErrorCode.FILE_IO_ERROR);
		}

	}

	/**
	 * 最大値のデータリストを取得する
	 * @param anAllList　全データ
	 * @return
	 */
	public List<InputDataModel> getMaxDataList(List<InputDataModel> anAllList) {

		long maxScore = -1;

		List<InputDataModel> maxList = new ArrayList<InputDataModel>();
		for (InputDataModel data : anAllList) {

			long score = data.getScore();
			if (maxScore <= score) {
				if(maxScore < score){
					maxList.clear();// 現在の最大値候補を削除
				}
				maxList.add(data);// 最大値を追加
				maxScore = score;// 最大値を更新
			}
		}

		return maxList;

	}

	/**
	 * 最大値データをファイルに書き出す。
	 * 複数個存在する場合はデータごとに改行して書き出し
	 *　[データの出現位置]:[データの内容]:[データの点数]
	 * @param maxDataList 最大値のデータリスト
	 * @param errorCode エラーコード　出力用　
	 * @throws KadaiException
	 */
	public void writeMaxDataToFile(List<InputDataModel> maxDataList)
			throws KadaiException {

		if (null == maxDataList) {
			return;
		}

		BufferedWriter bfWrite = null;
		try {
			bfWrite = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.outputFilePath),SystemConstants.CHARA_CODE ));
			String outStr = "";
			for (InputDataModel data : maxDataList) {
				outStr = String.format(SystemConstants.OUTPUT_FORMAT_STR,
						data.getPosition(),data.getDataStr(), data.getScore());
				bfWrite.write(outStr);
				bfWrite.newLine();
			}
		} catch (IOException ex) {
			throw new KadaiException(ErrorCode.FILE_IO_ERROR);
		} finally {
			if (null != bfWrite) {
				try {
					bfWrite.close();
				} catch (IOException ex) {
					throw new KadaiException(ErrorCode.FILE_IO_ERROR);
				}
			}
		}
	}

}
