package acadsyncStudent;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import acadsyncFile.DatabaseCredentials;


public class CustomEnrollPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private static final int NUM_FIELDS = 8;
	private JTextField[][] textFields;
	private Font lightFont;
	private Font mediumFont;
	
	// Database credentials
    private static String DB_URL = DatabaseCredentials.getDBURL();
    private static String DB_USER = DatabaseCredentials.getDBUSER();
    private static String DB_PASSWORD = DatabaseCredentials.getDBPASS();

	
	  
	

    public CustomEnrollPanel(Font lightFont, Font mediumFont) {
    	 this.lightFont = lightFont;  // Save the fonts
         this.mediumFont = mediumFont;
    	
        setLayout(null);
        setBackground(Color.WHITE);
        
        
        
    }
    
    public String[][] getData() {
        String[][] data = new String[textFields.length][NUM_FIELDS];
        for (int i = 0; i < textFields.length; i++) {
            for (int j = 0; j < NUM_FIELDS; j++) {
                data[i][j] = textFields[i][j].getText();
            }
        }
        return data;
    }

        // Initialize textFields array for dynamic panels, each with 7 fields
        public void setData(String[][] data) {
            removeAll(); // Remove existing components
            int numPanels = data.length;
            textFields = new JTextField[numPanels][NUM_FIELDS];

            // Create and add panels dynamically based on data
            for (int i = 0; i < numPanels; i++) {

				JPanel panel = createPanel(i, data[i][0], data[i]); // Pass the row data
                panel.setBounds(10, i * 150, 902, 146);
                add(panel);
            }

            // Adjust preferred size to fit all panels
            setPreferredSize(new Dimension(950, numPanels * 150));
            revalidate(); // Recalculate the layout
            repaint(); // Refresh the UI
        }

    private JPanel createPanel(int panelIndex, String subject, String[] rowData) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        panel.setBorder(new MatteBorder(0, 0, 1, 0, new Color(128, 128, 128)));

        // Subject field (top)
        textFields[panelIndex][0] = createTextField(subject, mediumFont, false); // Allow input
        textFields[panelIndex][0].setBounds(30, 11, 761, 32);
        panel.add(textFields[panelIndex][0]);

        // Labels for schedule, section, etc.
        String[] labels = {"Schedcode", "Section", "Instructor", "Schedule"};
        int[] labelPositions = {30, 146, 262, 497};

        for (int i = 0; i < labels.length; i++) {
            JLabel label = new JLabel(labels[i]);
            label.setFont(lightFont);
            label.setForeground(Color.GRAY);
            label.setBounds(labelPositions[i], 60, 106, 14);
            panel.add(label);
        }

        // Text fields for schedule, section, etc.
        int[] fieldPositions = {30, 146, 262, 497, 629, 497, 629};
        for (int i = 1; i < NUM_FIELDS; i++) { // Start from 1 to skip the subject field
            textFields[panelIndex][i] = createTextField(rowData[i], lightFont, false);
            textFields[panelIndex][i].setBounds(fieldPositions[i - 1], i >= 6 ? 103 : 76, 119, 32);
            panel.add(textFields[panelIndex][i]);
        }

        return panel;
    }

    private JTextField createTextField(String text, Font font, boolean isTopField) {
        JTextField textField = new JTextField(text);
        textField.setFont(font);
        textField.setOpaque(false);
        textField.setEnabled(false);
        textField.setEditable(false);
        textField.setDisabledTextColor(Color.BLACK);
        textField.setBorder(null);
        if (isTopField) {
            textField.setFont(font.deriveFont(Font.BOLD, 14f)); // Make top field bold
        }
        return textField;
    }

  

    public static void main(String[] args) throws IOException, FontFormatException {
        JFrame frame = new JFrame("Student Grades Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(950, 600);
        
        

        // Load custom fonts (if applicable)
        File fontFile = new File("C:\\Users\\Angel\\eclipse-workspace\\AcadSync\\Poppins-SemiBold.ttf");
        Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(12f);
        
        

        CustomEnrollPanel panel = new CustomEnrollPanel(customFont, customFont);
        panel.setPreferredSize(new Dimension(250, 1200));
       
        
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);
      
        
    }
        
           
       
/////// METHOD TO POPULATE TEXTFIELDS
    public static String[][] fetchData(int studentNumber) {
    	List<String[]> data = new ArrayList<>();
        String query = """
                SELECT DISTINCT 
                ss.SchedCode, 
                ss.Description, 
                ss.Section, 
                ss.Schedule1, 
                ss.Schedule2, 
                ss.Schedule3, 
                ss.Schedule4, 
                ss.Instructor
            FROM sections_subjects ss
            JOIN enrollment_status es 
                ON ss.Section = es.Section
                AND ss.Semester = es.Semester
                AND ss.AcademicYear = es.AcademicYear
            WHERE es.Status = 'Enrolled'
              AND es.StudentNumber = ?
              AND EXISTS (
                  SELECT 1
                  FROM student_grades sg
                  WHERE sg.StudentNumber = es.StudentNumber
                    AND sg.Section = ss.Section
                    AND sg.Semester = ss.Semester
                    AND sg.AcademicYear = ss.AcademicYear
                    AND sg.SchedCode = ss.SchedCode
              );
            """;
        

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement statement = connection.prepareStatement(query)) {

                statement.setInt(1, studentNumber);
                    try (ResultSet rs = statement.executeQuery()) {
                        while (rs.next()) {
                            String[] row = new String[NUM_FIELDS];
                            row[0] = rs.getString("Description");
                            row[1] = rs.getString("SchedCode");
                            row[2] = rs.getString("Section");
                            row[3] = rs.getString("Instructor");
                            row[4] = rs.getString("Schedule1");
                            row[5] = rs.getString("Schedule2");
                            row[6] = rs.getString("Schedule3");
                            row[7] = rs.getString("Schedule4");
                            data.add(row);
                        }
                    }
                }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to fetch data from the database.", "Fetch Error", JOptionPane.ERROR_MESSAGE);
        }
     // Convert List to 2D array
        return data.toArray(new String[0][NUM_FIELDS]);
    }
}
