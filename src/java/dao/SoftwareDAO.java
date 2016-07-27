package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import modelo.Software;

/**
 *
 * @author marcelosiedler
 */
public class SoftwareDAO {

    EntityManager em;
    
    public SoftwareDAO() throws Exception {
        EntityManagerFactory emf;
        emf = Conexao.getConexao();
        em = emf.createEntityManager();
    }
    
    public void incluir(Software obj) throws Exception {
        try {
            em.getTransaction().begin();
            em.persist(obj);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
            
        }
        
    }

    public List<Software> listar() throws Exception {
        return em.createNamedQuery("Software.findAll").getResultList();
    }
    
    public void alterar(Software obj) throws Exception {
        
        try {
            em.getTransaction().begin();
            em.merge(obj);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
             em.close();
        }
    }
    
    public void excluir(Software obj) throws Exception {
        
        try {
            em.getTransaction().begin();
            em.remove(obj);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public void fechaEmf() {
        em.close();
        Conexao.closeConexao();
    }
    
    public Software buscarPorChavePrimaria(Integer codigo)
    {
        return em.find(Software.class, codigo);
    }
    
}
