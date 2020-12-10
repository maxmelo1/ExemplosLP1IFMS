/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifms.exemplos.view;

import br.edu.ifms.exemplos.controller.AbstractController;
import javax.swing.JFrame;

/**
 *
 * @author gin
 */
public abstract class AbstractView extends JFrame{
    public abstract void registerController(AbstractController ac);
}
