/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifms.exemplos.model.DAO;

import java.util.List;

/**
 *
 * @author gin
 */
public interface DAO2<T> {
    T buscar(long id);
    List<T> listar();
    void salvar(T obj);
    void atualizar(T obj);
    void remover(T obj);
}
