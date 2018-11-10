package ufjf.wati.facade;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufjf.wati.dao.CigaretteDAO;
import ufjf.wati.dao.UserDAO;
import ufjf.wati.model.Cigarette;
import ufjf.wati.model.CigarettesAverage;
import ufjf.wati.model.User;
import ufjf.wati.response.RankingResponse;
import ufjf.wati.response.TotalCigaretteResponse;

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

        boolean validate = userDao.validate(token);

        if (validate) {
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

        } else {
            return new ResponseEntity("Ação não permitida para esse usuário.", HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/today")
    public ResponseEntity getToday(@RequestHeader("token") String token) {
        Cigarette today = null;

        boolean validate = userDao.validate(token);

        if (validate) {

            User user = userDao.findByToken(token);
            today = cigaretteDao.getToday(user.getId());
        }

        return cigarette(token, today);
    }

    @GetMapping("/total")
    public ResponseEntity total(@RequestHeader("token") String token) {
        boolean validate = userDao.validate(token);

        if (validate) {

            User user = userDao.findByToken(token);

            double spent = cigaretteDao.getTotalSpent(user.getId());
            long smokedTotal = cigaretteDao.getSmokedTotal(user.getId());
            long average = cigaretteDao.getAverage(user.getId());
            double economized = cigaretteDao.getTotalEconomized(user.getId());

            TotalCigaretteResponse total = new TotalCigaretteResponse(economized, spent, smokedTotal, average);

            return ResponseEntity.ok(total);

        } else {
            return new ResponseEntity("Ação não permitida para esse usuário.", HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/ranking")
    public ResponseEntity ranking(@RequestHeader("token") String token) {
        boolean validate = userDao.validate(token);

        if (validate) {


            User user = userDao.findByToken(token);
            List<CigarettesAverage> userCigarettes = cigaretteDao.getOneMonthAgoSmoked(user.getId());
            List<CigarettesAverage> averageCigarettes = cigaretteDao.getOneMonthAgoAverageSmoked();

            return ResponseEntity.ok(new RankingResponse(userCigarettes, averageCigarettes));

        } else {
            return new ResponseEntity("Ação não permitida para esse usuário.", HttpStatus.FORBIDDEN);
        }
    }

    private ResponseEntity cigarette(String token, Cigarette cigarette) {
        boolean validate = userDao.validate(token);

        if (validate) {
            return ResponseEntity.ok(cigarette);
        } else {
            return new ResponseEntity("Ação não permitida para esse usuário.", HttpStatus.FORBIDDEN);
        }
    }
}
