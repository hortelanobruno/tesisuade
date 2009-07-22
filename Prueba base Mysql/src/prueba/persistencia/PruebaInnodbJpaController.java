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
public class PruebaInnodbJpaController {

    public PruebaInnodbJpaController() {
        emf = Persistence.createEntityManagerFactory("Prueba_base_MysqlPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PruebaInnodb pruebaInnodb) throws PreexistingEntityException, Exception {
        if (pruebaInnodb.getPruebaInnodbPK() == null) {
            pruebaInnodb.setPruebaInnodbPK(new PruebaInnodbPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(pruebaInnodb);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPruebaInnodb(pruebaInnodb.getPruebaInnodbPK()) != null) {
                throw new PreexistingEntityException("PruebaInnodb " + pruebaInnodb + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PruebaInnodb pruebaInnodb) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            pruebaInnodb = em.merge(pruebaInnodb);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                PruebaInnodbPK id = pruebaInnodb.getPruebaInnodbPK();
                if (findPruebaInnodb(id) == null) {
                    throw new NonexistentEntityException("The pruebaInnodb with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(PruebaInnodbPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PruebaInnodb pruebaInnodb;
            try {
                pruebaInnodb = em.getReference(PruebaInnodb.class, id);
                pruebaInnodb.getPruebaInnodbPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pruebaInnodb with id " + id + " no longer exists.", enfe);
            }
            em.remove(pruebaInnodb);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PruebaInnodb> findPruebaInnodbEntities() {
        return findPruebaInnodbEntities(true, -1, -1);
    }

    public List<PruebaInnodb> findPruebaInnodbEntities(int maxResults, int firstResult) {
        return findPruebaInnodbEntities(false, maxResults, firstResult);
    }

    private List<PruebaInnodb> findPruebaInnodbEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from PruebaInnodb as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public PruebaInnodb findPruebaInnodb(PruebaInnodbPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PruebaInnodb.class, id);
        } finally {
            em.close();
        }
    }

    public int getPruebaInnodbCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from PruebaInnodb as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
