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

import cadastrobd.model.PessoaJuridica;

public class PessoaJuridicaDAO {
    
    public PessoaJuridica getPessoa(int id){
        PessoaJuridica pessoa = null;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            c = ConectorBD.getConnection();
            
            String sql = "SELECT pj.*, p.nome, p.logradouro, p.cidade, p.estado, p.telefone, p.email " + 
                            "FROM Pessoas_Juridicas pj " +
                            "JOIN Pessoas p ON pj.id_pessoa = p.id_pessoa " +
                            "WHERE pj.id_pessoa = ?";
            ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            
            rs = ps.executeQuery();
            
            if(rs.next()){
                pessoa = new PessoaJuridica();
                pessoa.setId_pessoa(rs.getInt("id_pessoa"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setLogradouro(rs.getString("logradouro"));
                pessoa.setCidade(rs.getString("cidade"));
                pessoa.setEstado(rs.getString("estado"));
                pessoa.setTelefone(rs.getString("telefone"));
                pessoa.setEmail(rs.getString("email"));
                pessoa.setCnpj(rs.getString("cnpj"));
                
            }
          
        } catch(SQLException excep){
            excep.printStackTrace();
        }
        return pessoa;
    }
    
    public List<PessoaJuridica> getPessoas(){
       List<PessoaJuridica> pessoas = new ArrayList<>();
       Connection c = null;
       PreparedStatement ps = null;
       ResultSet rs = null;
       
       try{
           c = ConectorBD.getConnection();
           
           String sql = "SELECT pj.*, p.nome, p.logradouro, p.cidade, p.estado, p.telefone, p.email " + 
                        "FROM Pessoas_Juridicas pj " + 
                        "JOIN Pessoas p ON pj.id_pessoa = p.id_pessoa";
           ps = c.prepareStatement(sql);
           rs = ps.executeQuery();
           
           while (rs.next()){
               PessoaJuridica pessoa = new PessoaJuridica();
               pessoa.setId_pessoa(rs.getInt("id_pessoa"));
               pessoa.setNome(rs.getString("nome"));
               pessoa.setLogradouro(rs.getString("logradouro"));
               pessoa.setCidade(rs.getString("cidade"));
               pessoa.setEstado(rs.getString("estado"));
               pessoa.setTelefone(rs.getString("telefone"));
               pessoa.setEmail(rs.getString("email"));
               pessoa.setCnpj(rs.getString("cnpj"));
               
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
    
    public boolean incluir(PessoaJuridica pessoa){
        Connection c = null;
        PreparedStatement psPessoa = null;
        PreparedStatement psPessoaJuridica = null;
        boolean taNoBalde = false;
        
        try{
            c = ConectorBD.getConnection();
            
            String sqlPessoa = "INSERT INTO Pessoas(nome, logradouro, cidade, estado, telefone, email) " +
                                "VALUES (?,?,?,?,?,?)";
            psPessoa = c.prepareStatement(sqlPessoa, Statement.RETURN_GENERATED_KEYS);
            psPessoa.setString(1, pessoa.getNome());
            psPessoa.setString(2, pessoa.getLogradouro());
            psPessoa.setString(3, pessoa.getCidade());
            psPessoa.setString(4, pessoa.getEstado());
            psPessoa.setString(5, pessoa.getTelefone());
            psPessoa.setString(6, pessoa.getEmail());
            int linhasAfetadas = psPessoa.executeUpdate();
            if(linhasAfetadas == 0){
            throw new SQLException("Inserção na tabela Pessoas falhou, nenhuma linha afetada");
            }
            
            String sqlIdPessoa = "SELECT TOP 1 id_pessoa FROM Pessoas ORDER BY id_pessoa DESC;";
            PreparedStatement psIdPessoa = c.prepareStatement(sqlIdPessoa);
            ResultSet rsIdPessoa = psIdPessoa.executeQuery();
            
            int idPessoa = -1;
            if(rsIdPessoa.next()){
                idPessoa = rsIdPessoa.getInt("id_pessoa");
            } else {
                throw new SQLException("Falha ao obter o ID gerado para a pessoa inserida na tabela Pessoas");
            }
            
            String sqlPessoaFisica = "INSERT INTO Pessoas_Juridicas (id_pJuridica, id_pessoa, cnpj) " +
                        "VALUES (?,?,?)";
            psPessoaJuridica = c.prepareStatement(sqlPessoaFisica);
            psPessoaJuridica.setInt(1,idPessoa);
            psPessoaJuridica.setInt(2,idPessoa);
            psPessoaJuridica.setString(3,pessoa.getCnpj());
            psPessoaJuridica.executeUpdate();
            
            taNoBalde = true;
        } catch(SQLException excep){
            excep.printStackTrace();
        } finally {
            ConectorBD.close(c);
            ConectorBD.close(psPessoa);
            ConectorBD.close(psPessoaJuridica);
        }
        return taNoBalde;
    }
    
    public boolean alterar(PessoaJuridica pessoa){
        Connection c = null;
        PreparedStatement psPessoa = null;
        PreparedStatement psPessoaJuridica = null;
        boolean taNoBalde = false;
        
        try{
            c = ConectorBD.getConnection();
            
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
            
            String sqlPessoaJuridica = "UPDATE Pessoas_Juridicas " + 
                                     "SET cnpj = ? " + 
                                     "WHERE id_pessoa = ?";
            psPessoaJuridica = c.prepareStatement(sqlPessoaJuridica);
            psPessoaJuridica.setString(1,pessoa.getCnpj());
            psPessoaJuridica.setInt(2,pessoa.getId_pessoa());
            psPessoaJuridica.executeUpdate();
            
            int linhasAfetadas = psPessoaJuridica.executeUpdate();
            taNoBalde = (linhasAfetadas > 0);
                    
        } catch(SQLException excep){
            excep.printStackTrace();
        } finally {
            ConectorBD.close(c);
            ConectorBD.close(psPessoa);
            ConectorBD.close(psPessoaJuridica);
        }
        return taNoBalde;
    }
    
    public boolean excluir(int id){
        Connection c = null;
        PreparedStatement psPessoa = null;
        PreparedStatement psPessoaJuridica = null;
        boolean taNoBalde = false;
        
        try {
            c = ConectorBD.getConnection();
            
            String sqlPessoaJuridica = "DELETE FROM Pessoas_Juridicas WHERE id_pessoa = ?";
            psPessoaJuridica = c.prepareStatement(sqlPessoaJuridica);
            psPessoaJuridica.setInt(1, id);
            psPessoaJuridica.executeUpdate();
            
            String sqlPessoa = "DELETE FROM Pessoas WHERE id_pessoa = ?";
            psPessoa = c.prepareStatement(sqlPessoa);
            psPessoa.setInt(1, id);
            psPessoa.executeUpdate();
                
        } catch(SQLException excep){
            excep.printStackTrace();
        } finally {
            ConectorBD.close(c);
            ConectorBD.close(psPessoa);
            ConectorBD.close(psPessoaJuridica);
        }
        return taNoBalde;
        
    }
    
}
