package jp.ktsystem.kadai201408.y_murakami;

import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import jp.ktsystem.kadai201408.y_murakami.Common.ErrorCode;

import org.junit.Test;

public class KadaiTest {

	private static final String INPUT_DIR_PATH = "testInput\\"; // 出力ファイル格納ディレクトリ
	private static final String OUTPUT_DIR_PATH = "testOutput\\"; // 出力ファイル格納ディレクトリ
	private static final String EXPECT_DIR_PATH = "testExpect\\"; // 出力ファイル格納ディレクトリ

	private void executeValidTestLv1(String inputPath, int count) {

		try {
			Kadai.calcScoreSum(INPUT_DIR_PATH + inputPath);
		} catch (KadaiException ex) {
			fail(String.valueOf(ex.getErrorCode()));
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

	}

	private void executeInValidLv1Test(String inputPath, ErrorCode expectErrorCode) {

		try {
			Kadai.calcScoreSum(INPUT_DIR_PATH + inputPath);
			fail();
		} catch (KadaiException ex) {
			assertEquals(expectErrorCode.getErrorCode(), ex.getErrorCode());
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

	}

	private void executeValidTestLv2(String inputPath, String outputPath, String expectPath) {

		try {
			Kadai.printMaxScore(INPUT_DIR_PATH + inputPath, OUTPUT_DIR_PATH + outputPath);
			compareFile(outputPath, expectPath);
		} catch (KadaiException ex) {
			fail(String.valueOf(ex.getErrorCode()));
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

	}

	private void executeInValidLv2Test(String inputPath, String outputPath, ErrorCode expectErrorCode) {

		try {
			Kadai.printMaxScore(INPUT_DIR_PATH + inputPath, OUTPUT_DIR_PATH + outputPath);
			fail();
		} catch (KadaiException ex) {
			assertEquals(expectErrorCode.getErrorCode(), ex.getErrorCode());
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

	}

	private void compareFile(String outputFilePath, String expectFilePath) throws IOException {

		InputStream compFileStream = new BufferedInputStream(
				new FileInputStream(OUTPUT_DIR_PATH + outputFilePath));
		InputStream expectFileStream = new BufferedInputStream(
				new FileInputStream(EXPECT_DIR_PATH + expectFilePath));

		int input = -1;
		do {
			input = compFileStream.read();
			int output = expectFileStream.read();
			assertEquals(input, output);
		} while (-1 < input);

		compFileStream.close();
		expectFileStream.close();

	}

	@Test
	public void testCaseLv1_01() {
		try {
			Kadai.calcScoreSum(null);
			fail();
		} catch (KadaiException ex) {
			assertEquals(ErrorCode.FILE_IO_ERROR.getErrorCode(), ex.getErrorCode());
		} catch (Exception ex) {
			fail();
		}
	}

	@Test
	public void testCaseLv1_02() {
		try {
			Kadai.calcScoreSum("");
			fail();
		} catch (KadaiException ex) {
			assertEquals(ErrorCode.FILE_IO_ERROR.getErrorCode(), ex.getErrorCode());
		} catch (Exception ex) {
			fail();
		}
	}

	@Test
	public void testCaseLv1_03() {
		executeInValidLv1Test("qazwsxedc", ErrorCode.FILE_IO_ERROR);
	}

	@Test
	public void testCaseLv1_04() {
		executeInValidLv1Test("input001.txt", ErrorCode.FILE_IO_ERROR);
	}

	@Test
	public void testCaseLv1_05() {
		executeInValidLv1Test("input002.txt", ErrorCode.ILLEGAL_INPUT_DATA);
	}

	@Test
	public void testCaseLv1_06() {
		executeValidTestLv1("input003.txt", 0);
	}

	@Test
	public void testCaseLv1_07() {
		executeValidTestLv1("input004.txt", 12402);
	}

	@Test
	public void testCaseLv1_08() {
		executeValidTestLv1("input005.txt", 12402);
	}

	@Test
	public void testCaseLv1_09() {
		executeValidTestLv1("input006.txt", 12402);
	}

	@Test
	public void testCaseLv1_10() {
		executeValidTestLv1("input007.txt", 12402);
	}

	@Test
	public void testCaseLv1_11() {
		executeValidTestLv1("input008.txt", 26);
	}

	@Test
	public void testCaseLv2_01() {
		try {
			Kadai.printMaxScore(null, "testOut/testOut.txt");
			fail();
		} catch (KadaiException ex) {
			assertEquals(ErrorCode.FILE_IO_ERROR.getErrorCode(), ex.getErrorCode());
		} catch (Exception ex) {
			fail();
		}
	}

	@Test
	public void testCaseLv2_02() {
		try {
			Kadai.printMaxScore("", "testOut/testOut.txt");
			fail();
		} catch (KadaiException ex) {
			assertEquals(ErrorCode.FILE_IO_ERROR.getErrorCode(), ex.getErrorCode());
		} catch (Exception ex) {
			fail();
		}
	}

	@Test
	public void testCaseLv2_03() {
		executeInValidLv2Test("qazwsxedc", "testOut.txt", ErrorCode.FILE_IO_ERROR);
	}

	@Test
	public void testCaseLv2_04() {
		executeInValidLv2Test("input001.txt", "testOut.txt", ErrorCode.FILE_IO_ERROR);
	}

	@Test
	public void testCaseLv2_05() {
		executeInValidLv2Test("input002.txt", "testOut.txt", ErrorCode.ILLEGAL_INPUT_DATA);
	}

	@Test
	public void testCaseLv2_06() {
		executeValidTestLv2("input003.txt", "testOut.txt", "expected1.txt");
	}

	@Test
	public void testCaseLv2_07() {
		executeValidTestLv2("input004.txt", "testOut.txt", "expected2.txt");
	}

	@Test
	public void testCaseLv2_08() {
		executeValidTestLv2("input005.txt", "testOut.txt", "expected2.txt");
	}

	@Test
	public void testCaseLv2_09() {
		executeValidTestLv2("input009.txt", "testOut.txt", "expected3.txt");
	}

	@Test
	public void testCaseLv2_10() {
		try {
			Kadai.printMaxScore("input004", null);
			fail();
		} catch (KadaiException ex) {
			assertEquals(ErrorCode.FILE_IO_ERROR.getErrorCode(), ex.getErrorCode());
		} catch (Exception ex) {
			fail();
		}
	}

	@Test
	public void testCaseLv2_11() {
		try {
			Kadai.printMaxScore("input004", "");
			fail();
		} catch (KadaiException ex) {
			assertEquals(ErrorCode.FILE_IO_ERROR.getErrorCode(), ex.getErrorCode());
		} catch (Exception ex) {
			fail();
		}
	}

	@Test
	public void testCaseLv2_12() {
		executeValidTestLv2("input010.txt", "testOut.txt", "expected4.txt");
	}


}
