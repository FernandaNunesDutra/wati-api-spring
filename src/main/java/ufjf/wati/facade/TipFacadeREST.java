package ufjf.wati.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ufjf.wati.dao.TipDAO;
import ufjf.wati.dao.UserDAO;
import ufjf.wati.dto.TipsResponse;
import ufjf.wati.model.Tip;
import ufjf.wati.model.User;

import java.util.Date;
import java.util.List;

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
    public TipsResponse all(@RequestHeader("token") String token,
                              @RequestParam("date") @DateTimeFormat(pattern="yyyyMMdd") Date creationDate) {
        User user = userDao.findByToken(token);

        List<Tip> tips;

        if (creationDate == null) {
            tips = tipDao.getAll();
        } else {
            tips = tipDao.getByDateRecommender(creationDate, user.getId());
        }

        return new TipsResponse(tips);
    }
}
