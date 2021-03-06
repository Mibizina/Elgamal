/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chiffrement.elgamal;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
     *Ordre des nombres dans Z/nZ
     */
    private static BigInteger bi;
    public static BigInteger ordre(BigInteger n)
    {
        for (BigInteger a = BigInteger.ONE; a.compareTo(n) < 0; a = a.add(BigInteger.ONE))
        {
            for (BigInteger b = BigInteger.ONE; b.compareTo(n.subtract(BigInteger.ONE)) <= 0; b = b.add(BigInteger.ONE))
            {
                //System.out.println("a = " + a + "; b = " + b + "; n = " + n);
                BigInteger z = a.modPow(b, n);
                if(z.equals(BigInteger.ONE))
                    System.out.println("l'ordre de " + a +" dans " + n + " est " + b);
                bi = b;
            }//Fin boucle 2
        } //Fin boucle 1
        return bi;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Random mohamed = new Random();
        Elgamal k = new Elgamal(512, 40, mohamed);
        BigInteger p = k.getPrime(512, 40, mohamed);
        System.out.println();
        System.out.println("on a un nombre premier p = ");
        System.out.println (p);
        BigInteger g, g2, gpp, g2pp, pPrime, pPrime2, gpp2;
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
        //System.out.println(p.subtract(g));
        g2 = g.modPow(BigInteger.ONE.add(BigInteger.ONE), p);//calcul de g² modulo p
        System.out.println("g² = " + g2);
        pPrime  = p.subtract(BigInteger.ONE).divide(new BigInteger("2"));//calcul de p' à partir de p ==>p' = (p-1)/2
        gpp = g.modPow(pPrime, p); //calcul de g^p' modulo p
        System.out.println("p' = " + pPrime);
        System.out.println("g^p' = " + gpp);
        g2pp = g.modPow(pPrime.multiply(new BigInteger("2")), p);//calcul de g^2p' modulo p
        System.out.println("g^2p' = " + g2pp);
        
        //ecriture dans un fichier texte
        String nomfichier;
        nomfichier = "Elgamal.txt";
        PrintWriter sortie = new PrintWriter(new FileWriter(nomfichier));
        sortie.println("\"on a un nombre premier p = \"" + p);//sortie.println(X) pour ecrire X dans le fichier texte
        sortie.println("p' = " + pPrime);
        sortie.println("on a un nombre premier g = " + g );
        sortie.println("g² = " + g2);
        sortie.println("g^p' = " + gpp);
        sortie.println("g^2p' = " + g2pp);
        sortie.close();//pour terminer l'écriture
        
        //utilisation ordre Z/23Z
        System.out.println("On commence ordre");
        BigInteger x = new BigInteger("23");
        BigInteger c = new BigInteger("21");
        BigInteger z = ordre(c);
        BigInteger q = ordre(x);
        BigInteger r = ordre(g);//Citer les ordres des éléments de g est trop long avec cet algorithme
    }
    
}
