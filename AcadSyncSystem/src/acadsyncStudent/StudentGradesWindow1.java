package acadsyncStudent;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import acadsyncFile.AcadSyncFontManager;
import acadsyncFile.DatabaseCredentials;

public class StudentGradesWindow1 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtInc;
	private JTextField acadyrTextfield;
	private JTextField semTextfield;
	private JTable TableStudentGrades;
    private DefaultTableModel tableModelGrades;
    private String AcademicYear = GradesButtonSession.getAcademicYear();
	private String Semester = GradesButtonSession.getSemester();
	private int studentNumber = Session.getStudentNumber();   
	
	// Database credentials
    private String DB_URL = DatabaseCredentials.getDBURL();
    private String DB_USER = DatabaseCredentials.getDBUSER();
    private String DB_PASSWORD = DatabaseCredentials.getDBPASS();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentGradesWindow1 frame = new StudentGradesWindow1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 *
	 */
	public StudentGradesWindow1() {
		UIManager.put("Button.select", new Color(69, 70, 73));
		setResizable(false);
		setTitle("Academic Grade");
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/SMS Logo.png"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 900, 550);
		setLocationRelativeTo(null);  // Center the frame on the screen
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 886, 513);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		 	
		
      
         // move to while in fetch data class
         acadyrTextfield = new JTextField();
         acadyrTextfield.setText("Academic Year: " + AcademicYear); 
         
         semTextfield = new JTextField();
         semTextfield.setText("| " + Semester); 
         
         acadyrTextfield.setEditable(false);
         acadyrTextfield.setEnabled(false);
         acadyrTextfield.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold"));
         acadyrTextfield.setBorder(null);
         acadyrTextfield.setBackground(Color.WHITE);
         acadyrTextfield.setDisabledTextColor(Color.BLACK);
         acadyrTextfield.setBounds(28, 23, 208, 20);
         acadyrTextfield.setOpaque(true);
         panel.add(acadyrTextfield);
         acadyrTextfield.setColumns(10);
         
   
         semTextfield.setOpaque(true);
         semTextfield.setFont(AcadSyncFontManager.getFont("Poppins-Medium2"));
         semTextfield.setEnabled(false);
         semTextfield.setEditable(false);
         semTextfield.setDisabledTextColor(Color.BLACK);
         semTextfield.setColumns(10);
         semTextfield.setBorder(null);
         semTextfield.setBackground(Color.WHITE);
         semTextfield.setBounds(245, 23, 125, 20);
         panel.add(semTextfield);
         
  
         
		
		txtInc = new JTextField();
		txtInc.setEnabled(false);
		txtInc.setEditable(false);
		txtInc.setHorizontalAlignment(SwingConstants.CENTER);
		txtInc.setText("INC - Incomplete; DRP - Dropped; S - Satisfactory; US - Unsatisfactory");
		txtInc.setBounds(216, 381, 463, 20);
		txtInc.setDisabledTextColor(Color.BLACK);
		txtInc.setBorder(null);
		txtInc.setFont(AcadSyncFontManager.getFont("Poppins-Light"));
		txtInc.setBackground(Color.WHITE);
		panel.add(txtInc);
		txtInc.setColumns(10);
		
		 JTextPane textPane = new JTextPane();
		 textPane.setSize(802, 60);
		 textPane.setLocation(46, 412);
         textPane.setEditable(false);
         textPane.setFont(AcadSyncFontManager.getFont("Poppins-Light"));

         // Center text horizontally using StyledDocument
         StyledDocument doc = textPane.getStyledDocument();
         SimpleAttributeSet center = new SimpleAttributeSet();
         StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
         doc.setParagraphAttributes(0, doc.getLength(), center, false);
         textPane.setText("INC and 4.00 grade(s) CAN ONLY be completed within (1) Academic Year otherwise completion will be marked 5.00. Example: INC grades acquired in the current Second Semester can only be completed until the Second Semester of the next Academic Year.");
         panel.add(textPane);
		
	
	
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
    
    
       // Set table row height and background color
    TableStudentGrades.setRowHeight(30);
    TableStudentGrades.setBackground(Color.WHITE);
    TableStudentGrades.setGridColor(Color.BLACK);
    TableStudentGrades.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
       
    if (AcadSyncFontManager.getFont("Poppins-Medium2") != null) {
        TableStudentGrades.setFont(AcadSyncFontManager.getFont("Poppins-Medium2").deriveFont(12f));
    }
    

    if (AcadSyncFontManager.getFont("Poppins-Medium2") != null) {
    	TableStudentGrades.getTableHeader().setFont(AcadSyncFontManager.getFont("Poppins-Medium2").deriveFont(13f)); // Larger font for header
    	TableStudentGrades.getTableHeader().setBackground(new Color(40, 167, 69)); // Header background color
    	TableStudentGrades.getTableHeader().setForeground(Color.BLACK); // Header text color
    }
    
    
    // Set custom renderer
    TableStudentGrades.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
        private static final long serialVersionUID = 1L;

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            setHorizontalAlignment(CENTER);

         // Center the text
            setHorizontalAlignment(CENTER);

            // Remove any selection highlight
            component.setBackground(Color.WHITE); // Default background color
            component.setForeground(Color.BLACK);  // Default text color
            return component;
        }
    });
    
    
    
    // Column names
    tableModelGrades.addColumn("SUBJECT CODE");
    tableModelGrades.addColumn("DESCRIPTION");
    tableModelGrades.addColumn("GRADE");
    tableModelGrades.addColumn("COMPLETION");
    tableModelGrades.addColumn("UNIT");
    tableModelGrades.addColumn("CREDIT UNIT");   
    contentPane.setLayout(null);
    
 // Add table to a scroll pane
   JScrollPane scrollPane = new JScrollPane(TableStudentGrades);
   scrollPane.setBorder(new LineBorder(Color.BLACK, 2));
   scrollPane.setBounds(10, 52, 866, 315);
   panel.add(scrollPane);
   
	
   
   
 //Load Table Data
   LoadDataFromDatabase();
   		 

}


	// METHOD TO LOAD TABLE DATA
	private List<Object[]> AllTableData = new ArrayList<>(); // Cache to hold all rows
	private void LoadDataFromDatabase() {
		String query = "SELECT * FROM student_grades WHERE StudentNumber = ? AND Semester = ? AND AcademicYear = ?"; //add AND Deleted = 0;
		
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				 PreparedStatement statement = connection.prepareStatement(query)) {

	        	  // Clear the existing table data and cache
				statement.setInt(1, studentNumber);
				statement.setString(2, Semester);
				statement.setString(3, AcademicYear);	
				 
				 
	            tableModelGrades.setRowCount(0);

	            // Execute the query
	            try (ResultSet rs = statement.executeQuery()) {

	                // Clear the existing table data and cache
	                tableModelGrades.setRowCount(0);
	                AllTableData.clear(); // Clear cache before adding new data

	                while (rs.next()) {
	                	 // Get the grade value and check if it is NULL
	                    Float grade = rs.getFloat("Grade");
	                    if (rs.wasNull()) {
	                        grade = null; // Set to null if the value in the database is NULL
	                    }
	                    // Create a row object from the ResultSet
	                    Object[] row = new Object[] {
	                        rs.getString("SubjectCode"),
	                        rs.getString("Description"),
	                        grade,
	                        rs.getString("Completion"),
	                        rs.getInt("Unit"),
	                        rs.getInt("CreditUnit"),
	                    };

	                // Add the row to the cache
	                AllTableData.add(row);

	                // Add the row to the table model
	                tableModelGrades.addRow(row);
	            }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(this, "Error loading data from database: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
	        }
		}
}


