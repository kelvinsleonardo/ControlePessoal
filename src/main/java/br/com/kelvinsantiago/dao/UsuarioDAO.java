package br.com.kelvinsantiago.dao;

import br.com.kelvinsantiago.factory.FactoryEntityManager;
import br.com.kelvinsantiago.model.Usuario;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;


@Repository
public class UsuarioDAO {

    public Boolean adicionar(Usuario usuario){
        EntityManager manager = FactoryEntityManager.getEntityManagerFactory().createEntityManager();
        try{
            manager.getTransaction().begin();
            manager.persist(usuario);
            manager.getTransaction().commit();
            return true;
        }catch(Exception e){
            return false;
        }finally{
            manager.close();
        }
    }

    public Boolean editar(Usuario usuario){
        EntityManager manager = FactoryEntityManager.getEntityManagerFactory().createEntityManager();
        try{
            manager.getTransaction().begin();
            Usuario usuariobuscado = manager.find(Usuario.class, usuario.getId());
            manager.merge(usuario);
            manager.getTransaction().commit();
            return true;
        }catch(Exception e){
            return false;
        }finally{
            manager.close();
        }
    }

    public Boolean remover(long id){
        EntityManager manager = FactoryEntityManager.getEntityManagerFactory().createEntityManager();
        try{

            manager.getTransaction().begin();
            Usuario usuariobuscado = manager.find(Usuario.class, id);
            manager.remove(usuariobuscado);
            manager.getTransaction().commit();
            return true;

        }catch (Exception e){
            return false;
        }finally {
            manager.close();
        }
    }

    public Usuario buscarPeloCPF(String cpf){
        EntityManager manager = FactoryEntityManager.getEntityManagerFactory().createEntityManager();
        try{
            manager.getTransaction().begin();
            TypedQuery<Usuario> typedQuery = manager.createNamedQuery("Usuario.buscarPeloCPF", Usuario.class);
            typedQuery.setParameter("cpf", cpf);
            Usuario usuario = typedQuery.getSingleResult();
            return usuario;

        }catch (Exception e){
            return null;
        }finally {
            manager.close();
        }
    }

    public ArrayList<Usuario> getTodosUsuarios(){
        EntityManager manager = FactoryEntityManager.getEntityManagerFactory().createEntityManager();
        try{
            TypedQuery<Usuario> typedQuery = manager.createNamedQuery("Usuario.buscarTodosUsuarios", Usuario.class);
            ArrayList<Usuario> usuarios = (ArrayList<Usuario>) typedQuery.getResultList();
            return usuarios;

        }catch (Exception e){
            return null;
        }finally {
            manager.close();
        }
    }
}
