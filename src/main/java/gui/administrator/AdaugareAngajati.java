package gui.administrator;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.time.LocalDate;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.ComboPopup;
import javax.swing.DefaultListCellRenderer;
import controller.GestionareAngajati;
import gui.DetaliiPersonaleAngajat;
import model.Angajat;

public class AdaugareAngajati extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private final JTextField txtNume;
	private final JTextField txtJob;
    private final JTextField txtSalariu;
    private final JTextField txtEmail;	
    private final JTextField txtManagerId;
    private final JTextField txtNrTelefon;
    private final JTextField txtAdresa;
    private final JLabel lblNume = new JLabel("Name:");
    private final JLabel lblTitlu = new JLabel("Add New Employee");
    private final JLabel lblJob = new JLabel("Job:");
    private final JLabel lblEmail = new JLabel("Email:");
    private final JLabel lblManagerId = new JLabel("Manager ID:");
    private final JLabel lblNrTelefon = new JLabel("Phone Number:");
    private final JLabel lblSalariu = new JLabel("Salary: ");
    private final JLabel lblAdresa = new JLabel("Address:");
    private final JLabel lblTip =  new JLabel("Role:");
    private final JButton btnAdauga = new JButton("Add Employee");
 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AdaugareAngajati dialog = new AdaugareAngajati();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AdaugareAngajati() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(DetaliiPersonaleAngajat.class.getResource("/Fotografii/pic10.png")));
		contentPanel.setLayout(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(new Color(36, 30, 30));
       
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		        
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize);
		setTitle("Add Employee");
		
		lblTitlu.setFont(new Font("Times New Roman", Font.BOLD, 50));
		lblTitlu.setForeground(new Color(222, 98, 216));
		lblTitlu.setBounds(580, 20, 500, 58);
		contentPanel.add(lblTitlu);

		lblNume.setForeground(new Color(0, 255, 255));
		lblNume.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNume.setBounds(527, 140, 100, 25);
		contentPanel.add(lblNume);

		txtNume = new JTextField();
		txtNume.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtNume.setBounds(675, 140, 397, 25); 
		contentPanel.add(txtNume);

		lblJob.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblJob.setBounds(527, 190, 100, 25);
		lblJob.setForeground(new Color(0, 255, 255));
		contentPanel.add(lblJob);

		txtJob = new JTextField();
		txtJob.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtJob.setBounds(675, 190, 397, 25); 
		contentPanel.add(txtJob);

		lblSalariu.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblSalariu.setBounds(527, 240, 100, 25);
		lblSalariu.setForeground(new Color(0, 255, 255));
		contentPanel.add(lblSalariu);

		txtSalariu = new JTextField();
		txtSalariu.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtSalariu.setBounds(675, 240, 397, 25); 
		contentPanel.add(txtSalariu);

		lblEmail.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblEmail.setBounds(527, 290, 100, 25);
		lblEmail.setForeground(new Color(0, 255, 255));
		contentPanel.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setBounds(675, 290, 397, 25);
		txtEmail.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		contentPanel.add(txtEmail);

		lblManagerId.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblManagerId.setBounds(527, 340, 138, 25);
		lblManagerId.setForeground(new Color(0, 255, 255));
		contentPanel.add(lblManagerId);

		txtManagerId = new JTextField();
		txtManagerId.setBounds(675, 340, 397, 25); 
		txtManagerId.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		contentPanel.add(txtManagerId);

		lblNrTelefon.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNrTelefon.setBounds(527, 390, 138, 25);
		lblNrTelefon.setForeground(new Color(0, 255, 255));
		contentPanel.add(lblNrTelefon);

		txtNrTelefon = new JTextField();
		txtNrTelefon.setBounds(675, 390, 397, 25);
		txtNrTelefon.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		contentPanel.add(txtNrTelefon);

		lblAdresa.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblAdresa.setBounds(527, 440, 138, 25);
		lblAdresa.setForeground(new Color(0, 255, 255));
		contentPanel.add(lblAdresa);

		txtAdresa = new JTextField();
		txtAdresa.setBounds(675, 440, 397, 25);
		txtAdresa.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		contentPanel.add(txtAdresa);

		lblTip.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTip.setBounds(527, 490, 100, 25);
		lblTip.setForeground(new Color(0, 255, 255));
		contentPanel.add(lblTip);

		JComboBox<String> tipuriAngajati = new JComboBox<>();
		tipuriAngajati.addItem("Select your role type");
		tipuriAngajati.addItem("Administrator");
		tipuriAngajati.addItem("Employee");
		tipuriAngajati.addItem("Manager");

		tipuriAngajati.setSelectedItem("Select role");
		tipuriAngajati.setBounds(675, 490, 397, 25);
		tipuriAngajati.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		tipuriAngajati.setBackground(Color.WHITE);
		tipuriAngajati.setForeground(new Color(0, 0, 0));
		tipuriAngajati.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
		tipuriAngajati.setOpaque(true);

		tipuriAngajati.setRenderer(new DefaultListCellRenderer() {
		    private static final long serialVersionUID = 1L;

		    @Override
		    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		        Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

		        if (isSelected) {
		            c.setBackground(new Color(222, 98, 216)); 
		            c.setForeground(Color.BLACK);
		        } else {
		            c.setBackground(Color.WHITE);
		            c.setForeground(Color.BLACK);
		        }

		        return c;
		    }
		});

		tipuriAngajati.setUI(new BasicComboBoxUI() {
		    @Override
		    protected ComboPopup createPopup() {
		        ComboPopup popup = super.createPopup();
		        popup.getList().setSelectionBackground(Color.WHITE);  
		        popup.getList().setSelectionForeground(Color.BLACK); 
		        return popup;
		    }
		});

		contentPanel.add(tipuriAngajati);

		JLabel lblTip = new JLabel("Role:");
		lblTip.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTip.setBounds(527, 490, 100, 25);
		lblTip.setForeground(new Color(0, 255, 255));
		contentPanel.add(lblTip);


		btnAdauga.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(222, 98, 216), new Color(222, 98, 216), new Color(222, 98, 216), new Color(222, 98, 216)));
		btnAdauga.setFont(new Font("Times New Roman", Font.PLAIN, 20));        
		btnAdauga.setForeground(Color.BLACK);
		btnAdauga.setBackground(new Color(0, 255, 255)); 
		btnAdauga.setFocusPainted(false);
		btnAdauga.setBounds(660, 620, 200, 40); 
		btnAdauga.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {        			
		    	 String nume = txtNume.getText();  
		         String job = txtJob.getText(); 
		         double salariu = Double.parseDouble(txtSalariu.getText()); 
		         String email = txtEmail.getText();   
		         String managerIdText = txtManagerId.getText();
		         Integer managerId =-1; 
		         
		         if (managerIdText != null && !managerIdText.isEmpty()) {
		             try {
		                 managerId = Integer.parseInt(managerIdText);
		             } catch (NumberFormatException ex) {
		                 JOptionPane.showMessageDialog(contentPanel, "Invalid manager ID.");
		             }
		         }String nrTelefon = txtNrTelefon.getText(); 
		         String adresa = txtAdresa.getText();  
		         String rolSelectat = (String) tipuriAngajati.getSelectedItem();
 
		         int ok=0;
		         if(isValidEmail(email)==true) ok=1;
		         if(ok==0) JOptionPane.showMessageDialog(contentPanel, "Please enter a valid email address.");
		            
		         if(nrTelefon.length()==10) ok++; 
		         else {
		        	 JOptionPane.showMessageDialog(contentPanel, "Please enter a valid phone number.");
			     }
		           
		          if(ok==2){
		        	Angajat a = new Angajat(0, nume, job, email, adresa, salariu, nrTelefon, Date.valueOf(LocalDate.now()), managerId);
		            GestionareAngajati.inserareAngajatNou(a, rolSelectat); 
		           
		       
		      
		          }
		          
		    }
		});
		 
		ImageIcon originalIcon3 = new ImageIcon(getClass().getResource("/Fotografii/exclamation.png"));
	    Image img3 = originalIcon3.getImage(); 
	    Image resizedImg3 = img3.getScaledInstance(32, 32, Image.SCALE_SMOOTH);
	    ImageIcon resizedIcon3=new ImageIcon(resizedImg3); 
	    UIManager.put("OptionPane.informationIcon", resizedIcon3);
	    UIManager.put("Button.border", BorderFactory.createLineBorder(Color.WHITE, 3));  
        UIManager.put("Button.select", new Color(222, 98, 216));
        UIManager.put("OptionPane.background", new Color(222, 98, 216)); 
        UIManager.put("Panel.background", new Color(222, 98, 216)); 
        UIManager.put("Button.background", new Color(0, 255, 255)); 
        UIManager.put("Button.foreground", new Color(0, 0, 0)); 
        UIManager.put("Button.pressed", Color.WHITE);  
		contentPanel.add(btnAdauga);

		setContentPane(contentPanel);
	}
	 
	public static boolean isValidEmail(String email) {
	       String emailRegex="^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
	         
	       Pattern p=Pattern.compile(emailRegex);

	       return email!=null && p.matcher(email).matches();
	    }

}
