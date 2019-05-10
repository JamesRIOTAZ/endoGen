package ClassicEndoGen;

import java.util.ArrayList;

public class precisEndorsement {
	WriteFile data;
	private ArrayList<String> endoPara = new ArrayList();
	private String schemeCode = "XX";
	private String polType = "PC";
	private String brokerCode1 = "ALL";
	private String brokerCode2 = "ALL";
	private String affinityCode = "ALL";
	private String startDate = "PAST";
	private String endDate = "FUTURE";

	private String code;

	private String wording;
	private String title;
	private String space = "    ";
	private String doubleSpace = "        ";
	private String tripleSpace = "            ";

	boolean bullet = false;
	boolean lastParagraph = false;

	public precisEndorsement() {
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
		data.writeToFile(space + "<endorsement>");
		data.writeToFile(doubleSpace + "<policy-type>" + polType + "</policy-type>");
		data.writeToFile(doubleSpace + "<scheme-code>" + schemeCode + "</scheme-code>");
		data.writeToFile(doubleSpace + "<endorsement-details>");
		data.writeToFile(tripleSpace + "<endorsement-code>" + code + "</endorsement-code>");
		data.writeToFile(tripleSpace + "<broker-code-1>" + brokerCode1 + "</broker-code-1>");
		data.writeToFile(tripleSpace + "<broker-code-2>" + brokerCode2 + "</broker-code-2>");
		data.writeToFile(tripleSpace + "<affinity>" + affinityCode + "</affinity>");
		data.writeToFile(tripleSpace + "<start-date>" + startDate + "</start-date>");
		data.writeToFile(tripleSpace + "<end-date>" + endDate + "</end-date>");
		data.writeToFile(tripleSpace + "<wording>");
		data.writeToFile(tripleSpace + "<![CDATA[<div>");
		data.writeToFile(tripleSpace + "<h4>" + title + "</h4>");
		writeWording();
		data.writeToFile(tripleSpace + "</div>]]></wording>");
		data.writeToFile(doubleSpace + "</endorsement-details>");
		data.writeToFile(space + "</endorsement>");
		data.writeToFile("");
	}

	private void writeWording() throws Exception {
		String[] paragraphs = wording.split("\\r?\\n");

		for (int x = 0; x < paragraphs.length; x++) {

			checkBulletStart(paragraphs[x]);

			if (bullet) {
				String tempWording;
				data.writeToFile(tripleSpace + space + "<li>" + formatWording(tempWording = paragraphs[x]) + "</li>");
			} else {
				String tempWording;
				data.writeToFile(tripleSpace + "<p>" + formatWording(tempWording = paragraphs[x]) + "</p>");
			}
			if ((paragraphs.length - 1 == x) && (bullet)) {
				bullet = true;
				data.writeToFile(tripleSpace + "</ol>");
				data.writeToFile(tripleSpace + "<p></p>");
			}
		}
	}

	private void checkBulletStart(String rawString) throws Exception {
		if ((Character.toString(rawString.charAt(0)).equals("â€¢"))
				|| (Character.toString(rawString.charAt(0)).equals("Â·"))) {
			if (!bullet) {
				bullet = true;
				data.writeToFile(tripleSpace + "<ol>");
			}
		} else if (bullet) {
			data.writeToFile(tripleSpace + "</ol>");
			bullet = false;
		} else {
			bullet = false;
		}
	}

	private String formatWording(String rawString)
    throws Exception
  {
    String formated = "";
    boolean foundChar = false;
    




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

        if (rawString.charAt(x) != ' ')
        {
        	formated = formated + rawString.toString().charAt(x);
        }
      }
    }
    
    return formated;
  }
}
