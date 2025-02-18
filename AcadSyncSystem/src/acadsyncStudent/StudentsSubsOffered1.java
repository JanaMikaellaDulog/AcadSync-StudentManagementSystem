package acadsyncStudent;

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
import java.sql.SQLException;
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
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import acadsyncFile.AcadSyncFontManager;
import acadsyncFile.DatabaseCredentials;

public class StudentsSubsOffered1 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable TableStudentSubs;
    private DefaultTableModel tableModelSubs;
	private final JLabel ExtraLabel = new JLabel("│");
	private JTextField SearchtextField;
	
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
					StudentsSubsOffered1 frame = new StudentsSubsOffered1();
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
	public StudentsSubsOffered1() {
		UIManager.put("Button.select", new Color(69, 70, 73));
		setResizable(false);
		setTitle("Subjects Offered");
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/SMS Logo.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1250, 710);
		setLocationRelativeTo(null);  // Center the frame on the screen

		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
	
			
			CustomButtonNavigation NavigationButton = new CustomButtonNavigation("NAVIGATION ", 0);
			
	        NavigationButton.addActionListener(new ActionListener() {
	              public void actionPerformed(ActionEvent e) {	         	     
	            	  StudentsSubsOffered StudendSubsOfferedNoNav = new StudentsSubsOffered();
	            	  StudendSubsOfferedNoNav .setVisible(true);
		                 
		                 dispose();
	              }
		     });
	        
	        
	        
	        
	        CustomButtonNavigation DashBoardButton = new CustomButtonNavigation("Dashboard", 0);
            // Add ActionListener
	         DashBoardButton.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent e) {
		        	 StudentDashboard StudentDashboardOpen = new StudentDashboard();
		        	 StudentDashboardOpen.setVisible(true);
		        	 
		        	 dispose();
		         }
		     });
	         
	         CustomButtonNavigation StudentProfileButton = new CustomButtonNavigation("Profile",0);
	        
	            // Add ActionListener
	         StudentProfileButton.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent e) {
		        	 StudentProfile1 StudentProfileOpen = new StudentProfile1();
		        	 StudentProfileOpen.setVisible(true);
		        	 
		        	 dispose();
		        
		             // Action code here (e.g., open another window or perform login)
		         }
		     });
	         
	         CustomButtonNavigation SectionSubjectButton = new  CustomButtonNavigation("Enrolled Subject", 0);
		        
		        // Add ActionListener
		        SectionSubjectButton.addActionListener(new ActionListener() {
			         public void actionPerformed(ActionEvent e) {
			        	 StudentEnrolledSubs1 StudentEnrolledSubsOpen = new StudentEnrolledSubs1();
			        	 StudentEnrolledSubsOpen.setVisible(true);
			        	 
			        	 dispose();
			             // Action code here (e.g., open another window or perform login)
			         }
			     });
	        
		      CustomButtonNavigation StudentStatusButton = new CustomButtonNavigation("Subjects Offered", 0);
			      
			      StudentStatusButton.addActionListener(new ActionListener() {
				         public void actionPerformed(ActionEvent e) {
				        	 
				         }
				     });
		      
		      CustomButtonNavigation StudentGradesButton = new CustomButtonNavigation("Grades", 0);	      

			      StudentGradesButton.addActionListener(new ActionListener() {
				         public void actionPerformed(ActionEvent e) {
				        	 StudentGrades1 StudentGradesOpen = new StudentGrades1();
				        	 StudentGradesOpen.setVisible(true);
				        	 
				        	 dispose();
				        
				             // Action code here (e.g., open another window or perform login)
				         }
				     });
	         
	        
			        NavigationButton.setIcon(new ImageIcon("src/back.png"));
			        NavigationButton.setBounds(0, 92, 270, 60);
			        NavigationButton.setForeground(new Color(255, 102, 54));
			        NavigationButton.setFont(AcadSyncFontManager.getFont("Poppins-Medium"));
			        // Align text to the left and icon to the right
			        NavigationButton.setHorizontalTextPosition(SwingConstants.LEFT); // Text on the left
			        NavigationButton.setHorizontalAlignment(SwingConstants.CENTER);   // Align content to the left
			        NavigationButton.setIconTextGap(70);  // Adjust gap between text and icon
			        contentPane.add(NavigationButton);
			        
			        DashBoardButton.setIcon(new ImageIcon("src/dashboard.png"));
			        DashBoardButton.setFont(AcadSyncFontManager.getFont("Poppins-Medium"));
	     		    DashBoardButton.setBackground(new Color(217, 217, 217));
	     		    DashBoardButton.setBounds(-77, 153, 347, 60);
	     		    DashBoardButton.setMargin(new Insets(0, 10, 0, 0)); // Insets(top, left, bottom, right)
	     		    DashBoardButton.setIconTextGap(20);
	     		    contentPane.add(DashBoardButton);
			       
			        
	     		    StudentProfileButton.setMargin(new Insets(0, 10, 0, 0));
		            StudentProfileButton.setIconTextGap(21);
		            StudentProfileButton.setIcon(new ImageIcon("src/avatar.png"));
		            StudentProfileButton.setForeground(Color.BLACK);
		            StudentProfileButton.setFont(AcadSyncFontManager.getFont("Poppins-Medium"));
		            StudentProfileButton.setBackground(new Color(217, 217, 217));
		            StudentProfileButton.setBounds(-117, 212, 387, 60);
			        contentPane.add(StudentProfileButton);
			        
			        
					SectionSubjectButton.setIconTextGap(21);
					SectionSubjectButton.setMargin(new Insets(0, 10, 0, 0)); // Insets(top, left, bottom, right)
					SectionSubjectButton.setIcon(new ImageIcon("src/books-stack-of-three.png"));
					SectionSubjectButton.setForeground(Color.BLACK);
					SectionSubjectButton.setFont(AcadSyncFontManager.getFont("Poppins-Medium"));
					SectionSubjectButton.setBounds(-26, 271, 296, 60);
					contentPane.add(SectionSubjectButton);
					
					
					StudentStatusButton.setIconTextGap(21);
					StudentStatusButton.setIcon(new ImageIcon("src/book.png"));
					StudentStatusButton.setMargin(new Insets(0, 10, 0, 0)); // Insets(top, left, bottom, right)
					StudentStatusButton.setForeground(Color.BLACK);
					StudentStatusButton.setFont(AcadSyncFontManager.getFont("Poppins-Medium"));
					StudentStatusButton.setBounds(-17, 329, 286, 60);
					contentPane.add(StudentStatusButton);
					
					
					StudentGradesButton.setIconTextGap(21);
					StudentGradesButton.setIcon(new ImageIcon("src/best.png"));
					StudentGradesButton.setMargin(new Insets(0, 10, 0, 0)); // Insets(top, left, bottom, right)
					StudentGradesButton.setForeground(Color.BLACK);
					StudentGradesButton.setFont(AcadSyncFontManager.getFont("Poppins-Medium"));
					StudentGradesButton.setBounds(-99, 388, 369, 60);
					contentPane.add(StudentGradesButton);
	        
	        
	        
			
			JPanel toppanel = new JPanel();
			toppanel.setBackground(new Color(0, 0, 0));
			toppanel.setBounds(0, 0, 1236, 92);
			contentPane.add(toppanel);
			toppanel.setLayout(null);
			
			
				JButton menubutton = new JButton("");
				menubutton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						StudentsSubsOffered StudendSubsOfferedNoNav = new StudentsSubsOffered();
		            	  StudendSubsOfferedNoNav .setVisible(true);
			                 
			                 dispose();
					}
				});
				
				   // Add MouseListener for hover effect
		        menubutton.addMouseListener(new MouseAdapter() {
		                 @Override
		                 public void mouseEntered(MouseEvent e) {
		                     // On mouse enter, change button background and text color
		                	 menubutton.setBackground(new Color(27, 27, 27));  // Hover background color
		                 }
		                 
		                 @Override
		                 public void mouseExited(MouseEvent e) {
		                     // On mouse exit, revert button background and text color to original
		                	 menubutton.setBackground(new Color(0, 0, 0));  // Original background color
		                 }
		             });
		        
		        
		        JButton logoutbutton = new JButton("");
				logoutbutton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						 StudentPortalLogIn StudentLogIn = new StudentPortalLogIn();
						 StudentLogIn.setVisible(true);
				        	 
				         dispose(); // Close the current window frame
					}
				});
				
				// Add MouseListener for hover effect
		        logoutbutton.addMouseListener(new MouseAdapter() {
		                 @Override
		                 public void mouseEntered(MouseEvent e) {
		                     // On mouse enter, change button background and text color
		                	 logoutbutton.setBackground(new Color(27, 27, 27));  // Hover background color
		                 }
		                 
		                 @Override
		                 public void mouseExited(MouseEvent e) {
		                     // On mouse exit, revert button background and text color to original
		                	 logoutbutton.setBackground(new Color(0, 0, 0));  // Original background color
		                 }
		             });


				
		        menubutton.setIcon(new ImageIcon("src/list (1).png"));
   				menubutton.setFocusPainted(false);
   				menubutton.setBorderPainted(false);
   				menubutton.setBackground(new Color(0, 0, 0));
   				menubutton.setBorder(null);
   				menubutton.setBounds(0, 0, 89, 90);
   				toppanel.add(menubutton);
 
   				
   				logoutbutton.setBackground(new Color(0, 0, 0));
   				logoutbutton.setFocusPainted(false);
   				logoutbutton.setBorderPainted(false);
   				logoutbutton.setOpaque(true);
   				logoutbutton.setIcon(new ImageIcon("src/turn-off.png"));
   				logoutbutton.setBounds(1167, 0, 70, 90);
   				toppanel.add(logoutbutton);
		   				
				
				
				JLabel logoIcon = new JLabel("New label");
				logoIcon.setHorizontalAlignment(SwingConstants.CENTER);
				logoIcon.setIcon(new ImageIcon("src/SMS Logo Small.jpg"));
				logoIcon.setBounds(99, 0, 77, 88);
				toppanel.add(logoIcon);
				
				JLabel TopStudentLabel = new JLabel("STUDENT");
				TopStudentLabel.setFont(AcadSyncFontManager.getFont("Poppins-Bold"));
				TopStudentLabel.setForeground(new Color(255, 255, 255));
				TopStudentLabel.setBounds(175, 10, 238, 38);
				toppanel.add(TopStudentLabel);
				
				JLabel TopPortalLabel = new JLabel("PORTAL");
				TopPortalLabel.setFont(AcadSyncFontManager.getFont("Poppins-Bold"));
				TopPortalLabel.setForeground(Color.WHITE);
				TopPortalLabel.setBounds(175, 36, 238, 26);
				toppanel.add(TopPortalLabel);
				
				JLabel AcadsyncLabel = new JLabel("AcadSync²");
				AcadsyncLabel.setForeground(Color.WHITE);
				AcadsyncLabel.setFont(AcadSyncFontManager.getFont("Poppins-Light"));
				AcadsyncLabel.setBounds(175, 54, 238, 23);
				toppanel.add(AcadsyncLabel);
				

	  
				//Line color white
		        ExtraLabel.setForeground(new Color(255, 255, 255));
		        ExtraLabel.setBounds(1162, -3, 15, 15);
		       
		        toppanel.add(ExtraLabel);
		                
	            JLabel ExtraLabel_1 = new JLabel("│");
	            ExtraLabel_1.setForeground(Color.WHITE);
	            ExtraLabel_1.setBounds(1162, 8, 15, 15);
	            toppanel.add(ExtraLabel_1);
	            
	            JLabel ExtraLabel_2 = new JLabel("│");
	            ExtraLabel_2.setForeground(Color.WHITE);
	            ExtraLabel_2.setBounds(1162, 19, 15, 15);
	            toppanel.add(ExtraLabel_2);
	            
	            JLabel ExtraLabel_3 = new JLabel("│");
	            ExtraLabel_3.setForeground(Color.WHITE);
	            ExtraLabel_3.setBounds(1162, 30, 15, 15);
	            toppanel.add(ExtraLabel_3);
	            
	            JLabel ExtraLabel_4 = new JLabel("│");
	            ExtraLabel_4.setForeground(Color.WHITE);
	            ExtraLabel_4.setBounds(1162, 41, 15, 15);
	            toppanel.add(ExtraLabel_4);
	            
	            JLabel ExtraLabel_5 = new JLabel("│");
	            ExtraLabel_5.setForeground(Color.WHITE);
	            ExtraLabel_5.setBounds(1162, 52, 15, 15);
	            toppanel.add(ExtraLabel_5);
	            
	            JLabel ExtraLabel_6 = new JLabel("│");
	            ExtraLabel_6.setForeground(Color.WHITE);
	            ExtraLabel_6.setBounds(1162, 63, 15, 15);
	            toppanel.add(ExtraLabel_6);
	            
	            JLabel ExtraLabel_7 = new JLabel("│");
	            ExtraLabel_7.setForeground(Color.WHITE);
	            ExtraLabel_7.setBounds(1162, 74, 15, 15);
	            toppanel.add(ExtraLabel_7);
	            
	            JLabel ExtraLabel_8 = new JLabel("│");
	            ExtraLabel_8.setForeground(Color.WHITE);
	            ExtraLabel_8.setBounds(1162, 85, 15, 15);
	            toppanel.add(ExtraLabel_8);
	            
			
		
			
			JLabel NavigationBackground = new JLabel("");
			NavigationBackground.setBounds(0, 90, 270, 736);
			contentPane.add(NavigationBackground);
			NavigationBackground.setIcon(new ImageIcon("src/Group 89.png"));
			
			CustomRoundPanel Mainpanel = new CustomRoundPanel(20);
			Mainpanel.setBorder(new EmptyBorder(0, 0, 0, 0));
            Mainpanel.setBounds(279, 98, 949, 565);
			contentPane.add(Mainpanel);
			Mainpanel.setLayout(null);
			
			JLabel Moreinfo = new JLabel("Click a row for more information");
			Moreinfo.setFont(AcadSyncFontManager.getFont("Poppins-Light"));
			Moreinfo.setBounds(385, 157, 200, 25);
			Mainpanel.add(Moreinfo);
			
			
			//Top panel
			JPanel GreetingPanel = new JPanel();
			GreetingPanel.setLayout(null);
			GreetingPanel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(128, 128, 128)));
			GreetingPanel.setBackground(Color.WHITE);
			GreetingPanel.setBounds(10, 11, 929, 60);
			Mainpanel.add(GreetingPanel);
			
			JLabel IconProfile = new JLabel("");
			IconProfile.setIcon(new ImageIcon("src/book.png"));
			IconProfile.setBounds(43, 15, 24, 24);
			GreetingPanel.add(IconProfile);
			
			JLabel LabelSubs = new JLabel("Subjects Offered\r\n");
			LabelSubs.setBounds(79, 15, 462, 28);
			LabelSubs.setFont(AcadSyncFontManager.getFont("Poppins-Bold"));
			GreetingPanel.add(LabelSubs);
			
			JPanel Searchpanel = new JPanel();
			Searchpanel.setBackground(new Color(255, 255, 255));
			Searchpanel.setBounds(21, 100, 918, 60);
			Mainpanel.add(Searchpanel);
			Searchpanel.setLayout(null);
			
			JLabel SearchLabel = new JLabel("Search:");
			SearchLabel.setBounds(34, 16, 72, 14);
			SearchLabel.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold"));
			Searchpanel.add(SearchLabel);
			
			SearchtextField = new JTextField();
			SearchtextField.setBounds(107, 8, 330, 29);
			SearchtextField.setForeground(Color.GRAY);
			SearchtextField.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold"));
			Searchpanel.add(SearchtextField);
			SearchtextField.setColumns(10);
			SearchtextField.setBorder(new LineBorder(new Color(128, 128, 128), 2));
			
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
                    tableModelSubs.setRowCount(0); // Clear the table

                    // Add all rows back to the table model from the full data list
                    for (Object[] row : AllTableData) { // `AllTableData` contains the full data set
                        tableModelSubs.addRow(row);
                    }
                }
            });
	
			
			
			tableModelSubs = new DefaultTableModel() {
		         private static final long serialVersionUID = 1L;
		
		         @Override
		         public boolean isCellEditable(int row, int column) {
		             return false;  // Prevent all cells from being editable
		         }
		     };
		     
		     
		     TableStudentSubs = new JTable(tableModelSubs);
		     TableStudentSubs.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Only one row can be selected at a time
		     TableStudentSubs.setBounds(70, 10, 600, 200);
		     
		
		
		     // Set the font for the JTable content
		     if (AcadSyncFontManager.getFont("Poppins-SemiBold") != null) {
		    	 TableStudentSubs.setFont(AcadSyncFontManager.getFont("Poppins-SemiBold").deriveFont(12f));
		     }
		
		     
		        // Set table row height and background color
		     	TableStudentSubs.setRowHeight(30);
		     	TableStudentSubs.setBackground(Color.WHITE);
		     	TableStudentSubs.setGridColor(Color.BLACK);
		     	TableStudentSubs.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		        

		     // Customize table header font
		     if (AcadSyncFontManager.getFont("Poppins-SemiBold") != null) {
		    	 TableStudentSubs.getTableHeader().setFont(AcadSyncFontManager.getFont("Poppins-SemiBold").deriveFont(13f)); // Larger font for header
		    	 TableStudentSubs.getTableHeader().setBackground(Color.WHITE); // Header background color
		    	 TableStudentSubs.getTableHeader().setForeground(Color.BLACK); // Header text color
		     }
		     
		     
		     // Set custom renderer
		     TableStudentSubs.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
	             private static final long serialVersionUID = 1L;
	    
	             @Override
	             public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	                 Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	                 setHorizontalAlignment(CENTER);
	    
	                 if (isSelected) {
	                     component.setBackground(new Color(239, 159, 185));
	                     component.setForeground(Color.BLACK);
	                 } else {
	                     component.setBackground(Color.WHITE);
	                     component.setForeground(Color.BLACK);
	                 }
	                 return component;
	             }
	         });
	         
	         
	         
	         // Column names
		     tableModelSubs.addColumn("SECTION");
		     tableModelSubs.addColumn("SUBJECT CODE");
		     tableModelSubs.addColumn("DESCRIPTION");
		     tableModelSubs.addColumn("SCHEDULE CODE");
		     tableModelSubs.addColumn("UNITS");
		     
		  // Add table to a scroll pane
	        JScrollPane scrollPane = new JScrollPane(TableStudentSubs);
	        scrollPane.setBorder(new LineBorder(Color.BLACK, 2)); 
	        scrollPane.setBounds(12, 180, 924, 358);
	        Mainpanel.add(scrollPane);
	        
			
	        
	        
	      //Load Table Data
	        LoadDataFromDatabase(AcadSyncFontManager.getFont("Poppins-Light"), AcadSyncFontManager.getFont("Poppins-Light"), AcadSyncFontManager.getFont("Poppins-Light"), AcadSyncFontManager.getFont("Poppins-Light"));
	        
	        

	        // Add mouse listener to detect row selection
	        TableStudentSubs.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	            	int selectedRow = TableStudentSubs.getSelectedRow();
	                
	                if (selectedRow >= 0) {     
	                	String subjectCode = (String) tableModelSubs.getValueAt(selectedRow, 1);
	                    SubjectDetails subjectDetails = getSubjectDetails(subjectCode);

	                    // Open the new window and set the subject details
	                    SubDetailsWindow detailsWindow = new SubDetailsWindow(AcadSyncFontManager.getFont("Poppins-SemiBold"), subjectDetails);
	                    detailsWindow.setVisible(true);
	                    
	                    
	                }
	            }
	        });
	}	
	
