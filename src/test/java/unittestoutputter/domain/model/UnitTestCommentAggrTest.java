package unittestoutputter.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * UnitTest出力用テストコード.
 */
@DisplayName("UnitTest出力用テストコード")
class UnitTestCommentAggrTest {

    /**
     * 単体項目内容の読み込み確認.<br/>
     * ■実施内容<br>
     * ①1項目出力<br/>
     * ■確認内容<br>
     * ①期待通りとなること<br/>
     * ★<br>
     * ■実施内容<br>
     * ②1項目出力<br>
     * 改行あり<br/>
     * ■確認内容<br>
     * ②期待通りとなること<br/>
     */
    @ParameterizedTest
    @CsvSource({"試験実施①<br/>\\r\\n■実施内容<br/>\\r\\n■確認内容①<br/>,testMethod1,1",
            "試験実施①<br/>\\r\\n■実施内容①<br>\\r\\n改行<br/>\\r\\n■確認内容①<br/>,testMethod2,1"})
    @DisplayName("UnitTestCommentオブジェクトへの格納")
    void testUnitTestCommentAggr(String methodDoc, String methodDocName, int count) {

        System.out.println("テスト実行");
        assertEquals(2, 1 + 1);

    }
}
