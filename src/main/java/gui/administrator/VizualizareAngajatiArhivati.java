package gui.administrator;

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
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.GestionareAngajati;
import gui.DetaliiPersonaleAngajat;
import model.AngajatArhivat;

public class VizualizareAngajatiArhivati extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTable table;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            VizualizareAngajatiArhivati dialog = new VizualizareAngajatiArhivati();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public VizualizareAngajatiArhivati() {
        setTitle("Employees Archive");
        setIconImage(Toolkit.getDefaultToolkit().getImage(DetaliiPersonaleAngajat.class.getResource("/Fotografii/pic10.png")));
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize);
        
        contentPanel.setBackground(new Color(36, 30, 30)); 
        contentPanel.setLayout(null); 
        setContentPane(contentPanel);
        
        JLabel lblTitlu = new JLabel("Employees Archive");
        lblTitlu.setFont(new Font("Times New Roman", Font.BOLD, 50));
        lblTitlu.setForeground(new Color(222, 98, 216));
        lblTitlu.setBounds(580, 20, 420, 58);
        contentPanel.add(lblTitlu);
        
        DefaultTableModel tableModel = new DefaultTableModel(new String[] {
            "Emp. ID", "Name", "Job", "Salary", "Hire D.", "Res. D.", "Phone No.", "Email", "Address", "Man. ID"
        }, 0);
      
        table = new JTable(tableModel);
        table.setBackground(new Color(238,238,238,255)); 
        table.setForeground(Color.BLACK);
        table.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        table.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 19));
        table.getTableHeader().setBackground(new Color(0, 255, 255));
        table.getTableHeader().setForeground(Color.BLACK);
        table.setRowHeight(25);
        table.getTableHeader().setPreferredSize(new Dimension(table.getColumnModel().getTotalColumnWidth(), 30)); 
        table.getColumnModel().getColumn(0).setPreferredWidth(5);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);
        table.getColumnModel().getColumn(3).setPreferredWidth(20);
        table.getColumnModel().getColumn(4).setPreferredWidth(5);
        table.getColumnModel().getColumn(5).setPreferredWidth(5);
        table.getColumnModel().getColumn(6).setPreferredWidth(30);
        table.getColumnModel().getColumn(7).setPreferredWidth(150);
        table.getColumnModel().getColumn(8).setPreferredWidth(150);
        table.getColumnModel().getColumn(9).setPreferredWidth(10);
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 140, screenSize.width - 80, screenSize.height - 300);
        
        contentPanel.add(scrollPane);

        List<AngajatArhivat> angajatiArhivati = GestionareAngajati.infoAngajatiArhivati();
        for (AngajatArhivat angajat : angajatiArhivati) {
            tableModel.addRow(new Object[]{
                angajat.getId(),
                angajat.getNume(),
                angajat.getJob(),
                angajat.getSalariu(),
                angajat.getData(),
                angajat.getDataDemisie(),
                angajat.getNrTelefon(),
                angajat.getEmail(),
                angajat.getAdresa(),
                angajat.getManagerId()
            });
        }
    }
}
