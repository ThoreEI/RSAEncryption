package rsaEncryption;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

public class BlockCipher {

    public static byte[] encryptMessage(String message, PublicKey publicKey) {
        Cipher blockCipher = null;
        try {
            blockCipher = Cipher.getInstance("RSA");
            blockCipher.init(Cipher.ENCRYPT_MODE, publicKey);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException securityException) {
            securityException.printStackTrace();
        }

        byte[] ciphertext;
        try {
            assert blockCipher != null;
            ciphertext =  blockCipher.doFinal(message.getBytes());
        } catch (IllegalBlockSizeException | BadPaddingException securityException) {
            throw new RuntimeException(securityException);
        }
        return ciphertext;
    }


    public static String decryptMessage(byte[] ciphertext, PrivateKey privateKey) {
        byte[] plainText = null;
        Cipher blockCipher = null;
        try {
            blockCipher = Cipher.getInstance("RSA");
            blockCipher.init(Cipher.DECRYPT_MODE, privateKey);
        } catch(NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException securityException) {
            securityException.printStackTrace();
        }

        try {
            assert blockCipher != null;
            plainText = blockCipher.doFinal(ciphertext);
        } catch (IllegalBlockSizeException | BadPaddingException securityException) {
            securityException.printStackTrace();
        }

        assert plainText != null;
        return new String(plainText);
    }
}
