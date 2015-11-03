package br.com.kelvinsantiago.dao;

import br.com.kelvinsantiago.factory.FactoryEntityManager;
import br.com.kelvinsantiago.model.Usuario;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
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
            Usuario usuariobuscado = manager.find(Usuario.class, usuario.getCpf());
            manager.merge(usuario);
            manager.getTransaction().commit();
            return true;
        }catch(Exception e){
            return false;
        }finally{
            manager.close();
        }
    }

    public Boolean remover(Usuario usuario){
        EntityManager manager = FactoryEntityManager.getEntityManagerFactory().createEntityManager();
        try{

            manager.getTransaction().begin();
            Usuario usuariobuscado = manager.find(Usuario.class, usuario.getCpf());
            manager.remove(usuariobuscado);
            manager.getTransaction().commit();
            System.out.println("Removendo usuario");
            return true;

        }catch (Exception e){
            return false;
        }finally {
            manager.close();
        }
    }

    public Usuario buscar(Usuario usuario){
        EntityManager manager = FactoryEntityManager.getEntityManagerFactory().createEntityManager();
        try{
            manager.getTransaction().begin();
            Usuario usuariobuscado = manager.find(Usuario.class, usuario.getCpf());
            return usuariobuscado;

        }catch (Exception e){
            return usuario;
        }finally {
            manager.close();
        }
    }

    public ArrayList<Usuario> getTodosUsuarios(){
        EntityManager manager = FactoryEntityManager.getEntityManagerFactory().createEntityManager();
        TypedQuery<Usuario> typedQuery = manager.createNamedQuery("Usuario.buscarTodosUsuarios", Usuario.class);
        ArrayList<Usuario> usuarios = (ArrayList<Usuario>) typedQuery.getResultList();
        return usuarios;
    }

}
