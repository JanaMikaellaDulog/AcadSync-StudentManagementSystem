package acadsyncFile;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class CustomButtonForSmallPanelProfile extends JButton {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int cornerRadius; // Corner radius field
	private Color hoverBackground;   // Hover background color

    public CustomButtonForSmallPanelProfile(String text, int cornerRadius) {
        super(text);
        this.cornerRadius = cornerRadius;
        this.hoverBackground = new Color(28, 203, 208);   // Hover background
        setUI(new javax.swing.plaf.basic.BasicButtonUI()); // Basic UI allows customization
        setForeground(new Color(0, 0, 0)); 
        setBackground(new Color(36, 248, 255)); // Default background
        setOpaque(false); // Ensure transparency for rounded corners
        
        
        // Add MouseListener for hover effect
        addMouseListener(new MouseAdapter() {
        	
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(hoverBackground); // Change to hover background
            }
            
            @Override
            public void mouseExited(MouseEvent e) { 
                setBackground(new Color(36, 248, 255)); // Change to hover background
            }
        });
    }
    

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Smooth edges

        // Determine button state color
        if (getModel().isPressed()) {
            g2.setColor(new Color(36, 248, 255)); // Pressed state color
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
        g2.setStroke(new java.awt.BasicStroke(3));  // Set the stroke thickness (for LineBorder)

        // Draw border with rounded corners
        g2.setColor(new Color(232, 0, 85));
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

