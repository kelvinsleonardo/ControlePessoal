package br.com.kelvinsantiago.factory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class FactoryEntityManager {

    private static EntityManagerFactory fabrica = null;

    public static EntityManagerFactory getEntityManagerFactory() {
        if(fabrica == null){

          fabrica  = Persistence.createEntityManagerFactory("controlepessoal");
        }
        return fabrica;
    }

}
