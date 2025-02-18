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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
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




public class AdminEditStudentProfile1 extends JFrame {

	    private static final long serialVersionUID = 1L;
		private JPanel contentPane;
    	private JTable TableStudentProfile;
    	private DefaultTableModel tableModelProfile;
    
    	// Database credentials
        private String DB_URL = DatabaseCredentials.getDBURL();
        private String DB_USER = DatabaseCredentials.getDBUSER();
        private String DB_PASSWORD = DatabaseCredentials.getDBPASS();
	
    	private CustomRoundedTextField LastNametextField;
	    private CustomRoundedTextField FirstNametextField;
	    private CustomRoundedTextField MiddleNametextField;
	    private CustomRoundedTextField SuffixNametextField;
	    private CustomRoundedTextField SextextField;
	    private CustomRoundedTextField BirthDatetextField;
	    private CustomRoundedTextField AgetextField;
	    private CustomRoundedTextField ReligiontextField;
	    private CustomRoundedTextField CivilStatustextField;
	    private CustomRoundedTextField AddresstextField;
	    private CustomRoundedTextField GuardiantextField;
	    private CustomRoundedTextField ContactNumbertextField;
	    private CustomRoundedTextField CoursetextField;
	    private CustomRoundedTextField StudentNumbertextField;
	    private CustomRoundedTextField AcadSyncEmailAdtextField;
	    private CustomRoundedTextField PasswordtextField;
	    private CustomRoundedTextField LastLogtextField;
	    
	    private JTextField SearchtextField;
	    
	  
	    
	    
	public static void main(String[] args) {
	    EventQueue.invokeLater(() -> {
	        try {
	        	AdminEditStudentProfile1 frame = new AdminEditStudentProfile1();
	            frame.setVisible(true);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }); 
	} 

	       
	/**
	 * Create the frame.
	 */
	public AdminEditStudentProfile1() {
		
		
		setTitle("Edit Student Profile");
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/SMS Logo.png"));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Prevents closing the application
		setBounds(100, 100, 1342, 756);
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
	        	 AdminStudentProfile1 AdminStudentProfileNoNavigation = new AdminStudentProfile1();
		         AdminStudentProfileNoNavigation.setVisible(true);
		        	 
		         dispose();
	        }
	    });
	
	 
		 
		
		
		 tableModelProfile = new DefaultTableModel() {
	            private static final long serialVersionUID = 1L;

	            @Override
	            public boolean isCellEditable(int row, int column) {
	                return false;  // Prevent all cells from being editable
	            }
	        };
	        TableStudentProfile = new JTable(tableModelProfile);
	        TableStudentProfile.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Only one row can be selected at a time
	        TableStudentProfile.setBounds(70, 10, 600, 200);
	        
	

	        // Set the font for the JTable content
	        if (AcadSyncFontManager.getFont("Poppins-SemiBold1") != null) {
	            TableStudentProfile.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
	        }

	        // Set table row height and background color
	        TableStudentProfile.setRowHeight(30);
	        TableStudentProfile.setBackground(Color.WHITE);
	        TableStudentProfile.setGridColor(Color.BLACK);
	        TableStudentProfile.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));

	        
	        // Customize table header font
	        if (AcadSyncFontManager.getFont("Poppins-SemiBold1") != null) {
	            TableStudentProfile.getTableHeader().setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1").deriveFont(13f)); // Larger font for header
	            TableStudentProfile.getTableHeader().setBackground(Color.DARK_GRAY); // Header background color
	            TableStudentProfile.getTableHeader().setForeground(Color.WHITE); // Header text color
	        }

	        
	        
	        // Set custom renderer
	        TableStudentProfile.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
	            private static final long serialVersionUID = 1L;

	            @Override
	            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	                setHorizontalAlignment(CENTER);

	                if (isSelected) {
	                    component.setBackground(new Color(167, 252, 255));
	                    component.setForeground(Color.BLACK);
	                } else {
	                    component.setBackground(Color.WHITE);
	                    component.setForeground(Color.BLACK);
	                }
	                return component;
	            }
	        });

	        // Column names
	        tableModelProfile.addColumn("STUDENT NUMBER");
	        tableModelProfile.addColumn("ACADSYNC² EMAIL");
	        tableModelProfile.addColumn("PASSWORD");
	        tableModelProfile.addColumn("LAST NAME");
	        tableModelProfile.addColumn("FIRST NAME");
	        tableModelProfile.addColumn("MIDDLE NAME");
	        tableModelProfile.addColumn("SUFFIX NAME");
	        tableModelProfile.addColumn("SEX");
	        tableModelProfile.addColumn("BIRTH DATE");
	        tableModelProfile.addColumn("AGE");
	        tableModelProfile.addColumn("RELIGION");
	        tableModelProfile.addColumn("CIVIL STATUS");
	        tableModelProfile.addColumn("ADDRESS");
	        tableModelProfile.addColumn("GUARDIAN");
	        tableModelProfile.addColumn("CONTACT NUMBER");
	        tableModelProfile.addColumn("COURSE");
	        tableModelProfile.addColumn("LAST LOG");
	        
	        
	        
	        JLabel SearchLabel = new JLabel("Search");
	        SearchLabel.setHorizontalAlignment(SwingConstants.LEFT);
	        SearchLabel.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold"));
	        SearchLabel.setBounds(134, 24, 59, 13);
	        contentPane.add(SearchLabel);
	        
	        SearchtextField = new JTextField();
	        SearchtextField.setForeground(Color.GRAY);
	        SearchtextField.setFont(AcadSyncFontManager.getFont("Poppins-Medium1"));
	        SearchtextField.setColumns(10);
	        SearchtextField.setBorder(new LineBorder(new Color(128, 128, 128), 2));
	        SearchtextField.setBounds(212, 12, 205, 38);
	        contentPane.add(SearchtextField);
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
	                tableModelProfile.setRowCount(0); // Clear the table

	                // Add all rows back to the table model from the full data list
	                for (Object[] row : AllTableData) { // `AllTableData` contains the full data set
	                    tableModelProfile.addRow(row);
	                }
	            }
	        });

	  
	        

	        JScrollPane scrollPane = new JScrollPane(TableStudentProfile);
	        scrollPane.setBounds(18, 64, 1293, 240);
	        scrollPane.setBorder(new LineBorder(Color.BLACK, 2));
	        contentPane.add(scrollPane, BorderLayout.CENTER);
	        
	        

	        // Load data from database
	        LoadDataFromDatabase(AcadSyncFontManager.getFont("Poppins-SemiBold1"), AcadSyncFontManager.getFont("Poppins-Bold3"), AcadSyncFontManager.getFont("Poppins-SemiBold"), AcadSyncFontManager.getFont("Arvo-Bold"));
	
	}
	
	
	
	
	
			

