/**
 * fprime.java
 *
 * A program to find prime factors of large numbers.
 *
 * Copyright(c) 1999, Particle
 */

import java.lang.*;
import java.io.*;
import java.math.*;

/**
 * implements a class to print prime factors of a
 * large number.
 * @author Particle
 * @version 0.0.1
 */
public class fprime implements Runnable{

    /**
     * finds the smallest factor of the number
     * (usually prime) if number returned is a 1
     * then the parameter is prime.
     *
     * @param n A BigInteger value to factor
     * @return The smallest factor of the number, or 1 if n is prime
     */
    private BigInteger factor(BigInteger n){
        BigInteger i = BigInteger.valueOf(2);
        BigInteger j = n;
        for(;i.compareTo(j) < 0;i = i.add(BigInteger.valueOf(1))){
            BigInteger[] q = n.divideAndRemainder(i);
            if(q[1].compareTo(BigInteger.valueOf(0)) == 0)
                return i;
            j = q[0];
        }
        return BigInteger.valueOf(1);
    }

    /**
     * prints factors of a BigInteger to a PrintStream
     *
     * @param n The BigInteger to factor (and print factors of)
     * @param ps The PrintStream of where to print the factors
     */
    private void printfactors(BigInteger n,PrintStream ps){
        BigInteger i = factor(n);
        if(i.compareTo(BigInteger.valueOf(1)) == 0)
            ps.print(""+n.toString(10)+" ");
        else{
            printfactors(i,ps);
            printfactors(n.divide(i),ps);
        }
    }

    /**
     * Runnable interface (execution happens here)
     */
    public void run(){
        BufferedReader r = new BufferedReader(
                new InputStreamReader(System.in));
        System.out.print(": ");
        String buffer = null;
        try{
            buffer = r.readLine();
        }catch(IOException e){
            System.err.println(e.toString());
            return;
        }
        while(buffer != null && !buffer.equals("quit")){
            BigInteger i = null;
            try{
                if(buffer.length() != 0)
                    i = new BigInteger(buffer,10);
            }catch(NumberFormatException e){
                System.err.println(e.toString());
            }
            if(i != null)
                printfactors(i,System.out);
            System.out.print("\n: ");
            try{
                buffer = r.readLine();
            }catch(IOException e){
                System.err.println(e.toString());
                return;
            }
        }
    }

    /**
     * main to kick off the execution of this app
     * @param args Command line parameters.
     */
    public static void main(String[] args){
        Thread thread = new Thread(new fprime());
        thread.start();
    }
}