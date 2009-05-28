/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bruno.elbruto.db.persistencia;

import com.bruno.elbruto.db.persistencia.exceptions.NonexistentEntityException;
import com.bruno.elbruto.db.persistencia.exceptions.PreexistingEntityException;
import com.bruno.elbruto.manager.Bruto;
import com.bruno.elbruto.manager.Pelea;
import java.util.Date;
import java.util.LinkedList;
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
public class BrutoJpaController {

    public BrutoJpaController() {
        emf = Persistence.createEntityManagerFactory("TestDJPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Bruto bruto) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(bruto);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findBruto(bruto.getNombre()) != null) {
                throw new PreexistingEntityException("Bruto " + bruto + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Bruto bruto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            bruto = em.merge(bruto);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = bruto.getNombre();
                if (findBruto(id) == null) {
                    throw new NonexistentEntityException("The bruto with id " + id + " no longer exists.");
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
            Bruto bruto;
            try {
                bruto = em.getReference(Bruto.class, id);
                bruto.getNombre();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The bruto with id " + id + " no longer exists.", enfe);
            }
            em.remove(bruto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public LinkedList<Bruto> findRivales(Bruto bruto, int cant) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Bruto as o where (o.nivel BETWEEN  :num1 and :num2) and o.nombre <> :nom").setParameter("num1", bruto.getNivel() - 2).setParameter("num2", bruto.getNivel()).setParameter("nom", bruto.getNombre());
            if (!true) {
                q.setMaxResults(3);
                q.setFirstResult(-1);
            }
            LinkedList<Bruto> rivales = new LinkedList<Bruto>(q.getResultList());
            if (cant < 3) {
                q = em.createQuery("select object(o) from Pelea as o where o.fecha = :f and o.bruto = :b").setParameter("f", new Date()).setParameter("b", bruto);
                List<Pelea> peleas = q.getResultList();
                for (Pelea pelea : peleas) {
                    if (rivales.contains(pelea.getRival())) {
                        rivales.remove(pelea.getRival());
                    }
                }
            }
            return rivales;
        } finally {
            em.close();
        }
    }

    public List<Bruto> findBrutosPropietarios() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Bruto as o where o.propietario = :p").setParameter("p", true);
            if (!true) {
                q.setMaxResults(-1);
                q.setFirstResult(-1);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Bruto> findBrutoEntities() {
        return findBrutoEntities(true, -1, -1);
    }

    public List<Bruto> findBrutoEntities(int maxResults, int firstResult) {
        return findBrutoEntities(false, maxResults, firstResult);
    }

    private List<Bruto> findBrutoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Bruto as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Bruto findBruto(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Bruto.class, id);
        } finally {
            em.close();
        }
    }

    public int getBrutoCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Bruto as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
