package persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Objects;

public class JPAUtil {

   /* var emf = Persistence.createEntityManagerFactory("cardapio");
    var em = emf.createEntityManager();
        em.clear();
        conexao com o banco direito pelo o Main
*/



    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("cardapio");
    private EntityManager entityManager;
    public EntityManager getEntityManager(){
        if (Objects.isNull(entityManager)){
            entityManager = emf.createEntityManager();
        }
        return entityManager;
    }




}
