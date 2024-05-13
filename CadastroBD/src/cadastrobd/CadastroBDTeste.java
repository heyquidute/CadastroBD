/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastrobd;

import cadastrobd.model.PessoaFisica;
import cadastro.model.PessoaFisicaDAO;
import cadastrobd.model.PessoaJuridica;
import cadastro.model.PessoaJuridicaDAO;
import java.sql.SQLException;
import java.util.List;


/**
 *
 * @author anaqu
 */
public class CadastroBDTeste {
    
    public void testeInserir(){
    //criando uma pessoa fisica e testando o incluir()
        PessoaFisica pessoaFisica = new PessoaFisica();
        pessoaFisica.setNome("Rachel Green");
        pessoaFisica.setLogradouro("Central Park");
        pessoaFisica.setCidade("New York City");
        pessoaFisica.setEstado("NY");
        pessoaFisica.setTelefone("+1 12345678");
        pessoaFisica.setEmail("joey@friends.com");
        pessoaFisica.setCpf("12345678910");
        
        PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
        
        boolean deuCerto = pessoaFisicaDAO.incluir(pessoaFisica);
        if(deuCerto){
            System.out.println("DEU CERTOO");
        } else {
            System.out.println("tururu NÃO DEU CERTO");
        }
    // criando uma pessoa jurídica e testando o incluir()
        PessoaJuridica pessoaJuridica = new PessoaJuridica();
        pessoaJuridica.setNome("Friends LTDA");
        pessoaJuridica.setLogradouro("Central PErk");
        pessoaJuridica.setCidade("NYC");
        pessoaJuridica.setEstado("NY");
        pessoaJuridica.setTelefone("+1 77777777");
        pessoaJuridica.setEmail("friends@friends.com");
        pessoaJuridica.setCnpj("7700077700017");
        
        PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();
        boolean deuCerto2 = pessoaJuridicaDAO.incluir(pessoaJuridica);
        if(deuCerto2){
            System.out.println("DEU CERTOO");
        } else {
            System.out.println("tururu NÃO DEU CERTO");
        }
        
    }
    
    public void testeAlterar() throws SQLException{
        // testando o alterar()
        PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
        PessoaFisica pf = new PessoaFisica();
        
        pf.setId_pessoa(11);
        pf = pessoaFisicaDAO.getPessoa(pf.getId_pessoa());
        
        if(pf == null){
            System.out.println("Pessoa não encontrada");
        } else {
            pf.setNome("Chandler Bing");
            pf.setEmail("chandler@friends.com");
        }
        
        boolean deuCerto = pessoaFisicaDAO.alterar(pf);
        if(deuCerto){
            System.out.println("DEU CERTOO");
        } else {
            System.out.println("tururu NÃO DEU CERTO");
        }     
        
        PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();
        PessoaJuridica pj = new PessoaJuridica();
        
        pj.setId_pessoa(19);
        pj = pessoaJuridicaDAO.getPessoa(pj.getId_pessoa());
        
        if(pj == null){
            System.out.println("Empresa não encontrada");
        } else {
            pj.setNome("Central Perk Coffee");
            pj.setEmail("coffee@friends.com");
        }
        
        boolean deuCerto2 = pessoaJuridicaDAO.alterar(pj);
        if(deuCerto2){
            System.out.println("DEU CERTOO");
        }else {
            System.out.println("tururu NÃO DEU CERTO");
        }
    }
    
    public void testeExibirPessoas() throws SQLException{
        PessoaFisicaDAO pFdao = new PessoaFisicaDAO();
        List<PessoaFisica> pessoasFisicas = pFdao.getPessoas();
        
        PessoaJuridicaDAO pJdao = new PessoaJuridicaDAO();
        List<PessoaJuridica> pessoasJuridicas = pJdao.getPessoas();
        
        
        if(pessoasFisicas != null && !pessoasFisicas.isEmpty()){
            System.out.println("Lista de todas as pessoas físicas no banco de dados:");
            System.out.println("====================================================");
            for(PessoaFisica pessoa : pessoasFisicas){
                pessoa.exibir();
                System.out.println("----------------------");
            }
        }
        if(pessoasJuridicas != null && !pessoasJuridicas.isEmpty()){
            System.out.println("Lista de todas as pessoas jurídicas no banco de dados:");
            System.out.println("======================================================");
            for(PessoaJuridica pessoa : pessoasJuridicas){
                pessoa.exibir();
                System.out.println("----------------------");
            }
        }  
    }
    
    public void testeExcluir(String type,int id){
        
        switch(type.toUpperCase()){
            case "F":
                PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
                pessoaFisicaDAO.excluir(id);
                break;
            case "J":
                PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();
                pessoaJuridicaDAO.excluir(id);
                break;
            default:
                System.out.println("Tipo inválido");
        }
    }
    
    public static void main(String[] args) throws SQLException{
        
        CadastroBDTeste cadastro = new CadastroBDTeste();
        
        //TESTE PARA INSERIR DADOS NA BD
        cadastro.testeInserir();
        
        // TESTE PARA ALTERAR DADOS NA BD
        cadastro.testeAlterar(); 
        
        // TESTE DE CONSULTAR TODAS AS PESSOAS DO BD
        cadastro.testeExibirPessoas();
        
        //TESTE PARA EXCLUIR DADOS NA BD
        cadastro.testeExcluir("f",6);
        
        
        
        
        
    }
    
}
