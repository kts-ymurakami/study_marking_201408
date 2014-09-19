package jp.ktsystem.kadai201408.y_murakami;

import java.util.List;

public class Kadai {

	/**
	 * 入力ファイルを読み込んで得点の合計を計算して返す
	 * @param anInputPath 入力ファイルパス(フルパスで****.txtまで指定する)
	 * @return データの点数の積の合計
	 * @throws KadaiException
	 */
	public static long calcScoreSum(String anInputPath) throws KadaiException {

		// コントロールオブジェクトを生成
		KadaiLv1Control kadaiCtrl =  new KadaiLv1Control(anInputPath);
		// データモデルのリストを生成
		List<InputDataModel> dataModelList = kadaiCtrl.createDataList();
		// 総スコアを計算
		return  kadaiCtrl.calcTotalScore(dataModelList);

	}

	/**
	 *　入力ファイルを読み込んで最大得点のデータをファイルに出力する
	 * @param anInputPath 入力ファイルパス(フルパスで****.txtまで指定する)
	 * @param anOutputPath 出力ファイルパス(フルパスで****.txtまで指定する)
	 * @throws KadaiException
	 */
	public static void printMaxScore(String anInputPath, String anOutputPath) throws KadaiException {

		// コントロールオブジェクトを生成
		KadaiLv2Control kadaiCtrl =  new KadaiLv2Control(anInputPath,anOutputPath);
		// データのリストを作成
		List<InputDataModel> dataModelList = kadaiCtrl.createDataList();
		// 最大データリストの作成
		List<InputDataModel> maxDataList = kadaiCtrl.getMaxDataList(dataModelList);
		// ファイル書き出し
		kadaiCtrl.writeMaxDataToFile(maxDataList);

	}

}
