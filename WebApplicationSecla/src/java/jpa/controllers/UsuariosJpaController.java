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
import jpa.entities.Usuarios;

/**
 *
 * @author Administrador
 */
public class UsuariosJpaController {

    public UsuariosJpaController() {
        emf = Persistence.createEntityManagerFactory("WebApplicationSeclaPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuarios usuarios) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(usuarios);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUsuarios(usuarios.getUsuario()) != null) {
                throw new PreexistingEntityException("Usuarios " + usuarios + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuarios usuarios) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            usuarios = em.merge(usuarios);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = usuarios.getUsuario();
                if (findUsuarios(id) == null) {
                    throw new NonexistentEntityException("The usuarios with id " + id + " no longer exists.");
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
            Usuarios usuarios;
            try {
                usuarios = em.getReference(Usuarios.class, id);
                usuarios.getUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuarios with id " + id + " no longer exists.", enfe);
            }
            em.remove(usuarios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuarios> findUsuariosEntities() {
        return findUsuariosEntities(true, -1, -1);
    }

    public List<Usuarios> findUsuariosEntities(int maxResults, int firstResult) {
        return findUsuariosEntities(false, maxResults, firstResult);
    }

    private List<Usuarios> findUsuariosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Usuarios as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Usuarios findUsuarios(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuarios.class, id);
        } finally {
            em.close();
        }
    }

    public Usuarios findUsuariosByResponsable(String responsable) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createNamedQuery("Usuarios.findByResponsable").setParameter("responsable", responsable);
            return (Usuarios) query.getSingleResult();
        } finally {
            em.close();
        }
    }

    public int existsUsuarioByResponsable(String responsable) {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createNamedQuery("Usuarios.existsUsuarioByResponsable").setParameter("responsable", responsable).getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public int existDigitos(String digitos) {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createNamedQuery("Usuarios.existDigitos").setParameter("digitos", digitos).getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public int existUsuarioByDigitos(String responsable, String digitos) {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createNamedQuery("Usuarios.existUsuarioByDigitos").setParameter("responsable", responsable).setParameter("digitos", digitos).getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<String> findOperators() {
        EntityManager em = getEntityManager();
        try {
            return (em.createNamedQuery("Usuarios.findOperators").getResultList());
        } finally {
            em.close();
        }
    }

    public List<String> sectorList() {
        EntityManager em = getEntityManager();
        try {
            return (em.createNamedQuery("Usuarios.sectorList").getResultList());
        } finally {
            em.close();
        }
    }

    public List<String> operatorInspectorCajeroList() {
        EntityManager em = getEntityManager();
        try {
            return (em.createNamedQuery("Usuarios.operatorInspectorCajeroList").getResultList());
        } finally {
            em.close();
        }
    }

    public List<String> operatorInspectorList() {
        EntityManager em = getEntityManager();
        try {
            return (em.createNamedQuery("Usuarios.operatorInspectorList").getResultList());
        } finally {
            em.close();
        }
    }

    public List<String> operatorInspectorListUsuario() {
        EntityManager em = getEntityManager();
        try {
            return (em.createNamedQuery("Usuarios.operatorInspectorListUsuario").getResultList());
        } finally {
            em.close();
        }
    }

    public int getUsuariosCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Usuarios as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
