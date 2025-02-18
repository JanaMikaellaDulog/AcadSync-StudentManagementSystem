package acadsyncStudent;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import java.awt.Color;

public class CustomSmallRoundPanel extends JPanel{

	    private static final long serialVersionUID = 1L;
	    private int cornerRadius;

	    // Constructor to set the corner radius
	    public CustomSmallRoundPanel(int cornerRadius) {
	    	setForeground(new Color(255, 255, 255));
	        this.cornerRadius = cornerRadius;
	        //setBackground(new Color(34, 35, 95)); // Set the background color
	        setOpaque(false); // Ensure transparency for rounded corners
	    }

	    @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        Graphics2D g2 = (Graphics2D) g;
	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	        // Set the background color of the panel (if needed)
	        g2.setColor(getBackground());
	        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
	    }

	 
	    // Method to update the corner radius dynamically
	    public void setCornerRadius(int radius) {
	        this.cornerRadius = radius;
	        repaint();
	    }
	}


