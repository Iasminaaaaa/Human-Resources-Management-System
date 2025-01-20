package gui.manager;

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

public class EchipeManager extends JDialog {

	private static final long serialVersionUID=1L;
	private JPanel contentPane;
    private JTable table;
    JTable secondTable;
    JLabel titleLabel;
    JLabel titleLabelTeam;
    JScrollPane scrollPane;
    JLabel separatorLabel;
    DefaultTableModel modelSecondTable;
    JScrollPane scrollSecondTable;
    DefaultTableModel model;
    
    public EchipeManager(int idAngajat) {
        contentPane=new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);

        setIconImage(Toolkit.getDefaultToolkit().getImage(DetaliiPersonaleAngajat.class.getResource("/Fotografii/pic10.png")));
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize);
        
        contentPane.setBackground(new Color(36, 30, 30));

        titleLabel=new JLabel("My Teams");
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 50));
        titleLabel.setForeground(new Color(222, 98, 216));
        titleLabel.setBounds(572, 20, 500, 58);
        contentPane.add(titleLabel);
    
        titleLabelTeam=new JLabel("Managerial Team");
        titleLabelTeam.setFont(new Font("Times New Roman", Font.BOLD, 30));
        titleLabelTeam.setForeground(new Color(222, 98, 216));
        titleLabelTeam.setBounds(100, 100, 400, 40);
        contentPane.add(titleLabelTeam);
        
        int idManager=GestionareAngajati.getManagerId(idAngajat);
        Angajat manager=GestionareAngajati.detaliiAngajat(idManager);
        List<Angajat> membriiEchipa=GestionareAngajati.infoEchipa(idManager);
        
        model=new DefaultTableModel();
        model.addColumn("Nume");
        model.addColumn("Job");
        model.addColumn("Email");
        model.addColumn("Telefon");

        if (manager != null) {
            model.addRow(new Object[] {
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

        table=new JTable(model);
        table.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        table.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 19));
        table.getTableHeader().setBackground(new Color(0, 255, 255));
        table.getTableHeader().setForeground(Color.BLACK);
        table.setRowHeight(25);

        scrollPane=new JScrollPane(table);
        scrollPane.setBounds(35, 152, 1442, 270); 
        contentPane.add(scrollPane);

        separatorLabel=new JLabel("Direct Subordinates Team");
        separatorLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
        separatorLabel.setForeground(new Color(222, 98, 216));
        separatorLabel.setBounds(100, 440, 1442, 40); 
        contentPane.add(separatorLabel);

        modelSecondTable=new DefaultTableModel();
        modelSecondTable.addColumn("Nume");
        modelSecondTable.addColumn("Job");
        modelSecondTable.addColumn("Email");
        modelSecondTable.addColumn("Telefon");

       Angajat angj=GestionareAngajati.detaliiAngajat(idAngajat);
       membriiEchipa=GestionareAngajati.infoEchipa(idAngajat);
       if (angj != null) {
           modelSecondTable.addRow(new Object[] {
               angj.getNume(),
               angj.getJob(),
               angj.getEmail(),
               angj.getNrTelefon()
           });
       }
        for (Angajat angajat : membriiEchipa) {
            modelSecondTable.addRow(new Object[] {
                angajat.getNume(),
                angajat.getJob(),
                angajat.getEmail(),
                angajat.getNrTelefon()
            });
        }
       

        secondTable=new JTable(modelSecondTable);
        secondTable.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        secondTable.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 19));
        secondTable.getTableHeader().setBackground(new Color(0, 255, 255));
        secondTable.getTableHeader().setForeground(Color.BLACK);
        secondTable.setRowHeight(25);

        scrollSecondTable=new JScrollPane(secondTable);
        scrollSecondTable.setBounds(35, 480, 1442, 270); 
        contentPane.add(scrollSecondTable);
    }
}
