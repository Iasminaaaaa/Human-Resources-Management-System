package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.ComboPopup;

import controller.GestionareAngajati;
import controller.GestionareCereri;
import model.Cerere;
import model.ExceptieAplicatie;
import model.Cerere.TipCerere;

public class AdaugareCereri extends JDialog {

    private static final long serialVersionUID=1L;
    private final JPanel contentPanel=new JPanel();
    private final JTextField txtStartDate;
    private final JTextField txtEndDate;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            AdaugareCereri dialog=new AdaugareCereri(1); 
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public AdaugareCereri(int employeeId) {
    	int managerId=GestionareAngajati.getManagerId(employeeId);
    
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Fotografii/pic10.png")));
        contentPanel.setLayout(null);
        contentPanel.setBackground(new Color(36, 30, 30));

        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize);
        setTitle("Add Request");

        JLabel lblTitle=new JLabel("Add New Request");
        lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 50));
        lblTitle.setForeground(new Color(222, 98, 216));
        lblTitle.setBounds(580, 20, 500, 58);
        contentPanel.add(lblTitle);

        JLabel lblRequestType=new JLabel("Request Type:");
        lblRequestType.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblRequestType.setForeground(new Color(0, 255, 255));
        lblRequestType.setBounds(527, 176, 150, 25);
        contentPanel.add(lblRequestType);

        JComboBox<String> cmbRequestType=new JComboBox<>();
        cmbRequestType.addItem("Select Request Type");
        cmbRequestType.addItem("Resignation");
        cmbRequestType.addItem("Leave");
        cmbRequestType.addItem("Vacation");
        cmbRequestType.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        cmbRequestType.setBounds(675, 176, 397, 25);
        contentPanel.add(cmbRequestType);
        cmbRequestType.setFont(new Font("Times New Roman", Font.PLAIN, 20));
    	cmbRequestType.setBackground(Color.WHITE);
    	cmbRequestType.setForeground(new Color(0, 0, 0));
    	cmbRequestType.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
    	cmbRequestType.setSelectedItem("Select your role");
    	cmbRequestType.setOpaque(true);
    	cmbRequestType.setRenderer(new DefaultListCellRenderer() {
    	
	        private static final long serialVersionUID=1L;

			@Override
    	    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
    	        Component c=super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
    	        
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
  	
        cmbRequestType.setUI(new BasicComboBoxUI() {
            @Override
            protected ComboPopup createPopup() {
                ComboPopup popup=super.createPopup();
                popup.getList().setSelectionBackground(Color.WHITE);  
                popup.getList().setSelectionForeground(Color.BLACK);  
                return popup;
            }
        });
        JLabel lblStartDate=new JLabel("Start Date:");
        lblStartDate.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblStartDate.setForeground(new Color(0, 255, 255));
        lblStartDate.setBounds(527, 226, 200, 25);
        contentPanel.add(lblStartDate);

        txtStartDate=new JTextField();
        txtStartDate.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        txtStartDate.setBounds(675, 226, 397, 25);
        contentPanel.add(txtStartDate);

        JLabel lblEndDate=new JLabel("End Date:");
        lblEndDate.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblEndDate.setForeground(new Color(0, 255, 255));
        lblEndDate.setBounds(527, 276, 200, 25);
        contentPanel.add(lblEndDate);

        txtEndDate=new JTextField();
        txtEndDate.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        txtEndDate.setBounds(675, 276, 397, 25);
        contentPanel.add(txtEndDate);

        JButton btnAddRequest=new JButton("Add Request");
        btnAddRequest.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(222, 98, 216), new Color(222, 98, 216), new Color(222, 98, 216), new Color(222, 98, 216)));
        btnAddRequest.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnAddRequest.setForeground(Color.BLACK);
        btnAddRequest.setBackground(new Color(0, 255, 255));
        btnAddRequest.setFocusPainted(false);
        btnAddRequest.setBounds(675, 562, 200, 40);
        btnAddRequest.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String requestType=(String) cmbRequestType.getSelectedItem();
                TipCerere request=TipCerere.valueOf(requestType);
                String startDate=txtStartDate.getText();
                String endDate=txtEndDate.getText();

                if ("Select Request Type".equals(requestType) || startDate.isEmpty() || endDate.isEmpty()) {
                    JOptionPane.showMessageDialog(contentPanel, "Please fill all fields and select a valid request type.");
                    return;
                }

                try {
                    Date start=Date.valueOf(startDate);
                    Date end=Date.valueOf(endDate);

                    if (start.after(end)) {
                        JOptionPane.showMessageDialog(contentPanel, "Start date cannot be after end date.");
                        return;
                    }

                    Cerere cerere=new Cerere(0, employeeId, "Pending", start, end, request, managerId);
                   	   
                    GestionareCereri.adaugareCerereNoua(cerere);
                    JOptionPane.showMessageDialog(contentPanel, "Request added successfully!");
                    dispose();

                } catch (IllegalArgumentException | ExceptieAplicatie ex) {
                    JOptionPane.showMessageDialog(contentPanel, "Invalid date format. Please use YYYY-MM-DD.");
                }
            }
        });
        
        ImageIcon originalIcon1 = new ImageIcon(getClass().getResource("/Fotografii/error.png"));
        Image img1 = originalIcon1.getImage(); 
        Image resizedImg1 = img1.getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon1=new ImageIcon(resizedImg1);
        UIManager.put("OptionPane.errorIcon", resizedIcon1);

        ImageIcon originalIcon3 = new ImageIcon(getClass().getResource("/Fotografii/exclamation.png"));
        Image img3 = originalIcon3.getImage(); 
        Image resizedImg3 = img3.getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon3=new ImageIcon(resizedImg3); 
        UIManager.put("OptionPane.informationIcon", resizedIcon3);
      
        UIManager.put("Button.border", BorderFactory.createLineBorder(Color.WHITE, 3));  
        UIManager.put("Button.select", new Color(222, 98, 216));
        UIManager.put("OptionPane.background", new Color(222, 98, 216)); 
        UIManager.put("Panel.background", new Color(222, 98, 216)); 
        UIManager.put("Button.background", new Color(0, 255, 255)); 
        UIManager.put("Button.foreground", new Color(0, 0, 0)); 
        UIManager.put("Button.pressed", Color.WHITE); 
        
        contentPanel.add(btnAddRequest);
        setContentPane(contentPanel);
    }
}
