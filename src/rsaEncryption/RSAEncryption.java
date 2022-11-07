package rsaEncryption;
import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;


public class RSAEncryption {

    public static CompletableFuture<BigInteger> getPrimeNumber(final int LENGTH_OF_BYTES) {
        Supplier<BigInteger> primeSupplier = () -> {
            CompletableFuture<Object> completableFuturePrime = CompletableFuture.anyOf(
                    CompletableFuture.supplyAsync(
                            () -> BigInteger.probablePrime(LENGTH_OF_BYTES, ThreadLocalRandom.current())));
            return (BigInteger) completableFuturePrime.join();
        };
        return CompletableFuture.supplyAsync(primeSupplier);
    }


    public static BigInteger[] generateKeyValue(final int LENGTH_OF_BYTES) {
        Random random = new Random();
        CompletableFuture<BigInteger> futurePrimeP =  getPrimeNumber(LENGTH_OF_BYTES);
        CompletableFuture<BigInteger> futurePrimeQ = getPrimeNumber(LENGTH_OF_BYTES);
        CompletableFuture<BigInteger> futureN = futurePrimeP.thenCombineAsync(futurePrimeQ,BigInteger::multiply);
        CompletableFuture<BigInteger> futurePhi = futurePrimeP.thenApplyAsync(
                b -> b.subtract(BigInteger.ONE)).thenCombineAsync(
                futurePrimeQ.thenApplyAsync(b -> b.subtract(BigInteger.ONE)),
                BigInteger::multiply);

        /*
            Definitions
                        N = p*q
                        phi(N) = (p-1)*(q-1)
                        e = natural odd number & we have  1 < e < phi
                        d = modular multiplicative inverse
                        P = e*N is the public key
                        S = d*N is the private key
         */

        BigInteger N = futureN.join();
        BigInteger phi = futurePhi.join();

        BigInteger e = new BigInteger(phi.bitLength() / 3, random);
        while (!e.gcd(phi).equals(BigInteger.ONE))
            e = new BigInteger(phi.bitLength() / 3, random);

        BigInteger d = e.modInverse(phi);
        return new BigInteger[]{N,e,d};
    }
}

