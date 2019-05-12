package ClassicEndoGen;

public class PrecisEndorsement {
	WriteFile data;

	private String schemeCode;
	private String polType;
	private String brokerCode1;
	private String brokerCode2;
	private String affinityCode;
	private String startDate;
	private String endDate;

	private String code;

	private String wording;
	private String title;
	private String space = "    ";
	private String doubleSpace = "        ";
	private String tripleSpace = "            ";

	boolean bullet = false;
	boolean lastParagraph = false;

	public PrecisEndorsement(String schemeCode, String polType, String brokerCode1, String brokerCode2, String affinityCode, String startDate, String endDate) {
		this.schemeCode = schemeCode;
		this.polType = polType;
		this.brokerCode1 = brokerCode1;
		this.brokerCode2 = brokerCode2;
		this.affinityCode = affinityCode;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public void setValues(String gui_Scheme, String gui_poltype, String gui_brokerCode1, String gui_brokerCode2,
			String gui_affinity, String gui_StartDate, String gui_endDate) {
		schemeCode = gui_Scheme;
		polType = gui_poltype;
		brokerCode1 = gui_brokerCode1;
		brokerCode2 = gui_brokerCode2;
		affinityCode = gui_affinity;
		startDate = gui_StartDate;
		endDate = gui_endDate;
	}

	public void setDetails(String input_code, String input_title, String input_wording, WriteFile input_data)
			throws Exception {
		code = input_code;
		wording = input_wording;
		title = formatWording(input_title);
		data = input_data;
	}

	public void add() throws Exception {
		data.writeLineToFile(space + "<endorsement>", true);
		data.writeLineToFile(doubleSpace + "<policy-type>" + polType + "</policy-type>", true);
		data.writeLineToFile(doubleSpace + "<scheme-code>" + schemeCode + "</scheme-code>", true);
		data.writeLineToFile(doubleSpace + "<endorsement-details>", true);
		data.writeLineToFile(tripleSpace + "<endorsement-code>" + code + "</endorsement-code>", true);
		data.writeLineToFile(tripleSpace + "<broker-code-1>" + brokerCode1 + "</broker-code-1>", true);
		data.writeLineToFile(tripleSpace + "<broker-code-2>" + brokerCode2 + "</broker-code-2>", true);
		data.writeLineToFile(tripleSpace + "<affinity>" + affinityCode + "</affinity>", true);
		data.writeLineToFile(tripleSpace + "<start-date>" + startDate + "</start-date>", true);
		data.writeLineToFile(tripleSpace + "<end-date>" + endDate + "</end-date>", true);
		data.writeLineToFile(tripleSpace + "<wording>", true);
		data.writeLineToFile(tripleSpace + "<![CDATA[<div>", true);
		data.writeLineToFile(tripleSpace + "<h4>" + title + "</h4>", true);
		writeWording();
		data.writeLineToFile(tripleSpace + "</div>]]></wording>", true);
		data.writeLineToFile(doubleSpace + "</endorsement-details>", true);
		data.writeLineToFile(space + "</endorsement>", true);
		data.writeLineToFile("", true);
	}

	private void writeWording() throws Exception {
		String[] paragraphs = wording.split("\\r?\\n");

		for (int x = 0; x < paragraphs.length; x++) {

			checkBulletStart(paragraphs[x]);

			if (bullet) {
				data.writeLineToFile(tripleSpace + space + "<li>" + formatWording(paragraphs[x]) + "</li>", true);
			} else {
				data.writeLineToFile(tripleSpace + "<p>" + formatWording(paragraphs[x]) + "</p>", true);
			}
			if ((paragraphs.length - 1 == x) && (bullet)) {
				bullet = true;
				data.writeLineToFile(tripleSpace + "</ol>", true);
				data.writeLineToFile(tripleSpace + "<p></p>", true);
			}
		}
	}

	private void checkBulletStart(String rawString) throws Exception {
		if (Character.toString(rawString.charAt(0)).equals("•") || Character.toString(rawString.charAt(0)).equals("·")){
			if (!bullet) {
				bullet = true;
				data.writeLineToFile(tripleSpace + "<ol>",true);
			}
		} else if (bullet) {
			data.writeLineToFile(tripleSpace + "</ol>",true);
			bullet = false;
		} else {
			bullet = false;
		}
	}

	private String formatWording(String rawString) throws Exception
	{
	rawString = rawString.trim().replaceAll(" +", " ");	
    String formated = "";
 //   boolean foundChar = false;
    




    for (int x = 0; x < rawString.length(); x++)
    {



      if (Character.toString(rawString.charAt(x)).equals("{")) {
        formated = formated + "<strong>";
      }
      else if (Character.toString(rawString.charAt(x)).equals("}")) {
        formated = formated + "</strong>";

      }
      else if (Character.toString(rawString.charAt(x)).equals("£")) {
        formated = formated + "&pound;";

      }
      else if (Character.toString(rawString.charAt(x)).equals("&")) {
        formated = formated + "&amp;";

      }
      else if (Character.toString(rawString.charAt(x)).equals("‘")) {
        formated = formated + "'";

      }
      else if (Character.toString(rawString.charAt(x)).equals("’")) {
        formated = formated + "'";

      }
      else if (Character.toString(rawString.charAt(x)).equals("“")) {
        formated = formated + '"';

      }
      else if (Character.toString(rawString.charAt(x)).equals("”")) {
        formated = formated + '"';

      }
      else if(Character.toString(rawString.charAt(x)).equals("–")){
        formated = formated + "-";

      }
      else if (Character.toString(rawString.charAt(x)).equals("<")) {
        formated = formated + "&lt;";

      }
      else if (Character.toString(rawString.charAt(x)).equals(">")) {
        formated = formated + "&gt;";

      }
      else if ((!Character.toString(rawString.charAt(x)).equals("•")) && (!Character.toString(rawString.charAt(x)).equals("·")))
      {

    	  formated = formated + rawString.toString().charAt(x);

      }
    }
    
    return formated;
  }
}
