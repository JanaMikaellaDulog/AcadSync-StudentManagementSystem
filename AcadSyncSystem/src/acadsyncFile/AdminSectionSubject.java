// -------------------------------------- Dependencies --------------------------------------

package acadsyncFile;



import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;




//--------------------------------------------------------------------------------------------




public class AdminSectionSubject extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final JLabel ExtraLabel = new JLabel("│");
	private JTable TableSectionSubject;
	private DefaultTableModel tableModelSubject;

	// Database credentials
    private String DB_URL = DatabaseCredentials.getDBURL();
    private String DB_USER = DatabaseCredentials.getDBUSER();
    private String DB_PASSWORD = DatabaseCredentials.getDBPASS();
	
	private JTextField SearchtextField;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	
	    EventQueue.invokeLater(() -> {
	        try {
	        	AdminSectionSubject frame = new AdminSectionSubject();
	            frame.setVisible(true);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    });
	}
 
	        
	/**
	 * Create the frame.
	 */
	public AdminSectionSubject() {
		UIManager.put("Button.select", new Color(69, 70, 73)); // Change the default "pressed" color
		
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/SMS Logo.png"));
        setTitle("Admin Section Subject");
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
			        	 AdminSectionSubject1 AdminSectionSubjectNavigation = new AdminSectionSubject1();
			        	 AdminSectionSubjectNavigation.setVisible(true);
			        	 
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
				        panel_1.setBounds(10, 98, 1218, 565);
				        contentPane.add(panel_1);
				        panel_1.setLayout(null);
		        
		        
		        
		        CustomButtonSubject  EditSubjectButton = new CustomButtonSubject("Edit Subject", 15);
		        
		        
		        
		        EditSubjectButton.addActionListener(new ActionListener() { 
		        	public void actionPerformed(ActionEvent e) {
		        		// Action code here (e.g., open another window or perform login)
		        		 AdminEditSectionSubject AdminEditSectionSubjectNoNavigation = new AdminEditSectionSubject();
				         AdminEditSectionSubjectNoNavigation.setVisible(true);
				        	
				         dispose();
		        	}
		        });
		        
		        
		        //EditCalendarButton.setCornerRadius(15);
		        EditSubjectButton.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold"));
		        EditSubjectButton.setBounds(1066, 10, 140, 40);
		        
		        panel_1.add( EditSubjectButton);
		        
		        
		        tableModelSubject = new DefaultTableModel() {
			         private static final long serialVersionUID = 1L;
			
			         @Override
			         public boolean isCellEditable(int row, int column) {
			             return false;  // Prevent all cells from being editable
			         }
			     };
			     
			     
			     TableSectionSubject = new JTable(tableModelSubject);
			     TableSectionSubject.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Only one row can be selected at a time
			     TableSectionSubject.setBounds(70, 10, 600, 200);
			     
			
			
			     // Set the font for the JTable content
			     if (AcadSyncFontManager.getFont("Poppins-SemiBold1") != null) {
			         TableSectionSubject.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
			     }
			
			     
			        // Set table row height and background color
			        TableSectionSubject.setRowHeight(30);
			        TableSectionSubject.setBackground(Color.WHITE);
			        TableSectionSubject.setGridColor(Color.BLACK);
			        TableSectionSubject.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
			        

			     // Customize table header font
			     if (AcadSyncFontManager.getFont("Poppins-SemiBold") != null) {
			         TableSectionSubject.getTableHeader().setFont(AcadSyncFontManager.getFont("Poppins-SemiBold").deriveFont(13f)); // Larger font for header
			         TableSectionSubject.getTableHeader().setBackground(Color.DARK_GRAY); // Header background color
			         TableSectionSubject.getTableHeader().setForeground(Color.WHITE); // Header text color
			     }
			     
			     
			     // Set custom renderer
		         TableSectionSubject.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
		             private static final long serialVersionUID = 1L;
		    
		             @Override
		             public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		                 Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		                 setHorizontalAlignment(CENTER);
		    
		                 if (isSelected) {
		                     component.setBackground(new Color(113, 142, 143));
		                     component.setForeground(Color.BLACK);
		                 } else {
		                     component.setBackground(Color.WHITE);
		                     component.setForeground(Color.BLACK);
		                 }
		                 return component;
		             }
		         });
		         
		         
		         
		         // Column names
			     tableModelSubject.addColumn("SECTION ID");
			     tableModelSubject.addColumn("SECTION");
			     tableModelSubject.addColumn("SEMESTER");
			     tableModelSubject.addColumn("ACADEMIC YEAR");
			     tableModelSubject.addColumn("SCHED CODE");
			     tableModelSubject.addColumn("SUBJECT CODE");
			     tableModelSubject.addColumn("DESCRIPTION");
			     tableModelSubject.addColumn("UNIT");
			     tableModelSubject.addColumn("CREDIT UNIT");
			     tableModelSubject.addColumn("SCHEDULE 1");
			     tableModelSubject.addColumn("SCHEDULE 2");
			     tableModelSubject.addColumn("SCHEDULE 3");
			     tableModelSubject.addColumn("SCHEDULE 4");
			     tableModelSubject.addColumn("INSTRUCTOR");
			   
		         
		         
		         
			      JScrollPane scrollPane = new JScrollPane(TableSectionSubject);
	              scrollPane.setBounds(12, 98, 1194, 424);
	              scrollPane.setBorder(new LineBorder(Color.BLACK, 2));
	              panel_1.add(scrollPane);
	              
	              SearchtextField = new JTextField();
	              SearchtextField.setBounds(180, 32, 205, 38);
	              panel_1.add(SearchtextField);
	              SearchtextField.setForeground(Color.GRAY);
	              SearchtextField.setFont(AcadSyncFontManager.getFont("Poppins-Medium1"));
	              SearchtextField.setColumns(10);
	              SearchtextField.setBorder(new LineBorder(new Color(128, 128, 128), 2));
	              
	              
	              
	              JLabel SearchLabel = new JLabel("Search");
	              SearchLabel.setBounds(105, 44, 59, 13);
	              panel_1.add(SearchLabel);
	              SearchLabel.setHorizontalAlignment(SwingConstants.LEFT);
	              SearchLabel.setFont(AcadSyncFontManager.getFont("Poppins-Bold2"));
	              SearchtextField.getDocument().addDocumentListener(new DocumentListener() {
	                  @Override
	                  public void insertUpdate(DocumentEvent e) {
	                      performSearch();
	                  }

	                  @Override
	                  public void removeUpdate(DocumentEvent e) {
	                      performSearch();
	                  }

	                  @Override
	                  public void changedUpdate(DocumentEvent e) {
	                      performSearch();
	                  }


	                  private void performSearch() {
	                      String searchTerm = SearchtextField.getText().trim();
	                      if (searchTerm.isEmpty()) {
	                      	ResetTableData(); // Show all data without reloading from the database
	                      } else {
	                          SearchData(searchTerm);
	                      }
	                  }

	                  private void ResetTableData() {
	                      tableModelSubject.setRowCount(0); // Clear the table

	                      // Add all rows back to the table model from the full data list
	                      for (Object[] row : AllTableData) { // `AllTableData` contains the full data set
	                          tableModelSubject.addRow(row);
	                      }
	                  }
	              });
	              
	              
	              
	              // Load data from database
	              LoadDataFromDatabase(AcadSyncFontManager.getFont("Poppins-SemiBold"), AcadSyncFontManager.getFont("Poppins-Bold2"), AcadSyncFontManager.getFont("Poppins-SemiBold1"), AcadSyncFontManager.getFont("Arvo-Bold"));
	              
	              
	              
	              // Add mouse listener to detect row selection
	              TableSectionSubject.addMouseListener(new MouseAdapter() {
	                  @Override
	                  public void mouseClicked(MouseEvent e) {
	                      int selectedRow = TableSectionSubject.getSelectedRow();

	                      if (selectedRow >= 0) {
	                          Object sectionID = tableModelSubject.getValueAt(selectedRow, 0);
	                          Object section = tableModelSubject.getValueAt(selectedRow, 1);
	                          Object semester = tableModelSubject.getValueAt(selectedRow, 2);
	                          Object academicYear = tableModelSubject.getValueAt(selectedRow, 3);
	                          Object schedcode = tableModelSubject.getValueAt(selectedRow, 4);
	                          Object subjectcode = tableModelSubject.getValueAt(selectedRow, 5);
	                          Object description = tableModelSubject.getValueAt(selectedRow, 6);
	                          Object unit = tableModelSubject.getValueAt(selectedRow, 7);
	                          Object creditUnit = tableModelSubject.getValueAt(selectedRow, 8);
	                          Object schedule1 = tableModelSubject.getValueAt(selectedRow, 9);
	                          Object schedule2= tableModelSubject.getValueAt(selectedRow, 10);
	                          Object schedule3 = tableModelSubject.getValueAt(selectedRow, 11);
	                          Object schedule4 = tableModelSubject.getValueAt(selectedRow, 12);
	                          Object instructor = tableModelSubject.getValueAt(selectedRow, 13);
	                        

	                          // Construct the message string
	                          String message = "Section ID: " + sectionID + "\n" +
	                                           "Section: " + section + "\n" +
	                                           "Semester: " + semester + "\n" +
	                                           "Academic Year: " + academicYear + "\n" +
	                                           "Schedule Code: " + schedcode + "\n" +
	                                           "Subject Code: " + subjectcode + "\n" +
	                                           "Description: " + description + "\n" +
	                                           "Unit: " + unit + "\n" +
	                                           "Credit Unit: " + creditUnit + "\n" +
	                                           "Schedule 1: " + schedule1 + "\n" +
	                                           "Schedule 2: " + schedule2 + "\n" +
	                                           "Schedule 3: " + schedule3 + "\n" +
	                                           "Schedule 4: " + schedule4 + "\n" +
	                                           "Instructor: " + instructor;
	                                       

	                          // Show the message dialog
	                          JOptionPane.showMessageDialog(AdminSectionSubject.this,
	                                  message,
	                                  "Section Subjects",
	                                  JOptionPane.INFORMATION_MESSAGE);
	                      }
	                  }
	              });

		        
		         
		
      }
						
		
	
	
	
    

		        
