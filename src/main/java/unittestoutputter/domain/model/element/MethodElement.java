package unittestoutputter.domain.model.element;

import javax.lang.model.element.Element;
import lombok.Data;
import unittestoutputter.domain.type.BrKind;
import unittestoutputter.domain.type.JavaDocChar;

/**
 * メソッドElementクラス.
 *
 */
@Data
public class MethodElement {

    /** メソッドElement. */
    private Element methodElement;

    /** メソッド名. */
    private String methodName;

    /**
     * コンストラクタ.
     *
     * @param methodElement メソッドElement
     *
     */
    public MethodElement(Element methodElement) {
        this.methodElement = methodElement;
        this.methodName = methodElement.getSimpleName().toString();
    }

    /**
     * メソッドのJavaDocコメントを取得.
     *
     * @param classElementAggr クラスElement集約
     * @return JavaDocコメント文字列
     *
     */
    public String getMethodJavaDoc(ClassElementAggr classElementAggr) {

        String javadocUnicode =
                classElementAggr.getDocTrees().getDocCommentTree(methodElement).toString();

        // Unicode変換
        return this.convertToOiginal(javadocUnicode);
    }

    /**
     * Unicode文字列(UTF-16)からの変換.
     *
     * 改行コード毎のStringリスト作成を行う
     *
     * @param String JavaDoc内容(Unicode)
     * @return JavaDoc内容
     */
    private String convertToOiginal(String javadocUnicode) {

        // 改行コードで分割
        String[] unicodeSplit = javadocUnicode.toString().split("\r\n|\r|\n");

        StringBuffer sb = new StringBuffer();

        for (String unicodeStr : unicodeSplit) {

            JavaDocChar javaDocCharAggr = new JavaDocChar(unicodeStr);

            if (!(javaDocCharAggr.isEmptyStr())) {
                // 空ではない場合、ユニコード変換を行う
                sb.append(unicodeChange(javaDocCharAggr));
            }

            if (javaDocCharAggr.isBrType()) {
                // 区切り文字<br>付与
                sb.append(BrKind.br.getType());

            }
            if (javaDocCharAggr.isBrSlashType()) {
                // 区切り文字<br/>付与
                sb.append(BrKind.brSlash.getType());
            }

            // 改行コード挿入
            sb.append("\n");
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    /**
     * Unicode文字列(UTF-16)からの変換.
     *
     * 1文字ずつ文字コード変換を行う<br>
     * 英数字の場合は変換をスキップする<br>
     *
     * @param JavaDocChar JavaDoc文字列(Unicode)
     * @return String文字列(変換後)
     */
    private String unicodeChange(JavaDocChar javaDocCharAggr) {

        int[] codePoints = new int[1];

        String encodedText = "";
        StringBuilder sb = new StringBuilder();

        for (String javaDocChar : javaDocCharAggr.getJavaDocChars()) {

            if (javaDocChar.startsWith("\\u")) {

                codePoints[0] = Integer.parseInt(javaDocChar.substring(2, 6), 16);
                // 英数字の場合は変換をかけずに連結する
                encodedText = new String(codePoints, 0, 1)
                        + javaDocChar.substring(6, javaDocChar.length());
            } else {
                encodedText = javaDocChar;
            }

            sb.append(encodedText);
        }

        return sb.toString();

    }

}
