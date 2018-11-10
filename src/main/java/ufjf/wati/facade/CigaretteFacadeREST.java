package ufjf.wati.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ufjf.wati.dao.CigaretteDAO;
import ufjf.wati.dao.UserDAO;
import ufjf.wati.dto.CigaretteDto;
import ufjf.wati.dto.CigarettesAverageDto;
import ufjf.wati.dto.RankingResponse;
import ufjf.wati.dto.TotalCigaretteResponse;
import ufjf.wati.model.Cigarette;
import ufjf.wati.model.User;

import javax.transaction.Transactional;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RestController
@RequestMapping(value = "/cigarette", consumes = {MediaType.APPLICATION_JSON})
public class CigaretteFacadeREST {

    private UserDAO userDao;

    private CigaretteDAO cigaretteDao;

    @Autowired
    public CigaretteFacadeREST(UserDAO userDao, CigaretteDAO cigaretteDao) {
        this.userDao = userDao;
        this.cigaretteDao = cigaretteDao;
    }

    @PostMapping("/today")
    @Transactional
    public void postToday(@RequestHeader("token") String token, @RequestBody CigaretteDto dto) {
        userDao.validateUserToken(token);

        cigaretteDao.alter(new Cigarette(dto.getPackCigarettesPrice(), dto.getEconomized(), dto.getSpent(), dto.getNumCigarette(), dto.getDate(), dto.getUserId()));
    }

    @GetMapping("/today")
    public Cigarette getToday(@RequestHeader("token") String token) {
        User user = userDao.findByToken(token);

        return cigaretteDao.getToday(user.getId());
    }

    @GetMapping("/total")
    public TotalCigaretteResponse total(@RequestHeader("token") String token) {
        User user = userDao.findByToken(token);

        double spent = cigaretteDao.getTotalSpent(user.getId());
        long smokedTotal = cigaretteDao.getSmokedTotal(user.getId());
        long average = cigaretteDao.getAverage(user.getId());
        double economized = cigaretteDao.getTotalEconomized(user.getId());

        return new TotalCigaretteResponse(economized, spent, smokedTotal, average);
    }

    @GetMapping("/ranking")
    public RankingResponse ranking(@RequestHeader("token") String token) {

        User user = userDao.findByToken(token);
        List<CigarettesAverageDto> userCigarettes = cigaretteDao.getOneMonthAgoSmoked(user.getId());
        List<CigarettesAverageDto> averageCigarettes = cigaretteDao.getOneMonthAgoAverageSmoked();

        return new RankingResponse(userCigarettes, averageCigarettes);
    }
}
