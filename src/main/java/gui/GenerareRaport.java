package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.time.LocalDate;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import controller.GestionareRapoarte;
import model.RaportPerformanta;

public class GenerareRaport extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private final JTextArea txtComentarii;
	private final JTextArea txtScor;
	private final JLabel lblTitluRaport = new JLabel("Report Generation");
	private final JLabel lblComentarii = new JLabel("Comments:");
	private final JLabel lblScor = new JLabel("Score:");
	private final JButton btnGenerareRaport = new JButton("Generate Report");
	private final JLabel lblIdAngajat = new JLabel("Employee ID:");
	private final JTextArea txtIdAngajat;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GenerareRaport dialog = new GenerareRaport(1);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public GenerareRaport(int id) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GenerareRaport.class.getResource("/Fotografii/pic10.png")));
		contentPanel.setLayout(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(new Color(36, 30, 30));

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize);
		setTitle("Generate Report");

		lblTitluRaport.setFont(new Font("Times New Roman", Font.BOLD, 50));
		lblTitluRaport.setForeground(new Color(222, 98, 216));
		lblTitluRaport.setBounds(580, 20, 500, 58);
		contentPanel.add(lblTitluRaport);

		lblComentarii.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblComentarii.setBounds(513, 340, 100, 25);
		lblComentarii.setForeground(new Color(0, 255, 255));
		contentPanel.add(lblComentarii);

		txtComentarii = new JTextArea();
		txtComentarii.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtComentarii.setBounds(673, 208, 397, 325);
		txtComentarii.setLineWrap(true);
		txtComentarii.setWrapStyleWord(true);
		contentPanel.add(txtComentarii);

		lblScor.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblScor.setBounds(513, 569, 100, 25);
		lblScor.setForeground(new Color(0, 255, 255));
		contentPanel.add(lblScor);

		txtScor = new JTextArea();
		txtScor.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtScor.setBounds(673, 567, 397, 34);
		txtScor.setLineWrap(true);
		txtScor.setWrapStyleWord(true);
		contentPanel.add(txtScor);

		lblIdAngajat.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblIdAngajat.setForeground(new Color(0, 255, 255));
		lblIdAngajat.setBounds(513, 141, 150, 25);
		contentPanel.add(lblIdAngajat);

		txtIdAngajat = new JTextArea();
		txtIdAngajat.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtIdAngajat.setBounds(673, 140, 397, 34); 
		txtIdAngajat.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK)); 
		txtIdAngajat.setLineWrap(true);
		txtIdAngajat.setWrapStyleWord(true);

		contentPanel.add(txtIdAngajat);
		
		btnGenerareRaport.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(222, 98, 216), new Color(222, 98, 216), new Color(222, 98, 216), new Color(222, 98, 216)));
		btnGenerareRaport.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnGenerareRaport.setForeground(Color.BLACK);
		btnGenerareRaport.setBackground(new Color(0, 255, 255));
		btnGenerareRaport.setFocusPainted(false);
		btnGenerareRaport.setBounds(660, 673, 200, 40);
		btnGenerareRaport.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String idAngajat=txtIdAngajat.getText();
				String comentarii = txtComentarii.getText();
				String scor = txtScor.getText();

				if (comentarii.isEmpty() || scor.isEmpty()) {
					JOptionPane.showMessageDialog(contentPanel, "Please fill in all fields.");
				} else {
					System.out.println(id);
					JOptionPane.showMessageDialog(contentPanel, "Report generated successfully!");
					GestionareRapoarte.inserareRaportNou(new RaportPerformanta(0, comentarii, Integer.parseInt(scor), Date.valueOf(LocalDate.now()), Integer.parseInt(idAngajat), id));
				}
			}
		});

		ImageIcon originalIcon3 = new ImageIcon(getClass().getResource("/Fotografii/exclamation.png"));
		Image img3 = originalIcon3.getImage();
		Image resizedImg3 = img3.getScaledInstance(32, 32, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon3 = new ImageIcon(resizedImg3);
		UIManager.put("OptionPane.informationIcon", resizedIcon3);
		UIManager.put("Button.border", BorderFactory.createLineBorder(Color.WHITE, 3));
		UIManager.put("Button.select", new Color(222, 98, 216));
		UIManager.put("OptionPane.background", new Color(222, 98, 216));
		UIManager.put("Panel.background", new Color(222, 98, 216));
		UIManager.put("Button.background", new Color(0, 255, 255));
		UIManager.put("Button.foreground", new Color(0, 0, 0));
		UIManager.put("Button.pressed", Color.WHITE);
		contentPanel.add(btnGenerareRaport);

		setContentPane(contentPanel);
	}
}
