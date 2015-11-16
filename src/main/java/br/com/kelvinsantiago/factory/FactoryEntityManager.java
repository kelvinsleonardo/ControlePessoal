package br.com.kelvinsantiago.factory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class FactoryEntityManager {

    private static final String PERSISTENCE_UNIT_NAME = "controlepessoal";
    private static EntityManagerFactory fabrica = null;

    public static EntityManagerFactory getEntityManagerFactory() {
        if(fabrica == null){

          fabrica  = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }
        return fabrica;
    }
}
