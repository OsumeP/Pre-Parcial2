/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Model.RealTimeDataBase;
import ModelView.Calcular;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author Juan David Patarroyo
 */
public class Operaciones {
    
    public static JFrame root; 
    public static JComboBox cbFuncion;
    public static JTextField numero;
    public static JLabel lbResultado;
    public static JRadioButton radianes;
    public static JRadioButton grados;
    public static ActionListener calculo;
    public static ActionListener historial;
    public static Calcular operar;
    
    public static void Iniciar(){
        root = CalculadoraUI.CreacionFrame();
        GenerarListener();
        DibujarCalculadora();
    }
    
    public static void DibujarCalculadora(){
        JPanel panel = new JPanel();
        panel.setLayout(null);
        root.add(panel);
        
        JLabel lbOperacion = new JLabel("Función Trigonométrica:");
        lbOperacion.setBounds(20, 20, 150, 20);
        panel.add(lbOperacion);
                
        cbFuncion = new JComboBox();
        cbFuncion.addItem("Sin");
        cbFuncion.addItem("Cos");
        cbFuncion.addItem("Tan");
        cbFuncion.addItem("Sec");
        cbFuncion.addItem("Csc");
        cbFuncion.addItem("Cot");
        cbFuncion.setBounds(20, 55, 100, 20);
        panel.add(cbFuncion);
        
        JLabel lbNumero = new JLabel("Número:");
        lbNumero.setBounds(200, 20, 100, 20);
        panel.add(lbNumero);
        
        numero = new JTextField();
        numero.setBounds(200, 55, 100, 20);
        panel.add(numero);
        
        JButton btnCalcular = new JButton("Calcular");
        btnCalcular.setBounds(350, 55, 100, 20);
        btnCalcular.addActionListener(calculo);
        panel.add(btnCalcular);
        
        JLabel lbResul = new JLabel("Resultado:");
        lbResul.setBounds(20, 80, 100, 20);
        panel.add(lbResul);
        
        lbResultado = new JLabel("");
        lbResultado.setBounds(200, 80, 300, 20);
        panel.add(lbResultado);
        
        JButton btnHistorial = new JButton("Historial");
        btnHistorial.setBounds(20, 105, 100, 20);
        btnHistorial.addActionListener(historial);
        panel.add(btnHistorial);
        
        ButtonGroup grupo = new ButtonGroup();
        
        radianes = new JRadioButton("Radianes", true);
        radianes.setBounds(200, 105, 100, 20);
        grupo.add(radianes);
        panel.add(radianes);
        
        grados = new JRadioButton("Grados");
        grados.setBounds(200, 130, 100, 20);
        grupo.add(grados);
        panel.add(grados);
        
        RealTimeDataBase.conectarBase();
        
        root.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        root.setVisible(true);
    }
    
    public static void GenerarListener(){
        calculo = new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent e){
                try{
                    double num = Double.parseDouble(Operaciones.numero.getText());
                    String unit = "Radianes";
                    if (Operaciones.grados.isSelected()){
                        unit = "Grados";
                    }
                    operar = new Calcular(num, (String)Operaciones.cbFuncion.getSelectedItem(), unit);
                    operar.Resultado();
                    Operaciones.lbResultado.setText(String.valueOf(operar.resultado));
                } catch(NumberFormatException ex){
                    Operaciones.lbResultado.setText("Ingresa un valor válido.");
                }
                RealTimeDataBase.AgregarDatos(operar);
            }
        };
        
        historial = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                Historial.DibujarHistorial(CalculadoraUI.CreacionFrame());
            }
        };
    }
}
