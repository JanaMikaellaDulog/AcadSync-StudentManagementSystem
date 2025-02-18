package acadsyncStudent;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import acadsyncFile.DatabaseCredentials;



public class StudentPortalForgotPass extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textStudentNo;
	private JTextField AcadSyncEmailtext;
	private JPasswordField NewPasswordJPasswordField;
	private JPasswordField ConfirmPassField;
	private JLabel LogIntxt;
	private final JLabel NewPassLabel = new JLabel("New Password");
	
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
					StudentPortalForgotPass frame = new StudentPortalForgotPass();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	} 

	/**
	 * Create the frame.
	 */
	public StudentPortalForgotPass() {
		UIManager.put("Button.select", new Color(232, 0, 85)); // Change the default "pressed" color
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/SMS Logo.png"));
        setTitle("Student Portal Forgot Password");
        setResizable(false);  // Disable resizing the window
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1250, 710);  // Set the frame's size and position 
        setUndecorated(false);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 128, 192));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        
        
	     // Add WindowListener to handle close event
		    addWindowListener(new WindowAdapter() {
		        @Override
		        public void windowClosing(WindowEvent e) {
		            // Call the method that shows the MainFrame and disposes the current frame
		        	 StudentPortalLogIn StudentPortal = new StudentPortalLogIn();
		        	 StudentPortal.setVisible(true);
		        	
			         dispose();
		        }
		    });
		
        
        
        // Create a JLabel to hold the icon
	    JLabel SPIcon = new JLabel("");
	    ImageIcon originalIcon1 = new ImageIcon("src/portal.png");

	    // Scale the image to fit the JLabel's size (keep the original aspect ratio)
	    Image originalImage1 = originalIcon1.getImage();
	    Image scaledImage1 = originalImage1.getScaledInstance(40, 38, Image.SCALE_SMOOTH); // Adjust width and height as necessary
	    

	     JButton EyeButton = new JButton("");
		 ImageIcon originalIcon = new ImageIcon("src/hide.png");
		 Image originalImage = originalIcon.getImage();
		 Image resizedImage = originalImage.getScaledInstance(21, 21, Image.SCALE_SMOOTH);
		 EyeButton.setIcon(new ImageIcon(resizedImage));
		 EyeButton.setBounds(889, 363, 29, 21);
		 EyeButton.setBackground(new Color(245, 245, 247)); 
		 EyeButton.setOpaque(true);  
		 EyeButton.setContentAreaFilled(true);  // Prevent the default button background rendering
		 EyeButton.setBorderPainted(false);  // Disable border painting
		 EyeButton.setFocusPainted(false);   // Disable focus painting
		 
		 
		 EyeButton.addActionListener(new ActionListener() {
			    private boolean showPassword = false; // Toggle state for password visibility

			    @Override
			    public void actionPerformed(ActionEvent e) {
			        showPassword = !showPassword; // Toggle the state

			        if (showPassword) {
			            ConfirmPassField.setEchoChar((char) 0); // Show plain text
			            ImageIcon originalIcon = new ImageIcon("src/eye.png");
						Image originalImage = originalIcon.getImage();
						Image resizedImage = originalImage.getScaledInstance(21, 21, Image.SCALE_SMOOTH);
						EyeButton.setIcon(new ImageIcon(resizedImage));
						
			        } else {
			        	ConfirmPassField.setEchoChar('•'); // Mask with '*'
			        	ImageIcon originalIcon = new ImageIcon("src/hide.png");
						Image originalImage = originalIcon.getImage();
						Image resizedImage = originalImage.getScaledInstance(21, 21, Image.SCALE_SMOOTH);
						EyeButton.setIcon(new ImageIcon(resizedImage));
			        }
			    }
			});
		 

		 
		 contentPane.add(EyeButton);
		 
	    JLabel ConfirmPassLabel = new JLabel("Confirm Password");
	    ConfirmPassLabel.setForeground(new Color(94, 94, 94));
	    ConfirmPassLabel.setFont(new Font("Poppins", Font.PLAIN, 12));
	    ConfirmPassLabel.setBounds(741, 402, 128, 36);
	    contentPane.add(ConfirmPassLabel);
	    
	    JLabel lblAcadsyncEmail = new JLabel("AcadSync² Email");
	    lblAcadsyncEmail.setForeground(new Color(94, 94, 94));
	    lblAcadsyncEmail.setFont(new Font("Poppins", Font.PLAIN, 12));
	    lblAcadsyncEmail.setBounds(427, 402, 128, 36);
	    contentPane.add(lblAcadsyncEmail);
	    
	    JLabel StudentNumLabel = new JLabel("Student Number");
	    StudentNumLabel.setForeground(new Color(94, 94, 94));
	    StudentNumLabel.setFont(new Font("Poppins", Font.PLAIN, 12));
	    StudentNumLabel.setBounds(427, 291, 128, 36);
	    contentPane.add(StudentNumLabel);
	    NewPassLabel.setBounds(748, 291, 128, 36);
	    contentPane.add(NewPassLabel);
	    NewPassLabel.setForeground(new Color(94, 94, 94));
	    NewPassLabel.setFont(new Font("Poppins", Font.PLAIN, 12));
	    SPIcon.setIcon(new ImageIcon(scaledImage1));
        
	    
	    // Set the bounds for the icon
	    SPIcon.setBounds(62, 78, 49, 54);
	    SPIcon.setFocusable(false);   // Disable focus painting
	    contentPane.add(SPIcon);
	    
	    JLabel StudentPortalLabel = new JLabel("Student Portal");
	    StudentPortalLabel.setForeground(Color.WHITE);
	    StudentPortalLabel.setFont(new Font("Arial", Font.BOLD, 37));
	    StudentPortalLabel.setBounds(116, 78, 521, 59);
	    contentPane.add(StudentPortalLabel);
	    
	    JLabel LitttleTitleLabel = new JLabel(" AcadSync²");
	    LitttleTitleLabel.setForeground(new Color(255, 255, 255));
	    LitttleTitleLabel.setFont(new Font("Arial Black", Font.BOLD, 17));
	    LitttleTitleLabel.setBounds(58, 42, 140, 27);
	    contentPane.add(LitttleTitleLabel); 
	    
	    
	    //Textfields
        
        textStudentNo = new JTextField();
        
        
        textStudentNo.setText("");
        textStudentNo.setBackground(new Color(245, 245, 247));
        textStudentNo.setForeground(new Color(128, 128, 128));
        textStudentNo.setFont(AcadSyncFontManager1.getFont("Poppins-Medium"));
        textStudentNo.setBorder(null);
        textStudentNo.setBounds(380, 247, 188, 37);
        contentPane.add(textStudentNo);
        textStudentNo.setColumns(10);
        
        AcadSyncEmailtext = new JTextField();
        AcadSyncEmailtext.setText("");
        AcadSyncEmailtext.setForeground(Color.GRAY);
        AcadSyncEmailtext.setFont(AcadSyncFontManager1.getFont("Poppins-Medium"));
        AcadSyncEmailtext.setColumns(10);
        AcadSyncEmailtext.setBorder(null);
        AcadSyncEmailtext.setBackground(new Color(245, 245, 247));
        AcadSyncEmailtext.setBounds(380, 355, 221, 37);
        contentPane.add(AcadSyncEmailtext);
        
        NewPasswordJPasswordField = new JPasswordField();
        NewPasswordJPasswordField.setText("");
        NewPasswordJPasswordField.setForeground(Color.GRAY);
        NewPasswordJPasswordField.setFont(AcadSyncFontManager1.getFont("Poppins-Medium"));
        NewPasswordJPasswordField.setColumns(10);
        NewPasswordJPasswordField.setBorder(null);
        NewPasswordJPasswordField.setBackground(new Color(245, 245, 247));
        NewPasswordJPasswordField.setBounds(700, 245, 188, 37);
        contentPane.add(NewPasswordJPasswordField);
        
        ConfirmPassField = new JPasswordField();
        ConfirmPassField.setText("");
        ConfirmPassField.setForeground(Color.GRAY);
        ConfirmPassField.setFont(AcadSyncFontManager1.getFont("Poppins-Medium"));
        ConfirmPassField.setColumns(10);
        ConfirmPassField.setBorder(null);
        ConfirmPassField.setBackground(new Color(245, 245, 247));
        ConfirmPassField.setBounds(700, 355, 188, 37);
        contentPane.add(ConfirmPassField);
        
        LogIntxt = new JLabel("Log in to your account?");
        LogIntxt.setForeground(new Color(0, 0, 0));
        LogIntxt.setFont(new Font("Arial", Font.BOLD, 14));
        LogIntxt.setBounds(328, 481, 221, 17);
        contentPane.add(LogIntxt);
        
        
        
        
        JButton RegisterBtn = new JButton("Reset");
        RegisterBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get inputs from text fields
                String studentNumber = textStudentNo.getText().trim();
                String acadSyncEmail = AcadSyncEmailtext.getText().trim();
                char[] newPassword = NewPasswordJPasswordField.getPassword();
                char[] confirmPassword = ConfirmPassField.getPassword();

                // Validate inputs
                if (studentNumber.isEmpty() || acadSyncEmail.isEmpty() || newPassword.length == 0 || confirmPassword.length == 0) {
                    JOptionPane.showMessageDialog(null, "Please fill all fields.");
                    return;
                }

                String newPasswordString = new String(newPassword);
                String confirmPasswordString = new String(confirmPassword);

                if (!newPasswordString.equals(confirmPasswordString)) {
                    JOptionPane.showMessageDialog(null, "Passwords do not match.");
                    return;
                }

                // Hash the password
                String hashedPassword = hashPassword(newPasswordString);

                // Database interaction
                String selectQuery = "SELECT * FROM student_profile WHERE StudentNumber = ? AND AcadSyncEmail = ?";
                String updateQuery = "UPDATE student_profile SET Password = ? WHERE StudentNumber = ? AND AcadSyncEmail = ?";

                try {
                    // Load MySQL driver
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                         PreparedStatement selectStmt = connection.prepareStatement(selectQuery);
                         PreparedStatement updateStmt = connection.prepareStatement(updateQuery)) {

                        // Check if the student number and email match
                        selectStmt.setString(1, studentNumber);
                        selectStmt.setString(2, acadSyncEmail);
                        ResultSet rs = selectStmt.executeQuery();

                        if (rs.next()) {
                            // Update password
                            updateStmt.setString(1, hashedPassword);
                            updateStmt.setString(2, studentNumber);
                            updateStmt.setString(3, acadSyncEmail);

                            int rowsAffected = updateStmt.executeUpdate();

                            ClearData(); // Clear the fields

                            if (rowsAffected > 0) {
                                JOptionPane.showMessageDialog(null, "Password reset successful.");
                            } else {
                                JOptionPane.showMessageDialog(null, "Error updating password.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Student Number and AcadSyncEmail do not match.");
                        }
                    }
                } catch (ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "MySQL driver not found: " + ex.getMessage(), "Driver Error", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        
        
        JButton ClearBtn = new JButton("Clear");
        ClearBtn.addActionListener(_ -> ClearData());
        	
        
        
        
        JButton LoginBtn = new JButton("Click here");
        LoginBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		StudentPortalLogIn  OpenStudentLogin = new StudentPortalLogIn();
        		OpenStudentLogin.setVisible(true);
        		
        		dispose();
        	}
        });
        
        
        
        RegisterBtn.setForeground(Color.BLACK);
        RegisterBtn.setBounds(695, 459, 115, 43);
        RegisterBtn.setOpaque(true);  
        RegisterBtn.setFont(new Font("Poppins", Font.PLAIN, 19));  
        RegisterBtn.setContentAreaFilled(false);  
        RegisterBtn.setBorderPainted(false);  
        RegisterBtn.setFocusPainted(false); 
        contentPane.add(RegisterBtn);
         
        
        
        ClearBtn.setOpaque(true);
        ClearBtn.setForeground(Color.BLACK);
        ClearBtn.setFont(new Font("Poppins", Font.BOLD, 18));
        ClearBtn.setFocusPainted(false);
        ClearBtn.setContentAreaFilled(false);
        ClearBtn.setBorderPainted(false);
        ClearBtn.setBounds(834, 459, 85, 43);
        contentPane.add(ClearBtn);

        
        
        LoginBtn.setHorizontalAlignment(SwingConstants.LEADING);
        LoginBtn.setOpaque(true);
        LoginBtn.setForeground(new Color(49, 234, 241));
        LoginBtn.setFont(new Font("Arial", Font.BOLD, 14));
        LoginBtn.setFocusPainted(false);
        LoginBtn.setContentAreaFilled(false);
        LoginBtn.setBorderPainted(false);
        LoginBtn.setBounds(486, 476, 115, 27);
        contentPane.add(LoginBtn);
        
        
        
        // Create a JLabel to hold the image (initialize before using it)
        JLabel BgFrameLabel = new JLabel();
        BgFrameLabel.setHorizontalAlignment(SwingConstants.CENTER);  // Center the image in the label
        BgFrameLabel.setBounds(0, 0 , 1236, 673);  // Set the label to take the full window size
        contentPane.add(BgFrameLabel);
        
        // Load and scale the image
        try {
            // Load the image from file
            ImageIcon originalIconPS = new ImageIcon("AcadSyncSystem/Student Portal Forgot Pass.png");
            Image StudentPortalImage = originalIconPS.getImage();

            // Scale the image to fit the content pane size
            Image scaledImage = StudentPortalImage.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);

            // Create a new ImageIcon with the scaled image
            ImageIcon scaledIcon = new ImageIcon(scaledImage);

            // Set the scaled image as the JLabel's icon
            BgFrameLabel.setIcon((scaledIcon));
            
            
            
        } catch (Exception e) {
            // Handle any error related to loading or scaling the image
            e.printStackTrace();
     }
	}
        
	
	
	
				private void ClearData() {
					textStudentNo.setText("");
			        AcadSyncEmailtext.setText("");
			        NewPasswordJPasswordField.setText("");
			        ConfirmPassField.setText("");
				     
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
				
			
			
}

