package gui.administrator;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.ComboPopup;
import javax.swing.table.DefaultTableModel;

import controller.GestionareCereri;
import gui.DetaliiPersonaleAngajat;
import model.Cerere;
import model.ExceptieAplicatie;

import javax.swing.JTable;

public class VizualizareCereri extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VizualizareCereri dialog = new VizualizareCereri(1);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @throws ExceptieAplicatie 
	 */
	public VizualizareCereri(int idAngajat) throws ExceptieAplicatie {
	    setIconImage(Toolkit.getDefaultToolkit().getImage(DetaliiPersonaleAngajat.class.getResource("/Fotografii/pic10.png")));
	    contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	    setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    setSize(screenSize);
	    
	    contentPanel.setBackground(new Color(36, 30, 30)); 
	    contentPanel.setLayout(null); 
	    setContentPane(contentPanel);
	    
	    JLabel titleLabel = new JLabel("Requests Management");
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 50));
        titleLabel.setForeground(new Color(222, 98, 216));
        titleLabel.setBounds(572, 20, 500, 58);
        contentPanel.add(titleLabel);

	    
	   DefaultTableModel tableModel = new DefaultTableModel(new String[] {
	        "Request ID", "Employee ID", "Request Type", "Start Date", "End Date", "Manager ID", "Status"
	    }, 0);
	    
	    table = new JTable(tableModel);
	    table.setBackground(new Color(255, 255, 255)); 
	    table.setBounds(10, 10, 860, 540);
	    table.setBackground(new Color(238,238,238,255)); 
        table.setForeground(Color.BLACK);
        table.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        table.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 19));
        table.getTableHeader().setBackground(new Color(0, 255, 255));
        table.getTableHeader().setForeground(Color.BLACK);
        table.setRowHeight(25);
        table.getTableHeader().setPreferredSize(new Dimension(table.getColumnModel().getTotalColumnWidth(), 30)); 
        
	    contentPanel.add(table);
	    
	    JScrollPane scrollPane = new JScrollPane(table);
	    scrollPane.setBounds(35, 152, 1442, 540);
	    contentPanel.add(scrollPane);

	   
	   List<Cerere> cereriInAsteptare = GestionareCereri.vizualizareCereriInAsteptare(idAngajat);
	   	for (Cerere cerere : cereriInAsteptare) {
	   		String valoareManager;
	   		if (cerere.getManagerId() == -1) {
	   		    valoareManager = "Fără manager";
	   		} else {
	   		    valoareManager = String.valueOf(cerere.getManagerId());
	   		}
	   		tableModel.addRow(new Object[]{
	                   cerere.getCerereId(),
	                   cerere.getAngajatId(),
	                   cerere.getTipCerere().toString(),
	                   cerere.getDataInceput(),
	                   cerere.getDataSfarsit(),
	                   valoareManager,
	                   cerere.getStatus(),
	   
	            });
	        }
	   	table.setSelectionBackground(new Color(222, 98, 216));
	   	JComboBox<String> comboBox = new JComboBox<>(new String[]{"Pending", "Accepted", "Declined"});
        table.getColumnModel().getColumn(6).setCellEditor(new DefaultCellEditor(comboBox));
       comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        comboBox.setRenderer(new javax.swing.plaf.basic.BasicComboBoxRenderer() {
            private static final long serialVersionUID = 1L;

            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if (isSelected) {
                    c.setBackground(new Color(222, 98, 216));
                    c.setForeground(Color.BLACK);
                } else {
                    c.setBackground(Color.WHITE);
                    c.setForeground(Color.BLACK);
                }

                return c;
            }
        });

        comboBox.setUI(new BasicComboBoxUI() {
            @Override
            protected ComboPopup createPopup() {
                ComboPopup popup = super.createPopup();
                popup.getList().setSelectionBackground(Color.WHITE);
                popup.getList().setSelectionForeground(Color.BLACK);
                return popup;
            }
        });
        tableModel.addTableModelListener(event -> {
            int row = event.getFirstRow();
            int column = event.getColumn();

            if (column == 6) { 
                String requestId = tableModel.getValueAt(row, 0).toString(); 
                String newStatus = tableModel.getValueAt(row, column).toString(); 

                try {
                    boolean updated = GestionareCereri.actualizareStatus(requestId, newStatus); 
                    if (updated) {
                        System.out.println("Statusul cererii " + requestId + " a fost actualizat la " + newStatus);
                    } else {
                        System.out.println("Eroare la actualizarea statusului cererii " + requestId);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

	}
}
