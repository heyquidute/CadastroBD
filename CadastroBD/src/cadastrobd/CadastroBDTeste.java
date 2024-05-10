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
        pessoaFisica.setNome("John Smith");
        pessoaFisica.setLogradouro("Queens");
        pessoaFisica.setCidade("New York City");
        pessoaFisica.setEstado("NY");
        pessoaFisica.setTelefone("+1 40028922");
        pessoaFisica.setEmail("jhon@smith.com");
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
