package unittestoutputter.domain.model.excel;

import lombok.Data;

/**
 * 試験項目内容クラス.
 */
@Data
public class ExamContent {

    /** no. */
    private String no;

    /** 実施方法. */
    private String howTo;

    /** 確認事項. */
    private String expected;

    /** 確認日付. */
    private String dateString;

    /** 実施結果. */
    private String result;


    /**
     * コンストラクタ.
     *
     * @param no No
     * @param howTo 実施方法
     * @param expected 確認事項
     * @param dateString 確認日付
     * @param result 実施結果
     */
    public ExamContent(String no, String howTo, String expected, String dateString, String result) {
        this.howTo = howTo;
        this.expected = expected;
        this.dateString = dateString;
        this.result = result;
        this.no = no;
    }
}