//----------------------------------------------------------------------------Method------------------------------------------------------------------------





				
							//Method for Search a data inside our JtextField 
							private void SearchData(String searchTerm) {
							   tableModelSubject.setRowCount(0); // Clear the table
						
							    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
							        // Modify query to exclude "Last Log In" column
							    	String query = "SELECT * FROM Sections_Subjects WHERE " +
						                       "AcademicYear LIKE ? OR Semester LIKE ? OR " +
						                       "Section LIKE ? OR SchedCode LIKE ? OR SubjectCode LIKE ? OR Description LIKE ? OR " +
						                       "Schedule1 LIKE ? OR Schedule2 LIKE ? OR Schedule3 LIKE ? OR Schedule4 LIKE ? OR " +
						                       "Instructor LIKE ? OR Unit LIKE ? OR CreditUnit LIKE ?";
							                     
							        try (PreparedStatement searchStmt = conn.prepareStatement(query)) {
							            // Set the search term for all columns
							            for (int i = 1; i <= 13; i++) { 
							                searchStmt.setString(i, "%" + searchTerm + "%");
							            }
						
							            try (ResultSet rs = searchStmt.executeQuery()) {
							                while (rs.next()) {
							                	tableModelSubject.addRow(new Object[]{
							                	    rs.getString("SectionID"),
										            rs.getString("Section"),
										            rs.getString("Semester"),
										            rs.getString("AcademicYear"),
										            rs.getString("SchedCode"),
										            rs.getString("SubjectCode"),
										            rs.getString("Description"),
										            rs.getString("Unit"),
										            rs.getString("CreditUnit"),
										            rs.getString("Schedule1"),
										            rs.getString("Schedule2"),
										            rs.getString("Schedule3"),
										            rs.getString("Schedule4"),
										            rs.getString("Instructor"),    
							                       
							                    });
							                }
							            }
							        }
							    } catch (Exception e) {
							        e.printStackTrace();
							        JOptionPane.showMessageDialog(this, "Error searching data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
							    }
							}
	
						
						
						
							
							// Method to Load data from the database and populate the table
							private List<Object[]> AllTableData = new ArrayList<>(); // Cache to hold all rows
							private void LoadDataFromDatabase(Font semiBoldFont, Font bold1Font, Font semiBold1Font, Font arvoFont) {
						        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
						             Statement stmt = conn.createStatement();
						             ResultSet rs = stmt.executeQuery("SELECT * FROM Sections_Subjects")) {
						        	
						        	  // Clear the existing table data and cache
						            tableModelSubject.setRowCount(0);
						            AllTableData.clear();
						
						            while (rs.next()) {
						                // Create a row object from the ResultSet
						                Object[] row = new Object[] {
						                    rs.getString("SectionID"),
						                    rs.getString("Section"),
						                    rs.getString("Semester"),
						                    rs.getString("AcademicYear"),
						                    rs.getString("SchedCode"),
						                    rs.getString("SubjectCode"),
						                    rs.getString("Description"),
						                    rs.getString("Unit"),
						                    rs.getString("CreditUnit"),
						                    rs.getString("Schedule1"),
						                    rs.getString("Schedule2"),
						                    rs.getString("Schedule3"),
						                    rs.getString("Schedule4"),
						                    rs.getString("Instructor"),       
						               
						                };
						
						                // Add the row to the cache
						                AllTableData.add(row);
						
						                // Add the row to the table model
						                tableModelSubject.addRow(row);
						            }
						        } catch (Exception e) {
						            e.printStackTrace();
						            JOptionPane.showMessageDialog(this, "Error loading data from database: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
						        }
						        
						        
    
    
   
    
 
    
    

//----------------------------------------------------------------------------------------------------------------------------------------------------------	        

	
			
			
		        
		        
							
				
		        

        
  
        
		   

	}
}


