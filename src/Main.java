import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Main extends PrimeFactorization{
    public static BigInteger functionOfEuler(ArrayList<BigInteger>  fact) {
        //уменьшаем все на 1
        BigInteger result=BigInteger.ONE;
        int count=1;
        for (int i = 0; i< fact.size()-1; i++){
            if((fact.get(i)).equals(fact.get(i+1)))
            {
                count++;
                if(i==fact.size()-2)
                    result=result.multiply(fact.get(i).pow(count+1).subtract(fact.get(i).pow(count)));
            }
            else
            {
                result=result.multiply(fact.get(i).pow(count).subtract(fact.get(i).pow(count-1)));
                if(i==fact.size()-2) {
                    result = result.multiply(fact.get(fact.size()-1).subtract(BigInteger.ONE));
                }
                count=1;
            }
        }
        return  result;
    }


    public static void main(String[] args) {
        BigInteger N = new BigInteger("190036018274508216586696675158436572823536393575281146244016687382409175715829258295815447406195299817455974397703288803952414902363922551");
        BigInteger e = new BigInteger("65537");
        ArrayList<BigInteger> factList = new ArrayList<BigInteger>();
       // factList = primeBigFactorize(N);

        factList.add(new BigInteger("43"));
        factList.add(new BigInteger("53"));
        factList.add(new BigInteger("103"));
        factList.add(new BigInteger("151"));
        factList.add(new BigInteger("179"));
        factList.add(new BigInteger("191"));
        factList.add(new BigInteger("197"));
        factList.add(new BigInteger("233"));
        factList.add(new BigInteger("271"));
        factList.add(new BigInteger("419"));
        factList.add(new BigInteger("2003"));
        factList.add(new BigInteger("9127"));
        factList.add(new BigInteger("12451"));
        factList.add(new BigInteger("301843"));
        factList.add(new BigInteger("94513"));
        factList.add(new BigInteger("829847"));
        factList.add(new BigInteger("86501"));
        factList.add(new BigInteger("2627657"));
        factList.add(new BigInteger("383527583"));
        factList.add(new BigInteger("24284414569"));
        factList.add(new BigInteger("516997109"));
        factList.add(new BigInteger("38388740763319"));
        factList.add(new BigInteger("92390129391924345677"));
        factList.add(new BigInteger("1438368113387747"));

        /*for (int i = 0; i < factList.size(); i++) {
            System.out.print(factList.get(i) + "  ");
        }*/

        ArrayList<BigInteger> factListfoeN = new ArrayList<BigInteger>();
        //1 способ Нахождение мультипликативно обратного:
        BigInteger foeN = finctionOfEuler(factList);
        BigInteger d1 = e.modInverse(foeN);
        System.out.println("Нахождение мультипликативно обратного: d1 = " + d1);
        //2 способ Расширенный алгоритм Евклида
        PrimeFactorization tmp = gcdWide(foeN,e);
        BigInteger d2 = tmp.y.add(foeN);
        System.out.println("Расширенный     алгоритм    Евклида:   d2 = " + d2);

        //3 способ Теорема Эйлера
        factListfoeN=primeBigFactorize(foeN);
        BigInteger ei = functionOfEuler(factListfoeN);
        BigInteger d3 =e.modPow(ei.subtract(BigInteger.ONE),foeN);
        System.out.println("Теорема Эйлера: d3 = " + d3);
        /*BigInteger chuz=new BigInteger("107780752640183316623877621926626667255632277594049956010452723776756367672898389642127643441438504500842702631649266809676968068810473473");
        if(ei.equals(foeN))
        System.out.println("ПРоверка");
        BigInteger d4 = e.modPow(finctionOfEuler(factListfoeN).subtract(BigInteger.ONE),foeN);
        System.out.println("Теорема         Эйлера:                d4 = " + d4);*/
//        BigInteger check = e.multiply(d).remainder(foeN);
//        System.out.println(check);



    }


}
