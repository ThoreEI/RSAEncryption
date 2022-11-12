package rsaEncryption.parallizedKeyGeneration;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        BigInteger[] keyValue = ParallelizedRSAKeyPairGenerator.generateKeyValue(4096);
        for (BigInteger bigInteger : keyValue)
            System.out.println(bigInteger);
    }
}
