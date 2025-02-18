// -------------------------------------- Dependencies --------------------------------------

package acadsyncFile;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
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




public class AdminStudentGrades1 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final JLabel ExtraLabel = new JLabel("│");
	private JTable TableStudentGrades;
	private DefaultTableModel tableModelGrades;

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
	        	AdminStudentGrades1 frame = new AdminStudentGrades1();
	            frame.setVisible(true);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    });
	}

	       
	/**
	 * Create the frame.
	 */
	public AdminStudentGrades1() {
		UIManager.put("Button.select", new Color(69, 70, 73)); // Change the default "pressed" color
		
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/SMS Logo.png"));
        setTitle("Admin Student Grades");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1250, 710);  // Set the frame's size and position 
        setUndecorated(false);
        setLocationRelativeTo(null);  // Center the frame on the screen

        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(null);
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        
       
        
        
		         
		        
		   
        
		        CustomButtonNavigation StudentGradesButton = new CustomButtonNavigation("Student Grades", 0);
		        
		        // Add ActionListener
		        StudentGradesButton.addActionListener(new ActionListener() {
			         public void actionPerformed(ActionEvent e) {
   
			             // Action code here (e.g., open another window or perform login)
			         }
			     });
		        
		        
		        
		        CustomButtonNavigation StudentStatusButton = new CustomButtonNavigation("Student Status", 0);
		        
		        // Add ActionListener
		        StudentStatusButton.addActionListener(new ActionListener() {
			         public void actionPerformed(ActionEvent e) {
		
			             // Action code here (e.g., open another window or perform login)
			        	 AdminStudentStatus1 AdminStudentStatusNavigation = new AdminStudentStatus1();
			        	 AdminStudentStatusNavigation.setVisible(true);
			        	 dispose(); // Close the current window frame
			         }
			     });
		        
		        
		       	         
		        CustomButtonNavigation SectionSubjectButton = new  CustomButtonNavigation("Section Subject", 0);
		        
		        // Add ActionListener
		        SectionSubjectButton.addActionListener(new ActionListener() {
			         public void actionPerformed(ActionEvent e) {
			        
			             // Action code here (e.g., open another window or perform login)
			        	 AdminSectionSubject1 AdminSectionSubjectNavigation = new AdminSectionSubject1();
			        	 AdminSectionSubjectNavigation.setVisible(true);
			        	 
			        	 dispose(); // Close the current window frame
			         }
			     });
		            
		            
		            
		            
		        CustomButtonNavigation StudentProfileButton = new CustomButtonNavigation("Student Profile",0);
		            
		            
		        // Add ActionListener
		        StudentProfileButton.addActionListener(new ActionListener() {
			         public void actionPerformed(ActionEvent e) {
			       
			             // Action code here (e.g., open another window or perform login)
			        	 AdminStudentProfile1 AdminStudentProfileNavigation = new AdminStudentProfile1();
			        	 AdminStudentProfileNavigation.setVisible(true);
				        	 
				         dispose(); // Close the current window frame
			         }
			     });
		            
		      
		            
		        CustomButtonNavigation DashBoardButton = new CustomButtonNavigation("Dashboard", 0);
		      
		        // Add ActionListener
		        DashBoardButton.addActionListener(new ActionListener() {
			         public void actionPerformed(ActionEvent e) {
			        	 
			        	 // Action code here (e.g., open another window or perform login)  
			        	 AdminDashBoard1 AdminDashBoardNavigation = new AdminDashBoard1();
			        	 AdminDashBoardNavigation.setVisible(true);
			        	 
			        	 dispose(); // Close the current window frame
			         }
			     });
		            		        
		            		        	        
		            		        
		         CustomButtonNavigation NavigationButton = new CustomButtonNavigation("NAVIGATION ", 0);
		            		        
		        // Add ActionListener
		        NavigationButton.addActionListener(new ActionListener() {
		        	public void actionPerformed(ActionEvent e) {
		            		         	     
		               // Action code here (e.g., open another window or perform login)
		        		AdminStudentGrades AdminStudentGradesNoNavigation = new AdminStudentGrades();
			        	AdminStudentGradesNoNavigation.setVisible(true);
			        	 
			        	dispose(); // Close the current window frame
		              }
			     });
		        
		      
		        NavigationButton.setIcon(new ImageIcon("src/back.png"));
		        NavigationButton.setForeground(new Color(255, 102, 54));
		        NavigationButton.setFont(AcadSyncFontManager.getFont("Poppins-Medium"));
		        NavigationButton.setBounds(0, 90, 270, 60);
		        
		        // Align text to the left and icon to the right
		        NavigationButton.setHorizontalTextPosition(SwingConstants.LEFT); // Text on the left
		        NavigationButton.setHorizontalAlignment(SwingConstants.CENTER);   // Align content to the left
		        NavigationButton.setIconTextGap(70);  // Adjust gap between text and icon
		        
		             
		        contentPane.add(NavigationButton);
		            		        
		             // Add MouseListener for hover effect
     		        //DashBoardButton.addMouseListener(new MouseAdapter() {
     		                 //@Override
     		                 //public void mouseEntered(MouseEvent e) {
     		                     // On mouse enter, change button background and text color
     		                	 //DashBoardButton.setBackground(new Color(180, 180, 180));  // Hover background color
     		                 //}
     		        
     		                 //@Override
     		                 //public void mouseExited(MouseEvent e) {
     		                     // On mouse exit, revert button background and text color to original
     		                	//DashBoardButton.setBackground(new Color(217, 217, 217));  // Original background color
     		                 //}
     		               
     		             //});
     		        
		        DashBoardButton.setIcon(new ImageIcon("src/dashboard.png"));
     		    DashBoardButton.setFont(AcadSyncFontManager.getFont("Poppins-Medium"));
     		    DashBoardButton.setBackground(new Color(217, 217, 217));
     		    DashBoardButton.setBounds(-77, 153, 347, 60);
     		    DashBoardButton.setMargin(new Insets(0, 10, 0, 0)); // Insets(top, left, bottom, right)
     		    DashBoardButton.setIconTextGap(20); 
     		        

		        contentPane.add(DashBoardButton);
		            
		                 // Add MouseListener for hover effect
		            //StudentProfileButton.addMouseListener(new MouseAdapter() {
		                     //@Override
		                    //public void mouseEntered(MouseEvent e) {
		                         // On mouse enter, change button background and text color
		                    	 //StudentProfileButton.setBackground(new Color(180, 180, 180));  // Hover background color
		                     //}
		            
		                     //@Override
		                     //public void mouseExited(MouseEvent e) {
		                         // On mouse exit, revert button background and text color to original
		                    	 //StudentProfileButton.setBackground(new Color(217, 217, 217));  // Original background color
		                     //}
		                 //});
		            

		        StudentProfileButton.setMargin(new Insets(0, 10, 0, 0));
	            StudentProfileButton.setIconTextGap(21);
	            StudentProfileButton.setIcon(new ImageIcon("src/avatar.png"));
	            StudentProfileButton.setForeground(Color.BLACK);
	            StudentProfileButton.setFont(AcadSyncFontManager.getFont("Poppins-Medium"));
	            StudentProfileButton.setBackground(new Color(217, 217, 217));
	            StudentProfileButton.setBounds(-45, 212, 315, 60);
	            
	            contentPane.add(StudentProfileButton);
	            
	            
		                 // Add MouseListener for hover effect
		            //SectionSubjectButton.addMouseListener(new MouseAdapter() {
		                     //@Override
		                     // void mouseEntered(MouseEvent e) {
		                         // On mouse enter, change button background and text color
		                    	 //SectionSubjectButton.setBackground(new Color(180, 180, 180));  // Hover background color
		                     //}
		            
		                     //@Override
		                     //public void mouseExited(MouseEvent e) {
		                         // On mouse exit, revert button background and text color to original
		                    	 //SectionSubjectButton.setBackground(new Color(217, 217, 217));  // Original background color
		                     //}
		                 //});
		            
		       
	            SectionSubjectButton.setMargin(new Insets(0, 10, 0, 0));
	            SectionSubjectButton.setIconTextGap(21);
	            SectionSubjectButton.setIcon(new ImageIcon("src/books-stack-of-three.png"));
	            SectionSubjectButton.setForeground(Color.BLACK);
	            SectionSubjectButton.setFont(AcadSyncFontManager.getFont("Poppins-Medium"));
	            SectionSubjectButton.setBounds(-34, 271, 304, 60);
	            
	            contentPane.add(SectionSubjectButton);
	            
		        
		        // Add MouseListener for hover effect
		       //StudentStatusButton.addMouseListener(new MouseAdapter() {
		                 //@Override
		                 // void mouseEntered(MouseEvent e) {
		                     // On mouse enter, change button background and text color
		                	 //StudentStatusButton.setBackground(new Color(180, 180, 180));  // Hover background color
		                 //}
		        
		                 //@Override
		                 //public void mouseExited(MouseEvent e) {
		                     // On mouse exit, revert button background and text color to original
		                	 //StudentStatusButton.setBackground(new Color(217, 217, 217));  // Original background color
		                 //}
		             //});
		        
		        
		        StudentStatusButton.setMargin(new Insets(0, 10, 0, 0));
		        StudentStatusButton.setIconTextGap(21);
		        StudentStatusButton.setIcon(new ImageIcon("src/clipboard.png"));
		        StudentStatusButton.setForeground(Color.BLACK);
		        StudentStatusButton.setFont(AcadSyncFontManager.getFont("Poppins-Medium"));
		        StudentStatusButton.setBounds(-41, 330, 311, 60);
		        
		         contentPane.add(StudentStatusButton);
		        
		             // Add MouseListener for hover effect
		        //StudentGradesButton.addMouseListener(new MouseAdapter() {
		                 //@Override
		                 //public void mouseEntered(MouseEvent e) {
		                     // On mouse enter, change button background and text color
		                	 //StudentGradesButton.setBackground(new Color(180, 180, 180));  // Hover background color
		                 //}
		        
		                 //@Override
		                 //public void mouseExited(MouseEvent e) {
		                     // On mouse exit, revert button background and text color to original
		                	 //StudentGradesButton.setBackground(new Color(217, 217, 217));  // Original background color
		                 //}
		             //});
		    
		        StudentGradesButton.setMargin(new Insets(0, 10, 0, 0));
		        StudentGradesButton.setIconTextGap(20);
		        StudentGradesButton.setIcon(new ImageIcon("src/best.png"));
		        StudentGradesButton.setForeground(Color.BLACK);
		        StudentGradesButton.setFont(AcadSyncFontManager.getFont("Poppins-Medium"));
		        StudentGradesButton.setBounds(-31, 388, 301, 60);
		        
		        contentPane.add(StudentGradesButton);
        
		        
		        
		        
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
			        	 AdminStudentGrades AdminStudentGradesNoNavigation = new AdminStudentGrades();
			        	 AdminStudentGradesNoNavigation.setVisible(true);
			        	 
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
		                
		                
		                
		        
		        
		        // This is the picture background of all our navigation button
		        JLabel NavigationPicLabel = new JLabel("");
				NavigationPicLabel.setIcon(new ImageIcon("src/Group 89.png"));
				NavigationPicLabel.setBounds(0, 73, 270, 767);   
				contentPane.add(NavigationPicLabel);
				
				// Panel that has a round gray border and white background
		        CustomRoundPanel panel_1 = new CustomRoundPanel(30);
		        panel_1.setBorder(new EmptyBorder(0, 0, 0, 0));
		        panel_1.setBounds(279, 98, 949, 565);
		        contentPane.add(panel_1);
		        panel_1.setLayout(null);
		        
		        
		        
		        CustomButtonGrades EditGradesButton = new CustomButtonGrades("Edit Grades", 15);

		        
		        
		        EditGradesButton.addActionListener(new ActionListener() {
		        	public void actionPerformed(ActionEvent e) {
		        		// Action code here (e.g., open another window or perform login)
		        		AdminEditStudentGrades1 AdminStudentGradesNavigation = new AdminEditStudentGrades1();
			        	AdminStudentGradesNavigation.setVisible(true);
			        	 
			        	dispose(); // Close the current window frame
		        	}
		        });
		        
		        
		        
		        //EditCalendarButton.setCornerRadius(15);
		        EditGradesButton.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold"));
		        EditGradesButton.setBounds(796, 10, 140, 40);
		        
		        panel_1.add(EditGradesButton);
		        
		    
		  
		        
		      	
				

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
		        if (AcadSyncFontManager.getFont("Poppins-SemiBold") != null) {
		            TableStudentGrades.getTableHeader().setFont(AcadSyncFontManager.getFont("Poppins-SemiBold").deriveFont(13f)); // Larger font for header
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
		        scrollPane.setBounds(12, 98, 924, 424);
		        panel_1.add(scrollPane);
		        scrollPane.setBorder(new LineBorder(Color.BLACK, 2));
		        
		        
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
		                tableModelGrades.setRowCount(0); // Clear the table
		
		                // Add all rows back to the table model from the full data list
		                for (Object[] row : AllTableData) { // `AllTableData` contains the full data set
		              	  tableModelGrades.addRow(row);
		                }
		            }
		        });	  
		        
		        
		        
		        // Load data from database
		        LoadDataFromDatabase(AcadSyncFontManager.getFont("Poppins-SemiBold"), AcadSyncFontManager.getFont("Poppins-Bold2"), AcadSyncFontManager.getFont("Poppins-SemiBold1"), AcadSyncFontManager.getFont("Arvo-Bold"));
		        
		        
		        
		        
		        
		          // Add mouse listener to detect row selection
		          TableStudentGrades.addMouseListener(new MouseAdapter() {
		              @Override
		              public void mouseClicked(MouseEvent e) {
		                  int selectedRow = TableStudentGrades.getSelectedRow();
		
		                  if (selectedRow >= 0) {
		                      Object gradeID = tableModelGrades.getValueAt(selectedRow, 0);
		                      Object studentNumber = tableModelGrades.getValueAt(selectedRow, 1);
		                      Object academicYear = tableModelGrades.getValueAt(selectedRow, 2);
		                      Object semester = tableModelGrades.getValueAt(selectedRow, 3);
		                      Object section = tableModelGrades.getValueAt(selectedRow, 4);
		                      Object schedCode = tableModelGrades.getValueAt(selectedRow, 5);
		                      Object subjectCode = tableModelGrades.getValueAt(selectedRow, 6);
		                      Object description = tableModelGrades.getValueAt(selectedRow, 7);
		                      Object schedule1 = tableModelGrades.getValueAt(selectedRow, 8);
		                      Object schedule2 = tableModelGrades.getValueAt(selectedRow, 9);
		                      Object schedule3 = tableModelGrades.getValueAt(selectedRow, 10);
		                      Object schedule4 = tableModelGrades.getValueAt(selectedRow, 11);
		                      Object instructor = tableModelGrades.getValueAt(selectedRow, 12);
		                      Object grade = tableModelGrades.getValueAt(selectedRow, 13);
		                      Object unit = tableModelGrades.getValueAt(selectedRow, 14);
		                      Object creditUnit = tableModelGrades.getValueAt(selectedRow, 15);
		                      Object completion = tableModelGrades.getValueAt(selectedRow, 16);
		                      
		
		                      // Construct the message string
		                      String message = "GradeID: " + gradeID + "\n" +
		                    		           "Student Number: " + studentNumber + "\n" +
		                                       "Academic Year: " + academicYear + "\n" +
		                                       "Semester: " + semester + "\n" +
		                                       "Section: " + section + "\n" +
		                                       "Schedule Code: " + schedCode + "\n" +
		                                       "Subject Code: " + subjectCode + "\n" +
		                                       "Description: " + description + "\n" +
		                                       "Schedule 1: " + schedule1 + "\n" +
		                                       "Schedule 2: " + schedule2 + "\n" +
		                                       "Schedule 3: " + schedule3 + "\n" +
		                                       "Schedule 4: " + schedule4 + "\n" +
		                                       "Instructor: " + instructor + "\n" +
		                                       "Grade: " + grade + "\n" +
		                                       "Unit: " + unit + "\n" +
		                                       "Credit Unit: " + creditUnit + "\n" +
		                                       "Completion: " + completion;
		                      				   
		
		                      // Show the message dialog
		                      JOptionPane.showMessageDialog(AdminStudentGrades1.this,
		                              message,
		                              "Student Grades",
		                              JOptionPane.INFORMATION_MESSAGE);
		                  }
		              }
		          });
		     
		    	  
		}


		




