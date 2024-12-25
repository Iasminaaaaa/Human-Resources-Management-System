package gui;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Color;
import com.jgoodies.forms.factories.DefaultComponentFactory;

import controller.GestionareConturi;
import model.Cont;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.MatteBorder;
import javax.swing.JComboBox;

public class InterfataAp extends JFrame {

    private static final long serialVersionUID = 1L;
    private final JLabel lblWelcomeToHuman = DefaultComponentFactory.getInstance().createTitle("Welcome to Human Resources");
    private JLabel lblNewLabel;
    private final JLabel lblEmail = new JLabel("E-mail:");
    private final JTextField txtEmail = new JTextField();
    private final JLabel lblPassword = new JLabel("Password:");
    private final JPasswordField txtPassword = new JPasswordField();
    private final JLabel lblAccount = new JLabel("Connect as:");
    private final JButton loginButton = new JButton("Login");
    String[] tipuri={"Administrator", "Employee"};
    private final JComboBox <String> tipuriAngajati = new JComboBox<>(tipuri);
    private final JLabel lblWrongEmail = new JLabel("Nu exista un cont cu acest email!");
    private final JLabel lblWrongPassword = new JLabel("Parola incorecta!");
    private final JLabel lblWrongAccountType = new JLabel("Datele oferite nu corespund cu tipul angajatului!");

    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    InterfataAp frame = new InterfataAp();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace(); 
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public InterfataAp() {
    	tipuriAngajati.setBackground(new Color(128, 255, 255));
    	tipuriAngajati.setBounds(200, 485, 300, 30);
    	tipuriAngajati.setFont(new Font("Times New Roman", Font.PLAIN, 20));
    	//tipuriAngajati.setBackground();
    	tipuriAngajati.setForeground(new Color(0, 0, 0));
    	tipuriAngajati.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
    	
    	
    	loginButton.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(128, 255, 255)));
    	loginButton.setBounds(304, 580, 98, 30);
    	loginButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
    	loginButton.setForeground(Color.BLACK);
    	loginButton.setBackground(new Color(222, 98, 216));
  
    	loginButton.addActionListener(new ActionListener(){
    @Override
       public void actionPerformed(ActionEvent e) {	
    	try {
    		String email=txtEmail.getText();
    		String passText = new String(txtPassword.getPassword());
			String tipSelectat = (String) tipuriAngajati.getSelectedItem();
    	    Cont c=new Cont(email, passText, tipSelectat);
    			
    	    int rezultat = GestionareConturi.autentificareCont(c);
            lblWrongEmail.setVisible(false);
            lblWrongPassword.setVisible(false);
            lblWrongAccountType.setVisible(false);
              
    	if(rezultat==1){
    		if(tipSelectat=="Administrator") {
    			InterfataAdministrator fereastraAdministrator = new InterfataAdministrator();
    			fereastraAdministrator.setVisible(true);
             	dispose(); 
           } 
      	else{
        		if (tipSelectat=="Employee") {
        			InterfataAngajati fereastraAngajati = new InterfataAngajati();
        			fereastraAngajati.setVisible(true);
        			dispose();
        		}
        	}
    	}else 
    		if (rezultat == 2) {
    			lblWrongAccountType.setVisible(true);
    	   } else 
    		   if (rezultat == 3) {
    		   lblWrongPassword.setVisible(true);
    	   	   } else 
    	   		   if (rezultat == 4) {
    	   			   lblWrongEmail.setVisible(true);
    	   		   }
    	} catch (SQLException e1) {
    		e1.printStackTrace();
    	}
    }
   });

    	
    	txtPassword.setColumns(10);
    	txtPassword.setFont(new Font("Times New Roman", Font.PLAIN, 20));
    	txtPassword.setBounds(200, 390, 300, 30);
    	txtPassword.setBackground(new Color(255, 255, 255, 180));
        txtPassword.setForeground(new Color(0, 0, 0)); 
    	
        txtEmail.setColumns(10);
    	txtEmail.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        txtEmail.setBounds(200, 296, 300, 30);  
        txtEmail.setBackground(new Color(255, 255, 255, 180));
        txtEmail.setForeground(new Color(0, 0, 0));
        
        
        lblWelcomeToHuman.setFont(new Font("Times New Roman", Font.BOLD, 50));
    	lblWelcomeToHuman.setForeground(new Color(222, 98, 216));  
    	lblWelcomeToHuman.setBounds(23, 98, 743, 50); 
    	
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(Frame.MAXIMIZED_BOTH); 
        setResizable(false); 
        setTitle("Human Resources Management System");

        JPanel panouFundal = new JPanel() {
        	
			private static final long serialVersionUID = 1L;
			Image imagineFundal = new ImageIcon("src/main/java/Fotografii/ProjectBackgroundJavaP3.jpg").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(imagineFundal, 0, 0, getWidth(), getHeight(), this);  
            }
        };

      panouFundal.setLayout(null);
      setContentPane(panouFundal);
      panouFundal.add(lblWelcomeToHuman);
      
      lblNewLabel = new JLabel("Management System");
      lblNewLabel.setBounds(120, 158, 527, 58);
      lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 50));
      lblNewLabel.setForeground(new Color(222, 98, 216));
      
  	  lblPassword.setFont(new Font("Times New Roman", Font.HANGING_BASELINE, 20));
      lblPassword.setForeground(new Color(222, 98, 216));
  	  lblPassword.setBounds(307, 352, 98, 30);
      
      lblEmail.setBounds(319, 256, 200, 30);
      lblEmail.setFont(new Font("Times New Roman", Font.HANGING_BASELINE, 20));
      lblEmail.setForeground(new Color(222, 98, 216));
      
      lblAccount.setFont(new Font("Times New Roman", Font.HANGING_BASELINE, 20));
      lblAccount.setForeground(new Color(222, 98, 216));
  	  lblAccount.setBounds(299, 450, 98, 30);
      
  	  lblWrongEmail.setFont(new Font("Times New Roman", Font.HANGING_BASELINE, 20));
      lblWrongEmail.setForeground(new Color(222, 98, 216));
	  lblWrongEmail.setBounds(299, 450, 98, 30);
    
	  lblWrongEmail.setVisible(false);
	  lblWrongPassword.setVisible(false);
      lblWrongPassword.setFont(new Font("Times New Roman", Font.HANGING_BASELINE, 20));
      lblWrongPassword.setForeground(Color.RED);
      lblWrongPassword.setBounds(200, 530, 300, 30);
	  
	  lblWrongAccountType.setVisible(false);
      lblWrongAccountType.setFont(new Font("Times New Roman", Font.HANGING_BASELINE, 20));
      lblWrongAccountType.setForeground(Color.RED);
      lblWrongAccountType.setBounds(200, 530, 300, 30);
  
      panouFundal.add(lblNewLabel);
      panouFundal.add(lblEmail);
      panouFundal.add(txtEmail);
      panouFundal.add(lblPassword);
      panouFundal.add(txtPassword);
      panouFundal.add(loginButton);
      panouFundal.add(lblAccount);
      panouFundal.add(tipuriAngajati);
      panouFundal.add(lblWrongEmail);
    }     
}
