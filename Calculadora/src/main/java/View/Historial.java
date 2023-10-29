/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Model.RealTimeDataBase;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

/**
 *
 * @author Juan David Patarroyo
 */
public class Historial {
    
    public static JFrame root;
    public static ActionListener borrar;
    public static DefaultListModel model;
    public static JList lsNombres;

    
    public static void DibujarHistorial(JFrame Root){
        root = Root;
        root.setBounds(100, 100, 600, 500);
        GenerarListener();
        JPanel panel = new JPanel();
        panel.setLayout(null);
        root.add(panel);
        
        JLabel lbTitulo = new JLabel("Historial:");
        lbTitulo.setBounds(10, 20, 100, 20);
        panel.add(lbTitulo);
        
        lsNombres = new JList();
        lsNombres.setBounds(10, 70, 550, 250);
        panel.add(lsNombres);
        
        RealTimeDataBase.LeerDatoFinal();
        
        model = new DefaultListModel();
        model.removeAllElements();
        
        for (String i : RealTimeDataBase.lecturas){
            if (!model.contains(i)){
                model.addElement(i);
            }
        }
        lsNombres.setModel(model);
        
        JButton btnBorrar = new JButton("Borrar Historial");
        btnBorrar.setBounds(10, 350, 100, 20);
        btnBorrar.addActionListener(borrar);
        panel.add(btnBorrar);
        
        root.setVisible(true);
    }
    
    public static void GenerarListener(){
        borrar = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                RealTimeDataBase.EliminarDatos();
                DefaultListModel modelNone = new DefaultListModel();
                lsNombres.setModel(modelNone);
                root.setVisible(false);
            }
        };
    }
}
