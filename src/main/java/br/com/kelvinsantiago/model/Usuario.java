package br.com.kelvinsantiago.model;

import javax.persistence.*;
import java.io.Serializable;

@NamedQueries({
        @NamedQuery(name="Usuario.buscarTodosUsuarios",
                    query="SELECT usu FROM Usuario usu"),
        @NamedQuery(name="Usuario.buscarPeloCPF",
        query="SELECT user FROM Usuario user WHERE user.cpf LIKE :cpf")
})

/**
 * Classe Model (Bean) que representa uma entidade.
 * @author Kelvin Santiago
 */
@Entity
@Table(name = "tb_usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String cpf;

    private String nome;
    private String endereco;
    private String telefone;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
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

    @Override
    public String toString() {
        return "ID: " + this.id + ", " +
                "CPF: " + this.cpf + ", "+
                "Nome: " + this.nome + ", "+
                "Telefone:"+this.telefone + ", "+
                "Endereco:"+this.endereco;
    }
}

