import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ExemploDao {

    public void salvar(Objeto exemplo) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("nomeDaUnidadeDePersistencia");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(exemplo);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        } finally {
            em.close();
            emf.close();
        }
    }
}
