package acadsyncStudent;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

public class CustomLinePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	//Create Matteborder panel
	
	public CustomLinePanel() {
		setBorder(BorderFactory.createMatteBorder(
                0, 0, 0, 4, Color.black));
	}

	private void setBorder(MatteBorder matteBorder) {
		// TODO Auto-generated method stub
		
	}
	
}
