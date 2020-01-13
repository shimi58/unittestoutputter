package unittestoutputter.domain.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.Data;
import unittestoutputter.domain.model.excel.ExamContent;
import unittestoutputter.domain.model.excel.ExamTitleAggr;
import unittestoutputter.domain.model.excel.Title;
import unittestoutputter.setting.Settings;



/**
 * 単体テストコメント出力項目モデル集約クラス.<br/>
 *
 */
@Data
public class UnitTestCommentAggr {

    /** 単体試験項目内容集約. */
    private ExamTitleAggr examTitleAggr;

    /** 試験項目数. */
    private int countExam;


    /** コンストラクタ. */
    public UnitTestCommentAggr() {

    }

    /**
     * コンストラクタ.
     *
     * br/毎に文字列を分割し、UnitTestCommentオブジェクトに格納する<br>
     *
     * @param methodDoc JavaDoc情報
     * @param count No
     */
    public UnitTestCommentAggr(String methodDoc, String methodDocName, int count) {

        // メソッド名
        String methodName = methodDocName;
        // 出力テキスト文字列
        String commentText = methodDoc;

        // 特定文字列★で分割
        List<String> commentsArrayList =
                Arrays.asList(commentText.split("★<br>\r\n|★<br>\\r|★<br>\n"));

        // 1回目の処理フラグ
        boolean firstFlg = true;

        // 項目ごとの件数
        int titleCount = count;

        // 項目クラス
        Title title = null;

        List<ExamContent> examContentList = new ArrayList<ExamContent>();

        for (int i = 0; i < commentsArrayList.size(); i++) {
            // 改行コードを全置換し、１行に整形
            String tmpComment =
                    commentsArrayList.get(i).replaceAll("<br/>\r\n|<br/>\r|<br/>\n", "<br/>");
            // <br>を削除
            String comment = tmpComment.replaceAll("<br>", "");

            // <br/>で分割
            final String[] comments = comment.split("<br/>");


            try {
                if (firstFlg) {
                    // 初回のみ項目オブジェクトを作成
                    title = new Title(comments[0], methodName);

                    ExamContent examContent =
                            new ExamContent(Integer.toString(titleCount + 1), comments[1],
                                    comments[2], Settings.getDateString(), Settings.DEFAULT_RESULT);
                    examContentList.add(examContent);

                    firstFlg = false;
                } else {
                    // 2行目以降
                    ExamContent examContent =
                            new ExamContent(Integer.toString(titleCount + 1), comments[0],
                                    comments[1], Settings.getDateString(), Settings.DEFAULT_RESULT);
                    examContentList.add(examContent);
                }
            } catch (ArrayIndexOutOfBoundsException e) {

                System.out.println("JavaDocコメントが不足しています。(" + count + "," + methodName + ")");
                break;
            }
            titleCount++;
        }

        this.examTitleAggr = new ExamTitleAggr(title, examContentList);
        this.countExam = examContentList.size();

    }
}
