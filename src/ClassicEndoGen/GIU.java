package ClassicEndoGen;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GIU extends JFrame {
	
	//CLASS VARIABLES
	private static final long serialVersionUID = 1L;
	
	//INSTANCE VARIABLES
	EndorsementProcessor EndoGen = new EndorsementProcessor();
	
	
	private JPanel contentPane;
	private final Action action = new SwingAction();
	
	private JCheckBox PrecisCheckBox;
	private JCheckBox ClassicCheckBox;

	private JLabel lblSchemeCode;
	private JLabel lblLineSpace;
	private JLabel lblParaSpace;
	private JLabel lblBottomMargin;
	private JLabel lblPageHeight;
	private JLabel lblTotHeightCalc;
	private JLabel lblCurrentHeightCalc;
	private JLabel lblBoldFont;
	private JLabel lblNormalFont;
	private JLabel lblLineSize;
	private JLabel lblPolicyType;
	private JLabel lblBrokerCode1;
	private JLabel lblBrokerCode2;
	private JLabel lblAffinityCode;
	private JLabel lblStartDate;
	private JLabel lblEndDate;
	
	private JTextField Classic_Normal_Font;
	private JTextField Classic_Line_Size;
	private JTextField Classic_Bold_Font;
	private JTextField Classic_Current_Height;
	private JTextField classic_LineSpace;
	private JTextField Classic_paragraphSpace;
	private JTextField Classic_Margin;
	private JTextField Classic_Page_Height;
	private JTextField Classic_Total_Height;
	private JTextField SchemeCodeValue = new JTextField();
	private JTextField PolicyTypeValue;
	private JTextField BrokerCode1Value;
	private JTextField BrokerCode2Value;
	private JTextField AffinityCodeValue;
	private JTextField StartDateValue;
	private JTextField EndDateValue;
	
	public static void main(String[] args){
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GIU frame = new GIU();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	/**
	 * Create the frame.
	 */
	public GIU() {
		setTitle("CDL Endorsement Generator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 463, 355);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		createLabels();
		
		ClassicCheckBox = new JCheckBox("Classic");
		ClassicCheckBox.setHorizontalAlignment(SwingConstants.RIGHT);
		ClassicCheckBox.setBounds(67, 7, 142, 23);
		contentPane.add(ClassicCheckBox);
		
		PrecisCheckBox = new JCheckBox("Pr\u00E9cis");
		PrecisCheckBox.setHorizontalAlignment(SwingConstants.RIGHT);
		PrecisCheckBox.setBounds(301, 7, 136, 23);
		contentPane.add(PrecisCheckBox);
		
		JButton generateButton = new JButton("Generate");
		generateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		generateButton.setAction(action);
		generateButton.setBounds(246, 254, 191, 51);
		contentPane.add(generateButton);
		
		SchemeCodeValue = new JTextField();
		SchemeCodeValue.setText("XX");
		SchemeCodeValue.setBounds(351, 37, 86, 20);
		contentPane.add(SchemeCodeValue);
		SchemeCodeValue.setColumns(10);
		
		PolicyTypeValue = new JTextField();
		PolicyTypeValue.setText("PC");
		PolicyTypeValue.setBounds(351, 68, 86, 20);
		contentPane.add(PolicyTypeValue);
		PolicyTypeValue.setColumns(10);
		
		BrokerCode1Value = new JTextField();
		BrokerCode1Value.setText("ALL");
		BrokerCode1Value.setBounds(351, 99, 86, 20);
		contentPane.add(BrokerCode1Value);
		BrokerCode1Value.setColumns(10);
		
		BrokerCode2Value = new JTextField();
		BrokerCode2Value.setText("ALL");
		BrokerCode2Value.setBounds(351, 130, 86, 20);
		contentPane.add(BrokerCode2Value);
		BrokerCode2Value.setColumns(10);
		
		AffinityCodeValue = new JTextField();
		AffinityCodeValue.setText("ALL");
		AffinityCodeValue.setBounds(351, 161, 86, 20);
		contentPane.add(AffinityCodeValue);
		AffinityCodeValue.setColumns(10);
		
		StartDateValue = new JTextField();
		StartDateValue.setText("PAST");
		StartDateValue.setBounds(351, 192, 86, 20);
		contentPane.add(StartDateValue);
		StartDateValue.setColumns(10);
		
		EndDateValue = new JTextField();
		EndDateValue.setText("FUTURE");
		EndDateValue.setBounds(351, 223, 86, 20);
		contentPane.add(EndDateValue);
		EndDateValue.setColumns(10);
		

		
		Classic_Bold_Font = new JTextField();
		Classic_Bold_Font.setText("FONT 56;");
		Classic_Bold_Font.setBounds(123, 223, 86, 20);
		contentPane.add(Classic_Bold_Font);
		Classic_Bold_Font.setColumns(10);
		
		Classic_Current_Height = new JTextField();
		Classic_Current_Height.setText("#10");
		Classic_Current_Height.setBounds(123, 192, 86, 20);
		contentPane.add(Classic_Current_Height);
		Classic_Current_Height.setColumns(10);
		
		classic_LineSpace = new JTextField();
		classic_LineSpace.setText("0.12");
		classic_LineSpace.setBounds(123, 37, 86, 20);
		contentPane.add(classic_LineSpace);
		classic_LineSpace.setColumns(10);
		
		Classic_paragraphSpace = new JTextField();
		Classic_paragraphSpace.setText("0.15");
		Classic_paragraphSpace.setBounds(123, 68, 86, 20);
		contentPane.add(Classic_paragraphSpace);
		Classic_paragraphSpace.setColumns(10);
		
		Classic_Margin = new JTextField();
		Classic_Margin.setText("0.20");
		Classic_Margin.setBounds(123, 99, 86, 20);
		contentPane.add(Classic_Margin);
		Classic_Margin.setColumns(10);
		
		Classic_Page_Height = new JTextField();
		Classic_Page_Height.setText("9.00");
		Classic_Page_Height.setBounds(123, 130, 86, 20);
		contentPane.add(Classic_Page_Height);
		Classic_Page_Height.setColumns(10);
		
		Classic_Total_Height = new JTextField();
		Classic_Total_Height.setText("#11");
		Classic_Total_Height.setBounds(123, 161, 86, 20);
		contentPane.add(Classic_Total_Height);
		Classic_Total_Height.setColumns(10);
		
		Classic_Normal_Font = new JTextField();
		Classic_Normal_Font.setText("FONT 13;");
		Classic_Normal_Font.setBounds(123, 254, 86, 20);
		contentPane.add(Classic_Normal_Font);
	//	Classic_Normal_Font.setColumns(10);
		
		Classic_Line_Size = new JTextField();
		Classic_Line_Size.setText("140");
		Classic_Line_Size.setBounds(123, 285, 86, 20);
		contentPane.add(Classic_Line_Size);
	//	Classic_Line_Size.setColumns(10);
	}
	
	private void createLabels() {
		lblNormalFont = new JLabel("Normal Font");
		lblNormalFont.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNormalFont.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNormalFont.setBounds(37, 257, 76, 14);
		contentPane.add(lblNormalFont);
		
		lblLineSize = new JLabel("Line Size");
		lblLineSize.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblLineSize.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLineSize.setBounds(10, 288, 103, 14);
		contentPane.add(lblLineSize);
		
		lblLineSpace = new JLabel("Line Space");
		lblLineSpace.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLineSpace.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblLineSpace.setBounds(37, 40, 76, 14);
		contentPane.add(lblLineSpace);
		
		lblParaSpace = new JLabel("Paragraphs Space");
		lblParaSpace.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblParaSpace.setHorizontalAlignment(SwingConstants.RIGHT);
		lblParaSpace.setBounds(10, 71, 103, 14);
		contentPane.add(lblParaSpace);
		
		lblBottomMargin = new JLabel("Bottom Margin");
		lblBottomMargin.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBottomMargin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBottomMargin.setBounds(37, 102, 76, 14);
		contentPane.add(lblBottomMargin);
		
		lblPageHeight = new JLabel("Page Height");
		lblPageHeight.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPageHeight.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblPageHeight.setBounds(37, 133, 76, 14);
		contentPane.add(lblPageHeight);
		
		lblTotHeightCalc = new JLabel("Total Height calc");
		lblTotHeightCalc.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTotHeightCalc.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotHeightCalc.setBounds(27, 164, 86, 14);
		contentPane.add(lblTotHeightCalc);
		
		lblCurrentHeightCalc = new JLabel("Current Height calc");
		lblCurrentHeightCalc.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCurrentHeightCalc.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCurrentHeightCalc.setBounds(10, 195, 103, 14);
		contentPane.add(lblCurrentHeightCalc);
		
		lblBoldFont = new JLabel("Bold Font");
		lblBoldFont.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBoldFont.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBoldFont.setBounds(37, 226, 76, 14);
		contentPane.add(lblBoldFont);
		
		lblSchemeCode = new JLabel("Scheme Code");
		lblSchemeCode.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblSchemeCode.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSchemeCode.setBounds(276, 40, 65, 14);
		contentPane.add(lblSchemeCode);
		
		lblPolicyType = new JLabel("Policy Type");
		lblPolicyType.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblPolicyType.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPolicyType.setBounds(287, 71, 55, 14);
		contentPane.add(lblPolicyType);
		
		lblBrokerCode1 = new JLabel("Broker Code 1");
		lblBrokerCode1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBrokerCode1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBrokerCode1.setBounds(265, 102, 76, 14);
		contentPane.add(lblBrokerCode1);
		
		lblBrokerCode2 = new JLabel("broker Code 2");
		lblBrokerCode2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBrokerCode2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBrokerCode2.setBounds(265, 133, 76, 14);
		contentPane.add(lblBrokerCode2);
		
		lblAffinityCode = new JLabel("Affinity Code");
		lblAffinityCode.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAffinityCode.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAffinityCode.setBounds(265, 164, 76, 14);
		contentPane.add(lblAffinityCode);
		
		lblStartDate = new JLabel("Start Date");
		lblStartDate.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblStartDate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStartDate.setBounds(276, 195, 65, 14);
		contentPane.add(lblStartDate);
		
		lblEndDate = new JLabel("End Date");
		lblEndDate.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblEndDate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEndDate.setBounds(276, 226, 65, 14);
		contentPane.add(lblEndDate);
		
	}
	
	private class SwingAction extends AbstractAction{
		
		private static final long serialVersionUID = 1L;
		public SwingAction() {
			putValue(NAME, "Generate");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e){
			System.out.println("pressed");
			try{
				if(PrecisCheckBox.isSelected()){
					EndoGen.setPrecisValues(PrecisCheckBox.isSelected(),SchemeCodeValue.getText(),PolicyTypeValue.getText(),BrokerCode1Value.getText(),BrokerCode2Value.getText(),AffinityCodeValue.getText(),StartDateValue.getText(),EndDateValue.getText());
				//	System.out.println("set precis values");
				}
				if(ClassicCheckBox.isSelected()){
					EndoGen.setClassicValues(ClassicCheckBox.isSelected(),Classic_Bold_Font.getText() , Classic_Normal_Font.getText() , Classic_Current_Height.getText() ,  classic_LineSpace.getText() , Classic_paragraphSpace.getText() , Classic_Margin.getText() , Classic_Page_Height.getText() , Classic_Total_Height.getText() , Classic_Line_Size.getText());
				//	System.out.println("set classic values");
				}
				
			//	EndoGen.setValues(ClassicCheckBox.isSelected(), PrecisCheckBox.isSelected());
				EndoGen.run();
			}
			catch(Exception ex){
				
			}

		}
	}
}