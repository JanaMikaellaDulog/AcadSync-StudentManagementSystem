// -------------------------------------- Dependencies --------------------------------------
package acadsyncStudent;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.SwingConstants;



//--------------------------------------------------------------------------------------------




public class CustomButtonNavigation extends JButton {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int cornerRadius; // Corner radius field
	private Color hoverBackground;   // Hover background color

    public CustomButtonNavigation(String text, int cornerRadius) {
        super(text);
        this.cornerRadius = cornerRadius;
        this.hoverBackground = new Color(180, 180, 180);   // Hover background
        setUI(new javax.swing.plaf.basic.BasicButtonUI()); // Basic UI allows customization
        setBackground(new Color(217, 217, 217)); // Default background
        setForeground(Color.BLACK);
        setContentAreaFilled(false);
        setBorderPainted(false);  // Disable border painting
        setFocusPainted(false);   // Disable focus painting
        
        // Align text to the left and icon to the right
        setHorizontalTextPosition(SwingConstants.RIGHT); // Text on the left
        setHorizontalAlignment(SwingConstants.CENTER);   // Align content to the left
        setOpaque(false); // Ensure transparency for rounded corners
        
        
        // Add MouseListener for hover effect
        addMouseListener(new MouseAdapter() {
   
            @Override
            public void mouseEntered(MouseEvent e) {
                // On mouse enter, change button background and text color
                setBackground(hoverBackground); // Change to hover background
            }
            
            @Override
            public void mouseExited(MouseEvent e) { 
            	// On mouse exit, revert button background and text color to original
                setBackground(new Color(217, 217, 217)); // Original background color
            }
        });
    }
    

		    @Override
		    protected void paintComponent(Graphics g) {
		        Graphics2D g2 = (Graphics2D) g;
		        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Smooth edges
		
		        // Determine button state color
		        if (getModel().isPressed()) {
		            g2.setColor(new Color(164, 164, 164)); // Pressed state color
		        } else {
		            g2.setColor(getBackground());
		        }
		
		        // Draw rounded rectangle
		        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
		        super.paintComponent(g); // Paint text and icon
		    }
		
		    @Override
		    protected void paintBorder(Graphics g) {
		    	    // No border drawing
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


