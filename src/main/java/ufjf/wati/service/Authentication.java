/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufjf.wati.service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;

/**
 *
 * @author fernanda
 */
public class Authentication {
        
    public static String generateToken(String email, long id) {
        SecureRandom random = new SecureRandom();
        String randomString = new BigInteger(130, random).toString(32);
        String keySource = randomString + new Date() + id;
        byte[] tokenByte = Base64.getEncoder().encode(keySource.getBytes());
        String token = new String(tokenByte);
        
        return token;
    }  

}
