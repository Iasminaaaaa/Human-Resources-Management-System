package gui.angajati;

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
import gui.AdaugareCereri;
import gui.DetaliiPersonaleAngajat;
import model.ExceptieAplicatie;

import javax.swing.UIManager;
import javax.swing.SwingConstants;

public class InterfataAngajati extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane = new JPanel();;
	private static JLabel lblTitle;
	private JLabel lblNameMessage;
	private final JLabel label = new JLabel("");
	private final JButton btnEchipa = new JButton("My Team");
	   
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfataAngajati frame = new InterfataAngajati(1);
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
	public InterfataAngajati(int idAngajat) throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setResizable(false); 
		 setTitle("Employees Dashboard"); 
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
		
		lblTitle = new JLabel("Employees Dashboard");
	    lblTitle.setBounds(480, 20, 600, 58);
	    lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 50));
	    lblTitle.setForeground(new Color(222, 98, 216));
        lblTitle.setVisible(true);
			
		lblNameMessage=new JLabel(GestionareAngajati.getNumeAngajat(idAngajat));
		lblNameMessage.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNameMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblNameMessage.setBounds(440, 468, 600, 35);
		lblNameMessage.setFont(new Font("Times New Roman", Font.HANGING_BASELINE, 30)); 
		lblNameMessage.setForeground(Color.WHITE); 
		
		btnEchipa.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(222, 98, 216), new Color(222, 98, 216), new Color(222, 98, 216), new Color(222, 98, 216)));
	    btnEchipa.setFont(new Font("Times New Roman", Font.PLAIN, 20));        
	    btnEchipa.setForeground(Color.BLACK);
	    btnEchipa.setBackground(new Color(0, 255, 255)); 
	    btnEchipa.setFocusPainted(false);
	    btnEchipa.setBounds(275, 210, 200, 40);
	   
	    btnEchipa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {        			
            	VizualizareEchipa dialog = new VizualizareEchipa(idAngajat);
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

        JButton btnVizualizareCereri = new JButton("View requests");
        btnVizualizareCereri.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnVizualizareCereri.setBounds(275, 592, 200, 40);
        btnVizualizareCereri.setBackground(new Color(0, 255, 255));
        btnVizualizareCereri.setForeground(Color.BLACK);
        btnVizualizareCereri.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(222, 98, 216), new Color(222, 98, 216), new Color(222, 98, 216), new Color(222, 98, 216)));
        btnVizualizareCereri.setFocusPainted(false);
        btnVizualizareCereri.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	VizualizareCereriPerAngajat dialog;
				try {
					dialog = new VizualizareCereriPerAngajat(idAngajat);
					  dialog.setVisible(true);
				} catch (ExceptieAplicatie e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                
            }
        });
        contentPane.add(btnVizualizareCereri);

        JButton btnAdaugareCerere = new JButton("Add request");
        btnAdaugareCerere.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnAdaugareCerere.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(222, 98, 216), new Color(222, 98, 216), new Color(222, 98, 216), new Color(222, 98, 216)));
        btnAdaugareCerere.setBounds(990, 592, 200, 40);
        btnAdaugareCerere.setBackground(new Color(0, 255, 255));
        btnAdaugareCerere.setForeground(Color.BLACK);
        btnAdaugareCerere.setFocusPainted(false);
        btnAdaugareCerere.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
              AdaugareCereri dialog = new AdaugareCereri(idAngajat);
			 dialog.setVisible(true);
           }
        });
        contentPane.add(btnAdaugareCerere);

        JButton btnGenerareRapoarte = new JButton("My Reports");
        btnGenerareRapoarte.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnGenerareRapoarte.setBounds(990, 210, 200, 40);
        btnGenerareRapoarte.setBackground(new Color(0, 255, 255));
        btnGenerareRapoarte.setForeground(Color.BLACK);
        btnGenerareRapoarte.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(222, 98, 216), new Color(222, 98, 216), new Color(222, 98, 216), new Color(222, 98, 216)));
        btnGenerareRapoarte.setFocusPainted(false);
        btnGenerareRapoarte.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
             	  VizualizareRapoartePerAngajat dialog = new VizualizareRapoartePerAngajat(idAngajat);
                  dialog.setVisible(true);
         
            }
        });
        contentPane.add(btnGenerareRapoarte);

	    setContentPane(contentPane);
	    contentPane.add(btnEchipa);
		contentPane.add(label);
		contentPane.add(lblTitle);
		contentPane.add(lblNameMessage);
	}

}
