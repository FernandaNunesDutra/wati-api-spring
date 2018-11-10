package ufjf.wati.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ufjf.wati.dao.ChallengeDAO;
import ufjf.wati.dao.UserDAO;
import ufjf.wati.dto.ChallengesResponse;
import ufjf.wati.model.Challenge;

import javax.ws.rs.QueryParam;
import java.util.Date;
import java.util.List;

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

    @GetMapping("/all")
    public ChallengesResponse all(@RequestHeader("token") String token,
                            @QueryParam("date") @DateTimeFormat(pattern="yyyyMMdd") Date creationDate) {
        userDao.validateUserToken(token);

        List<Challenge> challenges;

        if (creationDate == null) {
            challenges = challengeDao.getAll();
        } else {
            challenges = challengeDao.getByDate(creationDate);
        }

        return new ChallengesResponse(challenges);
    }
}
