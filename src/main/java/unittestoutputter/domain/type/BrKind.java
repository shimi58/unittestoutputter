package unittestoutputter.domain.type;

import lombok.Getter;

/**
 * JavaDoc区切り文字定義クラス.
 *
 */
public enum BrKind {
    br("<br>"),
    brSlash("<br/>"),
    none("");

    @Getter
    private final String type;

    private BrKind(String type) {

        this.type = type;

    }

}
