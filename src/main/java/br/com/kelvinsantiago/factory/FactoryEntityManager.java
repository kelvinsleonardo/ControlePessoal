package br.com.kelvinsantiago.factory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Classe utiliza o padrão de projeto (Design Pattern) Singleton para fornecer apenas
 * uma instancia do EntityManagerFactory
 */
public class FactoryEntityManager {

    private static EntityManagerFactory fabrica = null;

    private static final String PERSISTENCE_UNIT_NAME = "controlepessoal";

    public static EntityManagerFactory getEntityManagerFactory() {
        if(fabrica == null){

          fabrica  = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }
        return fabrica;
    }
}
