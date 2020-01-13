package unittestoutputter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Excelファイル操作クラス.<br/>
 */
public class ExcelFile {

    public static final int MAX_ROW = 65535;

    public static final int MAX_COLUMN = 256;

    private String filepath = null;

    private Workbook book = null;

    private Sheet sheet = null;

    private FileInputStream inStream = null;

    public ExcelFile(String path) {
        this.filepath = path;
    }

    /**
     * シート選択.
     *
     * @param no シートNo
     */
    public boolean selectSheet(int no) {
        boolean result = false;
        if (book != null) {
            try {
                sheet = book.getSheetAt(no);
                result = true;

            } catch (Exception e) {
                e.printStackTrace();

            }
        }
        return result;
    }

    /**
     * シート選択.
     *
     * @param name シート名
     */
    public boolean selectSheet(String name) {
        boolean result = false;
        if (book != null) {
            try {
                sheet = book.getSheet(name);
                result = true;

            } catch (Exception e) {
                e.printStackTrace();

            }
        }
        return result;
    }

    /**
     * Excelセル内容取得(配置場所指定).
     *
     */
    public String getValue(int r, int c) {
        String result = null;

        if (r < 0 || r > MAX_ROW || c < 0 || c > MAX_COLUMN) {
            return result;
        }

        try {
            if (sheet != null) {
                final Cell cell = sheet.getRow(r).getCell(c);
                result = getValueFromCell(cell);
            }

        } catch (Exception e) {
            e.printStackTrace();

        }

        return result;
    }

    /**
     * Excelセル内容取得(セル情報指定).
     *
     */
    private String getValueFromCell(Cell cell) {
        String result = null;

        if (CellType.NUMERIC == cell.getCellType()) {
            if (DateUtil.isCellDateFormatted(cell)) {
                result = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
                        .format(cell.getDateCellValue());
            } else {
                result = Double.toString(cell.getNumericCellValue());
            }
        } else if (CellType.STRING == cell.getCellType()) {
            result = cell.toString();
        }

        return result;
    }

    /**
     * Excel情報セット.
     *
     */
    public boolean putValue(int r, int c, String value) {
        if (r < 0 || r > MAX_ROW || c < 0 || c > MAX_COLUMN) {
            return false;
        }

        boolean result = true;

        try {
            if (sheet != null) {
                Row row = sheet.getRow(r);
                if (row == null) {
                    row = sheet.createRow(r);
                }
                Cell cell = row.getCell(c);
                if (cell == null) {
                    cell = row.createCell(c);
                }
                cell.setCellValue(value);
            }

        } catch (Exception e) {
            e.printStackTrace();
            result = false;

        }

        return result;
    }

    /**
     * Excelセル配置取得.
     *
     */
    public String[] getRow(int r, int toColumn) {
        String[] result = null;

        if (r < 0 || r > MAX_ROW || toColumn < 1 || toColumn > MAX_COLUMN) {
            return result;
        }

        if (sheet != null) {
            List<String> list = new ArrayList<String>();

            for (int i = 0; i < toColumn; i++) {
                list.add(getValue(r, i));
            }

            result = list.toArray(new String[0]);
        }

        return result;
    }

    /**
     * Excelファイルオープン.
     *
     */
    public boolean open() {
        boolean result = true;

        try {
            inStream = new FileInputStream(filepath);
            book = new XSSFWorkbook(inStream);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            result = false;

        } catch (IOException e) {
            e.printStackTrace();
            result = false;

        }

        return result;
    }

    /**
     * Excelファイル保存.
     *
     */
    public boolean save() {
        if (book == null) {
            book = new XSSFWorkbook();
            book.createSheet("Sheet1");
        }

        boolean result = true;

        final File file = new File(filepath);
        if (file.exists()) {
            file.delete();
        }

        FileOutputStream outStream = null;

        try {
            outStream = new FileOutputStream(filepath);
            book.write(outStream);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            result = false;

        } catch (IOException e) {
            e.printStackTrace();
            result = false;

        } finally {
            try {
                outStream.close();

            } catch (IOException e) {
                e.printStackTrace();
                result = false;

            }

        }

        return result;
    }

    public boolean saveAs(String path) {
        this.filepath = path;
        return save();
    }

    /**
     * Excelファイルクローズ.
     *
     */
    public void close() {
        this.sheet = null;
        this.book = null;

        try {
            if (inStream != null) {
                inStream.close();
                inStream = null;
            }

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

}
