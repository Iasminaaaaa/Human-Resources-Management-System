package gui.administrator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.time.LocalDate;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import controller.GestionareAngajati;
import gui.DetaliiPersonaleAngajat;
import model.Angajat;

public class VizualizareDetaliiAngajatAdministrator extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPane = new JPanel();

    public static void main(String[] args) {
        try {
            VizualizareDetaliiAngajatAdministrator dialog=new VizualizareDetaliiAngajatAdministrator(1);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public VizualizareDetaliiAngajatAdministrator(int idAngajat) {
        setIconImage(Toolkit.getDefaultToolkit().getImage(DetaliiPersonaleAngajat.class.getResource("/Fotografii/pic10.png")));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize);
        contentPane.setBackground(new Color(36, 30, 30));
        contentPane.setLayout(null);

        JLabel lblDetalii=new JLabel("Info Employee");
        lblDetalii.setFont(new Font("Times New Roman", Font.BOLD, 50));
        lblDetalii.setForeground(new Color(222, 98, 216));
        lblDetalii.setBounds(580, 20, 400, 55);
        contentPane.add(lblDetalii);

        Angajat a=GestionareAngajati.detaliiAngajat(idAngajat);

        ImageIcon originalIcon2=new ImageIcon(getClass().getResource("/Fotografii/question.png"));
        Image img2=originalIcon2.getImage(); 
        Image resizedImg2=img2.getScaledInstance(32, 32, Image.SCALE_SMOOTH); 
        ImageIcon resizedIcon2=new ImageIcon(resizedImg2); 
        
        JLabel lblNume=new JLabel("Name: "+a.getNume());
        lblNume.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNume.setForeground(Color.WHITE);
        lblNume.setBounds(460, 140, 400, 30);
        contentPane.add(lblNume);

        JButton btnEditNume=new JButton("Edit");
        btnEditNume.setBounds(900, 138, 80, 25);
        btnEditNume.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	 String numeNou = (String) JOptionPane.showInputDialog(contentPane, "Please enter new name:", "Update Name", JOptionPane.INFORMATION_MESSAGE, resizedIcon2, null, "");  
   
            	 if(GestionareAngajati.actualizareAngajat("nume", numeNou, idAngajat)==1) 
                        lblNume.setText("Name: "+numeNou);
                }
        });
        contentPane.add(btnEditNume);

        JLabel lblJob = new JLabel("Job: " + a.getJob());
        lblJob.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblJob.setForeground(Color.WHITE);
        lblJob.setBounds(460, 185, 400, 30);
        contentPane.add(lblJob);

        JLabel lblData = new JLabel("Hiring Date: " + a.getData());
        lblData.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblData.setForeground(Color.WHITE);
        lblData.setBounds(460, 425, 400, 30);
        contentPane.add(lblData);
        
                
        JButton btnEditJob = new JButton("Edit");
        btnEditJob.setBounds(900, 178, 80, 25);
        btnEditJob.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String jobNou = (String) JOptionPane.showInputDialog(contentPane, "Please enter new job: ", "Update Job", JOptionPane.INFORMATION_MESSAGE, resizedIcon2, null, "");
                   if (GestionareAngajati.actualizareAngajat("job", jobNou, idAngajat) == 1) {
                        lblJob.setText("Job: " + jobNou);
                        lblData.setText("Hiring Date: "+Date.valueOf(LocalDate.now()));
                    }
                }
        });
        contentPane.add(btnEditJob);

        JLabel lblSalariu = new JLabel("Salary: " + a.getSalariu());
        lblSalariu.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblSalariu.setForeground(Color.WHITE);
        lblSalariu.setBounds(460, 225, 400, 30);
        contentPane.add(lblSalariu);

        JButton btnEditSalariu = new JButton("Edit");
        btnEditSalariu.setBounds(900, 218, 80, 25);
        btnEditSalariu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String salariuNou=(String) JOptionPane.showInputDialog(null,"Please enter new salary","Insert salary", JOptionPane.INFORMATION_MESSAGE, resizedIcon2, null, "");      
                
                if(GestionareAngajati.actualizareAngajat("salariu", salariuNou, idAngajat)==1){
                    
                	lblSalariu.setText("Salary: "+salariuNou);
                    }
            }
        });
        contentPane.add(btnEditSalariu);

        JLabel lblEmail = new JLabel("Email: " + a.getEmail());
        lblEmail.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblEmail.setForeground(Color.WHITE);
        lblEmail.setBounds(460, 265, 400, 30);
        contentPane.add(lblEmail);

        JButton btnEditEmail = new JButton("Edit");
        btnEditEmail.setBounds(900, 258, 80, 25);
        btnEditEmail.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String emailNou = (String) JOptionPane.showInputDialog(null,"Please enter new email","Insert email", JOptionPane.INFORMATION_MESSAGE, resizedIcon2, null, "");      
                if(isValidEmail(emailNou)) {
                    if(GestionareAngajati.actualizareAngajat("email", emailNou, idAngajat)==1){
                        lblEmail.setText("Email: " + emailNou);
                    }
                }else
                    JOptionPane.showMessageDialog(contentPane, "Please enter a valid email address.");
            }
        });
        contentPane.add(btnEditEmail);

        JLabel lblManagerId = new JLabel("Manager ID: " + a.getManagerId());
        lblManagerId.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblManagerId.setForeground(Color.WHITE);
        lblManagerId.setBounds(460, 345, 300, 30);
        contentPane.add(lblManagerId);

        JButton btnEditManagerId = new JButton("Edit");
        btnEditManagerId.setBounds(900, 338, 80, 25);
        btnEditManagerId.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String managerIdNou=(String) JOptionPane.showInputDialog(contentPane, "Please enter new manager id:", "Update Manager ID", JOptionPane.INFORMATION_MESSAGE, resizedIcon2, null, "");
                   if (GestionareAngajati.actualizareAngajat("manager_id", managerIdNou, idAngajat)==1){
                        lblManagerId.setText("Manager ID: "+managerIdNou);
                }
            }
        });
        contentPane.add(btnEditManagerId);

        JLabel lblNrTelefon = new JLabel("Phone number: " + a.getNrTelefon());
        lblNrTelefon.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNrTelefon.setForeground(Color.WHITE);
        lblNrTelefon.setBounds(460, 385, 400, 30);
        contentPane.add(lblNrTelefon);

        JButton btnEditNrTelefon = new JButton("Edit");
        btnEditNrTelefon.setBounds(900, 378, 80, 25);
        btnEditNrTelefon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nrTelefonNou=(String) JOptionPane.showInputDialog(contentPane, "Please enter new phone number:", "Update Phone Number", JOptionPane.INFORMATION_MESSAGE, resizedIcon2, null, "");
                if (GestionareAngajati.actualizareAngajat("numar_telefon", nrTelefonNou, idAngajat) == 1) {
                        lblNrTelefon.setText("Phone number: " + nrTelefonNou);
                }
            }
        });
        contentPane.add(btnEditNrTelefon);

        JLabel lblAdresa = new JLabel("Address: " + a.getAdresa());
        lblAdresa.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblAdresa.setForeground(Color.WHITE);
        lblAdresa.setBounds(460, 305, 400, 30);
        contentPane.add(lblAdresa);

        JButton btnEditAdresa = new JButton("Edit");
        btnEditAdresa.setBounds(900, 298, 80, 25);
        btnEditAdresa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String adresaNoua = (String) JOptionPane.showInputDialog(contentPane, "Please enter new address:", "Update Address", JOptionPane.INFORMATION_MESSAGE, resizedIcon2, null, "");
                if (GestionareAngajati.actualizareAngajat("adresa", adresaNoua, idAngajat)==1) {
                      lblAdresa.setText("Address: " + adresaNoua);
               }
            }
        });
        
        contentPane.add(btnEditAdresa);

        btnEditAdresa.setBackground(new Color(0, 255, 255)); 
        btnEditEmail.setBackground(new Color(0, 255, 255)); 
        btnEditJob.setBackground(new Color(0, 255, 255)); 
        btnEditManagerId.setBackground(new Color(0, 255, 255)); 
        btnEditNrTelefon.setBackground(new Color(0, 255, 255)); 
        btnEditNume.setBackground(new Color(0, 255, 255)); 
        btnEditSalariu.setBackground(new Color(0, 255, 255)); 

        
        JButton btnVizualizareModificari = new JButton("History of Changes");
        btnVizualizareModificari.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnVizualizareModificari.setBounds(460, 504, 520, 30);
     	btnVizualizareModificari.setBackground(new Color(222, 98, 216));
        btnVizualizareModificari.setForeground(Color.BLACK);
        btnVizualizareModificari.setFocusPainted(false);
        UIManager.put("Button.select", new Color(0,255,255));
        btnVizualizareModificari.addChangeListener(e -> {
            if (btnVizualizareModificari.getModel().isPressed()) {
            	btnVizualizareModificari.setBackground(new Color(0, 255, 255)); 
            } else if (btnVizualizareModificari.getModel().isRollover()) {
                btnVizualizareModificari.setBorder(BorderFactory.createLineBorder(Color.cyan, 2));
              
            } else {
             btnVizualizareModificari.setBorder(new MatteBorder(1, 1, 1, 1, new Color(222, 98, 216)));
            	btnVizualizareModificari.setBackground(new Color(222, 98, 216)); 
            }
        });

        btnVizualizareModificari.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	 if (e.getClickCount() == 1) { 
	                      try {
	                        IstoricModificariAngajat dialog = new IstoricModificariAngajat(idAngajat);
	                        dialog.setModal(true);;
	                        dialog.setVisible(true);
	                        
	                    } catch (Exception ex) {
	                        ex.printStackTrace();
	                    }
	            }
            }
        });
        contentPane.add(btnVizualizareModificari);
        
        ImageIcon originalIcon1 = new ImageIcon(getClass().getResource("/Fotografii/error.png"));
        Image img1 = originalIcon1.getImage(); 
        Image resizedImg1 = img1.getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon1=new ImageIcon(resizedImg1);
        UIManager.put("OptionPane.errorIcon", resizedIcon1);

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
        setContentPane(contentPane);
   
        JButton stergereAngajatBtn = new JButton("Delete Employee");

        stergereAngajatBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmare = JOptionPane.showConfirmDialog(null, 
                    "Are you sure you want to delete this employee?", 
                    "Confirm Deletion", 
                    JOptionPane.YES_NO_OPTION
                    );
                
                if (confirmare == JOptionPane.YES_OPTION) {
                    try {
                        GestionareAngajati.stergereAngajat(idAngajat);    
                        JOptionPane.showMessageDialog(null, "Angajatul a fost șters cu succes!");
                        dispose(); 
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Eroare la ștergerea angajatului: " + ex.getMessage());
                    }
                    
                }
            }
        });
        UIManager.put("OptionPane.questionIcon", resizedIcon2);

       stergereAngajatBtn.setFont(new Font("Times New Roman", Font.PLAIN, 20));
       stergereAngajatBtn.setBounds(623, 787, 200, 30);
       contentPane.add(stergereAngajatBtn);

    }

    public static boolean isValidEmail(String email) {
       String emailRegex="^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@"+"(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

       Pattern p=Pattern.compile(emailRegex);

       return email!=null && p.matcher(email).matches();
    }
}
