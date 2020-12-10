/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifms.exemplos.model;

import br.edu.ifms.exemplos.model.DAO.DAO;
import br.edu.ifms.exemplos.model.DAO.DatabaseUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gin
 */
public class Autor implements DAO{
    private Long id;
    private String nome;
    private String sobreNome;
    
    private final Connection conn;

    public Autor() throws ClassNotFoundException, SQLException {
        conn = DatabaseUtils.getConnection();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobreNome() {
        return sobreNome;
    }

    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
    }
    
    
    

        

    

    @Override
    public Object buscar(long id) {
        String query = "SELECT * FROM livraria.autor WHERE id=?";
        PreparedStatement ps;
        
        
        try {
            Autor a = new Autor();
            
            ps = conn.prepareStatement(query);
            ps.setLong(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                a.setId(rs.getLong("id"));
                a.setNome(rs.getString("nome"));
                a.setSobreNome(rs.getString("sobrenome"));
                
                return a;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Autor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Autor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
        
    }

    @Override
    public List listar() {
        String query = "SELECT * FROM livraria.autor";
        PreparedStatement ps;
        List<Autor> la = new ArrayList<>();
        
        try {
            
            
            ps = conn.prepareStatement(query);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Autor a = new Autor();
                a.setId(rs.getLong("id"));
                a.setNome(rs.getString("nome"));
                a.setSobreNome(rs.getString("sobrenome"));
                
                la.add(a);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Autor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Autor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return la;
    }

    @Override
    public void salvar() {
        String query = "INSERT INTO livraria.autor (nome, sobrenome) VALUES (?,?)";
        PreparedStatement ps;
        
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, this.getNome());
            ps.setString(2, this.getSobreNome() );
            
            ps.executeUpdate();      
            
        } catch (SQLException ex) {
            Logger.getLogger(Autor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void atualizar() {
        String query = "UPDATE livraria.autor SET nome =?, sobrenome =? WHERE id = ?";
        PreparedStatement ps;
        
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, this.getNome());
            ps.setString(2, this.getSobreNome() );
            ps.setLong(3, this.getId() );
            
            ps.executeUpdate();      
            
        } catch (SQLException ex) {
            Logger.getLogger(Autor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void remover() {
        String query = "DELETE FROM livraria.autor WHERE id=?";
        PreparedStatement ps;
        
        try {
            ps = conn.prepareStatement(query);
            ps.setLong(1, this.getId());
            
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Autor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String toString() {
        return getNome()+" "+getSobreNome(); 
    }

    
    
    
    
}
