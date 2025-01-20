package gui.administrator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import controller.GestionareAngajati;
import gui.DetaliiPersonaleAngajat;
import gui.GenerareRaport;
import gui.angajati.InterfataAngajati;
import model.ExceptieAplicatie;

import javax.swing.UIManager;
import javax.swing.SwingConstants;

public class InterfataAdministrator extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane = new JPanel();;
	private static JLabel lblTitle;
	private JLabel lblNameMessage;
	private final JLabel label = new JLabel("");
	private final JButton btnDetaliiPersonale = new JButton("Employees List");
	private final JButton btnAdaugaAngajat = new JButton("Add Employee");
	   
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfataAdministrator frame = new InterfataAdministrator(1);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public InterfataAdministrator(int idAngajat) throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setResizable(false); 
	    setTitle("Administrator Dashboard");
	    contentPane.setLayout(null);
		contentPane.setBackground(new Color(36, 30, 30));
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize);
		label.setIcon(new ImageIcon(InterfataAngajati.class.getResource("/Fotografii/little.png")));
		label.setBounds(410, 10, 695, 695);
		label.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {        			
      			DetaliiPersonaleAngajat dialog = new DetaliiPersonaleAngajat(idAngajat);
			    dialog.setVisible(true);
	            }
	        });
		
		lblTitle = new JLabel("Administrator Dashboard");
	    lblTitle.setBounds(450, 40, 600, 58);
	    lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 50));
	    lblTitle.setForeground(new Color(222, 98, 216));
        lblTitle.setVisible(true);
			
		lblNameMessage=new JLabel(GestionareAngajati.getNumeAngajat(idAngajat));
		lblNameMessage.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNameMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblNameMessage.setBounds(440, 468, 600, 35);
		lblNameMessage.setFont(new Font("Times New Roman", Font.HANGING_BASELINE, 30)); 
		lblNameMessage.setForeground(Color.WHITE); 
		
		btnDetaliiPersonale.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(222, 98, 216), new Color(222, 98, 216), new Color(222, 98, 216), new Color(222, 98, 216)));
	    btnDetaliiPersonale.setFont(new Font("Times New Roman", Font.PLAIN, 20));        
	    btnDetaliiPersonale.setForeground(Color.BLACK);
	    btnDetaliiPersonale.setBackground(new Color(0, 255, 255)); 
	    btnDetaliiPersonale.setFocusPainted(false);
	    btnDetaliiPersonale.setBounds(275, 210, 200, 40);
	    
	    btnDetaliiPersonale.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {        			
            	VizualizareAngajati dialog = new VizualizareAngajati();
        	    dialog.setVisible(true);
            }
        });
	    
	    UIManager.put("Button.select", new Color(4,105,155));
	       
	    label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {        			
    			DetaliiPersonaleAngajat dialog = new DetaliiPersonaleAngajat(idAngajat);
			    dialog.setVisible(true);
            }
        });
	    
		 
	    btnAdaugaAngajat.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(222, 98, 216), new Color(222, 98, 216), new Color(222, 98, 216), new Color(222, 98, 216)));
	    btnAdaugaAngajat.setFont(new Font("Times New Roman", Font.PLAIN, 20));        
	    btnAdaugaAngajat.setForeground(Color.BLACK);
	    btnAdaugaAngajat.setBackground(new Color(0, 255, 255)); 
	    btnAdaugaAngajat.setFocusPainted(false);
	    btnAdaugaAngajat.setBounds(77, 402, 200, 40);
	    
	    btnAdaugaAngajat.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {        			
            	AdaugareAngajati dialog = new AdaugareAngajati();
        	    dialog.setVisible(true);
            }
        });
	    
	    JButton btnVizualizareCereri = new JButton("View Requests");
	    btnVizualizareCereri.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(222, 98, 216), new Color(222, 98, 216), new Color(222, 98, 216), new Color(222, 98, 216)));
	    btnVizualizareCereri.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnVizualizareCereri.setBounds(1220, 402, 200, 40);
        btnVizualizareCereri.setBackground(new Color(0, 255, 255));
        btnVizualizareCereri.setForeground(Color.BLACK);
        btnVizualizareCereri.setFocusPainted(false);
        btnVizualizareCereri.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                VizualizareCereri dialog;
				try {
					dialog = new VizualizareCereri(idAngajat);
					dialog.setVisible(true);
				} catch (ExceptieAplicatie e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                 }
        });
        contentPane.add(btnVizualizareCereri);

        JButton btnVizualizareArhivaAngajati = new JButton("Employee Archive");
        btnVizualizareArhivaAngajati.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnVizualizareArhivaAngajati.setBounds(275, 592, 200, 40);
        btnVizualizareArhivaAngajati.setBackground(new Color(0, 255, 255));
        btnVizualizareArhivaAngajati.setForeground(Color.BLACK);
        btnVizualizareArhivaAngajati.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(222, 98, 216), new Color(222, 98, 216), new Color(222, 98, 216), new Color(222, 98, 216)));
        btnVizualizareArhivaAngajati.setFocusPainted(false);
        btnVizualizareArhivaAngajati.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	  VizualizareAngajatiArhivati dialog = new VizualizareAngajatiArhivati();
                  dialog.setVisible(true);
            }
        });
        contentPane.add(btnVizualizareArhivaAngajati);

        JButton btnVizualizareArhivaCereri = new JButton("Request Archive");
        btnVizualizareArhivaCereri.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnVizualizareArhivaCereri.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(222, 98, 216), new Color(222, 98, 216), new Color(222, 98, 216), new Color(222, 98, 216)));
        btnVizualizareArhivaCereri.setBounds(990, 592, 200, 40);
        btnVizualizareArhivaCereri.setBackground(new Color(0, 255, 255));
        btnVizualizareArhivaCereri.setForeground(Color.BLACK);
        btnVizualizareArhivaCereri.setFocusPainted(false);
        btnVizualizareArhivaCereri.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
              ArhivaCereri dialog;
			try {
				dialog = new ArhivaCereri(idAngajat);
				dialog.setVisible(true);
			} catch (ExceptieAplicatie e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
           }
        });
        contentPane.add(btnVizualizareArhivaCereri);

        JButton btnGenerareRapoarte = new JButton("Generate Raports");
        btnGenerareRapoarte.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnGenerareRapoarte.setBounds(990, 210, 200, 40);
        btnGenerareRapoarte.setBackground(new Color(0, 255, 255));
        btnGenerareRapoarte.setForeground(Color.BLACK);
        btnGenerareRapoarte.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(222, 98, 216), new Color(222, 98, 216), new Color(222, 98, 216), new Color(222, 98, 216)));
        btnGenerareRapoarte.setFocusPainted(false);
        btnGenerareRapoarte.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	GenerareRaport dialog = new GenerareRaport(idAngajat);
                dialog.setVisible(true); 
            }
        });
        contentPane.add(btnGenerareRapoarte);

        JButton btnVizualizareRapoarte = new JButton("View Performance Reports");
        btnVizualizareRapoarte.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnVizualizareRapoarte.setBounds(595, 725, 300, 40);
        btnVizualizareRapoarte.setBackground(new Color(0, 255, 255));
        btnVizualizareRapoarte.setForeground(Color.BLACK);
        btnVizualizareRapoarte.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(222, 98, 216), new Color(222, 98, 216), new Color(222, 98, 216), new Color(222, 98, 216)));
        btnVizualizareRapoarte.setFocusPainted(false);
        btnVizualizareRapoarte.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	VizualizareRapoarte dialog= new VizualizareRapoarte();
            	dialog.setVisible(true);
            }
        });
        contentPane.add(btnVizualizareRapoarte);
	    
	    
	    setContentPane(contentPane);
	    contentPane.add(btnAdaugaAngajat);
	    contentPane.add(btnDetaliiPersonale);
		contentPane.add(label);
		contentPane.add(lblTitle);
		contentPane.add(lblNameMessage);
	}

}
