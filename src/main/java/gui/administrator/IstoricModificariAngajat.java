package gui.administrator;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import controller.GestionareAngajati;
import gui.DetaliiPersonaleAngajat;
import model.IstoricJob;
import model.IstoricManager;
import model.IstoricSalariu;

public class IstoricModificariAngajat extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JScrollPane scrollPane = null;
    /**
     * Create the dialog.
     */
    public IstoricModificariAngajat(int angajatId) {
        setTitle("Change History");
        setSize(535, 230);
        setLocation(460, 580);
        contentPanel.setLayout(null);
        setIconImage(Toolkit.getDefaultToolkit().getImage(DetaliiPersonaleAngajat.class.getResource("/Fotografii/pic10.png")));
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPanel.setBackground(new Color(222, 98, 216));
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        
       
        setContentPane(contentPanel);
        JButton btnVizualizareSalariu = new JButton("Salary Changes");
            
          btnVizualizareSalariu.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	 if (scrollPane != null) {
                		 contentPanel.remove(scrollPane);
                     } 
                	List<IstoricSalariu> istoricSalariu = GestionareAngajati.vizualizareIstoricSalariu(angajatId);  
                    if (istoricSalariu.isEmpty()) {
                        JOptionPane.showMessageDialog(contentPanel, "Nu există modificări ale salariului pentru acest angajat.", "Informație", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    else {
                    DefaultListModel<String> listModel = new DefaultListModel<>();
                    
                    for (IstoricSalariu salariu : istoricSalariu) {
                    	String item;
                        if(salariu.getEndDate()!=null) item = "Start Date: " + salariu.getStartDate() + ", Salary: " + salariu.getSalariulAnterior()+ ", End Date: " + salariu.getEndDate();
                        else item = "Start Date: " + salariu.getStartDate() + ", Salary: " + salariu.getSalariulAnterior()+ ", End Date: -";
                        listModel.addElement(item);
                    }

                     JList<String> modificationList = new JList<>(listModel);
                    
                    modificationList.setFont(new Font("Times New Roman", Font.PLAIN, 18));
                    modificationList.setBackground(Color.WHITE); 
                    modificationList.setForeground(Color.BLACK); 
                    
                    scrollPane = new JScrollPane(modificationList);
                    scrollPane.setBounds(5, 46, 506, 137);
                    scrollPane.setVisible(true);
                    contentPanel.add(scrollPane); 
                }
                    contentPanel.revalidate(); 
                    contentPanel.repaint();
                }
            });
          btnVizualizareSalariu.setBounds(176, 5, 159, 21);
          btnVizualizareSalariu.setVisible(true);
          contentPanel.add(btnVizualizareSalariu);
          
         JButton btnVizualizareJob = new JButton("Job Changes");
          btnVizualizareJob.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
            	  if (scrollPane != null) {
             		 contentPanel.remove(scrollPane);
                  } 
                  List<IstoricJob> istoricJob = GestionareAngajati.vizualizareIstoricJob(angajatId);

                  if (istoricJob.isEmpty()) {
                      JOptionPane.showMessageDialog(contentPanel, 
                          "Nu există modificări ale joburilor pentru acest angajat.");
                      return;
                  }
                  else {
                  DefaultListModel<String> listModel = new DefaultListModel<>();

                  for (IstoricJob job : istoricJob) {
                	  String item;
                	  if(job.getEndDate()!=null) item = "Start Date: " + job.getStartDate() + 
                                    ", Position: " + job.getPozitiaAnterioara() + ", End Date: " + job.getEndDate();
                	  else item = "Start Date: " + job.getStartDate()+
                              ", Position: " + job.getPozitiaAnterioara()+", End Date: -";
                      listModel.addElement(item);
                  }

                  JList<String> modificationList = new JList<>(listModel);

                  modificationList.setFont(new Font("Times New Roman", Font.PLAIN, 18));
                  modificationList.setBackground(Color.WHITE);
                  modificationList.setForeground(Color.BLACK);

                  scrollPane = new JScrollPane(modificationList);
                  scrollPane.setBounds(5, 46, 506, 137);
                  scrollPane.setVisible(true);
                  contentPanel.add(scrollPane);
                 }
                  contentPanel.revalidate();
                  contentPanel.repaint();
              }
          });
          btnVizualizareJob.setBounds(5, 5, 140, 21);
           btnVizualizareJob.setVisible(true);
          contentPanel.add(btnVizualizareJob);

          JButton btnVizualizareManager = new JButton("Manager Changes");
          btnVizualizareManager.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
            	  if (scrollPane != null) {
             		 contentPanel.remove(scrollPane);
                  } 
                  List<IstoricManager> istoricManager = GestionareAngajati.vizualizareIstoricManager(angajatId);

                  if (istoricManager.isEmpty()) {
                      JOptionPane.showMessageDialog(contentPanel, 
                          "Nu există modificări ale managerilor pentru acest angajat."); 
                          return;
                  }
                  else {
                  DefaultListModel<String> listModel = new DefaultListModel<>();

                  for (IstoricManager manager : istoricManager) {
                	  String item;
                	  if( manager.getEndDate()!=null) item = "Start Date: " + manager.getStartDate() + ", Manager: " + manager.getManagerAnterior() +  ", End Date: " + manager.getEndDate();
                	  else item = "Start Date: " + manager.getStartDate() + ", Manager: " + manager.getManagerAnterior() +  ", End Date: -"; 
                 	 
                	  listModel.addElement(item);
                  }

                  JList<String> modificationList = new JList<>(listModel);

                  modificationList.setFont(new Font("Times New Roman", Font.PLAIN, 18));
                  modificationList.setBackground(Color.WHITE);
                  modificationList.setForeground(Color.BLACK);

                  scrollPane = new JScrollPane(modificationList);
                  scrollPane.setBounds(5, 46, 506, 137);
                  scrollPane.setVisible(true);
                  contentPanel.add(scrollPane);
              
                  contentPanel.revalidate(); 
                  contentPanel.repaint();
             }
              }
          });
          btnVizualizareManager.setBounds(371, 5, 140, 21);
          btnVizualizareManager.setVisible(true);
          contentPanel.add(btnVizualizareManager);
    }


}
