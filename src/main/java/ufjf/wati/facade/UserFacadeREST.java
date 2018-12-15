package ufjf.wati.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ufjf.wati.dao.UserDAO;
import ufjf.wati.dto.LoginDto;
import ufjf.wati.dto.UserDto;
import ufjf.wati.model.User;
import ufjf.wati.service.Authentication;
import ufjf.wati.service.Encrypter;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.transaction.Transactional;
import javax.ws.rs.core.MediaType;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

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
    public UserDto login(@RequestBody LoginDto loginDto) {


        User user = userDao.login(loginDto.getEmail(), Encrypter.encrypt(loginDto.getPassword()));

        String token = Authentication.generateToken(user.getId());
        UserDto response = new UserDto(user.getId(), user.getEmail(), user.getName(), token);
        userDao.updateToken(token, user.getId());

        return response;
    }

    @GetMapping("/logout")
    @Transactional
    public void logout(@RequestHeader("token") String token) {
        userDao.validateUserToken(token);

        userDao.logout(token);
    }
}
