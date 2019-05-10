package ClassicEndoGen;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;

import javax.swing.Action;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;

public class GIU extends JFrame {
	
	ClassicEndoGen EndoGen = new ClassicEndoGen();
	
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
		
	/*	ReadFile inputData = new ReadFile();
		List rawData = inputData.getData(); 	
		showExelData(rawData, inputData);*/
	}

	private JPanel contentPane;
	private final Action action = new SwingAction();

	
	private JCheckBox PrecisCheckBox;
	private JCheckBox ClassicCheckBox;
	
	private JTextField SchemeCodeValue = new JTextField();
	private JTextField PolicyTypeValue;
	private JTextField BrokerCode1Value;
	private JTextField BrokerCode2Value;
	private JTextField AffinityCodeValue;
	private JTextField StartDateValue;
	private JTextField EndDateValue;
	
	JLabel lblNewLabel = new JLabel("Scheme Code");
	private JTextField Classic_Bold_Font;
	private JTextField Classic_Current_Height;
	private JTextField classic_LineSpace;
	private JTextField Classic_paragraphSpace;
	private JTextField Classic_Margin;
	private JTextField Classic_Page_Height;
	private JTextField Classic_Total_Height;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;
	private JLabel lblNewLabel_9;
	private JLabel lblNewLabel_10;
	private JLabel lblNewLabel_11;
	private JLabel lblNewLabel_12;
	private JLabel lblNewLabel_13;
	private JTextField Classic_Normal_Font;
	private JTextField Classic_Line_Size;
	private JLabel lblNewLabel_14;
	private JLabel lblNewLabel_15;
	
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
		
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(276, 40, 65, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Policy Type");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(287, 71, 55, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Broker Code 1");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(265, 102, 76, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("broker Code 2");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setBounds(265, 133, 76, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Affinity Code");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setBounds(265, 164, 76, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Start Date");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_5.setBounds(276, 195, 65, 14);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("End Date");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_6.setBounds(276, 226, 65, 14);
		contentPane.add(lblNewLabel_6);
		
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
		
		lblNewLabel_7 = new JLabel("Line Space");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_7.setBounds(37, 40, 76, 14);
		contentPane.add(lblNewLabel_7);
		
		lblNewLabel_8 = new JLabel("Paragraphs Space");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_8.setBounds(10, 71, 103, 14);
		contentPane.add(lblNewLabel_8);
		
		lblNewLabel_9 = new JLabel("Bottom Margin");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_9.setBounds(37, 102, 76, 14);
		contentPane.add(lblNewLabel_9);
		
		lblNewLabel_10 = new JLabel("Page Height");
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_10.setBounds(37, 133, 76, 14);
		contentPane.add(lblNewLabel_10);
		
		lblNewLabel_11 = new JLabel("Total Height calc");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_11.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_11.setBounds(27, 164, 86, 14);
		contentPane.add(lblNewLabel_11);
		
		lblNewLabel_12 = new JLabel("Current Height calc");
		lblNewLabel_12.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_12.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_12.setBounds(10, 195, 103, 14);
		contentPane.add(lblNewLabel_12);
		
		lblNewLabel_13 = new JLabel("Bold Font");
		lblNewLabel_13.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_13.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_13.setBounds(37, 226, 76, 14);
		contentPane.add(lblNewLabel_13);
		
		Classic_Normal_Font = new JTextField();
		Classic_Normal_Font.setText("FONT 13;");
		Classic_Normal_Font.setBounds(123, 254, 86, 20);
		contentPane.add(Classic_Normal_Font);
		Classic_Normal_Font.setColumns(10);
		
		Classic_Line_Size = new JTextField();
		Classic_Line_Size.setText("140");
		Classic_Line_Size.setBounds(123, 285, 86, 20);
		contentPane.add(Classic_Line_Size);
		Classic_Line_Size.setColumns(10);
		
		lblNewLabel_14 = new JLabel("Normal Font");
		lblNewLabel_14.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_14.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_14.setBounds(37, 257, 76, 14);
		contentPane.add(lblNewLabel_14);
		
		lblNewLabel_15 = new JLabel("Line Size");
		lblNewLabel_15.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_15.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_15.setBounds(10, 288, 103, 14);
		contentPane.add(lblNewLabel_15);
	}
	private class SwingAction extends AbstractAction{
		public SwingAction() {
			putValue(NAME, "Generate");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e){
			System.out.println("pressed");
			try{
				if(PrecisCheckBox.isSelected()){
					EndoGen.setPrecisValues(PrecisCheckBox.isSelected(),SchemeCodeValue.getText(),PolicyTypeValue.getText(),BrokerCode1Value.getText(),BrokerCode2Value.getText(),AffinityCodeValue.getText(),StartDateValue.getText(),EndDateValue.getText());
					System.out.println("set precis values");
				}
				if(ClassicCheckBox.isSelected()){
					EndoGen.setClassicValues(ClassicCheckBox.isSelected(),Classic_Bold_Font.getText() , Classic_Normal_Font.getText() , Classic_Current_Height.getText() ,  classic_LineSpace.getText() , Classic_paragraphSpace.getText() , Classic_Margin.getText() , Classic_Page_Height.getText() , Classic_Total_Height.getText() , Classic_Line_Size.getText());
					System.out.println("set classic values");
				}
				
				EndoGen.setValues(ClassicCheckBox.isSelected(), PrecisCheckBox.isSelected());
				EndoGen.run();
			}
			catch(Exception ex){
				
			}

		}
	}
}