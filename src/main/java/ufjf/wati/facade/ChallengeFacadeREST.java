package ufjf.wati.facade;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ufjf.wati.dao.ChallengeDAO;
import ufjf.wati.dao.UserDAO;
import ufjf.wati.model.Challenge;
import ufjf.wati.response.ChallengesResponse;

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
@RequestMapping("/challenge")
public class ChallengeFacadeREST {

    private UserDAO userDao;

    private ChallengeDAO challengeDao;

    @Autowired
    public ChallengeFacadeREST(UserDAO userDao, ChallengeDAO challengeDao) {
        this.userDao = userDao;
        this.challengeDao = challengeDao;
    }

    @GetMapping("/koe")
    public String koe() {
        return "KOE";
    }

    @GetMapping("/all")
    public Response all(@HeaderParam("token") String token, @QueryParam("date") String date) {

        try{

            boolean validate = userDao.validate(token);

            if(validate){
                List<Challenge> challenges;
                Date creationDate = parseDate(date);
                
                if(creationDate == null){
                    challenges = challengeDao.getAll();
                }else{
                    challenges = challengeDao.getByDate(creationDate);
                }
                
                ChallengesResponse response = new ChallengesResponse(challenges);
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
