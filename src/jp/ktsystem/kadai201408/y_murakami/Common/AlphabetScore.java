package jp.ktsystem.kadai201408.y_murakami.Common;

/**
 * アルファベットのスコア　A=1,B=2.....Z=26
 * @author y_murakami
 */
public enum AlphabetScore {

	A(1),B(2),C(3),D(4),E(5),F(6),G(7),
	H(8),I(9),J(10),K(11),L(12),M(13),N(14),
	O(15),P(16),Q(17),R(18),S(19),T(20),U(21),
	V(22),W(23),X(24),Y(25),Z(26);

	private int score;

	/**
	 * スコアを設定するプライベートコンストラクタ
	 * @param score スコア
	 */
	private AlphabetScore(int score) {
		this.score = score;
	}

	public int GetScore() {
		return this.score;
	}

}
