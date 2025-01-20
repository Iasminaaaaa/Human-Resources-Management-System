package gui.manager;

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

public class VizualizareRapoarteManager extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            VizualizareRapoarteManager dialog = new VizualizareRapoarteManager(1);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public VizualizareRapoarteManager(int idAngajat) {
        setIconImage(Toolkit.getDefaultToolkit().getImage(DetaliiPersonaleAngajat.class.getResource("/Fotografii/pic10.png")));
        contentPanel.setLayout(null);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPanel.setBackground(new Color(36, 30, 30));
       
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize);

        JLabel lblTitlu = new JLabel("Reports");
        lblTitlu.setFont(new Font("Times New Roman", Font.BOLD, 50));
        lblTitlu.setForeground(new Color(222, 98, 216));
        lblTitlu.setBounds(640, 20, 400, 58);
        contentPanel.add(lblTitlu);

        JLabel lblReceivedReports = new JLabel("Received Reports");
        lblReceivedReports.setFont(new Font("Times New Roman", Font.BOLD, 30));
        lblReceivedReports.setForeground(new Color(222, 98, 216));
        lblReceivedReports.setBounds(100, 100, 400, 40);
        contentPanel.add(lblReceivedReports);

        List<RaportPerformanta> receivedReports = GestionareRapoarte.infoRapoartePtAngajat(idAngajat); 
        JList<RaportPerformanta> receivedList = createStyledList(receivedReports);
        JScrollPane scrollReceived = new JScrollPane(receivedList);
        scrollReceived.setBounds(100, 150, 600, 400);
        contentPanel.add(scrollReceived);

        JLabel lblGeneratedReports = new JLabel("Generated Reports");
        lblGeneratedReports.setFont(new Font("Times New Roman", Font.BOLD, 30));
        lblGeneratedReports.setForeground(new Color(222, 98, 216));
        lblGeneratedReports.setBounds(800, 100, 400, 40);
        contentPanel.add(lblGeneratedReports);

        List<RaportPerformanta> generatedReports = GestionareRapoarte.rapoarteGenerateManager(idAngajat);
        JList<RaportPerformanta> generatedList = createStyledList(generatedReports);
        JScrollPane scrollGenerated = new JScrollPane(generatedList);
        scrollGenerated.setBounds(800, 150, 600, 400);
        contentPanel.add(scrollGenerated);

        setContentPane(contentPanel);
    }

    private JList<RaportPerformanta> createStyledList(List<RaportPerformanta> reports) {
        JList<RaportPerformanta> list = new JList<>(reports.toArray(new RaportPerformanta[0]));
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        list.setBackground(new Color(36, 30, 30));
        list.setForeground(Color.WHITE);

        list.setCellRenderer(new DefaultListCellRenderer() {
           
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

        return list;
    }
}
