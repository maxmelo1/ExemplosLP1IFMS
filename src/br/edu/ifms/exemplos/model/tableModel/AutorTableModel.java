/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifms.exemplos.model.tableModel;

import br.edu.ifms.exemplos.model.DAO.DatabaseUtils;
import javax.swing.table.AbstractTableModel;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gin
 */
public class AutorTableModel extends AbstractTableModel{

    private final Connection connection;
    private final Statement statement;
    private ResultSet resultSet;
    
    private ResultSetMetaData metaData;
    private int numberOfRows;
    
    private boolean connectedToDatabase = false;
    
    private final String query = "SELECT * FROM livraria.autor ORDER BY id ASC";

    public AutorTableModel() throws ClassNotFoundException, SQLException {
        this.connection = DatabaseUtils.getConnection();
        this.statement = this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        this.connectedToDatabase = true;
        
        setQuery(query);
        
        
    }
    
    
    public void setQuery(String query) throws SQLException{
        
        if(!this.connectedToDatabase){
            throw new IllegalStateException("Não foi possível conectar ao BD!");
        }
        
        this.resultSet = this.statement.executeQuery(query);
        this.metaData = this.resultSet.getMetaData();
        
        this.resultSet.last();
        this.numberOfRows = this.resultSet.getRow();
        
        fireTableStructureChanged();
    }
    
    public void update() throws SQLException{
        setQuery(query);
    }
    

    @Override
    public int getRowCount() {
        if(!this.connectedToDatabase){
            throw new IllegalStateException("Não foi possível conectar ao BD!");
        }
        
        return this.numberOfRows;
        
    }

    @Override
    public int getColumnCount() {
        if(!this.connectedToDatabase){
            throw new IllegalStateException("Não foi possível conectar ao BD!");
        }
        
        try {
            return this.metaData.getColumnCount();
        } catch (SQLException ex) {
            Logger.getLogger(AutorTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        //deu erro
        return 0;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(!this.connectedToDatabase){
            throw new IllegalStateException("Não foi possível conectar ao BD!");
        }
        try {
            String name = this.metaData.getColumnClassName(columnIndex+1);
            
            return Class.forName(name);
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AutorTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //deu erro
        return Object.class;
    }

    @Override
    public String getColumnName(int column) {
        if(!this.connectedToDatabase){
            throw new IllegalStateException("Não foi possível conectar ao BD!");
        }
        
        try {
            return this.metaData.getColumnName(column+1);
        } catch (SQLException ex) {
            Logger.getLogger(AutorTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "";
    }
    
    

    @Override
    public Object getValueAt(int lin, int col) {
        try {
            if(!this.connectedToDatabase){
                throw new IllegalStateException("Não foi possível conectar ao BD!");
            }
            
            this.resultSet.absolute(lin+1);
            
            return this.resultSet.getObject(col+1);
        } catch (SQLException ex) {
            Logger.getLogger(AutorTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        //deu erro
        return "error 404";
    }

    public void fecharConexao() throws SQLException{
        this.resultSet.close();
        this.statement.close();
        this.connection.close();
    }
    
}
