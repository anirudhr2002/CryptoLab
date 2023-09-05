package Ciphers.AffineCipher;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class AffineClient {

    private static int a, b;
    private static String cipherText;
    private static String plainText;
    public static void main(String[] args){
        try{

            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter plain text: ");
            plainText = scanner.nextLine();

            System.out.print("Enter the values for a and b: ");
            a = scanner.nextInt();
            b = scanner.nextInt();

            scanner.close();

            cipherText = encrypt(plainText, a, b);
            System.out.println("Encrypted Text: " + cipherText);

            Socket socket = new Socket("localhost", 7118);
            DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
            dout.writeUTF(cipherText);
            dout.flush();

            dout.writeInt(a);
            dout.writeInt(b);

            dout.close();
            socket.close();

        } catch(Exception e){
            System.out.println(e);
        }
    }


    public static String encrypt(String plaintext, int a, int b) {
        StringBuilder ciphertext = new StringBuilder();

        for (char ch : plaintext.toCharArray()) {
                char encryptedChar = (char) (((a * (ch - 'a') + b) % 26) + 'a');
                ciphertext.append(encryptedChar);
        }

        return ciphertext.toString();
    }
}
