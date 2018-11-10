package ufjf.wati.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ufjf.wati.dao.TipDAO;
import ufjf.wati.dao.UserDAO;
import ufjf.wati.dto.TipsResponse;
import ufjf.wati.model.Tip;

import javax.ws.rs.QueryParam;
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
                              @QueryParam("date") @DateTimeFormat(pattern="yyyyMMdd") Date creationDate) {
        userDao.validateUserToken(token);

        List<Tip> tips;

        if (creationDate == null) {
            tips = tipDao.getAll();
        } else {
            tips = tipDao.getByDate(creationDate);
        }

        return new TipsResponse(tips);
    }
}
