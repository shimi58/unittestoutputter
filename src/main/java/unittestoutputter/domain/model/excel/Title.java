package unittestoutputter.domain.model.excel;

import lombok.Data;

/**
 * 試験項目クラス.
 */
@Data
public class Title {

    /** 試験項目. */
    String titleName;

    /**
     * コンストラクタ.
     *
     * @param title 試験項目
     * @param methodName メソッド名
     */
    public Title(String title, String methodName) {
        this.titleName = title + "\r\n(" + methodName + ")";;
    }

}
