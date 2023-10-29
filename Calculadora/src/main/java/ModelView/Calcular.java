/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModelView;

/**
 *
 * @author Juan David Patarroyo
 */
public class Calcular{
    
    public double valor;
    public String operacion;
    public String unidad;
    public double resultado; 
    
    public Calcular(double valor, String operacion, String unidad){
            this.valor = valor;
            this.operacion = operacion;
            this.unidad = unidad;
    }
    
    public void Resultado(){
        if (this.unidad.equals("Grados")){
            this.valor = Math.toRadians(this.valor);
        }
        switch (this.operacion) {
            case "Sin":
                this.resultado = Math.sin(this.valor);
                break;
            case "Cos":
                this.resultado = Math.cos(this.valor);
                break;
            case "Tan":
                this.resultado = Math.tan(this.valor);
                break;
            case "Sec":
                this.resultado = 1/(Math.cos(this.valor));
                break;
            case "Cot":
                this.resultado = 1/(Math.tan(this.valor));
                break;
            case "Csc":
                this.resultado = 1/(Math.sin(this.valor));
                break;
            default:
                break;
        }
    }
}
