/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bruno.elbruto.db.persistencia.controller;

import com.bruno.elbruto.db.persistencia.controller.exceptions.NonexistentEntityException;
import com.bruno.elbruto.db.persistencia.controller.exceptions.PreexistingEntityException;
import com.bruno.elbruto.db.persistencia.entities.Nombre;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;

/**
 *
 * @author Admin
 */
public class NombreJpaController {

    public NombreJpaController() {
        emf = Persistence.createEntityManagerFactory("TestDJPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Nombre nombre) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(nombre);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findNombre(nombre.getNombre()) != null) {
                throw new PreexistingEntityException("Nombre " + nombre + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Nombre nombre) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            nombre = em.merge(nombre);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = nombre.getNombre();
                if (findNombre(id) == null) {
                    throw new NonexistentEntityException("The nombre with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Nombre nombre;
            try {
                nombre = em.getReference(Nombre.class, id);
                nombre.getNombre();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The nombre with id " + id + " no longer exists.", enfe);
            }
            em.remove(nombre);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Nombre> findNombreEntities() {
        return findNombreEntities(true, -1, -1);
    }

    public List<Nombre> findNombreEntities(int maxResults, int firstResult) {
        return findNombreEntities(false, maxResults, firstResult);
    }

    private List<Nombre> findNombreEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Nombre as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Nombre findNombre(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Nombre.class, id);
        } finally {
            em.close();
        }
    }

    //SELECT nombre FROM nombres ORDER BY RAND() LIMIT 1
    public String random() {
        List<Nombre> nombres = findNombreEntities();
        int size = nombres.size();
        int ran = (int) (Math.random() * size);
        return nombres.get(ran).getNombre();
    }

    public int getNombreCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Nombre as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
