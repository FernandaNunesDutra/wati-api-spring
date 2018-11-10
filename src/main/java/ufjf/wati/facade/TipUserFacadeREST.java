/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufjf.wati.facade;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufjf.wati.dao.TipUserDAO;
import ufjf.wati.dao.UserDAO;
import ufjf.wati.response.TipUserResponse;

import javax.transaction.Transactional;
import javax.ws.rs.core.MediaType;

/**
 * @author fernanda
 */
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
    public ResponseEntity like(@RequestHeader("token") String token, @RequestBody String tip) {
        boolean validate = userDao.validate(token);

        if (validate) {
            JsonParser parser = new JsonParser();
            JsonObject o = parser.parse(tip).getAsJsonObject();

            int tipId = o.get("id_tip").getAsInt();
            long userId = o.get("id_user").getAsLong();
            boolean like = o.get("like").getAsBoolean();

            tipUserDao.alter(tipId, userId, like);
            return ResponseEntity.ok(new TipUserResponse(tipId, userId));

        } else {
            return new ResponseEntity("Ação não permitida para esse usuário.", HttpStatus.FORBIDDEN);
        }
    }
}
