/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package substitution;
import java.util.*;

public class ceaser {
    public static void main(String[] args) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        Scanner ds = new Scanner(System.in);
        String cipher = "";
        String decipher = "";
        System.out.print("Enter text to be encrypted: ");
        int s =3;
        String inp = ds.nextLine();
        int ind;
        inp = inp.toLowerCase();
        // Encryption
        for (int i = 0; i < inp.length(); i++) {
            char ch = inp.charAt(i);
            if (Character.isAlphabetic(ch)) {
                ind = alphabet.indexOf(ch) + s;
                if (ind >= alphabet.length())
                    ind = ind % alphabet.length();
                cipher += alphabet.charAt(ind);
            } else {
                cipher += ch;
            }
        }

        System.out.println("Encrypted Text is: " + cipher);
        // Decryption
        for (int i = 0; i < cipher.length(); i++) {
            char ch = cipher.charAt(i);
            if (Character.isAlphabetic(ch)) {
                ind = alphabet.indexOf(ch) - s;
                if (ind < 0)
                    ind = alphabet.length() + ind;
                decipher += alphabet.charAt(ind);
            } else {
                decipher += ch;
            }
        }
        System.out.println("Decrypted Text is: " + decipher);
    }
}
