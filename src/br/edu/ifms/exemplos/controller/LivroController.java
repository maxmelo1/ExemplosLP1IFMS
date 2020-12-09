/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifms.exemplos.controller;

import br.edu.ifms.exemplos.view.LivroCadastro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author gin
 */
public class LivroController extends AbstractController{
    
    public LivroController() {
        view = new LivroCadastro();
        view.registerController(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        backward();
    }
    
    
}
