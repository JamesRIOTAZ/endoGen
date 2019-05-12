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
import org.apache.poi.ss.usermodel.Row;

public class ReadFile {
	HSSFWorkbook workbook;

	//create a list of HSSFCells
	public List<List<HSSFCell>> getData(){
		String filename = "c:/endoGen/endorsement.xls"; //open source file
		List<List<HSSFCell>> sheetData = new ArrayList<>();
		FileInputStream fis = null;

		try {
			fis = new FileInputStream(filename);
			workbook = new HSSFWorkbook(fis);//get workbook
			HSSFSheet sheet = workbook.getSheetAt(0);//get sheet from workbook
			List<HSSFCell> cellList = new ArrayList<>(); //Arraylist will hold cells for endorsement
			Iterator<Row> rows = sheet.rowIterator();
			
			while (rows.hasNext()) {
				HSSFRow row = (HSSFRow) rows.next();
				HSSFCell cell = row.getCell(0);

				if ((hasText(cell)) && (cell.toString() == "END")) { //look for terminator 
					sheetData.add(cellList);
				} else {
					if (hasText(cell)) {
						if (row.getRowNum() != 0) {
							sheetData.add(cellList);
							cellList = new ArrayList<>();
						}
						cellList.add(cell);
						HSSFCell cell1 = row.getCell(1);
						cellList.add(cell1);
					}
					HSSFCell cell2 = row.getCell(2);
					if (hasText(cell2)) {
						cellList.add(cell2);
					}
				}
			}
			if (fis != null) {
				fis.close();
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		return sheetData;
	}

	//Check if a given Cell has text
	private boolean hasText(HSSFCell cell) {
		if((cell == null) || (cell.getCellType() == 3))
			return false;
		else
			return true;
	}
	//Check if a given rts if bold
	public boolean checkBold(HSSFRichTextString rts, int index) {
		HSSFFont font = workbook.getFontAt(rts.getFontAtIndex(index));
		if (font.getBoldweight() == 700)
			return true;
		else
			return false;
	}
}