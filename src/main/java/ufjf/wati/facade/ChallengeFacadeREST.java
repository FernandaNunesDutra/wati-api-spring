package ufjf.wati.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ufjf.wati.dao.ChallengeDAO;
import ufjf.wati.dao.UserDAO;
import ufjf.wati.dto.ChallengesResponse;
import ufjf.wati.model.Challenge;

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
                            @RequestParam("date") @DateTimeFormat(pattern="yyyyMMdd") Date creationDate) {
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