//----------------------------------------------------------------------------Method------------------------------------------------------------------------
			
		
	
	
	
			
					        //Method for Search a data inside our JtextField 
							private void SearchData(String searchTerm) {
							    tableModelProfile.setRowCount(0); // Clear the table
						
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
							            tableModelProfile.addRow(row);
							        }
							    }
							}
							
							
							
							
							// Method to hash the password
							private String hashPassword(String password) {
							    try {
							        MessageDigest md = MessageDigest.getInstance("SHA-256");
							        byte[] hashedBytes = md.digest(password.getBytes());
							        return Base64.getEncoder().encodeToString(hashedBytes);
							    } catch (NoSuchAlgorithmException e) {
							        e.printStackTrace();
							        return null;
							    }
							}
							
				
							
					
							//Method to Clear the text inside our JtextField 
							private void ClearData() {
						    	  // Clear all the text fields
						            LastNametextField.setText("");
						            FirstNametextField.setText("");
						            MiddleNametextField.setText("");
						            SuffixNametextField.setText("");
						            SextextField.setText("");
						            BirthDatetextField.setText("");
						            AgetextField.setText("");
						            ReligiontextField.setText("");
						            CivilStatustextField.setText("");
						            AddresstextField.setText("");
						            GuardiantextField.setText("");
						            ContactNumbertextField.setText("");
						            CoursetextField.setText("");
						            StudentNumbertextField.setText("");
						            AcadSyncEmailAdtextField.setText("");
						            PasswordtextField.setText("");
						            LastLogtextField.setText("");
						
						            
						            StudentNumbertextField.setEditable(true);
						            PasswordtextField.setEditable(true);
						            
						            //Reset selected items or clear any custom selections
						            TableStudentProfile.clearSelection();
						
						        }
							
							
							 
							
							//Method to Add a data to our database as well as the row in our table 
							private void AddData() {
							    String studentNumber = StudentNumbertextField.getText().trim();
							    String firstName = FirstNametextField.getText().trim();
							    String middleName = MiddleNametextField.getText().trim();
							    String lastName = LastNametextField.getText().trim();
							    String suffixName = SuffixNametextField.getText().trim();
							    String sex = SextextField.getText().trim();
							    String birthDate = BirthDatetextField.getText().trim();
							    String age = AgetextField.getText().trim();
							    String religion = ReligiontextField.getText().trim();
							    String civilStatus = CivilStatustextField.getText().trim();
							    String address = AddresstextField.getText().trim();
							    String guardian = GuardiantextField.getText().trim();
							    String contactNumber = ContactNumbertextField.getText().trim();
							    String course = CoursetextField.getText().trim().toUpperCase();
							    String acadSyncEmail = AcadSyncEmailAdtextField.getText().trim();
							    String password = PasswordtextField.getText().trim();
				
							    // Check required fields
							    if (studentNumber.isEmpty() || firstName.isEmpty() || middleName.isEmpty() || lastName.isEmpty() || sex.isEmpty() || age.isEmpty() || religion.isEmpty() ||
							        civilStatus.isEmpty() || address.isEmpty() || guardian.isEmpty() || course.isEmpty() || acadSyncEmail.isEmpty() || password.isEmpty()) {
							        JOptionPane.showMessageDialog(this, "Only Contact Number, Date of Birth, Suffix Name.", "Validation Error", JOptionPane.WARNING_MESSAGE);
							        return;
							    }
							    
							    // Define the list of valid civil statuses
							    List<String> validCivilStatuses = Arrays.asList("Single", "Married", "Widowed", "Divorced", "Separated");
							    
			
							    // Validate CivilStatus against the list of valid statuses
							    if (!validCivilStatuses.contains(civilStatus)) {
							        JOptionPane.showMessageDialog(this, "Invalid Civil Status. Should only be: " + validCivilStatuses, "Validation Error", JOptionPane.WARNING_MESSAGE);
							        return;
							    }
							    
							    // Define the list of valid sex
							    List<String> validSex = Arrays.asList("Female", "Male", "Preferred Not to Say");
							    
			
							    // Validate Sex against the list of valid sex
							    if (!validSex.contains(sex)) {
							        JOptionPane.showMessageDialog(this, "Invalid Sex. Should only be: " + validSex, "Validation Error", JOptionPane.WARNING_MESSAGE);
							        return;
							    }

							    
							    // Hash the password
							    String hashedPassword = hashPassword(password);
							    if (hashedPassword == null) {
							        JOptionPane.showMessageDialog(this, "Error hashing the password. Please try again.", "Hashing Error", JOptionPane.ERROR_MESSAGE);
							        return;
							    }

							    
							    // Validate integer fields
							    try {
							        Integer.parseInt(studentNumber);
							    } catch (NumberFormatException e) {
							        JOptionPane.showMessageDialog(this, "Student Number must be an integer.", "Validation Error", JOptionPane.WARNING_MESSAGE);
							        return;
							    }
							    
				
							    try {
							        Integer.parseInt(age);
							    } catch (NumberFormatException e) {
							        JOptionPane.showMessageDialog(this, "Age must be an integer.", "Validation Error", JOptionPane.WARNING_MESSAGE);
							        return;
							    }
							   
							    
							    if (!contactNumber.isEmpty()) {
							        // Ensure the contact number is 11 digits and starts with '0'
							        if (!contactNumber.matches("^0\\d{10}$")) {
							            JOptionPane.showMessageDialog(this, "Contact Number must be 11 digits and start with 0.", "Validation Error", JOptionPane.WARNING_MESSAGE);
							            return;
							        }
							    }
				
							    // Validate birth date format only if not empty
							    if (!birthDate.isEmpty()) {
							        if (!birthDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
							            JOptionPane.showMessageDialog(this, "Birth Date must be in this format YYYY-MM-DD.", "Validation Error", JOptionPane.WARNING_MESSAGE);
							            return;
							        }
				
							        try {
							            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
							            dateFormat.setLenient(false); // Strict validation
							            dateFormat.parse(birthDate);
							        } catch (ParseException e) {
							            JOptionPane.showMessageDialog(this, "Invalid Birth Date. Please use a valid date in the format YYYY-MM-DD.", "Validation Error", JOptionPane.WARNING_MESSAGE);
							            return;
							        }
							    }
				
							    // Check for duplicate record
							    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
							        String CheckQuery = "SELECT * FROM Student_Profile WHERE StudentNumber = ? OR FirstName = ? OR AcadSyncEmail = ?";
							        try (PreparedStatement CheckStmt = conn.prepareStatement(CheckQuery)) {
							            CheckStmt.setString(1, studentNumber.toLowerCase());
							            CheckStmt.setString(2, firstName.toLowerCase());
							            CheckStmt.setString(3, acadSyncEmail.toLowerCase());
				
							            try (ResultSet rs = CheckStmt.executeQuery()) {
							                if (rs.next()) {
							                    JOptionPane.showMessageDialog(this, "Student Number, First Name, or AcadSyncEmail already exists.", "Duplicate Record", JOptionPane.WARNING_MESSAGE);
							                    return;
							                }
							            }
							        }
				
							        // Insert new Student_Profile
							        String insertQuery = "INSERT INTO Student_Profile (StudentNumber, FirstName, MiddleName, LastName, SuffixName, Sex, BirthDate, Age, Religion, CivilStatus, Address, Guardian, ContactNumber, Course, AcadSyncEmail, Password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
							        try (PreparedStatement InsertStmt = conn.prepareStatement(insertQuery)) {
							            InsertStmt.setString(1, studentNumber);
							            InsertStmt.setString(2, firstName);
							            InsertStmt.setString(3, middleName);
							            InsertStmt.setString(4, lastName);
							            InsertStmt.setString(5, suffixName.isEmpty() ? null : suffixName);
							            InsertStmt.setString(6, sex);
							            InsertStmt.setString(7, birthDate.isEmpty() ? null : birthDate);
							            InsertStmt.setString(8, age);
							            InsertStmt.setString(9, religion);
							            InsertStmt.setString(10, civilStatus);
							            InsertStmt.setString(11, address);
							            InsertStmt.setString(12, guardian);
							            InsertStmt.setString(13, contactNumber.isEmpty() ? null : contactNumber);
							            InsertStmt.setString(14, course);
							            InsertStmt.setString(15, acadSyncEmail);
							            InsertStmt.setString(16, hashedPassword);
				
							            InsertStmt.executeUpdate();
							            
							            
							         // Insert new record into Enrollment_Status
							         String enrollmentStatusQuery = "INSERT INTO Enrollment_Status (StudentNumber, FirstName, MiddleName, LastName, SuffixName) VALUES (?, ?, ?, ?, ?)";
							         try (PreparedStatement EnrollmentStmt = conn.prepareStatement(enrollmentStatusQuery)) {
							             EnrollmentStmt.setString(1, studentNumber);
							             EnrollmentStmt.setString(2, firstName);
							             EnrollmentStmt.setString(3, middleName);
							             EnrollmentStmt.setString(4, lastName);
							             EnrollmentStmt.setString(5, suffixName.isEmpty() ? null : suffixName);
				
							             EnrollmentStmt.executeUpdate();
							         }
				
							            // Clear input fields and reload data
							            ClearData();
							            JOptionPane.showMessageDialog(this, "Student Profile Successfully Added!", "Success", JOptionPane.INFORMATION_MESSAGE);
				
							            
							            // Add new row to the table model
							            tableModelProfile.addRow(new Object[] {
							                studentNumber,
							                acadSyncEmail,
							                hashedPassword,
							                lastName,
							                firstName,
							                middleName,
							                suffixName,
							                sex,
							                birthDate,
							                age,
							                religion,
							                civilStatus,
							                address,
							                guardian,
							                contactNumber,
							                course
							            });
							            
							            
							            //Add new record to AllTableData
							            AllTableData.add(new Object[]{
							                Integer.parseInt(studentNumber), acadSyncEmail, hashedPassword,
							                lastName, firstName, middleName, suffixName, sex,
							                birthDate, Integer.parseInt(age), religion, civilStatus,
							                address, guardian, contactNumber, course
							            });
							            
				
							        }
							    } catch (Exception e) {
							        e.printStackTrace();
							        JOptionPane.showMessageDialog(this, "Error Adding Student Profile: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
							    }
							}
				
							
							
							
							
							
							//Method to Update a data inside our database as well as the row in our table
							private void UpdateData() {
							    // Get the currently selected row
							    int selectedRow = TableStudentProfile.getSelectedRow();
							    if (selectedRow == -1) {
							        JOptionPane.showMessageDialog(this, "Please select a row to Update.", "No Selection", JOptionPane.WARNING_MESSAGE);
							        return;
							    }
				
							    // Get values from text fields
							    String studentNumber = StudentNumbertextField.getText().trim();
							    String acadSyncEmail = AcadSyncEmailAdtextField.getText().trim();
							    String password = PasswordtextField.getText().trim();
							    String lastName = LastNametextField.getText().trim();
							    String firstName = FirstNametextField.getText().trim();
							    String middleName = MiddleNametextField.getText().trim();
							    String suffixName = SuffixNametextField.getText().trim();
							    String sex = SextextField.getText().trim();
							    String birthDate = BirthDatetextField.getText().trim();
							    String age = AgetextField.getText().trim();
							    String religion = ReligiontextField.getText().trim();
							    String civilStatus = CivilStatustextField.getText().trim();
							    String address = AddresstextField.getText().trim();
							    String guardian = GuardiantextField.getText().trim();
							    String contactNumber = ContactNumbertextField.getText().trim();
							    String course = CoursetextField.getText().trim().toUpperCase();
				
							    
							    // Validate fields
							    if (studentNumber.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || acadSyncEmail.isEmpty() || sex.isEmpty() || age.isEmpty() || religion.isEmpty() || civilStatus.isEmpty() || address.isEmpty() || guardian.isEmpty() || course.isEmpty() || password.isEmpty()) {
							        JOptionPane.showMessageDialog(this, "Please Fill in All Required fields.", "Validation Error", JOptionPane.WARNING_MESSAGE);
							        return;
							    }
							    
							    // Define the list of valid civil statuses
							    List<String> validCivilStatuses = Arrays.asList("Single", "Married", "Widowed", "Divorced", "Separated");
							    
			
							    // Validate CivilStatus against the list of valid statuses
							    if (!validCivilStatuses.contains(civilStatus)) {
							        JOptionPane.showMessageDialog(this, "Invalid Civil Status. Should only be: " + validCivilStatuses, "Validation Error", JOptionPane.WARNING_MESSAGE);
							        return;
							    }
							    
							    
							    // Define the list of valid sex
							    List<String> validSex = Arrays.asList("Female", "Male", "Preferred Not to Say");
									    
							    
			
							    // Validate Sex against the list of valid sex
							    if (!validSex.contains(sex)) {
							        JOptionPane.showMessageDialog(this, "Invalid Sex. Should only be: " + validSex, "Validation Error", JOptionPane.WARNING_MESSAGE);
							        return;
							    }
							    
							    
							    // Hash the password
							    String hashedPassword = hashPassword(password);
							    if (hashedPassword == null) {
							        JOptionPane.showMessageDialog(this, "Error hashing the password. Please try again.", "Hashing Error", JOptionPane.ERROR_MESSAGE);
							        return;
							    }

							    
							    
							    // Validate integer fields
							    try {
							        Integer.parseInt(studentNumber);
							    } catch (NumberFormatException e) {
							        JOptionPane.showMessageDialog(this, "Student Number must be an integer.", "Validation Error", JOptionPane.WARNING_MESSAGE);
							        return;
							    }
							    
							    
							    try {
							        Integer.parseInt(age);
							    } catch (NumberFormatException e) {
							        JOptionPane.showMessageDialog(this, "Age must be an integer.", "Validation Error", JOptionPane.WARNING_MESSAGE);
							        return;
							    }
							  
							    
							    if (!contactNumber.isEmpty()) {
							        // Ensure the contact number is 11 digits and starts with '0'
							        if (!contactNumber.matches("^0\\d{10}$")) {
							            JOptionPane.showMessageDialog(this, "Contact Number must be 11 digits and start with 0.", "Validation Error", JOptionPane.WARNING_MESSAGE);
							            return;
							        }
							    }
							    
							    
							    // Validate birth date format only if not empty
							    if (!birthDate.isEmpty()) {
							        if (!birthDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
							            JOptionPane.showMessageDialog(this, "Birth Date must be in this format YYYY-MM-DD.", "Validation Error", JOptionPane.WARNING_MESSAGE);
							            return;
							        }
							        
				
							        try {
							            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
							            dateFormat.setLenient(false); // Strict validation
							            dateFormat.parse(birthDate);
							        } catch (ParseException e) {
							            JOptionPane.showMessageDialog(this, "Invalid Birth Date. Please use a valid date in the format YYYY-MM-DD.", "Validation Error", JOptionPane.WARNING_MESSAGE);
							            return;
							        }
							    }
							    
				
							    // Update database
							    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
							        conn.setAutoCommit(false);  // Start transaction
				
							        // Update Student_Profile table
							        String updateStudentQuery = "UPDATE Student_Profile SET FirstName = ?, MiddleName = ?, LastName = ?, SuffixName = ?, Sex = ?, BirthDate = ?, Age = ?, Religion = ?, CivilStatus = ?, Address = ?, Guardian = ?, ContactNumber = ?, Course = ?, AcadSyncEmail = ?, Password = ? WHERE StudentNumber = ?";
							        try (PreparedStatement updateStudentStmt = conn.prepareStatement(updateStudentQuery)) {
							            updateStudentStmt.setString(1, firstName);
							            updateStudentStmt.setString(2, middleName);
							            updateStudentStmt.setString(3, lastName);
							            updateStudentStmt.setString(4, suffixName.isEmpty() ? null : suffixName);
							            updateStudentStmt.setString(5, sex);
							            updateStudentStmt.setString(6, birthDate.isEmpty() ? null : birthDate);
							            updateStudentStmt.setString(7, age);
							            updateStudentStmt.setString(8, religion);
							            updateStudentStmt.setString(9, civilStatus);
							            updateStudentStmt.setString(10, address);
							            updateStudentStmt.setString(11, guardian);
							            updateStudentStmt.setString(12, contactNumber.isEmpty() ? null : contactNumber);
							            updateStudentStmt.setString(13, course);
							            updateStudentStmt.setString(14, acadSyncEmail);
							            updateStudentStmt.setString(15, hashedPassword);
							            updateStudentStmt.setString(16, studentNumber);
				
							            int rowsUpdatedStudent = updateStudentStmt.executeUpdate();
				
							            // Update Enrollment_Status table
							            String updateEnrollmentQuery = "UPDATE Enrollment_Status SET FirstName = ?, LastName = ?, MiddleName = ?, SuffixName = ? WHERE StudentNumber = ?";
							            try (PreparedStatement updateEnrollmentStmt = conn.prepareStatement(updateEnrollmentQuery)) {
							                updateEnrollmentStmt.setString(1, firstName);
							                updateEnrollmentStmt.setString(2, lastName);
							                updateEnrollmentStmt.setString(3, middleName);
							                updateEnrollmentStmt.setString(4, suffixName.isEmpty() ? null : suffixName);
							                updateEnrollmentStmt.setString(5, studentNumber);
				
							                int rowsUpdatedEnrollment = updateEnrollmentStmt.executeUpdate();
				
							                if (rowsUpdatedStudent > 0 && rowsUpdatedEnrollment > 0) {
							                    // Commit transaction if both updates succeed
							                    conn.commit();
							                    JOptionPane.showMessageDialog(this, "Student Profile and Enrollment Status updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
				
							                    
							                    // Update the table model
							                    tableModelProfile.setValueAt(studentNumber, selectedRow, 0);
							                    tableModelProfile.setValueAt(acadSyncEmail, selectedRow, 1);
							                    tableModelProfile.setValueAt(hashedPassword, selectedRow, 2);
							                    tableModelProfile.setValueAt(lastName, selectedRow, 3);
							                    tableModelProfile.setValueAt(firstName, selectedRow, 4);
							                    tableModelProfile.setValueAt(middleName, selectedRow, 5);
							                    tableModelProfile.setValueAt(suffixName, selectedRow, 6);
							                    tableModelProfile.setValueAt(sex, selectedRow, 7);
							                    tableModelProfile.setValueAt(birthDate, selectedRow, 8);
							                    tableModelProfile.setValueAt(age, selectedRow, 9);
							                    tableModelProfile.setValueAt(religion, selectedRow, 10);
							                    tableModelProfile.setValueAt(civilStatus, selectedRow, 11);
							                    tableModelProfile.setValueAt(address, selectedRow, 12);
							                    tableModelProfile.setValueAt(guardian, selectedRow, 13);
							                    tableModelProfile.setValueAt(contactNumber, selectedRow, 14);
							                    tableModelProfile.setValueAt(course, selectedRow, 15);
							                    
							                    
							                    /// Update AllTableData based on StudentNumber
							                    for (Object[] row : AllTableData) {
							                        if (row[0].toString().equals(studentNumber)) { // Match by StudentNumber
							                            row[1] = acadSyncEmail;
							                            row[2] = hashedPassword;
							                            row[3] = lastName;
							                            row[4] = firstName;
							                            row[5] = middleName;
							                            row[6] = suffixName;
							                            row[7] = sex;
							                            row[8] = birthDate;
							                            row[9] = Integer.parseInt(age);
							                            row[10] = religion;
							                            row[11] = civilStatus;
							                            row[12] = address;
							                            row[13] = guardian;
							                            row[14] = contactNumber;
							                            row[15] = course;
							                            break;
							                        }
							                    }
							                    
							                    
							                    
							                } else {
							                    conn.rollback();  // Roll back if any update fails
							                    JOptionPane.showMessageDialog(this, "Failed to update the Student Profile and Enrollment Status.", "Error", JOptionPane.ERROR_MESSAGE);
							                }
							            }
							        } catch (Exception e) {
							            conn.rollback();  // Roll back if any update fails
							            e.printStackTrace();
							            JOptionPane.showMessageDialog(this, "Error updating Student Profile and Enrollment Status: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
							        }
							    } catch (SQLException e) {
							        e.printStackTrace();
							        JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
							    }
							}
				
							
							
							
							
							
							//Method to Delete a data inside our database as well as the row in our table
							private void DeleteData() {
							    // Get the currently selected row
							    int selectedRow = TableStudentProfile.getSelectedRow();
							    if (selectedRow == -1) {
							        JOptionPane.showMessageDialog(this, "Please select a row to Delete.", "No Selection", JOptionPane.WARNING_MESSAGE);
							        return;
							    }
			
							    // Confirm deletion
							    int confirm = JOptionPane.showConfirmDialog(this, 
							        "Are you sure you want to delete the selected Student Profile?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
							    if (confirm != JOptionPane.YES_OPTION) {
							        return;
							    }
			
							    // Get the student data from the selected row
							    String studentNumber = Objects.toString(tableModelProfile.getValueAt(selectedRow, 0), "");
			
							    // Delete from the database
							    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
							        conn.setAutoCommit(false); // Start a transaction to ensure both deletes are executed
			
							        // Delete from Student_Profile table
							        String deleteStudentQuery = "DELETE FROM Student_Profile WHERE StudentNumber = ?";
							        try (PreparedStatement deleteStudentStmt = conn.prepareStatement(deleteStudentQuery)) {
							            deleteStudentStmt.setString(1, studentNumber);
							            int rowsDeletedStudent = deleteStudentStmt.executeUpdate();
			
							            if (rowsDeletedStudent > 0) {
							                // Delete corresponding entry in Enrollment_Status table
							                String deleteEnrollmentQuery = "DELETE FROM Enrollment_Status WHERE StudentNumber = ?";
							                try (PreparedStatement deleteEnrollmentStmt = conn.prepareStatement(deleteEnrollmentQuery)) {
							                    deleteEnrollmentStmt.setString(1, studentNumber);
							                    int rowsDeletedEnrollment = deleteEnrollmentStmt.executeUpdate();
			
							                    if (rowsDeletedEnrollment > 0) {
							                        // Commit the transaction if both deletions are successful
							                        conn.commit();
							                        JOptionPane.showMessageDialog(this, "Student Profile and Enrollment Status deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
			
							                        // Remove the row from AllTableData
							                        AllTableData.removeIf(row -> row[0].toString().equals(studentNumber));
			
							                        // Remove the row from the table model
							                        tableModelProfile.removeRow(selectedRow);
			
							                        // Clear input fields
							                        ClearData();
							                    } else {
							                        // Roll back the transaction if deletion from Enrollment_Status failed
							                        conn.rollback();
							                        JOptionPane.showMessageDialog(this, "Failed to delete Enrollment Status.", "Error", JOptionPane.ERROR_MESSAGE);
							                    }
							                } catch (Exception e) {
							                    conn.rollback(); // Roll back if there is any issue in deleting Enrollment Status
							                    e.printStackTrace();
							                    JOptionPane.showMessageDialog(this, "Error deleting Enrollment Status: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
							                }
							            } else {
							                // Roll back if there is any issue in deleting the Student Profile
							                conn.rollback();
							                JOptionPane.showMessageDialog(this, "Failed to delete the Student Profile.", "Error", JOptionPane.ERROR_MESSAGE);
							            }
							        } catch (Exception e) {
							            conn.rollback(); // Roll back if there is any issue in deleting the Student Profile
							            e.printStackTrace();
							            JOptionPane.showMessageDialog(this, "Error deleting Student Profile: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
							        }
							    } catch (Exception e) {
							        e.printStackTrace();
							        JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
							    }
							}
			
				
				
				
							
						    // Method to Load data from the database and populate the table
							private List<Object[]> AllTableData = new ArrayList<>(); // Cache to hold all rows
							private void LoadDataFromDatabase(Font poppinsSemiBold1, Font poppinsBold3, Font poppinsSemiBold, Font arvoBold) {
								try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
							             Statement stmt = conn.createStatement();
							             ResultSet rs = stmt.executeQuery("SELECT * FROM Student_Profile")) {
							        	
							        	  // Clear the existing table data and cache
							            tableModelProfile.setRowCount(0);
							            AllTableData.clear();
						
							            while (rs.next()) {
							                // Create a row object from the ResultSet
							                Object[] row = new Object[] {
							                    rs.getString("StudentNumber"),
							                    rs.getString("AcadSyncEmail"),
							                    rs.getString("Password"),
							                    rs.getString("LastName"),
							                    rs.getString("FirstName"),
							                    rs.getString("MiddleName"),
							                    rs.getString("SuffixName"),
							                    rs.getString("Sex"),
							                    rs.getDate("BirthDate"),
							                    rs.getInt("Age"),
							                    rs.getString("Religion"),
							                    rs.getString("CivilStatus"),
							                    rs.getString("Address"),
							                    rs.getString("Guardian"),
							                    rs.getString("ContactNumber"),
							                    rs.getString("Course"),
							                    rs.getTimestamp("LastLog")
							                };
						
							                // Add the row to the cache
							                AllTableData.add(row);
						
							                // Add the row to the table model
							                tableModelProfile.addRow(row);
							            }
							        } catch (Exception e) {
							            e.printStackTrace();
							            JOptionPane.showMessageDialog(this, "Error loading data from database: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
							        }
						        
						
								
			
					
			 
	       
       
		           

		        
		        
		        
		   
//----------------------------------------------------------------------------------------------------------------------------------------------------------	        
	


	  
						
					CustomButtonDelete1 DeleteB = new CustomButtonDelete1("Delete", 18);
					DeleteB.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold"));
					DeleteB.setBounds(377, 663, 95, 37);
					contentPane.add(DeleteB);
					DeleteB.addActionListener(_ -> DeleteData());
							
				    CustomButtonUpdate1 UpdateB = new CustomButtonUpdate1("Update", 18);
					UpdateB.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold"));
					UpdateB.setBounds(263, 663, 95, 37);
					contentPane.add(UpdateB);
					UpdateB.addActionListener(_ -> UpdateData());
							
					CustomButtonAdd1 AddB = new CustomButtonAdd1("Add", 18);
					AddB.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold"));
					AddB.setBounds(150, 663, 95, 37);
					contentPane.add(AddB);
					AddB.addActionListener(_ -> AddData());
						
							
					CustomButtonClear1 ClearB = new CustomButtonClear1("Clear", 18);
					ClearB.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold"));
					ClearB.setBounds(37, 663, 95, 37);
					contentPane.add(ClearB);
					ClearB.addActionListener(_ -> ClearData());
					
					
						
					
					JLabel LineLabel = new JLabel("______________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________");
					LineLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
					LineLabel.setForeground(new Color(128, 128, 128));
					LineLabel.setBounds(0, -18, 2000, 25);
					contentPane.add(LineLabel);
				
					
					JLabel PersonalDetailsLabel = new JLabel("Personal Details");
					PersonalDetailsLabel.setBounds(37, 453, 200, 31);
					PersonalDetailsLabel.setFont(AcadSyncFontManager.getFont("Arvo-Bold"));
					
					contentPane.add(PersonalDetailsLabel);
					
					
					JLabel ContactDetailsLabel = new JLabel("Contact Details");
					ContactDetailsLabel.setBounds(37, 409, 200, 31);
					ContactDetailsLabel.setFont(AcadSyncFontManager.getFont("Arvo-Bold"));
					
					contentPane.add(ContactDetailsLabel);
					
					
					JLabel EnrollmentDetailsLabel = new JLabel("Enrollment Details");
					EnrollmentDetailsLabel.setBounds(37, 340, 254, 31);
					EnrollmentDetailsLabel.setFont(AcadSyncFontManager.getFont("Arvo-Bold"));
					
					contentPane.add(EnrollmentDetailsLabel);
					
					
					
					JLabel AcadSyncGoogle = new JLabel("AcadSync² Google ");
					AcadSyncGoogle.setBounds(1070, 325, 258, 31);
					AcadSyncGoogle.setFont(AcadSyncFontManager.getFont("Arvo-Bold"));
					
					contentPane.add(AcadSyncGoogle);
			
					JLabel Account = new JLabel("Account");
					Account.setBounds(1135, 362, 239, 31);
					Account.setFont(AcadSyncFontManager.getFont("Arvo-Bold"));
					
					contentPane.add(Account);
					
					
			
					
					
					LastNametextField = new CustomRoundedTextField(10);
					LastNametextField.setBorderColor(new Color(255, 102, 54));
					LastNametextField.setBorderThickness(3);
					LastNametextField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
					LastNametextField.setBounds(37, 580, 231, 37);
					contentPane.add(LastNametextField);
					LastNametextField.setColumns(100);
					
					SextextField = new CustomRoundedTextField(10);
					SextextField.setColumns(100);
					SextextField.setBorderThickness(3);
					SextextField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
					SextextField.setBorderColor(new Color(255, 102, 54));
					SextextField.setBounds(533, 645, 231, 37);
					contentPane.add(SextextField);
					
					AddresstextField = new CustomRoundedTextField(10);
					AddresstextField.setColumns(100);
					AddresstextField.setBorderThickness(3);
					AddresstextField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
					AddresstextField.setBorderColor(new Color(255, 102, 54));
					AddresstextField.setBounds(285, 405, 231, 37);
					contentPane.add(AddresstextField);
					
					CoursetextField = new CustomRoundedTextField(10);
					CoursetextField.setColumns(100);
					CoursetextField.setBorderThickness(3);
					CoursetextField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
					CoursetextField.setBorderColor(new Color(255, 102, 54));
					CoursetextField.setBounds(533, 337, 231, 37);
					contentPane.add(CoursetextField);
					
					
					FirstNametextField = new CustomRoundedTextField(10);
					FirstNametextField.setFont(null);
					FirstNametextField.setColumns(100);
					FirstNametextField.setBorderThickness(3);
					FirstNametextField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
					FirstNametextField.setBorderColor(new Color(255, 102, 54));
					FirstNametextField.setBounds(285, 580, 231, 37);
					contentPane.add(FirstNametextField);
					
					MiddleNametextField = new CustomRoundedTextField(10);
					MiddleNametextField.setFont(null);
					MiddleNametextField.setColumns(100);
					MiddleNametextField.setBorderThickness(3);
					MiddleNametextField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
					MiddleNametextField.setBorderColor(new Color(255, 102, 54));
					MiddleNametextField.setBounds(533, 580, 231, 37);
					contentPane.add(MiddleNametextField);
					
					SuffixNametextField = new CustomRoundedTextField(10);
					SuffixNametextField.setFont(null);
					SuffixNametextField.setColumns(100);
					SuffixNametextField.setBorderThickness(3);
					SuffixNametextField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
					SuffixNametextField.setBorderColor(new Color(255, 102, 54));
					SuffixNametextField.setBounds(781, 580, 231, 37);
					contentPane.add(SuffixNametextField);
					
					CivilStatustextField = new CustomRoundedTextField(10);
					CivilStatustextField.setFont(null);
					CivilStatustextField.setColumns(100);
					CivilStatustextField.setBorderThickness(3);
					CivilStatustextField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
					CivilStatustextField.setBorderColor(new Color(255, 102, 54));
					CivilStatustextField.setBounds(37, 514, 231, 37);
					contentPane.add(CivilStatustextField);
					
					
					BirthDatetextField = new CustomRoundedTextField(10);
					BirthDatetextField.setFont(null);
					BirthDatetextField.setColumns(100);
					BirthDatetextField.setBorderThickness(3);
					BirthDatetextField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
					BirthDatetextField.setBorderColor(new Color(255, 102, 54));
					BirthDatetextField.setBounds(533, 514, 231, 37);
					contentPane.add(BirthDatetextField);
					
					AgetextField = new CustomRoundedTextField(10);
					AgetextField.setFont(null);
					AgetextField.setColumns(100);
					AgetextField.setBorderThickness(3);
					AgetextField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
					AgetextField.setBorderColor(new Color(255, 102, 54));
					AgetextField.setBounds(781, 514, 231, 37);
					contentPane.add(AgetextField);
					
					ReligiontextField = new CustomRoundedTextField(10);
					ReligiontextField.setFont(null);
					ReligiontextField.setColumns(100);
					ReligiontextField.setBorderThickness(3);
					ReligiontextField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
					ReligiontextField.setBorderColor(new Color(255, 102, 54));
					ReligiontextField.setBounds(285, 514, 231, 37);
					contentPane.add(ReligiontextField);
					
					GuardiantextField = new CustomRoundedTextField(10);
					GuardiantextField.setFont(null);
					GuardiantextField.setColumns(100);
					GuardiantextField.setBorderThickness(3);
					GuardiantextField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
					GuardiantextField.setBorderColor(new Color(255, 102, 54));
					GuardiantextField.setBounds(533, 405, 231, 37);
					contentPane.add(GuardiantextField);
					
					ContactNumbertextField = new CustomRoundedTextField(10);
					ContactNumbertextField.setFont(null);
					ContactNumbertextField.setColumns(100);
					ContactNumbertextField.setBorderThickness(3);
					ContactNumbertextField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
					ContactNumbertextField.setBorderColor(new Color(255, 102, 54));
					ContactNumbertextField.setBounds(781, 405, 231, 37);
					contentPane.add(ContactNumbertextField);
					
					StudentNumbertextField = new CustomRoundedTextField(10);
					StudentNumbertextField.setFont(null);
					StudentNumbertextField.setColumns(100);
					StudentNumbertextField.setBorderThickness(3);
					StudentNumbertextField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
					StudentNumbertextField.setBorderColor(new Color(255, 102, 54));
					StudentNumbertextField.setBounds(285, 337, 231, 37);
					contentPane.add(StudentNumbertextField);
					
					
					AcadSyncEmailAdtextField = new CustomRoundedTextField(10);
					AcadSyncEmailAdtextField.setFont(null);
					AcadSyncEmailAdtextField.setColumns(100);
					AcadSyncEmailAdtextField.setBorderThickness(3);
					AcadSyncEmailAdtextField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
					AcadSyncEmailAdtextField.setBorderColor(new Color(255, 102, 54));
					AcadSyncEmailAdtextField.setBounds(1061, 445, 231, 37);
					contentPane.add(AcadSyncEmailAdtextField);
					
					PasswordtextField = new CustomRoundedTextField(10);
					PasswordtextField.setFont(null);
					PasswordtextField.setColumns(100);
					PasswordtextField.setBorderThickness(3);
					PasswordtextField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
					PasswordtextField.setBorderColor(new Color(255, 102, 54));
					PasswordtextField.setBounds(1061, 531, 231, 37);
					contentPane.add(PasswordtextField);
					
					LastLogtextField = new CustomRoundedTextField(10);
				    LastLogtextField.setText("");
				    LastLogtextField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
				    LastLogtextField.setEditable(false);
				    LastLogtextField.setColumns(100);
				    LastLogtextField.setBorderThickness(3);
				    LastLogtextField.setBorderColor(new Color(255, 102, 54));
				    LastLogtextField.setBounds(781, 645, 231, 37);
				    contentPane.add(LastLogtextField);
					
					
					
					JLabel FirstNameLabel = new JLabel("First Name");
					FirstNameLabel.setBounds(295, 565, 67, 13);
					FirstNameLabel.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
					contentPane.add(FirstNameLabel);
					
					JLabel MiddleNameLabel = new JLabel("Middle Name");
					MiddleNameLabel.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
					MiddleNameLabel.setBounds(544, 565, 95, 13);
					contentPane.add(MiddleNameLabel);
					
					JLabel SuffixNameLabel = new JLabel("Suffix  Name");
					SuffixNameLabel.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
					SuffixNameLabel.setBounds(793, 565, 90, 13);
					contentPane.add(SuffixNameLabel);
					
					
					JLabel LastNameLabel = new JLabel("Last Name");
					LastNameLabel.setBounds(46, 565, 67, 13);
					LastNameLabel.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
					contentPane.add(LastNameLabel);
					
					
					JLabel CivilStatusLabel = new JLabel("Civil Status");
					CivilStatusLabel.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
					CivilStatusLabel.setBounds(46, 499, 90, 13);
					contentPane.add(CivilStatusLabel);
					
					
					JLabel SexLabel = new JLabel("Sex");
					SexLabel.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
					SexLabel.setBounds(544, 630, 67, 13);
					contentPane.add(SexLabel);
					
					JLabel AddressLabel = new JLabel("Address ");
					AddressLabel.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
					AddressLabel.setBounds(295, 390, 67, 13);
					contentPane.add(AddressLabel);
					
					JLabel CourseLabel = new JLabel("Course");
					CourseLabel.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
					CourseLabel.setBounds(544, 322, 67, 13);
					contentPane.add(CourseLabel);
					
					JLabel BirthDateLabel = new JLabel("Birth Date");
					BirthDateLabel.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
					BirthDateLabel.setBounds(544, 499, 67, 13);
					contentPane.add(BirthDateLabel);
					
					JLabel AgeLabel = new JLabel("Age");
					AgeLabel.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
					AgeLabel.setBounds(793, 499, 67, 13);
					contentPane.add(AgeLabel);
					
					JLabel ReligionLabel = new JLabel("Religion");
					ReligionLabel.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
					ReligionLabel.setBounds(295, 499, 67, 13);
					contentPane.add(ReligionLabel);
					
					JLabel GuardianLabel = new JLabel("Guardian");
					GuardianLabel.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
					GuardianLabel.setBounds(544, 390, 67, 13);
					contentPane.add(GuardianLabel);
					
					JLabel ContactNumberLabel = new JLabel("Contact Number");
					ContactNumberLabel.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
					ContactNumberLabel.setBounds(793, 390, 129, 13);
					contentPane.add(ContactNumberLabel);
					
					JLabel StudentNumberLabel = new JLabel("Student Number");
					StudentNumberLabel.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
					StudentNumberLabel.setBounds(295, 322, 122, 13);
					contentPane.add(StudentNumberLabel);
					
					
					JLabel AcadSyncEmailAdLabel = new JLabel("AcadSync² Email Ad");
					AcadSyncEmailAdLabel.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
					AcadSyncEmailAdLabel.setBounds(1072, 430, 129, 13);
					contentPane.add(AcadSyncEmailAdLabel);
					
					JLabel PasswordLabel = new JLabel("Password");
					PasswordLabel.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
					PasswordLabel.setBounds(1072, 516, 177, 13);
					contentPane.add(PasswordLabel);
					

					JLabel LastLogLabel = new JLabel("Last Log");
				    LastLogLabel.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
				    LastLogLabel.setBounds(793, 629, 122, 13);
				    contentPane.add(LastLogLabel);
					
				    
					
				   // Add a mouse listener to the table
				   TableStudentProfile.addMouseListener(new java.awt.event.MouseAdapter() {
				        @Override
				        public void mouseClicked(java.awt.event.MouseEvent event) {
				            int selectedRow = TableStudentProfile.getSelectedRow();
				            
				            if (selectedRow != -1) {
				                // Safely retrieve data from the selected row
				                String studentNumber = Objects.toString(tableModelProfile.getValueAt(selectedRow, 0), "");
				                String acadSyncEmail = Objects.toString(tableModelProfile.getValueAt(selectedRow, 1), "");
				                String password = Objects.toString(tableModelProfile.getValueAt(selectedRow, 2), "");
				                String lastName = Objects.toString(tableModelProfile.getValueAt(selectedRow, 3), "");
				                String firstName = Objects.toString(tableModelProfile.getValueAt(selectedRow, 4), "");
				                String middleName = Objects.toString(tableModelProfile.getValueAt(selectedRow, 5), "");
				                String suffixName = Objects.toString(tableModelProfile.getValueAt(selectedRow, 6), "");
				                String sex = Objects.toString(tableModelProfile.getValueAt(selectedRow, 7), "");

				                // Format date if necessary
				                String birthDate = "";
				                if (tableModelProfile.getValueAt(selectedRow, 8) != null) {
				                    birthDate = tableModelProfile.getValueAt(selectedRow, 8).toString();
				                }

				                // Convert age safely
				                String age = "";
				                if (tableModelProfile.getValueAt(selectedRow, 9) != null) {
				                    age = tableModelProfile.getValueAt(selectedRow, 9).toString();
				                }

				                String religion = Objects.toString(tableModelProfile.getValueAt(selectedRow, 10), "");
				                String civilStatus = Objects.toString(tableModelProfile.getValueAt(selectedRow, 11), "");
				                String address = Objects.toString(tableModelProfile.getValueAt(selectedRow, 12), "");
				                String guardian = Objects.toString(tableModelProfile.getValueAt(selectedRow, 13), "");
				                String contactNumber = Objects.toString(tableModelProfile.getValueAt(selectedRow, 14), "");
				                String course = Objects.toString(tableModelProfile.getValueAt(selectedRow, 15), "");
				                String lastlog = Objects.toString(tableModelProfile.getValueAt(selectedRow, 16), "");

				                
				                // Set data to text fields
				                StudentNumbertextField.setText(studentNumber);
				                AcadSyncEmailAdtextField.setText(acadSyncEmail);
				                PasswordtextField.setText(password);
				                LastNametextField.setText(lastName);
				                FirstNametextField.setText(firstName);
				                MiddleNametextField.setText(middleName);
				                SuffixNametextField.setText(suffixName);
				                SextextField.setText(sex);
				                BirthDatetextField.setText(birthDate);
				                AgetextField.setText(age);
				                ReligiontextField.setText(religion);
				                CivilStatustextField.setText(civilStatus);
				                AddresstextField.setText(address);
				                GuardiantextField.setText(guardian);
				                ContactNumbertextField.setText(contactNumber);
				                CoursetextField.setText(course.toUpperCase());
				                LastLogtextField.setText(lastlog);
				                
				                
				                StudentNumbertextField.setEditable(false);
				                LastLogtextField.setEditable(false);
				                PasswordtextField.setEditable(false);
				                
				                
				                
				            }
				        }
				    });
				   
				   
				   
		}
	}
	
	

