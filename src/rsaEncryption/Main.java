package rsaEncryption;
import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException {

        RSAKeyPairGenerator keyPairGenerator = new RSAKeyPairGenerator();
        byte[] encryptedMessage = BlockCipher.encryptMessage("Hallo", keyPairGenerator.getPublicKey());
        System.out.println(BlockCipher.decryptMessage(encryptedMessage, keyPairGenerator.getPrivateKey()));

        /*
        keyPairGenerator.writeKeyToFile("keys/public/publicKey", keyPairGenerator.getPublicKey().getEncoded());
        keyPairGenerator.writeKeyToFile("keys/private/privateKey", keyPairGenerator.getPrivateKey().getEncoded());
        System.out.println(Base64.getEncoder().encodeToString(keyPairGenerator.getPublicKey().getEncoded()));
        System.out.println(Base64.getEncoder().encodeToString(keyPairGenerator.getPrivateKey().getEncoded()));
        */
    }
}
