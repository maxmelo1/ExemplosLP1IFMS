/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exemploslp1ifms;

import javax.swing.JOptionPane;

/**
 *
 * @author gin
 */
public class ContaBancaria {

    
    Double saldo = 0.0;
    
    void verSaldo(){
            JOptionPane.showMessageDialog( null, "Saldo atual = R$ " + saldo);

    }

    void depositar(Double quantia){
            saldo += quantia;
    }

    void sacar(Double quantia){
        saldo -= quantia;
    }

    public ContaBancaria() {
        //teste aqui o programa!!!!!!!!!!!!

        //lembre-se, o sufixo d indica casting(coerção) para double. 
        //Você sempre pode associar um double a um Double, mas o contrário não é verdade. Por quê?
        
        
        /*******************************
        * use para o exercício no final
        * (double) ( (int)(Math.random()*(300)) );
        * 
        * Random gerador = new Random();
        * gerador.nextInt(300);
        * 
        *******************************/
        
    }
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new ContaBancaria();
    }
    
}
