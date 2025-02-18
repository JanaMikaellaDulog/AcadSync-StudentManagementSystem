package acadsyncFile;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class CustomButtonClear extends JButton {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int cornerRadius; // Corner radius field
	private Color hoverBackground;   // Hover background color

    public CustomButtonClear(String text, int cornerRadius) {
        super(text);
        this.cornerRadius = cornerRadius;
        this.hoverBackground = new Color(26, 185, 191);   // Hover background
        setUI(new javax.swing.plaf.basic.BasicButtonUI()); // Basic UI allows customization
        setBackground(new Color(0, 170, 160)); // Default background
        setForeground(new Color(0, 0, 0));
        setOpaque(false); // Ensure transparency for rounded corners
        
        
        // Add MouseListener for hover effect
        addMouseListener(new MouseAdapter() {
        	
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(hoverBackground); // Change to hover background
            }
            
            @Override
            public void mouseExited(MouseEvent e) { 
                setBackground(new Color(0, 170, 160)); // Change to hover background
            }
        });
    }
    

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Smooth edges

        // Determine button state color
        if (getModel().isPressed()) {
            g2.setColor(new Color(0, 170, 160)); // Pressed state color
        } else {
            g2.setColor(getBackground());
        }

        // Draw rounded rectangle
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
        super.paintComponent(g); // Paint text and icon
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Smooth edges
        g2.setStroke(new java.awt.BasicStroke((int)(1)));  // Set the stroke thickness (for LineBorder)

        // Draw border with rounded corners
        g2.setColor(getForeground());
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);
    }

    // Setter for corner radius
    public void setCornerRadius(int radius) {
        this.cornerRadius = radius;
        repaint(); // Redraw the button with the new radius
    }

    // Getter for corner radius
    public int getCornerRadius() {
        return cornerRadius;
    }
    
    // Set hover background color
    public void setHoverBackground(Color color) {
        this.hoverBackground = color;
    }
}

