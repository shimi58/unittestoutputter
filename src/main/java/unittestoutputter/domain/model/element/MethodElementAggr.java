package unittestoutputter.domain.model.element;

import java.util.ArrayList;
import java.util.List;
import javax.lang.model.element.Element;
import lombok.Data;

@Data
public class MethodElementAggr {

    /** メソッドElement集約. */
    public List<MethodElement> list;

    /**
     * コンストラクタ.
     *
     * @param classElement Doclet構成情報
     *
     */
    public MethodElementAggr(ClassElement classElement) {

        this.list = new ArrayList<MethodElement>();

        // クラスリストを取得
        List<? extends Element> elementList = classElement.getClassElement().getEnclosedElements();
        for (Element element : elementList) {
            MethodElement methodElement = new MethodElement(element);
            this.list.add(methodElement);
        }

    }
}
