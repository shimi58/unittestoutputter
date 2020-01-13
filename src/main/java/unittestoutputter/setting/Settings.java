package unittestoutputter.setting;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Settingsクラス.
 *
 */
public class Settings {
    /** デフォルト出力パス. */
    public static final String DEFAULT_OUTPUT_DIR_PATH = "C:\\Temp\\UnitTestDoc\\";
    /** デフォルトテンプレートパス. */
    public static final String DEFAULT_TEMPLATE_PATH = "C:\\Temp\\UnitTestTemplate.xlsx";
    /** デフォルトDateFormat. */
    public static final String DEFAULT_DATE_FORMAT = "yyyy/MM/dd";
    /** デフォルトシート名. */
    public static final String DEFAULT_SHEET_NAME = "Sheet1";
    /** デフォルト出力パス. */
    public static final String DEFAULT_RESULT = "OK";
    /** デフォルト開始行. */
    public static final int DEFAULT_START_ROW = 4;
    /** デフォルトタイトル行. */
    public static int DEFAULT_TITLE_ROW = 0;
    /** デフォルトタイトル列. */
    public static int DEFAULT_TITLE_COLUMN = 1;
    /** デフォルト件数行. */
    public static int DEFAULT_COUNT_ROW = 1;
    /** デフォルト件数列. */
    public static int DEFAULT_COUNT_COLUMN = 1;
    /** 出力パス. */
    public static String outputDirPath = null;
    /** テンプレートパス. */
    public static String templatePath = null;
    /** 確認日付. */
    public static String dateString = null;
    /** 日付フォーマット. */
    public static String dateFormat = null;
    /** シート名. */
    public static String targetSheetName = null;
    /** 開始行. */
    public static int startRow = 0;
    /** タイトル行. */
    public static int titleRow = 0;
    /** タイトル列. */
    public static int titleColumn = 0;
    /** 件数行. */
    public static int countRow = 0;
    /** 件数列. */
    public static int countColumn = 0;

    /**
     * 初期設定構築.
     *
     */
    public static boolean getSettings() {
        final ResourceBundle bundle = ResourceBundle.getBundle("application");

        outputDirPath = SettingUtil.getSetting(bundle, "output.dir=", DEFAULT_OUTPUT_DIR_PATH);
        templatePath = SettingUtil.getSetting(bundle, "template.path", DEFAULT_TEMPLATE_PATH);
        dateFormat = SettingUtil.getSetting(bundle, "format.date", DEFAULT_DATE_FORMAT);
        targetSheetName = SettingUtil.getSetting(bundle, "target.sheet", DEFAULT_SHEET_NAME);
        startRow = SettingUtil.intTryParse(SettingUtil.getSetting(bundle, "start.row", null),
                DEFAULT_START_ROW);
        titleRow = SettingUtil.intTryParse(SettingUtil.getSetting(bundle, "title.row", null),
                DEFAULT_TITLE_ROW);
        titleColumn = SettingUtil.intTryParse(SettingUtil.getSetting(bundle, "title.column", null),
                DEFAULT_TITLE_COLUMN);
        countRow = SettingUtil.intTryParse(SettingUtil.getSetting(bundle, "count.row", null),
                DEFAULT_COUNT_ROW);
        countColumn = SettingUtil.intTryParse(SettingUtil.getSetting(bundle, "count.column", null),
                DEFAULT_COUNT_COLUMN);

        return true;
    }

    /**
     * 現在日時を返却.
     *
     */
    public static String getDateString() {
        if (dateString == null) {
            dateString = new SimpleDateFormat(dateFormat).format(new Date());
        }
        return dateString;
    }
}
