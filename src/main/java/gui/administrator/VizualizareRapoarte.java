package gui.administrator;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.GestionareRapoarte;  
import gui.DetaliiPersonaleAngajat;
import model.RaportPerformanta;

public class VizualizareRapoarte extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            VizualizareRapoarte dialog = new VizualizareRapoarte();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public VizualizareRapoarte() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(DetaliiPersonaleAngajat.class.getResource("/Fotografii/pic10.png")));
        contentPanel.setLayout(null);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPanel.setBackground(new Color(36, 30, 30));
       
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize);

      
        JLabel lblTitlu = new JLabel("Reports List");
        lblTitlu.setFont(new Font("Times New Roman", Font.BOLD, 50));
        lblTitlu.setForeground(new Color(222, 98, 216));
        lblTitlu.setBounds(580, 20, 400, 58);
      
        List<RaportPerformanta> reports = GestionareRapoarte.infoRapoarte();

        JList<RaportPerformanta> reportList = new JList<>(reports.toArray(new RaportPerformanta[0]));
        reportList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        reportList.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        reportList.setBackground(new Color(36, 30, 30)); 
        reportList.setForeground(Color.WHITE); 
        
        JScrollPane scrollPane = new JScrollPane(reportList);
        scrollPane.setBounds(294, 130, 890, 600);
        scrollPane.setBackground(new Color(36, 30, 30));
        
        reportList.setCellRenderer(new DefaultListCellRenderer() {
           
			private static final long serialVersionUID = 1L;

			@Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setHorizontalAlignment(SwingConstants.CENTER); 
                
                if (isSelected) {
                    label.setBackground(new Color(222, 98, 216)); 
                    label.setForeground(Color.BLACK); 
                } else {
                    label.setBackground(new Color(36, 30, 30)); 
                    label.setForeground(Color.WHITE); 
                }
                return label;
            }
        });
        
        setContentPane(contentPanel);
        contentPanel.add(scrollPane);
        contentPanel.add(lblTitlu);
    }

}
