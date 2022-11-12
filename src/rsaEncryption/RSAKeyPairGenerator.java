package rsaEncryption;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;

public class RSAKeyPairGenerator {
    private final PublicKey PUBLIC_KEY;
    private final PrivateKey PRIVATE_KEY;

    public RSAKeyPairGenerator() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(4096);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PUBLIC_KEY = keyPair.getPublic();
        PRIVATE_KEY = keyPair.getPrivate();
    }

    public void writeKeyToFile(String destinationPath, byte[] key) throws IOException {
        File filename = new File(destinationPath);
        filename.getParentFile().mkdirs();
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(filename);
            fileOutputStream.write(key);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException ioException) {
            System.err.println(ioException.getMessage());
        }
    }



    public PublicKey getPublicKey() {
        return PUBLIC_KEY;
    }

    public PrivateKey getPrivateKey() {
        return PRIVATE_KEY;
    }


}