//----------------------------------------------------------------------------Method------------------------------------------------------------------------





					
						//Method for Search a data inside our JtextField 
						private void SearchData(String searchTerm) {
						   tableModelGrades.setRowCount(0); // Clear the table
					
						    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
						        // Modify query to exclude "Last Log In" column
						    	String query = "SELECT * FROM Student_Grades WHERE " +
					                       "StudentNumber LIKE ? OR AcademicYear LIKE ? OR Semester LIKE ? OR " +
					                       "Section LIKE ? OR SchedCode LIKE ? OR SubjectCode LIKE ? OR Description LIKE ? OR " +
					                       "Schedule1 LIKE ? OR Schedule2 LIKE ? OR Schedule3 LIKE ? OR Schedule4 LIKE ? OR " +
					                       "Instructor LIKE ? OR Grade LIKE ? OR Unit LIKE ? OR CreditUnit LIKE ? OR Completion LIKE ?";
						                     
						        try (PreparedStatement searchStmt = conn.prepareStatement(query)) {
						            // Set the search term for all columns
						            for (int i = 1; i <= 16; i++) { 
						                searchStmt.setString(i, "%" + searchTerm + "%");
						            }
					
						            try (ResultSet rs = searchStmt.executeQuery()) {
						                while (rs.next()) {
						                    tableModelGrades.addRow(new Object[]{
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



				
				
				
				
		        

        
  
        
		
	}
}



