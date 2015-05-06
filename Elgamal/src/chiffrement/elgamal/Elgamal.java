/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chiffrement.elgamal;

import java.math.BigInteger;
import java.util.Random;

/**
 *
 * @author iks-d
 */
public class Elgamal {
    
    public Elgamal(int nb_bits, int certainty, Random prg)
    {
     this.nb_bits = nb_bits;
     this.certainty = certainty;
     this.prg = prg;
    }
    int nb_bits, certainty;
    Random prg;
    
    public static BigInteger getPrime(int nb_bits, int certainty, Random prg)
    {
        System.out.print("loading");
        while(true)
            {
             System.out.print(".");
             BigInteger pPrime = new BigInteger(nb_bits, certainty, prg); 
             BigInteger p = pPrime.multiply(new BigInteger("2"));//création de p = 2*p'+1
             p = p.add(BigInteger.ONE); 
             if(p.isProbablePrime(certainty))
             return p;
            }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Random mohamed = new Random();
        Elgamal k = new Elgamal(512, 40, mohamed);
        BigInteger p = k.getPrime(512, 40, mohamed);
        System.out.println();
        System.out.println("on a un nombre premier p = ");
        System.out.println (p);
        BigInteger g;
        do 
        {
            System.out.println(".");
        Elgamal l = new Elgamal(512, 40, mohamed);
        g = l.getPrime(512, 40, mohamed);
        //System.out.println("\non a un nombre premier g = ");
        //System.out.println (g);
        }
        //tirage de g
        while (p.compareTo(g)<0);
        System.out.println("\non a un nombre premier g = ");
        System.out.println (g);
        System.out.println(p.subtract(g));
    }
    
}
