// -------------------------------------- Dependencies --------------------------------------

package acadsyncFile;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;



//--------------------------------------------------------------------------------------------


 

public class AdminDashBoard extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final JLabel ExtraLabel = new JLabel("│");
	private JTable TableCalendar;
	private DefaultTableModel tableModelCalendar;

	// Database credentials
    private String DB_URL = DatabaseCredentials.getDBURL();
    private String DB_USER = DatabaseCredentials.getDBUSER();
    private String DB_PASSWORD = DatabaseCredentials.getDBPASS();
	
	private JTextField RegularStudenttextField;
	private JTextField IrregularStudenttextField;
	private JTextField TotalStudenttextField;
	private JTextField NewStudenttextField;
	private JTextField AcademicProgramtextField;
	private JTextField CurrentSemestertextField;
	private JTextField FinancialBudgettextField;
	
	private JTextField LittleAYtextField;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	    
	    EventQueue.invokeLater(() -> {
	        try {
	        	AdminDashBoard frame = new AdminDashBoard();
	            frame.setVisible(true);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    });
	}
	       
	/**
	 * Create the frame.
	 */
	
	public AdminDashBoard() {
		UIManager.put("Button.select", new Color(69, 70, 73)); // Change the default "pressed" color
		
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/SMS Logo.png"));
        setTitle("Admin DashBoard\r\n");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1250, 710);  // Set the frame's size and position 
        setUndecorated(false);
        setLocationRelativeTo(null);  // Center the frame on the screen
       

        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(null);
        setContentPane(contentPane);
        contentPane.setLayout(null);
		        
		        
		        
		        
		        JPanel panel = new JPanel();
		        panel.setBorder(null);
		        panel.setBackground(new Color(0, 0, 0));
		        panel.setBounds(0, 0, 1236, 90);
		        
		        contentPane.add(panel);
		        panel.setLayout(null);
		        
		        
		        JButton MenuButton = new JButton("");
		        
		     // Add ActionListener
		        MenuButton.addActionListener(new ActionListener() {
			         public void actionPerformed(ActionEvent e) {
		
			           // Action code here (e.g., open another window or perform login)
			        	 AdminDashBoard1 AdminDashBoardNavigation = new AdminDashBoard1();
			        	 AdminDashBoardNavigation.setVisible(true);
			        	 
			        	
			        	 dispose(); // Close the current window frame
			         }
			         
			     });
		        
		             // Add MouseListener for hover effect
		        MenuButton.addMouseListener(new MouseAdapter() {
		                 @Override
		                 public void mouseEntered(MouseEvent e) {
		                     // On mouse enter, change button background and text color
		                	 MenuButton.setBackground(new Color(27, 27, 27));  // Hover background color
		                 }
		                 
		                 @Override
		                 public void mouseExited(MouseEvent e) {
		                     // On mouse exit, revert button background and text color to original
		                	 MenuButton.setBackground(new Color(0, 0, 0));  // Original background color
		                 }
		             });
		        
		        MenuButton.setBackground(new Color(0, 0, 0));
		        MenuButton.setIcon(new ImageIcon("src/list.png"));
		        MenuButton.setBounds(0, 0, 90, 90);
		        MenuButton.setBorderPainted(false);  // Disable border painting
		        MenuButton.setFocusPainted(false);   // Disable focus painting
		        MenuButton.setBorder(null);
		        MenuButton.setOpaque(true);  // Make sure the button is opaque to allow background color change
		        MenuButton.setContentAreaFilled(true);  // Prevent the default button background rendering
		        
		        panel.add(MenuButton);
		        
		         
		        
		        JButton LogOutButton = new JButton("");
		        
		        // Add ActionListener
		        LogOutButton.addActionListener(new ActionListener() {
			         public void actionPerformed(ActionEvent e) {
 
			            // Action code here (e.g., open another window or perform login)
			        	 AdminPortalLogIn AdminLogIn = new AdminPortalLogIn();
				         AdminLogIn.setVisible(true);
				        	 
				         dispose(); // Close the current window frame
			         }
			     });
		        
		             // Add MouseListener for hover effect
		        LogOutButton.addMouseListener(new MouseAdapter() {
		                 @Override
		                 public void mouseEntered(MouseEvent e) {
		                     // On mouse enter, change button background and text color
		                	 LogOutButton.setBackground(new Color(27, 27, 27)); // Hover background color
		                 }
		        
		                 @Override
		                 public void mouseExited(MouseEvent e) {
		                     // On mouse exit, revert button background and text color to original
		                	 LogOutButton.setBackground(new Color(0, 0, 0));  // Original background color
		                 }
		             });
		        
		        LogOutButton.setBackground(new Color(0, 0, 0));
		        LogOutButton.setIcon(new ImageIcon("src/turn-off.png"));
		        LogOutButton.setForeground(new Color(255, 255, 255));
		        LogOutButton.setBounds(1166, 0, 70, 90);
		        LogOutButton.setBorderPainted(false);  // Disable border painting
		        LogOutButton.setFocusPainted(false);   // Disable focus painting
		        LogOutButton.setBorder(null);
		        LogOutButton.setOpaque(true);  // Make sure the button is opaque to allow background color change
		        LogOutButton.setContentAreaFilled(true);  // Prevent the default button background rendering
		        
		        panel.add(LogOutButton);
		        
		        
		        
		        // Create a JLabel to hold the image
		        JLabel SMSLogoLabel = new JLabel();
		        SMSLogoLabel.setLabelFor(panel);
		        SMSLogoLabel.setBounds(89, 0, 79, 90);
		        SMSLogoLabel.setIcon(new ImageIcon("src/SMS Logo Small.jpg"));
		        SMSLogoLabel.setBackground(new Color(240, 240, 240));
		        
		        panel.add(SMSLogoLabel);
		        
		        
		        JLabel LittleAcadLabel = new JLabel("AcadSync²");
		        LittleAcadLabel.setFont(AcadSyncFontManager.getFont("Poppins-Light"));
		        LittleAcadLabel.setForeground(new Color(255, 255, 255));
		        LittleAcadLabel.setBounds(163, 58, 65, 13);
		        
		        
		        panel.add(LittleAcadLabel);
		        
		        JLabel StudentUpLabel = new JLabel("ADMIN");
		        StudentUpLabel.setFont(AcadSyncFontManager.getFont("Poppins-Bold"));
		        StudentUpLabel.setForeground(new Color(255, 255, 255));
		        StudentUpLabel.setBounds(163, 11, 125, 36);
		        
		        panel.add(StudentUpLabel);
		        
		        JLabel PortalUpLabel = new JLabel("PORTAL");
		        PortalUpLabel.setForeground(Color.WHITE);
		        PortalUpLabel.setFont(AcadSyncFontManager.getFont("Poppins-Bold"));
		        PortalUpLabel.setBounds(163, 32, 128, 35);
		        
		        panel.add(PortalUpLabel);
		        
		      
		        		//Line color white
				        ExtraLabel.setForeground(new Color(255, 255, 255));
				        ExtraLabel.setBounds(1162, -3, 15, 15);
				       
				        panel.add(ExtraLabel);
				                
		                JLabel ExtraLabel_1 = new JLabel("│");
		                ExtraLabel_1.setForeground(Color.WHITE);
		                ExtraLabel_1.setBounds(1162, 8, 15, 15);
		                panel.add(ExtraLabel_1);
		                
		                JLabel ExtraLabel_2 = new JLabel("│");
		                ExtraLabel_2.setForeground(Color.WHITE);
		                ExtraLabel_2.setBounds(1162, 19, 15, 15);
		                panel.add(ExtraLabel_2);
		                
		                JLabel ExtraLabel_3 = new JLabel("│");
		                ExtraLabel_3.setForeground(Color.WHITE);
		                ExtraLabel_3.setBounds(1162, 30, 15, 15);
		                panel.add(ExtraLabel_3);
		                
		                JLabel ExtraLabel_4 = new JLabel("│");
		                ExtraLabel_4.setForeground(Color.WHITE);
		                ExtraLabel_4.setBounds(1162, 41, 15, 15);
		                panel.add(ExtraLabel_4);
		                
		                JLabel ExtraLabel_5 = new JLabel("│");
		                ExtraLabel_5.setForeground(Color.WHITE);
		                ExtraLabel_5.setBounds(1162, 52, 15, 15);
		                panel.add(ExtraLabel_5);
		                
		                JLabel ExtraLabel_6 = new JLabel("│");
		                ExtraLabel_6.setForeground(Color.WHITE);
		                ExtraLabel_6.setBounds(1162, 63, 15, 15);
		                panel.add(ExtraLabel_6);
		                
		                JLabel ExtraLabel_7 = new JLabel("│");
		                ExtraLabel_7.setForeground(Color.WHITE);
		                ExtraLabel_7.setBounds(1162, 74, 15, 15);
		                panel.add(ExtraLabel_7);
		                
		                JLabel ExtraLabel_8 = new JLabel("│");
		                ExtraLabel_8.setForeground(Color.WHITE);
		                ExtraLabel_8.setBounds(1162, 85, 15, 15);
		                panel.add(ExtraLabel_8);
				
				// Panel that has a round gray border and white background
		        CustomRoundPanel panel_1 = new CustomRoundPanel(30);
		        panel_1.setBorder(new EmptyBorder(0, 0, 0, 0));
		        panel_1.setBounds(9, 98, 1219, 565);
		        contentPane.add(panel_1);
		        panel_1.setLayout(null);
		        
		        
		        
		        CustomButtonCalendar EditCalendarButton = new CustomButtonCalendar("Edit Calendar", 15);
		        
		        
		        
		        EditCalendarButton.addActionListener(new ActionListener() {
		        	public void actionPerformed(ActionEvent e) {
		        		// Action code here (e.g., open another window or perform login)
		        		 AdminEditCalendar AdminDashBoardNavigation = new AdminEditCalendar();
				         AdminDashBoardNavigation.setVisible(true);
				        	 
				         dispose();
		        	}
		        });
		        
		        FinancialBudgettextField = new JTextField();
		        FinancialBudgettextField.setText("500M");
		        FinancialBudgettextField.setOpaque(false);
		        FinancialBudgettextField.setFont(AcadSyncFontManager.getFont("Poppins-Bold4"));
		        FinancialBudgettextField.setHorizontalAlignment(SwingConstants.CENTER);
		        FinancialBudgettextField.setForeground(new Color(32, 117, 120));
		        FinancialBudgettextField.setEditable(false);
		        FinancialBudgettextField.setColumns(10);
		        FinancialBudgettextField.setBorder(null);
		        FinancialBudgettextField.setBackground(Color.WHITE);
		        FinancialBudgettextField.setBounds(926, 258, 150, 42);
		        panel_1.add(FinancialBudgettextField);
		        
		        CurrentSemestertextField = new JTextField();
		        CurrentSemestertextField.setFont(AcadSyncFontManager.getFont("Poppins-Bold4"));
		        CurrentSemestertextField.setText("...");
		        CurrentSemestertextField.setOpaque(false);
		        CurrentSemestertextField.setHorizontalAlignment(SwingConstants.CENTER);
		        CurrentSemestertextField.setForeground(new Color(32, 117, 120));
		        CurrentSemestertextField.setEditable(false);
		        CurrentSemestertextField.setColumns(10);
		        CurrentSemestertextField.setBorder(null);
		        CurrentSemestertextField.setBackground(Color.WHITE);
		        CurrentSemestertextField.setBounds(530, 258, 150, 42);
		        panel_1.add(CurrentSemestertextField);
		        
		        AcademicProgramtextField = new JTextField();
		        AcademicProgramtextField.setFont(AcadSyncFontManager.getFont("Poppins-Bold4"));
		        AcademicProgramtextField.setText("12");
		        AcademicProgramtextField.setOpaque(false);
		        AcademicProgramtextField.setEditable(false);
		        AcademicProgramtextField.setHorizontalAlignment(SwingConstants.CENTER);
		        AcademicProgramtextField.setBackground(new Color(255, 255, 255));
		        AcademicProgramtextField.setForeground(new Color(32, 117, 120));
		        AcademicProgramtextField.setBounds(130, 258, 150, 42);
		        AcademicProgramtextField.setBorder(null);
		        panel_1.add(AcademicProgramtextField);
		        AcademicProgramtextField.setColumns(10);
		        
		        
		        
		        //EditCalendarButton.setCornerRadius(15);
		        EditCalendarButton.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold"));
		        EditCalendarButton.setBounds(1066, 10, 140, 40);
		        
		        panel_1.add(EditCalendarButton);
		        
		      
		        //This is our 4 small round panel that has a different color  
		        CustomSmallRoundPanel panel2 = new CustomSmallRoundPanel(12);
		        panel2.setBackground(new Color(34, 35, 95));
		        panel2.setBounds(13,61,280,91);	
		        panel_1.add(panel2);
		        panel2.setLayout(null);
		        
		        JLabel TotalStudent = new JLabel("Total Student");
                TotalStudent.setHorizontalAlignment(SwingConstants.CENTER);
                TotalStudent.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold"));
                TotalStudent.setLayout(null);
                TotalStudent.setForeground(Color.WHITE);
                TotalStudent.setBounds(48, 62, 180, 21);
                panel2.add(TotalStudent);
                
                TotalStudenttextField = new JTextField();
                TotalStudenttextField.setText("0");
                TotalStudenttextField.setOpaque(false);
                TotalStudenttextField.setHorizontalAlignment(SwingConstants.CENTER);
                TotalStudenttextField.setForeground(Color.WHITE);
                TotalStudenttextField.setFont(AcadSyncFontManager.getFont("Poppins-Bold4"));
                TotalStudenttextField.setEditable(false);
                TotalStudenttextField.setColumns(20);
                TotalStudenttextField.setBorder(null);
                TotalStudenttextField.setBackground(new Color(235, 104, 160));
                TotalStudenttextField.setBounds(10, 12, 260, 47);
                panel2.add(TotalStudenttextField);
		        
                
		        CustomSmallRoundPanel panel2_1 = new CustomSmallRoundPanel(12);
		        panel2_1.setLayout(null);
		        panel2_1.setBackground(new Color(122, 77, 159));
		        panel2_1.setBounds(317, 61, 280, 91);
		        panel_1.add(panel2_1);
		        
		        
		        JLabel RegularStudent = new JLabel("Regular Student");
                RegularStudent.setHorizontalAlignment(SwingConstants.CENTER);
                RegularStudent.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold"));
                RegularStudent.setLayout(null);
                RegularStudent.setForeground(Color.WHITE);
                RegularStudent.setBounds(51, 62, 180, 21);
                panel2_1.add(RegularStudent);
                
                RegularStudenttextField = new JTextField();
                RegularStudenttextField.setText("0");
                RegularStudenttextField.setOpaque(false);
                RegularStudenttextField.setHorizontalAlignment(SwingConstants.CENTER);
                RegularStudenttextField.setForeground(Color.WHITE);
                RegularStudenttextField.setFont(AcadSyncFontManager.getFont("Poppins-Bold4"));
                RegularStudenttextField.setEditable(false);
                RegularStudenttextField.setColumns(20);
                RegularStudenttextField.setBorder(null);
                RegularStudenttextField.setBackground(new Color(235, 104, 160));
                RegularStudenttextField.setBounds(10, 12, 260, 47);
                panel2_1.add(RegularStudenttextField);
                
		        
                
		        CustomSmallRoundPanel panel2_2 = new CustomSmallRoundPanel(12);
		        panel2_2.setBackground(new Color(235, 104, 160));
		        panel2.setBackground(new Color(34, 35, 95));
		        panel2_2.setBounds(622, 61, 280, 91);
		        panel_1.add(panel2_2);
		        panel2_2.setLayout(null);
		        
		        
		        JLabel IrregularStudent = new JLabel("Irregular Student");
                IrregularStudent.setHorizontalAlignment(SwingConstants.CENTER);
                IrregularStudent.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold"));
                IrregularStudent.setLayout(null);
                IrregularStudent.setForeground(Color.WHITE);
                IrregularStudent.setBounds(51, 62, 180, 21);
                panel2_2.add(IrregularStudent);
                
                
                IrregularStudenttextField = new JTextField();
                IrregularStudenttextField.setText("0");
                IrregularStudenttextField.setOpaque(false);
                IrregularStudenttextField.setHorizontalAlignment(SwingConstants.CENTER);
                IrregularStudenttextField.setForeground(Color.WHITE);
                IrregularStudenttextField.setFont(AcadSyncFontManager.getFont("Poppins-Bold4"));
                IrregularStudenttextField.setEditable(false);
                IrregularStudenttextField.setColumns(20);
                IrregularStudenttextField.setBorder(null);
                IrregularStudenttextField.setBackground(new Color(235, 104, 160));
                IrregularStudenttextField.setBounds(10, 12, 260, 47);
                panel2_2.add(IrregularStudenttextField);
                IrregularStudenttextField.setColumns(20);
                
		        
		        CustomSmallRoundPanel panel2_3 = new CustomSmallRoundPanel(12);
		        panel2_3.setBackground(new Color(149, 32, 77));
		        panel2_3.setLayout(null);
		        panel2_3.setBounds(926, 61, 280, 91);
		        panel_1.add(panel2_3);
		        
		        
		        JLabel NewStudent = new JLabel("New Student");
                NewStudent.setHorizontalAlignment(SwingConstants.CENTER);
                NewStudent.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold"));
                NewStudent.setLayout(null);
                NewStudent.setForeground(Color.WHITE);
                NewStudent.setBounds(52, 62, 180, 21);
                panel2_3.add(NewStudent);
                
                
                NewStudenttextField = new JTextField();
                NewStudenttextField.setText("0");
                NewStudenttextField.setOpaque(false);
                NewStudenttextField.setHorizontalAlignment(SwingConstants.CENTER);
                NewStudenttextField.setForeground(Color.WHITE);
                NewStudenttextField.setFont(AcadSyncFontManager.getFont("Poppins-Bold4"));
                NewStudenttextField.setEditable(false);
                NewStudenttextField.setColumns(20);
                NewStudenttextField.setBorder(null);
                NewStudenttextField.setBackground(new Color(235, 104, 160));
                NewStudenttextField.setBounds(10, 12, 260, 47);
                panel2_3.add( NewStudenttextField);
                NewStudenttextField.setColumns(20);
                
  
                   
        
                Custom3ButtonDashboard AcademicYearButton = new Custom3ButtonDashboard("Academic Program", 5);
                AcademicYearButton.setMargin(new Insets(10, 0, 0, 0)); 
                AcademicYearButton.setIconTextGap(48); 
                AcademicYearButton.setBounds(25, 174, 373, 151); 
                AcademicYearButton.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold2"));
                AcademicYearButton.setForeground(Color.GRAY);

                // Load and resize the icon
                ImageIcon originalIcon = new ImageIcon("src/program.png");
                Image resizedImage = originalIcon.getImage().getScaledInstance(100, 67, Image.SCALE_SMOOTH); 
                ImageIcon resizedIcon = new ImageIcon(resizedImage);
                AcademicYearButton.setIcon(resizedIcon);

                // Position icon above text
                AcademicYearButton.setHorizontalTextPosition(SwingConstants.CENTER);
                AcademicYearButton.setVerticalTextPosition(SwingConstants.BOTTOM);
                panel_1.add(AcademicYearButton);
                
                
                Custom3ButtonDashboard CurrentSemesterButton = new Custom3ButtonDashboard("Current Semester", 5);
                CurrentSemesterButton.setMargin(new Insets(10, 0, 0, 0)); 
                CurrentSemesterButton.setIconTextGap(52); 
                CurrentSemesterButton.setBounds(25, 174, 373, 151); 
                CurrentSemesterButton.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold2"));
                CurrentSemesterButton.setForeground(Color.GRAY);

                // Load and resize the icon
                ImageIcon originalIcon1 = new ImageIcon("src/rating (2).png");
                Image resizedImage1 = originalIcon1.getImage().getScaledInstance(100, 62, Image.SCALE_SMOOTH); 
                ImageIcon resizedIcon1 = new ImageIcon(resizedImage1);
                CurrentSemesterButton.setIcon(resizedIcon1);

                // Position icon above text
                CurrentSemesterButton.setHorizontalTextPosition(SwingConstants.CENTER);
                CurrentSemesterButton.setVerticalTextPosition(SwingConstants.BOTTOM);
                CurrentSemesterButton.setBounds(423, 174, 373, 151);
                panel_1.add(CurrentSemesterButton);
                
                
                
                Custom3ButtonDashboard FinancialBudgetButton = new Custom3ButtonDashboard("Financial Budget of the Year", 5);
                FinancialBudgetButton.setMargin(new Insets(10, 0, 0, 0)); 
                FinancialBudgetButton.setIconTextGap(48); 
                FinancialBudgetButton.setBounds(25, 174, 373, 151); 
                FinancialBudgetButton.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold2"));
                FinancialBudgetButton.setForeground(Color.GRAY);

                // Load and resize the icon
                ImageIcon originalIcon2 = new ImageIcon("src/accounting.png");
                Image resizedImage2= originalIcon2.getImage().getScaledInstance(100, 70, Image.SCALE_SMOOTH); 
                ImageIcon resizedIcon2 = new ImageIcon(resizedImage2);
                FinancialBudgetButton.setIcon(resizedIcon2);

                // Position icon above text
                FinancialBudgetButton.setHorizontalTextPosition(SwingConstants.CENTER);
                FinancialBudgetButton.setVerticalTextPosition(SwingConstants.BOTTOM);
                FinancialBudgetButton.setBounds(821, 174, 370, 151);
                panel_1.add(FinancialBudgetButton);
                
                
                
                
                  // Create a new JPanel to hold the table and the label
	              JPanel combinedPanel = new JPanel();
	              combinedPanel.setBackground(Color.WHITE);
	              combinedPanel.setLayout(null);
	              combinedPanel.setPreferredSize(new Dimension(200, 600)); 
	             

	              
	              // Add the JLabel with the image
	              JLabel AcademicYearLabel = new JLabel();
	              AcademicYearLabel.setBounds(0, 3, 1170, 151); // Adjust position within the panel
	              ImageIcon originalIcon4 = new ImageIcon("src/Group 90.png");
	              Image scaledImage4 = originalIcon4.getImage().getScaledInstance(AcademicYearLabel.getWidth(), AcademicYearLabel.getHeight(), Image.SCALE_SMOOTH);
	              ImageIcon scaledIcon4 = new ImageIcon(scaledImage4);
	              
	              
	              
	              LittleAYtextField = new JTextField();
	              LittleAYtextField.setText("A.Y. 3030-3031");
	              LittleAYtextField.setFont(AcadSyncFontManager.getFont("Poppins-Medium"));
	              LittleAYtextField.setForeground(Color.WHITE);
	              LittleAYtextField.setBackground(new Color(32, 117, 120));
	              LittleAYtextField.setBounds(500, 103, 170, 21);
	              LittleAYtextField.setBorder(null);
	              LittleAYtextField.setEditable(false);
	              LittleAYtextField.setHorizontalAlignment(SwingConstants.CENTER);
	              LittleAYtextField.setColumns(10);
	              combinedPanel.add(LittleAYtextField);
	              AcademicYearLabel.setIcon(scaledIcon4);
	              combinedPanel.add(AcademicYearLabel);
              
                
	              
	              
                tableModelCalendar = new DefaultTableModel() {
			         private static final long serialVersionUID = 1L;
			
			         @Override
			         public boolean isCellEditable(int row, int column) {
			             return false;  // Prevent all cells from being editable
			         }
			     };
			     
			
			     
			     TableCalendar = new JTable(tableModelCalendar);
			     TableCalendar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Only one row can be selected at a time
	
			
			
			
			     // Set the font for the JTable content
			     if (AcadSyncFontManager.getFont("Poppins-Bold") != null) {
			         TableCalendar.setFont(AcadSyncFontManager.getFont("Poppins-Bold"));
			     }
			
			     
			        // Set table row height and background color
			        TableCalendar.setRowHeight(30);
			        TableCalendar.setBackground(Color.WHITE);
			        TableCalendar.setGridColor(Color.WHITE);
			        TableCalendar.setForeground(new Color(32, 117, 120));
			        TableCalendar.setBorder(null);
			        TableCalendar.setOpaque(false);
			        

			     // Customize table header font
			     if (AcadSyncFontManager.getFont("Poppins-SemiBold") != null) {
			         TableCalendar.getTableHeader().setFont(AcadSyncFontManager.getFont("Poppins-SemiBold").deriveFont(20f)); // Larger font for header
			         TableCalendar.getTableHeader().setBackground(Color.DARK_GRAY); // Header background color
			         TableCalendar.getTableHeader().setForeground(Color.WHITE); // Header text color
			     }
			     
			     
			     // Set custom renderer
			     TableCalendar.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			         private static final long serialVersionUID = 1L;

			         @Override
			         public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			             Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

			             // Customize alignment based on column index
			             if (column == 0) { // "DATE" column
			                 setHorizontalAlignment(RIGHT); // Align text to the right\
			                 setForeground(new Color(235, 35, 99));
			             } else if (column == 1) { // "EVENT" column
			                 setHorizontalAlignment(LEFT); // Align text to the left
			                 setForeground(new Color(32, 117, 120));
			             } else {
			                 setHorizontalAlignment(CENTER); // Default alignment
			             }

			             
			             // Customize selection and default colors
			             if (isSelected) {
			                 component.setBackground(null);
			             } else {
			                 component.setBackground(null);
			             }
			             
			             
			             // Remove focus border
			             if (component instanceof DefaultTableCellRenderer) {
			                 ((DefaultTableCellRenderer) component).setBorder(noFocusBorder);
			             }

			             return component;
			         }
			     });

		         
		         
		         // Column names
			     tableModelCalendar.addColumn("DATE");
			     tableModelCalendar.addColumn("EVENT");
			     
			     


				  // Set column widths
				  TableColumnModel columnModel = TableCalendar.getColumnModel();
				  columnModel.getColumn(0).setPreferredWidth(40); // Set width for "DATE" column
				  columnModel.getColumn(1).setPreferredWidth(450); // Set width for "EVENT" column
			   
	             
	              // Add the JTable below the JLabel
	              TableCalendar.setBounds(2, 180, 1167, 424); // Adjust table position within the panel
	              combinedPanel.add(TableCalendar);

	              // Add the combined panel to the scroll pane
	              JScrollPane scrollPane = new JScrollPane(combinedPanel);
	              scrollPane.setForeground(new Color(255,255,255));
	              scrollPane.setBounds(12, 342, 1196, 200);
	              scrollPane.getViewport().setBackground(new Color(255, 255, 255)); 
	              scrollPane.setBorder(new LineBorder(Color.WHITE, 0));
	              
	              panel_1.add(scrollPane);
	              
	              
	              
	              
	              // Load data from database
	 		      LoadDataFromDatabase(AcadSyncFontManager.getFont("Poppins-SemiBold"), AcadSyncFontManager.getFont("Poppins-Bold2"), AcadSyncFontManager.getFont("Poppins-SemiBold1"), AcadSyncFontManager.getFont("Arvo-Bold"));
	 		      UpdateStudentCount(); // Fetch and display the total student count
	 		      
	 		      
	 		      // Add mouse listener to detect row selection
	              TableCalendar.addMouseListener(new MouseAdapter() {
	                  @Override
	                  public void mouseClicked(MouseEvent e) {
	                      int selectedRow = TableCalendar.getSelectedRow();

	                      if (selectedRow >= 0) {
	                          Object date = tableModelCalendar.getValueAt(selectedRow, 0);
	                          Object event = tableModelCalendar.getValueAt(selectedRow, 1);

	                        

	                          // Construct the message string
	                          String message = "Date: " + date + "\n" +
	                                           "Event: " + event;
	                                  

	                          // Show the message dialog
	                          JOptionPane.showMessageDialog(AdminDashBoard.this,
	                                  message,
	                                  "Acaddemic Year Schedule",
	                                  JOptionPane.INFORMATION_MESSAGE);
	                      }
	                  }
	              });
	 		      
	}
	
	
	
	
	
	
	
	
