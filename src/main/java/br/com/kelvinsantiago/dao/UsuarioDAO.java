package br.com.kelvinsantiago.dao;

import java.util.ArrayList;

import javax.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.kelvinsantiago.model.Usuario;

/**
 * Classe DAO (Data Access Object) responsável por fazer a interqação com a camada de persistencia JPA
 */
@Repository
@Transactional
public class UsuarioDAO {

  @PersistenceUnit
  private EntityManagerFactory factory;

  private EntityManager getEntityManager(){
    return factory.createEntityManager();
  }

  public Boolean adicionar(Usuario usuario) {
    getEntityManager().persist(usuario);
    return true;
  }

  public Boolean editar(Usuario usuario) {
    getEntityManager().merge(usuario);
    return true;
  }

  public Boolean remover(long id) {
    Usuario usuariobuscado = getEntityManager().find(Usuario.class, id);
    getEntityManager().remove(usuariobuscado);
    return true;
  }

    @Transactional(readOnly = true)
  public Usuario buscarPeloCPF(String cpf) {
    TypedQuery<Usuario> typedQuery = getEntityManager().createNamedQuery("Usuario.buscarPeloCPF", Usuario.class);
    typedQuery.setParameter("cpf", cpf);
    Usuario usuario = typedQuery.getSingleResult();
    return usuario;
  }

    @Transactional(readOnly = true)
  public ArrayList<Usuario> getTodosUsuarios() {
    TypedQuery<Usuario> typedQuery = getEntityManager().createNamedQuery("Usuario.buscarTodosUsuarios", Usuario.class);
    ArrayList<Usuario> usuarios = (ArrayList<Usuario>) typedQuery.getResultList();
    return usuarios;
  }
}
