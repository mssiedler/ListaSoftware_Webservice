/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import com.google.gson.Gson;
import dao.SoftwareDAO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import modelo.Software;


/**
 * REST Web Service
 *
 * @author marcelosiedler
 */
@Path("software")
public class SoftwareWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of SoftwareWS
     */
    public SoftwareWS() {
    }

   @GET
    @Produces("application/json")
    @Path("get/{codigo}")
    public String get(@PathParam("codigo") String codigo)
    {
        Software obj = new Software();
        
        
        SoftwareDAO dao;
        try {
            dao = new SoftwareDAO();
            obj = dao.buscarPorChavePrimaria(Integer.parseInt(codigo));
            dao.fechaEmf();
        } catch (Exception ex) {
            Logger.getLogger(SoftwareWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Converter para Gson
        Gson g = new Gson();
        return g.toJson(obj);
    }
    @GET
    @Produces("application/json")
    @Path("list")
    public String list()
    {
        try {
            List<Software> lista;
            
            SoftwareDAO dao = new SoftwareDAO();
            lista = dao.listar();
            
            //Converter para Gson
            Gson g = new Gson();
            return g.toJson(lista);
        } catch (Exception ex) {
            Logger.getLogger(SoftwareWS.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    @POST
    @Consumes({"application/json"})
    @Path("insert")
public boolean insert(String content){
     Gson g = new Gson();
    Software u = (Software) g.fromJson(content, Software.class);
        SoftwareDAO dao;  
        try {
            dao = new SoftwareDAO();
             dao.incluir(u);
             dao.fechaEmf();
             return true;
        } catch (Exception ex) {
            Logger.getLogger(SoftwareWS.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        
        
}
    
    /**
     * PUT method for updating or creating an instance of FazendaWS
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    @Path("update")
    public boolean update(String content) {
        Gson g = new Gson();
        Software u = (Software) g.fromJson(content, Software.class);
        SoftwareDAO dao;  
        try {
            dao = new SoftwareDAO();
            dao.alterar(u);
            dao.fechaEmf();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SoftwareWS.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
   @DELETE
   @Path("delete/{codigo}")
    public boolean delete(@PathParam("codigo") String codigo) 
    {    
        Software u = new Software();
        
        
        SoftwareDAO dao;
        try {
            dao = new SoftwareDAO();
            u = dao.buscarPorChavePrimaria(Integer.parseInt(codigo));
            dao.excluir(u);
            dao.fechaEmf();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SoftwareWS.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }

}
