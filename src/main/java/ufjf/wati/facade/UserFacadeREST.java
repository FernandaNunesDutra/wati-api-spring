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
import ufjf.wati.dao.UserDAO;
import ufjf.wati.model.User;
import ufjf.wati.response.UserResponse;
import ufjf.wati.service.Authentication;

import javax.transaction.Transactional;
import javax.ws.rs.core.MediaType;

/**
 * @author fernanda
 */
@RestController
@RequestMapping(value = "/user", consumes = {MediaType.APPLICATION_JSON})
public class UserFacadeREST {

    private UserDAO userDao;

    @Autowired
    public UserFacadeREST(UserDAO userDao) {
        this.userDao = userDao;
    }

    @PostMapping("/login")
    @Transactional
    public ResponseEntity login(@RequestBody String login) {
        JsonParser parser = new JsonParser();
        JsonObject o = parser.parse(login).getAsJsonObject();
        String password = o.get("password").getAsString();
        User user = userDao.login(o.get("email").getAsString(), password);

        if (user != null) {

            String token = Authentication.generateToken(user.getEmail(), user.getId());
            UserResponse response = new UserResponse(user.getId(), user.getEmail(), user.getName(), token);
            userDao.updateToken(token, user.getId());
            return ResponseEntity.ok(response);
        } else {
            return new ResponseEntity("Usuário inválido.", HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/logout")
    @Transactional
    public ResponseEntity logout(@RequestHeader("token") String token) {
        boolean validate = userDao.validate(token);

        if (validate) {

            userDao.logout(token);

            return ResponseEntity.ok().build();
        } else {
            return new ResponseEntity("Usuário inválido.", HttpStatus.UNAUTHORIZED);
        }
    }
}