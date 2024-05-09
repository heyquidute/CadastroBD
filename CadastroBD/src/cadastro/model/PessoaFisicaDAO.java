/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastro.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import cadastro.model.util.ConectorBD;
import cadastro.model.util.SequenceManager;

import cadastrobd.model.PessoaFisica;
/**
 *
 * @author anaqu
 */
public class PessoaFisicaDAO {
    private SequenceManager sequence;
    private ConectorBD conector;

    public PessoaFisicaDAO(SequenceManager sequence, ConectorBD conector) {
        this.sequence = sequence;
        this.conector = conector;
    }

    public PessoaFisicaDAO() {}
    
    
    public PessoaFisica getPessoa(int id){
        PessoaFisica pessoa = null;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            c = ConectorBD.getConnection();
            //String sql = "SELECT * FROM Pessoas_Fisicas WHERE id_pessoa = ?";
            String sql = "SELECT pf.*, p.nome, p.logradouro, p.cidade, p.estado,p.telefone, p.email" +
                         "FROM Pessoas_Fisicas pf" +
                         "JOIN Pessoas p ON pf.id_pessoa = p.id_pessoa" +
                         "WHERE pf.id_pessoa = ?";
            ps = c.prepareStatement(sql);
            ps.setInt(1,id);
            
            rs = ps.executeQuery();
            
            if(rs.next()){
                pessoa = new PessoaFisica();
                pessoa.setId_pessoa(rs.getInt("id_pessoa"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setLogradouro(rs.getString("logradouro"));
                pessoa.setCidade(rs.getString("cidade"));
                pessoa.setEstado(rs.getString("estado"));
                pessoa.setTelefone(rs.getString("telefone"));
                pessoa.setEmail(rs.getString("email"));
                pessoa.setCpf(rs.getString("cpf"));
        }
            
        } catch(SQLException excep){
            excep.printStackTrace();
        } finally {
            ConectorBD.close(c);
            ConectorBD.close(rs);
            ConectorBD.close(ps);
        }
        return pessoa;
    }
    
    public List<PessoaFisica> getPessoas(){
        List<PessoaFisica> pessoas = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            c = ConectorBD.getConnection();
            //String sql = "SELECT * FROM Pessoas_Fisicas";
            String sql = "SELECT pf.*, p.nome, p.logradouro, p.cidade, p.estado,p.telefone, p.email" +
                         "FROM Pessoas_Fisicas pf" +
                         "JOIN Pessoas p ON pf.id_pessoa = p.id_pessoa";
            ps = c.prepareStatement(sql);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                PessoaFisica pessoa = new PessoaFisica();
                pessoa.setId_pessoa(rs.getInt("id_pessoa"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setLogradouro(rs.getString("logradouro"));
                pessoa.setCidade(rs.getString("cidade"));
                pessoa.setEstado(rs.getString("estado"));
                pessoa.setTelefone(rs.getString("telefone"));
                pessoa.setEmail(rs.getString("email"));
                pessoa.setCpf(rs.getString("cpf"));
                
                pessoas.add(pessoa);
            }
        } catch(SQLException excep){
            excep.printStackTrace();
        } finally {
            ConectorBD.close(c);
            ConectorBD.close(ps);
            ConectorBD.close(rs);
        }
        return pessoas;
    }
    
    public boolean incluir(PessoaFisica pessoa){
        Connection c = null;
        PreparedStatement ps = null;
        boolean taNoBalde = false;
        
        try{
            c = ConectorBD.getConnection();
            String sql = "INSERT INTO Pessoas_Fisicas (nome, logradouro, cidade, estado, telefone, email, cpf)" + 
                        "VALUES (?,?,?,?,?,?,?)";
            ps = c.prepareStatement(sql);
            ps.setString(1, pessoa.getNome());
            ps.setString(2, pessoa.getLogradouro());
            ps.setString(3, pessoa.getCidade());
            ps.setString(4, pessoa.getEstado());
            ps.setString(5, pessoa.getTelefone());
            ps.setString(6, pessoa.getEmail());
            ps.setString(7, pessoa.getCpf());
            
            int linhasAfetadas = ps.executeUpdate();
            taNoBalde = (linhasAfetadas > 0);
        } catch (SQLException excep){
            excep.printStackTrace();
        } finally {
            ConectorBD.close(c);
            ConectorBD.close(ps);
        }
        return taNoBalde;
    }
    
    
    
    
    
    
}