//----------------------------------------------------------------------------Method------------------------------------------------------------------------
	
	
	
	
	
	
	
	              
	                // Method to Load data from the database and populate the table
					private List<Object[]> AllTableData = new ArrayList<>(); // Cache to hold all rows
				    private void LoadDataFromDatabase(Font poppinsSemiBold, Font poppinsBold2, Font poppinsSemiBold1, Font arvoBold) {
				        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				             Statement stmt = conn.createStatement();
				             ResultSet rs = stmt.executeQuery("SELECT * FROM Academic_Year_Schedule")) {
				        	
				        	  // Clear the existing table data and cache
				            tableModelCalendar.setRowCount(0);
				            AllTableData.clear();
				
				            while (rs.next()) {
				                // Create a row object from the ResultSet
				                Object[] row = new Object[] {
				                    rs.getString("Date")  + "    -    ",
				                    rs.getString ("Event"),
				                
				               
				                };
				
				                // Add the row to the cache
				                AllTableData.add(row);
				
				                // Add the row to the table model
				                tableModelCalendar.addRow(row);
				            }
				        } catch (Exception e) {
				            e.printStackTrace();
				            JOptionPane.showMessageDialog(this, "Error loading data from database: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
				        }
				    }
				        
				
				        
	 
				        
				        
				        // Method to Load the total student, semester and academic year
				        private void UpdateStudentCount() {
				            Connection connection = null;
				            Statement statement = null;
				            ResultSet resultSet = null;

				            try {
				                // Connect to the database
				                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				                statement = connection.createStatement();

				                // Query to count total students
				                String query = "SELECT COUNT(*) AS total FROM Student_Profile";
				                resultSet = statement.executeQuery(query);

				                if (resultSet.next()) {
				                    // Retrieve the count and set it to the TotalStudenttextField
				                    int totalStudents = resultSet.getInt("total");
				                    TotalStudenttextField.setText(String.valueOf(totalStudents));
				                }
				                
				                // Query to fetch the current academic year
			                    String littleAYQuery = "SELECT DISTINCT AcademicYear FROM Academic_Year_Schedule ORDER BY AcademicYear DESC LIMIT 1";
			                    resultSet = statement.executeQuery(littleAYQuery);
			                    if (resultSet.next()) {
			                        String littleacademicyear = resultSet.getString("AcademicYear");
			                        LittleAYtextField.setText("A.Y. " + littleacademicyear);
			                    }
				                
				                // Query to fetch the current semester
			                    String currentSemesterQuery = "SELECT DISTINCT Semester FROM Academic_Year_Schedule ORDER BY Semester DESC LIMIT 1";
			                    resultSet = statement.executeQuery(currentSemesterQuery);
			                    if (resultSet.next()) {
			                        String currentSemester = resultSet.getString("Semester");
			                        CurrentSemestertextField.setText(currentSemester);
			                    }

			                    // Query to count distinct regular students
			                    String regularStudentsQuery = "SELECT COUNT(DISTINCT StudentNumber) AS RegularStudents FROM Enrollment_Status WHERE State = 'Regular'";
			                    resultSet = statement.executeQuery(regularStudentsQuery);
			                    if (resultSet.next()) {
			                        int regularStudents = resultSet.getInt("RegularStudents");
			                        RegularStudenttextField.setText(String.valueOf(regularStudents));
			                    }

			                    // Query to count distinct irregular students
			                    String irregularStudentsQuery = "SELECT COUNT(DISTINCT StudentNumber) AS IrregularStudents FROM Enrollment_Status WHERE State = 'Irregular'";
			                    resultSet = statement.executeQuery(irregularStudentsQuery);
			                    if (resultSet.next()) {
			                        int irregularStudents = resultSet.getInt("IrregularStudents");
			                        IrregularStudenttextField.setText(String.valueOf(irregularStudents));
			                    }

			                    // Query to count distinct new students
			                    String newStudentsQuery = "SELECT COUNT(DISTINCT StudentNumber) AS NewStudents FROM Enrollment_Status WHERE Type = 'New'";
			                    resultSet = statement.executeQuery(newStudentsQuery);
			                    if (resultSet.next()) {
			                        int newStudents = resultSet.getInt("NewStudents");
			                        NewStudenttextField.setText(String.valueOf(newStudents));
			                    }

				                
				                
				                
				                
				            } catch (Exception e) {
				                e.printStackTrace();
				                JOptionPane.showMessageDialog(this, "Error fetching total student count: " + e.getMessage(), 
				                                              "Database Error", JOptionPane.ERROR_MESSAGE);
				            } finally {
				                // Close resources
				                try {
				                    if (resultSet != null) resultSet.close();
				                    if (statement != null) statement.close();
				                    if (connection != null) connection.close();
				                } catch (Exception ex) {
				                    ex.printStackTrace();
				                }
				            }
				            
				            
				          
				        
     
				        
				        
				        
				        
				        
//----------------------------------------------------------------------------------------------------------------------------------------------------------	        

        
		   

	}
}

