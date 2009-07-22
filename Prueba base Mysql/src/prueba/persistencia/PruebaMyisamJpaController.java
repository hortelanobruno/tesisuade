/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package prueba.persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import prueba.persistencia.exceptions.NonexistentEntityException;
import prueba.persistencia.exceptions.PreexistingEntityException;

/**
 *
 * @author Brunoli
 */
public class PruebaMyisamJpaController {

    public PruebaMyisamJpaController() {
        emf = Persistence.createEntityManagerFactory("Prueba_base_MysqlPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PruebaMyisam pruebaMyisam) throws PreexistingEntityException, Exception {
        if (pruebaMyisam.getPruebaMyisamPK() == null) {
            pruebaMyisam.setPruebaMyisamPK(new PruebaMyisamPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(pruebaMyisam);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPruebaMyisam(pruebaMyisam.getPruebaMyisamPK()) != null) {
                throw new PreexistingEntityException("PruebaMyisam " + pruebaMyisam + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PruebaMyisam pruebaMyisam) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            pruebaMyisam = em.merge(pruebaMyisam);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                PruebaMyisamPK id = pruebaMyisam.getPruebaMyisamPK();
                if (findPruebaMyisam(id) == null) {
                    throw new NonexistentEntityException("The pruebaMyisam with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(PruebaMyisamPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PruebaMyisam pruebaMyisam;
            try {
                pruebaMyisam = em.getReference(PruebaMyisam.class, id);
                pruebaMyisam.getPruebaMyisamPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pruebaMyisam with id " + id + " no longer exists.", enfe);
            }
            em.remove(pruebaMyisam);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PruebaMyisam> findPruebaMyisamEntities() {
        return findPruebaMyisamEntities(true, -1, -1);
    }

    public List<PruebaMyisam> findPruebaMyisamEntities(int maxResults, int firstResult) {
        return findPruebaMyisamEntities(false, maxResults, firstResult);
    }

    private List<PruebaMyisam> findPruebaMyisamEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from PruebaMyisam as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public PruebaMyisam findPruebaMyisam(PruebaMyisamPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PruebaMyisam.class, id);
        } finally {
            em.close();
        }
    }

    public int getPruebaMyisamCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from PruebaMyisam as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
