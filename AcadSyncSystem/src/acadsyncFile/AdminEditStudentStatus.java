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



public class AdminEditStudentStatus extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable TableStudentStatus;
	private DefaultTableModel tableModelStatus;

	// Database credentials
    private String DB_URL = DatabaseCredentials.getDBURL();
    private String DB_USER = DatabaseCredentials.getDBUSER();
    private String DB_PASSWORD = DatabaseCredentials.getDBPASS();
	

	private CustomRoundedTextField EnrollmentIDtextField;
    private CustomRoundedTextField StudentNumbertextField;
	private CustomRoundedTextField LastNametextField;
    private CustomRoundedTextField FirstNametextField;
    private CustomRoundedTextField MiddleNametextField;
    private CustomRoundedTextField SuffixNametextField;
    private CustomRoundedTextField SectiontextField;
    private CustomRoundedTextField SemestertextField;
    private CustomRoundedTextField AcademicYeartextField;
    private CustomRoundedTextField YearLeveltextField;
    private CustomRoundedTextField StatustextField;
    private CustomRoundedTextField StatetextField;
    private CustomRoundedTextField TypetextField;
	
	private JTextField SearchtextField;
	 
	
	public static void main(String[] args) {
		 
	    EventQueue.invokeLater(() -> {
	        try {
	        	AdminEditStudentStatus frame = new AdminEditStudentStatus();
	            frame.setVisible(true);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    });
	}

	       
	/**
	 * Create the frame.
	 */
	public AdminEditStudentStatus() {
		setTitle("Edit Student Status");
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/SMS Logo.png"));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1115, 755);
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
	        	 AdminStudentStatus AdminStudentStatusNoNavigation = new AdminStudentStatus();
		         AdminStudentStatusNoNavigation.setVisible(true);
		        	 
		         dispose();
	        }
	    }); 
		
		
		
		    tableModelStatus = new DefaultTableModel() {
	        private static final long serialVersionUID = 1L;
			
	         @Override
	         public boolean isCellEditable(int row, int column) {
	             return false;  // Prevent all cells from being editable
	         	}
	     	};
	     
	     	 TableStudentStatus = new JTable(tableModelStatus);
		     TableStudentStatus = new JTable(tableModelStatus);
		     TableStudentStatus.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Only one row can be selected at a time
		     TableStudentStatus.setBounds(70, 10, 600, 200);
		     

	        // Set font and styling for rows
	        if (AcadSyncFontManager.getFont("Poppins-SemiBold1") != null) {
	            TableStudentStatus.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
	        } else {
	            System.out.println("Custom font not loaded; using default font.");
	        }
	        
	        
	        TableStudentStatus.setRowHeight(30);
	        TableStudentStatus.setBackground(Color.WHITE);
	        TableStudentStatus.setGridColor(Color.BLACK);
	        TableStudentStatus.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));

	        
	        
	        // Customize table header font
	        if (AcadSyncFontManager.getFont("Poppins-SemiBold") != null) {
	            TableStudentStatus.getTableHeader().setFont(AcadSyncFontManager.getFont("Poppins-SemiBold").deriveFont(13f)); // Larger font for header
	            TableStudentStatus.getTableHeader().setBackground(Color.DARK_GRAY); // Header background color
	            TableStudentStatus.getTableHeader().setForeground(Color.WHITE); // Header text color
	        }

	        // Custom cell renderer for selected row behavior
	        TableStudentStatus.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
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
	                    component.setBackground(new Color(255, 194, 175)); // Custom selected row background color
	                    component.setForeground(Color.BLACK); // Custom selected row foreground color
	                } else {
	                    component.setBackground(Color.WHITE); // Default background color
	                    component.setForeground(Color.BLACK); // Default text color
	                }
	                return component;
	            }
	        }); 

	        
	        
	        // Column names
	        tableModelStatus.addColumn("ENROLLMENT ID");
	        tableModelStatus.addColumn("STUDENT NUMBER");
	        tableModelStatus.addColumn("LAST NAME");
	        tableModelStatus.addColumn("FIRST NAME");
	        tableModelStatus.addColumn("MIDDLE NAME");
	        tableModelStatus.addColumn("SUFFIX NAME");
	        tableModelStatus.addColumn("ACADEMIC YEAR");
	        tableModelStatus.addColumn("SECTION");
	        tableModelStatus.addColumn("SEMESTER");
	        tableModelStatus.addColumn("YEAR LEVEL");
	        tableModelStatus.addColumn("STATUS");
	        tableModelStatus.addColumn("STATE");
	        tableModelStatus.addColumn("TYPE");
	        

	       
	        // Add table to a scroll pane
	        JScrollPane scrollPane = new JScrollPane(TableStudentStatus);
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
                    tableModelStatus.setRowCount(0); // Clear the table

                    // Add all rows back to the table model from the full data list
                    for (Object[] row : AllTableData) { // `AllTableData` contains the full data set
                  	  tableModelStatus.addRow(row);
                    }
                }
            });	  
            
            
            
            // Load data from database
	        LoadDataFromDatabase(AcadSyncFontManager.getFont("Poppins-SemiBold1"), AcadSyncFontManager.getFont("Poppins-Bold3"), AcadSyncFontManager.getFont("Poppins-SemiBold"), AcadSyncFontManager.getFont("Arvo-Bold1"));
	
			}
	
              
	

		
		
		
		
		
		
