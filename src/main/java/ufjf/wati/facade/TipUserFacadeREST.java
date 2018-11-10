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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ufjf.wati.dao.TipUserDAO;
import ufjf.wati.dao.UserDAO;
import ufjf.wati.response.TipUserResponse;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author fernanda
 */
@RestController
@RequestMapping("/tipuser")
public class TipUserFacadeREST {

    private UserDAO userDao;

    private TipUserDAO tipUserDao;

    @Autowired
    public TipUserFacadeREST(UserDAO userDao, TipUserDAO tipUserDao) {
        this.userDao = userDao;
        this.tipUserDao = tipUserDao;
    }

    @PostMapping(value = "/like", consumes = { MediaType.APPLICATION_JSON })
    public Response like(@HeaderParam("token") String token, String tip) {
        try{
            
            boolean validate = userDao.validate(token);

            if(validate){
                JsonParser parser = new JsonParser();                
                JsonObject o = parser.parse(tip).getAsJsonObject();
                
                int tipId =  o.get("id_tip").getAsInt();
                long userId =  o.get("id_user").getAsLong();
                boolean like =  o.get("like").getAsBoolean();

                tipUserDao.alter(tipId, userId, like);
                
                Gson gson = new Gson();
                String json = gson.toJson(new TipUserResponse(tipId, userId));
                
                return Response.ok(json, MediaType.APPLICATION_JSON).build();   
            
            }else{
                return Response.status(Response.Status.FORBIDDEN).entity("Ação não permitida para esse usuário.").build();  
            }                  

        }catch(Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();  
        }
    }
}
