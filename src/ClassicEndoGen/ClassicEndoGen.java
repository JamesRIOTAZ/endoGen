package ClassicEndoGen;

import java.io.PrintStream;
import java.util.Date;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;

public class ClassicEndoGen {
	private boolean doClassic = false;
	private boolean doPrecis = false;

	private String precisScheme = "";
	private String precisPolType = "";
	private String precisBrokerCode1 = "";
	private String precisBrokerCode2 = "";
	private String precisAffinty = "";
	private String precisStartDate = "";
	private String precisEndDate = "";
	private String classicBoldFont;
	private String classicNormalFont;
	private String classicCurrentHeight;
	private String classicLineSpace;
	private String classicParaSpace;
	private String classicMargin;
	private String classicPageHeight;
	private String classicTotalHeight;
	private String classicLineSize;

	public ClassicEndoGen() {
	}

	public void setValues(boolean gui_Classic, boolean gui_Precis) {
		doClassic = gui_Classic;
		doPrecis = gui_Precis;
	}

	public void setPrecisValues(boolean gui_Precis, String gui_Scheme, String gui_poltype, String gui_brokerCode1,
			String gui_brokerCode2, String gui_affinity, String gui_StartDate, String gui_endDate) {
		doPrecis = gui_Precis;
		precisScheme = gui_Scheme;
		precisPolType = gui_poltype;
		precisBrokerCode1 = gui_brokerCode1;
		precisBrokerCode2 = gui_brokerCode2;
		precisAffinty = gui_affinity;
		precisStartDate = gui_StartDate;
		precisEndDate = gui_endDate;
	}

	public void setClassicValues(boolean gui_Classic, String Gui_Bold_Font, String Gui_Normal_Font,
			String Gui_Current_Height, String Gui_LineSpace, String Gui_paragraphSpace, String Gui_Margin,
			String Gui_Page_Height, String Gui_Total_Height, String Gui_Classic_Line_Size) {
		doPrecis = gui_Classic;
		classicBoldFont = Gui_Bold_Font;
		classicNormalFont = Gui_Normal_Font;
		classicCurrentHeight = Gui_Current_Height;
		classicLineSpace = Gui_LineSpace;
		classicParaSpace = Gui_paragraphSpace;
		classicMargin = Gui_Margin;
		classicPageHeight = Gui_Page_Height;
		classicTotalHeight = Gui_Total_Height;
		classicLineSize = Gui_Classic_Line_Size;
	}

	public void run() throws Exception {
		ReadFile inputData = new ReadFile();

		List rawData = inputData.getData();

		System.out.println(rawData.size());
		showExelData(rawData, inputData);
	}

	private void showExelData(List sheetData, ReadFile inputData) throws Exception {
		Date today = new Date();

		WriteFile data = new WriteFile("c:/endoGen/CLASSIC.txt", true);
		WriteFile Pdata = new WriteFile("c:/endoGen/PRECIS.txt", true);

		String tempCode = "";
		String tempTitle = "";
		String tempWording = "";
		String tempPrecisWording = "";
		String breakline = System.getProperty("line.separator");

		System.out.println(sheetData.size());

		for (int i = 0; i < sheetData.size(); i++) {
			List list = (List) sheetData.get(i);
			for (int j = 0; j < list.size(); j++) {
				HSSFCell cell = (HSSFCell) list.get(j);
				if (j == 0) {
					cell.setCellType(1);
					tempCode = cell.toString();
					System.out.println(tempCode);
				} else if (j == 1) {
					tempTitle = cell.toString();
					System.out.println(tempTitle);
				} else if (j == 2) {
					HSSFRichTextString tempRichWording = cell.getRichStringCellValue();
					tempWording = formatString(tempRichWording, inputData);
					tempPrecisWording = formatPrecisString(tempRichWording, inputData);
					System.out.println(tempPrecisWording);
				} else if (j > 2) {
					HSSFRichTextString tempRichWording = cell.getRichStringCellValue();
					tempWording = tempWording + breakline + formatString(tempRichWording, inputData);
					tempPrecisWording = tempPrecisWording + breakline + formatPrecisString(tempRichWording, inputData);
					System.out.println(tempPrecisWording);
				}
			}

			if ((tempCode != null) && (!tempCode.isEmpty())) {
				if (doClassic) {
					endorsement testEndo = new endorsement();

					testEndo.setValues(classicBoldFont, classicNormalFont, classicCurrentHeight, classicLineSpace,
							classicParaSpace, classicMargin, classicPageHeight, classicTotalHeight, classicLineSize);
					testEndo.setDetails(tempCode, tempTitle, tempWording, data);
					testEndo.add();
				}
				if (doPrecis) {
					precisEndorsement precisEndo = new precisEndorsement();

					precisEndo.setValues(precisScheme, precisPolType, precisBrokerCode1, precisBrokerCode2,
							precisAffinty, precisStartDate, precisEndDate);
					precisEndo.setDetails(tempCode, tempTitle, tempWording, Pdata);
					precisEndo.add();
				}
			}
		}
	}

	public String formatPrecisString(HSSFRichTextString rawString, ReadFile inputData) {
		String formated = "";
		boolean isBold = false;
		for (int x = 0; x < rawString.length(); x++) {
			boolean fontBold = inputData.checkBold(rawString, x);

			if ((fontBold) && (!isBold)) {
				formated = formated + "{";
				isBold = true;
			}
			if ((!fontBold) && (isBold)) {
				formated = formated + "}";
				isBold = false;
			}
			formated = formated + rawString.toString().charAt(x);
		}

		return formated;
	}

	public String formatString(HSSFRichTextString rawString, ReadFile inputData) {
		String formated = "";
		boolean isBold = false;
		for (int x = 0; x < rawString.length(); x++) {
			boolean fontBold = inputData.checkBold(rawString, x);

			if ((fontBold) && (!isBold)) {
				formated = formated + "{";
				isBold = true;
			}
			if ((!fontBold) && (isBold)) {
				formated = formated + "}";
				isBold = false;
			}

			formated = formated + rawString.toString().charAt(x);

			if ((rawString.length() - 1 == x) && (isBold)) {
				formated = formated + "}";
				isBold = false;
			}
		}
		return formated;
	}

	public boolean inBold(int Font) {
		if ((Font == 26) || (Font == 23)) {
			return true;
		}

		return false;
	}
}