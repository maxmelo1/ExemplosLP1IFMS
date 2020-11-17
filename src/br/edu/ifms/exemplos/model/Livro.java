/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifms.exemplos.model;

import br.edu.ifms.exemplos.model.DAO.DAO;
import br.edu.ifms.exemplos.model.DAO.DatabaseUtils;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gin
 */
public class Livro implements DAO<Livro>{
    private long id;
    private String nome;
    private Autor autor;
    
    private Connection conn;

    public Livro() throws ClassNotFoundException, SQLException {
        conn = DatabaseUtils.getConnection();
    }
    
    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public Livro buscar(long id) {
        String query = "SELECT * FROM livraria.livro WHERE id=?";
        PreparedStatement ps;
        
        
        try {
            Livro l = new Livro();
            
            ps = conn.prepareStatement(query);
            ps.setLong(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                l.setId(rs.getInt("id"));
                l.setNome(rs.getString("nome"));
                
                int aid = rs.getInt("autor_id");
                Autor a = (Autor) new Autor().buscar(aid);
                
                l.setAutor(a);
                
                return l;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Autor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Autor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    @Override
    public List<Livro> listar() {
        String query = "SELECT * FROM livraria.livro";
        PreparedStatement ps;
        List<Livro> ll = new ArrayList<>();
        
        try {
            
            
            ps = conn.prepareStatement(query);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Livro l = new Livro();
                l.setId(rs.getInt("id"));
                l.setNome(rs.getString("nome"));
                
                int aid = rs.getInt("autor_id");
                Autor a = (Autor) new Autor().buscar(aid);
                
                l.setAutor(a);
                
                ll.add(l);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Autor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Autor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ll;
    }

    @Override
    public void salvar() {
        String query = "INSERT INTO livraria.livro (nome, autor_id) VALUES (?,?)";
        PreparedStatement ps;
        
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, this.getNome());
            ps.setLong(2, this.getAutor().getId() );
            
            ps.executeUpdate();      
            
        } catch (SQLException ex) {
            Logger.getLogger(Autor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void atualizar() {
        String query = "UPDATE livraria.livro SET nome = ?, id_autor = ?";
        PreparedStatement ps;
        
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, this.getNome());
            ps.setLong(2, this.getAutor().getId() );
            
            ps.executeUpdate();      
            
        } catch (SQLException ex) {
            Logger.getLogger(Autor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void remover() {
        String query = "DELETE FROM livraria.livro WHERE id=?";
        PreparedStatement ps;
        
        try {
            ps = conn.prepareStatement(query);
            ps.setLong(1, this.getId());
            
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Autor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
