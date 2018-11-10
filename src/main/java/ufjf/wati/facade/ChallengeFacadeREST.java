package ufjf.wati.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ufjf.wati.dao.ChallengeDAO;
import ufjf.wati.dao.UserDAO;
import ufjf.wati.model.Challenge;
import ufjf.wati.response.ChallengesResponse;

import javax.ws.rs.QueryParam;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
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
    public ResponseEntity all(@RequestHeader("token") String token, @QueryParam("date") String date) {
        boolean validate = userDao.validate(token);

        if (validate) {
            List<Challenge> challenges;
            Date creationDate = parseDate(date);

            if (creationDate == null) {
                challenges = challengeDao.getAll();
            } else {
                challenges = challengeDao.getByDate(creationDate);
            }

            ChallengesResponse response = new ChallengesResponse(challenges);
            return ResponseEntity.ok(response);

        } else {
            return new ResponseEntity("Ação não permitida para esse usuário.", HttpStatus.FORBIDDEN);
        }
    }

    private Date parseDate(String date) {
        Date creationDate;

        try {

            creationDate = new SimpleDateFormat("yyyyMMdd").parse(date);

        } catch (Exception e) {

            creationDate = null;

        }

        return creationDate;
    }
}
