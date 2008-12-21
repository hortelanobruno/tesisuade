/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jpa.controllers;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import jpa.controllers.exceptions.NonexistentEntityException;
import jpa.controllers.exceptions.PreexistingEntityException;
import jpa.entities.Recibos;
import jpa.entities.RecibosPK;

/**
 *
 * @author Administrador
 */
public class RecibosJpaController {

    public RecibosJpaController() {
        emf = Persistence.createEntityManagerFactory("WebApplicationSeclaPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Recibos recibos) throws PreexistingEntityException, Exception {
        if (recibos.getRecibosPK() == null) {
            recibos.setRecibosPK(new RecibosPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(recibos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRecibos(recibos.getRecibosPK()) != null) {
                throw new PreexistingEntityException("Recibos " + recibos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Recibos recibos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            recibos = em.merge(recibos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                RecibosPK id = recibos.getRecibosPK();
                if (findRecibos(id) == null) {
                    throw new NonexistentEntityException("The recibos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(RecibosPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Recibos recibos;
            try {
                recibos = em.getReference(Recibos.class, id);
                recibos.getRecibosPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The recibos with id " + id + " no longer exists.", enfe);
            }
            em.remove(recibos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Recibos> findRecibosEntities() {
        return findRecibosEntities(true, -1, -1);
    }

    public List<Recibos> findRecibosEntities(int maxResults, int firstResult) {
        return findRecibosEntities(false, maxResults, firstResult);
    }

    private List<Recibos> findRecibosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Recibos as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Recibos findRecibos(RecibosPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Recibos.class, id);
        } finally {
            em.close();
        }
    }

    public Recibos findRecibosByNumero(String numero) {
        EntityManager em = getEntityManager();
        try {
            return (Recibos) em.createNamedQuery("Recibos.findByNumero").setParameter("numero", Integer.parseInt(numero)).getSingleResult();
        } finally {
            em.close();
        }
    }

    public List<Integer> obtenerRecibosConfirmados() {
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("Recibos.obtenerRecibosConfirmados").setMaxResults(100).getResultList();
        } finally {
            em.close();
        }
    }

    public List<Integer> obtenerRecibosPendientes(String usuario) {
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("Recibos.obtenerRecibosPendientes").setParameter("usuario", usuario).getResultList();
        } finally {
            em.close();
        }
    }

    public List<Recibos> obtenerRecibosAConfirmar(String usuario) {
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("Recibos.obtenerRecibosAConfirmar").setParameter("usuario", usuario).getResultList();
        } finally {
            em.close();
        }
    }

    public List<Recibos> obtenerRecibosExtraviadas(String usuario) {
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("Recibos.obtenerRecibosExtraviadas").setParameter("usuario", usuario).getResultList();
        } finally {
            em.close();
        }
    }

    public List<Recibos> obtenerRecibosAnuladas(String usuario) {
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("Recibos.obtenerRecibosAnuladas").setParameter("usuario", usuario).getResultList();
        } finally {
            em.close();
        }
    }

    public int existeRecibo(String num1, String num2){
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createNamedQuery("Recibos.existeRecibo").setParameter("num1", Integer.parseInt(num1)).setParameter("num2", Integer.parseInt(num2)).getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }


    public List<Recibos> obtenerRecibosExtraviadasRendidos(String usuario) {
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("Recibos.obtenerRecibosExtraviadasRendidos").setParameter("usuario", usuario).setMaxResults(50).getResultList();
        } finally {
            em.close();
        }
    }

    public List<Recibos> obtenerRecibosAnuladasRendidos(String usuario) {
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("Recibos.obtenerRecibosAnuladasRendidos").setParameter("usuario", usuario).setMaxResults(50).getResultList();
        } finally {
            em.close();
        }
    }

    public List<Recibos> obtenerRecibosCompletadasRendidos(String usuario) {
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("Recibos.obtenerRecibosCompletadasRendidos").setParameter("usuario", usuario).setMaxResults(50).getResultList();
        } finally {
            em.close();
        }
    }

    public List<Recibos> obtenerRecibosCompletadas(String usuario) {
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("Recibos.obtenerRecibosCompletadas").setParameter("usuario", usuario).getResultList();
        } finally {
            em.close();
        }
    }

    public int chequearBorrarResponsable(String usuario){
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createNamedQuery("Recibos.chequearBorrarResponsable").setParameter("usuario", usuario).getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public int getRecibosCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Recibos as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Double totalEfectivoOperador(String usuario) {
        EntityManager em = getEntityManager();
        try {
            return (Double) em.createNamedQuery("Recibos.totalEfectivoOperador").setParameter("usuario", usuario).getSingleResult();
        } finally {
            em.close();
        }
    }

    public Double totalChequeOperador(String usuario) {
        EntityManager em = getEntityManager();
        try {
            return (Double) em.createNamedQuery("Recibos.totalChequeOperador").setParameter("usuario", usuario).getSingleResult();
        } finally {
            em.close();
        }
    }

}
