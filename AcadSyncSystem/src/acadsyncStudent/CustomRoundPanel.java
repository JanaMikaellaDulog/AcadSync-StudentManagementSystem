package acadsyncStudent;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import java.awt.Color;

public class CustomRoundPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private int cornerRadius;

    // Constructor to set the corner radius
    public CustomRoundPanel(int cornerRadius) {
    	setForeground(new Color(128, 128, 128));
        this.cornerRadius = cornerRadius;
        setBackground(new Color(255, 255, 255)); // Set the background color
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

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // First, draw the LineBorder (if necessary)
        g2.setColor(new Color(128, 128, 128));  // Set the color for LineBorder (can customize)
        g2.setStroke(new java.awt.BasicStroke(2));  // Set the stroke thickness (for LineBorder)
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);

        // Then, draw the custom rounded border on top (for visual emphasis)
        g2.setColor(getForeground());  // Set the color for the custom rounded border (can customize)
        g2.setStroke(new java.awt.BasicStroke(0));  // Set the stroke thickness (for custom rounded border)
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);
    }

    // Method to update the corner radius dynamically
    public void setCornerRadius(int radius) {
        this.cornerRadius = radius;
        repaint();
    }
}