//----------------------------------------------------------------------------Method------------------------------------------------------------------------
		
	
	
	
	
	
		
							//Method for Search a data inside our JtextField 
							private void SearchData(String searchTerm) {
							    tableModelStatus.setRowCount(0); // Clear the table
						
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
							            tableModelStatus.addRow(row);
							        }
							    }
							}
				
						
				
				
							
					
							//Method to Clear the text inside our JtextField 
							private void ClearData() {
						    	  // Clear all the text fields
						            LastNametextField.setText("");
						            FirstNametextField.setText("");
						            MiddleNametextField.setText("");
						            SuffixNametextField.setText("");
						            YearLeveltextField.setText("");
						            SectiontextField.setText("");
						            SemestertextField.setText("");
						            EnrollmentIDtextField.setText("");
						            AcademicYeartextField.setText("");
						            StudentNumbertextField.setText("");
						            StatustextField.setText("");
						            StatetextField.setText("");
						            TypetextField.setText("");
						          
						
						            //Reset selected items or clear any custom selections
						            TableStudentStatus.clearSelection();
						
						        }
							
							   
							
							
							
							//Method to Add a data to our database as well as the row in our table 
							private void AddData() {
							    String studentNumber = StudentNumbertextField.getText().trim();
							    String lastName = LastNametextField.getText().trim();
							    String firstName = FirstNametextField.getText().trim();
							    String middleName = MiddleNametextField.getText().trim();
							    String suffixName = SuffixNametextField.getText().trim();
							    String academicyear = AcademicYeartextField.getText().trim();
							    String section = SectiontextField.getText().trim().toUpperCase();
							    String semester = SemestertextField.getText().trim();
							    String yearLevel = YearLeveltextField.getText().trim();
							    String status = StatustextField.getText().trim();
							    String state = StatetextField.getText().trim();
							    String type = TypetextField.getText().trim();
				
							    
							    
							    // Check required fields
							    if (studentNumber.isEmpty() || firstName.isEmpty() || middleName.isEmpty() || lastName.isEmpty() || academicyear.isEmpty() || section.isEmpty() || semester.isEmpty() ||
							    		yearLevel.isEmpty() ||  status.isEmpty() || state.isEmpty() || type.isEmpty()) {
							        JOptionPane.showMessageDialog(this, "All Fields are Required.", "Validation Error", JOptionPane.WARNING_MESSAGE);
							        return;
							    }
							    
							    // Define the list of valid status
							    List<String> validStatus = Arrays.asList("Enrolled",  "Passed", "Pending", "Dropped Out", "Not Enrolled");
							    
				
							    // Validate Status against the list of valid status
							    if (!validStatus.contains(status)) {
							        JOptionPane.showMessageDialog(this, "Invalid Status. Should only be: " + validStatus, "Validation Error", JOptionPane.WARNING_MESSAGE);
							        return;
							    }
				
							    // Define the list of valid state
							    List<String> validState = Arrays.asList("Regular",  "Irregular");
							    if (!validState.contains(state)) {
							        JOptionPane.showMessageDialog(this, "Invalid Status. Should only be: " + validState, "Validation Error", JOptionPane.WARNING_MESSAGE);
							        return;
							    }
							    
				
							    // Define the list of valid type
							    List<String> validType = Arrays.asList("New",  "Old");
							    if (!validType.contains(type)) {
							        JOptionPane.showMessageDialog(this, "Invalid Type. Should only be: " + validType, "Validation Error", JOptionPane.WARNING_MESSAGE);
							        return;
							    }
							    
							    
							    // Validate integer fields
							    try {
							        Integer.parseInt(studentNumber);
							    } catch (NumberFormatException e) {
							        JOptionPane.showMessageDialog(this, "Student Number must be an integer.", "Validation Error", JOptionPane.WARNING_MESSAGE);
							        return;
							    }
							    
							    
							    
							    // Check if the Section exists in Sections_Subjects table
							    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
							        String sectionCheckQuery = "SELECT 1 FROM Sections_Subjects WHERE TRIM(Section) = ? AND TRIM(Semester) = ? AND TRIM(AcademicYear) = ?";
							        try (PreparedStatement sectionCheckStmt = conn.prepareStatement(sectionCheckQuery)) {
							            sectionCheckStmt.setString(1, section);
							            sectionCheckStmt.setString(2, semester);
							            sectionCheckStmt.setString(3, academicyear);
							            try (ResultSet sectionRs = sectionCheckStmt.executeQuery()) {
							                if (!sectionRs.next()) {
							                    JOptionPane.showMessageDialog(this, "The entered Section does not exist for the selected \n                  Semester and Academic Year.", "Section Validation Error", JOptionPane.WARNING_MESSAGE);
							                    return;
							                }
							            }
							        }
							    } catch (Exception e) {
							        e.printStackTrace();
							        JOptionPane.showMessageDialog(this, "Error checking section validity: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
							        return;
							    }
				
				
				
							    // Check for duplicate record
							    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
							    	String checkQuery = "SELECT * FROM Enrollment_Status WHERE TRIM(StudentNumber) = ? AND TRIM(FirstName) = ? AND TRIM(MiddleName) = ? AND TRIM(LastName) = ? AND (SuffixName = ? OR (SuffixName IS NULL AND ? IS NULL)) AND TRIM(AcademicYear) = ? AND TRIM(Section) = ? AND TRIM(Semester) = ? AND TRIM(YearLevel) = ? AND TRIM(Status) = ? AND TRIM(State) = ? AND TRIM(Type) = ?";
							    	try (PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
							    	    checkStmt.setString(1, studentNumber);
							    	    checkStmt.setString(2, firstName);
							    	    checkStmt.setString(3, middleName);
							    	    checkStmt.setString(4, lastName);
							    	    checkStmt.setString(5, suffixName.isEmpty() ? null : suffixName);
							    	    checkStmt.setString(6, suffixName.isEmpty() ? null : suffixName);
							    	    checkStmt.setString(7, academicyear);
							    	    checkStmt.setString(8, section);
							    	    checkStmt.setString(9, semester);
							    	    checkStmt.setString(10, yearLevel);
							    	    checkStmt.setString(11, status);
							    	    checkStmt.setString(12, state);
							    	    checkStmt.setString(13, type);
							    	    
				
							    	    try (ResultSet rs = checkStmt.executeQuery()) {
							    	        if (rs.next()) {
							    	            JOptionPane.showMessageDialog(this, "Data already exists.", "Duplicate Record", JOptionPane.WARNING_MESSAGE);
							    	            return;
							    	        }
							    	    }
							    	}
							    	
							    	
							    	 // Get the next EnrollmentID
							        String getMaxIdQuery = "SELECT MAX(EnrollmentID) FROM Enrollment_Status";
							        int newEnrollmentId = 1; // Default for the first record
				
							        try (Statement stmt = conn.createStatement();
							             ResultSet rs = stmt.executeQuery(getMaxIdQuery)) {
							            if (rs.next()) {
							                newEnrollmentId = rs.getInt(1) + 1; // Increment the max ID
							            }
							        }
				 
				
							        // Insert new student
							        String insertQuery = "INSERT INTO Enrollment_Status (StudentNumber, FirstName, MiddleName, LastName, SuffixName, AcademicYear, Section, Semester, YearLevel, Status, State, Type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
							        try (PreparedStatement InsertStmt = conn.prepareStatement(insertQuery)) {
							            InsertStmt.setString(1, studentNumber);
							            InsertStmt.setString(2, firstName);
							            InsertStmt.setString(3, middleName);
							            InsertStmt.setString(4, lastName);
							            InsertStmt.setString(5, suffixName.isEmpty() ? null : suffixName);
							            InsertStmt.setString(6, academicyear);
							            InsertStmt.setString(7, section);
							            InsertStmt.setString(8, semester);
							            InsertStmt.setString(9, yearLevel);
							            InsertStmt.setString(10, status);
							            InsertStmt.setString(11, state);
							            InsertStmt.setString(12, type);
								         
				
							            InsertStmt.executeUpdate();
							    
				
							            // Clear input fields and reload data
							            ClearData();
							            JOptionPane.showMessageDialog(this, "Student Status Successfully Added!", "Success", JOptionPane.INFORMATION_MESSAGE);
				
							            
							            // Add new row to the table model
							            tableModelStatus.addRow(new Object[] {
							                newEnrollmentId,
							            	studentNumber,
							                lastName,
							                firstName,
							                middleName,
							                suffixName,
							                academicyear,
							                section,
							                semester,
							                yearLevel,
							                status,
							                state,
							                type
							            });
							            
							            
							            //Add new record to AllTableData
							            AllTableData.add(new Object[]{
							            	newEnrollmentId, 
							            	Integer.parseInt(studentNumber),
							                lastName, 
							                firstName, 
							                middleName, 
							                suffixName, 
							                academicyear,
							                section, 
							                semester, 
							                yearLevel, 
							                status, 
							                state,
							                type
							            });
							            
							            
							          
							        }
							    } catch (Exception e) {
							        e.printStackTrace();
							        JOptionPane.showMessageDialog(this, "Error adding Student Status: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
							    }
							}
				
							
							
							
							
							
							//Method to Update a data inside our database as well as the row in our table
							private void UpdateData() {
							    // Get the currently selected row
							    int selectedRow = TableStudentStatus.getSelectedRow();
							    if (selectedRow == -1) {
							        JOptionPane.showMessageDialog(this, "Please select a row to Update.", "No Selection", JOptionPane.WARNING_MESSAGE);
							        return;
							    }
				
							    // Get values from text fields
							    String enrollmentId = EnrollmentIDtextField.getText().trim();
							    String studentNumber = StudentNumbertextField.getText().trim();
							    String lastName = LastNametextField.getText().trim();
							    String firstName = FirstNametextField.getText().trim();
							    String middleName = MiddleNametextField.getText().trim();
							    String suffixName = SuffixNametextField.getText().trim();
							    String academicyear = AcademicYeartextField.getText().trim();
							    String section = SectiontextField.getText().trim().toUpperCase();
							    String semester = SemestertextField.getText().trim();
							    String yearLevel = YearLeveltextField.getText().trim();
							    String status = StatustextField.getText().trim();
							    String state = StatetextField.getText().trim();
							    String type = TypetextField.getText().trim();
				
							    // Validate fields
							    if (studentNumber.isEmpty() || firstName.isEmpty() || middleName.isEmpty() || lastName.isEmpty() ||
							        academicyear.isEmpty() || section.isEmpty() || semester.isEmpty() || yearLevel.isEmpty() || status.isEmpty() || state.isEmpty() || type.isEmpty()) {
							        JOptionPane.showMessageDialog(this, "Please fill in all required fields.", "Validation Error", JOptionPane.WARNING_MESSAGE);
							        return;
							    }
				
							    // Define valid status
							    List<String> validStatus = Arrays.asList("Enrolled", "Passed", "Pending", "Dropped Out", "Not Enrolled");
							    if (!validStatus.contains(status)) {
							        JOptionPane.showMessageDialog(this, "Invalid Status. Should only be: " + validStatus, "Validation Error", JOptionPane.WARNING_MESSAGE);
							        return;
							    }
							    
							    
							    // Define the list of valid state
							    List<String> validState = Arrays.asList("Regular",  "Irregular");
							    if (!validState.contains(state)) {
							        JOptionPane.showMessageDialog(this, "Invalid State. Should only be: " + validState, "Validation Error", JOptionPane.WARNING_MESSAGE);
							        return;
							    }
							    
							    // Define the list of valid type
							    List<String> validType = Arrays.asList("New",  "Old");
							    if (!validType.contains(type)) {
							        JOptionPane.showMessageDialog(this, "Invalid Type. Should only be: " + validType, "Validation Error", JOptionPane.WARNING_MESSAGE);
							        return;
							    }
				
							    
							    // Validate integer fields
							    try {
							        Integer.parseInt(studentNumber);
							    } catch (NumberFormatException e) {
							        JOptionPane.showMessageDialog(this, "Student Number must be an integer.", "Validation Error", JOptionPane.WARNING_MESSAGE);
							        return;
							    }
				
							    // Validate section existence in the database
							    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
							        String sectionCheckQuery = "SELECT 1 FROM Sections_Subjects WHERE TRIM(Section) = ? AND TRIM(Semester) = ? AND TRIM(AcademicYear) = ?";
							        try (PreparedStatement sectionCheckStmt = conn.prepareStatement(sectionCheckQuery)) {
							            sectionCheckStmt.setString(1, section);
							            sectionCheckStmt.setString(2, semester);
							            sectionCheckStmt.setString(3, academicyear);
							            try (ResultSet sectionRs = sectionCheckStmt.executeQuery()) {
							                if (!sectionRs.next()) {
							                    JOptionPane.showMessageDialog(this, "The entered Section does not exist for the selected \n                  Semester and Academic Year.", "Section Validation Error", JOptionPane.WARNING_MESSAGE);
							                    return;
							                }
							            }
							        }
							    } catch (Exception e) {
							        e.printStackTrace();
							        JOptionPane.showMessageDialog(this, "Error checking section validity: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
							        return;
							    }
				
							    
							    
							    // Update database
							    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
							        String updateQuery = "UPDATE Enrollment_Status SET AcademicYear = ?, Section = ?, Semester = ?, YearLevel = ?, Status = ?, State = ?, Type = ? WHERE EnrollmentID = ?";
							        try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
							            updateStmt.setString(1, academicyear);
							            updateStmt.setString(2, section);
							            updateStmt.setString(3, semester);
							            updateStmt.setString(4, yearLevel);
							            updateStmt.setString(5, status);
							            updateStmt.setString(6, state);
							            updateStmt.setString(7, type);
							            updateStmt.setString(8, enrollmentId);
				
							            int rowsUpdated = updateStmt.executeUpdate();
				
							            if (rowsUpdated > 0) {
							                JOptionPane.showMessageDialog(this, "Student Status updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
				
							                // Update the table model
							                tableModelStatus.setValueAt(enrollmentId, selectedRow, 0);
							                tableModelStatus.setValueAt(studentNumber, selectedRow, 1);
							                tableModelStatus.setValueAt(lastName, selectedRow, 2);
							                tableModelStatus.setValueAt(firstName, selectedRow, 3);
							                tableModelStatus.setValueAt(middleName, selectedRow, 4);
							                tableModelStatus.setValueAt(suffixName, selectedRow, 5);
							                tableModelStatus.setValueAt(academicyear, selectedRow, 6);
							                tableModelStatus.setValueAt(section, selectedRow, 7);
							                tableModelStatus.setValueAt(semester, selectedRow, 8);
							                tableModelStatus.setValueAt(yearLevel, selectedRow, 9);
							                tableModelStatus.setValueAt(status, selectedRow, 10);
							                tableModelStatus.setValueAt(state, selectedRow, 11);
							                tableModelStatus.setValueAt(type, selectedRow, 12);
				
							                
							                // Update AllTableData
							                for (Object[] row : AllTableData) {
							                    if (row[0].toString().equals(enrollmentId)) { // Match by EnrollmentID
							                        row[1] = studentNumber;
							                        row[2] = lastName;
							                        row[3] = firstName;
							                        row[4] = middleName;
							                        row[5] = suffixName;
							                        row[6] = academicyear;
							                        row[7] = section;
							                        row[8] = semester;
							                        row[9] = yearLevel;
							                        row[10] = status;
							                        row[11] = state;
							                        row[12] = type;
							                        break;
							                    }
							                }
				
							                // Refresh table
							                tableModelStatus.fireTableDataChanged();
							                TableStudentStatus.setRowSelectionInterval(selectedRow, selectedRow);
				
							            } else {
							                JOptionPane.showMessageDialog(this, "Failed to update Student Status.", "Error", JOptionPane.ERROR_MESSAGE);
							            }
							        }
							    } catch (Exception e) {
							        e.printStackTrace();
							        JOptionPane.showMessageDialog(this, "Error updating Student Status: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
							    }
							}
				
							
							
							
							
							
							//Method to Delete a data inside our database as well as the row in our table
							private void DeleteData() {
							    // Get the currently selected row
							    int selectedRow = TableStudentStatus.getSelectedRow();
							    if (selectedRow == -1) {
							        JOptionPane.showMessageDialog(this, "Please select a row to Delete.", "No Selection", JOptionPane.WARNING_MESSAGE);
							        return;
							    }
				
							    // Confirm deletion
							    int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete the selected Student Status?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
							    if (confirm != JOptionPane.YES_OPTION) {
							        return;
							    }
				
							    // Get the EnrollmentID from the selected row
							    String enrollmentId = Objects.toString(tableModelStatus.getValueAt(selectedRow, 0), "");
				
							    // Delete from the database
							    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
							        String deleteQuery = "DELETE FROM Enrollment_Status WHERE EnrollmentID = ?";
							        try (PreparedStatement deleteStmt = conn.prepareStatement(deleteQuery)) {
							            deleteStmt.setString(1, enrollmentId);
				
							            int rowsDeleted = deleteStmt.executeUpdate();
							            if (rowsDeleted > 0) {
							                JOptionPane.showMessageDialog(this, "Student Status deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
				
							                // Remove the row from AllTableData
							                AllTableData.removeIf(row -> row[0].toString().equals(enrollmentId));
				
							                // Remove the row from the table model
							                tableModelStatus.removeRow(selectedRow);
				
							                // Clear input fields
							                ClearData();
							            } else {
							                JOptionPane.showMessageDialog(this, "Failed to delete the Student Status.", "Error", JOptionPane.ERROR_MESSAGE);
							            }
							        }
							    } catch (Exception e) {
							        e.printStackTrace();
							        JOptionPane.showMessageDialog(this, "Error deleting Student Status: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
							    }
							}
				
				
				
							
							
				
							
						   // Method to Load data from the database and populate the table
							private List<Object[]> AllTableData = new ArrayList<>(); // Cache to hold all rows
					        private void LoadDataFromDatabase(Font semiBoldFont1, Font bold3Font, Font semiBold3, Font arvo1Font) {
						        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
						             Statement stmt = conn.createStatement();
						             ResultSet rs = stmt.executeQuery("SELECT * FROM Enrollment_Status")) {
						        	
						        	  // Clear the existing table data and cache
						            tableModelStatus.setRowCount(0);
						            AllTableData.clear();
				
						            while (rs.next()) {
						                // Create a row object from the ResultSet
						                Object[] row = new Object[] {
						                    rs.getString("EnrollmentID"),
						                    rs.getString("StudentNumber"),
						                    rs.getString("LastName"),
						                    rs.getString("FirstName"),
						                    rs.getString("MiddleName"),
						                    rs.getString("SuffixName"),
						                    rs.getString("AcademicYear"),
						                    rs.getString("Section"),
						                    rs.getString("Semester"),
						                    rs.getString("YearLevel"),
						                    rs.getString("Status"),
						                    rs.getString("State"),
						                    rs.getString("Type")
						               
						                };
				
						                // Add the row to the cache
						                AllTableData.add(row);
				
						                // Add the row to the table model
						                tableModelStatus.addRow(row);
						            }
						        } catch (Exception e) {
						            e.printStackTrace();
						            JOptionPane.showMessageDialog(this, "Error loading data from database: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
						        }
						        
						        
						        
						        
		       
	        
		        
		        
		        


		        
		        
		        
		   
//----------------------------------------------------------------------------------------------------------------------------------------------------------	        
	
				
		        
		        
					
				CustomButtonClear3 ClearB = new CustomButtonClear3("Clear", 18);	
				ClearB.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold"));
				ClearB.setBounds(37, 662, 95, 37);
				contentPane.add(ClearB);
				ClearB.addActionListener(_ -> ClearData());
						
				CustomButtonAdd3 AddB = new CustomButtonAdd3("Add", 18);
				AddB.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold"));
				AddB.setBounds(150, 662, 95, 37);
				contentPane.add(AddB);
				AddB.addActionListener(_ -> AddData());
					
				CustomButtonDelete3 DeleteB = new CustomButtonDelete3("Delete", 18);
			    DeleteB.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold"));
				DeleteB.setBounds(377, 662, 95, 37);
				contentPane.add(DeleteB);
				DeleteB.addActionListener(_ -> DeleteData());
					
				CustomButtonUpdate3 UpdateB = new CustomButtonUpdate3("Update", 18);
				UpdateB.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold"));
				UpdateB.setBounds(263, 662, 95, 37);
				contentPane.add(UpdateB);
				UpdateB.addActionListener(_ -> UpdateData());
				
				JLabel LineLabel = new JLabel("______________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________");
				LineLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
				LineLabel.setForeground(new Color(128, 128, 128));
				LineLabel.setBounds(0, -18, 2000, 25);
				contentPane.add(LineLabel);
			
				
				JLabel StudentEnrollmentLabel = new JLabel("Student Enrollment");
				StudentEnrollmentLabel.setBounds(432, 315, 353, 31);
				StudentEnrollmentLabel.setFont(AcadSyncFontManager.getFont("Arvo-Bold1"));
				
				contentPane.add(StudentEnrollmentLabel);
				
				
				JLabel Status1Label = new JLabel("Status");
				Status1Label.setBounds(521, 348, 200, 31);
				Status1Label.setFont(AcadSyncFontManager.getFont("Arvo-Bold1"));
				
				contentPane.add(Status1Label);
				
				SuffixNametextField = new CustomRoundedTextField(10);
				SuffixNametextField.setColumns(100);
				SuffixNametextField.setBorderThickness(3);
				SuffixNametextField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
				SuffixNametextField.setBorderColor(new Color(232, 0, 85));
				SuffixNametextField.setBounds(300, 504, 231, 37);
				SuffixNametextField.setEditable(false);
				contentPane.add(SuffixNametextField);
				
				SectiontextField = new CustomRoundedTextField(10);
				SectiontextField.setColumns(100);
				SectiontextField.setBorderThickness(3);
				SectiontextField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
				SectiontextField.setBorderColor(new Color(232, 0, 85));
				SectiontextField.setBounds(831, 504, 231, 37);
				contentPane.add(SectiontextField);
				
				
				EnrollmentIDtextField = new CustomRoundedTextField(10);
				EnrollmentIDtextField.setColumns(100);
				EnrollmentIDtextField.setBorderThickness(3);
				EnrollmentIDtextField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
				EnrollmentIDtextField.setBorderColor(new Color(232, 0, 85));
				EnrollmentIDtextField.setBounds(36, 430, 231, 37);
				EnrollmentIDtextField.setEditable(false);
				contentPane.add(EnrollmentIDtextField);
				
				MiddleNametextField = new CustomRoundedTextField(10);
				MiddleNametextField.setColumns(100);
				MiddleNametextField.setBorderThickness(3);
				MiddleNametextField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
				MiddleNametextField.setBorderColor(new Color(232, 0, 85));
				MiddleNametextField.setBounds(36, 504, 231, 37);
				MiddleNametextField.setEditable(false);
				contentPane.add(MiddleNametextField);
				
				YearLeveltextField = new CustomRoundedTextField(10);
				YearLeveltextField.setColumns(100);
				YearLeveltextField.setBorderThickness(3);
				YearLeveltextField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
				YearLeveltextField.setBorderColor(new Color(232, 0, 85));
				YearLeveltextField.setBounds(300, 575, 231, 37);
				contentPane.add(YearLeveltextField);
				
				
				StudentNumbertextField = new CustomRoundedTextField(10);
				StudentNumbertextField.setColumns(100);
				StudentNumbertextField.setBorderThickness(3);
				StudentNumbertextField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
				StudentNumbertextField.setBorderColor(new Color(232, 0, 85));
				StudentNumbertextField.setBounds(300, 430, 231, 37);
				StudentNumbertextField.setEditable(false);
				contentPane.add(StudentNumbertextField);
	
				StatustextField = new CustomRoundedTextField(10);
				StatustextField.setColumns(100);
				StatustextField.setBorderThickness(3);
				StatustextField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
				StatustextField.setBorderColor(new Color(232, 0, 85));
				StatustextField.setBounds(564, 575, 231, 37);
				contentPane.add(StatustextField);
				
				LastNametextField = new CustomRoundedTextField(10);
				LastNametextField.setColumns(100);
				LastNametextField.setBorderThickness(3);
				LastNametextField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
				LastNametextField.setBorderColor(new Color(232, 0, 85));
				LastNametextField.setBounds(564, 430, 231, 37);
				LastNametextField.setEditable(false);
				contentPane.add(LastNametextField);
				
				FirstNametextField = new CustomRoundedTextField(10);
				FirstNametextField.setColumns(100);
				FirstNametextField.setBorderThickness(3);
				FirstNametextField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
				FirstNametextField.setBorderColor(new Color(232, 0, 85));
				FirstNametextField.setBounds(831, 430, 231, 37);
				FirstNametextField.setEditable(false);
				contentPane.add(FirstNametextField);
				
				SemestertextField = new CustomRoundedTextField(10);
				SemestertextField.setColumns(100);
				SemestertextField.setBorderThickness(3);
				SemestertextField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
				SemestertextField.setBorderColor(new Color(232, 0, 85));
				SemestertextField.setBounds(36, 575, 231, 37);
				contentPane.add(SemestertextField);
				
				AcademicYeartextField = new CustomRoundedTextField(10);
				AcademicYeartextField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
				AcademicYeartextField.setColumns(100);
				AcademicYeartextField.setBorderThickness(3);
				AcademicYeartextField.setBorderColor(new Color(232, 0, 85));
				AcademicYeartextField.setBounds(564, 504, 231, 37);
				contentPane.add(AcademicYeartextField);
				
				
				
				JLabel EnrollmentIDLabel = new JLabel("Enrollment ID");
				EnrollmentIDLabel.setBounds(45, 415, 109, 13);
				EnrollmentIDLabel.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
				contentPane.add(EnrollmentIDLabel);
				
				JLabel AcademicYearLabel = new JLabel("Academic Year");
				AcademicYearLabel.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
				AcademicYearLabel.setBounds(573, 489, 95, 13);
				contentPane.add(AcademicYearLabel);
				
				JLabel MiddleNameLabel = new JLabel("Middle Name");
				MiddleNameLabel.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
				MiddleNameLabel.setBounds(45, 489, 95, 13);
				contentPane.add(MiddleNameLabel);
				
				
				JLabel YearLevelLabel = new JLabel("Year Level");
				YearLevelLabel.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
				YearLevelLabel.setBounds(309, 560, 90, 13);
				contentPane.add(YearLevelLabel);
				
				JLabel SemesterLabel = new JLabel("Semester");
				SemesterLabel.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
				SemesterLabel.setBounds(45, 560, 67, 13);
				contentPane.add(SemesterLabel);
				
				JLabel SectionLabel = new JLabel("Section");
				SectionLabel.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
				SectionLabel.setBounds(838, 489, 67, 13);
				contentPane.add(SectionLabel);
				
				JLabel StudentNumberLabel = new JLabel("Student Number");
				StudentNumberLabel.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
				StudentNumberLabel.setBounds(309, 415, 109, 13);
				contentPane.add(StudentNumberLabel);
				
				JLabel SuffixNameLabel = new JLabel("Suffix Name");
				SuffixNameLabel.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
				SuffixNameLabel.setBounds(309, 489, 95, 13);
				contentPane.add(SuffixNameLabel);
				
				JLabel LastNameLabel = new JLabel("Last Name");
				LastNameLabel.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
				LastNameLabel.setBounds(573, 415, 95, 13);
				contentPane.add(LastNameLabel);
				
				JLabel FirstNameLabel = new JLabel("First Name");
				FirstNameLabel.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
				FirstNameLabel.setBounds(838, 415, 122, 13);
				contentPane.add(FirstNameLabel);
				
				
				JLabel StatusLabel = new JLabel("Status");
				StatusLabel.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
				StatusLabel.setBounds(573, 560, 129, 13);
				contentPane.add(StatusLabel);
				
				StatetextField = new CustomRoundedTextField(10);
				StatetextField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
				StatetextField.setColumns(100);
				StatetextField.setBorderThickness(3);
				StatetextField.setBorderColor(new Color(232, 0, 85));
				StatetextField.setBounds(831, 575, 231, 37);
				contentPane.add(StatetextField);
				
				JLabel StateLabel = new JLabel("State");
				StateLabel.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
				StateLabel.setBounds(838, 560, 129, 13);
				contentPane.add(StateLabel);
				
				TypetextField = new CustomRoundedTextField(10);
				TypetextField.setFont(new Font("Poppins SemiBold", Font.PLAIN, 14));
				TypetextField.setColumns(100);
				TypetextField.setBorderThickness(3);
				TypetextField.setBorderColor(new Color(232, 0, 85));
				TypetextField.setBounds(635, 646, 231, 37);
				contentPane.add(TypetextField);
				
				JLabel TypeLabel = new JLabel("Type");
				TypeLabel.setFont(new Font("Poppins", Font.PLAIN, 12));
				TypeLabel.setBounds(644, 631, 129, 13);
				contentPane.add(TypeLabel);
				
				

				  // Add a mouse listener to the table
				   TableStudentStatus.addMouseListener(new java.awt.event.MouseAdapter() {
				        @Override
				        public void mouseClicked(java.awt.event.MouseEvent event) {
				            int selectedRow =  TableStudentStatus.getSelectedRow();
				            
				            if (selectedRow != -1) {
				                // Safely retrieve data from the selected row
				                String enrollmentId = Objects.toString(tableModelStatus.getValueAt(selectedRow, 0), "");
				                String studentNumber = Objects.toString(tableModelStatus.getValueAt(selectedRow, 1), "");
				                String lastName = Objects.toString(tableModelStatus.getValueAt(selectedRow, 2), "");
				                String firstName = Objects.toString(tableModelStatus.getValueAt(selectedRow, 3), "");
				                String middleName = Objects.toString(tableModelStatus.getValueAt(selectedRow, 4), "");
				                String suffixName = Objects.toString(tableModelStatus.getValueAt(selectedRow, 5), "");
				                String academicYear = Objects.toString(tableModelStatus.getValueAt(selectedRow, 6), "");
				                String section = Objects.toString(tableModelStatus.getValueAt(selectedRow, 7), "");
				                String semester = Objects.toString(tableModelStatus.getValueAt(selectedRow, 8), "");
				                String yearLevel = Objects.toString(tableModelStatus.getValueAt(selectedRow, 9), "");
				                String status = Objects.toString(tableModelStatus.getValueAt(selectedRow, 10), "");
				                String state = Objects.toString(tableModelStatus.getValueAt(selectedRow, 11), "");
				                String type = Objects.toString(tableModelStatus.getValueAt(selectedRow, 12), "");
				       

				                // Set data to text fields
				                EnrollmentIDtextField.setText(enrollmentId);
				                StudentNumbertextField.setText(studentNumber);
				                LastNametextField.setText(lastName);
				                FirstNametextField.setText(firstName);
				                MiddleNametextField.setText(middleName);
				                SuffixNametextField.setText(suffixName);
				                AcademicYeartextField.setText(academicYear);
				                SectiontextField.setText(section.toUpperCase());
				                SemestertextField.setText(semester);
				                YearLeveltextField.setText(yearLevel);
				                StatustextField.setText(status);
				                StatetextField.setText(state);
				                TypetextField.setText(type);
				                
				                
				            }
				        }
				    });
				
			
				
				
				
		
			    
			
		
		
		
	
		
		
		
	}
}

