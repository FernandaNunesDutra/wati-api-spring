package ufjf.wati.facade;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufjf.wati.dao.CigaretteDAO;
import ufjf.wati.dao.UserDAO;
import ufjf.wati.dto.RankingResponse;
import ufjf.wati.dto.TotalCigaretteResponse;
import ufjf.wati.model.Cigarette;
import ufjf.wati.model.CigarettesAverage;
import ufjf.wati.model.User;

import javax.transaction.Transactional;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author fernanda
 */
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
    public ResponseEntity postToday(@RequestHeader("token") String token, @RequestBody String cigarette) throws ParseException {

        userDao.validateUserToken(token);

        JsonParser parser = new JsonParser();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        JsonObject o = parser.parse(cigarette).getAsJsonObject();

        double packCigarettesPrice = o.get("pack_cigarettes_price").getAsDouble();
        String dateString = o.get("date_creation").getAsString();
        Date date = formatter.parse(dateString);
        int numCigarette = o.get("num_cigarette").getAsInt();
        double economized = o.get("economized").getAsDouble();
        double spent = o.get("spent").getAsDouble();
        Long userId = o.get("id_user").getAsLong();

        cigaretteDao.alter(new Cigarette(packCigarettesPrice, economized, spent, numCigarette, date, userId));

        return ResponseEntity.ok(cigaretteDao.getToday(userId));
    }

    @GetMapping("/today")
    public ResponseEntity getToday(@RequestHeader("token") String token) {
        Cigarette today;

        userDao.validateUserToken(token);

        User user = userDao.findByToken(token);
        today = cigaretteDao.getToday(user.getId());

        return cigarette(token, today);
    }

    @GetMapping("/total")
    public ResponseEntity total(@RequestHeader("token") String token) {
        userDao.validateUserToken(token);


        User user = userDao.findByToken(token);

        double spent = cigaretteDao.getTotalSpent(user.getId());
        long smokedTotal = cigaretteDao.getSmokedTotal(user.getId());
        long average = cigaretteDao.getAverage(user.getId());
        double economized = cigaretteDao.getTotalEconomized(user.getId());

        TotalCigaretteResponse total = new TotalCigaretteResponse(economized, spent, smokedTotal, average);

        return ResponseEntity.ok(total);
    }

    @GetMapping("/ranking")
    public ResponseEntity ranking(@RequestHeader("token") String token) {
        userDao.validateUserToken(token);

        User user = userDao.findByToken(token);
        List<CigarettesAverage> userCigarettes = cigaretteDao.getOneMonthAgoSmoked(user.getId());
        List<CigarettesAverage> averageCigarettes = cigaretteDao.getOneMonthAgoAverageSmoked();

        return ResponseEntity.ok(new RankingResponse(userCigarettes, averageCigarettes));

    }

    private ResponseEntity cigarette(String token, Cigarette cigarette) {
        userDao.validateUserToken(token);

        return ResponseEntity.ok(cigarette);
    }
}
