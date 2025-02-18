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




public class AdminEditStudentGrades extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable TableStudentGrades;
	private DefaultTableModel tableModelGrades;

	// Database credentials
    private String DB_URL = DatabaseCredentials.getDBURL();
    private String DB_USER = DatabaseCredentials.getDBUSER();
    private String DB_PASSWORD = DatabaseCredentials.getDBPASS();
	
	private CustomRoundedTextField GradeIDtextField;
    private CustomRoundedTextField StudentNumbertextField;
	private CustomRoundedTextField AcademicYeartextField;
	private CustomRoundedTextField SemestertextField;
	private CustomRoundedTextField SectiontextField;
	private CustomRoundedTextField SchedCodetextField;
    private CustomRoundedTextField SubjectCodetextField;
    private CustomRoundedTextField DescriptiontextField;
    private CustomRoundedTextField GradetextField;
    private CustomRoundedTextField UnittextField;
    private CustomRoundedTextField CreditUnittextField;
    private CustomRoundedTextField CompletiontextField;
    private CustomRoundedTextField Schedule1textField;
    private CustomRoundedTextField Schedule2textField;
    private CustomRoundedTextField Schedule3textField;
    private CustomRoundedTextField Schedule4textField;
    private CustomRoundedTextField InstructortextField;
   
	 
	private JTextField SearchtextField;
	
	 
	public static void main(String[] args) {
	
	    EventQueue.invokeLater(() -> {
	        try {
	        	 AdminEditStudentGrades frame = new  AdminEditStudentGrades();
	            frame.setVisible(true);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }); 
	}
 
	       
	/**
	 * Create the frame.
	 */
	public  AdminEditStudentGrades() {
		setTitle("Edit Student Grades");
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
	        	 AdminStudentGrades AdminStudentGradesNoNavigation = new AdminStudentGrades();
		         AdminStudentGradesNoNavigation.setVisible(true);
		        	 
		         dispose();
	        }
	    });
		
		
		
					// Initialize JTable and DefaultTableModel
			        tableModelGrades = new DefaultTableModel() {
			        private static final long serialVersionUID = 1L;
					
			         @Override
			         public boolean isCellEditable(int row, int column) {
			             return false;  // Prevent all cells from being editable
			         	}
			     	};
			     	
			     	
			        TableStudentGrades = new JTable(tableModelGrades);
				    TableStudentGrades.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Only one row can be selected at a time
				    TableStudentGrades.setBounds(70, 10, 600, 200);
				    
			
			        // Set font and styling for rows
			        if (AcadSyncFontManager.getFont("Poppins-SemiBold1") != null) {
			            TableStudentGrades.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
			        } else {
			            System.out.println("Custom font not loaded; using default font.");
			        }
			        
			        
			        
			        TableStudentGrades.setRowHeight(30);
			        TableStudentGrades.setBackground(Color.WHITE);
			        TableStudentGrades.setGridColor(Color.BLACK);
			        TableStudentGrades.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
			
			        
			        
			        // Customize table header font
			        if (AcadSyncFontManager.getFont("Poppins-SemiBold1") != null) {
			            TableStudentGrades.getTableHeader().setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1").deriveFont(13f)); // Larger font for header
			            TableStudentGrades.getTableHeader().setBackground(Color.DARK_GRAY); // Header background color
			            TableStudentGrades.getTableHeader().setForeground(Color.WHITE); // Header text color
			        }
			
			        // Custom cell renderer for selected row behavior
			        TableStudentGrades.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
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
			        tableModelGrades.addColumn("GRADE ID");
			        tableModelGrades.addColumn("STUDENT NUMBER");
			        tableModelGrades.addColumn("ACADEMIC YEAR");
			        tableModelGrades.addColumn("SEMESTER");
			        tableModelGrades.addColumn("SECTION");
			        tableModelGrades.addColumn("SCHED CODE");
			        tableModelGrades.addColumn("SUBJECT CODE");
			        tableModelGrades.addColumn("DESCRIPTION"); 
			        tableModelGrades.addColumn("SCHEDULE 1");
			        tableModelGrades.addColumn("SCHEDULE 2");
			        tableModelGrades.addColumn("SCHEDULE 3");
			        tableModelGrades.addColumn("SCHEDULE 4");
			        tableModelGrades.addColumn("INSTRUCTOR");
			        tableModelGrades.addColumn("GRADE");
			        tableModelGrades.addColumn("UNIT");
			        tableModelGrades.addColumn("CREDIT UNIT");  
			        tableModelGrades.addColumn("COMPLETION");
			       
			
			        
			        
			        // Add table to a scroll pane
			        JScrollPane scrollPane = new JScrollPane(TableStudentGrades);
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
			                tableModelGrades.setRowCount(0); // Clear the table
			
			                // Add all rows back to the table model from the full data list
			                for (Object[] row : AllTableData) { // `AllTableData` contains the full data set
			              	  tableModelGrades.addRow(row);
			                }
			            }
			        });	  
			        
			        
			        
			        // Load data from database
			        LoadDataFromDatabase(AcadSyncFontManager.getFont("Poppins-SemiBold1"), AcadSyncFontManager.getFont("Poppins-Bold3"), AcadSyncFontManager.getFont("Poppins-SemiBold"), AcadSyncFontManager.getFont("Arvo-Bold1"));
			
	      }
							
			        
        
	
	
	
			        
