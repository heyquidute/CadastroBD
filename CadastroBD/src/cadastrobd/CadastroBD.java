/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cadastrobd;

import cadastro.model.PessoaFisicaDAO;
import cadastro.model.PessoaJuridicaDAO;
import cadastrobd.model.PessoaFisica;
import cadastrobd.model.PessoaJuridica;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author anaqu
 */
public class CadastroBD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException{
        Scanner sc = new Scanner(System.in);
        PessoaFisicaDAO pfDAO = new PessoaFisicaDAO();
        PessoaJuridicaDAO pjDAO = new PessoaJuridicaDAO();
        int opcao;
        String tipoPessoa, resposta;
        boolean deuCerto;
        
        do {
            System.out.println("==============================");
            System.out.println("1 - Incluir Pessoa");
            System.out.println("2 - Alterar Pessoa");
            System.out.println("3 - Excluir Pessoa");
            System.out.println("4 - Buscar pelo ID");
            System.out.println("5 - Exibir Todos");
            System.out.println("0 - Finalizar Programa");
            System.out.println("==============================");
            opcao = Integer.parseInt(sc.nextLine());
            switch(opcao){
                
                case 1:
                    System.out.println("F - Pessoas Físicas | J - Pessoas Jurídicas");
                    tipoPessoa = sc.nextLine();
                    if(tipoPessoa.equalsIgnoreCase("F")){
                        PessoaFisica pf = new PessoaFisica();
                        
                        System.out.println("Digite o nome da pessoa:");
                        String nome = sc.nextLine();
                        pf.setNome(nome);
                        System.out.println("Digite o logradouro da pessoa:");
                        String logradouro = sc.nextLine();
                        pf.setLogradouro(logradouro);
                        System.out.println("Digite a cidade da pessoa:");
                        String cidade = sc.nextLine();
                        pf.setCidade(cidade);
                        System.out.println("Digite o estado da pessoa:");
                        String estado = sc.nextLine();
                        pf.setEstado(estado);
                        System.out.println("Digite o telefone da pessoa: (11 dígitos)");
                        String telefone = sc.nextLine();
                        pf.setTelefone(telefone);
                        System.out.println("Digite o email da pessoa:");
                        String email = sc.nextLine();
                        pf.setEmail(email);
                        System.out.println("Digite o CPF da pessoa: (Sem ponto e traço)");
                        String cpf = sc.nextLine();
                        pf.setCpf(cpf);
                        
                        deuCerto = pfDAO.incluir(pf);
                        if(deuCerto){
                            System.out.println("Pessoa incluída com sucesso!");
                        } else {
                            System.out.println("Falha na inclusão de dados.");
                        }
                        
                    } else if (tipoPessoa.equalsIgnoreCase("J")){
                        PessoaJuridica pj = new PessoaJuridica();
                        
                        System.out.println("Digite o nome da empresa:");
                        String nome = sc.nextLine();
                        pj.setNome(nome);
                        System.out.println("Digite o logradouro da empresa:");
                        String logradouro = sc.nextLine();
                        pj.setLogradouro(logradouro);
                        System.out.println("Digite a cidade da empresa:");
                        String cidade = sc.nextLine();
                        pj.setCidade(cidade);
                        System.out.println("Digite o estado da empresa:");
                        String estado = sc.nextLine();
                        pj.setEstado(estado);
                        System.out.println("Digite o telefone da empresa:");
                        String telefone = sc.nextLine();
                        pj.setTelefone(telefone);
                        System.out.println("Digite o email da empresa:");
                        String email = sc.nextLine();
                        pj.setEmail(email);
                        System.out.println("Digite o CNPJ da empresa:");
                        String cnpj = sc.nextLine();
                        pj.setCnpj(cnpj);
                        
                        deuCerto = pjDAO.incluir(pj);
                        if(deuCerto){
                            System.out.println("Empresa incluída com sucesso!");
                        } else {
                            System.out.println("Falha na inclusão de dados.");
                        }
                    } else {
                        System.out.println("Opção inválida");
                    }
                    break;
                case 2:
                    System.out.println("F - Pessoas Físicas | J - Pessoas Jurídicas");
                    tipoPessoa = sc.nextLine();
                    if(tipoPessoa.equalsIgnoreCase("F")){
                        PessoaFisica pf = new PessoaFisica();
                        
                        System.out.println("Digite o ID da pessoa que você quer alterar:");
                        int id = Integer.parseInt(sc.nextLine());
                        pf = pfDAO.getPessoa(id);
                        
                        if(pf == null){
                            System.out.println("ID inexistente no banco de dados.");
                        } else {
                            pf.exibir();
                        
                            System.out.println("Deseja mudar o nome? (S/N)");
                            resposta = sc.nextLine();
                            if(resposta.equalsIgnoreCase("S")){
                                System.out.println("Entre com o novo nome:");
                                String nome = sc.nextLine();
                                pf.setNome(nome);
                            }
                            System.out.println("Deseja mudar o logradouro? (S/N)");
                            resposta = sc.nextLine();
                            if(resposta.equalsIgnoreCase("S")){
                                System.out.println("Entre com o novo logradouro:");
                                String logradouro = sc.nextLine();
                                pf.setLogradouro(logradouro);
                            }
                            System.out.println("Deseja mudar a cidade? (S/N)");
                            resposta = sc.nextLine();
                            if(resposta.equalsIgnoreCase("S")){
                                System.out.println("Entre com a nova cidade:");
                                String cidade = sc.nextLine();
                                pf.setCidade(cidade);
                            }
                            System.out.println("Deseja mudar o estado? (S/N)");
                            resposta = sc.nextLine();
                            if(resposta.equalsIgnoreCase("S")){
                                System.out.println("Entre com o novo estado:");
                                String estado = sc.nextLine();
                                pf.setEstado(estado);
                            }
                            System.out.println("Deseja mudar o telefone? (S/N)");
                            resposta = sc.nextLine();
                            if(resposta.equalsIgnoreCase("S")){
                                System.out.println("Entre com o novo telefone:");
                                String telefone = sc.nextLine();
                                pf.setTelefone(telefone);
                            }
                            System.out.println("Deseja mudar o email? (S/N)");
                            resposta = sc.nextLine();
                            if(resposta.equalsIgnoreCase("S")){
                                System.out.println("Entre com o novo email:");
                                String email = sc.nextLine();
                                pf.setEmail(email);
                            }
                            System.out.println("Deseja mudar o CPF? (S/N)");
                            resposta = sc.nextLine();
                            if(resposta.equalsIgnoreCase("S")){
                                System.out.println("Entre com o novo CPF:");
                                String cpf = sc.nextLine();
                                pf.setCpf(cpf);
                            }
                            deuCerto = pfDAO.alterar(pf);
                            if(deuCerto){
                                System.out.println("Pessoa alterada com sucesso!");
                            } else {
                                System.out.println("Falha na alteração de dados.");
                            }
                        }   
                    } else if (tipoPessoa.equalsIgnoreCase("J")){
                        PessoaJuridica pj = new PessoaJuridica();
                        
                        System.out.println("Digite o ID da empresa que você quer alterar:");
                        int id = Integer.parseInt(sc.nextLine());
                        pj = pjDAO.getPessoa(id);
                        if(pj == null){
                            System.out.println("ID inexistente no banco de dados.");
                        } else {
                            pj.exibir();
                        
                            System.out.println("Deseja mudar o nome? (S/N)");
                            resposta = sc.nextLine();
                            if(resposta.equalsIgnoreCase("S")){
                                System.out.println("Entre com o novo nome:");
                                String nome = sc.nextLine();
                                pj.setNome(nome);
                            }
                            System.out.println("Deseja mudar o logradouro? (S/N)");
                            resposta = sc.nextLine();
                            if(resposta.equalsIgnoreCase("S")){
                                System.out.println("Entre com o novo logradouro:");
                                String logradouro = sc.nextLine();
                                pj.setLogradouro(logradouro);
                            }
                            System.out.println("Deseja mudar a cidade? (S/N)");
                            resposta = sc.nextLine();
                            if(resposta.equalsIgnoreCase("S")){
                                System.out.println("Entre com a nova cidade:");
                                String cidade = sc.nextLine();
                                pj.setCidade(cidade);
                            }
                            System.out.println("Deseja mudar o estado? (S/N)");
                            resposta = sc.nextLine();
                            if(resposta.equalsIgnoreCase("S")){
                                System.out.println("Entre com o novo estado:");
                                String estado = sc.nextLine();
                                pj.setEstado(estado);
                            }
                            System.out.println("Deseja mudar o telefone? (S/N)");
                            resposta = sc.nextLine();
                            if(resposta.equalsIgnoreCase("S")){
                                System.out.println("Entre com o novo telefone:");
                                String telefone = sc.nextLine();
                                pj.setTelefone(telefone);
                            }
                            System.out.println("Deseja mudar o email? (S/N)");
                            resposta = sc.nextLine();
                            if(resposta.equalsIgnoreCase("S")){
                                System.out.println("Entre com o novo email:");
                                String email = sc.nextLine();
                                pj.setEmail(email);
                            }
                            System.out.println("Deseja mudar o CNPJ? (S/N)");
                            resposta = sc.nextLine();
                            if(resposta.equalsIgnoreCase("S")){
                                System.out.println("Entre com o novo CNPJ:");
                                String cnpj = sc.nextLine();
                                pj.setCnpj(cnpj);
                            }
                            deuCerto = pjDAO.alterar(pj);
                            if(deuCerto){
                                System.out.println("Pessoa alterada com sucesso!");
                            } else {
                                System.out.println("Falha na alteração de dados.");
                            }
                        }   
                    } else {
                        System.out.println("Opção inválida");
                    }
                    break;
                case 3:
                    System.out.println("F - Pessoas Físicas | J - Pessoas Jurídicas");
                    tipoPessoa = sc.nextLine();
                    if(tipoPessoa.equalsIgnoreCase("F")){
                        PessoaFisica pf = new PessoaFisica();
                        
                        System.out.println("Digite o ID da pessoa que você quer excluir:");
                        int id = Integer.parseInt(sc.nextLine());
                        pf = pfDAO.getPessoa(id);
                        
                        if(pf == null){
                            System.out.println("ID inexistente no bando de dados.");
                        } else {
                            pf.exibir();
                        
                            System.out.println("Certeza que dejesa excluir essa pessoa do banco de dados? (S/N)");
                            resposta = sc.nextLine();
                            if(resposta.equalsIgnoreCase("S")){
                                pfDAO.excluir(id);
                                System.out.println("Pessoa excluída do banco de dados.");
                            } else {
                                System.out.println("Exclusão cancelada.");
                            }
                            
                        }
                    }else if (tipoPessoa.equalsIgnoreCase("J")){
                        PessoaJuridica pj = new PessoaJuridica();
                        
                        System.out.println("Digite o ID da empresa que você quer excluir:");
                        int id = Integer.parseInt(sc.nextLine());
                        pj = pjDAO.getPessoa(id);
                        
                        if(pj == null){
                            System.out.println("ID inexistente no banco de dados.");
                        } else {
                            pj.exibir();
                        
                            System.out.println("Certeza que dejesa excluir essa empresa do banco de dados? (S/N)");
                            resposta = sc.nextLine();
                            if(resposta.equalsIgnoreCase("S")){
                                pjDAO.excluir(id);
                                System.out.println("Empresa excluída do banco de dados.");
                            } else {
                                System.out.println("Exclusão cancelada.");
                            }
                        }
                    } else {
                        System.out.println("Opção inválida");                        
                    }
                    break;
                case 4:
                    System.out.println("F - Pessoas Físicas | J - Pessoas Jurídicas");
                    tipoPessoa = sc.nextLine();
                    if(tipoPessoa.equalsIgnoreCase("F")){
                        PessoaFisica pf = new PessoaFisica();
                        
                        System.out.println("Digite o ID da pessoa que você quer ver:");
                        int id = Integer.parseInt(sc.nextLine());
                        pf = pfDAO.getPessoa(id);
                        pf.exibir();
                    } else if (tipoPessoa.equalsIgnoreCase("J")){
                        PessoaJuridica pj = new PessoaJuridica();
                        
                        System.out.println("Digite o ID da empresa que você quer ver:");
                        int id = Integer.parseInt(sc.nextLine());
                        pj = pjDAO.getPessoa(id);
                        pj.exibir(); 
                    } else {
                        System.out.println("Opção inválida");
                    }
                    break;
                case 5:
                    System.out.println("F - Pessoas Físicas | J - Pessoas Jurídicas");
                    tipoPessoa = sc.nextLine();
                    if(tipoPessoa.equalsIgnoreCase("F")){
                        List<PessoaFisica> listaPf = pfDAO.getPessoas();
                        
                        if(listaPf != null && !listaPf.isEmpty()){
                        System.out.println("Lista de todas as pessoas físicas no banco de dados:");
                        System.out.println("====================================================");
                        for(PessoaFisica pessoa : listaPf){
                        pessoa.exibir();
                        System.out.println("----------------------");
            }
        }
                    } else if (tipoPessoa.equalsIgnoreCase("J")){
                        List<PessoaJuridica> listaPj = pjDAO.getPessoas();
                        
                        if(listaPj != null && !listaPj.isEmpty()){
                        System.out.println("Lista de todas as empresas no banco de dados:");
                        System.out.println("======================================================");
                        for(PessoaJuridica pessoa : listaPj){
                        pessoa.exibir();
                        System.out.println("----------------------");
            }
        }
                    } else {
                        System.out.println("Opção inválida");
                    }
                    break;
                case 0:
                    System.out.println("Finalizando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }    
        } while (opcao != 0);
       sc.close(); 
    } 
}
