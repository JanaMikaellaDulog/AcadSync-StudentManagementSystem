package acadsyncStudent;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import javax.swing.JLabel;

public class SubDetailsWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	
	public static void main(String[] args) {
	Font customFontSemiBold = null;
 
    
    
	try {
        // Load the Poppins-SemiBold font
        File fontFileSemiBold = new File("C:\\Users\\Angel\\eclipse-workspace\\AcadSync\\Poppins-SemiBold.ttf");
        customFontSemiBold = Font.createFont(Font.TRUETYPE_FONT, fontFileSemiBold).deriveFont(15f);

     
        // Register all fonts globally in the Graphics Environment
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(customFontSemiBold);

        

    } catch (FontFormatException | IOException e) {
        e.printStackTrace();
        System.out.println("Failed to load one or more custom fonts.");
    }
			
	 // Pass the loaded fonts to the UI
    Font finalCustomFontSemiBold = customFontSemiBold;

 

	/**
	 * Launch the application.
	 */
	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SubDetailsWindow frame = new SubDetailsWindow(finalCustomFontSemiBold, null);
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
	public SubDetailsWindow(Font semiBoldFont, SubjectDetails subjectDetails) {
		setTitle("Subject Details");
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/SMS Logo Small.jpg"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 418, 247);
		setLocationRelativeTo(null);  // Center the frame on the screen
		
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Label1 = new JLabel("Schedule 1:");
		Label1.setBounds(10, 14, 111, 14);
		Label1.setFont(semiBoldFont);
		contentPane.add(Label1);
		
		JLabel Label1_1 = new JLabel("Schedule 2:");
		Label1_1.setFont(semiBoldFont);
		Label1_1.setBounds(10, 51, 111, 14);
		contentPane.add(Label1_1);
		
		JLabel Label1_2 = new JLabel("Schedule 3:");
		Label1_2.setFont(semiBoldFont);
		Label1_2.setBounds(10, 88, 111, 14);
		contentPane.add(Label1_2);
		
		JLabel Label1_3 = new JLabel("Schedule 4:");
		Label1_3.setFont(semiBoldFont);
		Label1_3.setBounds(10, 125, 111, 14);
		contentPane.add(Label1_3);
		
		JLabel Label1_4 = new JLabel("Instructor:");
		Label1_4.setFont(semiBoldFont);
		Label1_4.setBounds(10, 162, 111, 14);
		contentPane.add(Label1_4);
		
		
		///these textfields will change
		
		CustomRoundTextField sched1 = new CustomRoundTextField();
		sched1.setText(" " + subjectDetails.getSchedule1());
		
		CustomRoundTextField sched2 = new CustomRoundTextField();
		sched2.setText(" " + subjectDetails.getSchedule2());
		
		CustomRoundTextField sched3 = new CustomRoundTextField();
		sched3.setText(" " + subjectDetails.getSchedule3());
		
		CustomRoundTextField sched4 = new CustomRoundTextField();
		sched4.setText(" " +subjectDetails.getSchedule4());
		
		CustomRoundTextField instructor = new CustomRoundTextField();
		instructor.setText(" " + subjectDetails.getInstructor());
		
		
		sched1.setEnabled(false);
		sched1.setEditable(false);
		sched1.setDisabledTextColor(Color.GRAY);
		sched1.setBorder(null);
		sched1.setOpaque(false);
		sched1.setFont(semiBoldFont);
		sched1.setBounds(118, 11, 243, 25);
		contentPane.add(sched1);
		
		
		sched2.setEnabled(false);
		sched2.setEditable(false);
		sched2.setDisabledTextColor(Color.GRAY);
		sched2.setFont(semiBoldFont);
		sched2.setBorder(null);
		sched2.setOpaque(false);
		sched2.setBounds(118, 48, 243, 25);
		contentPane.add(sched2);
		

		sched3.setEnabled(false);
		sched3.setEditable(false);
		sched3.setDisabledTextColor(Color.GRAY);
		sched3.setFont(semiBoldFont);
		sched3.setBorder(null);
		sched3.setOpaque(false);
		sched3.setBounds(118, 85, 243, 25);
		contentPane.add(sched3);
		

		sched4.setEnabled(false);
		sched4.setEditable(false);
		sched4.setDisabledTextColor(Color.GRAY);
		sched4.setFont(semiBoldFont);
		sched4.setOpaque(false);
		sched4.setBorder(null);
		sched4.setBounds(118, 122, 243, 25);
		contentPane.add(sched4);
		

		instructor.setEnabled(false);
		instructor.setEditable(false);
		instructor.setDisabledTextColor(Color.GRAY);
		instructor.setFont(semiBoldFont);
		instructor.setOpaque(false);
		instructor.setBorder(null);
		instructor.setBounds(118, 159, 243, 25);
		contentPane.add(instructor);
	
		
		
		
	}}


