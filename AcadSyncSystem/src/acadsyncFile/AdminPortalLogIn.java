// -------------------------------------- Dependencies --------------------------------------

package acadsyncFile;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.border.LineBorder;



//--------------------------------------------------------------------------------------------


 

public class AdminPortalLogIn extends JFrame { 

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField AdminNumbertextField;
    private JPasswordField AdminPasswordtextField;
    
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
                	AdminPortalLogIn frame = new AdminPortalLogIn();
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
    public AdminPortalLogIn() {
    	UIManager.put("Button.select", new Color(189, 165, 179)); // Change the default "pressed" color
    	
        setIconImage(Toolkit.getDefaultToolkit().getImage("src/SMS Logo.png"));
        setTitle("Admin Portal Log In");
        setResizable(false);  // Disable resizing the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1250, 710);  // Set the frame's size and position 
        setUndecorated(false);
        setLocationRelativeTo(null);  // Center the frame on the screen

        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 128, 192));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
			                
			     
			 
        
			    JButton LogInButton = new JButton("Log In >");
			
			     // Add ActionListener
			    LogInButton.addActionListener(new ActionListener() {
			        public void actionPerformed(ActionEvent e) {
			            String adminNumber = AdminNumbertextField.getText().trim();
			            char[] passwordChars = AdminPasswordtextField.getPassword();
			            String password = new String(passwordChars); // Convert char array to String

			            // Hash the password before checking 
			            String hashedPassword = hashPassword(password);

			            // SQL query to check if the AdminData credentials are correct
			            String loginQuery = "SELECT AdminNumber, PasswordHash FROM AcadSync_Admin WHERE AdminNumber = ? AND PasswordHash = ?";
			            String updateQuery = "UPDATE AcadSync_Admin SET LastLog = NOW() WHERE AdminNumber = ?";

			            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			                 PreparedStatement stmt = conn.prepareStatement(loginQuery);
			                 PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {

			                // Set the parameters for the login query
			                stmt.setString(1, adminNumber);   // AdminNumber
			                stmt.setString(2, hashedPassword); // Hashed Password

			                // Execute the login query
			                ResultSet rs = stmt.executeQuery();

			                // Check if the credentials match
			                if (rs.next()) {
			                    // Valid credentials, log the user in and navigate to the dashboard
			                    AdminDashBoard1 AdminDashBoardNavigation = new AdminDashBoard1();
			                    AdminDashBoardNavigation.setVisible(true);

			                    dispose(); // Close the current login window

			                    // Update the last login time
			                    updateStmt.setString(1, adminNumber); // Set admin number for update query
			                    updateStmt.executeUpdate();
			                } else {
			                    // Invalid credentials, show an error message
			                    JOptionPane.showMessageDialog(null, "Invalid Admin Number or Password.");
			                }
			            } catch (SQLException ex) {
			                // Handle database connection issues or query execution errors
			                ex.printStackTrace();
			                JOptionPane.showMessageDialog(null, "An error occurred while logging in.");
			            }
			        }
			    });

			    

			     // Add MouseListener for hover effect
			    LogInButton.addMouseListener(new MouseAdapter() {
			         @Override
			         public void mouseEntered(MouseEvent e) {
			             // On mouse enter, change button background and text color
			             LogInButton.setBackground(new Color(232, 0, 85));  // Hover background color
			         }
			
			         @Override
			         public void mouseExited(MouseEvent e) {
			             // On mouse exit, revert button background and text color to original
			             LogInButton.setBackground(new Color(232, 0, 85));  // Original background color
			         }
			     });
			    
			    
			    
			    
			     JButton EyeButton = new JButton("");
				 ImageIcon originalIcon = new ImageIcon("src/hide.png");
				 Image originalImage = originalIcon.getImage();
				 Image resizedImage = originalImage.getScaledInstance(21, 21, Image.SCALE_SMOOTH);
				 EyeButton.setIcon(new ImageIcon(resizedImage));
				 EyeButton.setBounds(1095, 365, 29, 21);
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
					            AdminPasswordtextField.setEchoChar((char) 0); // Show plain text
					            ImageIcon originalIcon = new ImageIcon("src/eye.png");
								Image originalImage = originalIcon.getImage();
								Image resizedImage = originalImage.getScaledInstance(21, 21, Image.SCALE_SMOOTH);
								EyeButton.setIcon(new ImageIcon(resizedImage));
								
					        } else {
					        	AdminPasswordtextField.setEchoChar('*'); // Mask with '*'
					        	ImageIcon originalIcon = new ImageIcon("src/hide.png");
								Image originalImage = originalIcon.getImage();
								Image resizedImage = originalImage.getScaledInstance(21, 21, Image.SCALE_SMOOTH);
								EyeButton.setIcon(new ImageIcon(resizedImage));
					        }
					    }
					});
				 
				 JLabel AdminPasswordLabel = new JLabel("Admin Password");
				 AdminPasswordLabel.setForeground(Color.GRAY);
				 AdminPasswordLabel.setFont(new Font("Poppins", Font.PLAIN, 12));
				 AdminPasswordLabel.setBounds(955, 415, 128, 13);
				 contentPane.add(AdminPasswordLabel);
				 
				 JLabel AdminNumberLabel = new JLabel("Admin Number");
				 AdminNumberLabel.setBounds(958, 305, 128, 13);
				 AdminNumberLabel.setFont(AcadSyncFontManager.getFont("Poppins-Bold3"));
				 AdminNumberLabel.setForeground(Color.GRAY);
				 contentPane.add(AdminNumberLabel);
		
				 
				 
				 contentPane.add(EyeButton);

			    
			    
			    
			    
			    // Set initial button properties
			    LogInButton.setForeground(new Color(0, 0, 0));  // Initial text color
			    LogInButton.setBackground(new Color(0, 0, 0));  // Initial background color
			    LogInButton.setBorder(null);  // No border
			    LogInButton.setFont(new Font("Holtwood One SC", Font.PLAIN, 17));  // Font settings
			
			     // Set button to be opaque and allow background color change
			    LogInButton.setOpaque(true);  // Make sure the button is opaque
			    LogInButton.setContentAreaFilled(false);  // Prevent the default button background rendering
			    LogInButton.setBorderPainted(false);  // Disable border painting
			    LogInButton.setFocusPainted(false);   // Disable focus painting
			    LogInButton.setBounds(1018, 452, 136, 47);  // Button size and position
			
			    contentPane.add(LogInButton);
			    
			    
			    JLabel Instruction1Label3 = new JLabel("<html>" +
			    		"<div style='padding-bottom:10px;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  Fill out the provided form for assistance.</div></html>");
			    Instruction1Label3.setForeground(Color.WHITE);
			    Instruction1Label3.setFont(new Font("Arial", Font.BOLD, 16));
			    Instruction1Label3.setBounds(88, 525, 368, 45);
			    
			    contentPane.add(Instruction1Label3);
			    
			    
			    JLabel Instruction1Label2 = new JLabel("<html>" +
			    		"<div style='padding-bottom:10px;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  Ensure the account is activated and that your admin ID and password</div>" +
			    		"<div style='padding-bottom:10px;'>match the records. If you've forgotten your password, use the recovery link.</div>" +
			    		"</html>");
			    Instruction1Label2.setForeground(new Color(255, 255, 255));
			    Instruction1Label2.setFont(new Font("Arial", Font.BOLD, 16));
			    Instruction1Label2.setBounds(88, 420, 645, 70);
			    
			    contentPane.add(Instruction1Label2);
			    
			    
			    JLabel Instruction1Label = new JLabel("<html>" +
			    	    "<div style='padding-bottom:10px;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  Admins must provide required information to register. Ensure a valid email</div>" +
			    	    "<div style='padding-bottom:10px;'>address and correct formatting. Only authorized staff can register.</div>" +
			    	    "</html>");
			    Instruction1Label.setFont(new Font("Arial", Font.BOLD, 16));
			    Instruction1Label.setForeground(new Color(255, 255, 255));
			    Instruction1Label.setBounds(88, 312, 645, 70); // Adjust the bounds to fit the text
			    
			    contentPane.add(Instruction1Label);
			     
			    
			    JLabel ForAdminConcernsLabel = new JLabel("   • For Admin Portal Concerns");
			    ForAdminConcernsLabel.setForeground(Color.WHITE);
			    ForAdminConcernsLabel.setFont(new Font("Arial Black", Font.BOLD, 17));
			    ForAdminConcernsLabel.setBounds(78, 490, 312, 27);
			    contentPane.add(ForAdminConcernsLabel);
			    
			    JLabel LogInIssuesLabel = new JLabel("   • Log In Issues");
			    LogInIssuesLabel.setForeground(Color.WHITE);
			    LogInIssuesLabel.setFont(new Font("Arial Black", Font.BOLD, 17));
			    LogInIssuesLabel.setBounds(78, 380, 181, 27);
			    contentPane.add(LogInIssuesLabel);
			    
			    JLabel CreatingAccountLabel = new JLabel("   • Creating Account");
			    CreatingAccountLabel.setFont(new Font("Arial Black", Font.BOLD, 17));
			    CreatingAccountLabel.setForeground(new Color(255, 255, 255));
			    CreatingAccountLabel.setBounds(78, 275, 214, 27);
			    contentPane.add(CreatingAccountLabel);
			    
			    JLabel TitleLabel = new JLabel(" AcadSync²");
			    TitleLabel.setHorizontalAlignment(SwingConstants.LEFT);
			    TitleLabel.setForeground(new Color(36, 248, 255));
			    TitleLabel.setFont(new Font("Arial", Font.BOLD, 52));
			    TitleLabel.setBounds(67, 176, 521, 60);
			    contentPane.add(TitleLabel);
			    
			    // Create a JLabel to hold the icon
			    JLabel SPIcon = new JLabel("");
			    ImageIcon originalIcon1 = new ImageIcon("src/security.png");

			    // Scale the image to fit the JLabel's size (keep the original aspect ratio)
			    Image originalImage1 = originalIcon1.getImage();
			    Image scaledImage1 = originalImage1.getScaledInstance(67, 60, Image.SCALE_SMOOTH); // Adjust width and height as necessary
			    SPIcon.setIcon(new ImageIcon(scaledImage1));

			    // Set the bounds for the icon
			    SPIcon.setBounds(76, 106, 67, 60);
			    SPIcon.setFocusable(false);   // Disable focus painting
			    contentPane.add(SPIcon);
			    
			    JLabel StudentPortalLabel = new JLabel("Admin Portal");
			    StudentPortalLabel.setForeground(Color.WHITE);
			    StudentPortalLabel.setFont(new Font("Arial", Font.BOLD, 52));
			    StudentPortalLabel.setBounds(151, 107, 521, 70);
			    contentPane.add(StudentPortalLabel);
			    
			    JLabel LitttleTitleLabel = new JLabel(" AcadSync²");
			    LitttleTitleLabel.setForeground(new Color(255, 255, 255));
			    LitttleTitleLabel.setFont(new Font("Arial Black", Font.BOLD, 17));
			    LitttleTitleLabel.setBounds(78, 41, 120, 27);
			    contentPane.add(LitttleTitleLabel); 
                
                AdminPasswordtextField = new JPasswordField();
                AdminPasswordtextField.setHorizontalAlignment(SwingConstants.LEFT);
                AdminPasswordtextField.setForeground(Color.GRAY);
                AdminPasswordtextField.setFont(AcadSyncFontManager.getFont("Poppins-Medium2"));
                AdminPasswordtextField.setColumns(10);
                AdminPasswordtextField.setBorder(new LineBorder(new Color(245, 245, 247), 0));
                AdminPasswordtextField.setBackground(new Color(245, 245, 247));
                AdminPasswordtextField.setBounds(902, 365, 181, 19);
                AdminPasswordtextField.setEchoChar('*');
                contentPane.add(AdminPasswordtextField);
                
                AdminNumbertextField = new JTextField();
                AdminNumbertextField.setBackground(new Color(245, 245, 247));
                AdminNumbertextField.setColumns(10);
                AdminNumbertextField.setForeground(new Color(128, 128, 128));
                AdminNumbertextField.setFont(AcadSyncFontManager.getFont("Poppins-Medium2"));
                AdminNumbertextField.setText("");
                AdminNumbertextField.setBorder(new LineBorder(new Color(245, 245, 247), 0));  // Set a custom border with a specific color and thickness
                AdminNumbertextField.setBounds(902, 257, 181, 19);
                contentPane.add(AdminNumbertextField);
         
                // Add text labels over the image
                JLabel LogInLabel = new JLabel("Log Into your account");
                LogInLabel.setFont(new Font("Holtwood One SC", Font.PLAIN, 17)); // Larger font for visibility
                LogInLabel.setForeground(new Color(0, 0, 0));  // White text
                LogInLabel.setBounds(870, 160, 265, 45);  // Adjust position to be above image
                contentPane.add(LogInLabel);

		        // Create a JLabel to hold the image (initialize before using it)
		        JLabel BgFrameLabel = new JLabel();
		        BgFrameLabel.setHorizontalAlignment(SwingConstants.CENTER);  // Center the image in the label
		        BgFrameLabel.setBounds(0, 0 , 1236, 673);  // Set the label to take the full window size
		        contentPane.add(BgFrameLabel);
		        
		        
		        // Load and scale the image
		        try {
		            // Load the image from file
		            ImageIcon originalIconPS = new ImageIcon("src/Admin Portal Log In.png");
		            Image StudentPortalImage = originalIconPS.getImage();
		
		            // Scale the image to fit the content pane size
		            Image scaledImage = StudentPortalImage.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
		
		            // Create a new ImageIcon with the scaled image
		            ImageIcon scaledIcon = new ImageIcon(scaledImage);
		
		            // Set the scaled image as the JLabel's icon
		            BgFrameLabel.setIcon(scaledIcon);
		            
		        } catch (Exception e) {
		            // Handle any error related to loading or scaling the image
		            e.printStackTrace();
        }
    }



    
    
    

  //----------------------------------------------------------------------------Method------------------------------------------------------------------------


    
    
    
					  /// Method to hash the password
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




//----------------------------------------------------------------------------------------------------------------------------------------------------------	        

