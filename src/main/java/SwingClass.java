import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
//public class SwingClass {
//	    public static void main(String[] args) {
//	        JFrame frame = new JFrame("Sistem de management al resurselor umane");
//	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	        frame.setSize(300, 200);
//	        
//	        JButton button = new JButton("Sistem de management al resurselor umane");
//	        frame.getContentPane().add(button); 
//
//	        frame.setVisible(true); 
//	        
//	        
//	        
//	        
//	    }
import javax.swing.*;
import java.awt.*;




public class SwingClass {

    SwingClass() {
        JFrame f = new JFrame("Sistem de management al resurselor umane");
       
        JButton b = new JButton(new ImageIcon("C:\\Users\\Iasmina\\Pictures\\Screenshots\\Logo.png"));
        f.add(b, BorderLayout.CENTER);
        b.setPreferredSize(new Dimension(200, 100));
       
        MyPanel panel = new MyPanel();
        f.add(panel, BorderLayout.CENTER);

        
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        new SwingClass();
    }
}

class MyPanel extends JPanel {
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Toolkit t = Toolkit.getDefaultToolkit();
        Image i = t.getImage("C:\\Users\\Iasmina\\Downloads\\photo.jpg");
       g.drawImage(i, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}

