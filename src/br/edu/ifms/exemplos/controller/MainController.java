/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifms.exemplos.controller;

import br.edu.ifms.exemplos.view.ExemploMenu;
import java.awt.event.ActionEvent;
import java.util.Stack;

/**
 *
 * @author gin
 */
public class MainController extends AbstractController{
    
    public MainController() {
        view = new ExemploMenu();
        view.registerController(this);
        view.setVisible(true);
        
        routes = new Stack<>();
        routes.add(this);
    }

    
    
    public static void main(String[] args) {
        new MainController();
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        forward(new LivroController());
    }
    
}
