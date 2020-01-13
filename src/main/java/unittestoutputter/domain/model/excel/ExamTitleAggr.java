package unittestoutputter.domain.model.excel;

import java.util.List;
import lombok.Data;

/**
 * 試験項目集約クラス.
 */
@Data
public class ExamTitleAggr {

    /** 試験項目. */
    private Title title;

    /** 試験内容集約. */
    private List<ExamContent> examContentAggr;

    /**
     * コンストラクタ.
     *
     * @param title 試験項目
     * @param contentList 試験項目内容
     */
    public ExamTitleAggr(Title title, List<ExamContent> contentList) {
        this.title = title;
        this.examContentAggr = contentList;
    }

}
