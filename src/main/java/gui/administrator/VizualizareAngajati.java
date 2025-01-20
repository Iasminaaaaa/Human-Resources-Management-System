package gui.administrator;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.GestionareAngajati;
import gui.DetaliiPersonaleAngajat;

public class VizualizareAngajati extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JList<String> employeeList;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VizualizareAngajati dialog = new VizualizareAngajati();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VizualizareAngajati() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(DetaliiPersonaleAngajat.class.getResource("/Fotografii/pic10.png")));
		contentPanel.setLayout(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(new Color(36, 30, 30));
       
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		        
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize);

        JLabel lblTitlu = new JLabel("Employees List");
        lblTitlu.setFont(new Font("Times New Roman", Font.BOLD, 50));
        lblTitlu.setForeground(new Color(222, 98, 216));
        lblTitlu.setBounds(580, 20, 400, 58);
      
        List<String> employees=GestionareAngajati.numeSiIdAngajati();
	       
        employeeList = new JList<>(employees.toArray(new String[0]));
	        employeeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	        employeeList.setFont(new Font("Times New Roman", Font.PLAIN, 18));
	        employeeList.setBackground(new Color(36, 30, 30)); 
	        employeeList.setForeground(Color.WHITE); 
	        
	        JScrollPane scrollPane = new JScrollPane(employeeList);
	        scrollPane.setBounds(540, 130, 400, 600);
	        scrollPane.setBackground(new Color(36, 30, 30));
	        employeeList.setCellRenderer(new DefaultListCellRenderer() {
	          
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
	        
	        employeeList.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                if (e.getClickCount()==2){
	                    int index=employeeList.locationToIndex(e.getPoint());
	                    String selectedValue=employeeList.getModel().getElementAt(index);
	                    String[] parts=selectedValue.split(" ");
	                    String id=parts[0]; 
	                    try {
	                        VizualizareDetaliiAngajatAdministrator detaliiDialog = new VizualizareDetaliiAngajatAdministrator(Integer.parseInt(id));
	                        detaliiDialog.setModal(true); 
	                        detaliiDialog.setVisible(true);
		                        
	                        actualizareListaAngajati();
	                    } catch (Exception ex) {
	                        ex.printStackTrace();
	                    }
	                }
	           }
	        });

	        
	        
	        setContentPane(contentPanel);
	  
	        contentPanel.add(scrollPane);
	        contentPanel.add(lblTitlu);

	}
	  
	public void actualizareListaAngajati() {
	        try {
	            List<String> employees = GestionareAngajati.numeSiIdAngajati();
	            for(String el: employees)
	            	System.out.println(el);
	            employeeList.setListData(employees.toArray(new String[0])); 
	            employeeList.revalidate();
	            employeeList.repaint();
	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(null, "Eroare la actualizarea listei de angajați: " + e.getMessage());
	        }
	    }
	
}
