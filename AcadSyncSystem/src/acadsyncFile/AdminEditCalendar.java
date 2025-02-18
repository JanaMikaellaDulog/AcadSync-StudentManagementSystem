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
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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




public class AdminEditCalendar extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable TableCalendar;
	private DefaultTableModel tableModelCalendar;

	// Database credentials
    private String DB_URL = DatabaseCredentials.getDBURL();
    private String DB_USER = DatabaseCredentials.getDBUSER();
    private String DB_PASSWORD = DatabaseCredentials.getDBPASS();

	private CustomRoundedTextField AcadIDtextField;
	private CustomRoundedTextField AcademicYeartextField;
	private CustomRoundedTextField DatetextField;
	private CustomRoundedTextField EventtextField;
	private CustomRoundedTextField SemestertextField;
	private CustomRoundedTextField NotetextField;
	
	private JTextField YeartextField;
	private JTextField SearchtextField;
	 
	
	public static void main(String[] args) {
	    EventQueue.invokeLater(() -> {
	        try {
	        	 AdminEditCalendar frame = new  AdminEditCalendar();
	            frame.setVisible(true);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    });
	}

	       
	/**
	 * Create the frame.
	 */
	public  AdminEditCalendar() {
		setTitle("Edit Academic Calendar");
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/SMS Logo.png"));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1109, 704);
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
	        	 AdminDashBoard AdminDashBoardNavigation = new AdminDashBoard();
		         AdminDashBoardNavigation.setVisible(true);
		        	 
		         dispose();
	        }
	    });
		 
	
		
		
						
						// Initialize JTable and DefaultTableModel
				        tableModelCalendar = new DefaultTableModel() {
				        private static final long serialVersionUID = 1L;
						
				         @Override
				         public boolean isCellEditable(int row, int column) {
				             return false;  // Prevent all cells from being editable
				         	}
				     	};
				     	
				     	
				        TableCalendar = new JTable(tableModelCalendar);
					    TableCalendar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Only one row can be selected at a time
					    TableCalendar.setBounds(70, 10, 600, 200);
					     
				
				        // Set font and styling for rows
				        if (AcadSyncFontManager.getFont("Poppins-SemiBold1") != null) {
				            TableCalendar.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
				        } else {
				            System.out.println("Custom font not loaded; using default font.");
				        }
				        
				        
				        
				        TableCalendar.setRowHeight(30);
				        TableCalendar.setBackground(Color.WHITE);
				        TableCalendar.setGridColor(Color.BLACK);
				        TableCalendar.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
				
				        
				        
				        // Customize table header font
				        if (AcadSyncFontManager.getFont("Poppins-SemiBold1") != null) {
				            TableCalendar.getTableHeader().setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1").deriveFont(13f)); // Larger font for header
				            TableCalendar.getTableHeader().setBackground(Color.DARK_GRAY); // Header background color
				            TableCalendar.getTableHeader().setForeground(Color.WHITE); // Header text color
				        }
				
				        // Custom cell renderer for selected row behavior
				        TableCalendar.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
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
				                    component.setBackground(new Color(246, 153, 187)); // Custom selected row background color
				                    component.setForeground(Color.BLACK); // Custom selected row foreground color
				                } else {
				                    component.setBackground(Color.WHITE); // Default background color
				                    component.setForeground(Color.BLACK); // Default text color
				                }
				                return component;
				            }
				        });
				
				
				        
				        
				        // Column names
				        tableModelCalendar.addColumn("ACADEMIC YEAR ID");
				        tableModelCalendar.addColumn("ACADEMIC YEAR");
				        tableModelCalendar.addColumn("DATE");
				        tableModelCalendar.addColumn("EVENT");
				        tableModelCalendar.addColumn("SEMESTER");
				        tableModelCalendar.addColumn("NOTE");
				        
				        
				        YeartextField = new JTextField("");
				        YeartextField.setBackground(new Color(238, 238, 238));
				        YeartextField.setBounds(495, 385, 129, 19);
				        YeartextField.setBorder(null);
				        YeartextField.setEditable(false);
				        YeartextField.setFont(AcadSyncFontManager.getFont("Poppins-Bold"));
				        contentPane.add(YeartextField);
				        YeartextField.setColumns(20);
				        
				        
				        JLabel forAYLabel = new JLabel("for A.Y.");
				        forAYLabel.setBounds(520, 348, 200, 31);
				        forAYLabel.setFont(AcadSyncFontManager.getFont("Arvo-Bold"));
				        
				        contentPane.add(forAYLabel);
				 
				
				     
				        
				        // Add table to a scroll pane
				        JScrollPane scrollPane = new JScrollPane(TableCalendar);
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
				                tableModelCalendar.setRowCount(0); // Clear the table
				
				                // Add all rows back to the table model from the full data list
				                for (Object[] row : AllTableData) { // `AllTableData` contains the full data set
				              	  tableModelCalendar.addRow(row);
				                }
				            }
				        });	  
				        
						
				        // Load data from database
				        LoadDataFromDatabase(AcadSyncFontManager.getFont("Poppins-SemiBold"), AcadSyncFontManager.getFont("Poppins-Bold2"), AcadSyncFontManager.getFont("Poppins-SemiBold1"), AcadSyncFontManager.getFont("Arvo-Bold"));
				        UpdateAcadYearinCalendar();
						
					}
						
						
						
	
	
	
		
