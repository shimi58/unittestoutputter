package unittestoutputter;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import javax.lang.model.SourceVersion;
import javax.tools.Diagnostic.Kind;
import jdk.javadoc.doclet.Doclet;
import jdk.javadoc.doclet.DocletEnvironment;
import jdk.javadoc.doclet.Reporter;
import unittestoutputter.domain.model.UnitTestCommentAggr;
import unittestoutputter.domain.model.element.ClassElement;
import unittestoutputter.domain.model.element.ClassElementAggr;
import unittestoutputter.domain.model.element.MethodElement;
import unittestoutputter.domain.model.element.MethodElementAggr;
import unittestoutputter.domain.model.excel.ExamContent;
import unittestoutputter.setting.Settings;

/**
 * ユニットテスト項目出力メインクラス.
 *
 */
public class UnitTestOutputter implements Doclet {

    Reporter reporter;

    /** Excel行. */
    private static int excelRow;

    @Override
    public void init(Locale locale, Reporter reporter) {
        reporter.print(Kind.NOTE, "Doclet using locale: " + locale);
        this.reporter = reporter;

        // 設定情報セット
        Settings.getSettings();
    }

    @Override
    public boolean run(DocletEnvironment docEnv) {
        reporter.print(Kind.NOTE, "overviewfile: " + overviewfile);

        // 設定情報セット
        if (!Settings.getSettings()) {
            return false;
        }

        // クラスファイル要素を取得
        ClassElementAggr classElementAggr = new ClassElementAggr(docEnv);

        for (ClassElement classElement : classElementAggr.getList()) {

            // Excel出力準備
            if (!classElement.getClassName().endsWith("Test")) {
                // クラス名終わりがTest以外の場合は除外
                continue;
            }
            // クラス毎にExcel出力
            excelRow = 0;

            // パッケージ名のディレクトリを生成
            new File(classElement.getDirPath(classElementAggr)).mkdirs();

            // Templete読み込み
            ExcelFile excel = new ExcelFile(Settings.templatePath);
            excel.open();
            excel.selectSheet(Settings.targetSheetName);

            // クラス内のメソッド要素を取得
            MethodElementAggr methodElementAggr = new MethodElementAggr(classElement);

            createMethodJavaDoc(classElementAggr, methodElementAggr, excel);

            excel.putValue(Settings.titleRow, Settings.titleColumn, classElement.getClassName());
            excel.putValue(Settings.countRow, Settings.countColumn, Integer.toString(excelRow));

            excel.saveAs(classElement.getFilePath(classElementAggr));

            excel.close();

        }

        return true;
    }

    /**
     * メソッド毎のJavaDoc生成.
     *
     * @param classElementAggr methodDoc
     * @param methodElementAggr 行数
     * @param excel エクセルファイル情報
     *
     * @return count 行数
     */
    public static boolean createMethodJavaDoc(ClassElementAggr classElementAggr,
            MethodElementAggr methodElementAggr, ExcelFile excel) {

        for (MethodElement method : methodElementAggr.list) {
            // メソッド毎のExcel項目出力
            if (!method.getMethodName().startsWith("test")) {
                // メソッド名始まりがtest以外の場合は除外
                continue;
            }

            int outputRow = outputMethodInfo(method.getMethodJavaDoc(classElementAggr),
                    method.getMethodName(), excel);

            System.out.println(outputRow + "件の出力" + "(" + method.getMethodName() + ")");

        }

        return true;
    }

    /**
     * ユニットテスト出力処理.
     *
     * @param methodDoc methodDoc
     * @param excel エクセルファイル情報
     * @return count 行数
     */
    public static int outputMethodInfo(String methodDoc, String methodDocName, ExcelFile excel) {

        // 単体テストコメント出力項目モデル集約初期化
        UnitTestCommentAggr unitTestCommentAggr =
                new UnitTestCommentAggr(methodDoc, methodDocName, excelRow);

        boolean firstFlg = true;

        for (ExamContent examContent : unitTestCommentAggr.getExamTitleAggr()
                .getExamContentAggr()) {

            if (firstFlg) {
                // 初回のみ項目を出力
                excel.putValue(excelRow + Settings.startRow, 0, examContent.getNo());
                excel.putValue(excelRow + Settings.startRow, 1,
                        unitTestCommentAggr.getExamTitleAggr().getTitle().getTitleName());
                excel.putValue(excelRow + Settings.startRow, 2, examContent.getHowTo());
                excel.putValue(excelRow + Settings.startRow, 3, examContent.getExpected());
                excel.putValue(excelRow + Settings.startRow, 4, examContent.getDateString());
                excel.putValue(excelRow + Settings.startRow, 5, examContent.getResult());

                firstFlg = false;
            } else {
                excel.putValue(excelRow + Settings.startRow, 0, examContent.getNo());
                excel.putValue(excelRow + Settings.startRow, 2, examContent.getHowTo());
                excel.putValue(excelRow + Settings.startRow, 3, examContent.getExpected());
                excel.putValue(excelRow + Settings.startRow, 4, examContent.getDateString());
                excel.putValue(excelRow + Settings.startRow, 5, examContent.getResult());
            }
            excelRow++;
        }

        return unitTestCommentAggr.getCountExam();
    }

    @Override
    public String getName() {
        return "Example";
    }

    private String overviewfile;

    @Override
    public Set<? extends Option> getSupportedOptions() {
        Option[] options = {new Option() {
            private final List<String> someOption =
                    Arrays.asList("-overviewfile", "--overview-file", "-o");

            @Override
            public int getArgumentCount() {
                return 1;
            }

            @Override
            public String getDescription() {
                return "an option with aliases";
            }

            @Override
            public Option.Kind getKind() {
                return Option.Kind.STANDARD;
            }

            @Override
            public List<String> getNames() {
                return someOption;
            }

            @Override
            public String getParameters() {
                return "file";
            }

            @Override
            public boolean process(String opt, List<String> arguments) {
                overviewfile = arguments.get(0);
                return true;
            }
        }};
        return new HashSet<>(Arrays.asList(options));
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        // support the latest release
        return SourceVersion.latest();
    }
}
