package br.com.kelvinsantiago.dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.kelvinsantiago.model.Usuario;

/**
 * Classe DAO (Data Access Object) responsável por fazer a interqação com a camada de persistencia JPA
 */
@Repository
public class UsuarioDAO {

  @Autowired
  private EntityManager manager;

  @Transactional
  public Boolean adicionar(Usuario usuario) {
    manager.persist(usuario);
    return true;
  }

  @Transactional
  public Boolean editar(Usuario usuario) {
    manager.merge(usuario);
    return true;
  }

  @Transactional
  public Boolean remover(long id) {
    Usuario usuariobuscado = manager.find(Usuario.class, id);
    manager.remove(usuariobuscado);
    return true;
  }

  public Usuario buscarPeloCPF(String cpf) {
    TypedQuery<Usuario> typedQuery = manager.createNamedQuery("Usuario.buscarPeloCPF", Usuario.class);
    typedQuery.setParameter("cpf", cpf);
    Usuario usuario = typedQuery.getSingleResult();
    return usuario;
  }

  public ArrayList<Usuario> getTodosUsuarios() {
    TypedQuery<Usuario> typedQuery = manager.createNamedQuery("Usuario.buscarTodosUsuarios", Usuario.class);
    ArrayList<Usuario> usuarios = (ArrayList<Usuario>) typedQuery.getResultList();
    return usuarios;
  }
}
