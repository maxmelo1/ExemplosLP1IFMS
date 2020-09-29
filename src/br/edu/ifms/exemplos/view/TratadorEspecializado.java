/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifms.exemplos.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

/**
 *
 * @author gin
 */
public class TratadorEspecializado implements ActionListener{

    private JTextField txtMsg;
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        System.out.println(txtMsg.getText());
    }
    
}
