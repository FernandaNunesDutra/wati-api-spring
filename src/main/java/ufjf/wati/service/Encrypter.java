package ufjf.wati.service;

import ufjf.wati.exception.EncryptException;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Encrypter {
    private static final byte[] key = ("ekhilAitcapWarHy").getBytes();
    private static final SecretKey aesKey = new SecretKeySpec(key, "AES");

    private static Cipher aesCipher;

    public Encrypter() { }

    /**
     * @return the aesCipher
     */
    static Cipher getAesCipher() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        if (Encrypter.aesCipher == null) {
            Encrypter.aesCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            Encrypter.aesCipher.init(Cipher.ENCRYPT_MODE, aesKey);
        }
        return Encrypter.aesCipher;
    }

    public static byte[] encrypt(String text) {
        byte[] encrypt;

        try {

            encrypt = Encrypter.getAesCipher().doFinal(text.getBytes());
            return encrypt;

        }catch (Exception ex){
            throw new EncryptException();
        }
    }
}
