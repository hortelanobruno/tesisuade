/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controladores;

import controladores.exceptions.NonexistentEntityException;
import entidades.Mercaderia;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;

/**
 *
 * @author Administrador
 */
public class MercaderiaJpaController {

    public MercaderiaJpaController() {
        emf = Persistence.createEntityManagerFactory("SistemaRegalosPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Mercaderia mercaderia) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(mercaderia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Mercaderia mercaderia) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            mercaderia = em.merge(mercaderia);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = mercaderia.getId();
                if (findMercaderia(id) == null) {
                    throw new NonexistentEntityException("The mercaderia with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mercaderia mercaderia;
            try {
                mercaderia = em.getReference(Mercaderia.class, id);
                mercaderia.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mercaderia with id " + id + " no longer exists.", enfe);
            }
            em.remove(mercaderia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroyByName(String name){
        EntityManager em = getEntityManager();
        try {
            em.createNamedQuery("Mercaderia.destroyByName").setParameter("name", name).executeUpdate();
        } finally {
            em.close();
        }
    }

    public List<Mercaderia> findMercaderiaEntities() {
        return findMercaderiaEntities(true, -1, -1);
    }

    public List<Mercaderia> findMercaderiaEntities(int maxResults, int firstResult) {
        return findMercaderiaEntities(false, maxResults, firstResult);
    }

    private List<Mercaderia> findMercaderiaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Mercaderia as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Mercaderia findMercaderia(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Mercaderia.class, id);
        } finally {
            em.close();
        }
    }

    public int getMercaderiaCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Mercaderia as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
