/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufjf.wati.facade;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ufjf.wati.dao.CigaretteDAO;
import ufjf.wati.dao.UserDAO;
import ufjf.wati.model.Cigarette;
import ufjf.wati.model.CigarettesAverage;
import ufjf.wati.model.User;
import ufjf.wati.response.RankingResponse;
import ufjf.wati.response.TotalCigaretteResponse;

import javax.ws.rs.HeaderParam;
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
@RequestMapping(value = "/cigarette", consumes = { MediaType.APPLICATION_JSON })
public class CigaretteFacadeREST {

    private UserDAO userDao;

    private CigaretteDAO cigaretteDao;

    @Autowired
    public CigaretteFacadeREST(UserDAO userDao, CigaretteDAO cigaretteDao) {
        this.userDao = userDao;
        this.cigaretteDao = cigaretteDao;
    }

    @PostMapping("/today")
    public Response postToday(@HeaderParam("token") String token, String cigarette) {

        
        try{
            
            boolean validate = userDao.validate(token);

            if(validate){
                JsonParser parser = new JsonParser();
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                
                JsonObject o = parser.parse(cigarette).getAsJsonObject();
                
                double packCigarettesPrice =  o.get("pack_cigarettes_price").getAsDouble();
                String dateString = o.get("date_creation").getAsString();
                Date date = formatter.parse(dateString);
                int numCigarette = o.get("num_cigarette").getAsInt();
                double economized =  o.get("economized").getAsDouble();
                double spent =  o.get("spent").getAsDouble();
                int userId = o.get("id_user").getAsInt();

                cigaretteDao.alter(new Cigarette(packCigarettesPrice, economized, spent, numCigarette, date, userId));
                
                Gson gson = new Gson();
                String json = gson.toJson(cigaretteDao.getToday(userId));
                return Response.ok(json, MediaType.APPLICATION_JSON).build();  
            
            }else{
                return Response.status(Response.Status.FORBIDDEN).entity("Ação não permitida para esse usuário.").build();  
            }                  

        }catch(Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();  
        }
    }

    @GetMapping("/today")
    public Response getToday(@HeaderParam("token") String token) {
        Cigarette today = null;
        
        boolean validate = userDao.validate(token);

        if(validate){
            
            User user = userDao.findByToken(token);
            today = cigaretteDao.getToday((int)user.getId());
        }
        
        return cigarette(token, today);
    }

    @GetMapping("/total")
    public Response total(@HeaderParam("token") String token) {

        
        try{
            
            boolean validate = userDao.validate(token);

            if(validate){
                
                User user = userDao.findByToken(token);
                
                double spent = cigaretteDao.getTotalSpent(user.getId());
                long smokedTotal = cigaretteDao.getSmokedTotal(user.getId());
                long average = cigaretteDao.getAverage(user.getId());
                double economized = cigaretteDao.getTotalEconomized(user.getId());
                
                TotalCigaretteResponse total = new TotalCigaretteResponse(economized, spent, smokedTotal, average);
                
                Gson gson = new Gson();
                String json = gson.toJson(total);
                return Response.ok(json, MediaType.APPLICATION_JSON).build();  
            
            }else{
                return Response.status(Response.Status.FORBIDDEN).entity("Ação não permitida para esse usuário.").build();  
            }                  

        }catch(Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();  
        }
    }

    @GetMapping("/ranking")
    public Response ranking(@HeaderParam("token") String token) {

        
        try{
            
            boolean validate = userDao.validate(token);

            if(validate){

                
                User user = userDao.findByToken(token);
                List<CigarettesAverage> userCigarettes = cigaretteDao.getOneMonthAgoSmoked(user.getId());
                List<CigarettesAverage> averageCigarettes = cigaretteDao.getOneMonthAgoAverageSmoked();
                
                Gson gson = new Gson();
                String json = gson.toJson(new RankingResponse(userCigarettes, averageCigarettes));
                return Response.ok(json, MediaType.APPLICATION_JSON).build(); 
            
            }else{
                return Response.status(Response.Status.FORBIDDEN).entity("Ação não permitida para esse usuário.").build();  
            }                  

        }catch(Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();  
        }
    }
    
    private Response cigarette(String token, Cigarette cigarette){
        
        try{
            
            boolean validate = userDao.validate(token);

            if(validate){
                
                Gson gson = new Gson();
                String json = gson.toJson(cigarette);
                return Response.ok(json, MediaType.APPLICATION_JSON).build();  
            
            }else{
                return Response.status(Response.Status.FORBIDDEN).entity("Ação não permitida para esse usuário.").build();  
            }                  

        }catch(Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();  
        }
    }
}
