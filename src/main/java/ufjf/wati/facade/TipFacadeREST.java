/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufjf.wati.facade;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ufjf.wati.dao.TipDAO;
import ufjf.wati.dao.UserDAO;
import ufjf.wati.model.Tip;
import ufjf.wati.response.TipsResponse;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author fernanda
 */
@RestController
@RequestMapping("/tip")
public class TipFacadeREST {

    private UserDAO userDao;

    private TipDAO tipDao;

    @Autowired
    public TipFacadeREST(UserDAO userDao, TipDAO tipDao) {
        this.userDao = userDao;
        this.tipDao = tipDao;
    }

    @GetMapping("/all")
    public Response all(@HeaderParam("token") String token, @QueryParam("date") String date) {
        try{

            boolean validate = userDao.validate(token);

            if(validate){

                List<Tip> tips;
                Date creationDate = parseDate(date);
                
                if(creationDate == null) {
                    tips = tipDao.getAll();
                }else{
                    tips = tipDao.getByDate(creationDate);
                }          
                
                TipsResponse response = new TipsResponse(tips);
                Gson gson = new Gson();
                String json = gson.toJson(response);
                
                return Response.ok(json, MediaType.APPLICATION_JSON).build();   
            
            }else{
                return Response.status(Response.Status.FORBIDDEN).entity("Ação não permitida para esse usuário.").build();  
            }            

        }catch(Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();  
        }
    }
    
    private Date parseDate(String date){
        Date creationDate;
        
        try{
           
            creationDate = new SimpleDateFormat("yyyyMMdd").parse(date);
        
        }catch(Exception e){
            
           creationDate = null;
        
        }
        
        return creationDate;
    }
}