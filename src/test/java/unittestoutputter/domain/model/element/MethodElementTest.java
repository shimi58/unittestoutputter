package unittestoutputter.domain.model.element;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * UnitTest出力用テストコード
 */
@DisplayName("UnitTest出力用テストコード")
class MethodElementTest {

    /**
     * 改行コード毎のStringリスト作成確認.<br/>
     * ■実施内容<br>
     * ①改行コードあり<br/>
     * ■確認内容<br>
     * ①期待通りとなること<br/>
     */
    @ParameterizedTest
    @CsvSource({"convertToOiginalTest1"})
    @DisplayName("改行コード毎のStringリスト作成")
    void testConvertToOiginal(String test) {

        System.out.println("テスト実行:" + test);
        assertEquals(2, 1 + 1);

    }

    /**
     * 文字コード変換確認.<br/>
     * ■実施内容<br>
     * ①ユニコードのみ<br/>
     * ■確認内容<br>
     * ①期待通りとなること<br/>
     * ★<br>
     * ■実施内容<br>
     * ②ユニコード・Alphabet混在<br/>
     * ■確認内容<br>
     * ②期待通りとなること<br/>
     * ★<br>
     * ■実施内容<br>
     * ③ユニコード・123混在<br/>
     * ■確認内容<br>
     * ③期待通りとなること<br/>
     */
    @ParameterizedTest
    @CsvSource({"unicodeChangeTest1", "unicodeChangeTest2", "unicodeChangeTest3"})
    @DisplayName("文字コード変換")
    void testUnicodeChange(String test) {

        System.out.println("テスト実行:" + test);
        assertEquals(2, 1 + 1);

    }

}
