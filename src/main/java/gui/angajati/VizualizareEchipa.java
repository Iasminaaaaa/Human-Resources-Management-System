package gui.angajati;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.GestionareAngajati;
import gui.DetaliiPersonaleAngajat;
import model.Angajat;

public class VizualizareEchipa extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JTable table;

    public VizualizareEchipa(int idAngajat) {
        contentPane = new JPanel();
        contentPane.setLayout(null); 
        setContentPane(contentPane);

        setIconImage(Toolkit.getDefaultToolkit().getImage(DetaliiPersonaleAngajat.class.getResource("/Fotografii/pic10.png")));
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize);
        
        contentPane.setBackground(new Color(36, 30, 30));

        JLabel titleLabel = new JLabel("My Team");
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 50));
        titleLabel.setForeground(new Color(222, 98, 216));
        titleLabel.setBounds(572, 20, 500, 58); 
        contentPane.add(titleLabel);

        int idManager = GestionareAngajati.getManagerId(idAngajat);
        Angajat manager = GestionareAngajati.detaliiAngajat(idManager);
        List<Angajat> membriiEchipa = GestionareAngajati.infoEchipa(idManager);
        
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nume");
        model.addColumn("Job");
        model.addColumn("Email");
        model.addColumn("Telefon");

        if (manager != null) {
            model.addRow(new Object[]{
                manager.getNume(),
                manager.getJob(),
                manager.getEmail(),
                manager.getNrTelefon()
            });
        }

        for (Angajat angajat : membriiEchipa) {
            model.addRow(new Object[] {
                angajat.getNume(),
                angajat.getJob(),
                angajat.getEmail(),
                angajat.getNrTelefon()
            });
        }

        table = new JTable(model);
        table.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        table.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 19));
        table.getTableHeader().setBackground(new Color(0, 255, 255));
        table.getTableHeader().setForeground(Color.BLACK);
        table.setRowHeight(25);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(35, 152, 1442, 540); 
        contentPane.add(scrollPane); 
    }
}
