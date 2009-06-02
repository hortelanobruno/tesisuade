/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bruno.elbruto.db.persistencia.controller;

import com.bruno.elbruto.db.persistencia.controller.exceptions.NonexistentEntityException;
import com.bruno.elbruto.db.persistencia.controller.exceptions.PreexistingEntityException;
import com.bruno.elbruto.db.persistencia.entities.PeleasCompletadas;
import com.bruno.elbruto.db.persistencia.entities.PeleasCompletadasPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;

/**
 *
 * @author Brunoli
 */
public class PeleasCompletadasJpaController {

    public PeleasCompletadasJpaController() {
        emf = Persistence.createEntityManagerFactory("TestDJPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PeleasCompletadas peleasCompletadas) throws PreexistingEntityException, Exception {
        if (peleasCompletadas.getPeleasCompletadasPK() == null) {
            peleasCompletadas.setPeleasCompletadasPK(new PeleasCompletadasPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(peleasCompletadas);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPeleasCompletadas(peleasCompletadas.getPeleasCompletadasPK()) != null) {
                throw new PreexistingEntityException("PeleasCompletadas " + peleasCompletadas + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PeleasCompletadas peleasCompletadas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            peleasCompletadas = em.merge(peleasCompletadas);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                PeleasCompletadasPK id = peleasCompletadas.getPeleasCompletadasPK();
                if (findPeleasCompletadas(id) == null) {
                    throw new NonexistentEntityException("The peleasCompletadas with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(PeleasCompletadasPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PeleasCompletadas peleasCompletadas;
            try {
                peleasCompletadas = em.getReference(PeleasCompletadas.class, id);
                peleasCompletadas.getPeleasCompletadasPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The peleasCompletadas with id " + id + " no longer exists.", enfe);
            }
            em.remove(peleasCompletadas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PeleasCompletadas> findPeleasCompletadasEntities() {
        return findPeleasCompletadasEntities(true, -1, -1);
    }

    public List<PeleasCompletadas> findPeleasCompletadasEntities(int maxResults, int firstResult) {
        return findPeleasCompletadasEntities(false, maxResults, firstResult);
    }

    private List<PeleasCompletadas> findPeleasCompletadasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from PeleasCompletadas as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public PeleasCompletadas findPeleasCompletadas(PeleasCompletadasPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PeleasCompletadas.class, id);
        } finally {
            em.close();
        }
    }

    public int getPeleasCompletadasCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from PeleasCompletadas as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
