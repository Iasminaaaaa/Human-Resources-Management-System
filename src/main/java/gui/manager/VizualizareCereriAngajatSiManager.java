package gui.manager;

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
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.ComboPopup;
import javax.swing.table.DefaultTableModel;

import controller.GestionareCereri;
import model.Cerere;
import model.ExceptieAplicatie;

public class VizualizareCereriAngajatSiManager extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();

    public VizualizareCereriAngajatSiManager(int idManager) throws ExceptieAplicatie {
        setIconImage(Toolkit.getDefaultToolkit().getImage(VizualizareCereriAngajatSiManager.class.getResource("/Fotografii/pic10.png")));
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPanel.setBackground(new Color(36, 30, 30));
        contentPanel.setLayout(null);
        setContentPane(contentPanel);

        JLabel titleLabel = new JLabel("Requests Overview");
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 50));
        titleLabel.setForeground(new Color(222, 98, 216));
        titleLabel.setBounds(572, 20, 600, 58);
        contentPanel.add(titleLabel);

       
        JLabel managerRequestsLabel = new JLabel("My Requests");
        managerRequestsLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
        managerRequestsLabel.setForeground(new Color(222, 98, 216));
        managerRequestsLabel.setBounds(50, 100, 600, 40);
        contentPanel.add(managerRequestsLabel);

        DefaultTableModel managerTableModel = new DefaultTableModel(new String[]{
            "Request ID", "Employee ID", "Request Type", "Start Date", "End Date", "Manager ID", "Status"
        }, 0);

        JTable managerTable = createStyledTable(managerTableModel, false);
        JScrollPane managerScrollPane = new JScrollPane(managerTable);
        managerScrollPane.setBounds(50, 150, 1400, 250);
        contentPanel.add(managerScrollPane);

        List<Cerere> cereriManager = GestionareCereri.vizualizareCereriAngajat(idManager);
        populateTableWithRequests(managerTableModel, cereriManager);

        JLabel employeeRequestsLabel = new JLabel("Requests Submitted by Employees");
        employeeRequestsLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
        employeeRequestsLabel.setForeground(new Color(222, 98, 216));
        employeeRequestsLabel.setBounds(50, 430, 600, 40);
        contentPanel.add(employeeRequestsLabel);

        DefaultTableModel employeeTableModel = new DefaultTableModel(new String[]{
            "Request ID", "Employee ID", "Request Type", "Start Date", "End Date", "Manager ID", "Status"
        }, 0);

        JTable employeeTable = createStyledTable(employeeTableModel, true);
        JScrollPane employeeScrollPane = new JScrollPane(employeeTable);
        employeeScrollPane.setBounds(50, 480, 1400, 250);
        contentPanel.add(employeeScrollPane);

        List<Cerere> cereriAngajati = GestionareCereri.vizualizareCereriInAsteptare(idManager);
        populateTableWithRequests(employeeTableModel, cereriAngajati);

        addStatusChangeListener(managerTableModel, "Manager");
        addStatusChangeListener(employeeTableModel, "Employee");
    }

    private JTable createStyledTable(DefaultTableModel tableModel, boolean includeStatusEditor) {
        JTable table = new JTable(tableModel);
        table.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        table.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 19));
        table.getTableHeader().setBackground(new Color(0, 255, 255));
        table.getTableHeader().setForeground(Color.BLACK);
        table.setRowHeight(25);
        table.getTableHeader().setPreferredSize(new Dimension(table.getColumnModel().getTotalColumnWidth(), 30));
        table.setBackground(new Color(238, 238, 238));
        table.setForeground(Color.BLACK);
     
        table.setSelectionBackground(new Color(222, 98, 216));
       
        if (includeStatusEditor) {
            JComboBox<String> comboBox = new JComboBox<>(new String[]{"Pending", "Accepted", "Declined"});
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
            table.getColumnModel().getColumn(6).setCellEditor(new DefaultCellEditor(comboBox));
        }

        return table;
    }

    private void populateTableWithRequests(DefaultTableModel tableModel, List<Cerere> cereri) {
        for (Cerere cerere : cereri) {
            String valoareManager = cerere.getManagerId() == -1 ? "No Manager" : String.valueOf(cerere.getManagerId());
            tableModel.addRow(new Object[]{
                cerere.getCerereId(),
                cerere.getAngajatId(),
                cerere.getTipCerere().toString(),
                cerere.getDataInceput(),
                cerere.getDataSfarsit(),
                valoareManager,
                cerere.getStatus()
            });
        }
    }

    private void addStatusChangeListener(DefaultTableModel tableModel, String requestType) {
        tableModel.addTableModelListener(event -> {
            int row = event.getFirstRow();
            int column = event.getColumn();

            if (column == 6) {
                String requestId = tableModel.getValueAt(row, 0).toString();
                String newStatus = tableModel.getValueAt(row, column).toString();

                try {
                    boolean updated = GestionareCereri.actualizareStatus(requestId, newStatus);
                    if (updated) {
                        System.out.println(requestType+" Request "+requestId+" updated to "+newStatus);
                    } else {
                        System.out.println("Error updating "+requestType+" Request "+requestId);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
