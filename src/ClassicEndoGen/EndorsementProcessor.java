package ClassicEndoGen;

import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;

/*
 * Class to read in Endorsements from a spreadsheet, create endorsement logic and write out to a file
 */
public class EndorsementProcessor{

	private final String BREAKLINE = System.getProperty("line.separator");
	
	private boolean doClassic;
	private boolean doPrecis;
	
	private String tempCode;
	private String tempTitle;
	private String tempWording;

	private String precisScheme;
	private String precisPolType;
	private String precisBrokerCode1;
	private String precisBrokerCode2;
	private String precisAffinty;
	private String precisStartDate;
	private String precisEndDate;

	private String classicBoldFont;
	private String classicNormalFont;
	private String classicCurrentHeight;
	private String classicLineSpace;
	private String classicParaSpace;
	private String classicMargin;
	private String classicPageHeight;
	private String classicTotalHeight;
	private String classicLineSize;

	// read in precis endo configs and save to instance variables
	public void setPrecisValues(boolean doPrecis, String precisScheme, String precisPolType, String precisBrokerCode1, String precisBrokerCode2, String precisAffinty, String precisStartDate, String precisEndDate) {
		this.doPrecis = doPrecis;
		this.precisScheme = precisScheme;
		this.precisPolType = precisPolType;
		this.precisBrokerCode1 = precisBrokerCode1;
		this.precisBrokerCode2 = precisBrokerCode2;
		this.precisAffinty = precisAffinty;
		this.precisStartDate = precisStartDate;
		this.precisEndDate = precisEndDate;
	}

	// read in classic endo configs and save to instance variables
	public void setClassicValues(boolean doClassic, String classicBoldFont, String classicNormalFont, String classicCurrentHeight, String classicLineSpace, String classicParaSpace, String classicMargin, String classicPageHeight, String classicTotalHeight, String classicLineSize) {
		this.doClassic = doClassic;
		this.classicBoldFont = classicBoldFont;
		this.classicNormalFont = classicNormalFont;
		this.classicCurrentHeight = classicCurrentHeight;
		this.classicLineSpace = classicLineSpace;
		this.classicParaSpace = classicParaSpace;
		this.classicMargin = classicMargin;
		this.classicPageHeight = classicPageHeight;
		this.classicTotalHeight = classicTotalHeight;
		this.classicLineSize = classicLineSize;
	}

	//read in spreadsheet to a list of HSSFCells and pass to showExcelData for formating
	public void run() {
		try {
			//Read in spreadsheet and save each endorsement as a List of HSSFCells
			ReadFile inputData = new ReadFile(); 
			List<List<HSSFCell>> rawData = inputData.getData();
			processExelData(rawData, inputData);
		} catch (Exception e) {
			System.out.println("Error reading in file");
			e.printStackTrace();
		}
	}

	//take list of cells and transform into endorsement objects
	private void processExelData(List<List<HSSFCell>> sheetData, ReadFile inputData){
		try {
			//text files to save the output
			WriteFile classicFile = new WriteFile("c:/endoGen/CLASSIC.txt", true);
			WriteFile precisFile = new WriteFile("c:/endoGen/PRECIS.txt", true);

			for (int i = 0; i < sheetData.size(); i++) { 
				setTempData((List<HSSFCell>) sheetData.get(i), inputData); //Save data from cells into the temp data strings	
				createEndos(classicFile, precisFile); //used temp data to create Endorsements
			}
		}catch(Exception e) {
			System.out.println("Error writing out file");
			e.printStackTrace();
		}
	}

	//read in String and surround bold words with { } tags
	public String formatString(HSSFRichTextString rawString, ReadFile inputData) {
		String formated = "";
		boolean isBold = false; //used to set it bolding is already on
		for (int x = 0; x < rawString.length(); x++) { 
			boolean fontBold = inputData.checkBold(rawString, x);//is the character bold

			if ((fontBold) && (!isBold)) { //if font is bold and we are not already in a bold tag
				formated += "{";
				isBold = true;
			}
			if ((!fontBold) && (isBold)) { //if font is not bold and we are in a bold tag
				formated += "}";
				isBold = false;
			}
			formated = formated + rawString.toString().charAt(x); //build string
			if ((rawString.length() - 1 == x) && (isBold)) { //if still in bold at end of line add closing tag
				formated += "}";
				isBold = false;
			}
		}
		return formated;
	}

	//save values from cells into Strings
	private void setTempData(List<HSSFCell> cellList, ReadFile inputData){
		for (int j = 0; j < cellList.size(); j++) { 
			HSSFCell cell = (HSSFCell) cellList.get(j);
			if (j == 0) { //cell 0 will be the endo code
				cell.setCellType(1);
				tempCode = cell.toString();
			} else if (j == 1) {//call 1 will be endo title
				tempTitle = cell.toString();
			} else if (j == 2) {//cell 2 will be first paragraph
				HSSFRichTextString tempRichWording = cell.getRichStringCellValue();
				tempWording = formatString(tempRichWording, inputData);
			} else if (j > 2) {//cells greater than 2 will need a break line added
				HSSFRichTextString tempRichWording = cell.getRichStringCellValue();
				tempWording += BREAKLINE + formatString(tempRichWording, inputData);
			}
		}
	}
	
	//create endorsements
	private void createEndos(WriteFile classicFile, WriteFile precisFile) throws Exception{			
		if ((tempCode != null) && (!tempCode.isEmpty())) {
			if (doClassic) {
				endorsement classicEndo = new endorsement();
				classicEndo.setValues(classicBoldFont, classicNormalFont, classicCurrentHeight, classicLineSpace, classicParaSpace, classicMargin, classicPageHeight, classicTotalHeight, classicLineSize);
				classicEndo.setDetails(tempCode, tempTitle, tempWording, classicFile);
				classicEndo.add();
			}
			if (doPrecis) {
				precisEndorsement precisEndo = new precisEndorsement();
				precisEndo.setValues(precisScheme, precisPolType, precisBrokerCode1, precisBrokerCode2, precisAffinty, precisStartDate, precisEndDate);
				precisEndo.setDetails(tempCode, tempTitle, tempWording, precisFile);
				precisEndo.add();
			}
		}
	}

}