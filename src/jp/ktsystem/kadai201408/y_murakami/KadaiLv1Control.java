package jp.ktsystem.kadai201408.y_murakami;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import jp.ktsystem.kadai201408.y_murakami.Common.ErrorCode;
import jp.ktsystem.kadai201408.y_murakami.Common.SystemConstants;
import jp.ktsystem.kadai201408.y_murakami.Utils.CommonUtil;

/**
 * ファイル操作用クラス
 *
 * @author y_murakami
 */
public class KadaiLv1Control {

	private String inputFilePath = null;// 読み込みファイルパス

	/**
	 * ファイルパスをメンバに設定　コンストラクタ
	 *
	 * @param anInputPath
	 *            入力ファイルパス
	 * @throws KadaiException
	 */
	public KadaiLv1Control(String anInputPath) throws KadaiException {

		if (null != anInputPath && !anInputPath.isEmpty()) {
			this.inputFilePath = anInputPath;
		} else {
			throw new KadaiException(ErrorCode.FILE_IO_ERROR);
		}

	}

	/**
	 * メンバの入力ファイルパスからデータを読み込み、データモデルのリストを作成して返す
	 *
	 * @return　読み込みデータモデルのリスト
	 * @throws KadaiException
	 */
	public List<InputDataModel> createDataList() throws KadaiException {

		// データ文字列読み込み
		List<String> lineList = this.readInputFile();

		String topLine = "";
		if(null != lineList && 0 < lineList.size()){
			topLine = lineList.get(0);// 先頭1行を取得
		}

		// データ区切り文字列で分割
		String[] dataArray = topLine.split(SystemConstants.DATA_SEPARATE_STR,-1);

		ArrayList<InputDataModel> dataList = new ArrayList<InputDataModel>();// データ格納リスト
		// データモデルリストの生成
		for (int pos = 0; pos < dataArray.length; ++pos) {
			String data = dataArray[pos];

			InputDataModel dataModel = new InputDataModel(data, pos + 1);
			if (dataModel.getErrorFlag()) {
				throw new KadaiException(ErrorCode.ILLEGAL_INPUT_DATA);
			}

			dataList.add(dataModel);
		}

		return dataList;

	}

	/**
	 * リスト内のデータの総スコアを計算して返す
	 *
	 * @param dataModelList 入力データリスト
	 * @return　総スコア
	 * @throws KadaiException
	 */
	public long calcTotalScore(List<InputDataModel> dataModelList)
			throws KadaiException {

		long totalScore = 0;// 総スコア

		if (null == dataModelList) {
			return totalScore;
		}

		// 総スコアを計算
		for (InputDataModel dataModel : dataModelList) {
			if (null != dataModel && !dataModel.getErrorFlag()) {
				totalScore += dataModel.getScore();
			}
		}

		return totalScore;

	}

	/**
	 * 読み込み行内容をリストで返す
	 * @return 読み込み行内容のリスト
	 * @throws KadaiException
	 */
	private List<String> readInputFile() throws KadaiException {

		String dataStr = "";// 読み込み文字列
		BufferedReader bfReader = null;// ファイル読み込み用オブジェクト
		List<String> dataLineList = new ArrayList<String>();// 読み込み行のリスト

		// 入力ファイルの読み込み
		try {

			InputStream inputStrm =
					CommonUtil.skipBOM(new FileInputStream(this.inputFilePath));

			if(null == inputStrm){
				throw new KadaiException(ErrorCode.SYSTEM_EEROR);
			}

			bfReader = new BufferedReader(new InputStreamReader(inputStrm, SystemConstants.CHARA_CODE));

			// ファイルサイズチェック（仕様では考慮しないでよいことになっているが、１MB確認）
			File file=new File(this.inputFilePath);
			if(Math.pow(2, 20) < file.length()){
				//throw new KadaiException(ErrorCode.SYSTEM_EEROR); 必要があればthrow
			}

			// 読み込み
			dataStr = bfReader.readLine();
			for (int line = 0; null != dataStr; ++line) {
				if (1 > line) {
					dataLineList.add(dataStr);
				} else {
					break;
				}
				dataStr = bfReader.readLine();
			}

		} catch (IOException e) {
			throw new KadaiException(ErrorCode.FILE_IO_ERROR);
		} finally {
			if (bfReader != null) {
				try {
					bfReader.close();
				} catch (IOException e) {
					throw new KadaiException(ErrorCode.FILE_IO_ERROR);
				}
			}
		}

		return dataLineList;
	}

}
