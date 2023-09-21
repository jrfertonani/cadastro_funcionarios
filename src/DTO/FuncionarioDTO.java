
package DTO;


public class FuncionarioDTO {
    
    private int id;     
    private String nome,endereco,telefone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
     public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }  

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    
}
