package unittestoutputter.setting;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * 設定情報読み込みクラス.
 *
 */
public class SettingUtil {

    /**
     * 設定情報存在確認.
     *
     */
    public static int intTryParse(String value, int alternative) {
        int result = 0;

        try {
            result = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            result = alternative;
        }

        return result;
    }

    /**
     * 設定情報取得.
     *
     */
    public static String getSetting(ResourceBundle bundle, String key, String alternative) {
        String result = "";

        try {
            result = bundle.getString(key);

        } catch (MissingResourceException e) {
            result = alternative;
        }

        return result;

    }
}
