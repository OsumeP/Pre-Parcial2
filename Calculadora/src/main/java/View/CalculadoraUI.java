/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package View;

import javax.swing.JFrame;

public class CalculadoraUI {

    public static void main(String[] args) {
        Operaciones.Iniciar();
    }
    public static JFrame CreacionFrame(){
        JFrame root = new JFrame("");
        root.setBounds(100, 100, 500, 500);
        return root;
    }
}
