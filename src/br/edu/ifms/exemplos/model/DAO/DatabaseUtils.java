/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifms.exemplos.model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author gin
 */
public class DatabaseUtils {
    private static final String url = "jdbc:postgresql://";
    private static final String db = "lp";
    private static final String ip = "localhost:5432/";
    private static final String user = "***";//seu usuário
    private static final String pass = "***"; //sua senha
    
    public static Connection getConnection() throws ClassNotFoundException, SQLException{
        //String url = "jdbc:mysql://";
        
        
        //String ip = "localhost:3306/";
        
        
        String str = url+ip+db;
        
        //Class.forName("org.mariadb.jdbc.Driver");
        Class.forName("org.postgresql.Driver");
        
        return  DriverManager.getConnection(str, user, pass);
    }
}
