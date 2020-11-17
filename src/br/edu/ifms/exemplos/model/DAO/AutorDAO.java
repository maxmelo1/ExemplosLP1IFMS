/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifms.exemplos.model.DAO;

import br.edu.ifms.exemplos.model.Autor;
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
public class AutorDAO implements DAO2<Autor>{
    
    private final Connection conn;

    public AutorDAO() throws ClassNotFoundException, SQLException {
        conn = DatabaseUtils.getConnection();
    }
    
    

    @Override
    public Autor buscar(long id) {
        String query = "SELECT * FROM livraria.autor WHERE id=?";
        PreparedStatement ps;
        
        
        try {
            Autor a = new Autor();
            
            ps = conn.prepareStatement(query);
            ps.setLong(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                a.setId(rs.getInt("id"));
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
    public List<Autor> listar() {
        String query = "SELECT * FROM livraria.autor";
        PreparedStatement ps;
        List<Autor> la = new ArrayList<>();
        
        try {
            
            
            ps = conn.prepareStatement(query);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Autor a = new Autor();
                a.setId(rs.getInt("id"));
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
    public void salvar(Autor obj) {
        String query = "INSERT INTO livraria.autor (nome, sobrenome) VALUES (?,?)";
        PreparedStatement ps;
        
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, ((Autor)obj).getNome());
            ps.setString(2, ((Autor)obj).getSobreNome() );
            
            ps.executeUpdate();      
            
        } catch (SQLException ex) {
            Logger.getLogger(Autor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void atualizar(Autor obj) {
        String query = "UPDATE livraria.autor SET nome =?, sobrenome =?";
        PreparedStatement ps;
        
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, ((Autor)obj).getNome());
            ps.setString(2, ((Autor)obj).getSobreNome() );
            
            ps.executeUpdate();      
            
        } catch (SQLException ex) {
            Logger.getLogger(Autor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void remover(Autor obj) {
        String query = "DELETE FROM livraria.autor WHERE id=?";
        PreparedStatement ps;
        
        try {
            ps = conn.prepareStatement(query);
            ps.setLong(1, ((Autor)obj).getId());
            
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Autor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
