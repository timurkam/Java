import sun.swing.BakedArrayList;

import java.util.ArrayList;
import java.math.BigInteger;
import java.util.LinkedList;

import static java.math.BigInteger.ONE;

/**
 * This class is used to calculate the prime factorization of any positive integer.
 * 
 * @author Michael Yaworski of http://mikeyaworski.com
 * @version October 10, 2015
 */
public class PrimeFactorization {
    
    /**
     * Reduces the parameter n into a product of only prime numbers
     * and returns a list of those prime number factors as longs.
     * 
     * @param  n  the number to prime factorize
     * @return an ArrayList (of longs) of only prime factors of the parameter n
     */

    BigInteger d;
    BigInteger x;
    BigInteger y;

    PrimeFactorization(BigInteger one, BigInteger two, BigInteger three) {
        d = one;
        x = two;
        y = three;
    }

    PrimeFactorization(){}

    public static PrimeFactorization gcdWide(BigInteger a, BigInteger b) {
        //System.out.println("a = " + a + " b = " + b);
        PrimeFactorization temphere = new PrimeFactorization(a, BigInteger.ONE, BigInteger.ZERO);
        PrimeFactorization temphere2 = new PrimeFactorization();

        if (b == BigInteger.ZERO) {
            //System.out.println(" a = " + temphere.x + "  b = " + temphere.y + "  d = " + temphere.d);
            return temphere;
        }

        temphere2 = gcdWide(b, a.mod(b));
        temphere = new PrimeFactorization();
        temphere.d = temphere2.d;

        temphere.x = temphere2.y;
        temphere.y = temphere2.x.subtract(a.divide(b).multiply(temphere2.y));
        //System.out.println(" a = " + temphere.x + "  b = " + temphere.y + "  d = " + temphere.d);
        return temphere;
    }


    public static ArrayList<Long> primeFactorize(long n) {
        ArrayList<Long> primeFactors = new ArrayList<Long>();
        
        long primeFactor = 0L;

        for (long i = 2L; i <= n / i; ) { // smallest prime factor to the square root of n (largest possible factor of n)
            if (n % i == 0) { // the prime number i is a factor of n (i will never go into n if it's composite since the prime factor of that compositite number would have already been tested)
                primeFactor = i; // therefore, this is a prime factor of n
                primeFactors.add(primeFactor);
                n /= i; // divide out that prime factor from n to get the rest of the prime factors
                // don't increment i: test if this same prime factor goes into n multiple times (e.g. 18 = 2*3*3)
            } else {
                i++; // i is not a prime factor of n, so increment
            }
        }
        
        if (primeFactor < n) { // n had no more prime factors, so n is a prime factor
            primeFactors.add(n);
        } else { // n was divided down to 1, meaning that the last prime factor divided itself out. therefore, it is the last prime factor
            primeFactors.add(primeFactor);
        }
        
        return primeFactors;
    }
    
    /**
     * Reduces the parameter n into a product of only prime numbers
     * and returns a list of those prime number factors as BigIntegers.
     * 
     * @param  n  the number to prime factorize
     * @return an ArrayList (of BigIntegers) of only prime factors of the parameter n
     */

    public static ArrayList<BigInteger> primeBigFactorize(BigInteger n) {
        ArrayList<BigInteger> primeFactors = new ArrayList<BigInteger>();
        BigInteger primeFactor = BigInteger.ZERO;
    
        for (BigInteger i = new BigInteger("2"); i.compareTo(n.divide(i)) <= 0; ) {

            if (n.mod(i).longValue() == 0) {
                //System.out.println("Простой делитель N = " + i);
                primeFactor = i;
                primeFactors.add(primeFactor);
                n = n.divide(i);
            } else {
                i = i.nextProbablePrime();
            }
        }
        
        if (primeFactor.compareTo(n) < 0) {
            primeFactors.add(n);
        } else {
            primeFactors.add(primeFactor);
        }
        
        return primeFactors;
    }

    public static ArrayList<BigInteger> primeBigFactorizeTest(BigInteger n) {
        ArrayList<BigInteger> primeFactors = new ArrayList<BigInteger>();
        BigInteger primeFactor = BigInteger.ZERO;

        for (BigInteger i = new BigInteger("2"); i.compareTo(n.divide(i)) <= 0; ) {

            if (n.mod(i).longValue() == 0) {
                //System.out.println("Простой делитель N = " + i);
                primeFactor = i;
                primeFactors.add(primeFactor);
                n = n.divide(i);
            } else {
                i = i.add(ONE);
            }
        }

        if (primeFactor.compareTo(n) < 0) {
            primeFactors.add(n);
        } else {
            primeFactors.add(primeFactor);
        }

        return primeFactors;
    }


    public static BigInteger finctionOfEuler(ArrayList<BigInteger>  fact) {
        //уменьшаем все на 1
        ArrayList<BigInteger> foe = new ArrayList<BigInteger>();
        for (int i = 0; i< fact.size(); i++){
            foe.add(fact.get(i).subtract(ONE));
        }
//        //выводим
//        System.out.println();
//        for (int i = 0; i< foe.size(); i++){
//            System.out.print(foe.get(i)+" ");;
//        }
        //перемножаем
        BigInteger result = ONE;
        for (int i = 0; i< foe.size(); i++){
            result = result.multiply(foe.get(i));
        }

        //System.out.println();
        //System.out.println("Функция Эйлера для заданного числа N: foeN =  " + result);

        return  result;
    }


}