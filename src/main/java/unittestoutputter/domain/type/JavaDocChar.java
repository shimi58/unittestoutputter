package unittestoutputter.domain.type;

import lombok.Data;

/**
 * JavaDoc文字列クラス.
 *
 */
@Data
public class JavaDocChar {

    /** JavaDoc文字列(Unicode). */
    private String javaDocUnicode;

    /** 改行コードタイプ. */
    private String brType;

    /** JavaDoc文字集約. */
    private String[] javaDocChars;

    /**
     * コンストラクタ.
     *
     * br,br/がある場合、改行コードタイプを設定し、文字列から除外する
     *
     * @param str JavaDoc文字列(Unicode)
     */
    public JavaDocChar(String str) {

        if (str.endsWith("<br>")) {
            String[] strLen = str.split("<br>");
            this.javaDocUnicode = strLen[0];
            this.brType = "br";
        } else if (str.endsWith("<br/>")) {
            String[] strLen = str.split("<br/>");
            this.javaDocUnicode = strLen[0];
            this.brType = "brSlash";
        } else {
            this.javaDocUnicode = str;
            this.brType = "none";
        }

        // UTF-16文字コードの\\uを残した状態で切り出し
        javaDocChars = javaDocUnicode.split("(?=\\\\u)");

    }

    /**
     * JavaDoc文字列の空文字チェック.
     *
     * @return 空の場合、true
     */
    public boolean isEmptyStr() {

        if ("" == javaDocUnicode) {
            return true;
        }
        return false;

    }

    /**
     * 区切り文字タイプチェック:br.
     *
     * @return brの場合、true
     */
    public boolean isBrType() {

        if (brType.equals("br")) {
            return true;
        }
        return false;
    }

    /**
     * 区切り文字タイプチェック:br/.
     *
     * @return br/の場合、true
     */
    public boolean isBrSlashType() {

        if (brType.equals("brSlash")) {
            return true;
        }
        return false;
    }
}
