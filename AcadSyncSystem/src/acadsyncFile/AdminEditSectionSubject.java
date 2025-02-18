// -------------------------------------- Dependencies --------------------------------------

package acadsyncFile;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


//--------------------------------------------------------------------------------------------




public class AdminEditSectionSubject extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable TableSectionSubject;
	private DefaultTableModel tableModelSubject;

	// Database credentials
    private String DB_URL = DatabaseCredentials.getDBURL();
    private String DB_USER = DatabaseCredentials.getDBUSER();
    private String DB_PASSWORD = DatabaseCredentials.getDBPASS();
	
	private CustomRoundedTextField SectionIDtextField;
	private CustomRoundedTextField SectiontextField;
	private CustomRoundedTextField SemestertextField;
	private CustomRoundedTextField AcademicYeartextField;
	private CustomRoundedTextField SchedCodetextField;
    private CustomRoundedTextField SubjectCodetextField;
    private CustomRoundedTextField DescriptiontextField;
    private CustomRoundedTextField UnittextField;
    private CustomRoundedTextField CreditUnittextField;
    private CustomRoundedTextField Schedule1textField;
    private CustomRoundedTextField Schedule2textField;
    private CustomRoundedTextField Schedule3textField;
    private CustomRoundedTextField Schedule4textField;
    private CustomRoundedTextField InstructortextField;
   
	  
	private JTextField SearchtextField;
	
	
	public static void main(String[] args) {
	    EventQueue.invokeLater(() -> {
	        try {
	        	AdminEditSectionSubject frame = new  AdminEditSectionSubject();
	            frame.setVisible(true);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    });
	}
   
	       
	/**
	 * Create the frame.
	 */
	public  AdminEditSectionSubject() {
		setTitle("Edit Section Subjects");
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/SMS Logo.png"));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1109, 775);
	    setLocationRelativeTo(null);  // Center the frame on the screen
	    
	    
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		// Add WindowListener to handle close event
	    addWindowListener(new WindowAdapter() {
	        @Override
	        public void windowClosing(WindowEvent e) {
	            // Call the method that shows the MainFrame and disposes the current frame
	        	 AdminSectionSubject AdminSectionSubjectNoNavigation = new AdminSectionSubject();
	        	 AdminSectionSubjectNoNavigation.setVisible(true);
	        	
		         dispose();
	        }
	    });
		
		
		
					// Initialize JTable and DefaultTableModel
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
				    
			
			        // Set font and styling for rows
			        if (AcadSyncFontManager.getFont("Poppins-SemiBold1") != null) {
			            TableSectionSubject.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
			        } else {
			            System.out.println("Custom font not loaded; using default font.");
			        }
			        
			        
			        
			        TableSectionSubject.setRowHeight(30);
			        TableSectionSubject.setBackground(Color.WHITE);
			        TableSectionSubject.setGridColor(Color.BLACK);
			        TableSectionSubject.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
			
			        
			        
			        // Customize table header font
			        if (AcadSyncFontManager.getFont("Poppins-SemiBold2") != null) {
			            TableSectionSubject.getTableHeader().setFont(AcadSyncFontManager.getFont("Poppins-SemiBold2").deriveFont(13f)); // Larger font for header
			            TableSectionSubject.getTableHeader().setBackground(Color.DARK_GRAY); // Header background color
			            TableSectionSubject.getTableHeader().setForeground(Color.WHITE); // Header text color
			        }
			
			        // Custom cell renderer for selected row behavior
			        TableSectionSubject.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
						/**
						 * 
						 */
						private static final long serialVersionUID = 1L;
			
						@Override
						public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
			                    boolean hasFocus, int row, int column) {
			                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			                
			                
			                // Center the text
			                setHorizontalAlignment(CENTER);
			
			                if (isSelected) {
			                    component.setBackground(new Color(113, 142, 143)); // Custom selected row background color
			                    component.setForeground(Color.BLACK); // Custom selected row foreground color
			                } else {
			                    component.setBackground(Color.WHITE); // Default background color
			                    component.setForeground(Color.BLACK); // Default text color
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
			  
			
			        
			        
			        // Add table to a scroll pane
			        JScrollPane scrollPane = new JScrollPane(TableSectionSubject);
			        scrollPane.setBounds(18, 64, 1067, 240);
			        scrollPane.setBorder(new LineBorder(Color.BLACK, 2));
			        getContentPane().add(scrollPane, BorderLayout.CENTER);
			        
			        
			        SearchtextField = new JTextField();
			        SearchtextField.setBounds(212, 12, 205, 38);
			        SearchtextField.setForeground(Color.GRAY);
			        SearchtextField.setFont(AcadSyncFontManager.getFont("Poppins-Medium1"));
			        SearchtextField.setColumns(10);
			        SearchtextField.setBorder(new LineBorder(new Color(128, 128, 128), 2));
			        contentPane.add(SearchtextField);
			        
			        
			        
			        JLabel SearchLabel = new JLabel("Search");
			        SearchLabel.setBounds(134, 24, 59, 13);
			        contentPane.add(SearchLabel);
			        SearchLabel.setHorizontalAlignment(SwingConstants.LEFT);
			        SearchLabel.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold"));
			        SearchtextField.getDocument().addDocumentListener(new DocumentListener() {
			            @Override
			            public void insertUpdate(DocumentEvent e) {
			            	ClearData();
			                performSearch();
			            }
			
			            @Override
			            public void removeUpdate(DocumentEvent e) {
			            	ClearData();
			                performSearch();
			            }
			
			            @Override
			            public void changedUpdate(DocumentEvent e) {
			            	ClearData();
			                performSearch();
			            }
			
			
			            private void performSearch() {
			                String searchTerm = SearchtextField.getText().trim();
			                if (searchTerm.isEmpty()) {
			                	ClearData();
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
			        LoadDataFromDatabase(AcadSyncFontManager.getFont("Poppins-SemiBold"), AcadSyncFontManager.getFont("Poppins-Bold3"), AcadSyncFontManager.getFont("Poppins-SemiBold"), AcadSyncFontManager.getFont("Arvo-Bold1"));
			
	      }
							
			        
        
	 
			        
//----------------------------------------------------------------------------Method------------------------------------------------------------------------
	
	
	
	
	
	
				//Method for Search a data inside our JtextField 
				private void SearchData(String searchTerm) {
				    tableModelSubject.setRowCount(0); // Clear the table
			
				    // Filter and display rows from AllTableData matching the search term
				    for (Object[] row : AllTableData) {
				        boolean matches = false;
				        for (Object cell : row) {
				            if (cell != null && cell.toString().toLowerCase().contains(searchTerm.toLowerCase())) {
				                matches = true;
				                break;
				            }
				        }
			
				        if (matches) {
				            tableModelSubject.addRow(row);
				        }
				    }
				}
			
			
			
			
				
			
				//Method to Clear the text inside our JtextField 
				private void ClearData() {
			    	  // Clear all the text fields
			            SectionIDtextField.setText("");
			            SectiontextField.setText("");
			            SemestertextField.setText("");
			            AcademicYeartextField.setText("");
			            SchedCodetextField.setText("");
			            SubjectCodetextField.setText("");
			            DescriptiontextField.setText("");
			            UnittextField.setText("");
			            CreditUnittextField.setText("");
			            Schedule1textField.setText("");
			            Schedule2textField.setText("");
			            Schedule3textField.setText("");
			            Schedule4textField.setText("");
			            InstructortextField.setText("");
			            
			            
			            SectiontextField.setEditable(true);
			            SemestertextField.setEditable(true);
			            AcademicYeartextField.setEditable(true);
			            SubjectCodetextField.setEditable(true);
			           
			          
			            //Reset selected items or clear any custom selections
			            TableSectionSubject.clearSelection();
			
			        }
				
				
				
			
				
				
				//Method to Add a data to our database as well as the row in our table 
				private void AddData() {
					String section = SectiontextField.getText().trim().toUpperCase();
					String semester = SemestertextField.getText().trim();
					String academicYear = AcademicYeartextField.getText().trim();
					String schedCode = SchedCodetextField.getText().trim();
					String subjectCode = SubjectCodetextField.getText().trim().toUpperCase();
					String description = DescriptiontextField.getText().trim().toUpperCase();
					String unit = UnittextField.getText().trim();
					String creditUnit = CreditUnittextField.getText().trim();
					String schedule1 = Schedule1textField.getText().trim().toUpperCase();
					String schedule2 = Schedule2textField.getText().trim().toUpperCase();
					String schedule3 = Schedule3textField.getText().trim().toUpperCase();
					String schedule4 = Schedule4textField.getText().trim().toUpperCase();
					String instructor = InstructortextField.getText().trim().toUpperCase();

				    
					// Check required fields
					if (section.isEmpty() || semester.isEmpty() || academicYear.isEmpty() || schedCode.isEmpty() || 
					    subjectCode.isEmpty() || description.isEmpty() || unit.isEmpty() || creditUnit.isEmpty()) {
					    JOptionPane.showMessageDialog(this, "Only Schedule1, Schedule 2, Schedule 3, Schedule 4 \n                 and Instructor Fields can be null.", "Validation Error", JOptionPane.WARNING_MESSAGE);
					    return;
					}
 

				    // Define the list of valid semester
				    List<String> validSem = Arrays.asList("1st Sem", "2nd Sem");
				    

				    // Validate Semester against the list of valid semester
				    if (!validSem.contains(semester)) {
				        JOptionPane.showMessageDialog(this, "Invalid Sem. Should only be: " + validSem, "Validation Error", JOptionPane.WARNING_MESSAGE);
				        return;
				    }
				    
				    
				 // Validate integer fields
				    try {
				        Integer.parseInt(schedCode);
				        Integer.parseInt(unit);
				        Integer.parseInt(creditUnit);
				    } catch (NumberFormatException e) {
				        JOptionPane.showMessageDialog(this, "Schedule Code, Unit, and Credit Unit must be integers.", "Validation Error", JOptionPane.WARNING_MESSAGE);
				        return;
				    } 


				 // Check for duplicate record in sections_subjects (from section to creditUnit)
				    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
				        String checkQuery = "SELECT * FROM Sections_Subjects WHERE TRIM(Section) = ? AND TRIM(Semester) = ? AND TRIM(AcademicYear) = ? " +
				                            "AND TRIM(SubjectCode) = ?";
				        try (PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
				            checkStmt.setString(1, section);
				            checkStmt.setString(2, semester);
				            checkStmt.setString(3, academicYear);				           
				            checkStmt.setString(4, subjectCode);
				     

				            try (ResultSet rs = checkStmt.executeQuery()) {
				                if (rs.next()) {
				                    JOptionPane.showMessageDialog(this, "Duplicate data exists for the specified fields.", "Duplicate Record", JOptionPane.WARNING_MESSAGE);
				                    return;
				                }
				            }
				        }
				        
				        
				        // Get the next SectionID
				        String getMaxIdQuery = "SELECT MAX(SectionID) FROM Sections_Subjects";
				        int newsectionID = 1; // Default for the first record

				        try (Statement stmt = conn.createStatement();
				             ResultSet rs = stmt.executeQuery(getMaxIdQuery)) {
				            if (rs.next()) {
				            	newsectionID = rs.getInt(1) + 1; // Increment the max ID
				            }
				        }
				        

				        // Insert new record into sections_subjects
				        String insertQuery = "INSERT INTO Sections_Subjects (Section, Semester, AcademicYear, SchedCode, SubjectCode, Description, Unit, CreditUnit, Schedule1, Schedule2, Schedule3, Schedule4, Instructor) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				        try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
				            insertStmt.setString(1, section);
				            insertStmt.setString(2, semester);
				            insertStmt.setString(3, academicYear);
				            insertStmt.setString(4, schedCode);
				            insertStmt.setString(5, subjectCode);
				            insertStmt.setString(6, description);
				            insertStmt.setString(7, unit);
				            insertStmt.setString(8, creditUnit);
				            insertStmt.setString(9, schedule1);
				            insertStmt.setString(10, schedule2);
				            insertStmt.setString(11, schedule3);
				            insertStmt.setString(12, schedule4);
				            insertStmt.setString(13, instructor);

				            insertStmt.executeUpdate();

				            // Clear input fields and notify success
				            ClearData();
				            JOptionPane.showMessageDialog(this, "Section Subjects Successfully Added!", "Success", JOptionPane.INFORMATION_MESSAGE);

				            
				            // Add new row to table model
				            tableModelSubject.addRow(new Object[]{
				                newsectionID, 
				                section, 
				                semester, 
				                academicYear, 
				                schedCode, 
				                subjectCode, 
				                description,
				                unit, 
				                creditUnit, 
				                schedule1, 
				                schedule2, 
				                schedule3, 
				                schedule4, 
				                instructor
				            });
				            
				            
				            //Add new record to AllTableData
				            AllTableData.add(new Object[]{
				            		 newsectionID, 
				            		 section, 
				            		 semester, 
						             academicYear, 
						             schedCode, 
						             subjectCode, 
						             description,
						             unit, 
						             creditUnit, 
						             schedule1, 
						             schedule2, 
						             schedule3, 
						             schedule4, 
						             instructor
				            });
				        }
				    } catch (Exception e) {
				        e.printStackTrace();
				        JOptionPane.showMessageDialog(this, "Error adding Section Subjects: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
				    }
				}
				
				
			
				
				
				//Method to Update a data inside our database as well as the row in our table
				private void UpdateData() {
				    int selectedRow = TableSectionSubject.getSelectedRow();
				    if (selectedRow == -1) {
				        JOptionPane.showMessageDialog(this, "Please select a row to Update.", "No Selection", JOptionPane.WARNING_MESSAGE);
				        return;
				    }

				    String sectionID = SectionIDtextField.getText().trim();
				    String section = SectiontextField.getText().trim().toUpperCase();
				    String semester = SemestertextField.getText().trim();
				    String academicYear = AcademicYeartextField.getText().trim();
				    String schedCode = SchedCodetextField.getText().trim();
				    String subjectCode = SubjectCodetextField.getText().trim().toUpperCase();
				    String description = DescriptiontextField.getText().trim().toUpperCase();
				    String unit = UnittextField.getText().trim();
				    String creditUnit = CreditUnittextField.getText().trim();
				    String schedule1 = Schedule1textField.getText().trim().toUpperCase();
				    String schedule2 = Schedule2textField.getText().trim().toUpperCase();
				    String schedule3 = Schedule3textField.getText().trim().toUpperCase();
				    String schedule4 = Schedule4textField.getText().trim().toUpperCase();
				    String instructor = InstructortextField.getText().trim().toUpperCase();
				    

				    if (section.isEmpty() || semester.isEmpty() || academicYear.isEmpty() || schedCode.isEmpty() || 
				        subjectCode.isEmpty() || description.isEmpty() || unit.isEmpty() || creditUnit.isEmpty()) {
				        JOptionPane.showMessageDialog(this, "Only Schedule1, Schedule2, Schedule3, Schedule4, and Instructor fields can be null.", "Validation Error", JOptionPane.WARNING_MESSAGE);
				        return;
				    }

				    List<String> validSem = Arrays.asList("1st Sem", "2nd Sem");
				    if (!validSem.contains(semester)) {
				        JOptionPane.showMessageDialog(this, "Invalid Semester. Should only be: " + validSem, "Validation Error", JOptionPane.WARNING_MESSAGE);
				        return;
				    }

				    try {
				        Integer.parseInt(schedCode);
				        Integer.parseInt(unit);
				        Integer.parseInt(creditUnit);
				    } catch (NumberFormatException e) {
				        JOptionPane.showMessageDialog(this, "Schedule Code, Unit, and Credit Unit must be integers.", "Validation Error", JOptionPane.WARNING_MESSAGE);
				        return;
				    }

				    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
				        // Check if the record matches exactly
				        String exactMatchQuery = """
				            SELECT 1 
				            FROM Sections_Subjects 
				            WHERE SectionID = ? 
				              AND Section = ? 
				              AND Semester = ? 
				              AND AcademicYear = ? 
				              AND SchedCode = ? 
				              AND SubjectCode = ? 
				              AND Description = ? 
				              AND Unit = ? 
				              AND CreditUnit = ? 
				              AND Schedule1 = ? 
				              AND Schedule2 = ? 
				              AND Schedule3 = ? 
				              AND Schedule4 = ? 
				              AND Instructor = ?
				        """;

				        try (PreparedStatement exactMatchStmt = conn.prepareStatement(exactMatchQuery)) {
				            exactMatchStmt.setInt(1, Integer.parseInt(sectionID));
				            exactMatchStmt.setString(2, section);
				            exactMatchStmt.setString(3, semester);
				            exactMatchStmt.setString(4, academicYear);
				            exactMatchStmt.setInt(5, Integer.parseInt(schedCode));
				            exactMatchStmt.setString(6, subjectCode);
				            exactMatchStmt.setString(7, description);
				            exactMatchStmt.setString(8, unit);
				            exactMatchStmt.setString(9, creditUnit);
				            exactMatchStmt.setString(10, schedule1);
				            exactMatchStmt.setString(11, schedule2);
				            exactMatchStmt.setString(12, schedule3);
				            exactMatchStmt.setString(13, schedule4);
				            exactMatchStmt.setString(14, instructor);

				            try (ResultSet rs = exactMatchStmt.executeQuery()) {
				                if (rs.next()) {
				                    JOptionPane.showMessageDialog(this, "No changes were made.", "No Update Needed", JOptionPane.INFORMATION_MESSAGE);
				                    return;
				                }
				            }
				        }

				        
				        // Check for duplicates
				        String duplicateCheckQuery = """
				            SELECT 1 
				            FROM Sections_Subjects 
				            WHERE Section = ? 
				              AND Semester = ? 
				              AND AcademicYear = ? 
				              AND SchedCode = ? 
				              AND SubjectCode = ? 
				              AND SectionID != ?
				        """;

				        try (PreparedStatement duplicateCheckStmt = conn.prepareStatement(duplicateCheckQuery)) {
				            duplicateCheckStmt.setString(1, section);
				            duplicateCheckStmt.setString(2, semester);
				            duplicateCheckStmt.setString(3, academicYear);
				            duplicateCheckStmt.setInt(4, Integer.parseInt(schedCode));
				            duplicateCheckStmt.setString(5, subjectCode);
				            duplicateCheckStmt.setInt(6, Integer.parseInt(sectionID));

				            try (ResultSet rs = duplicateCheckStmt.executeQuery()) {
				                if (rs.next()) {
				                    JOptionPane.showMessageDialog(this, "A record with the same details already exists.", "Duplicate Record", JOptionPane.WARNING_MESSAGE);
				                    return;
				                }
				            }
				        }

				        
				        // Proceed with update
				        String updateQuery = """
				            UPDATE Sections_Subjects 
				            SET Section = ?, Semester = ?, AcademicYear = ?, SchedCode = ?, 
				                SubjectCode = ?, Description = ?, Unit = ?, CreditUnit = ?, 
				                Schedule1 = ?, Schedule2 = ?, Schedule3 = ?, Schedule4 = ?, 
				                Instructor = ? 
				            WHERE SectionID = ?
				        """;

				        try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
				            updateStmt.setString(1, section);
				            updateStmt.setString(2, semester);
				            updateStmt.setString(3, academicYear);
				            updateStmt.setInt(4, Integer.parseInt(schedCode));
				            updateStmt.setString(5, subjectCode);
				            updateStmt.setString(6, description);
				            updateStmt.setString(7, unit);
				            updateStmt.setString(8, creditUnit);
				            updateStmt.setString(9, schedule1);
				            updateStmt.setString(10, schedule2);
				            updateStmt.setString(11, schedule3);
				            updateStmt.setString(12, schedule4);
				            updateStmt.setString(13, instructor);
				            updateStmt.setInt(14, Integer.parseInt(sectionID));

				            int rowsUpdated = updateStmt.executeUpdate();
				            if (rowsUpdated > 0) {
				            	
				            	

				                // Update the table model
				                tableModelSubject.setValueAt(sectionID, selectedRow, 0);
				                tableModelSubject.setValueAt(section, selectedRow, 1);
				                tableModelSubject.setValueAt(semester, selectedRow, 2);
				                tableModelSubject.setValueAt(academicYear, selectedRow, 3);
				                tableModelSubject.setValueAt(schedCode, selectedRow, 4);
				                tableModelSubject.setValueAt(subjectCode, selectedRow, 5);
				                tableModelSubject.setValueAt(description, selectedRow, 6);
				                tableModelSubject.setValueAt(unit, selectedRow, 7);
				                tableModelSubject.setValueAt(creditUnit, selectedRow, 8);
				                tableModelSubject.setValueAt(schedule1, selectedRow, 9);
				                tableModelSubject.setValueAt(schedule2, selectedRow, 10);
				                tableModelSubject.setValueAt(schedule3, selectedRow, 11);
				                tableModelSubject.setValueAt(schedule4, selectedRow, 12);
				                tableModelSubject.setValueAt(instructor, selectedRow, 13);

				                
				                // Update AllTableData
				                for (Object[] row : AllTableData) {
				                    if (row[0].toString().equals(sectionID)) {
				                        row[1] = section;
				                        row[2] = semester;
				                        row[3] = academicYear;
				                        row[4] = schedCode;
				                        row[5] = subjectCode;
				                        row[6] = description;
				                        row[7] = unit;
				                        row[8] = creditUnit;
				                        row[9] = schedule1;
				                        row[10] = schedule2;
				                        row[11] = schedule3;
				                        row[12] = schedule4;
				                        row[13] = instructor;
				                        break;
				                    }
				                }
				                
				                JOptionPane.showMessageDialog(this, "Section Subjects updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
				            } else {
				                JOptionPane.showMessageDialog(this, "No rows were updated. Please check your inputs.", "Update Failed", JOptionPane.WARNING_MESSAGE);
				            }
				        }
				    } catch (Exception e) {
				        e.printStackTrace();
				        JOptionPane.showMessageDialog(this, "Error updating Section Subjects: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
				    }
				}

				            	
				            	
				      
				
				
				//Method to Delete a data inside our database as well as the row in our table
				private void DeleteData() {
				    // Get the currently selected row
				    int selectedRow = TableSectionSubject.getSelectedRow();
				    if (selectedRow == -1) {
				        JOptionPane.showMessageDialog(this, "Please select a row to Delete.", "No Selection", JOptionPane.WARNING_MESSAGE);
				        return;
				    }

				    // Confirm deletion
				    int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete the selected Section Subjects?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
				    if (confirm != JOptionPane.YES_OPTION) {
				        return;
				    }

				    // Get the SectionID, Semester, and AcademicYear from the selected row
				    String sectionID = Objects.toString(tableModelSubject.getValueAt(selectedRow, 0), "");
				    String section = Objects.toString(tableModelSubject.getValueAt(selectedRow, 1), "");
				    String semester = Objects.toString(tableModelSubject.getValueAt(selectedRow, 2), "");
				    String academicYear = Objects.toString(tableModelSubject.getValueAt(selectedRow, 3), "");
				    

				    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
				        // Check if there are no other subjects with the same section, semester, and academic year
				        String checkQuery = "SELECT COUNT(*) FROM Sections_Subjects WHERE Semester = ? AND AcademicYear = ? AND Section = ?";
				        try (PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
				            checkStmt.setString(1, semester);
				            checkStmt.setString(2, academicYear);
				            checkStmt.setString(3, section);

				            try (ResultSet rs = checkStmt.executeQuery()) {
				                if (rs.next() && rs.getInt(1) == 1) {
				                    JOptionPane.showMessageDialog(this, "Cannot Delete the only existing data of the selected \n    Section for this Academic Year and Semester.", "Deletion Restricted", JOptionPane.WARNING_MESSAGE);
				                    return;
				                }
				            }
				        }

				        // Proceed with deletion if there are other matching records
				        String deleteQuery = "DELETE FROM Sections_Subjects WHERE SectionID = ?";
				        try (PreparedStatement deleteStmt = conn.prepareStatement(deleteQuery)) {
				            deleteStmt.setString(1, sectionID);

				            int rowsDeleted = deleteStmt.executeUpdate();
				            if (rowsDeleted > 0) {
				                JOptionPane.showMessageDialog(this, "Section Subjects deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
				                
				                // Remove the row from AllTableData
				                AllTableData.removeIf(row -> row[0].toString().equals(sectionID));
				                
				                // Remove the row from the table model
				                tableModelSubject.removeRow(selectedRow);
				                
				                // Clear input fields
				                ClearData();
				            } else {
				                JOptionPane.showMessageDialog(this, "Failed to delete the Section Subjects.", "Error", JOptionPane.ERROR_MESSAGE);
				            }
				        }
				    } catch (Exception e) {
				        e.printStackTrace();
				        JOptionPane.showMessageDialog(this, "Error deleting Section Subjects: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
				    }
				}

				
			
			
			
				
			   // Method to Load data from the database and populate the table
				private List<Object[]> AllTableData = new ArrayList<>(); // Cache to hold all rows
			    private void LoadDataFromDatabase(Font poppinsSemiBold, Font poppinsBold2, Font poppinsSemiBold1, Font arvoBold) {
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

		
			        
			        
			        
			        
			        
			        
			       
							
					CustomButtonClear2 ClearB = new CustomButtonClear2("Clear", 18);
					ClearB.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold"));
					ClearB.setBounds(37, 678, 95, 37);
					contentPane.add(ClearB);
					ClearB.addActionListener(_ -> ClearData());
												
					CustomButtonAdd2 AddB = new CustomButtonAdd2("Add", 18);
					AddB.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold"));
					AddB.setBounds(150, 678, 95, 37);
					contentPane.add(AddB);
					AddB.addActionListener(_ -> AddData());
					
					CustomButtonUpdate2 UpdateB = new CustomButtonUpdate2("Update", 18);
					UpdateB.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold"));
					UpdateB.setBounds(263, 678, 95, 37);
					contentPane.add(UpdateB);
					UpdateB.addActionListener(_ -> UpdateData());
					
					CustomButtonDelete2 DeleteB = new CustomButtonDelete2("Delete", 18);
					DeleteB.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold"));
					DeleteB.setBounds(377, 678, 95, 37);
					contentPane.add(DeleteB);
					DeleteB.addActionListener(_ -> DeleteData());
					
					
					JLabel LineLabel = new JLabel("______________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________");
					LineLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
					LineLabel.setForeground(new Color(128, 128, 128));
					LineLabel.setBounds(0, -18, 2000, 25);
					contentPane.add(LineLabel);
				
					
					JLabel SectionSubjectsLabel = new JLabel("Section Subjects");
					SectionSubjectsLabel.setBounds(475, 315, 353, 31);
					SectionSubjectsLabel.setFont(AcadSyncFontManager.getFont("Arvo-Bold1"));
					
					contentPane.add(SectionSubjectsLabel);
					
					SemestertextField = new CustomRoundedTextField(10);
					SemestertextField.setColumns(100);
					SemestertextField.setBorderThickness(3);
					SemestertextField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
					SemestertextField.setBorderColor(new Color(32, 117, 120));
					SemestertextField.setBounds(564, 388, 231, 37);
					contentPane.add(SemestertextField);
					
					DescriptiontextField = new CustomRoundedTextField(10);
					DescriptiontextField.setColumns(100);
					DescriptiontextField.setBorderThickness(3);
					DescriptiontextField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
					DescriptiontextField.setBorderColor(new Color(32, 117, 120));
					DescriptiontextField.setBounds(564, 457, 231, 37);
					contentPane.add(DescriptiontextField);
				
					
					SectionIDtextField = new CustomRoundedTextField(10);
					SectionIDtextField.setColumns(100);
					SectionIDtextField.setBorderThickness(3);
					SectionIDtextField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
					SectionIDtextField.setBorderColor(new Color(32, 117, 120));
		            SectionIDtextField.setEditable(false);
					SectionIDtextField.setBounds(37, 388, 231, 37);
					
					contentPane.add(SectionIDtextField);
			
					SectiontextField = new CustomRoundedTextField(10);
					SectiontextField.setColumns(100);
					SectiontextField.setBorderThickness(3);
					SectiontextField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
					SectiontextField.setBorderColor(new Color(32, 117, 120));
					SectiontextField.setBounds(300, 388, 231, 37);
					contentPane.add(SectiontextField);
					
					SubjectCodetextField = new CustomRoundedTextField(10);
					SubjectCodetextField.setColumns(100);
					SubjectCodetextField.setBorderThickness(3);
					SubjectCodetextField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
					SubjectCodetextField.setBorderColor(new Color(32, 117, 120));
					SubjectCodetextField.setBounds(300, 457, 231, 37);
					contentPane.add(SubjectCodetextField);
					
					AcademicYeartextField = new CustomRoundedTextField(10);
					AcademicYeartextField.setFont(null);
					AcademicYeartextField.setColumns(100);
					AcademicYeartextField.setBorderThickness(3);
					AcademicYeartextField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
					AcademicYeartextField.setBorderColor(new Color(32, 117, 120));
					AcademicYeartextField.setBounds(831, 388, 231, 37);
					contentPane.add(AcademicYeartextField);
					
					
					UnittextField = new CustomRoundedTextField(10);
					UnittextField.setColumns(100);
					UnittextField.setBorderThickness(3);
					UnittextField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
					UnittextField.setBorderColor(new Color(32, 117, 120));
					UnittextField.setBounds(831, 457, 231, 37);
					contentPane.add(UnittextField);
					
					CreditUnittextField = new CustomRoundedTextField(10);
					CreditUnittextField.setColumns(100);
					CreditUnittextField.setBorderThickness(3);
					CreditUnittextField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
					CreditUnittextField.setBorderColor(new Color(32, 117, 120));
					CreditUnittextField.setBounds(37, 526, 231, 37);
					contentPane.add(CreditUnittextField);
					
					SchedCodetextField = new CustomRoundedTextField(10);
					SchedCodetextField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
					SchedCodetextField.setColumns(100);
					SchedCodetextField.setBorderThickness(3);
					SchedCodetextField.setBorderColor(new Color(32, 117, 120));
					SchedCodetextField.setBounds(37, 457, 231, 37);
					contentPane.add(SchedCodetextField);
					
					JLabel SectionIDLabel = new JLabel("Section ID");
					SectionIDLabel.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
					SectionIDLabel.setBounds(45, 373, 109, 13);
					contentPane.add(SectionIDLabel);
					
					JLabel CreditUnitLabel = new JLabel("Credit Units");
					CreditUnitLabel.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
					CreditUnitLabel.setBounds(45, 511, 95, 13);
					contentPane.add(CreditUnitLabel);
					
					JLabel DescriptionLabel = new JLabel("Description");
					DescriptionLabel.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
					DescriptionLabel.setBounds(573, 442, 122, 13);
					contentPane.add(DescriptionLabel);
					
					JLabel SectionLabel = new JLabel("Section");
					SectionLabel.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
					SectionLabel.setBounds(309, 373, 109, 13);
					contentPane.add(SectionLabel);
					
					JLabel SemesterLabel = new JLabel("Semester");
					SemesterLabel.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
					SemesterLabel.setBounds(573, 373, 95, 13);
					contentPane.add(SemesterLabel);
					
					JLabel SubjectCodeLabel = new JLabel("Subject Code");
					SubjectCodeLabel.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
					SubjectCodeLabel.setBounds(309, 442, 129, 13);
					contentPane.add(SubjectCodeLabel);
					
					JLabel AcademicYearLabel = new JLabel("Academic Year");
					AcademicYearLabel.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
					AcademicYearLabel.setBounds(838, 373, 122, 13);
					contentPane.add(AcademicYearLabel);
					
					JLabel UnitsLabel = new JLabel("Units");
					UnitsLabel.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
					UnitsLabel.setBounds(838, 442, 177, 13);
					contentPane.add(UnitsLabel);
					
					JLabel SchedCodeLabel = new JLabel("Sched Code");
					SchedCodeLabel.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
					SchedCodeLabel.setBounds(45, 442, 90, 13);
					contentPane.add(SchedCodeLabel);
					
					Schedule1textField = new CustomRoundedTextField(10);
					Schedule1textField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
					Schedule1textField.setColumns(100);
					Schedule1textField.setBorderThickness(3);
					Schedule1textField.setBorderColor(new Color(32, 117, 120));
					Schedule1textField.setBounds(300, 526, 231, 37);
					contentPane.add(Schedule1textField);
					
					Schedule2textField = new CustomRoundedTextField(10);
					Schedule2textField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
					Schedule2textField.setColumns(100);
					Schedule2textField.setBorderThickness(3);
					Schedule2textField.setBorderColor(new Color(32, 117, 120));
					Schedule2textField.setBounds(564, 526, 231, 37);
					contentPane.add(Schedule2textField);
					
					Schedule3textField = new CustomRoundedTextField(10);
					Schedule3textField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
					Schedule3textField.setColumns(100);
					Schedule3textField.setBorderThickness(3);
					Schedule3textField.setBorderColor(new Color(32, 117, 120));
					Schedule3textField.setBounds(831, 526, 231, 37);
					contentPane.add(Schedule3textField);
					
					Schedule4textField = new CustomRoundedTextField(10);
					Schedule4textField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
					Schedule4textField.setColumns(100);
					Schedule4textField.setBorderThickness(3);
					Schedule4textField.setBorderColor(new Color(32, 117, 120));
					Schedule4textField.setBounds(37, 593, 231, 37);
					contentPane.add(Schedule4textField);
					
					InstructortextField = new CustomRoundedTextField(10);
					InstructortextField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
					InstructortextField.setColumns(100);
					InstructortextField.setBorderThickness(3);
					InstructortextField.setBorderColor(new Color(32, 117, 120));
					InstructortextField.setBounds(300, 593, 231, 37);
					contentPane.add(InstructortextField);
					
					
					JLabel Schedule1Label = new JLabel("Schedule 1");
					Schedule1Label.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
					Schedule1Label.setBounds(309, 511, 90, 13);
					contentPane.add(Schedule1Label);
					
					JLabel Schedule2Label = new JLabel("Schedule 2");
					Schedule2Label.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
					Schedule2Label.setBounds(573, 511, 90, 13);
					contentPane.add(Schedule2Label);
					
					JLabel Schedule3Label = new JLabel("Schedule 3");
					Schedule3Label.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
					Schedule3Label.setBounds(838, 511, 90, 13);
					contentPane.add(Schedule3Label);
					
					JLabel Schedule4Label = new JLabel("Schedule 4");
					Schedule4Label.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
					Schedule4Label.setBounds(45, 578, 90, 13);
					contentPane.add(Schedule4Label);
					
					JLabel InstructorLabel = new JLabel("Instructor");
					InstructorLabel.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
					InstructorLabel.setBounds(309, 578, 90, 13);
					contentPane.add(InstructorLabel);
					
					   // Add a mouse listener to the table
					   TableSectionSubject.addMouseListener(new java.awt.event.MouseAdapter() {
					        @Override
					        public void mouseClicked(java.awt.event.MouseEvent event) {
					            int selectedRow = TableSectionSubject.getSelectedRow();
					            

					            if (selectedRow != -1) {
					                // Safely retrieve data from the selected row
					            	String sectionID = Objects.toString(tableModelSubject.getValueAt(selectedRow, 0), "");
					            	String section = Objects.toString(tableModelSubject.getValueAt(selectedRow, 1), "");
					            	String semester = Objects.toString(tableModelSubject.getValueAt(selectedRow, 2), "");
					            	String academicYear = Objects.toString(tableModelSubject.getValueAt(selectedRow, 3), "");
					            	String schedCode = Objects.toString(tableModelSubject.getValueAt(selectedRow, 4), "");
					            	String subjectCode = Objects.toString(tableModelSubject.getValueAt(selectedRow, 5), "");
					            	String description = Objects.toString(tableModelSubject.getValueAt(selectedRow, 6), "");
					             	String unit = Objects.toString(tableModelSubject.getValueAt(selectedRow, 7), "");
					            	String creditUnit = Objects.toString(tableModelSubject.getValueAt(selectedRow, 8), "");
					            	String schedule1 = Objects.toString(tableModelSubject.getValueAt(selectedRow, 9), "");
					            	String schedule2 = Objects.toString(tableModelSubject.getValueAt(selectedRow, 10), "");
					            	String schedule3 = Objects.toString(tableModelSubject.getValueAt(selectedRow, 11), "");
					            	String schedule4 = Objects.toString(tableModelSubject.getValueAt(selectedRow, 12), "");
					            	String instructor = Objects.toString(tableModelSubject.getValueAt(selectedRow, 13), "");
					            	
					                     	
					            	
					                // Set data to text fields
					                SectionIDtextField.setText(sectionID);
					                SectiontextField.setText(section.toUpperCase());
					                SemestertextField.setText(semester);
					                AcademicYeartextField.setText(academicYear);
					                SchedCodetextField.setText(schedCode);
					                SubjectCodetextField.setText(subjectCode.toUpperCase());
					                DescriptiontextField.setText(description.toUpperCase());
					                UnittextField.setText(unit);
					                CreditUnittextField.setText(creditUnit);
					                Schedule1textField.setText(schedule1.toUpperCase());
						            Schedule2textField.setText(schedule2.toUpperCase());
						            Schedule3textField.setText(schedule3.toUpperCase());
						            Schedule4textField.setText(schedule4.toUpperCase());
						            InstructortextField.setText(instructor.toUpperCase());
						            
						            
						            SectionIDtextField.setEditable(false);
						            SectiontextField.setEditable(false);
						            SemestertextField.setEditable(false);
						            AcademicYeartextField.setEditable(false);
						            SubjectCodetextField.setEditable(false);
						            
					                    
								    
					            }
					        }
					    });
					
			
					
				
					
					
					
					
					
					
	
		
		
		
	}
}




