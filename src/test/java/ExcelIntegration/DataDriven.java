package ExcelIntegration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.formula.functions.Rows;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataDriven {

	public ArrayList<String> getData(String testCaseName, String sheetName) throws IOException {
		ArrayList<String> aList = new ArrayList<String>();

		FileInputStream fis = new FileInputStream("C:\\Users\\USER\\OneDrive\\Documents\\demoData_RS.xlsx");

		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		int sheets = workbook.getNumberOfSheets();

		for (int i = 0; i < sheets; i++) {

			if (workbook.getSheetName(i).equalsIgnoreCase(sheetName)) {
				XSSFSheet workSheetString = workbook.getSheetAt(i);

				// Identify the Test Case Column by Scanning the entire 1st Row

				Iterator<Row> rows = workSheetString.iterator();
				Row firstROwString = rows.next();

				Iterator<Cell> cellIterator = firstROwString.cellIterator();

				int k = 0;
				int column = 0;

				while (cellIterator.hasNext()) {
					Cell CellValue = cellIterator.next();

					if (CellValue.getStringCellValue().equalsIgnoreCase("Test Cases")) {

						column = k;

					}

					k++;

				}

				System.out.println(column);

				// once column is identified then scan the entire column to identify purchase
				// test case row

				while (rows.hasNext()) {

					Row rows2 = rows.next();
					if (rows2.getCell(column).getStringCellValue().equalsIgnoreCase(testCaseName)) {

						Iterator<Cell> cellIterator2 = rows2.cellIterator();

						while (cellIterator2.hasNext()) {

							Cell cell = cellIterator2.next();

							if (cell.getCellType() == CellType.STRING) {

								String CellValue2 = cell.getStringCellValue();

								//System.out.println(CellValue2);

								aList.add(CellValue2);

							}

							else {

								String CellValue3 = NumberToTextConverter
										.toText(cell.getNumericCellValue());
								
								//System.out.println(CellValue3);
								
								aList.add(CellValue3);
							}

						}

					}

					// return aList;

				}
			}
		}
		return aList;

	}

}
