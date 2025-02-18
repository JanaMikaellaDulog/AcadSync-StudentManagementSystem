package acadsyncFile;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class CustomRoundedTextField extends JTextField {
    private static final long serialVersionUID = 1L;
    private int cornerRadius; // The radius of the rounded corners
    private Color borderColor; // The color of the border
    private int borderThickness; // The thickness of the border

    public CustomRoundedTextField(int cornerRadius) {
    	super();
        this.cornerRadius = cornerRadius;
        this.borderThickness = 1; // Default border thickness
        this.borderColor = new Color(255, 102, 54); // Default border color
        setOpaque(false); // Ensures the background is transparent
        setBorder(new EmptyBorder(5, 5, 5, 5)); // Add padding inside the text field
        setForeground(Color.GRAY);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw background with rounded corners
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

        // Draw text
        super.paintComponent(g2);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Set the thickness of the border
        g2.setStroke(new java.awt.BasicStroke(borderThickness));

        // Draw rounded border
        g2.setColor(borderColor);
        g2.drawRoundRect(
            borderThickness / 2, 
            borderThickness / 2, 
            getWidth() - borderThickness, 
            getHeight() - borderThickness, 
            cornerRadius, 
            cornerRadius
        );

        g2.dispose();
    }

    // Setter for border color
    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
        repaint();
    }

    // Setter for corner radius
    public void setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
        repaint();
    }

    // Setter for border thickness
    public void setBorderThickness(int borderThickness) {
        this.borderThickness = borderThickness;
        repaint();
    }

    // Getter for corner radius
    public int getCornerRadius() {
        return cornerRadius;
    }

    // Getter for border color
    public Color getBorderColor() {
        return borderColor;
    }

    // Getter for border thickness
    public int getBorderThickness() {
        return borderThickness;
    }
}
