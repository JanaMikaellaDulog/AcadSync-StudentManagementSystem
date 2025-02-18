package acadsyncStudent;

import javax.swing.*;
import java.awt.*;

public class CustomRoundTextField extends JTextField {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Border radius
	private int borderRadius = 12;


	    // Constructor
	    public CustomRoundTextField() {
	        super();
	        setOpaque(false);  // Make the background transparent
	    }

	    // Set the border radius
	    public void setBorderRadius(int radius) {
	        this.borderRadius = radius;
	        repaint();  // Repaint the text field when the border radius changes
	    }

	    // Override paintComponent to draw a rounded border
	    @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);  // Ensure the text is rendered correctly

	        // Create a Graphics2D object for drawing the custom border
	        Graphics2D g2d = (Graphics2D) g;
	        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	        // Set the border color (you can change this to match your UI theme)
	        g2d.setColor(getForeground());

	        // Draw a rounded rectangle for the border
	        g2d.setStroke(new BasicStroke(1));  // Set the border thickness
	        g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, borderRadius, borderRadius);
	    }
	}