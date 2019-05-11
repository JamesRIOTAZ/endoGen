package ClassicEndoGen;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class endorsement {
	WriteFile data;
	private ArrayList<String> endoPara = new ArrayList();

	private String lineSpace;
	private String paragraphSpace;
	private String code;
	private String wording;
	private String title;
	private String space;
	private String lastLineHeight;
	private double endoHeight;
	private String pageHeight;
	private String heightCalc;
	private String currentHeightCalc;
	private String boldFont;
	private String normalFont;
	private int lineCharNum;
	private double paraSpaceNum;
	private double lineSpaceNum;
	private String textCodeStart = "TEXT\"";
	private String codeEnd = "\";";
	private String codeEndSavePos = "\";,E";
	private String movePos = "MRP 0.00, ";
	private boolean lastEndoLine = false;

	DecimalFormat df = new DecimalFormat("#.##");

	public endorsement() {
	}

	public void setValues(String input_BoldFont, String input_NormalFont, String input_CurrentHeight,
			String input_LineSpace, String input_ParaSpace, String input_Margin, String input_PageHeight,
			String input_TotalHeight, String input_LineSize) {
		boldFont = input_BoldFont;
		normalFont = input_NormalFont;
		currentHeightCalc = input_CurrentHeight;
		lineSpace = (input_LineSpace + ";");
		paragraphSpace = (input_ParaSpace + ";");
		lastLineHeight = (input_Margin + ";");
		endoHeight = Double.parseDouble(input_Margin);
		pageHeight = input_PageHeight;
		heightCalc = input_TotalHeight;
		lineCharNum = Integer.parseInt(input_LineSize);

		lineSpaceNum = Double.parseDouble(input_LineSpace);
		paraSpaceNum = Double.parseDouble(input_ParaSpace);
	}

	public void setDetails(String input_code, String input_title, String input_wording, WriteFile input_data) {
		code = input_code;
		wording = input_wording;
		title = input_title;
		space = "    ";
		data = input_data;
	}

	public void add() throws Exception {
		writeLogic();
	}

	double RoundTo2Decimals(double val) {
		DecimalFormat df2 = new DecimalFormat(".00");
		return Double.valueOf(df2.format(val)).doubleValue();
	}

	public void writeLogic() throws Exception {
		writeParagraph();

		data.writeToFile(".CALC #1=0");
		data.writeToFile(".SCHLOOP #5,4");
		data.writeToFile(".Q S.ECODE=" + code);
		data.writeToFile(".IFT");
		data.writeToFile(".CALC #1=1");
		data.writeToFile(".IFTF");
		data.writeToFile("");
		data.writeToFile(".Q #1=1");
		data.writeToFile(".IFF");
		data.writeToFile(".GOTO NEXTEND");
		data.writeToFile(".IFTF");
		data.writeToFile("");
		data.writeToFile(".CALC " + currentHeightCalc + "=" + currentHeightCalc + " + "
				+ String.format("%.2f", new Object[] { Double.valueOf(endoHeight) }));
		data.writeToFile("");
		data.writeToFile(".Q " + currentHeightCalc + " > {" + heightCalc + "}");
		data.writeToFile(".IFT");
		data.writeToFile(space + "CALL NEWP;");
		data.writeToFile(".CALC " + currentHeightCalc + "=" + RoundTo2Decimals(endoHeight));
		data.writeToFile(".CALC " + heightCalc + "=" + pageHeight);
		data.writeToFile(".IFTF");
		data.writeToFile("");
		data.writeToFile(space + boldFont);
		data.writeToFile(writeText(title, "\"; " + movePos + paragraphSpace));

		if (!Character.toString(wording.charAt(0)).equals("<")) {

			data.writeToFile(space + normalFont);
		}
		for (int i = 0; i < endoPara.size(); i++) {
			data.writeToFile((String) endoPara.get(i));
		}
		data.writeToFile("");
		data.writeToFile(".LABEL NEXTEND");
	}

	private String writeText(String text, String lineEnd) {
		return space + textCodeStart + text + lineEnd;
	}

	private void writeParagraph() throws Exception {
		String[] paragraphs = wording.split("\\r?\\n");
		for (int x = 0; x < paragraphs.length; x++) {
			lastEndoLine = false;
			if (x + 1 == paragraphs.length) {
				lastEndoLine = true;
			}
			writeEndoText(paragraphs[x]);
		}
	}

	public ArrayList getDisplayLines(String rawPara) {

		rawPara = rawPara.trim().replaceAll(" +", " ");
		String[] words = rawPara.split(" ");
		String line = "";
		int charCount = 0;
		
		ArrayList<String> displayLine = new ArrayList();

		for (int x = 0; x < words.length; x++) {
		//	boolean empty = false;
		//	if(words[x]==null)
		//		empty = true;
		//	System.out.println("word split = <"+words[x]+"> "+empty);
			charCount = charCount + words[x].length() + 1;

			if (charCount > lineCharNum) {
			//	System.out.println("adding to displayLine array 1 <"+line+">");
				displayLine.add(line);
				line = "";
				charCount = 0;
			}
			line = line + words[x] + " ";
		}
		//System.out.println("adding to displayLine array 2 <"+line+">");
		displayLine.add(line);
		
		return displayLine;
	}

	// Method to take in a Paragraph and output the presribe code
	private void writeEndoText2(String rawPara) throws Exception{
	//	System.out.println("rawPara = "+rawPara);
		Boolean isBullet = false; //used to set if the paragraph is a bullet point
		
		for(int y = 0; y < rawPara.length(); y++) {
		//	Character tempCher = rawPara.charAt(y);
		//	tempCher.charValue()
		//	System.out.println("Char = <"+rawPara.charAt(y)+"> whitespace = "+Character.isWhitespace(rawPara.charAt(y)));
			
			//Character.
		}
		
		
		
	    //Check if first line is bold
		//if(Character.toString(rawPara.charAt(0)).equals("<")){
		//}
		
		//check for bullet character at the start of a paragraph
		if(Character.toString(rawPara.charAt(0)).equals("•") || Character.toString(rawPara.charAt(0)).equals("·")){
			isBullet = true;
			endoPara.add(space+"MRP 0.15, 0.00; FONT 11; TEXT\".\"; FONT 13;"); 
			endoPara.add("    MRP 0.15, 0.00;");
			String removeBullet="";
			for(int y = 1; y < rawPara.length(); y++) {
				if(y==1 && (Character.toString(rawPara.charAt(y)).equals(" "))){
					
				}
				else{
				removeBullet=removeBullet+Character.toString(rawPara.charAt(y));
				}
			}
			
			rawPara=removeBullet;
			
		}
		ArrayList<String> displayLine = getDisplayLines(rawPara);
		String tempMove = lineSpace; 
		
		
		//For each display line
		for (int i = 0; i < displayLine.size(); i++) {
			String outputLine = space; //code line to be output
			String workingLine = displayLine.get(i); //get the wording
			Boolean unbold = false; //used to add a font change to the end of a line if the last word is bold
			Boolean rppNeed = false; //used to add a RRP; to the end of a line when needed
			
		
			//check if the line has a font weight change start with a SCP;
			if(workingLine.contains("{") || workingLine.contains("}")){
				endoPara.add(space+"SCP;");
				rppNeed=true;
			}
			
			//Add code for bold/unbold;
			String formatLine = "";
		    for(int x = 0; x < workingLine.length(); x++) {

		    	 if(Character.toString(workingLine.charAt(x)).equals("{")){
		    		 //check if its the first word in the line
		    		 if(x==0){//check if its the first word in the line
		    			 endoPara.add(space+boldFont);
		    		 }
		    		 else{
		    			 formatLine=formatLine+"\",E; "+boldFont+" @    TEXT\"";
		    		 }
		    	 }
		    	 else if(Character.toString(workingLine.charAt(x)).equals("}")){
		    		 if(x==(workingLine.length()-2)){
		    			 unbold=true;
		    		 }
		    		 else{
		    			 formatLine=formatLine+"\",E; "+normalFont+" @    TEXT\"";
		    		 }
		    	 }
		    	 else{
		    		 if(x==workingLine.length()-1 && Character.toString(workingLine.charAt(x)).equals(" ")){
		    			 //don't add space at end of a line
		    		 }
		    		 else if((int)workingLine.charAt(x)==160){
		    			 //don't add white space
		    		 }
		    		 else{
		    			 formatLine=formatLine+Character.toString(workingLine.charAt(x));
		    		 }
		    	 }
		    }
		    

		    workingLine=formatLine;
		    
		    if(Character.toString(workingLine.charAt(workingLine.length()-1)).equals(" ")){
		    	
		    	workingLine.replace(workingLine.substring(workingLine.length()-1), "");
		    }
		    
		    
		    String tempHorMove="@    "+movePos;
			//check if last line in paragraph and set the correct line space
			if(i+1==displayLine.size()){
				if(lastEndoLine){
					tempMove = lastLineHeight;
				}
				else{
					tempMove = paragraphSpace;
				}
				endoHeight=endoHeight+paraSpaceNum;
				//System.out.println("Endo height is"+endoHeight);
				if(isBullet){
					tempHorMove="@    MRP-0.30, ";
				}
			}
			else{
				endoHeight=endoHeight+lineSpaceNum;
			//	System.out.println("Endo height is"+endoHeight);
			}
			
			if(rppNeed){
				outputLine=outputLine+"TEXT\""+workingLine+"\"; RPP; "+tempHorMove+tempMove;
			}
			else{
				outputLine=outputLine+"TEXT\""+workingLine+"\"; "+tempHorMove+tempMove;
			}
			
			
			if(unbold){
				outputLine=outputLine+" "+normalFont;
			}

		    
		    String[] eachLine = outputLine.split("@");
		    for(String result : eachLine){
		    //	System.out.println("rseult = "+result);
		    	endoPara.add(result);
		    }
		}
	}
	
	//new method
	private void writeEndoText(String rawPara) throws Exception {
	//	System.out.println("rawPara = "+rawPara);
		Boolean isBullet = Boolean.valueOf(false);

		Character.toString(rawPara.charAt(0)).equals("<");

		if(Character.toString(rawPara.charAt(0)).equals("•") || Character.toString(rawPara.charAt(0)).equals("·")){
			isBullet = Boolean.valueOf(true);
			endoPara.add(space + "MRP 0.15, 0.00; FONT 11; TEXT\".\"; FONT 13;");
			endoPara.add("    MRP 0.15, 0.00;");
			String removeBullet = "";
			for (int y = 1; y < rawPara.length(); y++) {
				if ((y != 1) || (!Character.toString(rawPara.charAt(y)).equals(" "))) {

					removeBullet = removeBullet + Character.toString(rawPara.charAt(y));
				}
			}

			rawPara = removeBullet;
		}

		ArrayList<String> displayLine = getDisplayLines(rawPara);
		String tempMove = lineSpace;

		for (int i = 0; i < displayLine.size(); i++) {
			String outputLine = space;
			String workingLine = (String) displayLine.get(i);
			Boolean unbold = Boolean.valueOf(false);
			Boolean rppNeed = Boolean.valueOf(false);

			if ((workingLine.contains("{")) || (workingLine.contains("}"))) {
				endoPara.add(space + "SCP;");
				rppNeed = Boolean.valueOf(true);
			}

			String formatLine = "";
			for (int x = 0; x < workingLine.length(); x++) {
				if (Character.toString(workingLine.charAt(x)).equals("{")) {
					if (x == 0) {
						endoPara.add(space + boldFont);
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

				//	if (workingLine.charAt(x) != 'Â') {

						formatLine = formatLine + Character.toString(workingLine.charAt(x));
				//	}
				}
			}

			System.out.println("formated line = <"+formatLine+">");
			workingLine = formatLine;

			if (Character.toString(workingLine.charAt(workingLine.length() - 1)).equals(" ")) {
				workingLine.replace(workingLine.substring(workingLine.length() - 1), "");
			}

			String tempHorMove = "@    " + movePos;

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
			//System.out.println("result para = <"+result+">");
				endoPara.add(result);
			}
		}
	}
}
