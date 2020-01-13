package unittestoutputter.domain.model.element;

import javax.lang.model.element.Element;
import lombok.Data;
import unittestoutputter.setting.Settings;

/**
 * クラスElementクラス.
 *
 */
@Data
public class ClassElement {

    /** クラスElement. */
    private Element classElement;

    /** クラス名. */
    private String className;

    /**
     * コンストラクタ.
     *
     * @param element クラスElement
     */
    public ClassElement(final Element element) {
        // クラスElement
        this.classElement = element;
        // クラス名
        this.className = classElement.getSimpleName().toString();
    }

    /**
     * パッケージ名取得. クラスに所属しているパッケージ名を返す<br>
     *
     * @param classElementAggr クラスElement集約
     * @return String パッケージ名
     *
     */
    public String getPackageName(ClassElementAggr classElementAggr) {

        return classElementAggr.getElements().getPackageOf(classElement).toString();
    }

    /**
     * ディレクトリパス取得.
     *
     * <p>
     * UnitTest出力先のディレクトリパスを返す
     * </p>
     *
     * @param classElementAggr クラスElement集約
     * @return String ディレクトリパス
     *
     */
    public String getDirPath(ClassElementAggr classElementAggr) {

        return Settings.outputDirPath + getPackageName(classElementAggr) + "\\";

    }

    /**
     * ファイルパス取得.
     *
     * <p>
     * UnitTest出力先のディレクトリパスを返す
     * </p>
     *
     * @param classElementAggr クラスElement集約
     * @return String ファイル名(フルパス)
     */
    public String getFilePath(ClassElementAggr classElementAggr) {

        return getDirPath(classElementAggr) + className + ".xlsx";

    }
}

