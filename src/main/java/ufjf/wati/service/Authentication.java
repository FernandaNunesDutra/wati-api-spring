package ufjf.wati.service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;

public class Authentication {
        
    public static String generateToken(long id) {
        SecureRandom random = new SecureRandom();
        String randomString = new BigInteger(130, random).toString(32);
        String keySource = randomString + new Date() + id;

        byte[] tokenByte = Base64.getEncoder().encode(keySource.getBytes());
        return new String(tokenByte);
    }  

}