//----------------------------------------------------------------------------Method------------------------------------------------------------------------
	
	
	
	     
	
	
				//Method for Search a data inside our JtextField 
				private void SearchData(String searchTerm) {
				    tableModelGrades.setRowCount(0); // Clear the table
			
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
				            tableModelGrades.addRow(row);
				        }
				    }
				}
			
			
			
			
				
			
				//Method to Clear the text inside our JtextField 
				private void ClearData() {
			    	  // Clear all the text fields
			            GradeIDtextField.setText("");
			            StudentNumbertextField.setText("");
			            GradetextField.setText("");
			            DescriptiontextField.setText("");
			            SubjectCodetextField.setText("");
			            SchedCodetextField.setText("");
			            SectiontextField.setText("");
			            SemestertextField.setText("");
			            UnittextField.setText("");
			            AcademicYeartextField.setText("");
			            CreditUnittextField.setText("");
			            CompletiontextField.setText("");
			            Schedule1textField.setText("");
			            Schedule2textField.setText("");
			            Schedule3textField.setText("");
			            Schedule4textField.setText("");
			            InstructortextField.setText("");
			          
			
			            //Reset selected items or clear any custom selections
			            TableStudentGrades.clearSelection();
			
			        }
				
				
				
			
				
				
				//Method to Delete a data inside our database as well as the row in our table
				private void DeleteData() {
				    // Get the currently selected row
				    int selectedRow = TableStudentGrades.getSelectedRow();
				    if (selectedRow == -1) {
				        JOptionPane.showMessageDialog(this, "Please select a row to Delete.", "No Selection", JOptionPane.WARNING_MESSAGE);
				        return;
				    }

				    // Confirm deletion
				    int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete the selected Studen Grade", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
				    if (confirm != JOptionPane.YES_OPTION) {
				        return;
				    }

				    // Get the GradeID from the selected row
				    String gradeID = Objects.toString(tableModelGrades.getValueAt(selectedRow, 0), "");

				    // Delete from the database
				    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
				        String deleteQuery = "DELETE FROM Student_Grades WHERE GradeID = ?";
				        try (PreparedStatement deleteStmt = conn.prepareStatement(deleteQuery)) {
				            deleteStmt.setString(1, gradeID);

				            int rowsDeleted = deleteStmt.executeUpdate();
				            if (rowsDeleted > 0) {
				                JOptionPane.showMessageDialog(this, "Student Grade deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

				                // Remove the row from AllTableData
				                AllTableData.removeIf(row -> row[0].toString().equals(gradeID));

				                // Remove the row from the table model
				                 tableModelGrades.removeRow(selectedRow);

				                // Clear input fields
				                ClearData();
				            } else {
				                JOptionPane.showMessageDialog(this, "Failed to delete the Student Grade.", "Error", JOptionPane.ERROR_MESSAGE);
				            }
				        }
				    } catch (Exception e) {
				        e.printStackTrace();
				        JOptionPane.showMessageDialog(this, "Error deleting Student Grade: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
				    }
				}

				
			
				
				
				//Method to Update a data inside our database as well as the row in our table
				private void UpdateData() {
				    // Get the currently selected row
				    int selectedRow = TableStudentGrades.getSelectedRow();
				    if (selectedRow == -1) {
				        JOptionPane.showMessageDialog(this, "Please select a row to Update.", "No Selection", JOptionPane.WARNING_MESSAGE);
				        return;
				    }
			
				    // Get values from text fields
				    String gradeID = GradeIDtextField.getText().trim();
				    String grade = GradetextField.getText().trim();
				    String completion = CompletiontextField.getText().trim();
				

			
				    // Validate grade as decimal, if entered
				    if (!grade.isEmpty()) {
				        try {
				            Double.parseDouble(grade);
				        } catch (NumberFormatException e) {
				            JOptionPane.showMessageDialog(this, "Grade must be a valid integer or decimal.", "Validation Error", JOptionPane.WARNING_MESSAGE);
				            return;
				        }
				    }
				    
				    
				    // Define the list of valid completion
				    List<String> validCompletion = Arrays.asList("Completed", "Incompleted");
				    

				    // Validate Completion  against the list of valid completion
				    if (!validCompletion.contains(completion)) {
				        JOptionPane.showMessageDialog(this, "Invalid Completion. Should only be: " + validCompletion, "Validation Error", JOptionPane.WARNING_MESSAGE);
				        return;
				    }
				  
				    
				    // Update database
				    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
				    	String updateQuery = "UPDATE Student_Grades SET Grade = ?, Completion = ? WHERE GradeID = ?";
				        try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
				            updateStmt.setString(1, grade.isEmpty() ? null : grade); // If grade is empty, set it as null
				            updateStmt.setString(2, completion.isEmpty() ? null : completion); // If completion is empty, set it as null
				            updateStmt.setString(3, gradeID);
				            
				            int rowsUpdated = updateStmt.executeUpdate();
			
				            if (rowsUpdated > 0) {
				                JOptionPane.showMessageDialog(this, "Student Grade successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
			
				                
				                // Update the table model
				                tableModelGrades.setValueAt(gradeID, selectedRow, 0);
				                tableModelGrades.setValueAt(grade, selectedRow, 13);
				                tableModelGrades.setValueAt(completion, selectedRow, 16);
				     
			
				                
				                // Update AllTableData
				                for (Object[] row : AllTableData) {
				                    if (row[0].toString().equals(gradeID)) { // Match by Grade ID
				                        row[13] = grade;
				                        row[16] = completion;
				                        break;
				                    }
				                }
				        
			
				            } else {
				                JOptionPane.showMessageDialog(this, "Failed to update Student Grade.", "Error", JOptionPane.ERROR_MESSAGE);
				            }
				        }
				    } catch (Exception e) {
				        e.printStackTrace();
				        JOptionPane.showMessageDialog(this, "Error updating Student Grade: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
				    }
				}
			
				
			
				
			
			
			
				
			   // Method to Load data from the database and populate the table
				private List<Object[]> AllTableData = new ArrayList<>(); // Cache to hold all rows
			    private void LoadDataFromDatabase(Font poppinsSemiBold, Font poppinsBold3, Font poppinsSemiBold1, Font arvoBold1) {
			        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			             Statement stmt = conn.createStatement();
			             ResultSet rs = stmt.executeQuery("SELECT * FROM Student_Grades")) {
			        	
			        	  // Clear the existing table data and cache
			            tableModelGrades.setRowCount(0);
			            AllTableData.clear();
			
			            while (rs.next()) {
			                // Create a row object from the ResultSet
			                Object[] row = new Object[] {
			                    rs.getString("GradeID"),
			                    rs.getString("StudentNumber"),
			                    rs.getString("AcademicYear"),
			                    rs.getString("Semester"),
			                    rs.getString("Section"),
			                    rs.getString("SchedCode"),
			                    rs.getString("SubjectCode"),
			                    rs.getString("Description"),
			                    rs.getString("Schedule1"),
			                    rs.getString("Schedule2"),
			                    rs.getString("Schedule3"),
			                    rs.getString("Schedule4"),
			                    rs.getString("Instructor"),
			                    rs.getString("Grade"),
			                    rs.getString("Unit"),
			                    rs.getString("CreditUnit"),
			                    rs.getString("Completion")
			                   
			               
			                };
			
			                // Add the row to the cache
			                AllTableData.add(row);
			
			                // Add the row to the table model
			                tableModelGrades.addRow(row);
			            }
			        } catch (Exception e) {
			            e.printStackTrace();
			            JOptionPane.showMessageDialog(this, "Error loading data from database: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
			        }
			        
			        
        
        
       
        
        
        
        


        
        
        
   
//----------------------------------------------------------------------------------------------------------------------------------------------------------	        

		
			        
			        
			        
			         
			        
			       
							
					CustomButtonAdd4 ClearB = new CustomButtonAdd4("Clear", 18);
					ClearB.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold"));
					ClearB.setBounds(37, 678, 95, 37);
					contentPane.add(ClearB);
					ClearB.addActionListener(_ -> ClearData());
												
					CustomButtonUpdate4 UpdateB = new CustomButtonUpdate4("Update", 18);
					UpdateB.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold"));
					UpdateB.setBounds(150, 678, 95, 37);
					contentPane.add(UpdateB);
					UpdateB.addActionListener(_ -> UpdateData());
					
					
					CustomButtonDelete4 DeleteB = new CustomButtonDelete4("Delete", 18);
					DeleteB.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold"));
					DeleteB.setBounds(263, 678, 95, 37);
					contentPane.add(DeleteB);
					DeleteB.addActionListener(_ -> DeleteData());
					
								
					
					JLabel LineLabel = new JLabel("______________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________");
					LineLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
					LineLabel.setForeground(new Color(128, 128, 128));
					LineLabel.setBounds(0, -18, 2000, 25);
					contentPane.add(LineLabel);
				
					
					JLabel StudentGradesLabel = new JLabel("Student Grades");
					StudentGradesLabel.setBounds(475, 315, 353, 31);
					StudentGradesLabel.setFont(AcadSyncFontManager.getFont("Arvo-Bold1"));
					
					contentPane.add(StudentGradesLabel);
					
					SemestertextField = new CustomRoundedTextField(10);
					SemestertextField.setColumns(100);
					SemestertextField.setBorderThickness(3);
					SemestertextField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
					SemestertextField.setBorderColor(new Color(94, 20, 187));
					SemestertextField.setBounds(300, 457, 231, 37);
		            SemestertextField.setEditable(false);
					contentPane.add(SemestertextField);
					
					DescriptiontextField = new CustomRoundedTextField(10);
					DescriptiontextField.setColumns(100);
					DescriptiontextField.setBorderThickness(3);
					DescriptiontextField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
					DescriptiontextField.setBorderColor(new Color(94, 20, 187));
					DescriptiontextField.setBounds(831, 457, 231, 37);
		            DescriptiontextField.setEditable(false);
					contentPane.add(DescriptiontextField);
					
					
					StudentNumbertextField = new CustomRoundedTextField(10);
					StudentNumbertextField.setFont(null);
					StudentNumbertextField.setColumns(100);
					StudentNumbertextField.setBorderThickness(3);
					StudentNumbertextField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
					StudentNumbertextField.setBorderColor(new Color(94, 20, 187));
					StudentNumbertextField.setBounds(300, 388, 231, 37);
				    StudentNumbertextField.setEditable(false);
					contentPane.add(StudentNumbertextField);
					
					GradeIDtextField = new CustomRoundedTextField(10);
					GradeIDtextField.setFont(null);
					GradeIDtextField.setColumns(100);
					GradeIDtextField.setBorderThickness(3);
					GradeIDtextField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
					GradeIDtextField.setBorderColor(new Color(94, 20, 187));
					GradeIDtextField.setBounds(37, 388, 231, 37);
				    GradeIDtextField.setEditable(false);
					contentPane.add(GradeIDtextField);
					
					CompletiontextField = new CustomRoundedTextField(10);
					CompletiontextField.setFont(null);
					CompletiontextField.setColumns(100);
					CompletiontextField.setBorderThickness(3);
					CompletiontextField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
					CompletiontextField.setBorderColor(new Color(94, 20, 187));
					CompletiontextField.setBounds(831, 526, 231, 37);
					contentPane.add(CompletiontextField);
					
					
					SectiontextField = new CustomRoundedTextField(10);
					SectiontextField.setFont(null);
					SectiontextField.setColumns(100);
					SectiontextField.setBorderThickness(3);
					SectiontextField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
					SectiontextField.setBorderColor(new Color(94, 20, 187));
					SectiontextField.setBounds(564, 388, 231, 37);
		            SectiontextField.setEditable(false);
					contentPane.add(SectiontextField);
					
					GradetextField = new CustomRoundedTextField(10);
					GradetextField.setFont(null);
					GradetextField.setColumns(100);
					GradetextField.setBorderThickness(3);
					GradetextField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
					GradetextField.setBorderColor(new Color(94, 20, 187));
					GradetextField.setBounds(37, 457, 231, 37);
					contentPane.add(GradetextField);
					
					SubjectCodetextField = new CustomRoundedTextField(10);
					SubjectCodetextField.setFont(null);
					SubjectCodetextField.setColumns(100);
					SubjectCodetextField.setBorderThickness(3);
					SubjectCodetextField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
					SubjectCodetextField.setBorderColor(new Color(94, 20, 187));
					SubjectCodetextField.setBounds(564, 457, 231, 37);
		            SubjectCodetextField.setEditable(false);
					contentPane.add(SubjectCodetextField);
					
					AcademicYeartextField = new CustomRoundedTextField(10);
					AcademicYeartextField.setFont(null);
					AcademicYeartextField.setColumns(100);
					AcademicYeartextField.setBorderThickness(3);
					AcademicYeartextField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
					AcademicYeartextField.setBorderColor(new Color(94, 20, 187));
					AcademicYeartextField.setBounds(831, 388, 231, 37);
		            AcademicYeartextField.setEditable(false);
					contentPane.add(AcademicYeartextField);
					
					
					UnittextField = new CustomRoundedTextField(10);
					UnittextField.setFont(null);
					UnittextField.setColumns(100);
					UnittextField.setBorderThickness(3);
					UnittextField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
					UnittextField.setBorderColor(new Color(94, 20, 187));
					UnittextField.setBounds(300, 526, 231, 37);
		            UnittextField.setEditable(false);
					contentPane.add(UnittextField);
					
					CreditUnittextField = new CustomRoundedTextField(10);
					CreditUnittextField.setFont(null);
					CreditUnittextField.setColumns(100);
					CreditUnittextField.setBorderThickness(3);
					CreditUnittextField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
					CreditUnittextField.setBorderColor(new Color(94, 20, 187));
					CreditUnittextField.setBounds(564, 526, 231, 37);
		            CreditUnittextField.setEditable(false);
					contentPane.add(CreditUnittextField);
					
					SchedCodetextField = new CustomRoundedTextField(10);
					SchedCodetextField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
					SchedCodetextField.setColumns(100);
					SchedCodetextField.setBorderThickness(3);
					SchedCodetextField.setBorderColor(new Color(94, 20, 187));
					SchedCodetextField.setBounds(37, 526, 231, 37);
		            SchedCodetextField.setEditable(false);
					contentPane.add(SchedCodetextField);
					
					
					
					JLabel StudentNumberLabel = new JLabel("Student Number");
					StudentNumberLabel.setBounds(309, 373, 109, 13);
					StudentNumberLabel.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
					contentPane.add(StudentNumberLabel);
					
					JLabel GradeIDLabel = new JLabel("Grade ID");
					GradeIDLabel.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
					GradeIDLabel.setBounds(45, 373, 95, 13);
					contentPane.add(GradeIDLabel);
					
					JLabel CompletionLabel = new JLabel("Completion");
					CompletionLabel.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
					CompletionLabel.setBounds(838, 511, 90, 13);
					contentPane.add(CompletionLabel);
					
					JLabel CreditUnitLabel = new JLabel("Credit Units");
					CreditUnitLabel.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
					CreditUnitLabel.setBounds(573, 511, 95, 13);
					contentPane.add(CreditUnitLabel);
					
					JLabel DescriptionLabel = new JLabel("Description");
					DescriptionLabel.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
					DescriptionLabel.setBounds(838, 442, 122, 13);
					contentPane.add(DescriptionLabel);
					
					JLabel SectionLabel = new JLabel("Section");
					SectionLabel.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
					SectionLabel.setBounds(573, 373, 109, 13);
					contentPane.add(SectionLabel);
					
					JLabel SemesterLabel = new JLabel("Semester");
					SemesterLabel.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
					SemesterLabel.setBounds(309, 442, 95, 13);
					contentPane.add(SemesterLabel);
					
					JLabel GradeLabel = new JLabel("Grade");
					GradeLabel.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
					GradeLabel.setBounds(45, 442, 109, 13);
					contentPane.add(GradeLabel);
					
					JLabel SubjectCodeLabel = new JLabel("Subject Code");
					SubjectCodeLabel.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
					SubjectCodeLabel.setBounds(573, 442, 129, 13);
					contentPane.add(SubjectCodeLabel);
					
					JLabel AcademicYearLabel = new JLabel("Academic Year");
					AcademicYearLabel.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
					AcademicYearLabel.setBounds(838, 373, 122, 13);
					contentPane.add(AcademicYearLabel);
					
					JLabel UnitsLabel = new JLabel("Units");
					UnitsLabel.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
					UnitsLabel.setBounds(309, 511, 177, 13);
					contentPane.add(UnitsLabel);
					
					JLabel SchedCodeLabel = new JLabel("Sched Code");
					SchedCodeLabel.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
					SchedCodeLabel.setBounds(45, 511, 90, 13);
					contentPane.add(SchedCodeLabel);
					
					Schedule1textField = new CustomRoundedTextField(10);
					Schedule1textField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
					Schedule1textField.setColumns(100);
					Schedule1textField.setBorderThickness(3);
					Schedule1textField.setBorderColor(new Color(94, 20, 187));
					Schedule1textField.setBounds(37, 593, 231, 37);
					Schedule1textField.setEditable(false);
					contentPane.add(Schedule1textField);
					
					Schedule2textField = new CustomRoundedTextField(10);
					Schedule2textField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
					Schedule2textField.setColumns(100);
					Schedule2textField.setBorderThickness(3);
					Schedule2textField.setBorderColor(new Color(94, 20, 187));
					Schedule2textField.setBounds(300, 593, 231, 37);
					Schedule2textField.setEditable(false);
					contentPane.add(Schedule2textField);
					
					Schedule3textField = new CustomRoundedTextField(10);
					Schedule3textField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
					Schedule3textField.setColumns(100);
					Schedule3textField.setBorderThickness(3);
					Schedule3textField.setBorderColor(new Color(94, 20, 187));
					Schedule3textField.setBounds(564, 593, 231, 37);
					Schedule3textField.setEditable(false);
					contentPane.add(Schedule3textField);
					
					Schedule4textField = new CustomRoundedTextField(10);
					Schedule4textField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
					Schedule4textField.setColumns(100);
					Schedule4textField.setBorderThickness(3);
					Schedule4textField.setBorderColor(new Color(94, 20, 187));
					Schedule4textField.setBounds(831, 593, 231, 37);
					Schedule4textField.setEditable(false);
					contentPane.add(Schedule4textField);
					
					InstructortextField = new CustomRoundedTextField(10);
					InstructortextField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold1"));
					InstructortextField.setColumns(100);
					InstructortextField.setBorderThickness(3);
					InstructortextField.setBorderColor(new Color(94, 20, 187));
					InstructortextField.setBounds(451, 663, 231, 37);
					InstructortextField.setEditable(false);
					contentPane.add(InstructortextField);
					
					
					JLabel Schedule1Label = new JLabel("Schedule 1");
					Schedule1Label.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
					Schedule1Label.setBounds(45, 578, 90, 13);
					contentPane.add(Schedule1Label);
					
					JLabel Schedule2Label = new JLabel("Schedule 2");
					Schedule2Label.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
					Schedule2Label.setBounds(309, 578, 90, 13);
					contentPane.add(Schedule2Label);
					
					JLabel Schedule3Label = new JLabel("Schedule 3");
					Schedule3Label.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
					Schedule3Label.setBounds(573, 578, 90, 13);
					contentPane.add(Schedule3Label);
					
					JLabel Schedule4Label = new JLabel("Schedule 4");
					Schedule4Label.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
					Schedule4Label.setBounds(838, 578, 90, 13);
					contentPane.add(Schedule4Label);
					
					JLabel InstructorLabel = new JLabel("Instructor");
					InstructorLabel.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
					InstructorLabel.setBounds(460, 648, 90, 13);
					contentPane.add(InstructorLabel);
					
					   // Add a mouse listener to the table
					   TableStudentGrades.addMouseListener(new java.awt.event.MouseAdapter() {
					        @Override
					        public void mouseClicked(java.awt.event.MouseEvent event) {
					            int selectedRow = TableStudentGrades.getSelectedRow();
					            

					            if (selectedRow != -1) {
					                // Safely retrieve data from the selected row
					            	String gradeID = Objects.toString(tableModelGrades.getValueAt(selectedRow, 0), "");
					            	String studentNumber = Objects.toString(tableModelGrades.getValueAt(selectedRow, 1), "");
					            	String academicYear = Objects.toString(tableModelGrades.getValueAt(selectedRow, 2), "");
					            	String semester = Objects.toString(tableModelGrades.getValueAt(selectedRow, 3), "");
					            	String section = Objects.toString(tableModelGrades.getValueAt(selectedRow, 4), "");
					            	String schedCode = Objects.toString(tableModelGrades.getValueAt(selectedRow, 5), "");
					            	String subjectCode = Objects.toString(tableModelGrades.getValueAt(selectedRow, 6), "");
					            	String description = Objects.toString(tableModelGrades.getValueAt(selectedRow, 7), "");
					            	String schedule1 = Objects.toString(tableModelGrades.getValueAt(selectedRow, 8), "");
					            	String schedule2 = Objects.toString(tableModelGrades.getValueAt(selectedRow, 9), "");
					            	String schedule3 = Objects.toString(tableModelGrades.getValueAt(selectedRow, 10), "");
					            	String schedule4 = Objects.toString(tableModelGrades.getValueAt(selectedRow, 11), "");
					            	String instructor = Objects.toString(tableModelGrades.getValueAt(selectedRow, 12), "");
					            	String grade = Objects.toString(tableModelGrades.getValueAt(selectedRow, 13), "");
					            	String unit = Objects.toString(tableModelGrades.getValueAt(selectedRow, 14), "");
					            	String creditUnit = Objects.toString(tableModelGrades.getValueAt(selectedRow, 15), "");
					            	String completion = Objects.toString(tableModelGrades.getValueAt(selectedRow, 16), "");
					            	
					            	
					            	
					                // Set data to text fields
					                GradeIDtextField.setText(gradeID);
						            StudentNumbertextField.setText(studentNumber);
						            GradetextField.setText(grade);
						            DescriptiontextField.setText(description);
						            SubjectCodetextField.setText(subjectCode);
						            SchedCodetextField.setText(schedCode);
						            SectiontextField.setText(section);
						            SemestertextField.setText(semester);
						            UnittextField.setText(unit);
						            AcademicYeartextField.setText(academicYear);
						            CreditUnittextField.setText(creditUnit);
						            CompletiontextField.setText(completion);
						            Schedule1textField.setText(schedule1);
						            Schedule2textField.setText(schedule2);
						            Schedule3textField.setText(schedule3);
						            Schedule4textField.setText(schedule4);
						            InstructortextField.setText(instructor);
					                
					                
					             
								    
					            }
					        }
					    });
					
			
					
				
					
					
					
					
					
					
	
		
		
		
	}
}