////////METHODS
			
	//Method for Search a data inside our JtextField 
	private void SearchData(String searchTerm) {
	   tableModelSubs.setRowCount(0); // Clear the table

	    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
	        String query = "SELECT * FROM sections_subjects WHERE " +
	                       "Section LIKE ? OR SubjectCode LIKE ? OR Description LIKE ? OR " +
	                       "Unit LIKE ? OR SchedCode LIKE ?" ;
	        try (PreparedStatement searchStmt = conn.prepareStatement(query)) {
	            // Set the search term for all columns
	            for (int i = 1; i <= 5; i++) { // Assuming 5 searchable columns
	                searchStmt.setString(i, "%" + searchTerm + "%");
	            }

	            try (ResultSet rs = searchStmt.executeQuery()) {
	                while (rs.next()) {
	                    tableModelSubs.addRow(new Object[]{
	                    		rs.getString("Section"),
			                    rs.getString("SubjectCode"),
			                    rs.getString("Description"),
			                    rs.getString("SchedCode"),
			                    rs.getString("Unit"),
	                  
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
		String query = "SELECT * FROM sections_subjects";
		
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(query)) {
	        	
	        	  // Clear the existing table data and cache
	            tableModelSubs.setRowCount(0);

	            while (rs.next()) {
	                // Create a row object from the ResultSet
	                Object[] row = new Object[] {
	                    rs.getString("Section"),
	                    rs.getString("SubjectCode"),
	                    rs.getString("Description"),
	                    rs.getString("SchedCode"),
	                    rs.getString("Unit"),
	                   
	                };

	                // Add the row to the cache
	                AllTableData.add(row);

	                // Add the row to the table model
	                tableModelSubs.addRow(row);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(this, "Error loading data from database: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
	        }
		}
	
	// Fetch the instructor and schedule details for the selected subjectCode
    private SubjectDetails getSubjectDetails(String subjectCode) {
        SubjectDetails subjectDetails = null;
        String query = "SELECT * FROM sections_subjects WHERE SubjectCode = ?";
        
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setString(1, subjectCode);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String instructor = resultSet.getString("Instructor");
                String schedule1 = resultSet.getString("Schedule1");
                String schedule2 = resultSet.getString("Schedule2");
                String schedule3 = resultSet.getString("Schedule3");
                String schedule4 = resultSet.getString("Schedule4");
                

                subjectDetails = new SubjectDetails(instructor, schedule1, schedule2, schedule3, schedule4);
   
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to fetch subject details.", "Fetch Error", JOptionPane.ERROR_MESSAGE);
	        }
	        
	        return subjectDetails;
	    }


   }





