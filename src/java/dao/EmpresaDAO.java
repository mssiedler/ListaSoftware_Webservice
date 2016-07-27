package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import modelo.Empresa;

/**
 *
 * @author marcelosiedler
 */
public class EmpresaDAO {

    EntityManager em;
    
    public EmpresaDAO() throws Exception {
        EntityManagerFactory emf;
        emf = Conexao.getConexao();
        em = emf.createEntityManager();
    }
    
    public void incluir(Empresa obj) throws Exception {
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

    public List<Empresa> listar() throws Exception {
        return em.createNamedQuery("Empresa.findAll").getResultList();
    }
    
    public void alterar(Empresa obj) throws Exception {
        
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
    
    public void excluir(Empresa obj) throws Exception {
        
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
    
    public Empresa buscarPorChavePrimaria(Integer codigo)
    {
        return em.find(Empresa.class, codigo);
    }
    
}
