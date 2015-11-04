package br.com.kelvinsantiago.model;



import javax.persistence.*;
import java.io.Serializable;

@NamedQueries({
        @NamedQuery(name="Usuario.buscarTodosUsuarios",
                    query="SELECT usu FROM Usuario usu")
})

@Entity
@Table(name = "tb_usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private long cpf;
    private String nome;
    private String endereco;
    private String telefone;

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

}
