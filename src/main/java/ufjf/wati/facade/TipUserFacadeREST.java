package ufjf.wati.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ufjf.wati.dao.TipUserDAO;
import ufjf.wati.dao.UserDAO;
import ufjf.wati.dto.TipUserDto;

import javax.transaction.Transactional;
import javax.ws.rs.core.MediaType;

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

    @PostMapping(value = "/like", consumes = {MediaType.APPLICATION_JSON})
    @Transactional
    public void like(@RequestHeader("token") String token, @RequestBody TipUserDto dto) {
        userDao.validateUserToken(token);

        tipUserDao.alter(dto.getTipId(), dto.getUserId(), dto.isLike());
    }
}