//----------------------------------------------------------------------------Method------------------------------------------------------------------------
		
		
		
		
		
		
					//Method for Search a data inside our JtextField 
					private void SearchData(String searchTerm) {
					    tableModelCalendar.setRowCount(0); // Clear the table
				
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
					            tableModelCalendar.addRow(row);
					        }
					    }
					}
				
				
				
				
					
				
					//Method to Clear the text inside our JtextField 
					private void ClearData() {
				    	  // Clear all the text fields
				            AcadIDtextField.setText("");
				            AcademicYeartextField.setText("");
				            SemestertextField.setText("");
				            EventtextField.setText("");
				            DatetextField.setText("");
				            NotetextField.setText("");
				          
				          
				            //Reset selected items or clear any custom selections
				            TableCalendar.clearSelection();
				
				        }
					
					
					
				
					
					
					//Method to Add a data to our database as well as the row in our table 
					private void AddData() {
					    String semester = SemestertextField.getText().trim();
					    String academicYear = AcademicYeartextField.getText().trim();
					    String date = DatetextField.getText().trim();
					    String note = NotetextField.getText().trim();
					    String event = EventtextField.getText().trim();

					    // Validation
					    if (semester.isEmpty() || academicYear.isEmpty() || date.isEmpty() || event.isEmpty()) {
					        JOptionPane.showMessageDialog(this, "Only Note Fields can be null.", "Validation Error", JOptionPane.WARNING_MESSAGE);
					        return;
					    }

					    List<String> validSem = Arrays.asList("1st Sem", "2nd Sem");
					    if (!validSem.contains(semester)) {
					        JOptionPane.showMessageDialog(this, "Invalid Sem. Should only be: " + validSem, "Validation Error", JOptionPane.WARNING_MESSAGE);
					        return;
					    }

					    if (!date.matches("\\d{4}-\\d{2}-\\d{2}")) {
					        JOptionPane.showMessageDialog(this, "Date must be in this format YYYY-MM-DD.", "Validation Error", JOptionPane.WARNING_MESSAGE);
					        return;
					    }

					    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
					        String checkQuery = "SELECT * FROM Academic_Year_Schedule WHERE Date = ? AND Semester = ? AND AcademicYear = ? AND Event = ?";
					        try (PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
					            checkStmt.setString(1, date);
					            checkStmt.setString(2, semester);
					            checkStmt.setString(3, academicYear);
					            checkStmt.setString(4, event);

					            try (ResultSet rs = checkStmt.executeQuery()) {
					                if (rs.next()) {
					                    JOptionPane.showMessageDialog(this, "Duplicate data exists for the specified fields.", "Duplicate Record", JOptionPane.WARNING_MESSAGE);
					                    return;
					                }
					            }
					        }

					        
					        String getMaxIdQuery = "SELECT MAX(AcademicYearID) FROM Academic_Year_Schedule";
					        int newsectionID = 1;
					        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(getMaxIdQuery)) {
					            if (rs.next() && rs.getInt(1) != 0) {
					                newsectionID = rs.getInt(1) + 1;
					            }
					        }

					        
					        
					        String insertQuery = "INSERT INTO Academic_Year_Schedule (Semester, AcademicYear, Date, Event, Notes) VALUES (?, ?, ?, ?, ?)";
					        try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
					            insertStmt.setString(1, semester);
					            insertStmt.setString(2, academicYear);
					            insertStmt.setString(3, date);
					            insertStmt.setString(4, event);
					            insertStmt.setString(5, note);

					            insertStmt.executeUpdate();
					            
					            ClearData();
					    
					            JOptionPane.showMessageDialog(this, "Acad Year Schedule Successfully Added!", "Success", JOptionPane.INFORMATION_MESSAGE);

					            tableModelCalendar.addRow(new Object[]{
					                newsectionID,
					                academicYear, 
					                date, 
					                event, 
					                semester, 
					                note
					            });

					            AllTableData.add(new Object[]{
					                newsectionID, 
					                academicYear, 
					                date, 
					                event, 
					                semester,
					                note
					            });
					            
					            
					        }
					    } catch (Exception e) {
					        e.printStackTrace();
					        JOptionPane.showMessageDialog(this, "Error adding Acad Year Schedule: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
					    }
					}

					
				
					
					
					
					
					//Method to Update a data inside our database as well as the row in our table
					private void UpdateData() {
					    // Get the currently selected row
					    int selectedRow = TableCalendar.getSelectedRow();
					    if (selectedRow == -1) {
					        JOptionPane.showMessageDialog(this, "Please select a row to Update.", "No Selection", JOptionPane.WARNING_MESSAGE);
					        return;
					    }
			
					    // Get values from text fields
					    String acadID = AcadIDtextField.getText().trim();
					    String semester = SemestertextField.getText().trim();
					    String academicYear = AcademicYeartextField.getText().trim();
					    String date = DatetextField.getText().trim();
					    String note = NotetextField.getText().trim();
					    String event = EventtextField.getText().trim();
			
					    // Validate required fields
					    if (semester.isEmpty() || academicYear.isEmpty() || date.isEmpty() || event.isEmpty()) {
					        JOptionPane.showMessageDialog(this, "Only Note fields can be null.", "Validation Error", JOptionPane.WARNING_MESSAGE);
					        return;
					    }
			
					    // Define valid semesters
					    List<String> validSem = Arrays.asList("1st Sem", "2nd Sem");
			
					    // Validate semester
					    if (!validSem.contains(semester)) {
					        JOptionPane.showMessageDialog(this, "Invalid Semester. Valid options are: " + validSem, "Validation Error", JOptionPane.WARNING_MESSAGE);
					        return;
					    }
			
					    // Validate date format
					    if (!date.matches("\\d{4}-\\d{2}-\\d{2}")) {
					        JOptionPane.showMessageDialog(this, "Date must be in the format YYYY-MM-DD.", "Validation Error", JOptionPane.WARNING_MESSAGE);
					        return;
					    }
			
					    try {
					        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					        dateFormat.setLenient(false); // Enforce strict date parsing
					        dateFormat.parse(date);
					    } catch (ParseException e) {
					        JOptionPane.showMessageDialog(this, "Invalid Date. Please use a valid date in the format YYYY-MM-DD.", "Validation Error", JOptionPane.WARNING_MESSAGE);
					        return;
					    }
			
					    
					    
					    // Database update logic
					    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
					        // Check for duplicates
					        String duplicateCheckQuery = "SELECT 1 FROM Academic_Year_Schedule WHERE TRIM(Semester) = ? AND TRIM(AcademicYear) = ? AND TRIM(Date) = ? AND TRIM(Event) = ? AND AcademicYearID != ?";
			
					        try (PreparedStatement duplicateCheckStmt = conn.prepareStatement(duplicateCheckQuery)) {
					            duplicateCheckStmt.setString(1, semester);
					            duplicateCheckStmt.setString(2, academicYear);
					            duplicateCheckStmt.setString(3, date);
					            duplicateCheckStmt.setString(4, event);
					            duplicateCheckStmt.setString(5, acadID);
			
					            try (ResultSet rs = duplicateCheckStmt.executeQuery()) {
					                if (rs.next()) {
					                    JOptionPane.showMessageDialog(this, "A record with the same details already exists.", "Duplicate Record", JOptionPane.WARNING_MESSAGE);
					                    return;
					                }
					            }
					        }
			
					        // Update query
					        String updateQuery = """
					            UPDATE Academic_Year_Schedule SET Semester = ?, AcademicYear = ?, Date = ?, Event = ?, Notes = ? WHERE AcademicYearID = ?""";
			
					        try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
					            updateStmt.setString(1, semester);
					            updateStmt.setString(2, academicYear);
					            updateStmt.setString(3, date);
					            updateStmt.setString(4, event);
					            updateStmt.setString(5, note);
					            updateStmt.setString(6, acadID);
			
					            int rowsUpdated = updateStmt.executeUpdate();
					            
					            ClearData();
					            
					            if (rowsUpdated > 0) {
					                JOptionPane.showMessageDialog(this, "Record updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
			
					                // Update the table model
					                tableModelCalendar.setValueAt(acadID, selectedRow, 0);
					                tableModelCalendar.setValueAt(academicYear, selectedRow, 1);
					                tableModelCalendar.setValueAt(date, selectedRow, 2);
					                tableModelCalendar.setValueAt(event, selectedRow, 3);
					                tableModelCalendar.setValueAt(semester, selectedRow, 4);
					                tableModelCalendar.setValueAt(note, selectedRow, 5);
					 
			
					                // Update AllTableData based on AcademicYearID
					                for (Object[] row : AllTableData) {
					                    if (row[0].toString().equals(acadID)) {
					                        row[1] = academicYear;
					                        row[2] = date;
					                        row[3] = event;
					                        row[4] = semester;
					                        row[5] = note;
					                        break;
					                    }
					                }
					            } else {
					                JOptionPane.showMessageDialog(this, "Failed to update the record.", "Error", JOptionPane.ERROR_MESSAGE);
					            }
					        }
					    } catch (SQLException e) {
					        e.printStackTrace();
					        JOptionPane.showMessageDialog(this, "Error updating the record: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
					    }
					}
			
					
					
					
					//Method to Delete a data inside our database as well as the row in our table
					private void DeleteData() {
					    // Get the currently selected row
					    int selectedRow = TableCalendar.getSelectedRow();
					    if (selectedRow == -1) {
					        JOptionPane.showMessageDialog(this, "Please select a row to Delete.", "No Selection", JOptionPane.WARNING_MESSAGE);
					        return;
					    }
			
					    // Confirm deletion
					    int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete the selected Acad Schedule?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
					    if (confirm != JOptionPane.YES_OPTION) {
					        return;
					    }
			
					    // Get the EnrollmentID from the selected row
					    String acadID = Objects.toString(tableModelCalendar.getValueAt(selectedRow, 0), "");
			
					    // Delete from the database
					    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
					        String deleteQuery = "DELETE FROM Academic_Year_Schedule WHERE AcademicYearID = ?";
					        try (PreparedStatement deleteStmt = conn.prepareStatement(deleteQuery)) {
					            deleteStmt.setString(1, acadID);
			
					            int rowsDeleted = deleteStmt.executeUpdate();
					            
					            if (rowsDeleted > 0) {
					                JOptionPane.showMessageDialog(this, "Acad Schedule deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
			
					                // Remove the row from AllTableData
					                AllTableData.removeIf(row -> row[0].toString().equals(acadID));
			
					                // Remove the row from the table model
					                 tableModelCalendar.removeRow(selectedRow);
			
					                // Clear input fields
					                ClearData();
					                
					            } else {
					                JOptionPane.showMessageDialog(this, "Failed to delete the Acad Schedule.", "Error", JOptionPane.ERROR_MESSAGE);
					            }
					        }
					    } catch (Exception e) {
					        e.printStackTrace();
					        JOptionPane.showMessageDialog(this, "Error deleting Acad Schedule: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
					    }
					}
			
					
				
				
				
					
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
				                    rs.getString("AcademicYearID"),
				                    rs.getString("AcademicYear"),
				                    rs.getString("Date"),
				                    rs.getString("Event"),
				                    rs.getString("Semester"),
				                    rs.getString("Notes")
				               
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
				        
			
			
			
			
			
				        
				        // Method to Load the latest Academic Year in our Edit Calendar 
				        private void UpdateAcadYearinCalendar() {
				            Connection connection = null;
				            Statement statement = null;
				            ResultSet resultSet = null;

				            try {
				                // Connect to the database
				                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				                statement = connection.createStatement();

				             
				                
				                // Query to fetch the current academic year
			                    String YearQuery = "SELECT DISTINCT AcademicYear FROM Academic_Year_Schedule ORDER BY AcademicYear DESC LIMIT 1";
			                    resultSet = statement.executeQuery(YearQuery);
			                    if (resultSet.next()) {
			                        String littleacademicyear = resultSet.getString("AcademicYear");
			                        YeartextField.setText(littleacademicyear);
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


				        
				        
				        
				        
						
						CustomButtonDelete DeleteB = new CustomButtonDelete("Delete", 18);
						DeleteB.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold"));
						DeleteB.setBounds(377, 608, 95, 37);
						contentPane.add(DeleteB);
						DeleteB.addActionListener(_ -> DeleteData());
						
						CustomButtonUpdate UpdateB = new CustomButtonUpdate("Update", 18);
						UpdateB.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold"));
						UpdateB.setBounds(263, 608, 95, 37);
						contentPane.add(UpdateB);
						UpdateB.addActionListener(_ -> UpdateData());
						
						CustomButtonAdd AddB = new CustomButtonAdd("Add", 18);
						AddB.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold"));
						AddB.setBounds(150, 608, 95, 37);
						contentPane.add(AddB);
						AddB.addActionListener(_ -> AddData());
						
						CustomButtonClear ClearB = new CustomButtonClear("Clear", 18);
						ClearB.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold"));
						ClearB.setBounds(37, 608, 95, 37);
						contentPane.add(ClearB);
						ClearB.addActionListener(_ -> ClearData());
					
						
							JLabel LineLabel = new JLabel("______________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________");
							LineLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
							LineLabel.setForeground(new Color(128, 128, 128));
							LineLabel.setBounds(0, -18, 2000, 25);
							contentPane.add(LineLabel);
						
							
							JLabel AcademicCalendarLabel = new JLabel("Academic Calendar");
							AcademicCalendarLabel.setBounds(440, 318, 293, 31);
							AcademicCalendarLabel.setFont(AcadSyncFontManager.getFont("Arvo-Bold"));
							
							contentPane.add(AcademicCalendarLabel);
							
							AcadIDtextField = new CustomRoundedTextField(10);
							AcadIDtextField.setColumns(100);
							AcadIDtextField.setBorderThickness(3);
							AcadIDtextField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
							AcadIDtextField.setEditable(false);
							AcadIDtextField.setBorderColor(new Color(199, 21, 133));
							AcadIDtextField.setBounds(147, 460, 231, 37);
							contentPane.add(AcadIDtextField);
							
							DatetextField = new CustomRoundedTextField(10);
							DatetextField.setColumns(100);
							DatetextField.setBorderThickness(3);
							DatetextField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
							DatetextField.setBorderColor(new Color(199, 21, 133));
							DatetextField.setBounds(440, 460, 231, 37);
							contentPane.add(DatetextField);
							
							
							EventtextField = new CustomRoundedTextField(10);
							EventtextField.setColumns(100);
							EventtextField.setBorderThickness(3);
							EventtextField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
							EventtextField.setBorderColor(new Color(199, 21, 133));
							EventtextField.setBounds(735, 460, 231, 37);
							contentPane.add(EventtextField);
							
							
							JLabel AcadIDLabel = new JLabel("Acad Year ID");
							AcadIDLabel.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
							AcadIDLabel.setBounds(157, 445, 100, 13);
							contentPane.add(AcadIDLabel);
							
							JLabel DateLabel = new JLabel("Date");
							DateLabel.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
							DateLabel.setBounds(451, 445, 67, 13);
							contentPane.add(DateLabel);
							
							
							JLabel EventLabel = new JLabel("Event");
							EventLabel.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
							EventLabel.setBounds(746, 445, 129, 13);
							contentPane.add(EventLabel);
							
							AcademicYeartextField = new CustomRoundedTextField(10);
							AcademicYeartextField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
							AcademicYeartextField.setColumns(100);
							AcademicYeartextField.setBorderThickness(3);
							AcademicYeartextField.setBorderColor(new Color(199, 21, 133));
							AcademicYeartextField.setBounds(147, 538, 231, 37);
							contentPane.add(AcademicYeartextField);
							
							SemestertextField = new CustomRoundedTextField(10);
							SemestertextField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
							SemestertextField.setColumns(100);
							SemestertextField.setBorderThickness(3);
							SemestertextField.setBorderColor(new Color(199, 21, 133));
							SemestertextField.setBounds(440, 538, 231, 37);
							contentPane.add(SemestertextField);
							
							NotetextField = new CustomRoundedTextField(10);
							NotetextField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
							NotetextField.setColumns(100);
							NotetextField.setBorderThickness(3);
							NotetextField.setBorderColor(new Color(199, 21, 133));
							NotetextField.setBounds(735, 538, 231, 37);
							contentPane.add(NotetextField);
							
							
							
							JLabel AcademicYearLabel = new JLabel("Academic Year");
							AcademicYearLabel.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
							AcademicYearLabel.setBounds(157, 523, 129, 13);
							contentPane.add(AcademicYearLabel);
							
							JLabel SemesterLabel = new JLabel("Semester");
							SemesterLabel.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
							SemesterLabel.setBounds(451, 523, 100, 13);
							contentPane.add(SemesterLabel);
							
							JLabel NoteLabel = new JLabel("Note");
							NoteLabel.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
							NoteLabel.setBounds(746, 523, 129, 13);
							contentPane.add(NoteLabel);
							
							
							
							 // Add a mouse listener to the table
							   TableCalendar.addMouseListener(new java.awt.event.MouseAdapter() {
							        @Override
							        public void mouseClicked(java.awt.event.MouseEvent event) {
							            int selectedRow = TableCalendar.getSelectedRow();
							            
			
							            if (selectedRow != -1) {
							                // Safely retrieve data from the selected row
							            	String acadID = Objects.toString(tableModelCalendar.getValueAt(selectedRow, 0), "");
							            	String academicYear = Objects.toString(tableModelCalendar.getValueAt(selectedRow, 1), "");
							            	String date = Objects.toString(tableModelCalendar.getValueAt(selectedRow, 2), "");
							            	String event1 = Objects.toString(tableModelCalendar.getValueAt(selectedRow, 3), "");
							            	String semester = Objects.toString(tableModelCalendar.getValueAt(selectedRow, 4), "");
							            	String note = Objects.toString(tableModelCalendar.getValueAt(selectedRow, 5), "");

							           
							            	
							                     
							                // Set data to text fields
							            	AcadIDtextField.setText(acadID);
							  	            AcademicYeartextField.setText(academicYear);
							  	            SemestertextField.setText(semester);
							  	            EventtextField.setText(event1);
							  	            DatetextField.setText(date);
							  	            NotetextField.setText(note);
							  	            
							  	  
								            
							
							
							
							            }
							        }
							    });
							
	    
		
		
		
	}
}

