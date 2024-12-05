import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfataAplicatie {

    public InterfataAplicatie() {
        JFrame f = new JFrame("Human Resources Management System");
        f.setSize(1920, 1080);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panouPrincipal = new JPanel() {
            Image imagineFundal = new ImageIcon("C:\\Users\\Iasmina\\Downloads\\ProjectBackgroundJavaP3.jpg").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(imagineFundal, 0, 0, getWidth(), getHeight(), this);  
            }
        };
        panouPrincipal.setLayout(new BorderLayout());

        JPanel panouLogare = new JPanel();
        panouLogare.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 15, 0);

        JLabel label = new JLabel("Welcome to the Human Resources Management System!");
        label.setFont(new Font("Arial", Font.BOLD, 50));
        label.setForeground(Color.WHITE);  
        panouLogare.add(label, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(5, 0, 15, 0);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        emailLabel.setForeground(Color.WHITE);
        panouLogare.add(emailLabel, gbc);

        gbc.gridy = 2;
        JTextField emailField = new JTextField(20);
        emailField.setFont(new Font("Arial", Font.PLAIN, 20));
        emailField.setBackground(new Color(255, 255, 255, 180));  // Culoare semitransparentă pentru câmpul de text
        panouLogare.add(emailField, gbc);

        gbc.gridy = 3;
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        passwordLabel.setForeground(Color.WHITE);
        panouLogare.add(passwordLabel, gbc);

        gbc.gridy = 4;
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 20));
        passwordField.setBackground(new Color(255, 255, 255, 180));  
        panouLogare.add(passwordField, gbc);

        gbc.gridy = 5;
        gbc.insets = new Insets(15, 0, 0, 0);

        JButton button = new JButton("Log In");
        button.setFont(new Font("Arial", Font.BOLD, 20));
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
        panouLogare.add(button, gbc);

        panouPrincipal.add(panouLogare, BorderLayout.EAST);

        f.add(panouPrincipal);

        f.setVisible(true);
    }

    public static void main(String[] args) {
        new InterfataAplicatie();
    }
}
