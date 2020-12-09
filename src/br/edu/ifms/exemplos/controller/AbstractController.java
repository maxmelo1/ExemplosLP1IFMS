/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifms.exemplos.controller;

import br.edu.ifms.exemplos.view.AbstractView;
import java.awt.event.ActionListener;
import java.util.Stack;

/**
 *
 * @author gin
 */
public abstract class AbstractController implements ActionListener {
    protected static Stack<AbstractController> routes;
    protected AbstractView view;

    public AbstractView getView() {
        return view;
    }

    public void setView(AbstractView view) {
        this.view = view;
    }
    
    
    
    protected void forward(AbstractController c){
        routes.push(c);
        c.view.setVisible(true);
        view.setVisible(false);
    }
    
    protected void backward(){
        if( routes.size() >0 ){
            AbstractController temp = routes.pop();
            temp.view.setVisible(false);
            routes.peek().view.setVisible(true);
        }
    }
}
