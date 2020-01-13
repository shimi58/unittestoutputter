package unittestoutputter.domain.model.element;

import com.sun.source.util.DocTrees;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.util.Elements;
import jdk.javadoc.doclet.DocletEnvironment;
import lombok.Data;

/**
 * クラスElement集約クラス.
 */
@Data
public class ClassElementAggr {

    /** クラスElement集約. */
    private List<ClassElement> list;

    /** JavaDoc構成情報. */
    private DocTrees docTrees;

    /** packageを含むElements構成情報. */
    private Elements elements;

    /**
     * コンストラクタ.
     *
     * @param docEnv Doclet構成情報
     *
     */

    public ClassElementAggr(DocletEnvironment docEnv) {

        this.list = new ArrayList<ClassElement>();

        // クラスリストを取得
        Set<? extends Element> temp = docEnv.getIncludedElements();
        for (Element classElementTemp : temp) {
            if (!(ElementKind.CLASS == classElementTemp.getKind())) {
                // クラスファイルのみ出力対象
                continue;
            }
            ClassElement classElement = new ClassElement(classElementTemp);

            this.list.add(classElement);
        }

        // JavaDoc情報を取得
        this.docTrees = docEnv.getDocTrees();

        // Elements情報を取得
        this.elements = docEnv.getElementUtils();
    }
}
