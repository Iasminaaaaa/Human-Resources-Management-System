package gui;

import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import controller.GestionareAngajati;
import model.Angajat;

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Component;
import javax.swing.SwingConstants;

public class DetaliiPersonaleAngajat extends JDialog {

	private static final long serialVersionUID=1L;
	private final JPanel contentPane=new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DetaliiPersonaleAngajat dialog=new DetaliiPersonaleAngajat(1);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DetaliiPersonaleAngajat(int idAngajat) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(DetaliiPersonaleAngajat.class.getResource("/Fotografii/pic10.png")));
		contentPane.setLayout(new FlowLayout());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		        
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize);

		        JPanel contentPane=new JPanel();
		        contentPane.setBackground(new Color(36, 30, 30));
		        
		        JLabel lblDetalii=new JLabel("Personal Details");
		        lblDetalii.setHorizontalAlignment(SwingConstants.CENTER);
		        lblDetalii.setFont(new Font("Times New Roman", Font.BOLD, 50));
		        lblDetalii.setForeground(new Color(222, 98, 216));

		        Angajat a=GestionareAngajati.detaliiAngajat(idAngajat);

		        JLabel lblNume=new JLabel("Name: " + a.getNume());
		        lblNume.setHorizontalAlignment(SwingConstants.CENTER);
		        lblNume.setAlignmentX(Component.CENTER_ALIGNMENT);
		        lblNume.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		        lblNume.setForeground(Color.WHITE);

		        JLabel lblJob=new JLabel("Job: " + a.getJob());
		        lblJob.setHorizontalAlignment(SwingConstants.CENTER);
		        lblJob.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		        lblJob.setForeground(Color.WHITE);

		        JLabel lblSalariu=new JLabel("Salary: " + a.getSalariu());
		        lblSalariu.setHorizontalAlignment(SwingConstants.CENTER);
		        lblSalariu.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		        lblSalariu.setForeground(Color.WHITE);

		        JLabel lblEmail=new JLabel("Email: " + a.getEmail());
		        lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		        lblEmail.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		        lblEmail.setForeground(Color.WHITE);

		        JLabel lblData=new JLabel("Hiring Date: " + a.getData());
		        lblData.setHorizontalAlignment(SwingConstants.CENTER);
		        lblData.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		        lblData.setForeground(Color.WHITE);

		        JLabel lblManagerId=new JLabel("Manager ID: " + a.getManagerId());
		        lblManagerId.setHorizontalAlignment(SwingConstants.CENTER);
		        lblManagerId.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		        lblManagerId.setForeground(Color.WHITE);

		        JLabel lblNrTelefon=new JLabel("Phone number: " + a.getNrTelefon());
		        lblNrTelefon.setHorizontalAlignment(SwingConstants.CENTER);
		        lblNrTelefon.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		        lblNrTelefon.setForeground(Color.WHITE);

		        JLabel lblAdresa=new JLabel("Address: " + a.getAdresa());
		        lblAdresa.setHorizontalAlignment(SwingConstants.CENTER);
		        lblAdresa.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		        lblAdresa.setForeground(Color.WHITE);

		        setContentPane(contentPane);
		        GroupLayout gl_contentPane=new GroupLayout(contentPane);
		        gl_contentPane.setHorizontalGroup(
		        	gl_contentPane.createParallelGroup(Alignment.LEADING)
		        		.addGroup(gl_contentPane.createSequentialGroup()
		        			.addGap(558)
		        			.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
		        				.addComponent(lblDetalii, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
		        				.addComponent(lblAdresa, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
		        				.addComponent(lblNrTelefon, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
		        				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
		        					.addComponent(lblData, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
		        					.addComponent(lblEmail, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
		        					.addComponent(lblSalariu, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
		        					.addComponent(lblJob, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
		        					.addComponent(lblNume, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
		        					.addComponent(lblManagerId, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
		        			.addContainerGap(582, Short.MAX_VALUE))
		        );
		        gl_contentPane.setVerticalGroup(
		        	gl_contentPane.createParallelGroup(Alignment.LEADING)
		        		.addGroup(gl_contentPane.createSequentialGroup()
		        			.addGap(24)
		        			.addComponent(lblDetalii, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
		        			.addGap(74)
		        			.addComponent(lblNume)
		        			.addGap(18)
		        			.addComponent(lblJob)
		        			.addGap(18)
		        			.addComponent(lblSalariu)
		        			.addGap(18)
		        			.addComponent(lblEmail)
		        			.addGap(18)
		        			.addComponent(lblData)
		        			.addGap(18)
		        			.addComponent(lblManagerId)
		        			.addGap(18)
		        			.addComponent(lblNrTelefon)
		        			.addGap(18)
		        			.addComponent(lblAdresa)
		        			.addContainerGap(313, Short.MAX_VALUE))
		        );
		        contentPane.setLayout(gl_contentPane);
		    }
}
	
