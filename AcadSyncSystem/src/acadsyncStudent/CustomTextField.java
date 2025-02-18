package acadsyncStudent;

import javax.swing.*;
import java.awt.*;

public class CustomTextField extends JTextField {
    
	private static final long serialVersionUID = 1L;
	 private Color shadowColor = Color.GRAY;
	    private int shadowOffsetX = 4;
	    private int shadowOffsetY = 4;
	    private Color backgroundColor = (new Color(141, 255, 247));

	    public CustomTextField(int columns) {
	        super(columns);
	        setOpaque(false); // Ensures we handle the background manually
	        setDisabledTextColor(Color.BLACK); // Set disabled text color to black
	    }

	    @Override
	    protected void paintComponent(Graphics g) {
	        Graphics2D g2d = (Graphics2D) g.create();

	        // Enable anti-aliasing for smooth rendering
	        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	        // Draw drop shadow (square corners)
	        g2d.setColor(shadowColor);
	        g2d.fillRect(shadowOffsetX, shadowOffsetY, getWidth() - shadowOffsetX, getHeight() - shadowOffsetY);

	        // Draw background (square corners)
	        g2d.setColor(backgroundColor);
	        g2d.fillRect(0, 0, getWidth() - shadowOffsetX, getHeight() - shadowOffsetY);

	        // Draw the text field content
	        super.paintComponent(g2d);
	        g2d.dispose();
	    }

	    @Override
	    protected void paintBorder(Graphics g) {
	        // No default border, only background and shadow are drawn
	    }

	    public void setShadowColor(Color color) {
	        this.shadowColor = color;
	        repaint();
	    }

	    public void setShadowOffset(int offsetX, int offsetY) {
	        this.shadowOffsetX = offsetX;
	        this.shadowOffsetY = offsetY;
	        repaint();
	    }

	    public void setBackgroundColor(Color color) {
	        this.backgroundColor = color;
	    }
}