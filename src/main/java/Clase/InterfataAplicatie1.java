package Clase;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfataAplicatie1 extends JFrame {

    public InterfataAplicatie1() {
        JFrame f = new JFrame("Human Resources Management System");
        f.setSize(1920, 1080);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panouFundal = new JPanel() {
        	Image imagineFundal = new ImageIcon("src/main/java/Fotografii/ProjectBackgroundJavaP3.jpg").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(imagineFundal, 0, 0, getWidth(), getHeight(), this);  
            }
        };
        
        panouFundal.setLayout(new BorderLayout()); 

        JPanel panouLogare = new JPanel(new GridBagLayout());
        panouLogare.setOpaque(false); 
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(100, 10, 10, 10); 
        c.anchor = GridBagConstraints.CENTER; 

        JLabel label1 = new JLabel("Welcome to the Human Resources");
        label1.setFont(new Font("Times New Roman", Font.BOLD, 50));
        label1.setForeground(new Color(222, 98, 216)); 
        c.gridx = 0;
        c.gridy = 0;
        panouLogare.add(label1, c);
        
        c.insets = new Insets(10, 10, 10, 10);
        c.gridy++;
        JLabel label2 = new JLabel("Management System!");
        label2.setFont(new Font("Times New Roman", Font.BOLD, 50));
        label2.setForeground(new Color(222, 98, 216));
        panouLogare.add(label2, c);
        
        c.gridy++;
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        emailLabel.setForeground(new Color(222, 98, 216));
        panouLogare.add(emailLabel, c);

        c.gridy++;
        JTextField emailField = new JTextField(20);
        emailField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        emailField.setBackground(new Color(255, 255, 255, 150)); 
        panouLogare.add(emailField, c);

        c.gridy++;
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        passwordLabel.setForeground(new Color(222, 98, 216));
        panouLogare.add(passwordLabel, c);

        c.gridy++;
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        passwordField.setBackground(new Color(255, 255, 255, 150));
        panouLogare.add(passwordField, c);

        c.gridy++;
        JButton button = new JButton("Log In");
        button.setFont(new Font("Times New Roman", Font.BOLD, 20));
        button.setBackground(new Color(255, 105, 180));
        button.setForeground(Color.WHITE);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
                System.out.println("Email: " + email + ", Password: " + password);
            }
        });
        panouLogare.add(button, c);

        JPanel panouLogareContainer = new JPanel(new BorderLayout());
        panouLogareContainer.setOpaque(false);
        panouLogareContainer.add(panouLogare, BorderLayout.WEST); 

        panouFundal.add(panouLogareContainer, BorderLayout.NORTH);

        f.setContentPane(panouFundal);
        f.setVisible(true);
    }

}
