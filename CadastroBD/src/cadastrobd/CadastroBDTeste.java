/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastrobd;

import cadastrobd.model.PessoaFisica;
import cadastro.model.PessoaFisicaDAO;

/**
 *
 * @author anaqu
 */
public class CadastroBDTeste {
    
    public static void main(String[] args){
        
        //criando uma pessoa fisica e testando o incluir()
        PessoaFisica pessoaFisica = new PessoaFisica();
        pessoaFisica.setNome("Joey Tribbiani");
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
        
    }
    
}
