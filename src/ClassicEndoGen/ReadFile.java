package ClassicEndoGen;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ReadFile {
	HSSFWorkbook workbook;

	public ReadFile() {
	}

	public List getData() throws Exception {
		String filename = "c:/endoGen/endorsement.xls";

		List sheetData = new ArrayList();

		FileInputStream fis = null;

		try {
			fis = new FileInputStream(filename);

			workbook = new HSSFWorkbook(fis);

			HSSFSheet sheet = workbook.getSheetAt(0);

			List data = new ArrayList();
			Iterator rows = sheet.rowIterator();
			while (rows.hasNext()) {
				HSSFRow row = (HSSFRow) rows.next();
				Iterator cells = row.cellIterator();

				HSSFCell cell = row.getCell(0);

				if ((hasText(cell)) && (cell.toString() == "END")) {
					sheetData.add(data);
				} else {
					if (hasText(cell)) {
						if (row.getRowNum() != 0) {
							sheetData.add(data);
							data = new ArrayList();
						}
						data.add(cell);
						HSSFCell cell1 = row.getCell(1);

						data.add(cell1);
					}
					HSSFCell cell2 = row.getCell(2);
					if (hasText(cell2)) {
						HSSFRichTextString rts = cell2.getRichStringCellValue();

						data.add(cell2);
					}

				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				fis.close();
			}
		}

		return sheetData;
	}

	private boolean hasText(HSSFCell cell) {
		if (cell == null) {
			return false;
		}

		if (cell.getCellType() == 3) {
			return false;
		}

		return true;
	}

	public boolean checkBold(HSSFRichTextString rts, int index) {
		HSSFFont font = workbook.getFontAt(rts.getFontAtIndex(index));
		if (font.getBoldweight() == 700) {
			return true;
		}
		return false;
	}
}