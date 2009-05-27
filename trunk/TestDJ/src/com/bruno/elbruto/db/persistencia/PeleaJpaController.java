/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bruno.elbruto.db.persistencia;

import com.bruno.elbruto.db.persistencia.exceptions.NonexistentEntityException;
import com.bruno.elbruto.db.persistencia.exceptions.PreexistingEntityException;
import com.bruno.elbruto.manager.Bruto;
import com.bruno.elbruto.manager.Pelea;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;

/**
 *
 * @author Administrator
 */
public class PeleaJpaController {

    public PeleaJpaController() {
        emf = Persistence.createEntityManagerFactory("TestDJPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pelea pelea) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(pelea);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPelea(pelea.getBruto()) != null) {
                throw new PreexistingEntityException("Pelea " + pelea + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pelea pelea) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            pelea = em.merge(pelea);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Bruto id = pelea.getBruto();
                if (findPelea(id) == null) {
                    throw new NonexistentEntityException("The pelea with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Bruto id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pelea pelea;
            try {
                pelea = em.getReference(Pelea.class, id);
                pelea.getBruto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pelea with id " + id + " no longer exists.", enfe);
            }
            em.remove(pelea);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pelea> findPeleaEntities() {
        return findPeleaEntities(true, -1, -1);
    }

    public List<Pelea> findPeleaEntities(int maxResults, int firstResult) {
        return findPeleaEntities(false, maxResults, firstResult);
    }

    private List<Pelea> findPeleaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Pelea as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Pelea findPelea(Bruto id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pelea.class, id);
        } finally {
            em.close();
        }
    }

    public int getPeleaCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Pelea as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
