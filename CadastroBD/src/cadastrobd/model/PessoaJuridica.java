/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastrobd.model;

/**
 *
 * @author anaqu
 */
public class PessoaJuridica extends Pessoa{
    private String cnpj;

    public PessoaJuridica(){
        super();
    }
    public PessoaJuridica(int idPessoa, String nome, String logradouro, String cidade, String estado, String telefone, String email, String cnpj){
        super();
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    @Override
    public void exibir(){
        super.exibir();
        System.out.println("CNPJ: " + cnpj);
    }
}
