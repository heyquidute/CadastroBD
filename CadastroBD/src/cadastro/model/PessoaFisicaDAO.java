/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastro.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    private ConectorBD connector;

    public PessoaFisicaDAO() {
    connector = new ConectorBD();
    }
    
    /*==========================================================================
    public PessoaFisica getPessoa(int id) throws SQLException{
        String sql = "SELECT p.*, pf.cpf " +
                         "FROM Pessoas p " +
                         "INNER JOIN Pessoas_Fisicas pf ON p.id_pessoa = pf.id_pessoa " +
                         "WHERE p.id_pessoa = ?";
        
        try(Connection c = connector.getConnection(); PreparedStatement ps = c.prepareStatement(sql) ){
        ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()) {
               System.out.println("Entrou no try 2");
               System.out.println(rs);
                if(rs.next()){
                    System.out.println("Entrou no if");
                    return new PessoaFisica(
                    rs.getInt("id_pessoa"),
                    rs.getString("nome"),
                    rs.getString("logradouro"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getString("cpf")                        
                );   
                }
            }
        }
        return null;
    }=========================================================================*/
    
    
    public PessoaFisica getPessoa(int id){
        PessoaFisica pessoa = null;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            c = ConectorBD.getConnection();
     
            String sql = "SELECT pf.*, p.nome, p.logradouro, p.cidade, p.estado,p.telefone, p.email " +
                         "FROM Pessoas_Fisicas pf " +
                         "JOIN Pessoas p ON pf.id_pessoa = p.id_pessoa " +
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
            String sql = "SELECT pf.*, p.nome, p.logradouro, p.cidade, p.estado,p.telefone, p.email " +
                         "FROM Pessoas_Fisicas pf " +
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
        PreparedStatement psPessoa = null;
        PreparedStatement psPessoaFisica = null;
        boolean taNoBalde = false;
        
        try{
            c = ConectorBD.getConnection();
            
            // inserção dos dados na tabela Pessoas
            String sqlPessoa = "INSERT INTO Pessoas (nome, logradouro, cidade, estado, telefone, email)" + 
                        "VALUES (?,?,?,?,?,?)";
            psPessoa = c.prepareStatement(sqlPessoa, Statement.RETURN_GENERATED_KEYS);
            psPessoa.setString(1, pessoa.getNome());
            psPessoa.setString(2, pessoa.getLogradouro());
            psPessoa.setString(3, pessoa.getCidade());
            psPessoa.setString(4, pessoa.getEstado());
            psPessoa.setString(5, pessoa.getTelefone());
            psPessoa.setString(6, pessoa.getEmail());
            int linhasAfetadas  = psPessoa.executeUpdate();
            if(linhasAfetadas == 0){
                throw new SQLException("Inserção na tabela Pessoas falhou, nenhuma linha afetada");
            }
            
            // pegando o id_pessoa gerado pela sequence
            String sqlIdPessoa = "SELECT TOP 1 id_pessoa FROM Pessoas ORDER BY id_pessoa DESC;";
            PreparedStatement psIdPessoa = c.prepareStatement(sqlIdPessoa);
            ResultSet rsIdPessoa = psIdPessoa.executeQuery();
            
            int idPessoa = -1;
            if(rsIdPessoa.next()){
                idPessoa = rsIdPessoa.getInt("id_pessoa");
            } else {
                throw new SQLException("Falha ao obter o ID gerado para a pessoa inserida na tabela Pessoas");
            }
            
            // inserção de dados na tabela Pessoas_Fisicas
            String sqlPessoaFisica = "INSERT INTO Pessoas_Fisicas (id_pFisica, id_pessoa, cpf)" +
                        "VALUES (?,?,?)";
            psPessoaFisica = c.prepareStatement(sqlPessoaFisica);
            psPessoaFisica.setInt(1,idPessoa);
            psPessoaFisica.setInt(2,idPessoa);
            psPessoaFisica.setString(3,pessoa.getCpf());
            psPessoaFisica.executeUpdate();
           
            taNoBalde = true;
        } catch (SQLException excep){
            excep.printStackTrace();
        } finally {
            ConectorBD.close(c);
            ConectorBD.close(psPessoa);
            ConectorBD.close(psPessoaFisica);
        }
        return taNoBalde;
    }
    
    public boolean alterar(PessoaFisica pessoa){
        Connection c = null;
        PreparedStatement psPessoa = null;
        PreparedStatement psPessoaFisica = null;
        boolean taNoBalde = false;
        
        try{
            c = ConectorBD.getConnection();
            
            // update da tabela Pessoas
            String sqlPessoa = "UPDATE Pessoas " +
                                "SET nome = ?, logradouro = ?, cidade = ?, estado = ?, telefone = ?, email = ? " +
                                "WHERE id_pessoa = ?";
            psPessoa = c.prepareStatement(sqlPessoa);
            psPessoa.setString(1, pessoa.getNome());
            psPessoa.setString(2, pessoa.getLogradouro());
            psPessoa.setString(3, pessoa.getCidade());
            psPessoa.setString(4, pessoa.getEstado());
            psPessoa.setString(5, pessoa.getTelefone());
            psPessoa.setString(6, pessoa.getEmail());
            psPessoa.setInt(7, pessoa.getId_pessoa());
            psPessoa.executeUpdate();
            
            //update da tabela Pessoas_Fisicas
            String sqlPessoaFisica = "UPDATE Pessoas_Fisicas " + 
                                     "SET cpf = ? " + 
                                     "WHERE id_pessoa = ?";
            psPessoaFisica = c.prepareStatement(sqlPessoaFisica);
            psPessoaFisica.setString(1,pessoa.getCpf());
            psPessoaFisica.setInt(2,pessoa.getId_pessoa());
            psPessoaFisica.executeUpdate();
            
            int linhasAfetadas = psPessoaFisica.executeUpdate();
            taNoBalde = (linhasAfetadas > 0);
            
        } catch(SQLException excep){
            excep.printStackTrace();
        } finally {
            ConectorBD.close(c);
            ConectorBD.close(psPessoa);
            ConectorBD.close(psPessoaFisica);
        }
        return taNoBalde;
    }
    
    public boolean excluir(int id){
        Connection c = null;
        PreparedStatement psPessoa = null;
        PreparedStatement psPessoaFisica = null;
        boolean taNoBalde = false;
        
        try{
            c = ConectorBD.getConnection();
            
            // exclusão na tabela Pessoas_Fisicas
            String sqlPessoaFisica = "DELETE FROM Pessoas_Fisicas WHERE id_pessoa = ?";
            psPessoaFisica = c.prepareStatement(sqlPessoaFisica);
            psPessoaFisica.setInt(1, id);
            psPessoaFisica.executeUpdate();
            
            // exclusão na tabela Pessoas
            String sqlPessoa = "DELETE FROM Pessoas WHERE id_pessoa = ?";
            psPessoa = c.prepareStatement(sqlPessoa);
            psPessoa.setInt(1, id);
            psPessoa.executeUpdate();
                
        } catch(SQLException excep){
            excep.printStackTrace();
        } finally {
            ConectorBD.close(c);
            ConectorBD.close(psPessoa);
            ConectorBD.close(psPessoaFisica);
        }
        return taNoBalde;
    }
      
}
