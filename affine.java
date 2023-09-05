/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package substitution;

import java.util.Scanner;

/**
 *
 * @author student
 */
public class affine {
    public static int gcd(int a, int b){
        int ans;
        if((a==0)&&(b==0))
                return 0;
        else if((a==0)&&(b!=0))
            return b;
        else if((b==0)&&(a!=0))
            return a;
        else
            return(gcd(b,a%b));
    }
    public static int naive_method(int a, int m){
        for(int i=0;i<m;i++){
            if(((a*i)%m==1)&&(gcd(a,m)==1))
                return i;
        }
        return -1;
    }
    public static void main(String[] args) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        Scanner ds = new Scanner(System.in);
        System.out.print("Enter text to be encrypted : ");
        int a=5,b=8,ind;
        String inp = new String();
        String cipher="";
        String decipher="";
        inp = ds.nextLine();
        System.out.print("\nEnter values for a : ");
        a = ds.nextInt();
        System.out.print("\nEnter values for b : ");
        b = ds.nextInt();
        //Encryption
        for(int i =0;i<inp.length();i++){
            char ch = inp.charAt(i);
            if (Character.isAlphabetic(ch)){
               ind = a*(alphabet.indexOf(ch))+b;
            if(ind>alphabet.length())
                ind=ind%(alphabet.length());
            cipher+=alphabet.charAt(ind); 
            }
            else{
                cipher += ch;
            }
            
        }
        System.out.println("Encrypted Text is : "+cipher);
        
        //Decryption
        int k = naive_method(5,26);
        for (int i = 0; i < cipher.length(); i++) {
            char ch = cipher.charAt(i);
            if (Character.isAlphabetic(ch)) {
                ind = (k*(alphabet.indexOf(ch)-b))%26;
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
