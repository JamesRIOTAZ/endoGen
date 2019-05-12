package ClassicEndoGen;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ClassicEndorsement {
	WriteFile data;
	private ArrayList<String> endoPara = new ArrayList<>();

	private String lineSpace;
	private String paragraphSpace;
	private String code;
	private String wording;
	private String title;
	private String lastLineHeight;
	private String pageHeight;
	private String heightCalc;
	private String currentHeightCalc;
	private String boldFont;
	private String normalFont;
	
	private final String SPACE = "    ";
	private final String TEXT_CODE = "TEXT\"";
	private final String MOVE_POS = "MRP 0.00, ";
	
	private int lineCharNum;
	
	private double paraSpaceNum;
	private double lineSpaceNum;
	private double endoHeight;

	private boolean lastEndoLine = false;

	//Constructor methods setting endorsement values
	public ClassicEndorsement(String boldFont, String normalFont, String currentHeightCalc, String lineSpace, String paragraphSpace, String lastLineHeight, String pageHeight, String heightCalc, String lineCharNum){
		this.boldFont = boldFont;
		this.normalFont = normalFont;
		this.currentHeightCalc = currentHeightCalc;
		this.lineSpace = (lineSpace + ";");
		this.paragraphSpace = (paragraphSpace + ";");
		this.lastLineHeight = (lastLineHeight + ";");
		this.endoHeight = Double.parseDouble(lastLineHeight);
		this.pageHeight = pageHeight;
		this.heightCalc = heightCalc;
		this.lineCharNum = Integer.parseInt(lineCharNum);
		this.lineSpaceNum = Double.parseDouble(lineSpace);
		this.paraSpaceNum = Double.parseDouble(paragraphSpace);
	}

	//set code, title, wording and file details for endorsement
	public void setDetails(String code, String title, String wording, WriteFile data) {
		this.code = code;
		this.wording = wording;
		this.title = title;
		this.data = data;
	}

	//add a endorsement
	public void add(){
		try {
			writeLogic();
		} catch (Exception e) {
			System.out.println("Error writing classic endo - <"+code+">");
			e.printStackTrace();
		}
	}

	//take in double and return in the correct format
	double RoundTo2Decimals(double val) {
		DecimalFormat df2 = new DecimalFormat(".00");
		return Double.valueOf(df2.format(val)).doubleValue();
	}

	//write endorsement logic
	public void writeLogic() throws Exception {
		writeParagraph();
		data.writeLineToFile(".CALC #1=0", true);
		data.writeLineToFile(".SCHLOOP #5,4", true);
		data.writeLineToFile(".Q S.ECODE=" + code, true);
		data.writeLineToFile(".IFT", true);
		data.writeLineToFile(".CALC #1=1", true);
		data.writeLineToFile(".IFTF", true);
		data.writeLineToFile("", true);
		data.writeLineToFile(".Q #1=1", true);
		data.writeLineToFile(".IFF", true);
		data.writeLineToFile(".GOTO NEXTEND", true);
		data.writeLineToFile(".IFTF", true);
		data.writeLineToFile("", true);
		data.writeLineToFile(".CALC " + currentHeightCalc + "=" + currentHeightCalc + " + "
				+ String.format("%.2f", new Object[] { Double.valueOf(endoHeight) }), true);
		data.writeLineToFile("", true);
		data.writeLineToFile(".Q " + currentHeightCalc + " > {" + heightCalc + "}", true);
		data.writeLineToFile(".IFT", true);
		data.writeLineToFile(SPACE + "CALL NEWP;", true);
		data.writeLineToFile(".CALC " + currentHeightCalc + "=" + RoundTo2Decimals(endoHeight), true);
		data.writeLineToFile(".CALC " + heightCalc + "=" + pageHeight, true);
		data.writeLineToFile(".IFTF", true);
		data.writeLineToFile("", true);
		data.writeLineToFile(SPACE + boldFont, true);
		data.writeLineToFile(formatTextLine(title, "\"; " + MOVE_POS + paragraphSpace), true);
		if (!Character.toString(wording.charAt(0)).equals("<")) {
			data.writeLineToFile(SPACE + normalFont, true);
		}
		for (int i = 0; i < endoPara.size(); i++) {
			data.writeLineToFile((String) endoPara.get(i), true);
		}
		data.writeLineToFile("", true);
		data.writeLineToFile(".LABEL NEXTEND", true);
	}

	//take in a string and add spacing and syntax
	private String formatTextLine(String text, String lineEnd) {
		return SPACE + TEXT_CODE + text + lineEnd;
	}

	//write a paragraph out with correct syntax
	private void writeParagraph(){
		String[] paragraphs = wording.split("\\r?\\n");
		for (int x = 0; x < paragraphs.length; x++) {
			lastEndoLine = false;
			if (x + 1 == paragraphs.length) {
				lastEndoLine = true;
			}
			writeEndoText(paragraphs[x]);
		}
	}

	//Split a paragraph into lines based on the line size set by the user
	public ArrayList<String> splitParaIntoLines(String rawPara) {
		rawPara = rawPara.trim().replaceAll(" +", " ");
		String[] words = rawPara.split(" ");
		String line = "";
		int charCount = 0;
		ArrayList<String> displayLine = new ArrayList<>();

		for (int x = 0; x < words.length; x++) {
			charCount = charCount + words[x].length() + 1;

			if (charCount > lineCharNum) {
				displayLine.add(line);
				line = "";
				charCount = 0;
			}
			line = line + words[x] + " ";
		}
		displayLine.add(line);
		return displayLine;
	}

	//take in a paragraph and add syntax and spacing before writing to a file
	private void writeEndoText(String rawPara){
		Boolean isBullet = Boolean.valueOf(false);

		Character.toString(rawPara.charAt(0)).equals("<");

		if(Character.toString(rawPara.charAt(0)).equals("•") || Character.toString(rawPara.charAt(0)).equals("·")){
			isBullet = Boolean.valueOf(true);
			endoPara.add(SPACE + "MRP 0.15, 0.00; FONT 11; TEXT\".\"; FONT 13;");
			endoPara.add("    MRP 0.15, 0.00;");
			String removeBullet = "";
			for (int y = 1; y < rawPara.length(); y++) {
				if ((y != 1) || (!Character.toString(rawPara.charAt(y)).equals(" "))) {
					removeBullet = removeBullet + Character.toString(rawPara.charAt(y));
				}
			}
			rawPara = removeBullet;
		}

		ArrayList<String> displayLine = splitParaIntoLines(rawPara);
		String tempMove = lineSpace;

		for (int i = 0; i < displayLine.size(); i++) {
			String outputLine = SPACE;
			String workingLine = (String) displayLine.get(i);
			Boolean unbold = Boolean.valueOf(false);
			Boolean rppNeed = Boolean.valueOf(false);

			if ((workingLine.contains("{")) || (workingLine.contains("}"))) {
				endoPara.add(SPACE + "SCP;");
				rppNeed = Boolean.valueOf(true);
			}

			String formatLine = "";
			for (int x = 0; x < workingLine.length(); x++) {
				if (Character.toString(workingLine.charAt(x)).equals("{")) {
					if (x == 0) {
						endoPara.add(SPACE + boldFont);
					} else {
						formatLine = formatLine + "\",E; " + boldFont + " @    TEXT\"";
					}
				} else if (Character.toString(workingLine.charAt(x)).equals("}")) {
					if (x == workingLine.length() - 2) {
						unbold = Boolean.valueOf(true);
					} else {
						formatLine = formatLine + "\",E; " + normalFont + " @    TEXT\"";
					}

				} else if ((x != workingLine.length() - 1) || (!Character.toString(workingLine.charAt(x)).equals(" "))) {
					formatLine = formatLine + Character.toString(workingLine.charAt(x));
				}
			}

			workingLine = formatLine;

			if (Character.toString(workingLine.charAt(workingLine.length() - 1)).equals(" ")) {
				workingLine.replace(workingLine.substring(workingLine.length() - 1), "");
			}

			String tempHorMove = "@    " + MOVE_POS;

			if (i + 1 == displayLine.size()) {
				if (lastEndoLine) {
					tempMove = lastLineHeight;
				} else {
					tempMove = paragraphSpace;
				}
				endoHeight += paraSpaceNum;

				if (isBullet.booleanValue()) {
					tempHorMove = "@    MRP-0.30, ";
				}
			} else {
				endoHeight += lineSpaceNum;
			}

			if (rppNeed.booleanValue()) {
				outputLine = outputLine + "TEXT\"" + workingLine + "\"; RPP; " + tempHorMove + tempMove;
			} else {
				outputLine = outputLine + "TEXT\"" + workingLine + "\"; " + tempHorMove + tempMove;
			}

			if (unbold.booleanValue()) {
				outputLine = outputLine + " " + normalFont;
			}

			String[] eachLine = outputLine.split("@");
			for (String result : eachLine) {
				endoPara.add(result);
			}
		}
	}
}
