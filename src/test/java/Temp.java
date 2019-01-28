import com.jackson.translation.L;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Create by: Jackson
 */
public class Temp {


    @Test
    public void show() throws IOException {
        InputStream is = new FileInputStream("/Users/jiaoyubing/work_space/localworkspace/translation/source/10.xlsx");

        XSSFWorkbook excel = new XSSFWorkbook(is);

        for (int numSheet = 0; numSheet < excel.getNumberOfSheets(); numSheet++) {
            XSSFSheet sheet = excel.getSheetAt(numSheet);

            XSSFRow row = sheet.getRow(0);
            XSSFCell cell = row.getCell(0);
            String stringCellValue = cell.getStringCellValue();
            L.console(stringCellValue);
        }


    }
}
