package jp.ktsystem.kadai201408.y_murakami;

public class test {

	public static void main(String[] args) {
		try{
			System.out.println(Kadai.calcScoreSum("test.txt"));
		}catch(KadaiException ke){
			System.out.println(ke.getErrorCode());
		}

	}

}
